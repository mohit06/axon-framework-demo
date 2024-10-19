package com.axon.demo.axon_demo.services;

import com.axon.demo.axon_demo.FindAllOrderedProductsQuery;
import com.axon.demo.axon_demo.Order;
import com.axon.demo.axon_demo.events.OrderConfirmedEvent;
import com.axon.demo.axon_demo.events.OrderCreatedEvent;
import com.axon.demo.axon_demo.events.OrderShippedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersEventHandler {

    private final Map<String, Order> orders = new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent event) {
        String orderId = event.getOrderId();
        orders.put(orderId, new Order(orderId, event.getProductId()));
        System.out.println("Order Created Event");
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        String orderId = event.getOrderId();
        Order order = orders.get(event.getOrderId());
        order.setOrderConfirmed();
        System.out.println("Order Confirmed Event");
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        String orderId = event.getOrderId();
        Order order = orders.get(event.getOrderId());
        order.setOrderShipped();
        System.out.println("Order Shipped Event");
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        System.out.println("Query Handler");
        return new ArrayList<>(orders.values());
    }
}