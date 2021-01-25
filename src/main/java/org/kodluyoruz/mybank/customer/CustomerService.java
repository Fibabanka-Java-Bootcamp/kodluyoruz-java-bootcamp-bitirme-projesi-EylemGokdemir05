package org.kodluyoruz.mybank.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer create(Customer customer){
        return customerRepo.save(customer);
    }

    public Page<Customer> list(PageRequest pageRequest){
        return customerRepo.findAll(pageRequest);
    }

    public Optional<Customer> get(UUID id){
        return customerRepo.findById(id);
    }

    @Modifying
    public Customer update(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer delete(UUID id){
        return customerRepo.delete(id);
    }
}
