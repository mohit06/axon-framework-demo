package com.axon.demo.axon_demo;

import com.axon.demo.axon_demo.events.OrderCreatedEvent;
import org.aspectj.lang.annotation.Before;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class AxonDemoApplicationTests {

	private static FixtureConfiguration<OrderAggregate> fixture;

	@BeforeAll
	public static void setUp() {
		fixture = new AggregateTestFixture<>(OrderAggregate.class);
	}

	@Test
	void contextLoads() {
		String orderId = UUID.randomUUID().toString();
		String productId = "Deluxe Chair";
		fixture.givenNoPriorActivity()
				.when(new CreateOrderCommand(orderId, productId))
				.expectEvents(new OrderCreatedEvent(orderId, productId));
	}

}
