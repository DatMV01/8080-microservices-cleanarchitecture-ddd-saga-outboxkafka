package food.order.service.infrastructure.dataaccess.customer.mapper;

import food.order.common.domain.valueobject.CustomerId;
import food.order.service.domain.aggregate.CustomerAggregate;
import food.order.service.infrastructure.dataaccess.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public CustomerAggregate customerEntityToCustomer(CustomerEntity customerEntity) {
        return new CustomerAggregate(new CustomerId(customerEntity.getId()));
    }
}
