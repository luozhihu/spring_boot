package com.example.spring_boot_aop.dao;

import com.example.spring_boot_aop.domain.SysLog;

public interface SysLogDao {
    void saveSysLog(SysLog syslog);
}