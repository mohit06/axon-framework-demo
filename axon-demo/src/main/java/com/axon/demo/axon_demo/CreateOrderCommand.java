package com.axon.demo.axon_demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
@Data
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String productId;

}
