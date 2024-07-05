package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.employee.GatePassesHistorySummaryDto;
import com.example.luckySystem.dto.salary.GatePassDto;
import com.example.luckySystem.service.salaryservice.GatepassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")

public class GatePassController {

    @Autowired
    public GatepassService gatepassService;

    @PostMapping("/addGatepass")
    public GatePassDto addGatepass(@RequestBody GatePassDto gatePassDto) {
        System.out.println("Received request to save user data.");
        return gatepassService.addGatepass(gatePassDto);
    }

    @GetMapping("/getGatepass")
    public ResponseEntity<List<GatePassDto>> getGatepassDetails() {
        System.out.println("Received request to save data.");
        List<GatePassDto> gatePassDtos = gatepassService.getGatepassDetails();
        return ResponseEntity.ok().body(gatePassDtos);
    }

    @PutMapping("/updateGatePassStatus")
    public ResponseEntity<String> updateGatePassStatus(@RequestParam Long employee_gate_pass_id, @RequestParam String status) {
        gatepassService.updateGatePassStatus(employee_gate_pass_id, status);
        return ResponseEntity.ok("GatePass status updated successfully.");
    }

    @GetMapping("/gatePassesHistorySummary")
    public ResponseEntity<GatePassesHistorySummaryDto> gatePassesHistorySummary(@RequestParam String empId) {

        GatePassesHistorySummaryDto summaryDto = gatepassService.gatePassesHistorySummary(empId);
        if (summaryDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summaryDto);
    }


}
