package food.order.service.application;

import food.order.service.application.cqrs.commands.CreateOrderCommand;
import food.order.service.application.cqrs.handles.OrderCreateCommandHandler;
import food.order.service.application.cqrs.handles.OrderTrackCommandHandler;
import food.order.service.application.dto.create.CreateOrderResponse;
import food.order.service.application.dto.track.TrackOrderQuery;
import food.order.service.application.dto.track.TrackOrderResponse;
import food.order.service.application.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private OrderCreateCommandHandler orderCreateCommandHandler;

    private OrderTrackCommandHandler orderTrackCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                       OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
