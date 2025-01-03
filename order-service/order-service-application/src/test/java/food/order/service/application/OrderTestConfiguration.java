package food.order.service.application;

import food.order.service.application.ports.output.message.payment.OrderCancelledPaymentRequestMessagePublisher;
import food.order.service.application.ports.output.message.payment.OrderCreatedPaymentRequestMessagePublisher;
import food.order.service.application.ports.output.message.restaurant.OrderPaidRestaurantRequestMessagePublisher;
import food.order.service.application.ports.output.repository.CustomerRepository;
import food.order.service.application.ports.output.repository.OrderRepository;
import food.order.service.application.ports.output.repository.RestaurantRepository;
import food.order.service.domain.OrderDomainService;
import food.order.service.domain.OrderDomainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "food.order.service")
public class OrderTestConfiguration {

    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}
