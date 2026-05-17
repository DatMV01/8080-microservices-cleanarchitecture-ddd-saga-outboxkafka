package food.order.service.application.cqrs.commands;

import food.order.service.application.dto.create.OrderAddressDTO;
import food.order.service.application.dto.create.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final UUID restaurantId;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final List<OrderItemDTO> items;
    @NotNull
    private final OrderAddressDTO address;
}
