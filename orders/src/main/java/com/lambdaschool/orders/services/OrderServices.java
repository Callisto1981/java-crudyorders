package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

public interface OrderServices
{
    Order save(Order order);
    Order findById(long id);
//    Long findAmount(long count);
    void delete(long id);
}
