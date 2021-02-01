package org.kodluyoruz.mybank.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.customer.Customer;
import org.kodluyoruz.mybank.debit.Debit;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue
    private UUID id;
    private String cardNumber;
    private int boundary;
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "card")
    private Set<Debit> debits;

    public CardDto cardDto(){
        return CardDto.builder()
                .id(this.id)
                .cardNumber(this.cardNumber)
                .boundary(this.boundary)
                .build();
    }
}
