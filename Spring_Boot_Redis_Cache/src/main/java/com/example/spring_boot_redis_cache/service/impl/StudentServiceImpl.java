package com.example.spring_boot_redis_cache.service.impl;

import com.example.spring_boot_redis_cache.bean.Student;
import com.example.spring_boot_redis_cache.mapper.StudentMapper;
import com.example.spring_boot_redis_cache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public Student update(Student student) {
		this.studentMapper.update(student);
		return this.studentMapper.queryStudentBySno(student.getSno());
	}

	@Override
	public void deleteStudentBySno(String sno) {
		this.studentMapper.deleteStudentBySno(sno);
	}

	@Override
	public Student queryStudentBySno(String sno) {
		return this.studentMapper.queryStudentBySno(sno);
	}

}