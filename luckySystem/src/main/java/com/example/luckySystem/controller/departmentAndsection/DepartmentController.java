package com.example.luckySystem.controller.departmentAndsection;
import com.example.luckySystem.dto.employee.DepartmentEmployeeCountDto;
import com.example.luckySystem.service.departmentAndsection.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
