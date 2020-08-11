package com.neu.his.mapper;

import com.neu.his.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User getUser(@Param("id") int id);

    @Select("select * from user")
    List<User> getAll();

    @Select("select name from user where id=${id}")
    String getNameOf(@Param("id") int id);

    @Insert("insert into user values (#{id}, #{psw}, #{name}, #{departmentId}, #{type})")
    void addUser(User user);

    @Update("update user set psw=#{psw}, name=#{name}, department_id=#{departmentId}, type=#{type} where id=#{id}")
    void updateUser(User user);

    @Select("select max(id)+1 from user")
    Integer newUserId();

    @Select("select * from user where department_id=#{departmentId}")
    List<User> userOfDepartment(@Param("departmentId") int departmentId);
}
