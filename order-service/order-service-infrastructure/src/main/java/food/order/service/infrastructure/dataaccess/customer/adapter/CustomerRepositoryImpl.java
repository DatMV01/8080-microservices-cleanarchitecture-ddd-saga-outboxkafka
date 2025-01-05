package food.order.service.infrastructure.dataaccess.customer.adapter;

import food.order.service.application.ports.output.repository.CustomerRepository;
import food.order.service.domain.aggregate.CustomerAggregate;
import food.order.service.infrastructure.dataaccess.customer.mapper.CustomerDataAccessMapper;
import food.order.service.infrastructure.dataaccess.customer.repository.CustomerJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Optional<CustomerAggregate> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::customerEntityToCustomer);
    }
}
