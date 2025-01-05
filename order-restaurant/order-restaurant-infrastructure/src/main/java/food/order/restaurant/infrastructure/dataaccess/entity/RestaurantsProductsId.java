package food.order.restaurant.infrastructure.dataaccess.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantsProductsId implements Serializable {

    private UUID id;
    private UUID restaurantId;
    private UUID productId;

}
