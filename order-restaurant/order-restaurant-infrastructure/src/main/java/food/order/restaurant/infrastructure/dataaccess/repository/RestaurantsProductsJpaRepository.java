package food.order.restaurant.infrastructure.dataaccess.repository;

import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantsProducts;
import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantsProductsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantsProductsJpaRepository extends JpaRepository<RestaurantsProducts, RestaurantsProductsId> {

}

