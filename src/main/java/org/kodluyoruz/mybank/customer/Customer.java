package org.kodluyoruz.mybank.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.card.Card;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Account> accounts;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Card> cards;

    public CustomerDto customerDto(){
        return CustomerDto.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .build();
    }
}
