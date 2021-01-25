package org.kodluyoruz.mybank.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepo extends CrudRepository<Customer, UUID> {
    Page<Customer> findAll(Pageable pageable);
    Customer delete(UUID id);
}
