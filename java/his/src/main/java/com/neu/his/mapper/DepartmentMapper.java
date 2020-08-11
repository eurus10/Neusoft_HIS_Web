package com.neu.his.mapper;

import com.neu.his.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    @Select("select * from department")
    List<Department> getAll();

    @Select("select name from department where id=#{id}")
    String getNameOf(@Param("id") int id);
}
