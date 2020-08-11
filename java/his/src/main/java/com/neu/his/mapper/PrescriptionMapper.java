package com.neu.his.mapper;

import com.neu.his.entity.Prescription;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionMapper {
    @Select("select * from prescription")
    List<Prescription> getAll();

    @Select("select max(id)+1 from prescription")
    Integer newPrescriptionId();

    @Insert("insert into prescription values (#{id}, #{name}, #{drugs})")
    void addPrescription(Prescription prescription);
}
