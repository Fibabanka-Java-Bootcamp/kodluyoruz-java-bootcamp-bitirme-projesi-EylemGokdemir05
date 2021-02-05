package org.kodluyoruz.mybank.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.card.Card;
import org.kodluyoruz.mybank.customer.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
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
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Card> cards;

    public AccountDto accountDto(){
        return AccountDto.builder()
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
