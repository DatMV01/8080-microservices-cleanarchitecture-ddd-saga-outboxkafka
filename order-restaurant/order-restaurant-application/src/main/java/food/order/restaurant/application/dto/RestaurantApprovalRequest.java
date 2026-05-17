package food.order.restaurant.application.dto;

import food.order.common.domain.valueobject.RestaurantOrderStatus;
import food.order.restaurant.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalRequest {
    private String id;
    private String sagaId;
    private String restaurantId;
    private String orderId;
    private RestaurantOrderStatus restaurantOrderStatus;
    private java.util.List<Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
}
