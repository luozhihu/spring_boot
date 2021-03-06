package com.example.spring_boot_thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.spring_boot_thymeleaf.bean.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	
	@RequestMapping("/account")
	// SpringMVC 框架帮我们把model中的数据, 一个一个添加到request域
	// Model 不能替换Request对象,
	public String index(Model m) {
		List<Account> list = new ArrayList<Account>();
		list.add(new Account("KangKang", "康康", "e10adc3949ba59abbe56e", "超级管理员", "17777777777"));
		list.add(new Account("Mike", "麦克", "e10adc3949ba59abbe56e", "管理员", "13444444444"));
		list.add(new Account("Jane","简","e10adc3949ba59abbe56e","运维人员","18666666666"));
		list.add(new Account("Maria", "玛利亚", "e10adc3949ba59abbe56e", "清算人员", "19999999999"));
        m.addAttribute("accountList",list);
		//请求转发
		//把当前的request和response转发到另一个请求，保存在request中的数据会传给response然后相应给客户端
        return "hello";
	}
}