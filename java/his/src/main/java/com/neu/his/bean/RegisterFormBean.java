package com.neu.his.bean;

import com.neu.his.entity.RegisterForm;

public class RegisterFormBean {
    private int id;
    private int patientId;
    private String department;
    private String doctor;
    private String level;
    private boolean recordNeeded;
    private double cost;
    private String diagnosed;

    public RegisterFormBean(RegisterForm registerForm) {
        id = registerForm.getId();
        patientId = registerForm.getPatientId();
        department = null;
        doctor = null;
        if (registerForm.getLevel() == 1) level = "普通号";
        else if (registerForm.getLevel() == 2) level = "急诊号";
        else level = "专家号";
        recordNeeded = registerForm.isRecordNeeded();
        cost = registerForm.getCost();
        diagnosed = registerForm.isDiagnosed() ? "已诊断" : "未诊断";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isRecordNeeded() {
        return recordNeeded;
    }

    public void setRecordNeeded(boolean recordNeeded) {
        this.recordNeeded = recordNeeded;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }
}
