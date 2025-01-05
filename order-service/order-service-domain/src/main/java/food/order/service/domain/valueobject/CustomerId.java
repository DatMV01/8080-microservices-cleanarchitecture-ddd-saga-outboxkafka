package food.order.service.domain.valueobject;

import food.order.common.domain.valueobject.BaseId;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID value) {
        super(value);
    }
}
