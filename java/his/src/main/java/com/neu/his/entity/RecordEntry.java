package com.neu.his.entity;

public class RecordEntry {
    private Integer order;
    private int id;
    private String complaint;
    private String diseaseLog;
    private String allergicLog;
    private String diseases;
    private String advice;

    public RecordEntry(Integer order, int id, String complaint, String diseaseLog, String allergicLog, String diseases, String advice) {
        this.order = order;
        this.id = id;
        this.complaint = complaint;
        this.diseaseLog = diseaseLog;
        this.allergicLog = allergicLog;
        this.diseases = diseases;
        this.advice = advice;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getDiseaseLog() {
        return diseaseLog;
    }

    public void setDiseaseLog(String diseaseLog) {
        this.diseaseLog = diseaseLog;
    }

    public String getAllergicLog() {
        return allergicLog;
    }

    public void setAllergicLog(String allergicLog) {
        this.allergicLog = allergicLog;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
