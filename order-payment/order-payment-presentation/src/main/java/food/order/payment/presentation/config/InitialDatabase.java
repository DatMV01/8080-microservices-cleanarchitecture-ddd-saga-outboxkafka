package food.order.payment.presentation.config;

import food.order.payment.domain.valueobject.TransactionType;
import food.order.payment.infrastructure.dataaccess.credithistory.entity.CreditHistoryEntity;
import food.order.payment.infrastructure.dataaccess.credithistory.repository.CreditHistoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class InitialDatabase implements CommandLineRunner {

    @Autowired
    CreditHistoryJpaRepository creditHistoryJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        creditHistoryJpaRepository.save(CreditHistoryEntity.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb21"))
                .customerId(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41"))
                .amount(BigDecimal.valueOf(500))
                .build());

        creditHistoryJpaRepository.save(CreditHistoryEntity.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb23"))
                .customerId(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41"))
                .amount(BigDecimal.valueOf(100))
                .type(TransactionType.CREDIT)
                .build());

        creditHistoryJpaRepository.save(CreditHistoryEntity.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb24"))
                .customerId(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41"))
                .amount(BigDecimal.valueOf(600))
                .type(TransactionType.CREDIT)
                .build());

        creditHistoryJpaRepository.save(CreditHistoryEntity.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb25"))
                .customerId(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41"))
                .amount(BigDecimal.valueOf(200))
                .type(TransactionType.DEBIT)
                .build());

        creditHistoryJpaRepository.save(CreditHistoryEntity.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb22"))
                .customerId(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb43"))
                .amount(BigDecimal.valueOf(100))
                .build());

        creditHistoryJpaRepository.save(CreditHistoryEntity.builder()
                .id(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb26"))
                .customerId(UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb43"))
                .amount(BigDecimal.valueOf(100))
                .type(TransactionType.CREDIT)
                .build());

        // SELECT BIN_TO_UUID(id) as id, amount, BIN_TO_UUID(customer_id) as  customer_id, type FROM `food-order-system.payment`.credit_history;

    }
}
