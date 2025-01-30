package com.project.demo.master;

public class InstancePc {
    String ip;
    String user;
    String password;


    public InstancePc(String ip, String user, String password) {
        this.ip = ip;
        this.user = user;
        this.password = password;
    }

//    public MasterConf generateMasterconf() {
//        return new MasterConf(this);
//    }

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
