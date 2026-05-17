package food.order.payment.application.ports.output.repository;

import food.order.common.domain.valueobject.CustomerId;
import food.order.payment.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
