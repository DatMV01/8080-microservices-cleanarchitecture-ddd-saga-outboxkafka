package food.order.service.domain.event;

import food.order.service.domain.aggregate.OrderAggregate;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<OrderAggregate> {
    private final OrderAggregate order;
    private final ZonedDateTime createdAt;

    public OrderEvent(OrderAggregate order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public OrderAggregate getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
