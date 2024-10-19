package com.axon.demo.axon_demo;

import com.axon.demo.axon_demo.events.OrderConfirmedEvent;
import com.axon.demo.axon_demo.events.OrderCreatedEvent;
import com.axon.demo.axon_demo.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    protected OrderAggregate() { }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        System.out.println("Publishing Order Created Event.");
        apply(new OrderCreatedEvent(command.getOrderId(), command.getProductId()));
        System.out.println("Order Created Event Published successfully.");
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        if (orderConfirmed) {
            System.out.println("Order is already confirmed.");
            return;
        }
        System.out.println("Publishing order confirmed event.");
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) throws Exception {
        if (!orderConfirmed) {
            System.out.println("Order needs to be confirmed before publishing ShipOrder command.");
            throw new Exception("Unconfirmed Order"+command.getOrderId());
        }
        System.out.println("Publishing Ship order command ");
        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        System.out.println("Received OrderCreated Event::"+event.getOrderId());
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        System.out.println("Received Order Confirmed Event :: "+event.getOrderId());
        orderConfirmed = true;
    }
}