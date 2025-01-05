package food.order.restaurant.infrastructure.dataaccess.repository;

import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantEntity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantJpaRepository2 extends JpaRepository<RestaurantEntity2, UUID> {

}
