package com.neu.his.mapper;

import com.neu.his.entity.Disease;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseMapper {
    @Select("select * from disease")
    List<Disease> getAll();
}
