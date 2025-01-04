package food.order.service.infrastructure.dataaccess.order.adapter;

import food.order.service.application.ports.output.repository.OrderRepository;
import food.order.service.domain.aggregate.OrderAggregate;
import food.order.service.domain.valueobject.TrackingId;
import food.order.service.infrastructure.dataaccess.order.entity.OrderEntity;
import food.order.service.infrastructure.dataaccess.order.mapper.OrderDataAccessMapper;
import food.order.service.infrastructure.dataaccess.order.repository.OrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
                               OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public OrderAggregate save(OrderAggregate order) {
        OrderEntity orderEntity = orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order));
        return orderDataAccessMapper.orderEntityToOrder(orderEntity);
    }

    @Override
    public Optional<OrderAggregate> findByTrackingId(TrackingId trackingId) {
        Optional<OrderEntity> orderEntity = orderJpaRepository.findByTrackingId(trackingId.getValue());
        return orderEntity.map(orderDataAccessMapper::orderEntityToOrder);
    }

}
