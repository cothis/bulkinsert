package com.example.bulkinsert.repository;

import com.example.bulkinsert.domain.Allotment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AllotmentMapper {

    @Insert("INSERT INTO allotment VALUES(#{id}, #{amount}, NOW(), NOW(), #{creator}, #{modifier})")
    Long create(Allotment allotment);

    Long createAllotments(List<Allotment> allotments);
}
