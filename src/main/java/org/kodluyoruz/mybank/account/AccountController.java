package org.kodluyoruz.mybank.account;

import org.springframework.http.HttpStatus;
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDto delete(@PathVariable("id")UUID id){
        return accountService.delete(id).accountDto();
    }
}
