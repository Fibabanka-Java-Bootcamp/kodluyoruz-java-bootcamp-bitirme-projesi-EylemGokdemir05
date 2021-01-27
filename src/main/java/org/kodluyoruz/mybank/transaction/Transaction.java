package org.kodluyoruz.mybank.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private UUID id;
    @GeneratedValue
    @NotNull
    @Size(min = 22, max = 34)
    private String IBAN;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;
    private long amount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String recepientName;
    private String recepientSurname;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public TransactionDto transactionDto(){
        return TransactionDto.builder()
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
