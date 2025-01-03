package food.order.service.domain.entitiy;

import java.time.ZonedDateTime;
import java.util.Objects;

import static java.time.ZoneOffset.UTC;

public abstract class BaseEntity<ID> {
    private ID id;

    private ZonedDateTime createdAt = ZonedDateTime.now(UTC);

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
