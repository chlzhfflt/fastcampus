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

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<SettlementApiResponse> create(Header<SettlementApiRequest> request) {

        SettlementApiRequest body = request.getData();

        Settlement settlement = Settlement.builder()
                .orderGroup(orderGroupRepository.getOne(body.getUserId()))
                .orderGroup(orderGroupRepository.getOne(body.getTotalPrice()))
                .build();

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
