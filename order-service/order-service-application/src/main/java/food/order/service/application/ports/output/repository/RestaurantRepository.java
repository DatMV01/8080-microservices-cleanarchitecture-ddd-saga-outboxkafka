package food.order.service.application.ports.output.repository;

import food.order.service.domain.aggregate.RestaurantAggregate;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<RestaurantAggregate> findRestaurantInformation(RestaurantAggregate restaurant);
}
