package food.order.service.application.ports.output.message.restaurant;

import food.order.service.domain.event.OrderPaidEvent;
import food.order.common.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
