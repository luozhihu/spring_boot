package com.example.spring_boot_redis_cache;

import com.example.spring_boot_redis_cache.bean.Student;
import com.example.spring_boot_redis_cache.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootTest
@EnableCaching
class SpringBootRedisCacheApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private StudentService studentService;

    @Test
    public void test() throws Exception {
        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());
        student1.setName("康康");
        this.studentService.update(student1);
        Student student2 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
    }
}
