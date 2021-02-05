package org.kodluyoruz.mybank.customer;

import lombok.*;
import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.account.AccountDto;
import org.kodluyoruz.mybank.card.Card;
import org.kodluyoruz.mybank.card.CardDto;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CustomerDto {
    private UUID id;
    @NotBlank(message = "Name is necessary")
    private String name;
    @NotBlank(message = "Surname is necessary")
    private String surname;
    private Set<AccountDto> accounts;
    private Set<CardDto> cards;

    public Customer toCustomer(){
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                /*.accounts(accounts.stream()
                    .map(AccountDto::toAccount)
                    .collect(Collectors.toSet()))
                .cards(cards.stream()
                    .map(CardDto::toCard)
                    .collect(Collectors.toSet()))*/
                .build();
    }
}
