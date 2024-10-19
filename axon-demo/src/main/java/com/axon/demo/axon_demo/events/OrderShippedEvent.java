package com.axon.demo.axon_demo.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class OrderShippedEvent {

    private final String orderId;

}
