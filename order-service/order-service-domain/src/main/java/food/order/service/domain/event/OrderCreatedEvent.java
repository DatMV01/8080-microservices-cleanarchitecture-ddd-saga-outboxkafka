package food.order.service.domain.event;

import food.order.service.domain.aggregate.OrderAggregate;
import food.order.common.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

    private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

    public OrderCreatedEvent(OrderAggregate order,
                             ZonedDateTime createdAt,
                             DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
        super(order, createdAt);
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderCreatedEventDomainEventPublisher.publish(this);
    }
}
