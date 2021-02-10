package org.kodluyoruz.mybank.account;

import org.kodluyoruz.mybank.customer.Customer;
import org.kodluyoruz.mybank.customer.CustomerRepo;
import org.kodluyoruz.mybank.exchange.Exchange;
import org.kodluyoruz.mybank.exchange.RestExchangeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;


@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private final CustomerRepo customerRepo;
    private static AccountType accountType;

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

    public AccountDto getAccountSummary(UUID id) {
        Customer customer=new Customer();
        customer.setId(id);

        if (!customerRepo.findById(id).isPresent()){
            System.out.println("Customer not found!");
        }

        Optional<Account> account=accountRepo.findByCustomer(customer);
        if (!account.isPresent()){
            System.out.println("Account not found!");
        }

        AccountDto accountDto=new AccountDto();
        BeanUtils.copyProperties(account.get().getAccountNumber(),accountDto);
        accountDto.setCurrency(account.get().getCurrency());
        accountDto.setBalance(1000);
        return accountDto;
    }

    public AccountDto transferMoney(String IBAN, int moneyTransfer){
        if (accountType==AccountType.DEMAND_DEPOSIT_ACCOUNT){
            AccountDto demandDepositAccountDto=accountRepo.getByIBAN(IBAN).accountDto();
            int demandDepositMoney=demandDepositAccountDto.getBalance();
            AccountDto savingsAccountDto=accountRepo.getByIBAN(IBAN).accountDto();
            int savingsMoney=savingsAccountDto.getBalance();
            if (demandDepositMoney-moneyTransfer<0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not enough money in your demand deposit account!");
            }else {
                if (demandDepositAccountDto.getCurrency().equals(savingsAccountDto.getCurrency())){
                    demandDepositAccountDto.setBalance(demandDepositMoney-moneyTransfer);
                    savingsAccountDto.setBalance(savingsMoney+moneyTransfer);
                }else {
                    RestExchangeDto exchangeDto=
                            Exchange.convertedCurrency.apply(demandDepositAccountDto.getCurrency());
                    demandDepositAccountDto.setBalance(demandDepositMoney-moneyTransfer);
                    savingsAccountDto.setBalance((int) (savingsMoney+(moneyTransfer*exchangeDto.getRates().get(savingsAccountDto.getCurrency()))));
                }
                accountRepo.save(savingsAccountDto.toAccount()).accountDto();
                return accountRepo.save(demandDepositAccountDto.toAccount()).accountDto();
            }
        } else if (accountType==AccountType.SAVINGS_ACCOUNT){
            AccountDto demandDepositAccountDto=accountRepo.getByIBAN(IBAN).accountDto();
            int demandDepositMoney=demandDepositAccountDto.getBalance();
            AccountDto savingsAccountDto=accountRepo.getByIBAN(IBAN).accountDto();
            int savingsMoney=demandDepositAccountDto.getBalance();
            if (demandDepositMoney-moneyTransfer<0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not enough money in your demand deposit account!");
            }else {
                if (savingsAccountDto.getCurrency().equals(demandDepositAccountDto.getCurrency())){
                    savingsAccountDto.setBalance(savingsMoney-moneyTransfer);
                    demandDepositAccountDto.setBalance(demandDepositMoney+moneyTransfer);
                }else {
                    RestExchangeDto exchangeDto=
                            Exchange.convertedCurrency.apply(savingsAccountDto.getCurrency());
                    savingsAccountDto.setBalance(savingsMoney-moneyTransfer);
                    demandDepositAccountDto.setBalance((int) (demandDepositMoney+(moneyTransfer*exchangeDto.getRates().get(demandDepositAccountDto.getCurrency()))));
                }
                accountRepo.save(demandDepositAccountDto.toAccount()).accountDto();
                return accountRepo.save(savingsAccountDto.toAccount()).accountDto();
            }
        }
        return null; // TODO : bu durumu dusunmen gerek yeniden
    }
}
