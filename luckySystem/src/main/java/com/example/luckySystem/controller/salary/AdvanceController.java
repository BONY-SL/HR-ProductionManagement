package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.salary.AdvanceDto;
import com.example.luckySystem.service.salaryservice.AdvanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
public class AdvanceController {

    @Autowired
    public AdvanceService advanceService;

    @GetMapping("/getAdvance")
    public ResponseEntity<List<AdvanceDto>> getAdvance(){
        System.out.println("Received request to save data.");
        List<AdvanceDto> advanceDtos = advanceService.getAdvance();
        return ResponseEntity.ok().body(advanceDtos);
    }

    @PostMapping("/addAdvance")
    public AdvanceDto addAdvance(@RequestBody AdvanceDto advanceDto) {
        System.out.println("Received request to xx  data.");
        return advanceService.addAdvanceDetails(advanceDto);
    }

    @PutMapping("/updateAdvance")
    public AdvanceDto updateAdvance(@RequestBody AdvanceDto advanceDto) {
        return advanceService.updateAdvanceDetails(advanceDto);
    }

    @DeleteMapping("/deleteAdvance")
    public boolean deleteAdvance(@RequestBody AdvanceDto advanceDto) {
        return  advanceService.deleteAdvanceDetails(advanceDto);
    }

    @GetMapping("/getAdvanceByID/{advanceID}")
    public AdvanceDto getAdvanceById(@PathVariable String advanceID) {
        return advanceService.getAdvanceByID(advanceID);
    }



}
