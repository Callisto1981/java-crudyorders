package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

public interface OrderServices
{
    Order save(Order order);
    Order findById(long id);
    Order findAmount(double count);
    void delete(long id);
}
