package com.residencia.dell.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.dell.entities.Products;
import com.residencia.dell.services.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    //Find by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Products> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productsService.findById(id), headers, HttpStatus.OK);
    }

    //Find all
    @GetMapping("/findall/{page}/{registersQuantity}")
    public ResponseEntity<List<Products>> findAll(@PathVariable Integer page, @PathVariable Integer registersQuantity) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(productsService.findAll(page, registersQuantity), headers, HttpStatus.OK);
    }

    //Count
    @GetMapping("/count")
    public Long count() {
        return productsService.count();
    }

    //Update
    @PutMapping("/update/{id}")
    public Products update( @Valid @RequestBody Products product, @PathVariable Integer id){
        return productsService.update(product, id);
    }

    //Create
    @PostMapping("/create")
    public ResponseEntity<Products> save( @RequestBody Products product){
        HttpHeaders headers = new HttpHeaders();
        if(null != productsService.create(product))
            return new ResponseEntity<>(productsService.create(product), headers, HttpStatus.OK);
        else
            return new ResponseEntity<>(productsService.create(product), headers, HttpStatus.BAD_REQUEST);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Products> delete(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        boolean isRemoved = productsService.delete(id);
        if (isRemoved) {
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
}
