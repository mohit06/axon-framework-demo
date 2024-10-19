package com.axon.demo.axon_demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@RequiredArgsConstructor
public class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
}
