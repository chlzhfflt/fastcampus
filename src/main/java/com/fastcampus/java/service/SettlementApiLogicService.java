package com.fastcampus.java.service;

import com.fastcampus.java.model.entity.Partner;
import com.fastcampus.java.model.entity.Settlement;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.SettlementApiRequest;
import com.fastcampus.java.model.network.response.PartnerApiResponse;
import com.fastcampus.java.model.network.response.SettlementApiResponse;
import com.fastcampus.java.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementApiLogicService extends BaseService<SettlementApiRequest, SettlementApiResponse, Settlement> {

//    CREATE TABLE `settlement` (
//            `user_id` bigint NOT NULL,
//            `total_price` decimal(12,4) NOT NULL,
//  `created_at` datetime NOT NULL,
//            `created_by` varchar(20) NOT NULL,
//  `updated_at` datetime DEFAULT NULL,
//            `updated_by` varchar(20) DEFAULT NULL,
//    PRIMARY KEY (`user_id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

    // orderGroup 테이블에 있는 userId와 totalPrice를 가지고와서 저장후 read를 하면 되겠다고 간단히 생각했는데
    // 작성하다보니 userId가 아닌 userId와 totalPrice를 보유한 settlement의 id값으로 get된다는걸 깨달았습니다
    // 프라이멀키 id값을 테이블에서 지우고 조금씩 고쳐보다가 코드가 계속 꼬여서 겨우 복구만하고 마무리합니다..
    // 첫단추부터 잘못꿴거같아서 나중에 시간을 두고 곰곰히 생각해봐야할 것 같습니다..

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<SettlementApiResponse> create(Header<SettlementApiRequest> request) {

//        SettlementApiRequest body = request.getData();
//
//        Settlement settlement = Settlement.builder()
//                .orderGroup(orderGroupRepository.getOne(body.getUserId()))
//                .orderGroup(orderGroupRepository.getOne(body.getTotalPrice()))
//                .build();

        return null;
    }

    @Override
    public Header<SettlementApiResponse> read(Long id) {
        
        return baseRepository.findById(id)
                .map(settlement -> response(settlement))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<SettlementApiResponse> update(Header<SettlementApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<SettlementApiResponse> response(Settlement settlement){

        return null;
    }
}
