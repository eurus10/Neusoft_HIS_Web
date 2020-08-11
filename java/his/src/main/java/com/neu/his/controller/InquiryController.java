package com.neu.his.controller;

import com.neu.his.bean.PatientBean;
import com.neu.his.bean.PrescriptionBean;
import com.neu.his.bean.RegPatientBean;
import com.neu.his.entity.*;
import com.neu.his.service.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InquiryController {
    @Autowired
    private Inquiry inquiry;

    // 看诊业务
    @RequestMapping("/diagnose/init_undia")
    public List<RegPatientBean> getUndiagnosedList(@RequestBody Map<String, Integer> doctor) {
        return inquiry.getPatientList(doctor.get("id"), false);
    }

    @RequestMapping("/diagnose/init_diag")
    public List<RegPatientBean> getDiagnosedList(@RequestBody Map<String, Integer> doctor) {
        return inquiry.getPatientList(doctor.get("id"), true);
    }

    @RequestMapping("/diagnose/init_disease")
    public List<Disease> getUndiagnosedList() {
        return inquiry.getDiseases();
    }

    @RequestMapping("/diagnose/search_reg")
    public RegisterForm getRegisterForm(@RequestBody Map<String, Integer> registerForm) {
        return inquiry.getRegisterForm(registerForm.get("id"));
    }

    @RequestMapping("/diagnose/search_patient")
    public PatientBean getPatient(@RequestBody Map<String, Integer> patient) {
        return inquiry.getPatient(patient.get("id"));
    }

    @RequestMapping("/diagnose/search_rec")
    public RecordEntry getRecordEntry(@RequestBody Map<String, Integer> medicalRecord) {
        return inquiry.getLastRecord(medicalRecord.get("id"));
    }

    @RequestMapping("/diagnose/insert")
    public void insertEntry(@RequestBody RecordEntry entry) {
        inquiry.insertRecordEntry(entry);
    }

    @RequestMapping("/diagnose/update")
    public void updateEntry(@RequestBody RecordEntry entry) {
        inquiry.updateRecordEntry(entry);
    }

    @RequestMapping("/diagnose")
    public void diagnose(@RequestBody Map<String, Integer> registerForm) {
        inquiry.diagnose(registerForm.get("id"));
    }

    // 开处方
    @RequestMapping("/make_presc/init_presc")
    public List<Prescription> getPrescriptions() {
        return inquiry.getPrescriptions();
    }

    @RequestMapping("/make_presc/init_drug")
    public List<Drug> getDrugs() {
        return inquiry.getDrugs();
    }

    @RequestMapping("/make_presc/init_id")
    public int newPrescriptionId() {
        return inquiry.newPrescriptionId();
    }

    @RequestMapping("/make_presc/patient_id")
    public int getPatientId(@RequestBody Map<String, Integer> registerForm) {
        return inquiry.getPatientId(registerForm.get("id"));
    }

    @RequestMapping("/make_presc")
    public void makePresc(@RequestBody PrescriptionBean bean) {
        inquiry.makePresc(bean);
    }

    @RequestMapping("/make_presc/save_presc")
    public void savePrescription(@RequestBody Prescription prescription) {
        inquiry.addPrescription(prescription);
    }
}
