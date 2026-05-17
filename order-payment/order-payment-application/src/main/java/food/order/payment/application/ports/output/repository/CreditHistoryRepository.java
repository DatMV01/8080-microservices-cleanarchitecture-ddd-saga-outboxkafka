package food.order.payment.application.ports.output.repository;

import food.order.common.domain.valueobject.CustomerId;
import food.order.payment.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
