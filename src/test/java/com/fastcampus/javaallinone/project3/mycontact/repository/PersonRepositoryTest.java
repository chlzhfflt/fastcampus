package com.fastcampus.javaallinone.project3.mycontact.repository;

import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("john");

        personRepository.save(person);
        System.out.println(personRepository.findAll());

        List<Person> result = personRepository.findByName("john");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("john");
//        assertThat(result.get(0).getAge()).isEqualTo(10);
    }

    @Test
    void findByBirthdayBetween(){
        System.out.println(personRepository.findAll());
        List<Person> result = personRepository.findByMonthOfBirthday(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }

    @Test
    void findByBirthday(){
        System.out.println(personRepository.findAll());

        Date date = new Date();
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.KOREA);
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.KOREA);
        String birthdayMonth = month.format(date);
        String birthdayday = day.format(date);
        int intMonth = Integer.parseInt(birthdayMonth); // 월
        int intDay = Integer.parseInt(birthdayday); // 일

        DateFormat dtf = new SimpleDateFormat("dd", Locale.KOREA);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String birthdayDtf = dtf.format(cal.getTime());
        int intDayTomorrow = Integer.parseInt(birthdayDtf); // 내일에 해당하는 '일'

        List<Person> result = personRepository.findByBirthday(intMonth,intDay);
        List<Person> resultTomorrow = personRepository.findByBirthday(intMonth,intDayTomorrow);
        System.out.println("오늘 생일자목록 : " + result);
        System.out.println("내일 생일자목록 : " + resultTomorrow);
    }
}