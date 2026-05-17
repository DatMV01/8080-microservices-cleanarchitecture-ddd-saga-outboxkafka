package food.order.service.infrastructure.kafka.publisher;

import food.order.common.infrastructure.kafka.model.RestaurantApprovalRequestAvroModel;
import food.order.common.infrastructure.kafka.producer.KafkaMessageHelper;
import food.order.common.infrastructure.kafka.producer.service.KafkaProducer;
import food.order.service.application.ports.output.message.restaurant.OrderPaidRestaurantRequestMessagePublisher;
import food.order.service.domain.event.OrderPaidEvent;
import food.order.service.infrastructure.kafka.config.OrderServiceConfigData;
import food.order.service.infrastructure.kafka.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public PayOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                         OrderServiceConfigData orderServiceConfigData,
                                         KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer,
                                         KafkaMessageHelper orderKafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
    }

    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();

        try {
            RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel =
                    orderMessagingDataMapper.orderPaidEventToRestaurantApprovalRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                    orderId,
                    restaurantApprovalRequestAvroModel,
                    orderKafkaMessageHelper
                            .getKafkaCallback(orderServiceConfigData.getRestaurantApprovalRequestTopicName(),
                                    restaurantApprovalRequestAvroModel,
                                    orderId,
                                    "RestaurantApprovalRequestAvroModel"));

            log.info("RestaurantApprovalRequestAvroModel sent to kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending RestaurantApprovalRequestAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
