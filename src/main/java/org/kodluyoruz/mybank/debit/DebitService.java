package org.kodluyoruz.mybank.debit;

import org.kodluyoruz.mybank.transaction.PaymentType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DebitService {
    private final DebitRepo debitRepo;

    public DebitService(DebitRepo debitRepo) {
        this.debitRepo = debitRepo;
    }

    public Debit get(UUID id) {
        return debitRepo.findAllById(id);
    }

    public boolean getDebit(UUID id, Integer debit) {
        if (debitRepo.findDebitsByIdAndMinDebit(id, debit) && debit != 0) {
            System.out.println("Debit: " + debit);
            return true;
        }
        return false;
    }
}
