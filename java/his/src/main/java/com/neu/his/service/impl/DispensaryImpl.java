package com.neu.his.service.impl;

import com.neu.his.bean.FinAffairBean;
import com.neu.his.bean.InvoiceEntryBean;
import com.neu.his.bean.PatientBean;
import com.neu.his.entity.InvoiceEntry;
import com.neu.his.mapper.DrugMapper;
import com.neu.his.mapper.FinAffairMapper;
import com.neu.his.mapper.InvoiceMapper;
import com.neu.his.mapper.PatientMapper;
import com.neu.his.service.Dispensary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispensaryImpl implements Dispensary {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private FinAffairMapper finAffairMapper;
    @Autowired
    private DrugMapper drugMapper;

    @Override
    public PatientBean getPatient(int id) {
        return new PatientBean(patientMapper.getPatient(id));
    }

    @Override
    public List<InvoiceEntryBean> getAffairs(int id) {
        List<InvoiceEntry> entries = finAffairMapper.getInvoiceEntriesOf(invoiceMapper.getLastIdOf(id));
        List<InvoiceEntryBean> beans = new ArrayList<>();
        for (InvoiceEntry entry : entries) {
            InvoiceEntryBean bean = new InvoiceEntryBean(entry);
            bean.setAffairName(drugMapper.getNameOf(bean.getAffairId()));
            beans.add(bean);
        }
        return beans;
    }

    @Override
    public void transmit(FinAffairBean bean) {
        for (InvoiceEntryBean invoiceEntryBean : bean.getAffairs()) {
            finAffairMapper.updateState(invoiceEntryBean.getOrder(), true);
        }
    }

    @Override
    public void receive(FinAffairBean bean) {
        for (InvoiceEntryBean invoiceEntryBean : bean.getAffairs()) {
            finAffairMapper.updateState(invoiceEntryBean.getOrder(), false);
        }
    }
}
