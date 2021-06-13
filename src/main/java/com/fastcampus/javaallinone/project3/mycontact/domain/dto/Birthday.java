package com.fastcampus.javaallinone.project3.mycontact.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Embeddable // entity에 속해있는 dto
@Data
@NoArgsConstructor
public class Birthday {
    private Integer yearOfBirthday;
    private Integer monthOfBirthday;
    private Integer dayOfBirthday;

    private Birthday(LocalDate birthday){ // birthday 를 localdate타입으로 한번 랩핑해줌
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }

    public static Birthday of(LocalDate birthday){
        return new Birthday(birthday);
    }
}