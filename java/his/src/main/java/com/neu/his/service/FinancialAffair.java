package com.neu.his.service;

import com.neu.his.bean.FinAffairBean;
import com.neu.his.bean.InvoiceEntryBean;
import com.neu.his.bean.PatientBean;

import java.util.List;

public interface FinancialAffair {
    PatientBean getPatient(int id);

    List<InvoiceEntryBean> getAffairs(int id);

    void pay(FinAffairBean bean);

    void refund(FinAffairBean bean);
}
