package com.fastcampus.java.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {

    REGISTERED(0,"등록","상품 등록 상태"),
    UNREGISTERED(1,"해지","상품 해지 상태"),
    WAITING(2,"검수(대기)","상품 검수 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}


// 1. entity enum으로 관리할 수 있는 것 찾기
// 2. 해상 enumclass 생성
// 3. entity에 적용
//      @Enumerated(EnumType.STRING)
//      private ItemStatus status;
// 4. request, response 변경
// 5. ApiLogicService 에러 있는지 확인 후 변경 ( enum 타입 매칭이 제대로 안되었다면 오류 발생 )
