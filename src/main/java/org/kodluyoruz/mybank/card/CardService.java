package org.kodluyoruz.mybank.card;


import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {
    private final CardRepo cardRepo;

    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public Optional<Card> get(UUID id){
        return cardRepo.findById(id);
    }
}
