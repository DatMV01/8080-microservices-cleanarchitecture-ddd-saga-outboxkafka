package food.order.restaurant.domain;

import food.order.common.domain.event.publisher.DomainEventPublisher;
import food.order.restaurant.domain.entity.Restaurant;
import food.order.restaurant.domain.event.OrderApprovalEvent;
import food.order.restaurant.domain.event.OrderApprovedEvent;
import food.order.restaurant.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
