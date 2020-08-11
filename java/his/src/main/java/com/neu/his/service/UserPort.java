package com.neu.his.service;

import com.neu.his.entity.Department;
import com.neu.his.entity.User;

import java.util.List;

public interface UserPort {
    User login(int id, String psw);

    User signUp(User user);

    void update(User user);

    int newUserId();

    List<Department> getDepartments();
}
