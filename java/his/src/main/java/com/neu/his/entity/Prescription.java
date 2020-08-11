package com.neu.his.entity;

public class Prescription {
    private int id;
    private String name;
    private String drugs;

    public Prescription(int id, String name, String drugs) {
        this.id = id;
        this.name = name;
        this.drugs = drugs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }
}
