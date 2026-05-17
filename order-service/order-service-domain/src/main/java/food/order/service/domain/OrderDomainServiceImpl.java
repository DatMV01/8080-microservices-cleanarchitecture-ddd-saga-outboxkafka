package food.order.service.domain;

import food.order.service.domain.aggregate.OrderAggregate;
import food.order.service.domain.aggregate.RestaurantAggregate;
import food.order.service.domain.entitiy.Product;
import food.order.service.domain.event.OrderCancelledEvent;
import food.order.service.domain.event.OrderCreatedEvent;
import food.order.service.domain.event.OrderPaidEvent;
import food.order.common.domain.event.publisher.DomainEventPublisher;
import food.order.service.domain.exception.OrderDomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class OrderDomainServiceImpl implements OrderDomainService {

    private static Logger log = LoggerFactory.getLogger(OrderDomainServiceImpl.class);

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(
            OrderAggregate order,
            RestaurantAggregate restaurant,
            DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {

        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();

        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(UTC), orderCreatedEventDomainEventPublisher);

    }

    @Override
    public OrderPaidEvent payOrder(
            OrderAggregate order,
            DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
        order.pay();

        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now((UTC)), orderPaidEventDomainEventPublisher);

    }

    @Override
    public void approveOrder(OrderAggregate order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(
            OrderAggregate order, List<String> failureMessages,
            DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue());

        return new OrderCancelledEvent(order, ZonedDateTime.now((UTC)),
                orderCancelledEventDomainEventPublisher);
    }

    @Override
    public void cancelOrder(OrderAggregate order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateRestaurant(RestaurantAggregate restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() + " is currently not active!");
        }
    }

    private void setOrderProductInformation(OrderAggregate order, RestaurantAggregate restaurant) {
        order.getItems().forEach(
                orderItem -> restaurant.getProducts().forEach(restaurantProduct -> {
                    Product currentProduct = orderItem.getProduct();
                    if (currentProduct.equals(restaurantProduct)) {
                        currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
                    }
                }));
    }

}
