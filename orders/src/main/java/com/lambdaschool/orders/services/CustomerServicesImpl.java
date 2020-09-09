package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    CustomerRepository customerrepos;

    @Override
    public Customer findAllOrders()
    {
        return null;
    }

    @Override
    public Customer findById(long id)
    {
        return customerrepos.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Id " + id + "Not found!"));
    }

    @Override
    public Customer findByCustName(String name)
    {
        return customerrepos.findByCustname();
    }
}
