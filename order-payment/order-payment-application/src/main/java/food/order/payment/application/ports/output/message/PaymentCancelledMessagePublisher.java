package food.order.payment.application.ports.output.message;

import food.order.common.domain.event.publisher.DomainEventPublisher;
import food.order.payment.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
