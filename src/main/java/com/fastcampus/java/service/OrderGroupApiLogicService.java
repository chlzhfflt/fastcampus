package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.OrderGroup;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.OrderGroupApiRequest;
import com.fastcampus.java.model.network.response.OrderGroupApiResponse;
import com.fastcampus.java.repository.OrderGroupRepository;
import com.fastcampus.java.repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(body.getOrderAt())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> response(orderGroup)) // .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

       return baseRepository.findById(body.getId())
                .map(orderGroup -> { // 있는경우
                  orderGroup
                          .setStatus(body.getStatus())
                          .setOrderType(body.getOrderType())
                          .setRevAddress(body.getRevAddress())
                          .setRevName(body.getRevName())
                          .setPaymentType(body.getPaymentType())
                          .setTotalPrice(body.getTotalPrice())
                          .setTotalQuantity(body.getTotalQuantity())
                          .setOrderAt(body.getOrderAt())
                          .setArrivalDate(body.getArrivalDate())
                          .setUser(userRepository.getOne(body.getUserId()));

                  return orderGroup;
                })
               .map(changeOrderGroup -> baseRepository.save(changeOrderGroup))
               .map(newOrderGroup -> response(newOrderGroup)) // (this::response)
               .orElseGet(()->Header.ERROR("데이터 없음")); // 없는경우
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                }) // 있는경우
                .orElseGet(()->Header.ERROR("데이터 없음")); // 없는경우
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup){

        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(body);
    }
}
