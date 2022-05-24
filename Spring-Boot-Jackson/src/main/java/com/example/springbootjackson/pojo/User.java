package com.example.springbootjackson.pojo;

import com.example.springbootjackson.config.UserSerializer;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;


//使用自定义的序列化规则。
//@JsonSerialize(using = UserSerializer.class)
public class User implements Serializable {

	private static final long serialVersionUID = 6222176558369919436L;

	public interface UserNameView {
	};

	public interface AllUserFieldView extends UserNameView {
	};

	@JsonView(UserNameView.class)
	private String userName;
	
	@JsonView(AllUserFieldView.class)
	private int age;

	// @JsonIgnore
	@JsonView(AllUserFieldView.class)
	private String password;

	//@JsonProperty，作用在属性上，用来为JSON Key指定一个别名。
	// @JsonProperty("bth")
	//@Jsonlgnore，作用在属性上，用来忽略此属性。
	//@JsonIgnore
	//@JsonFormat，用于日期格式化
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonView(AllUserFieldView.class)
	private Date birthday;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}