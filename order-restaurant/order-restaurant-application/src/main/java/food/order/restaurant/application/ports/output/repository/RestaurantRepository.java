package food.order.restaurant.application.ports.output.repository;

import food.order.restaurant.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
