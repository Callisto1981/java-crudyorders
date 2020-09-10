package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrderRepository orderrepos;
    @Override
    public Order findById(long id)
    {
        return orderrepos.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("This order number " + id + "Not found!"));
    }

    @Override
    public Order findAmount(double count)
    {
        Order order = orderrepos.count(count); // need to change to long but want a double
            return order;

    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (orderrepos.findById(id)
        .isPresent())
        {
            orderrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Order " + id + "Not found!");
        }

    }
}
