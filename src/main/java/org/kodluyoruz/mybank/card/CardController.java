package org.kodluyoruz.mybank.card;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public CardDto get(@PathVariable("id") UUID id){
        return cardService.get(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Card not found!")).cardDto();
    }

    @GetMapping("/{id}/debt")
    public CardDto getDebt(@PathVariable("id") UUID id,
                           @PathVariable("debt") int debt){
        return cardService.getDebt(id,debt)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Debt is not found!")).cardDto();
    }
}
