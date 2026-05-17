package food.order.payment.application.mapper;

import food.order.common.domain.valueobject.CustomerId;
import food.order.common.domain.valueobject.Money;
import food.order.common.domain.valueobject.OrderId;
import food.order.payment.application.dto.PaymentRequest;
import food.order.payment.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
