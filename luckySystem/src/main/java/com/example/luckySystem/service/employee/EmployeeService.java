package com.example.luckySystem.service.employee;


import com.example.luckySystem.dto.employee.EmployeeDTO;
import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.Section;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.depAndsec.DepartmentRepo;
import com.example.luckySystem.repo.depAndsec.SectionRepo;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private SectionRepo sectionRepo;

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

    public Employee addEmployee(EmployeeDTO employeeDto) {
        System.out.println("Service request to add an employee with ID: " + employeeDto.getEmployeeid());
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        if (employee.getEmployee_id() == null) {
            employee.setEmployee_id(employeeDto.getEmployeeid()); // Ensure this is correctly named and implemented
        }
        Department department=departmentRepo.findById(employeeDto.getDep_id())
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.BAD_REQUEST));

        Section section=sectionRepo.findById(employeeDto.getSec_id())
                .orElseThrow(() -> new AppException("Section not found", HttpStatus.BAD_REQUEST));

        employee.setDep_id(department);
        employee.setSec_id(section);
        
        employeeRepo.save(employee);
        System.out.println(employee);
        return employee;
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
