package com.neu.his.controller;

import com.neu.his.bean.FinAffairBean;
import com.neu.his.bean.InvoiceEntryBean;
import com.neu.his.bean.PatientBean;
import com.neu.his.service.FinancialAffair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FinAffairController {
    @Autowired
    private FinancialAffair financialAffair;

    @RequestMapping("/fin/get_patient")
    public PatientBean getPatient(@RequestBody Map<String, Integer> patient) {
        return financialAffair.getPatient(patient.get("id"));
    }

    @RequestMapping("/fin/affair")
    public List<InvoiceEntryBean> getFinAffairs(@RequestBody Map<String, Integer> patient) {
        return financialAffair.getAffairs(patient.get("id"));
    }

    @RequestMapping("/pay")
    public void pay(@RequestBody FinAffairBean bean) {
        financialAffair.pay(bean);
    }

    @RequestMapping("/refund")
    public void refund(@RequestBody FinAffairBean bean) {
        financialAffair.refund(bean);
    }
}
