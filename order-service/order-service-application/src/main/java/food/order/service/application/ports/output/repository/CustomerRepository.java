package food.order.service.application.ports.output.repository;

import food.order.service.domain.aggregate.CustomerAggregate;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<CustomerAggregate> findCustomer(UUID customerId);
}
