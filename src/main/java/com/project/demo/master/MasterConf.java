package com.project.demo.master;

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
        return "jdbc:mysql://" + getMasterIp() + ":3306/pokemon";
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
}
