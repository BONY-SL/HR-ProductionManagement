package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.DailyPayrollDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class DailyPayrollService {

    private final EmployeeDailyPayrollService employeeDailyPayrollService;
    private final DailyPayrollRepo dailyPayrollRepo;
    private final EmployeeRepo employeeRepo;
    private final BasicSalaryRepo basicSalaryRepo;
    private final AttendanceRepo attendanceRepo;
    private final GatePassRepo gatePassRepo;
    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;

    @Autowired
    public DailyPayrollService(DailyPayrollRepo dailyPayrollRepo, EmployeeRepo employeeRepo,
                               BasicSalaryRepo basicSalaryRepo, AttendanceRepo attendanceRepo,
                               EmployeeDailyPayrollRepo employeeDailyPayrollRepo,
                               EmployeeDailyPayrollService employeeDailyPayrollService, GatePassRepo gatePassRepo) {
        this.dailyPayrollRepo = dailyPayrollRepo;
        this.employeeRepo = employeeRepo;
        this.basicSalaryRepo = basicSalaryRepo;
        this.attendanceRepo = attendanceRepo;
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.employeeDailyPayrollService = employeeDailyPayrollService;
        this.gatePassRepo = gatePassRepo;
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
            EmployeeGatePass employeeGatePass = gatePassRepo.findGatepassamount(empId, dateStr);

            if (basicSalary != null && employeeAttendance != null) {

                if ("present".equals(employeeAttendance.getAttendance_status())) {

                    // Calculate working hours
                    double workingHours = (employeeAttendance.getOut_time().getTime() - employeeAttendance.getIn_time().getTime()) / (1000.0 * 60 * 60);
                    workingHours = Math.round(workingHours * 10.0) / 10.0;
                    System.out.println("Working hours" + workingHours);


                    // Reduce gatepass hours if the status is "approve"
                    if (employeeGatePass != null && "approve".equals(employeeGatePass.getStatus())) {
                        System.out.println("Gatepass approved");
                        double gatepassHours = (employeeGatePass.getOut_time().getTime() - employeeGatePass.getIn_time().getTime()) / (1000.0 * 60 * 60);
                        gatepassHours=Math.round(gatepassHours * 10.0)/10.0;
                        System.out.println("Gatepass hours" + gatepassHours);
                        workingHours = workingHours - gatepassHours;

                    }

                    System.out.println("Working hours" + workingHours);

                    //basic salary
                    double basicsalary=(basicSalary.getBasic_amount() + basicSalary.getBr_1() + basicSalary.getBr_2() + basicSalary.getSubsistant());
                    System.out.println("basic salary:"+basicsalary);

                    // Calculate ShiftAmount
                    double shiftAmount = basicsalary / workingHours;
                    BigDecimal shiftAmountRounded = new BigDecimal(shiftAmount).setScale(2, RoundingMode.HALF_UP);
                    double roundedShiftAmount = shiftAmountRounded.doubleValue();

                    System.out.println("shiftamount:"+roundedShiftAmount);

                    // Create and set values to DailyPayrollDto
                    DailyPayrollDto dailyPayrollDto = new DailyPayrollDto();
                    dailyPayrollDto.setDepartment_name(departmentName);
                    dailyPayrollDto.setJob_role(jobRole);
                    dailyPayrollDto.setSection_name(sectionName);
                    dailyPayrollDto.setSalary_type(basicSalary.getSalary_type());
                    dailyPayrollDto.setWorking_hours(workingHours);
                    dailyPayrollDto.setAmount_per_aditonal_hour(basicSalary.getOt_amount());
                    dailyPayrollDto.setShift_amount(roundedShiftAmount);

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
                    //System.out.println(savedDailyPayRoll);

                    // Pass the savedDailyPayRoll object to createEmployeeDailyPayroll method
                    employeeDailyPayrollService.createEmployeeDailyPayroll(savedDailyPayRoll, employee, dateStr);

                    return savedDailyPayRoll;
                }

            }
        } else {
            // Handle case where employee is not found with empId
            // For example, throw an exception or log an error
            System.out.println("Employee with ID " + empId + " not found.");
        }
        return null;
    }
}
