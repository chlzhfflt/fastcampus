package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;

    private MockMvc mockMvc;

    @BeforeEach // 해당 메서드는 각 메서드가 실행될때마다 한번씩 먼저 실행됨
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/person/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("martin"));
    }

    @Test
    void postPerson() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON) // APPLICATION_JSON_UTF8 은 더이상 사용되지 않음
                    .characterEncoding("UTF-8")
                    .content("{\n" +
                            "    \"name\": \"martin2\",\n" +
                            "    \"age\": 20,\n" +
                            "    \"bloodType\": \"A\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isCreated()); // status가 201 로 떨어지면 성공
    }

    @Test
    void modifyPerson() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\n" +
                        "    \"name\": \"martin\",\n" +
                        "    \"age\": 20,\n" +
                        "    \"bloodType\": \"A\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyName() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name","martin22"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

//        log.info("people deleted : {}", personRepository.findPeopleDeleted()); // cannot find symbol 에러 발생

//        C:\Users\choi\IdeaProjects\fastcampus2\src\test\java\com\fastcampus\javaallinone\project3\mycontact\controller\PersonControllerTest.java:94: error: cannot find symbol
//        log.info("people deleted : {}", personRepository.findPeopleDeleted());
//        ^
//        symbol:   variable log
//        location: class PersonControllerTest
    }
}