package com.project.demo.master;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MasterConf {
    String masterLogFile;
    String position;
    String ip;
    String masterIp;
    String user;
    String password;

    public MasterConf(String ip ,String masterIp, String user, String password) {
        this.ip = ip;
        this.masterIp = masterIp;
        this.user = user;
        this.password = password;
    }

//    public MasterConf(InstancePc pc) {
//        this.masterIp = pc.ip;
//        this.user = pc.user;
//        this.password = pc.password;
//    }

    public String jdbcUrl() {
        return "jdbc:mysql://" + getIp() + ":3306/pokemon";
    }

    public String jdbcUrlMaster() {
        return "jdbc:mysql://" + getMasterIp() + ":3306/?allowMultiQueries=true&allowLoadLocalInfile=true&allowPublicKeyRetrieval=true";
    }

    public String getMasterLogFile() {
        return masterLogFile;
    }

    public void setMasterLogFile(String masterLogFile) {
        this.masterLogFile = masterLogFile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMasterIp() {
        return masterIp;
    }

    public void setMasterIp(String masterIp) {
        this.masterIp = masterIp;
    }

    public String generateMasterQuery() {
        return "CHANGE MASTER TO" +
                "    MASTER_HOST='" + masterIp + "'," +
                "    MASTER_USER='" + user + "'," +
                "    MASTER_PASSWORD='" + password + "'," +
                "    MASTER_LOG_FILE='" + masterLogFile + "'," +
                "    MASTER_LOG_POS=" + position + ";";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void startSlaving() throws IOException, InterruptedException {
        String commande = "STOP REPLICA;" + generateMasterQuery() + "START REPLICA;";

        ProcessBuilder processBuilder = new ProcessBuilder(
                "mysql", "-u", "replicator", "-h", this.ip , "-proot", "-e", commande
        );

        // Exécuter le processus
        Process process = processBuilder.start();

        // Lire la sortie du processus
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Lire les erreurs
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

        // Attendre la fin du processus
        int exitCode = process.waitFor();
        System.out.println("Process terminé avec code : " + exitCode);
    }
}
