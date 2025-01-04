package food.order.service.application.ports.output.message.payment;

import food.order.service.domain.event.OrderCancelledEvent;
import food.order.service.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher
        extends DomainEventPublisher<OrderCancelledEvent> {
}
