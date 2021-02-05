package org.kodluyoruz.mybank.card;

import lombok.*;

import java.util.UUID;

@Data
@Builder
public class CardDto {
    private UUID id;
    private String cardNumber;
    private int boundary;

    public Card toCard(){
        return Card.builder()
                .id(this.id)
                .cardNumber(this.cardNumber)
                .boundary(this.boundary)
                .build();
    }
}
