package org.kodluyoruz.mybank.debit;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Service
public class DebitService {
    private final DebitRepo debitRepo;

    public DebitService(DebitRepo debitRepo) {
        this.debitRepo = debitRepo;
    }

    public Debit get(UUID id){
        return debitRepo.findAllById(id);
    }
}
