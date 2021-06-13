package com.fastcampus.javaallinone.project3.mycontact.domain;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Block block;

    public void set(PersonDto personDto){
        if(personDto.getAge() != 0){
            this.setAge(personDto.getAge());
        }

        if(StringUtils.hasText(personDto.getHobby())){ // 강의영상에선 !StringUtils.isEmpty인데 isEmpty가 더이상 사용되지 않고 hasText로 변경됨 - 조건이 반대로 됨
            this.setHobby(personDto.getHobby());
        }

        if(StringUtils.hasText(personDto.getBloodType())){
            this.setBloodType(personDto.getBloodType());
        }

        if(StringUtils.hasText(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }

        if(StringUtils.hasText(personDto.getJob())){
            this.setJob(personDto.getJob());
        }

        if(StringUtils.hasText(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
    }
}