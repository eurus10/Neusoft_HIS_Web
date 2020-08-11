package com.neu.his.service;

import com.neu.his.bean.PatientBean;
import com.neu.his.bean.PrescriptionBean;
import com.neu.his.bean.RegPatientBean;
import com.neu.his.entity.*;

import java.util.List;

public interface Inquiry {
    // 看诊业务
    List<RegPatientBean> getPatientList(int doctorId, boolean diagnosed);

    List<Disease> getDiseases();

    RegisterForm getRegisterForm(int id);

    PatientBean getPatient(int id);

    RecordEntry getLastRecord(int id);

    void insertRecordEntry(RecordEntry entry);

    void updateRecordEntry(RecordEntry entry);

    void diagnose(int id);

    // 开处方业务
    List<Prescription> getPrescriptions();

    List<Drug> getDrugs();

    int newPrescriptionId();

    int getPatientId(int registerFormId);

    void makePresc(PrescriptionBean bean);

    void addPrescription(Prescription prescription);
}
