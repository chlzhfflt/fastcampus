package com.fastcampus.java.repository;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.Item;
import com.fastcampus.java.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends JavaApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository; // = new UserRepository(); - Autowired를 통해 스프링이 직접 관리

    @Test
    public void create(){
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        // @Builder 빌더패턴을 통해서 생성
        // account, password, status, email까지만 생성자가 들어간 user 생성
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        // @Accessors(chain = true) 체인패턴을 통해서 값들을 업데이트
        user
                .setEmail("")
                .setPhoneNumber("")
                .setStatus("");

        User u = new User().setAccount("").setEmail("").setPassword("");

        if(user != null){
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("--------------------주문묶음--------------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("--------------------주문상세--------------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " +  orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());

                });

            });
        }

        Assertions.assertNotNull(user);
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method");

            userRepository.save(selectUser); // 해당 id가 존재한다면~ 값을 update해줌
        });
    }

    @Test
    @Transactional // 코드가 실행은 되지만 실제 데이터베이스에서 값이 지워지진 않음 ( 마지막에 롤백 시킴 )
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent()); // true

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); // false

        // 1번 id의 데이터를 select해서 반드시 값이 있는지 확인하여 통과를 하고 delete가 이루어지고
        // 다시 한번 1번 id를 select했을땐 이미 그 값이 delete됐기 때문에 false여야한다
        
        // 1번 id를 삭제한뒤에 한번 더 메서드를 실행시키면 assertTrue에서 값이 false이므로 에러 발생
    }
}
