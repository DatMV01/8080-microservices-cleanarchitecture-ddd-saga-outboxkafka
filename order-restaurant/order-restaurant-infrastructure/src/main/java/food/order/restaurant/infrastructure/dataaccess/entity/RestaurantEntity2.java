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
@Table(name = "restaurants")
@Entity
public class RestaurantEntity2 {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
