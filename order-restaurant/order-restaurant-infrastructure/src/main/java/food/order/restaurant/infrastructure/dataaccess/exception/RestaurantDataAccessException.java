package food.order.restaurant.infrastructure.dataaccess.exception;

public class RestaurantDataAccessException extends RuntimeException{

    public RestaurantDataAccessException(String message) {
        super(message);
    }
}
