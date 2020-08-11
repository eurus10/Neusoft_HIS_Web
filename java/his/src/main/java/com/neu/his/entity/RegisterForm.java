package com.neu.his.entity;

public class RegisterForm {
    private int id;
    private int patientId;
    private int departmentId;
    private int doctorId;
    private int level;
    private boolean recordNeeded;
    private double cost;
    private boolean diagnosed;

    public RegisterForm(int id, int patientId, int departmentId, int doctorId, int level, boolean recordNeeded, double cost, boolean diagnosed) {
        this.id = id;
        this.patientId = patientId;
        this.departmentId = departmentId;
        this.doctorId = doctorId;
        this.level = level;
        this.recordNeeded = recordNeeded;
        this.cost = cost;
        this.diagnosed = diagnosed;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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

    public boolean isDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(boolean diagnosed) {
        this.diagnosed = diagnosed;
    }
}
