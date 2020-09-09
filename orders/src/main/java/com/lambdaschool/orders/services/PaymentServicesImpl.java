package com.lambdaschool.orders.services;


import com.lambdaschool.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paymentServices")
public class PaymentServicesImpl implements PaymentServices
{
    @Autowired
    PaymentRepository paymentrepos;

    @Transactional
    @Override
    public void deleteAllPayments()
    {
        paymentrepos.deleteAll();
    }
}
