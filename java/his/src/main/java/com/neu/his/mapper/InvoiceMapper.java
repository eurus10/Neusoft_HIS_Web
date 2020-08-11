package com.neu.his.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceMapper {
    @Select("select max(id) from invoice group by patient_id having patient_id=#{patientId}")
    int getLastIdOf(@Param("patientId") int patientId);

    @Select("select max(id)+1 from invoice")
    Integer newInvoiceId();

    @Insert("insert into invoice values (#{id}, #{patientId})")
    void addInvoice(int id, long patientId);
}