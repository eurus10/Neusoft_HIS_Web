package com.neu.his.mapper;

import com.neu.his.entity.Drug;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugMapper {
    @Select("select * from drug where id=#{id}")
    Drug getDrug(@Param("id") int id);

    @Select("select * from drug")
    List<Drug> getAll();

    @Select("select name from drug where id=#{id}")
    String getNameOf(@Param("id") int id);
}
