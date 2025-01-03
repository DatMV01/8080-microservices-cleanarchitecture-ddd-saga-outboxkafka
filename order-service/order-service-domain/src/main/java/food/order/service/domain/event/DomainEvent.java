package food.order.service.domain.event;

public interface DomainEvent<T> {
    void fire();
}
