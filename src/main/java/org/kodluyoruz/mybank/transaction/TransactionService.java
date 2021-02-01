package org.kodluyoruz.mybank.transaction;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Transaction get(UUID id){
        return transactionRepo.findTransactionByAccount_Id(id);
    }
}
