package food.order.restaurant.application.ports.output.repository;

import food.order.restaurant.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
