package food.order.payment.domain;

import food.order.common.domain.event.publisher.DomainEventPublisher;
import food.order.payment.domain.entity.CreditEntry;
import food.order.payment.domain.entity.CreditHistory;
import food.order.payment.domain.entity.Payment;
import food.order.payment.domain.event.PaymentCancelledEvent;
import food.order.payment.domain.event.PaymentCompletedEvent;
import food.order.payment.domain.event.PaymentEvent;
import food.order.payment.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent>
                                                    paymentCompletedEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
