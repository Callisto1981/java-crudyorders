package com.lambdaschool.orders.models;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentid;
    private String Type;

    public Payment(String type)
    {
        Type = type;
    }

    public Payment()
    {
    }

    public long getPaymentid()
    {
        return paymentid;
    }

    public void setPaymentid(long paymentid)
    {
        this.paymentid = paymentid;
    }

    public String getType()
    {
        return Type;
    }

    public void setType(String type)
    {
        Type = type;
    }
}
