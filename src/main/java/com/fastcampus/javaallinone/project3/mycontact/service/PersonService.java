package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.domain.Block;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.repository.BlockRepository;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;
    
    public List<Person> getPeopleExcludeBlocks(){ // 차단된 사람을 제외하고 나머지 전체 사람을 가져오는 로직
        List<Person> people = personRepository.findAll();
        List<Block> blocks = blockRepository.findAll();
        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList()); // blocks에 들어있는 모든 값을 순회하면서 getName만 뽑아옴
        
        return people.stream().filter(person -> !blockNames.contains(person.getName())).collect(Collectors.toList()); // filter - 어떤 조건에 일치하는 것만 돌려주는 함수
                                                                                                                      // blockNames에 person에 getName이 들어있다면 제외하고싶음
    }
}
