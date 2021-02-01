package org.kodluyoruz.mybank.debit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.card.Card;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debit")
public class Debit {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    private LocalDateTime modifiedDate;
    private Integer totalAmount;
    private Integer minDebit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id",referencedColumnName = "id")
    private Card card;

    public DebitDto debitDto(){
        return DebitDto.builder()
                .id(this.id)
                .createdDate(this.createdDate)
                .endDate(this.endDate)
                .modifiedDate(this.modifiedDate)
                .totalAmount(this.totalAmount)
                .minDebit(this.minDebit)
                .build();
    }
}
