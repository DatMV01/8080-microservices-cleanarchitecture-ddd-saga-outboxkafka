package food.order.service.domain.event;

import food.order.service.domain.aggregate.OrderAggregate;
import food.order.common.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    private final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;

    public OrderCancelledEvent(OrderAggregate order,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
        super(order, createdAt);
        this.orderCancelledEventDomainEventPublisher = orderCancelledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderCancelledEventDomainEventPublisher.publish(this);
    }
}
