package com.neu.his.controller;

import com.neu.his.bean.FinAffairBean;
import com.neu.his.bean.InvoiceEntryBean;
import com.neu.his.bean.PatientBean;
import com.neu.his.service.Dispensary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DispensaryController {
    @Autowired
    private Dispensary dispensary;

    @RequestMapping("/disp/get_patient")
    public PatientBean getPatient(@RequestBody Map<String, Integer> patient) {
        return dispensary.getPatient(patient.get("id"));
    }

    @RequestMapping("/disp/affair")
    public List<InvoiceEntryBean> getFinAffairs(@RequestBody Map<String, Integer> patient) {
        return dispensary.getAffairs(patient.get("id"));
    }

    @RequestMapping("/drug_trans")
    public void drugTrans(@RequestBody FinAffairBean bean) {
        dispensary.transmit(bean);
    }

    @RequestMapping("/drug_rcv")
    public void drugRcv(@RequestBody FinAffairBean bean) {
        dispensary.receive(bean);
    }
}
