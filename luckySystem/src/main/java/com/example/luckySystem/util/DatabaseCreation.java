package com.example.luckySystem.util;

import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.Section;
import com.example.luckySystem.entity.User;
import com.example.luckySystem.repo.depAndsec.DepartmentRepo;
import com.example.luckySystem.repo.depAndsec.SectionRepo;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.user.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseCreation {

    private final DepartmentRepo departmentRepo;
    private final SectionRepo sectionRepo;
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;

    @PostConstruct
    public void CreationInitialData() {

        String[] departmentNames = {
                "Production",
                "Administration",
                "Finance",
                "Sales and Marketing",
                "Human Resource",
                "Quality Assurance",
                "Maintenance",
                "Stores",
                "Transport"
        };

        String[] sectionNames = {
                "Bottle washing",
                "Yogurt section",
                "UHT",
                "Packet",
                "Ice cream",
                "Milk taffy",
                "Administration",
                "Finance",
                "Sales and Marketing",
                "Human Resource",
                "Quality Assurance",
                "Maintenance",
                "Stores",
                "Transport"
        };

        List<Department> departmentList = departmentRepo.findAll();

        if (departmentList.isEmpty()) {

            for (int i = 1; i <= 9; i++) {
                Department department = new Department();
                department.setDepartment_id("DEP0"+i);
                department.setDepartment_name(departmentNames[i - 1]);
                department.setStart_date(new Date());
                departmentRepo.save(department);

                if (i == 1) {
                    for (int a = 1; a <= 6; a++) {
                        Section section = new Section();
                        section.setSection_id("SEC0"+a);
                        section.setSection_name(sectionNames[a - 1]);
                        section.setStart_date(new Date());
                        section.setDep_id(department);
                        sectionRepo.save(section);
                    }
                }
            }

            List<Department> departmentList2 = departmentRepo.findAll();
            int i=1;
            for(int b=6;b<14;b++){

                Department department2= new Department();
                Section section2 = new Section();
                department2.setDepartment_id(departmentList2.get(i).getDepartment_id());

                i=i+1;

                if(b < 9 ){
                    section2.setSection_id("SEC0"+(b+1));
                }else{
                    section2.setSection_id("SEC"+(b+1));
                }

                section2.setSection_name(sectionNames[b]);
                section2.setStart_date(new Date());
                section2.setDep_id(department2);
                sectionRepo.save(section2);
            }
        }



        Department employeeDepartment = new Department();
        Section employeeSection =  new Section();

        employeeDepartment.setDepartment_id("DEP05");
        employeeSection.setSection_id("SEC10");

        Employee adminEmployee = new Employee();
        adminEmployee.setEmployee_id("EPF00001");
        Employee employee = employeeRepo.findByEmployeeId("EPF00001");

        if(employee == null){
            adminEmployee.setEmployee_id("EPF00001");
            adminEmployee.setAddress("example");
            adminEmployee.setCompany_status("permeate");
            adminEmployee.setContact("0716543552");
            adminEmployee.setDob(new Date());
            adminEmployee.setCv(null);
            adminEmployee.setEmployee_name("admin");
            adminEmployee.setGender("Male");
            adminEmployee.setJob_role("admin");
            adminEmployee.setMa_uma("UnMarried");
            adminEmployee.setSalary_type("admin");
            adminEmployee.setDepartment(employeeDepartment);
            adminEmployee.setSec_id(employeeSection);
            employeeRepo.save(adminEmployee);

            User admin =new User();
            admin.setContact("0716543552");
            admin.setEmail("example@gmail.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin1234"));
            admin.setRoles("HR_Manager");
            admin.setUsername("admin");
            admin.setEmployee(adminEmployee);
            userRepo.save(admin);
        }
    }
}
