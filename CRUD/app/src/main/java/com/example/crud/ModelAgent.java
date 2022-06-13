package com.example.crud;

public class ModelAgent {

    private  String agent;
    private  String skiils;
    private  String key;

    public ModelAgent() {

    }

    public ModelAgent(String agent, String skiils) {
        this.agent = agent;
        this.skiils = skiils;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getSkiils() {
        return skiils;
    }

    public void setSkiils(String skiils) {
        this.skiils = skiils;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
