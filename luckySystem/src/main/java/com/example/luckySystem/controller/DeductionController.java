package com.example.luckySystem.controller;

import com.example.luckySystem.dto.DeductionDto;
import com.example.luckySystem.service.DeductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
public class DeductionController {

    @Autowired
    private DeductionService deductionService;



    @GetMapping("/getDeduction")
    public List<DeductionDto>getAllDeductionDetails(){
        return deductionService.getDeductionDetails();
    }

    @PutMapping("/updateDeduction")
    public DeductionDto updateDeduction(@RequestBody DeductionDto deductionDto) {
        return deductionService.updateDeductionDetails(deductionDto);

    }

    @PostMapping("/addDeduction")
    public DeductionDto addDeduction(@RequestBody DeductionDto deductionDto) {
        System.out.println("Received request to save deduction data.");
        return deductionService.addDeductionDetails(deductionDto);
    }


    @DeleteMapping("/deleteDeduction")
    public boolean deleteDeduction(@RequestBody DeductionDto deductionDto) {
        return deductionService.deleteDeductionDetails(deductionDto);
    }

    @GetMapping("/getDeductionByID/{deductionID}")
    public DeductionDto getDeductionById(@PathVariable String deductionID){
        return deductionService.getDeductionByDeductionID(deductionID);
    }

}


