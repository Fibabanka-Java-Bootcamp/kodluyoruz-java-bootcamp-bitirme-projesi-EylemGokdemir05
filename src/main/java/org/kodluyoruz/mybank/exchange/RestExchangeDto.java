package org.kodluyoruz.mybank.exchange;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;

@Data
@Builder
public class RestExchangeDto {
    private HashMap<String, Double> rates;
    private String base;
    private LocalDate date;

}
