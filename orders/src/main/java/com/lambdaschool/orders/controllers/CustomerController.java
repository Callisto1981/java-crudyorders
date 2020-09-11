package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

//    POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes="application/json", produces = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody Customer newCustomer)
    {
        newCustomer = customerServices.save(newCustomer);//data
        HttpHeaders responseHeaders = new HttpHeaders();//creating httpHeader

        //Here we build a new URI, using the Class-ServletUriComponentsBuilder and the
        //function-fromCurrentRequest which is in fact the current input/request
        //then creating/adding the "path()", building and expanding the new Object/class which
        //uses it's method "getCustcode()" to get the code/id of the customer
        //and adding it to the Uri function.
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{custcode}")
            .buildAndExpand(newCustomer.getCustcode())
            .toUri(); //creating URI - Get http.../customers/customer/7
        responseHeaders.setLocation(newCustomerURI);// setting location in headers with newCustomerURI
        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);


    }
//    PUT http://localhost:2019/customers/customer/19
//    PATCH http://localhost:2019/customers/customer/19
//    DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustId(@PathVariable long custcode)
    {
        customerServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
