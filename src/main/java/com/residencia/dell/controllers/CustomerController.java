package com.residencia.dell.controllers;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Create
    @PostMapping("/create")
    public ResponseEntity<Customers> create (@Valid @RequestBody Customers customer){
        HttpHeaders headers = new HttpHeaders();
        if(null != customerService.create(customer))
            return new ResponseEntity<>(customerService.create(customer), headers, HttpStatus.OK);
        else
            return new ResponseEntity<>(customerService.create(customer), headers, HttpStatus.BAD_REQUEST);
    }

    //Find by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Customers> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(customerService.findById(id), headers, HttpStatus.OK);
    }
}
