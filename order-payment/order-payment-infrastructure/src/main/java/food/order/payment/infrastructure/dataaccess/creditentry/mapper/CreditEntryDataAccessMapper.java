package food.order.payment.infrastructure.dataaccess.creditentry.mapper;

import food.order.common.domain.valueobject.CustomerId;
import food.order.common.domain.valueobject.Money;
import food.order.payment.domain.entity.CreditEntry;
import food.order.payment.domain.valueobject.CreditEntryId;
import food.order.payment.infrastructure.dataaccess.creditentry.entity.CreditEntryEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryDataAccessMapper {

    public CreditEntry creditEntryEntityToCreditEntry(CreditEntryEntity creditEntryEntity) {
        return CreditEntry.builder()
                .creditEntryId(new CreditEntryId(creditEntryEntity.getId()))
                .customerId(new CustomerId(creditEntryEntity.getCustomerId()))
                .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount()))
                .build();
    }

    public CreditEntryEntity creditEntryToCreditEntryEntity(CreditEntry creditEntry) {
        return CreditEntryEntity.builder()
                .id(creditEntry.getId().getValue())
                .customerId(creditEntry.getCustomerId().getValue())
                .totalCreditAmount(creditEntry.getTotalCreditAmount().getAmount())
                .build();
    }

}
