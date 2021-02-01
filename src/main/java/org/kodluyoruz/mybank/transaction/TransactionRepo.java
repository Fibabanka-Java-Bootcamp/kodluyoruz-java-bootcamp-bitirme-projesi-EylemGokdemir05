package org.kodluyoruz.mybank.transaction;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepo extends CrudRepository<Transaction, UUID> {
    Transaction findTransactionByAccount_Id(UUID id);
}
