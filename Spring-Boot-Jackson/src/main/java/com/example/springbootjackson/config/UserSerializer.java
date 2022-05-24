package com.example.springbootjackson.config;

import com.example.springbootjackson.pojo.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User user, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeStartObject();
		//我们仅仅序列化userName属性，且输出的key是user-name。 使用注解@JsonSerialize来指定User对象的序列化方式
		generator.writeStringField("user-name", user.getUserName());
		generator.writeEndObject();
	}
}