package org.kodluyoruz.mybank.card;

import org.kodluyoruz.mybank.debit.DebitRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {
    private final DebitRepo debitRepo;
    private final CardRepo cardRepo;

    public CardService(DebitRepo debitRepo, CardRepo cardRepo) {
        this.debitRepo = debitRepo;
        this.cardRepo = cardRepo;
    }

    public Optional<Card> get(UUID id){
        return cardRepo.findById(id);
    }
}
