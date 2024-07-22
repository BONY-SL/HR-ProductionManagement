package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.salary.DailyPayrollDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private double latehours;
    private double gatepassHours;
    private double finalShiftAmount;
    private double workingHours;

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

        System.out.println(empId);
        System.out.println(dateStr);

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
                    workingHours = (employeeAttendance.getOut_time().getTime() - employeeAttendance.getIn_time().getTime()) / (1000.0 * 60 * 60);
                    workingHours = Math.round(workingHours * 10.0) / 10.0;
                    System.out.println("Working hours: " + workingHours);

                    // Check late hours
                    if (workingHours < 8) {
                        latehours = 8.0 - workingHours;
                        System.out.println("Late hours: " + latehours);
                    } else {
                        latehours = 0; // No late hours if working hours are 8 or more
                        System.out.println("No late hours: " + latehours);
                    }

                    // Reduce gatepass hours if the status is "approved"
                    if (employeeGatePass != null && "approved".equals(employeeGatePass.getStatus())) {
                        System.out.println("Gatepass approved");
                        gatepassHours = (employeeGatePass.getOut_time().getTime() - employeeGatePass.getIn_time().getTime()) / (1000.0 * 60 * 60);
                        gatepassHours = Math.round(gatepassHours * 10.0) / 10.0;
                        System.out.println("Gatepass hours: " + gatepassHours);
                        workingHours = Math.max(0, workingHours - gatepassHours); // Ensure working hours don't go negative
                    }else {
                        gatepassHours = 0; // No late hours if working hours are 8 or more
                        System.out.println("No gatepass hours: " + latehours);
                    }

                    System.out.println("Adjusted working hours: " + workingHours);

                    // Basic salary
                    double basicsalary = (basicSalary.getBasic_amount() + basicSalary.getBr_1() + basicSalary.getBr_2());
                    System.out.println("Basic salary: " + basicsalary);

                    // Calculate ShiftAmount
                    double shiftAmount = basicsalary / 8.0;

                    BigDecimal shiftAmountRounded = new BigDecimal(shiftAmount).setScale(2, RoundingMode.HALF_UP);
                    finalShiftAmount = shiftAmountRounded.doubleValue();

                    System.out.println("Shift amount: " + finalShiftAmount);

                    // Reduce late amount from shift amount
                    finalShiftAmount = finalShiftAmount - (basicSalary.getLate_hours_amount() * latehours);

                    // Reduce gatepass amount from shift amount
                    finalShiftAmount = finalShiftAmount - (basicSalary.getGet_pass_amount() * gatepassHours);

                    // Create and set values to DailyPayrollDto
                    DailyPayrollDto dailyPayrollDto = new DailyPayrollDto();
                    dailyPayrollDto.setDepartment_name(departmentName);
                    dailyPayrollDto.setJob_role(jobRole);
                    dailyPayrollDto.setSection_name(sectionName);
                    dailyPayrollDto.setSalary_type(basicSalary.getSalary_type());
                    dailyPayrollDto.setWorking_hours(workingHours);
                    dailyPayrollDto.setLate_hours(latehours);
                    dailyPayrollDto.setGatepass_hours(gatepassHours);
                    dailyPayrollDto.setAmount_per_aditonal_hour(basicSalary.getOt_amount());
                    dailyPayrollDto.setShift_amount(finalShiftAmount);

                    // Convert DailyPayrollDto to DailyPayRoll entity and save
                    DailyPayRoll dailyPayRoll = new DailyPayRoll();
                    dailyPayRoll.setSalary_type(dailyPayrollDto.getSalary_type());
                    dailyPayRoll.setJob_role(dailyPayrollDto.getJob_role());
                    dailyPayRoll.setDepartment_name(dailyPayrollDto.getDepartment_name());
                    dailyPayRoll.setSection_name(dailyPayrollDto.getSection_name());
                    dailyPayRoll.setWorking_hours(dailyPayrollDto.getWorking_hours());
                    dailyPayRoll.setLate_hours(dailyPayrollDto.getLate_hours());
                    dailyPayRoll.setGatepass_hours(dailyPayrollDto.getGatepass_hours());
                    dailyPayRoll.setAmount_per_aditonal_hour(dailyPayrollDto.getAmount_per_aditonal_hour());
                    dailyPayRoll.setShift_amount(dailyPayrollDto.getShift_amount());

                    DailyPayRoll savedDailyPayRoll = dailyPayrollRepo.save(dailyPayRoll);

                    System.out.println(savedDailyPayRoll);

                    // Late amount
                    double lateAmount = latehours * basicSalary.getLate_hours_amount();

                    // Gatepass amount
                    double gatepassAmount = gatepassHours * basicSalary.getGet_pass_amount();

                    // Pass the savedDailyPayRoll object to createEmployeeDailyPayroll method
                    employeeDailyPayrollService.createEmployeeDailyPayroll(savedDailyPayRoll, employee, dateStr, lateAmount, gatepassAmount);

                    return savedDailyPayRoll;
                }
            }
        } else {
            // Handle case where employee is not found with empId
            System.out.println("Employee with ID " + empId + " not found.");
        }
        return null;
    }
}
