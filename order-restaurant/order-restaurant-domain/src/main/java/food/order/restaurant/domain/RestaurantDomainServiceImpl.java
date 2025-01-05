package food.order.restaurant.domain;

import food.order.common.domain.event.publisher.DomainEventPublisher;
import food.order.common.domain.valueobject.OrderApprovalStatus;
import food.order.restaurant.domain.entity.Restaurant;
import food.order.restaurant.domain.event.OrderApprovalEvent;
import food.order.restaurant.domain.event.OrderApprovedEvent;
import food.order.restaurant.domain.event.OrderRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Slf4j
public class RestaurantDomainServiceImpl implements RestaurantDomainService {

    @Override
    public OrderApprovalEvent validateOrder(Restaurant restaurant,
                                            List<String> failureMessages,
                                            DomainEventPublisher<OrderApprovedEvent>
                                                    orderApprovedEventDomainEventPublisher,
                                            DomainEventPublisher<OrderRejectedEvent>
                                                    orderRejectedEventDomainEventPublisher) {
        restaurant.validateOrder(failureMessages);
        log.info("Validating order with id: {}", restaurant.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Order is approved for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(UTC),
                    orderApprovedEventDomainEventPublisher);
        } else {
            log.info("Order is rejected for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(UTC),
                    orderRejectedEventDomainEventPublisher);
        }
    }
}
