package com.example.luckySystem.service.salaryservice;


import com.example.luckySystem.dto.salary.EmployeeDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.repo.salary.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;


    public List<EmployeeDto> getEmployee(){
        List<Employee>employeeList=employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDto>>() {}.getType());
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        System.out.println("work");
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepo.save(employee);
        return employeeDto;

    }

    public List<Object[]> countActiveEmployeesByDepartment() {
        return employeeRepo.countActiveEmployeesByDepartment();
    }

    public int getTotalEmployeesCount() {
        return employeeRepo.getTotalEmployeesCount();
    }

    public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto) {
        System.out.println("work");
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepo.save(employee);
        return employeeDto;
    }

    public boolean deleteEmployee(EmployeeDto employeeDto){
        employeeRepo.delete(modelMapper.map(employeeDto,Employee.class));
        return true;
    }

    public EmployeeDto  getEmployeeByEmployeeID(String employeeId){
        Employee employee=employeeRepo.getEmployeeByEmployeeID(employeeId);
        return modelMapper.map(employee,EmployeeDto.class);
    }



}
