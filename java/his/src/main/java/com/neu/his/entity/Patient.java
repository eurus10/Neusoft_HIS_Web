package com.neu.his.entity;

import java.util.Date;

public class Patient {
    private int id;
    private String name;
    private int gender;
    private String birth; // YYYY-mm-dd
    private int age;
    private String addr;
    private int tel;
    private int recordId;

    public Patient(int id, String name, int gender, String birth, int age, String addr, int tel, int recordId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.age = age;
        this.addr = addr;
        this.tel = tel;
        this.recordId = recordId;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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
