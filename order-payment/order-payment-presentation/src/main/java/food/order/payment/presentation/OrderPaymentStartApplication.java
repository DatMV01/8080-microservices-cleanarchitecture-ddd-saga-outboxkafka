package food.order.payment.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"food.order.payment.infrastructure", "food.order.common.infrastructure",})
@EntityScan(basePackages = {"food.order.payment.infrastructure", "food.order.common.infrastructure"})
@SpringBootApplication(scanBasePackages = {"food.order.payment", "food.order.common"})
public class OrderPaymentStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderPaymentStartApplication.class, args);
    }
}
