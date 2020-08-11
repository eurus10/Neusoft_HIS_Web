package com.neu.his.controller;

import com.neu.his.entity.Department;
import com.neu.his.entity.User;
import com.neu.his.service.UserPort;
import com.neu.his.bean.LoginUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserPortController {
    @Autowired
    private UserPort port;

    @RequestMapping("/login")
    public User login(@RequestBody LoginUserBean bean) {
        return port.login(bean.getUserId(), bean.getUserPsw());
    }

    @RequestMapping("/sign_up")
    public User signUp(@RequestBody User bean) {
        return port.signUp(bean);
    }

    @RequestMapping("/sign_up/init_id")
    public int newUserId() {
        return port.newUserId();
    }

    @RequestMapping("/sign_up/init_depart")
    public List<Department> getDepartments() {
        return port.getDepartments();
    }
}