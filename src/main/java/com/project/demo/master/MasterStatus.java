package com.project.demo.master;

public class MasterStatus {
    String masterLogFile;
    String position;
    String masterIp;
    String user;
    String password;

    public MasterStatus(String masterIp, String user, String password) {
        this.masterIp = masterIp;
        this.user = user;
        this.password = password;
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
}
