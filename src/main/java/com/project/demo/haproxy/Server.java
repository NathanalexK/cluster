package com.project.demo.haproxy;

public class Server {
    private String name;
    private String ipAdress;
    private int port = 80;

    public Server(){}

    public Server(String name, String ipAdress, int port) {
        this.name = name;
        this.ipAdress = ipAdress;
    }

    public Server(String str) {
        String[] split = str.split(" ");
        this.name = split[0];
        String[] split2 = split[1].split(":");
        this.ipAdress = split2[0];
        if(split2.length > 1) {
            this.port = Integer.parseInt(split2[1]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "server " + name + " " + ipAdress + ":" + port + " check";
    }
}
