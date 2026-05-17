package food.order.service.domain.aggregate;

import food.order.common.domain.aggregate.AggregateRoot;
import food.order.service.domain.entitiy.Product;
import food.order.common.domain.valueobject.RestaurantId;

import java.util.List;

public class RestaurantAggregate extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;

    private RestaurantAggregate(Builder builder) {
        super.setId(builder.restaurantId);
        products = builder.products;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private RestaurantId restaurantId;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public RestaurantAggregate build() {
            return new RestaurantAggregate(this);
        }
    }
}
