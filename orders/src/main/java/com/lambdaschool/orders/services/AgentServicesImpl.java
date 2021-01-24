package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent findById(long id)
    {
        return agentrepos.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Id " + id + "Not found!"));
    }

    @Transactional
    @Override
    public void deleteAllAgents()
    {
        agentrepos.deleteAll();
    }

    @Override
    public void delete(long id)
    {
        if (agentrepos.findById(id)
        .isPresent())
        {
            agentrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Agent " + id + "Not found!");
        }
    }
}
