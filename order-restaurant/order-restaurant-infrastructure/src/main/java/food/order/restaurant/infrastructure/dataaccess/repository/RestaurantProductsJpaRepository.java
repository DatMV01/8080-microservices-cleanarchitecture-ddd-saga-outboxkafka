package food.order.restaurant.infrastructure.dataaccess.repository;

import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantProductsJpaRepository extends JpaRepository<RestaurantProducts, UUID> {

}
