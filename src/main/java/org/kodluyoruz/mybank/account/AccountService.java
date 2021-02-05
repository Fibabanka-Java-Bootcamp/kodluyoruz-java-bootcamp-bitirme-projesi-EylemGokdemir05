package org.kodluyoruz.mybank.account;

import lombok.SneakyThrows;
import org.kodluyoruz.mybank.customer.Customer;
import org.kodluyoruz.mybank.customer.CustomerRepo;
import org.kodluyoruz.mybank.exception.AccountException;
import org.kodluyoruz.mybank.exception.CustomerException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private final CustomerRepo customerRepo;

    public AccountService(AccountRepo accountRepo, CustomerRepo customerRepo) {
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
    }

    public Account create(Account account){
        return accountRepo.save(account);
    }

    public Account delete(UUID id){
        return accountRepo.deleteAccountById(id);
    }

    @SneakyThrows
    public AccountDto getAccountSummary(UUID id) {
        Customer customer=new Customer();
        customer.setId(id);

        if (!customerRepo.findById(id).isPresent()){
            throw new CustomerException("Customer not found!");
        }

        Optional<Account> account=accountRepo.findByCustomer(customer);
        if (!account.isPresent()){
            throw new AccountException("Account not found!");
        }

        AccountDto accountDto=new AccountDto();
        BeanUtils.copyProperties(account.get().getAccountNumber(),accountDto);
        accountDto.setCurrency(account.get().getCurrency());
        accountDto.setBalance(BigDecimal.valueOf(1000.0));
        return accountDto;
    }
}
