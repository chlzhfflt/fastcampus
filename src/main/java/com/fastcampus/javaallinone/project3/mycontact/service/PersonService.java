package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.domain.Block;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.repository.BlockRepository;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    
    public List<Person> getPeopleExcludeBlocks(){ // 차단된 사람을 제외하고 나머지 전체 사람을 가져오는 로직
        List<Person> people = personRepository.findAll();
        
        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList()); // block data가 없는 사람만 가져옴
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();

//        System.out.println("person : " + person);
        log.info("person : {}", person);

        return person;
    }
}
