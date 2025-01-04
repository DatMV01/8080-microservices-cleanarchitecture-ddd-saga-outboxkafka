package food.order.service.application.cqrs.helpers;

import food.order.service.application.cqrs.commands.CreateOrderCommand;
import food.order.service.application.mapper.OrderDataMapper;
import food.order.service.application.ports.output.message.payment.OrderCreatedPaymentRequestMessagePublisher;
import food.order.service.application.ports.output.repository.CustomerRepository;
import food.order.service.application.ports.output.repository.OrderRepository;
import food.order.service.application.ports.output.repository.RestaurantRepository;
import food.order.service.domain.OrderDomainService;
import food.order.service.domain.aggregate.CustomerAggregate;
import food.order.service.domain.aggregate.OrderAggregate;
import food.order.service.domain.aggregate.RestaurantAggregate;
import food.order.service.domain.event.OrderCreatedEvent;
import food.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedEventDomainEventPublisher;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             @Autowired(required = false) CustomerRepository customerRepository,
                             @Autowired(required = false) RestaurantRepository restaurantRepository,
                             OrderDataMapper orderDataMapper,
                             OrderCreatedPaymentRequestMessagePublisher orderCreatedEventDomainEventPublisher) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        RestaurantAggregate restaurant = checkRestaurant(createOrderCommand);
        OrderAggregate order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant,
                orderCreatedEventDomainEventPublisher);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private RestaurantAggregate checkRestaurant(CreateOrderCommand createOrderCommand) {
        RestaurantAggregate restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<RestaurantAggregate> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find restaurant with restaurant id: {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Could not find restaurant with restaurant id: " +
                    createOrderCommand.getRestaurantId());
        }
        return optionalRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<CustomerAggregate> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private OrderAggregate saveOrder(OrderAggregate order) {
        OrderAggregate orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}
