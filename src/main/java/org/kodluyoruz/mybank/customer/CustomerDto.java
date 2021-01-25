package org.kodluyoruz.mybank.customer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID id;
    @NotBlank(message = "Name is necessary")
    private String name;
    @NotBlank(message = "Surname is necessary")
    private String surname;

    public Customer toCustomer(){
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .build();
    }
}
