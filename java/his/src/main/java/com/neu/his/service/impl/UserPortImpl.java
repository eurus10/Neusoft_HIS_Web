package com.neu.his.service.impl;

import com.neu.his.entity.Department;
import com.neu.his.entity.User;
import com.neu.his.mapper.DepartmentMapper;
import com.neu.his.mapper.UserMapper;
import com.neu.his.service.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPortImpl implements UserPort {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public User login(int id, String psw) {
        User user = userMapper.getUser(id);
        if (user != null && user.getPsw().equals(psw)) return user;
        else return null;
    }

    @Override
    public User signUp(User user) {
        userMapper.addUser(user);
        return user;
    }

    @Override
    public void update(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public int newUserId() {
        Integer id = userMapper.newUserId();
        if (id == null) return 1;
        else return id;
    }

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getAll();
    }
}
