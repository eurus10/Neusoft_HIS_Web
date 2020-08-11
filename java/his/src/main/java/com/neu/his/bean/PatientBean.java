package com.neu.his.bean;

import com.neu.his.entity.Patient;

public class PatientBean {
    private int id;
    private String name;
    private String gender;
    private String birth;
    private int age;
    private String addr;
    private int tel;
    private int recordId;

    public PatientBean(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.gender = patient.getGender() == 0 ? "男" : "女";
        this.birth = patient.getBirth();
        this.age = patient.getAge();
        this.addr = patient.getAddr();
        this.tel = patient.getTel();
        this.recordId = patient.getRecordId();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
