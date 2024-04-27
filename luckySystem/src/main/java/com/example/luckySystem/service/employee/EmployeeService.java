package com.example.luckySystem.service.employee;


import com.example.luckySystem.dto.employee.EmployeeDTO;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.repo.employee.EmployeeRepo;
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

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public boolean employeeExists(String employeeID){
        return employeeRepo.findById(employeeID).isPresent();
    }



    @Autowired
    private ModelMapper modelMapper;


    public List<EmployeeDTO> getEmployee(){
        List<Employee>employeeList=employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>() {}.getType());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDto) {
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

    public EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDto) {
        System.out.println("work");
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepo.save(employee);
        return employeeDto;
    }

    public boolean deleteEmployee(EmployeeDTO employeeDto){
        employeeRepo.delete(modelMapper.map(employeeDto,Employee.class));
        return true;
    }

    public EmployeeDTO  getEmployeeByEmployeeID(String employeeId){
        Employee employee=employeeRepo.getEmployeeByEmployeeID(employeeId);
        return modelMapper.map(employee,EmployeeDTO.class);
    }



}
