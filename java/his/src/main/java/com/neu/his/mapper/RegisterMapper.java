package com.neu.his.mapper;

import com.neu.his.entity.RegisterForm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterMapper {
    // 挂号业务
    @Select("select * from register where id=#{id}")
    RegisterForm getRegisterForm(@Param("id") int id);

    @Select("insert into register values (#{id}, #{patientId}, #{departmentId}, #{doctorId}, #{level}, #{recordNeeded}, #{cost}, #{diagnosed})")
    void addRegisterForm(RegisterForm registerForm);

    @Delete("delete from register where id=#{id}")
    void delRegisterForm(@Param("id") int id);

    @Select("select max(id)+1 from register")
    Integer newRegisterFormId();

    // 医师业务
    @Update("update register set diagnosed=true where id=#{id}")
    void updateState(@Param("id") int id);

    @Select("select * from register where doctor_id=#{doctorId} and diagnosed=#{diagnosed}")
    List<RegisterForm> getRegisterFormOf(@Param("doctorId") int doctorId, @Param("diagnosed") boolean diagnosed);
}
