package org.kodluyoruz.mybank.transaction;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private UUID id;
    @GeneratedValue
    @NotNull
    @Size(min = 22, max = 34)
    private String IBAN;
    private long amount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String recepientName;
    private String recepientSurname;

    public Transaction toTransaction(){
        return Transaction.builder()
                .id(this.id)
                .IBAN(this.IBAN)
                .amount(this.amount)
                .createdDate(this.createdDate)
                .modifiedDate(this.modifiedDate)
                .recepientName(this.recepientName)
                .recepientSurname(this.recepientSurname)
                .build();
    }
}
