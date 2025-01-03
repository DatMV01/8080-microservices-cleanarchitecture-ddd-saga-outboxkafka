package food.order.service.application.ports.input.service;

import food.order.service.application.cqrs.commands.CreateOrderCommand;
import food.order.service.application.dto.create.CreateOrderResponse;
import food.order.service.application.dto.track.TrackOrderQuery;
import food.order.service.application.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
