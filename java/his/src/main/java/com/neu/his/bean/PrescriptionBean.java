package com.neu.his.bean;

public class PrescriptionBean {
    private int id;
    private String drugs;

    public PrescriptionBean(int id, String drugs) {
        this.id = id;
        this.drugs = drugs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }
}
