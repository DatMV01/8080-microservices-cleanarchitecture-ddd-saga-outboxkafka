package food.order.service.domain.valueobject;

import food.order.common.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}
