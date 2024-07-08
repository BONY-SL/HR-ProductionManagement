package com.example.luckySystem.service.departmentAndsection;
import com.example.luckySystem.dto.employee.DepartmentEmployeeCountDto;
import com.example.luckySystem.dto.sectionanddepartment.DepartmentDto;
import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.repo.depAndsec.DepartmentRepo;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private EmployeeRepo employeeRepo;


    public List<DepartmentEmployeeCountDto> getDepartmentEmployeeCounts() {
        return departmentRepository.getDepartmentEmployeeCounts();
    }

    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartment_id(departmentDto.getDepartment_id());
        department.setDepartment_name(departmentDto.getDepartment_name());
        department.setStart_date(departmentDto.getStart_date());

        if (departmentDto.getHead_of_department() != null) {
            Employee headOfDepartment = new Employee();
            headOfDepartment.setEmployee_id(departmentDto.getHead_of_department());
            department.setHead_of_department(headOfDepartment);
        }

        departmentRepository.save(department);
        return departmentDto;
    }

    public List<DepartmentDto> DepartmentDetails() {
        return departmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private DepartmentDto convertToDTO(Department department) {

        return DepartmentDto.builder()
                .department_id(department.getDepartment_id())
                .department_name(department.getDepartment_name())
                .start_date(department.getStart_date())
                .head_of_department(department.getHead_of_department().getEmployee_name())
                .build();
    }

    public List<DepartmentDto> DepartmentDetails1() {
        List<Department> DepartmentList = departmentRepository.findAll();
        return modelMapper.map(DepartmentList, new TypeToken<List<DepartmentDto>>() {}.getType());
    }

    public DepartmentDto updateDepartmentDetails(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        departmentRepository.save(department);
        return departmentDto;
    }

}
