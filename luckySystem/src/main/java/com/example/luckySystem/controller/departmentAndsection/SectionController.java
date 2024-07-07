package com.example.luckySystem.controller.departmentAndsection;


import com.example.luckySystem.dto.salary.BasicSalaryDto;
import com.example.luckySystem.dto.sectionanddepartment.DepartmentDto;
import com.example.luckySystem.dto.sectionanddepartment.SectionDto;
import com.example.luckySystem.service.departmentAndsection.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
@RequiredArgsConstructor
public class SectionController {

    @Autowired
    public SectionService sectionService;

    @PostMapping("/addSection")
    public void addSection(@RequestBody SectionDto sectionDto) {
        System.out.println("Received request to save department data.");
        System.out.println(sectionDto);
        sectionService.addDepartment(sectionDto);

    }

    @GetMapping("/getSection")
    public ResponseEntity<List<SectionDto>> getSectionDetails() {
        System.out.println("Received request to save data.");
        List<SectionDto> sectionDtos =sectionService.SectionDetails();
        return ResponseEntity.ok().body(sectionDtos);
    }

    @PutMapping("/updateSection")
    public SectionDto updateSalary(@RequestBody SectionDto sectionDto) {
        return sectionService.updateSectionDetails(sectionDto);
    }
}
