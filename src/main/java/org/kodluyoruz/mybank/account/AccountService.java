package org.kodluyoruz.mybank.account;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account create(Account account){
        return accountRepo.save(account);
    }

    public Account delete(UUID id){
        return accountRepo.deleteAccountById(id);
    }
}
