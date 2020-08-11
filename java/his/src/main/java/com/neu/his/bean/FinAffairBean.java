package com.neu.his.bean;

import java.util.List;

public class FinAffairBean {
    private List<InvoiceEntryBean> affairs;

    public FinAffairBean() {}

    public List<InvoiceEntryBean> getAffairs() {
        return affairs;
    }

    public void setAffairs(List<InvoiceEntryBean> affairs) {
        this.affairs = affairs;
    }
}
