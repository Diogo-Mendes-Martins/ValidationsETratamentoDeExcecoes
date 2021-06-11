package com.residencia.dell.services;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    //Create customer
    public Customers create (Customers customer){
        return customersRepository.save(customer);
    }

    //Find by ID
    public Customers findById(Integer id) {
        return customersRepository.findById(id).get();
    }
}
