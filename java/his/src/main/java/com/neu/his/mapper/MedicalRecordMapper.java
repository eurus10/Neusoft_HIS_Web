package com.neu.his.mapper;

import com.neu.his.entity.RecordEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordMapper {
    @Select("select * from medical_record where id=#{id} order by `order`")
    List<RecordEntry> getRecordEntriesOf(@Param("id") int id);

    @Insert("insert into medical_record values (null, #{id}, #{complaint}, #{diseaseLog}, #{allergicLog}, #{diseases}, #{advice})")
    void addEntry(RecordEntry entry);

    @Update("update medical_record set complaint=#{complaint}, diseaseLog=#{diseaseLog}, allergicLog=#{allergicLog}, " +
            "diseases=#{diseases}, advice=#{advice} where order=#{order}")
    void updateEntry(RecordEntry entry);
}