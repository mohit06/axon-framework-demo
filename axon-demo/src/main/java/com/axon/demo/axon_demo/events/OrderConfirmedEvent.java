package com.axon.demo.axon_demo.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderConfirmedEvent {
    private final String orderId;

}
