package com.example.luckySystem.controller.departmentAndsection;
import com.example.luckySystem.dto.employee.DepartmentEmployeeCountDto;
import com.example.luckySystem.dto.salary.BasicSalaryDto;
import com.example.luckySystem.dto.salary.GatePassDto;
import com.example.luckySystem.dto.sectionanddepartment.DepartmentDto;
import com.example.luckySystem.dto.sectionanddepartment.SectionDto;
import com.example.luckySystem.service.departmentAndsection.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;




    @GetMapping("/employeeCountsByDepartments")
    public List<DepartmentEmployeeCountDto> getDepartmentEmployeeCounts() {
        return departmentService.getDepartmentEmployeeCounts();
    }

    @PostMapping("/addDepartment")
    public void addDepartment(@RequestBody DepartmentDto departmentDto) {
        System.out.println("Received request to save department data.");
        System.out.println(departmentDto);
        departmentService.addDepartment(departmentDto);
    }

    @GetMapping("/getDepartment")
    public ResponseEntity<List<DepartmentDto>> getDepartmentDetails() {
         System.out.println("Received request to save data.");
         List<DepartmentDto> departmentDtos = departmentService.DepartmentDetails();
         return ResponseEntity.ok().body(departmentDtos);
    }

    @GetMapping("/getDepartment1")
    public List<DepartmentDto> getSalary() {
        return departmentService.DepartmentDetails1();
    }

    @PutMapping("/updateDepartment")
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartmentDetails(departmentDto);
    }
}
