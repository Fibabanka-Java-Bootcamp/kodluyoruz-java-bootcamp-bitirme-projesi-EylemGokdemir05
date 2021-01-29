package org.kodluyoruz.mybank.debit;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/debit")
public class DebitController {
    private final DebitService debitService;

    public DebitController(DebitService debitService) {
        this.debitService = debitService;
    }

    @GetMapping("/{id}")
    public Debit get(@PathVariable("id") UUID id){
        return debitService.get(id);
    }
}
