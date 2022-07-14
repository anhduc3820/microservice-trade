package com.trading.orderservice.service;

import com.trading.orderservice.event.OrderRequest;
import com.trading.orderservice.event.OrderResponse;
import com.trading.orderservice.model.Order;
import com.trading.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        Order orderNew = convertOrderReq(orderRequest);
        orderRepository.save(orderNew);
        return "Order Placed Successfully";
    }

    private Order convertOrderReq(OrderRequest orderRequest) {
        Order orderLineItems = new Order();
        orderLineItems.setPrice(orderRequest.getPrice());
        orderLineItems.setOrderQty(orderRequest.getOrderQty());
        orderLineItems.setSymbol(orderRequest.getSymbol());
        return orderLineItems;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::convertOrderResp).collect(Collectors.toList());
    }

    private OrderResponse convertOrderResp(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setPrice(order.getPrice().toString());
        orderResponse.setOrderQty(order.getOrderQty());
        orderResponse.setSymbol(order.getSymbol());
        orderResponse.setOrderNumber(order.getOrderNumber());
        return orderResponse;
    }
}
