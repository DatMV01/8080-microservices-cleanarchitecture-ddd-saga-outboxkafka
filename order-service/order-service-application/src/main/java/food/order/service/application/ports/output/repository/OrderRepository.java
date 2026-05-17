package food.order.service.application.ports.output.repository;

import food.order.service.domain.aggregate.OrderAggregate;
import food.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    OrderAggregate save(OrderAggregate order);

    Optional<OrderAggregate> findByTrackingId(TrackingId trackingId);
}
