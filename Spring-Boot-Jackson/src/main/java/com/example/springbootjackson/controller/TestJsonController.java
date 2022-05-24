package com.example.springbootjackson.controller;

import com.example.springbootjackson.pojo.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class TestJsonController {

	@Autowired
	ObjectMapper mapper;

	//User.AllUserFieldView接口和其父接口都会被转成json
	@JsonView(User.AllUserFieldView.class)
	@RequestMapping("getuser")
	@ResponseBody
	public User getUser() {
		User user = new User();
		user.setUserName("mrbird");
		user.setAge(26);
		user.setPassword("123456");
		user.setBirthday(new Date());
		return user;
	}

	@RequestMapping("serialization")
	@ResponseBody
	public String serialization() {
		try {
			User user = new User();
			user.setUserName("mrbird");
			user.setBirthday(new Date());
			//ackson通过使用mapper的writeValueAsString方法将Java对象序列化为JSON格式字符串：
			String str = mapper.writeValueAsString(user);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("readjsonstring")
	@ResponseBody
	public String readJsonString() {
		try {
			String json = "{\"name\":\"mrbird\",\"age\":26}";
			//当采用树遍历的方式时，JSON被读入到JsonNode对象中，可以像操作XML DOM那样读取JSON
			//node为根节点
			JsonNode node = this.mapper.readTree(json);
			String name = node.get("name").asText();
			int age = node.get("age").asInt();
			return name + " " + age;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("readjsonasobject")
	@ResponseBody
	public String readJsonAsObject() {
		try {
			String json = "{\"userName\":\"mrbird\"}";
			User user = mapper.readValue(json, User.class);
			String name = user.getUserName();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("formatobjecttojsonstring")
	@ResponseBody
	public String formatObjectToJsonString() {
		try {
			User user = new User();
			user.setUserName("mrbird");
			user.setAge(26);
			user.setPassword("123456");
			user.setBirthday(new Date());
			String jsonStr = mapper.writeValueAsString(user);
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("updateuser")
	@ResponseBody
	//在Controller方法中，可以使用＠RequestBody将提交的JSON自动映射到方法参数上
	public int updateUser(@RequestBody List<User> list) {
		return list.size();
	}

	@RequestMapping("customize")
	@ResponseBody
	public String customize() throws JsonParseException, JsonMappingException, IOException {
		String jsonStr = "[{\"userName\":\"mrbird\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
		//在运行时刻，泛型己经被擦除了（不同于方法参数定义的泛型，不会被擦除）。为了提供泛型信息，Jackson提供了JavaType ，用来指明集合类型
		JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
		List<User> list = mapper.readValue(jsonStr, type);
		String msg = "";
		for (User user : list) {
			msg += user.getUserName();
		}
		return msg;
	}
}