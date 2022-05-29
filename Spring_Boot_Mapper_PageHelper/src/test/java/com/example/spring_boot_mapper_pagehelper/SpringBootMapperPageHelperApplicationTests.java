package com.example.spring_boot_mapper_pagehelper;

import com.example.spring_boot_mapper_pagehelper.bean.User;
import com.example.spring_boot_mapper_pagehelper.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringBootMapperPageHelperApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {
        User user = new User();
        //执行序列
        user.setId(userService.getSequence("seq_user"));
        user.setUsername("scott");
        user.setPasswd("ac089b11709f9b9e9980e7c497268dfa");
        user.setCreateTime(new Date());
        user.setStatus("0");
        this.userService.save(user);
    }
    @Test
    public void test1() throws Exception {
        //mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username like '%i%'");
        example.setOrderByClause("id desc");
        List<User> userList = this.userService.selectByExample(example);
        for (User u : userList) {
            System.out.println(u.getUsername());
        }

        List<User> all = this.userService.selectAll();
        for (User u : all) {
            System.out.println(u.getUsername());
        }

        User user = new User();
        Long id = 1l;
        user.setId(id);
        user = this.userService.selectByKey(user);
        System.out.println(user.getUsername());
    }
    @Test
    public void test2() throws Exception {
        User user = new User();
        Long id = 21l;
        user.setId(id);
        this.userService.delete(user);
    }
    @Test
    public void test3() throws Exception {
        //第一个参数是查询到的页码，第二个每页的大小
        PageHelper.startPage(2, 2);
        List<User> list = userService.selectAll();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        List<User> result = pageInfo.getList();
        for (User u : result) {
            System.out.println(u.getUsername());
        }
    }
}
