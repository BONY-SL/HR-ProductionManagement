package com.example.luckySystem.service;


import com.example.luckySystem.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public boolean employeeExists(String employeeID){
        return employeeRepo.findById(employeeID).isPresent();
    }
}
