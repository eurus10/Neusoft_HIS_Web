package com.neu.his.service.impl;

import com.neu.his.bean.PatientBean;
import com.neu.his.bean.RegisterFormBean;
import com.neu.his.entity.Department;
import com.neu.his.entity.Patient;
import com.neu.his.entity.RegisterForm;
import com.neu.his.entity.User;
import com.neu.his.mapper.DepartmentMapper;
import com.neu.his.mapper.PatientMapper;
import com.neu.his.mapper.RegisterMapper;
import com.neu.his.mapper.UserMapper;
import com.neu.his.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterImpl implements Register {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(RegisterForm registerForm) {
        registerMapper.addRegisterForm(registerForm);
    }

    @Override
    public void withdraw(int id) {
        registerMapper.delRegisterForm(id);
    }

    @Override
    public int newRegisterFormId() {
        Integer id = registerMapper.newRegisterFormId();
        if (id == null) return 1;
        else return id;
    }

    @Override
    public int newRecordId() {
        Integer id = patientMapper.newRecordId();
        if (id == null) return 1;
        else return id;
    }

    @Override
    public Patient getPatientForReg(int id) {
        return patientMapper.getPatient(id);
    }

    @Override
    public PatientBean getPatientForWith(int id) {
        return new PatientBean(patientMapper.getPatient(id));
    }

    @Override
    public RegisterFormBean getRegisterForm(int id) {
        RegisterForm registerForm = registerMapper.getRegisterForm(id);
        RegisterFormBean bean = new RegisterFormBean(registerForm);
        bean.setDepartment(departmentMapper.getNameOf(registerForm.getDepartmentId()));
        bean.setDoctor(userMapper.getNameOf(registerForm.getDoctorId()));
        return bean;
    }

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getAll();
    }

    @Override
    public List<User> getDoctors() {
        return userMapper.getAll();
    }

    @Override
    public List<User> getDoctorsOf(int departmentId) {
        return userMapper.userOfDepartment(departmentId);
    }

    @Override
    public void savePatient(Patient patient) {
        if (patientMapper.getPatient(patient.getId()) == null) {
            patientMapper.addPatient(patient);
        } else patientMapper.updatePatient(patient);
    }
}
