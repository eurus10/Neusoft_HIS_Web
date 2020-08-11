package com.neu.his.entity;

public class InvoiceEntry {
    private int order;
    private int id;
    private int affairId;
    private int num;
    private double cost;
    private boolean paid;
    private boolean state;

    public InvoiceEntry(int order, int id, int affairId, int num, double cost,boolean paid, boolean state) {
        this.order = order;
        this.id = id;
        this.affairId = affairId;
        this.num = num;
        this.cost = cost;
        this.paid = paid;
        this.state = state;
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
