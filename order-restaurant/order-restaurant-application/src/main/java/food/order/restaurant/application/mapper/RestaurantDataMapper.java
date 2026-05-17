package food.order.restaurant.application.mapper;

import food.order.common.domain.valueobject.Money;
import food.order.common.domain.valueobject.OrderId;
import food.order.common.domain.valueobject.OrderStatus;
import food.order.common.domain.valueobject.RestaurantId;
import food.order.restaurant.application.dto.RestaurantApprovalRequest;
import food.order.restaurant.domain.entity.OrderDetail;
import food.order.restaurant.domain.entity.Product;
import food.order.restaurant.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {
    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                    restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream().map(
                                        product -> Product.builder()
                                                .productId(product.getId())
                                                .quantity(product.getQuantity())
                                                .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }
}
