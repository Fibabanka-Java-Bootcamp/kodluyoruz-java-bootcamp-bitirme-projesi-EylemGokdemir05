package org.kodluyoruz.mybank.account;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepo extends CrudRepository<Account, UUID> {
    Account deleteAccountById(UUID id);
}
