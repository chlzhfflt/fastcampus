package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper objectMapper;

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
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();

        assertAll(
                ()->assertThat(result.getName()).isEqualTo("martin"),
                ()->assertThat(result.getHobby()).isEqualTo("programming"),
                ()->assertThat(result.getAddress()).isEqualTo("판교"),
                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                ()->assertThat(result.getJob()).isEqualTo("programmer"),
                ()->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );
    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception{
        PersonDto dto = PersonDto.of("james","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        assertThrows(NestedServletException.class, ()->
                mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk()));
    }

    @Test
    void modifyName() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name","martinModified"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");
    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));
    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }
}