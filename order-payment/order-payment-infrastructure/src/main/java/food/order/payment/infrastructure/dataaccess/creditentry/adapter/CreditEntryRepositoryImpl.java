package food.order.payment.infrastructure.dataaccess.creditentry.adapter;
 import food.order.common.domain.valueobject.CustomerId;
 import food.order.payment.application.ports.output.repository.CreditEntryRepository;
 import food.order.payment.domain.entity.CreditEntry;
 import food.order.payment.infrastructure.dataaccess.creditentry.mapper.CreditEntryDataAccessMapper;
 import food.order.payment.infrastructure.dataaccess.creditentry.repository.CreditEntryJpaRepository;
 import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

    public CreditEntryRepositoryImpl(CreditEntryJpaRepository creditEntryJpaRepository,
                                     CreditEntryDataAccessMapper creditEntryDataAccessMapper) {
        this.creditEntryJpaRepository = creditEntryJpaRepository;
        this.creditEntryDataAccessMapper = creditEntryDataAccessMapper;
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        return creditEntryDataAccessMapper
                .creditEntryEntityToCreditEntry(creditEntryJpaRepository
                        .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
    }

    @Override
    public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository
                .findByCustomerId(customerId.getValue())
                .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
    }
}
