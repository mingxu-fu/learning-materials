package com.yingxue.lesson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yingxue.lesson.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMockMvcApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void contextLoads() {
    }


//    Accept:text/xml；
//    Content-Type:text/html
//    即代表希望接受的数据类型是xml格式，本次请求发送的数据的数据格式是html。
    @Test
    public void mockMvcGet()throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/api/getUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("username", "admin"))
//                .param("userId", "211"))
                .andDo(print())
                .andExpect(status().isOk())   //校验响应，返回的值
//                .andExpect(jsonPath("$.userId").value("211"))
                .andExpect(jsonPath("$.username").value("admin"))
                .andReturn();
        System.out.println("111111111111111"+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void mockMvcPost()throws Exception{
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId("985");
        userInfo.setName("lisi");
        ObjectMapper mapper=new ObjectMapper();                      //要把请求体即java类对象转成jackson格式,之后转成String
        ObjectWriter ow=mapper.writer().withDefaultPrettyPrinter();
        String json=ow.writeValueAsString(userInfo);                     //？？？？？？？？？？？？？？？？？请求数据要求为UserInfo类，但这里变成了String
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/getUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_UTF8))                 //perform后会得到ResultActions,以下and方法全是ResultActions内的方法
                .andDo(print())
                .andExpect(jsonPath("$.userId").value("985"))
                .andExpect(jsonPath("$.name").value("lisi"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }





}
