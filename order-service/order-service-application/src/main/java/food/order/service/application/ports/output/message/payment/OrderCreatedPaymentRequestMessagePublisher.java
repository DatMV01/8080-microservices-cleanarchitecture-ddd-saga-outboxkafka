package food.order.service.application.ports.output.message.payment;

import food.order.service.domain.event.OrderCreatedEvent;
import food.order.common.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher
        extends DomainEventPublisher<OrderCreatedEvent> {
}
