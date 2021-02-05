package org.kodluyoruz.mybank.debit;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DebitRepo extends CrudRepository<Debit, UUID> {
    boolean findDebitsByIdAndMinDebit(UUID id,Integer debit);
    Debit findAllById(UUID id);
}
