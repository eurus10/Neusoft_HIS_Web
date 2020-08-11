package com.neu.his.service.impl;

import com.neu.his.bean.PatientBean;
import com.neu.his.bean.PrescriptionBean;
import com.neu.his.bean.RegPatientBean;
import com.neu.his.entity.*;
import com.neu.his.mapper.*;
import com.neu.his.service.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class InquiryImpl implements Inquiry {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;
    @Autowired
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private FinAffairMapper finAffairMapper;

    // 看诊业务
    @Override
    public List<RegPatientBean> getPatientList(int doctorId, boolean diagnosed) {
        List<RegisterForm> registerFormList = registerMapper.getRegisterFormOf(doctorId, diagnosed);
        List<RegPatientBean> beanList = new ArrayList<>();
        for (RegisterForm registerForm : registerFormList) {
            Patient patient = patientMapper.getPatient(registerForm.getPatientId());
            beanList.add(new RegPatientBean(registerForm.getId(), patient.getName()));
        }
        return beanList;
    }

    @Override
    public List<Disease> getDiseases() {
        return diseaseMapper.getAll();
    }

    @Override
    public RegisterForm getRegisterForm(int id) {
        return registerMapper.getRegisterForm(id);
    }

    @Override
    public PatientBean getPatient(int id) {
        return new PatientBean(patientMapper.getPatient(id));
    }

    @Override
    public RecordEntry getLastRecord(int id) {
        List<RecordEntry> entries = medicalRecordMapper.getRecordEntriesOf(id);
        if (entries.size() == 0)
            return new RecordEntry(1, id, null, null, null, null, null);
        else return entries.get(entries.size() - 1);
    }

    @Override
    public void insertRecordEntry(RecordEntry entry) {
        medicalRecordMapper.addEntry(entry);
    }

    @Override
    public void updateRecordEntry(RecordEntry entry) {
        medicalRecordMapper.updateEntry(entry);
    }

    @Override
    public void diagnose(int id) {
        registerMapper.updateState(id);
    }

    // 开处方业务
    @Override
    public List<Prescription> getPrescriptions() {
        return prescriptionMapper.getAll();
    }

    @Override
    public List<Drug> getDrugs() {
        return drugMapper.getAll();
    }

    @Override
    public int newPrescriptionId() {
        Integer nextId = prescriptionMapper.newPrescriptionId();
        if (nextId == null) return 1;
        else return nextId;
    }

    @Override
    public int getPatientId(int registerFormId) {
        return registerMapper.getRegisterForm(registerFormId).getPatientId();
    }

    @Override
    public void makePresc(PrescriptionBean bean) {
        Integer id = invoiceMapper.newInvoiceId();
        if (id == null) id = 1;
        invoiceMapper.addInvoice(id, bean.getId());
        StringTokenizer items = new StringTokenizer(bean.getDrugs(), "_");
        Integer order = finAffairMapper.newAffairOrder();
        if (order == null) order = 1;
        while (items.hasMoreTokens()) {
            StringTokenizer item = new StringTokenizer(items.nextToken(), "^");
            int drugId = Integer.parseInt(item.nextToken());
            String name = item.nextToken();
            int num = Integer.parseInt(item.nextToken());
            double cost = drugMapper.getDrug(drugId).getCost() * num;
            finAffairMapper.addEntry(new InvoiceEntry(order, id, drugId, num, cost, false, false));
            order++;
        }
    }

    @Override
    public void addPrescription(Prescription prescription) {
        prescriptionMapper.addPrescription(prescription);
    }
}
