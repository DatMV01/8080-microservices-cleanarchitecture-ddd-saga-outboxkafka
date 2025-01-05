package food.order.restaurant.application.ports.input;

import food.order.restaurant.application.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
