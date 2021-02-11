package org.kodluyoruz.mybank.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@Valid @RequestBody AccountDto accountDto){
        return accountService.create(accountDto.toAccount()).accountDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountSummary(@PathVariable("id")UUID id){
        return ResponseEntity.ok().body(accountService.getAccountSummary(id));
    }

    @PutMapping("/{IBAN}/{toIBAN}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto transferMoney(@PathVariable("IBAN") String IBAN,
                                    @PathVariable("toIBAN") String toIBAN,
                                    @RequestParam("moneyTransfer") int moneyTransfer){
        return accountService.transferMoney(IBAN,toIBAN, moneyTransfer);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDto delete(@PathVariable("id")UUID id){
        return accountService.delete(id).accountDto();
    }
}
