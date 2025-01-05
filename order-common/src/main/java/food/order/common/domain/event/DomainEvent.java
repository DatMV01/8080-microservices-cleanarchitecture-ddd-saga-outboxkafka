package food.order.common.domain.event;

public interface DomainEvent<T> {
    void fire();
}
