package com.oocl;

import java.util.List;

public class Player {

    private String name;
    private List<String> pokers;

    public Player(String name, List<String> pokers) {
        this.name = name;
        this.pokers = pokers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPokers() {
        return pokers;
    }

    public void setPokers(List<String> pokers) {
        this.pokers = pokers;
    }
}
