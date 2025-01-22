package com.project.demo.master;

public class MasterConf {
    String masterLogFile;
    String position;
    String masterIp;
    String user;
    String password;

    public MasterConf(String masterIp, String user, String password) {
        this.masterIp = masterIp;
        this.user = user;
        this.password = password;
    }

    public String jdbcUrl() {
        return "jdbc:mysql://" + getMasterIp() + ":3306/pokemon";
    }

    public MasterConf(InstancePc pc) {
        this.masterIp = pc.ip;
        this.user = pc.user;
        this.password = pc.password;
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
}
