package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController
{
    @Autowired
    AgentServices agentServices;

    //http://localhost:2019/agents/agent/9
    @GetMapping(value = "agent/{agentcode}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long agentcode)
    {
        Agent agent = agentServices.findById(agentcode);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    //DELETE http://localhost:2019/agents/unassigned/8
    @DeleteMapping(value = "/unassigned/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id)
    {
        agentServices.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE http://localhost:2019/agents/unassigned/16
}
