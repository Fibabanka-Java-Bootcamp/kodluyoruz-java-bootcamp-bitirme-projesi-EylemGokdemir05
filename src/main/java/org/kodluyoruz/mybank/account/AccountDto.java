package org.kodluyoruz.mybank.account;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private UUID id;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @GeneratedValue
    @NotNull
    @Size(min = 22, max = 34)
    private String IBAN;
    private String currency;

    public Account toAccount(){
        return Account.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .createdDate(this.createdDate)
                .modifiedDate(this.modifiedDate)
                .IBAN(this.IBAN)
                .currency(this.currency)
                .build();
    }
}
