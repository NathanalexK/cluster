package com.project.demo.haproxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HaProxyInstance {
    private String name;
    private int port;
    private String mode;
    private String loadBalancer;
    private List<Server> serverList = new ArrayList<>();


    public HaProxyInstance(){}



    public HaProxyInstance(String name, List<String> frontLines, List<String> backLines) {
        this.name = name;
        for(String line: frontLines){
            line = line.trim();
            if(line.startsWith("bind")){
                this.port = Integer.parseInt(line.split(":")[1]);

            } else if(line.startsWith("mode")){
                this.mode = line.split(" ")[1];
            }
        }

        for(String line: backLines) {
            line = line.trim();
            if(line.startsWith("balance")){
                this.loadBalancer = line.split(" ")[1];

            } else if(line.startsWith("server")) {
                this.serverList.add(new Server(line.replace("server ", "")));
            }
        }
    }

    public Optional<Server> getServerByName (String serverName) {
        return this.getServerList().stream().filter(server -> server.getName().equals(serverName)).findFirst();
    }

    public void deleteServer(String serverName) {
        Optional<Server> serverToDelete = this.getServerByName(serverName);
        if(serverToDelete.isPresent()) {
            this.getServerList().remove(serverToDelete.get());
        }
        System.err.println("No Server found having name: " + serverName + " in instance: " + this.getName());
    }

    @Override
    public String toString(){
        String str = "";
        str += "frontend " + name + "_front\n";
        str += "\tbind *:" + port + "\n";
        str += "\tmode " + mode + "\n";
        str += "\tdefault_backend " + name + "_backend\n";
        str += "\n";
        str += "backend " + name + "_backend\n";
        str += "\tmode " + mode + "\n";
        if(mode.equalsIgnoreCase("tcp")) {
            str += "\toption tcp-check\n";
        }
        str += "\tbalance " + loadBalancer + "\n";
        for(Server server: serverList){
            str += "\t" + server + "\n";
        }
        str += "\n";
        return str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLoadBalancer() {
        return loadBalancer;
    }

    public void setLoadBalancer(String loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public void addServer(Server server) {
        this.serverList.add(server);
    }

    public List<Server> getServerList() {
        return serverList;
    }

    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
    }

    //    public void fromString(List<String> frontLines, List<String> backLines) {
//        HaProxyInstance instance = new HaProxyInstance();
//
//
//
//    }
//


}
