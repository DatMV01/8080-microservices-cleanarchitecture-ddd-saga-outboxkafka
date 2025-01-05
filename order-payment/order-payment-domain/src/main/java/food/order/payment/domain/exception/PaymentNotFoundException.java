package food.order.payment.domain.exception;

import food.order.common.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {

    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
