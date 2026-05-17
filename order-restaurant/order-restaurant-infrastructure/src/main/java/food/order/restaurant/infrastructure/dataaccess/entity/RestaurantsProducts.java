package food.order.restaurant.infrastructure.dataaccess.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_products")
@IdClass(RestaurantsProductsId.class)
@Entity
public class RestaurantsProducts {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "restaurant_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID restaurantId;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID productId;


}
