package com.fastcampus.java.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {

    private Integer totalPages;     // 총 몇개의 페이지가 있는지
    private Long totalElements;     // 총 몇개의 유저수가 있는지
    private Integer currentPage;    // 현재 페이지가 몇번째인지
    private Integer currentElements;// 현재 몇개의 데이터가 내려갔는지
}
