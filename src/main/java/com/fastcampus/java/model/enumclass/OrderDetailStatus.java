package com.fastcampus.java.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderDetailStatus {

    WAITING(0,"상품준비","상품 준비중"),
    COMPLETE(1,"준비완료","상품 준비완료")
    ;

    private Integer id;
    private String title;
    private String description;
}
