package food.order.restaurant.application.ports.output.message;

import food.order.common.domain.event.publisher.DomainEventPublisher;
import food.order.restaurant.domain.event.OrderApprovedEvent;

public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
}
