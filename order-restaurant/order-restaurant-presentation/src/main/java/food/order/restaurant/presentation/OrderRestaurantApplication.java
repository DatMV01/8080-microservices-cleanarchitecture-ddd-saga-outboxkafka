package food.order.restaurant.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"food.order.restaurant.infrastructure",
        "food.order.common.infrastructure",})
@EntityScan(basePackages = {"food.order.restaurant.infrastructure",
        "food.order.common.infrastructure"})
@SpringBootApplication(scanBasePackages = {"food.order.restaurant",
        "food.order.common"})
public class OrderRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderRestaurantApplication.class, args);
    }

}
