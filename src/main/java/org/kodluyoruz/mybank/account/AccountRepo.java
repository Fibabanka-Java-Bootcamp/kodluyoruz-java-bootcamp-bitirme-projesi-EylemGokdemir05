package org.kodluyoruz.mybank.account;

import org.kodluyoruz.mybank.customer.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends CrudRepository<Account, UUID> {
    Account deleteAccountById(UUID id);
    Optional<Account> findById(UUID id);
    Optional<Account> findByCustomer(Customer customer);
    Account getByIBAN(String IBAN);
}
