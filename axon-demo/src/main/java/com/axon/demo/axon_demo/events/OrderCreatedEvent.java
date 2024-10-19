package com.axon.demo.axon_demo.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderCreatedEvent {
    private final String orderId;
    private final String productId;
}
