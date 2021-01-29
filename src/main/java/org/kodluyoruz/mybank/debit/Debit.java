package org.kodluyoruz.mybank.debit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
