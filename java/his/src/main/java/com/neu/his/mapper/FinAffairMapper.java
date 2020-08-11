package com.neu.his.mapper;

import com.neu.his.entity.InvoiceEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinAffairMapper {
    @Select("select * from fin_affair where id=#{id}")
    List<InvoiceEntry> getInvoiceEntriesOf(@Param("id") int id);

    @Select("select max(`order`)+1 from fin_affair")
    Integer newAffairOrder();

    @Insert("insert into fin_affair values (#{order}, #{id}, #{affairId}, #{num}, #{cost}, #{paid}, #{state})")
    void addEntry(InvoiceEntry entry);

    @Update("update fin_affair set paid=#{paid} where `order`=#{order}")
    void updatePaid(@Param("order") int order, @Param("paid") boolean paid);

    @Update("update fin_affair set state=#{state} where `order`=#{order}")
    void updateState(@Param("order") int order, @Param("state") boolean state);
}
