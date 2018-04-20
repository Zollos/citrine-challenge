package com.citrine.challenge;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConvertControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        
    	String expected = "{\"unit_name\":\"rad/s\",\"multiplication_factor\":2.9088820866572E-4}";
        
        mvc.perform(MockMvcRequestBuilders.get("/units/si")
        		.accept(MediaType.APPLICATION_JSON)
        		.param("units",  "degree/minute"))
        	.andExpect(status().isOk())
            .andExpect(content().string(equalTo(expected)));
    }
}
