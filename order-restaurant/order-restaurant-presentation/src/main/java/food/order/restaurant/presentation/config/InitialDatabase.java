package food.order.restaurant.presentation.config;

import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantEntity2;
import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantProducts;
import food.order.restaurant.infrastructure.dataaccess.entity.RestaurantsProducts;
import food.order.restaurant.infrastructure.dataaccess.repository.RestaurantJpaRepository2;
import food.order.restaurant.infrastructure.dataaccess.repository.RestaurantProductsJpaRepository;
import food.order.restaurant.infrastructure.dataaccess.repository.RestaurantsProductsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InitialDatabase implements CommandLineRunner {

    @Autowired
    RestaurantJpaRepository2 restaurantRepository2;

    @Autowired
    RestaurantProductsJpaRepository restaurantProductJpaRepository;

    @Autowired
    RestaurantsProductsJpaRepository restaurantsProductJpaRepository;

    @Override
    public void run(String... args) throws Exception {

        List<RestaurantEntity2> restaurantList = new ArrayList<>();

        restaurantList.add(RestaurantEntity2.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45"))
                .name("restaurant_1")
                .active(true).build());

        restaurantList.add(RestaurantEntity2.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb46"))
                .name("restaurant_2")
                .active(false)
                .build());

        restaurantRepository2.save(restaurantList.get(0));
        restaurantRepository2.save(restaurantList.get(1));

        List<RestaurantProducts> restaurantProductsList = new ArrayList<>();
        restaurantProductsList.add(RestaurantProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb47"))
                .name("product_1")
                .price(BigDecimal.valueOf(25))
                .available(false)
                .build());
        restaurantProductsList.add(RestaurantProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48"))
                .name("product_2")
                .price(BigDecimal.valueOf(50))
                .available(true)
                .build());
        restaurantProductsList.add(RestaurantProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb49"))
                .name("product_3")
                .price(BigDecimal.valueOf(20))
                .available(false)
                .build());
        restaurantProductsList.add(RestaurantProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb50"))
                .name("product_4")
                .price(BigDecimal.valueOf(40))
                .available(true)
                .build());

        restaurantProductJpaRepository.save(restaurantProductsList.get(0));
        restaurantProductJpaRepository.save(restaurantProductsList.get(1));
        restaurantProductJpaRepository.save(restaurantProductsList.get(2));
        restaurantProductJpaRepository.save(restaurantProductsList.get(3));

        List<RestaurantsProducts> restaurantsProductsList = new ArrayList<>();
        restaurantsProductsList.add(RestaurantsProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb51"))
                .restaurantId(restaurantList.get(0).getId())
                .productId(restaurantProductsList.get(0).getId())
                .build());
        restaurantsProductsList.add(RestaurantsProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb52"))
                .restaurantId(restaurantList.get(0).getId())
                .productId(restaurantProductsList.get(1).getId())
                .build());
        restaurantsProductsList.add(RestaurantsProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb53"))
                .restaurantId(restaurantList.get(1).getId())
                .productId(restaurantProductsList.get(2).getId())
                .build());
        restaurantsProductsList.add(RestaurantsProducts.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb54"))
                .restaurantId(restaurantList.get(1).getId())
                .productId(restaurantProductsList.get(3).getId())
                .build());

        restaurantsProductJpaRepository.save(restaurantsProductsList.get(0));
        restaurantsProductJpaRepository.save(restaurantsProductsList.get(1));
        restaurantsProductJpaRepository.save(restaurantsProductsList.get(2));
        restaurantsProductJpaRepository.save(restaurantsProductsList.get(3));
        // SELECT BIN_TO_UUID(id) as id, amount, BIN_TO_UUID(customer_id) as  customer_id, type FROM `food-order-system.payment`.credit_history;

    }
}
