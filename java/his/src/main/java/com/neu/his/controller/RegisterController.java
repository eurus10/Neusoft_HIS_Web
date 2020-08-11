package com.neu.his.controller;

import com.neu.his.bean.PatientBean;
import com.neu.his.bean.RegisterFormBean;
import com.neu.his.entity.Department;
import com.neu.his.entity.Patient;
import com.neu.his.entity.RegisterForm;
import com.neu.his.entity.User;
import com.neu.his.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private Register register;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/register/init_rigid")
    public int newRegisterFromId() {
        return register.newRegisterFormId();
    }

    @RequestMapping("/register/init_ricid")
    public int newRecordId() {
        return register.newRecordId();
    }

    @RequestMapping("/register/init_depart")
    public List<Department> getDepartments() {
        return register.getDepartments();
    }

    @RequestMapping("/register/search_patient")
    public Patient getPatientForReg(@RequestBody Map<String, Integer> patient) {
        return register.getPatientForReg(patient.get("id"));
    }

    @RequestMapping("/register/doc_depart")
    public List<User> getDoctors(@RequestBody Map<String, Integer> department) {
        return register.getDoctorsOf(department.get("id"));
    }

    @RequestMapping("/register/save_patient")
    public void savePatient(@RequestBody Patient patient) {
        register.savePatient(patient);
    }

    @RequestMapping("/register")
    public void register(@RequestBody RegisterForm registerForm) {
        register.register(registerForm);
    }

    @RequestMapping("/withdraw/search_reg")
    public RegisterFormBean getRegisterForm(@RequestBody Map<String, Integer> registerForm) {
        return register.getRegisterForm(registerForm.get("id"));
    }

    @RequestMapping("/withdraw/search_patient")
    public PatientBean getPatientForWith(@RequestBody Map<String, Integer> patient) {
        return register.getPatientForWith(patient.get("id"));
    }

    @RequestMapping("/withdraw/init_doc")
    public List<User> getDoctors() {
        return register.getDoctors();
    }

    @RequestMapping("/withdraw")
    public void withdraw(@RequestBody Map<String, Integer> registerForm) {
        register.withdraw(registerForm.get("id"));
    }
}
