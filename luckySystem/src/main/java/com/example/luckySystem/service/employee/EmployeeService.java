package com.example.luckySystem.service.employee;


import com.example.luckySystem.dto.agent.AgentDTO;
import com.example.luckySystem.dto.employee.EmployeeDTO;
import com.example.luckySystem.entity.Agent;
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
import java.util.stream.Collectors;

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


    public List<EmployeeDTO> getAllEmployee(){
        List<Employee>employeeList=employeeRepo.findAll();
        return employeeList.stream().map(this::convertEmployeeEntityToDTO).collect(Collectors.toList());
    }

    private EmployeeDTO convertEmployeeEntityToDTO(Employee unit) {

        return new EmployeeDTO(unit.getEmployee_id(),unit.getJob_role(),unit.getSalary_type(),unit.getEmployee_name(),
                unit.getDob(),unit.getAddress(),unit.getGender(),unit.getMa_uma(),unit.getContact(),
                unit.getCompany_status(),unit.getCv(),unit.getDep_id().getDepartment_id(),unit.getSec_id().getSection_id());
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

    public Employee updateEmployeeDetails(EmployeeDTO employeeDto) {
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

    public boolean deleteEmployee(EmployeeDTO employeeDto){
        employeeRepo.delete(modelMapper.map(employeeDto,Employee.class));
        return true;
    }

    public EmployeeDTO  getEmployeeByEmployeeID(String employeeId){
        Employee employee=employeeRepo.getEmployeeByEmployeeID(employeeId);
        return modelMapper.map(employee,EmployeeDTO.class);
    }



}
