package food.order.service.domain.aggregate;

import food.order.common.domain.aggregate.AggregateRoot;
import food.order.common.domain.valueobject.CustomerId;

public class CustomerAggregate extends AggregateRoot<CustomerId> {
    public CustomerAggregate() {
    }

    public CustomerAggregate(CustomerId customerId) {
        super.setId(customerId);
    }
}
