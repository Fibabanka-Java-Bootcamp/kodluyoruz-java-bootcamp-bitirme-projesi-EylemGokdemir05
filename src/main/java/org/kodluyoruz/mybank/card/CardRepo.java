package org.kodluyoruz.mybank.card;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepo extends CrudRepository<Card, UUID> {
    Optional<Card> findById(UUID id);
}
