package com.neu.his.bean;

import com.neu.his.entity.InvoiceEntry;

public class InvoiceEntryBean {
    private int order;
    private int id;
    private int affairId;
    private String affairName;
    private int num;
    private double cost;
    private String paid;
    private String state;

    public InvoiceEntryBean() {}

    public InvoiceEntryBean(InvoiceEntry entry) {
        this.order = entry.getOrder();
        this.id = entry.getId();
        this.affairId = entry.getAffairId();
        this.affairName = null;
        this.num = entry.getNum();
        this.cost = entry.getCost();
        this.paid = entry.isPaid() ? "已缴费" : "未缴费";
        this.state = entry.isState() ? "已出药" : "未出药";
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAffairId() {
        return affairId;
    }

    public void setAffairId(int affairId) {
        this.affairId = affairId;
    }

    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
