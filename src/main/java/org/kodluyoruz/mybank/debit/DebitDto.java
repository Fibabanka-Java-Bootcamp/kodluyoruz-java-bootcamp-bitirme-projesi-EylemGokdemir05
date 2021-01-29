package org.kodluyoruz.mybank.debit;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebitDto {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    private LocalDateTime modifiedDate;
    private Integer totalAmount;
    private Integer minDebit;

    public Debit toDebit(){
        return Debit.builder()
                .id(this.id)
                .createdDate(this.createdDate)
                .endDate(this.endDate)
                .modifiedDate(this.modifiedDate)
                .totalAmount(this.totalAmount)
                .minDebit(this.minDebit)
                .build();
    }
}
