package food.order.service.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"food.order.service.infrastructure", "food.order.common.infrastructure",})
@EntityScan(basePackages = {"food.order.service.infrastructure", "food.order.common.infrastructure"})
@SpringBootApplication(scanBasePackages = {"food.order.service", "food.order.common"})
public class OrderServiceStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceStartApplication.class, args);
    }

}