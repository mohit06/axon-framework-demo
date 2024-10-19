package com.axon.demo.axon_demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@RequiredArgsConstructor
@Data
public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
}
