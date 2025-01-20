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

    public MasterConf generateMasterconf() {
        return new MasterConf(this);
    }
}
