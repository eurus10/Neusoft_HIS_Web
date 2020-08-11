package com.neu.his.controller;

import com.neu.his.entity.Department;
import com.neu.his.entity.User;
import com.neu.his.service.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private UserPort port;

    @RequestMapping("/update_user")
    public void updateUser(@RequestBody User user) {
        port.update(user);
    }

    @RequestMapping("/init_depart")
    public List<Department> getDepartments() {
        return port.getDepartments();
    }
}
