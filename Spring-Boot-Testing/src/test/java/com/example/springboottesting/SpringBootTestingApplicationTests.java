package com.example.springboottesting;

import com.example.springboottesting.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;


/*
assertEquals("message",A,B)，判断A对象和B对象是否相等，这个判断在比较两个对象时调用了equals()方法。

assertSame("message",A,B)，判断A对象与B对象是否相同，使用的是==操作符。

assertTrue("message",A)，判断A条件是否为真。

assertFalse("message",A)，判断A条件是否不为真。

assertNotNull("message",A)，判断A对象是否不为null。

assertArrayEquals("message",A,B)，判断A数组与B数组是否相等。
 */


@SpringBootTest
class SpringBootTestingApplicationTests {

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        User user =new User();
        user.setUsername("Dopa");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        session.setAttribute("user",user);
    }
    @Test
    @Transactional
    public void test() throws Exception {
        //mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", "mrbird"));//get请求
        //mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", "mrbird"));
        User user = new User();
        user.setUsername("Dopa");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");
        String userJson = mapper.writeValueAsString(user);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/save")//设置请求方式和url
                                .contentType(MediaType.APPLICATION_JSON)//设置请求的Content-Type
                                .content(userJson.getBytes()))//模拟发送JSON参数
                .andExpect(MockMvcResultMatchers.status().isOk())//期望成功调用，即HTTP Status为200
                .andDo(MockMvcResultHandlers.print());//输出响应结果
    }
    @Test
    @Transactional
    public void test01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .param("userName", "mrbird")
                        .accept(MediaType.APPLICATION_JSON)
                        .cookie(new Cookie("mingzhi", "罗志虎")))
                .andDo(MockMvcResultHandlers.print());
    }


    //模拟一个get请求
    @Test
    @Transactional
    public void test02() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","mrbird"));
    }


    //模拟一个post请求
    @Test
    @Transactional
    public void test03() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
    }


    //模拟请求参数
    //也可以直接使用MultiValueMap构建参数
    @Test
    @Transactional
    public void test04() throws Exception {
        // 模拟发送一个message参数，值为hello
        //mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));
        // 模拟提交一个checkbox值，name为hobby，值为sleep和eat
        //mockMvc.perform(MockMvcRequestBuilders.get("/saveHobby").param("hobby", "sleep", "eat"));



        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", "mrbird");
        params.add("hobby", "sleep");
        params.add("hobby", "eat");
        mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));
    }


    //模拟发送JSON参数
    //实际测试中，要手动编写这么长的JSON格式字符串很繁琐也很容易出错，可以借助Spring Boot自带的Jackson技术来序列化一个Java对象
    @Test
    @Transactional
    public void test05() throws Exception {
        //String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
        //mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));

        User user = new User();
        user.setUsername("Dopa");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");

        String userJson = mapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userJson.getBytes()));
    }


    @Test
    @Transactional
    public void test06() throws Exception {
        //模拟Session和Cookie
        //mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
        //mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));



        //设置请求的Content-Type：
        //mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON_UTF8));
        //设置返回格式为JSON：
        //mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
        //模拟HTTP请求头：
        //mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, values));
    }


    //MockMvc处理返回结果
    @Test
    @Transactional
    public void test07() throws Exception {
        //期望成功调用，即HTTP Status为200：
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //期望返回内容是application/json：
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        //检查返回JSON数据中某个值的内容：
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("mrbird"));


        //比较Model：
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("password"))
                .andExpect(MockMvcResultMatchers.model().attribute("username", "mrbird"));

        //比较forward或者redirect
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index.html"));
        // 或者
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("index.html"));

        // 返回内容为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().string("hello"));

//        // 返回内容是XML，并且与xmlCotent一样
//        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
//                .andExpect(MockMvcResultMatchers.content().xml(xmlContent));
//
//        // 返回内容是JSON ，并且与jsonContent一样
//        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
//                .andExpect(MockMvcResultMatchers.content().json(jsonContent));


        //输出响应结果
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andDo(MockMvcResultHandlers.print());
    }
}
