package com.example.luckySystem.controller.agent;


import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.bottles.EmptyBottleDTO;
import com.example.luckySystem.entity.Agent;
import com.example.luckySystem.service.agent.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")

public class AgentController {


    @Autowired
    private AgentService agentService;

    @PostMapping("/addNewAgent")
    public ResponseEntity<Agent> addDailyEmptyBottleUnit(@RequestBody AgentDTO dto) {
        Agent savedEntity=agentService.saveNewAgentDetails(dto);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/getallAgentDetails")
    public ResponseEntity<List<AgentDTO>> getallAgentDetails() {
        List<AgentDTO> agent = agentService.getallAgentDetails();
        return ResponseEntity.ok().body(agent);
    }

    @PutMapping("/updateAgentDetails")
    public ResponseEntity<?> updateAgentDetails(@RequestBody AgentDTO dto) {
        agentService.updateAgentDetails(dto);
        return ResponseEntity.ok().build();
    }

}
