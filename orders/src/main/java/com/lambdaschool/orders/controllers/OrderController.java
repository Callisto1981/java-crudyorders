package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderServices orderServices;


    //http://localhost:2019/orders/order/7
    @GetMapping(value = "order/{ordnum}", produces = "application/json")
    public ResponseEntity<?> getOrder(@PathVariable long ordnum)
    {
        Order order = orderServices.findById(ordnum);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    //http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceamount()
    {
        Order order = orderServices.findAmount();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

//    POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewOrder(@Valid @PathVariable Order newOrder)
    {
        newOrder = orderServices.save(newOrder);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{ordnum}")
            .buildAndExpand(newOrder.getOrdnum())
            .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(newOrder, responseHeaders, HttpStatus.CREATED);
    }
//    PUT http://localhost:2019/orders/order/63
//    DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long id)
    {
        orderServices.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
