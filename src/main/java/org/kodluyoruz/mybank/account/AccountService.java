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

    public AccountDto transferMoney(String IBAN,String toIBAN, int moneyTransfer){
        AccountDto demandDepositAccountDto=accountRepo.getByIban(IBAN)
                .orElseThrow(() ->   new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found")).accountDto();
        accountType=demandDepositAccountDto.getAccountType();
        if (accountType==AccountType.DEMAND_DEPOSIT_ACCOUNT){
            int demandDepositMoney=demandDepositAccountDto.getBalance();
            AccountDto accountDto=accountRepo.getByIban(toIBAN)
                    .orElseThrow(() ->   new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found")).accountDto();
            int money=accountDto.getBalance();
            if (accountDto.getAccountType()==AccountType.SAVINGS_ACCOUNT){
                return moneyTransfer(moneyTransfer, demandDepositAccountDto, demandDepositMoney, accountDto, money);
            }else {
                return moneyTransfer(moneyTransfer, demandDepositAccountDto, demandDepositMoney, accountDto, money);
            }
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account is not found!");
        }
    }

    private AccountDto moneyTransfer(int moneyTransfer, AccountDto demandDepositAccountDto, int demandDepositMoney, AccountDto accountDto, int money) {
        if (demandDepositMoney - moneyTransfer < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough money in your demand deposit account!");
        } else {
            if (demandDepositAccountDto.getCurrency().equals(accountDto.getCurrency())) {
                demandDepositAccountDto.setBalance(demandDepositMoney - moneyTransfer);
                accountDto.setBalance(money + moneyTransfer);
            } else {
                RestExchangeDto exchangeDto =
                        Exchange.convertedCurrency.apply(demandDepositAccountDto.getCurrency());
                demandDepositAccountDto.setBalance(demandDepositMoney - moneyTransfer);
                accountDto.setBalance((int) (money + (moneyTransfer * exchangeDto.getRates().get(accountDto.getCurrency()))));
            }
            accountRepo.save(accountDto.toAccount()).accountDto();
            return accountRepo.save(demandDepositAccountDto.toAccount()).accountDto();
        }
    }
}
