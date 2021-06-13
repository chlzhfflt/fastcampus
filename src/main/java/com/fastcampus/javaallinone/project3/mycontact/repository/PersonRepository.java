package com.fastcampus.javaallinone.project3.mycontact.repository;

import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

//    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = ?1 and person.birthday.dayOfBirthday = ?2") // JPQL - entity 기반 query , ?1 은 첫번째 인자를 의미함
//    List<Person> findByMonthOfBirthday(int monthOfBirthday, int dayOfBirthday); // 8월이 생일인 사람을 구해옴
//    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday and person.birthday.dayOfBirthday = :dayOfBirthday") // 이름 기반
//    @Query(value = "select * from person where month_of_birthday = :monthOfBirthday and day_of_birthday = :dayOfBirthday", nativeQuery = true)
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday); // 이름 기반

    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();

//    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = ?1 and person.birthday.dayOfBirthday = ?2")
//    List<Person> findByBirthday(int monthOfBirthday, int dayOfBirthday);

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday and person.birthday.dayOfBirthday in :daysOfBirthday ")
    List<Person> findByBirthday(int monthOfBirthday, List<Integer> daysOfBirthday);
}
