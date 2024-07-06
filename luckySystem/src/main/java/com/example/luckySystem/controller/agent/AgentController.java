package com.example.luckySystem.controller.agent;
import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.agent.AgentPurchaseDTO;
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
    public ResponseEntity<Agent> addNewAgent(@RequestBody AgentDTO dto) {
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

    @PatchMapping("/deleteAgentDetails/{agentId}")
    public ResponseEntity<?> deleteAgentDetails(@PathVariable Long agentId, @RequestParam String deleteReason) {
        agentService.deleteAgentDetails(agentId, deleteReason);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/undoDeleteAgentDetails/{agentId}")
    public ResponseEntity<?> undoDeleteAgentDetails(@PathVariable Long agentId) {
        agentService.undoDeleteAgentDetails(agentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getAgentPurchaseDetails/{agentId}/{year}/{month}")
    public List<AgentPurchaseDTO> getPurchases(@PathVariable Long agentId, @PathVariable int year, @PathVariable int month) {

        System.out.println(agentId);
        System.out.println(year);
        System.out.println(month);
        return agentService.getPurchasesByAgentIdAndMonthYear(agentId, month, year);
    }
}
