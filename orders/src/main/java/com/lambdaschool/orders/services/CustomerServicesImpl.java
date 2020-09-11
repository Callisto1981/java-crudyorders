package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.models.Payment;
import com.lambdaschool.orders.repositories.CustomerRepository;
import com.lambdaschool.orders.repositories.OrderRepository;
import com.lambdaschool.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    PaymentRepository paymentrepos;
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
        return customerrepos.findByCustname(name);
    }

    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();
        if (customer.getCustcode() !=0)
        {
            customerrepos.findById(customer.getCustcode())
                .orElseThrow(()-> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found!"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(newCustomer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.getOrders().clear();

        for(Order o : customer.getOrders())
        {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), o.getCustomer(), o.getOrderdescription());

            newOrder.getPayments().clear();
            for(Payment p : o.getPayments())
            {
                Payment newPayment = paymentrepos.findById(p.getPaymentid())
                    .orElseThrow(()-> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));
                newOrder.addPayments(newPayment);
            }
        }
        return customerrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public void delete(long custCode)
    {
        if(customerrepos.findById(custCode)
        .isPresent())
        {
            customerrepos.deleteById(custCode);
        }
        else
        {
            throw new EntityNotFoundException("Customer " + custCode + " Not Found!");
        }
    }
}
