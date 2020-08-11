package com.neu.his.service;

import com.neu.his.bean.PatientBean;
import com.neu.his.bean.RegisterFormBean;
import com.neu.his.entity.Department;
import com.neu.his.entity.Patient;
import com.neu.his.entity.RegisterForm;
import com.neu.his.entity.User;

import java.util.List;

public interface Register {
    void register(RegisterForm registerForm);

    void withdraw(int id);

    int newRegisterFormId();

    int newRecordId();

    Patient getPatientForReg(int id);

    PatientBean getPatientForWith(int id);

    RegisterFormBean getRegisterForm(int id);

    List<Department> getDepartments();

    List<User> getDoctors();

    List<User> getDoctorsOf(int departmentId);

    void savePatient(Patient patient);
}
