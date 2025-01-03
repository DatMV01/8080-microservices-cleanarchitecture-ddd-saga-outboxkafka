package food.order.service.application.mapper;

import food.order.service.application.cqrs.commands.CreateOrderCommand;
import food.order.service.application.dto.create.CreateOrderResponse;
import food.order.service.application.dto.create.OrderAddressDTO;
import food.order.service.application.dto.create.OrderItemDTO;
import food.order.service.application.dto.track.TrackOrderResponse;
import food.order.service.domain.aggregate.OrderAggregate;
import food.order.service.domain.aggregate.RestaurantAggregate;
import food.order.service.domain.entitiy.OrderItem;
import food.order.service.domain.entitiy.Product;
import food.order.service.domain.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public RestaurantAggregate createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return RestaurantAggregate.builder()
                .restaurantId(
                        new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                                new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public OrderAggregate createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return OrderAggregate.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(OrderAggregate order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(OrderAggregate order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<OrderItemDTO> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddressDTO orderAddressDTO) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddressDTO.getStreet(),
                orderAddressDTO.getPostalCode(),
                orderAddressDTO.getCity()
        );
    }
}
