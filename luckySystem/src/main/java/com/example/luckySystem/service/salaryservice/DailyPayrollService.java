package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.salary.DailyPayrollDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.AttendanceRepo;
import com.example.luckySystem.repo.salary.BasicSalaryRepo;
import com.example.luckySystem.repo.salary.DailyPayrollRepo;
import com.example.luckySystem.repo.salary.EmployeeDailyPayrollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyPayrollService {

    private final EmployeeDailyPayrollService employeeDailyPayrollService;
    private final DailyPayrollRepo dailyPayrollRepo;
    private final EmployeeRepo employeeRepo;
    private final BasicSalaryRepo basicSalaryRepo;
    private final AttendanceRepo attendanceRepo;
    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;

    @Autowired
    public DailyPayrollService(DailyPayrollRepo dailyPayrollRepo, EmployeeRepo employeeRepo,
                               BasicSalaryRepo basicSalaryRepo, AttendanceRepo attendanceRepo,
                               EmployeeDailyPayrollRepo employeeDailyPayrollRepo,
                               EmployeeDailyPayrollService employeeDailyPayrollService) {
        this.dailyPayrollRepo = dailyPayrollRepo;
        this.employeeRepo = employeeRepo;
        this.basicSalaryRepo = basicSalaryRepo;
        this.attendanceRepo = attendanceRepo;
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.employeeDailyPayrollService = employeeDailyPayrollService;
    }

    public DailyPayRoll createDailyPayroll(String empId, String dateStr) {

        // Get employee details
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            String jobRole = employee.getJob_role();
            String departmentName = employee.getDepartment().getDepartment_name();
            String sectionName = employee.getSec_id().getSection_name();

            BasicSalary basicSalary = basicSalaryRepo.findByCustomQuery(jobRole, departmentName, sectionName);
            EmployeeAttendance employeeAttendance = attendanceRepo.findByEmpIdAndDateNative(empId, dateStr);

            if (basicSalary != null && employeeAttendance != null) {
                // Calculate working hours
                double workingHours = (employeeAttendance.getOut_time().getTime() - employeeAttendance.getIn_time().getTime()) / (1000 * 60 * 60);


                // Calculate ShiftAmount
                double shiftAmount = (basicSalary.getBasic_amount() + basicSalary.getBr_1() + basicSalary.getBr_2() + basicSalary.getSubsistant()) / workingHours;

                // Create and set values to DailyPayrollDto
                DailyPayrollDto dailyPayrollDto = new DailyPayrollDto();
                dailyPayrollDto.setDepartment_name(departmentName);
                dailyPayrollDto.setJob_role(jobRole);
                dailyPayrollDto.setSection_name(sectionName);
                dailyPayrollDto.setSalary_type(basicSalary.getSalary_type());
                dailyPayrollDto.setWorking_hours((int) workingHours);
                dailyPayrollDto.setAmount_per_aditonal_hour(basicSalary.getOt_amount());
                dailyPayrollDto.setShift_amount(shiftAmount);

                // Convert DailyPayrollDto to DailyPayRoll entity and save
                DailyPayRoll dailyPayRoll = new DailyPayRoll();
                dailyPayRoll.setSalary_type(dailyPayrollDto.getSalary_type());
                dailyPayRoll.setJob_role(dailyPayrollDto.getJob_role());
                dailyPayRoll.setDepartment_name(dailyPayrollDto.getDepartment_name());
                dailyPayRoll.setSection_name(dailyPayrollDto.getSection_name());
                dailyPayRoll.setWorking_hours(dailyPayrollDto.getWorking_hours());
                dailyPayRoll.setAmount_per_aditonal_hour(dailyPayrollDto.getAmount_per_aditonal_hour());
                dailyPayRoll.setShift_amount(dailyPayrollDto.getShift_amount());

                DailyPayRoll savedDailyPayRoll = dailyPayrollRepo.save(dailyPayRoll);
                System.out.println(savedDailyPayRoll);

                // Pass the savedDailyPayRoll object to createEmployeeDailyPayroll method
                employeeDailyPayrollService.createEmployeeDailyPayroll(savedDailyPayRoll,employeeAttendance.getEmp_id());

                return savedDailyPayRoll;

            }
        } else {
            // Handle case where employee is not found with empId
            // For example, throw an exception or log an error
        }
        return null;
    }
}

