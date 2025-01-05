package food.order.service.domain;

import food.order.service.domain.aggregate.OrderAggregate;
import food.order.service.domain.aggregate.RestaurantAggregate;
import food.order.service.domain.event.OrderCancelledEvent;
import food.order.service.domain.event.OrderCreatedEvent;
import food.order.service.domain.event.OrderPaidEvent;
import food.order.common.domain.event.publisher.DomainEventPublisher;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(OrderAggregate order, RestaurantAggregate restaurant, DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher);

    OrderPaidEvent payOrder(OrderAggregate order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);

    void approveOrder(OrderAggregate order);

    OrderCancelledEvent cancelOrderPayment(OrderAggregate order, List<String> failureMessages, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher);

    void cancelOrder(OrderAggregate order, List<String> failureMessages);
}
