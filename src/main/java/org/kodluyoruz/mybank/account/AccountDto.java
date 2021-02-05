package org.kodluyoruz.mybank.account;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AccountDto {
    private UUID id;
    private String accountNumber;
    private int balance;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @GeneratedValue
    @NotNull
    @Size(min = 22, max = 34)
    private String IBAN;
    private String currency;

    public AccountDto() {

    }

    public AccountDto(UUID id, String accountNumber, int balance, LocalDateTime createdDate, LocalDateTime modifiedDate, @NotNull @Size(min = 22, max = 34) String IBAN, String currency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.IBAN = IBAN;
        this.currency = currency;
    }

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
