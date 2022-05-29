package com.example.spring_boot_mapper_pagehelper.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SeqenceMapper {
    @Select("select ${seqName}.nextval from dual")
    Long getSequence(@Param("seqName") String seqName);
}