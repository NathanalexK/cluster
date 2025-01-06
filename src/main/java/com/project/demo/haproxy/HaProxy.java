package com.project.demo.haproxy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HaProxy {
    private static HaProxy instance;
    private File configFile = null;
    private List<HaProxyInstance> instances = new ArrayList<>();

    public static HaProxy getInstance() {
        if(instance == null) {
            instance = new HaProxy("/etc/haproxy/haproxy.cfg");
        }
        return instance;
    }

    public HaProxy(String filepath) {
        this.configFile = new File(filepath);
        try {
            readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addInstance(HaProxyInstance instance) {
        this.instances.add(instance);
    }

    public HaProxyInstance findInstanceByName(String name){
        for(HaProxyInstance inst: instances) {
            if(inst.getName().equalsIgnoreCase(name)){
                return inst;
            }
        }
        return null;
//        throw new RuntimeException("No HAProxy instance found for: " + name);
    }

    public Map<HaProxyInstance, List<Server>> getAllSevers() {
        Map<HaProxyInstance, List<Server>> map = new HashMap<>();
        for(HaProxyInstance inst : instances) {
            map.put(inst, inst.getServerList());
        }
        return map;
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        Stream<String> stream = reader.lines();
        List<String> instanceNames = stream.filter(line -> line.startsWith("backend")).map(line -> line.replace("backend ", "").split("_")[0]).toList();

        for(String instanceName: instanceNames) {
            instances.add(new HaProxyInstance(instanceName, getFrontendConfigurationOf(instanceName), getBackendConfigurationOf(instanceName)));
        }
    }

    public void rewrite() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
        writer.write(getDefaultConfiguration());
        writer.newLine();
        for(HaProxyInstance instance: instances){
            writer.write(instance.toString());
        }
        writer.close();
        try {
            restart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        readFile();
    }

    public void restart() throws IOException, InterruptedException {
        String[] command = {"sudo", "-S", "/bin/bash", "/home/nathanalex/Documents/cluster_web/demo/src/main/java/com/project/demo/haproxy/script.sh"};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            writer.write("1397\n");
            writer.flush();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        int exitCode = process.waitFor();
        if(exitCode == 0) {
            System.out.println("HAProxy restarted successfully.");

        } else {
            System.err.println("Failed to restart HAProxy, exit code = " + exitCode);
        }
    }

    public List<String> getBackendConfigurationOf(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        boolean begin = false;

        List<String> configLines = new ArrayList<>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            if(begin) {
                if(line.isEmpty()){
                    break;
                }

                configLines.add(line);
            }

            if(line.startsWith("backend " + name)) {
                begin = true;
            }
        }

        return configLines;
    }

    public List<String> getFrontendConfigurationOf(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        boolean begin = false;
        List<String> configLines = new ArrayList<>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            if(begin) {
                if(line.isEmpty()){
                    break;
                }
                configLines.add(line);
            }

            if(line.startsWith("frontend " + name)) {
                begin = true;
            }
        }
        return configLines;
    }


    @Override
    public String toString() {
        return "HaProxy{" +
            "instances=" + instances +
            '}';
    }

    public String getDefaultConfiguration() {
        return
            """
            global
                log /dev/log	local0
                log /dev/log	local1 notice
                chroot /var/lib/haproxy
                stats socket /run/haproxy/admin.sock mode 660 level admin
                stats timeout 30s
                user haproxy
                group haproxy
                daemon
            
                # Default SSL material locations
                ca-base /etc/ssl/certs
                crt-base /etc/ssl/private
            
                # See: https://ssl-config.mozilla.org/#server=haproxy&server-version=2.0.3&config=intermediate
                    ssl-default-bind-ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:DHE-RSA-AES128-GCM-SHA256:DHE-RSA-AES256-GCM-SHA384
                    ssl-default-bind-ciphersuites TLS_AES_128_GCM_SHA256:TLS_AES_256_GCM_SHA384:TLS_CHACHA20_POLY1305_SHA256
                    ssl-default-bind-options ssl-min-ver TLSv1.2 no-tls-tickets
            
            defaults
                log	global
                mode	http
                option	httplog
                option	dontlognull
                    timeout connect 5000
                    timeout client  50000
                    timeout server  50000
                errorfile 400 /etc/haproxy/errors/400.http
                errorfile 403 /etc/haproxy/errors/403.http
                errorfile 408 /etc/haproxy/errors/408.http
                errorfile 500 /etc/haproxy/errors/500.http
                errorfile 502 /etc/haproxy/errors/502.http
                errorfile 503 /etc/haproxy/errors/503.http
                errorfile 504 /etc/haproxy/errors/504.http
            """;
    }

    public static void setInstance(HaProxy instance) {
        HaProxy.instance = instance;
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public List<HaProxyInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<HaProxyInstance> instances) {
        this.instances = instances;
    }
}
