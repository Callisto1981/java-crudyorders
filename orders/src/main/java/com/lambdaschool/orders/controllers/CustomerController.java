package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerServices customerServices;

    //http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> getAllOrders()
    {
        Customer customer = customerServices.findAllOrders();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    //http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerId(@PathVariable long custcode)
    {
        Customer customer = customerServices.findById(custcode);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    //http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{namelike}", produces = "application/json")
    public ResponseEntity<?> getNameLike(@PathVariable String namelike)
    {
        Customer customer = customerServices.findByCustName(namelike);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
