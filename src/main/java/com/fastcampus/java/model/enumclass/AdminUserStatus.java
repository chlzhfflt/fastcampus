package com.fastcampus.java.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserStatus {
    REGISTERED(0,"등록","등록 완료"),
    UNREGISTERED(1,"해지","등록 해지")
    ;

    private Integer id;
    private String title;
    private String description;
}
