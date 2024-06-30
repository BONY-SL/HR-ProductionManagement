package com.example.luckySystem.service.departmentAndsection;

import com.example.luckySystem.dto.employee.DepartmentEmployeeCountDto;
import com.example.luckySystem.repo.depAndsec.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {


    @Autowired
    private DepartmentRepo departmentRepository;

    public List<DepartmentEmployeeCountDto> getDepartmentEmployeeCounts() {
        return departmentRepository.getDepartmentEmployeeCounts();
    }
}
