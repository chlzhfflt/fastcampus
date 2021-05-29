package com.fastcampus.java.service;

import com.fastcampus.java.model.entity.AdminUser;
import com.fastcampus.java.model.entity.OrderDetail;
import com.fastcampus.java.model.entity.OrderGroup;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.OrderDetailApiRequest;
import com.fastcampus.java.model.network.response.OrderDetailApiResponse;
import com.fastcampus.java.repository.ItemRepository;
import com.fastcampus.java.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailApiService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .item(itemRepository.getOne(body.getItemId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);
        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> response(orderDetail))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
    
        OrderDetailApiRequest body = request.getData();
        
        return baseRepository.findById(body.getId())
                .map(orderDetail -> {
                    orderDetail
                            .setStatus(body.getStatus())
                            .setArrivalDate(body.getArrivalDate())
                            .setQuantity(body.getQuantity())
                            .setTotalPrice(body.getTotalPrice())
                            .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                            .setItem(itemRepository.getOne(body.getItemId()));
                    
                    return orderDetail;
                })
                .map(changeOrderDetail -> baseRepository.save(changeOrderDetail))
                .map(newOrderDetail -> response(newOrderDetail))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail){

        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getId())
                .itemId(orderDetail.getId())
                .build();

        return Header.OK(body);
    }
}
