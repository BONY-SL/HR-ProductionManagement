package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.MonthlySalaryDto;
import com.example.luckySystem.dto.sectionanddepartment.SectionDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlySalaryService {

    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;
    private final AllowanceRepo allowanceRepo;
    private final AdvanceRepo advanceRepo;
    private final DeductionRepo deductionRepo;
    private final LeaveRepo leaveRepo;
    private final LoanRepo loanRepo;
    private final MedicalRepo medicalRepo;
    private final BasicSalaryRepo basicSalaryRepo;
    private final MonthlySalaryRepo monthlySalaryRepo;
    private final EmployeeRepo employeeRepo;


    private String sectionName;
    private String departmentName;
    private String jobRole;

    private String salarytype;
    private double totalAmount;

    private int acount;
    private double allowanceamount;
    private double deductionamount;

    private double advancesalary;

    private double cloanamount;

    private double cadvance;

    private double shiftamount;

    private double grossbasicsalary;

    private double netsalary;

    @Autowired
    public MonthlySalaryService(EmployeeDailyPayrollRepo employeeDailyPayrollRepo, AllowanceRepo allowanceRepo, AdvanceRepo advanceRepo, DeductionRepo deductionRepo, LeaveRepo leaveRepo, LoanRepo loanRepo, MedicalRepo medicalRepo, BasicSalaryRepo basicSalaryRepo, MonthlySalaryRepo monthlySalaryRepo, EmployeeRepo employeeRepo) {
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.allowanceRepo = allowanceRepo;
        this.advanceRepo = advanceRepo;
        this.deductionRepo = deductionRepo;
        this.leaveRepo = leaveRepo;
        this.loanRepo = loanRepo;
        this.medicalRepo = medicalRepo;
        this.basicSalaryRepo = basicSalaryRepo;
        this.monthlySalaryRepo = monthlySalaryRepo;
        this.employeeRepo = employeeRepo;

    }

    public void createMonthlySalary(String empId, String date, double bonus) {
        // Extract year and month from the date string (format: YYYY-MM)
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);

        // Fetch the Employee object by empId
        Employee employee = employeeRepo.findByEmployeeId(empId);
        if (employee == null) {
            System.out.println("Employee not found for ID: " + empId);
            return;
        }

        // Fetch data from repository
        List<EmployeeDailyPayRoll> employeeDailyPayRolls = employeeDailyPayrollRepo.findAllByByMonthYearAndEmpId(empId, month, year);

        if (employeeDailyPayRolls.isEmpty()) {
            System.out.println("No payroll records found for employee ID: " + empId + " in " + year + "-" + String.format("%02d", month));
            return;
        }

        // Calculate total shift amount and other details
        double totalShiftAmount = employeeDailyPayRolls.stream().mapToDouble(EmployeeDailyPayRoll::getShift_amount).sum();
        double totalAmount = employeeDailyPayRolls.stream().mapToDouble(EmployeeDailyPayRoll::getTotal_amount).sum();
        String jobRole = employeeDailyPayRolls.get(0).getDaily_pay_id().getJob_role();
        String departmentName = employeeDailyPayRolls.get(0).getDaily_pay_id().getDepartment_name();
        String sectionName = employeeDailyPayRolls.get(0).getDaily_pay_id().getSection_name();

        acount = employeeDailyPayrollRepo.countByMonthYearAndEmpId(empId, month, year);
        List<EmployeeLeave> employeeLeaveList = leaveRepo.findAllleave(empId, month, year);
        BasicSalary basicSalary = basicSalaryRepo.findByCustomQuery(jobRole, departmentName, sectionName);
        BasicSalary basicSalary1 = basicSalaryRepo.findByCustomQuery(jobRole, departmentName, sectionName);

        double netbasicsalay = basicSalary1.getBasic_amount() + basicSalary1.getBr_1() + basicSalary1.getBr_2() + basicSalary1.getSubsistant();
        double epf = (netbasicsalay * 8) / 100; // deduction
        double cepf = (netbasicsalay * 12) / 100; // not deduction
        double etf = (netbasicsalay * 3) / 100; // not deduction

        // Check leave days count and total leaves equal to initials days
        long totalLeaveDays = employeeLeaveList.stream()
                .filter(leaveRecord -> "approve".equalsIgnoreCase(leaveRecord.getStatus()))
                .mapToLong(leaveRecord -> ChronoUnit.DAYS.between(leaveRecord.getStart_time().toLocalDate(), leaveRecord.getEnd_time().toLocalDate()) + 1)
                .sum();

        System.out.println("Total leave days for approved leaves: " + totalLeaveDays);

        if (acount + totalLeaveDays == basicSalary.getInitial_days()) {
            shiftamount = ((basicSalary.getBasic_amount() + basicSalary.getBr_1() + basicSalary.getBr_2() + basicSalary.getSubsistant()) / 8) * basicSalary.getInitial_days();
        }

        // Fetch related allowance
        List<Allowances> allowancesList = allowanceRepo.findByCustomQuery(jobRole, departmentName, sectionName);

        if (allowancesList != null && !allowancesList.isEmpty()) {
            allowanceamount = allowancesList.stream()
                    .filter(allowance -> !(allowance.getAllowances_type().equalsIgnoreCase("Attendance Allowance") && acount == 28))
                    .mapToDouble(Allowances::getAllowances_amount)
                    .sum();

            System.out.println("Total Allowances amount: " + allowanceamount);
        } else {
            System.out.println("No allowances found for the given criteria.");
        }

        // Fetch Deduction
        List<Deduction> deductionList = deductionRepo.findByCustomQuery(jobRole, departmentName, sectionName);

        if (deductionList != null && !deductionList.isEmpty()) {
            deductionamount = deductionList.stream()
                    .mapToDouble(Deduction::getDeduction_amount)
                    .sum();

            System.out.println("Total Deduction amount: " + deductionamount);
        } else {
            System.out.println("No Deduction found for the given criteria.");
        }

        // Fetch loan
        EmployeeLoan loan = loanRepo.getloanbyid(empId);

        if (loan != null) {
            if (loan.getLoan_amount() > 0) {
                System.out.println("Loan amount: " + loan.getLoan_amount());
                cloanamount = loan.getLoan_amount() - loan.getInterest_amount();
                System.out.println(cloanamount);
                loanRepo.updateLoanAmount(cloanamount, loan.getLoan_id());
            } else {
                System.out.println("Loan payment complete");
            }
        } else {
            System.out.println("No loan found for the given criteria.");
        }

        // Fetch advance
        EmployeeAdvanceSalary advance = advanceRepo.getAdvancebyid(empId);

        if (advance != null) {
            if ("approve".equalsIgnoreCase(advance.getStatus()) && advance.getAmount() > 0) {
                System.out.println("Advance amount: " + advance.getAmount());
                advancesalary = advance.getAmount();
                advanceRepo.updateAdvanceAmount(0.0, advance.getAdvance_salary_id());
            }
        } else {
            System.out.println("No advance found for the given criteria.");
        }

        // Fetch medical
        EmployeeMedical medical = medicalRepo.getMedicalbyid(empId);

        if (medical != null) {
            System.out.println("Medical amount: " + medical.getMedical_status());
        } else {
            System.out.println("No medical found for the given criteria.");
        }

        // Net basic salary
        grossbasicsalary = netbasicsalay + totalShiftAmount + allowanceamount;

        // Net salary
        netsalary = (grossbasicsalary - (deductionamount + (loan != null ? loan.getInterest_amount() : 0.0) + advancesalary));

        // Print details
        System.out.println("Emp ID:" + empId);
        System.out.println("Salary type:" + basicSalary1.getSalary_type());
        System.out.println("Job Role: " + jobRole);
        System.out.println(date);
        System.out.println("Bonus:" + bonus);
        System.out.println("Allowance:" + allowanceamount);
        System.out.println("Deduction:" + deductionamount);
        System.out.println("EPF 8% : " + epf);
        System.out.println("ETF 3% : " + etf);
        System.out.println("Gross basic salary: " + grossbasicsalary);
        System.out.println("Net salary:" + netsalary);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Section Name: " + sectionName);
        System.out.println("Shift Amount: " + totalShiftAmount);
        System.out.println("Loan: " + (loan != null ? loan.getLoan_amount() : "N/A"));
        System.out.println("Advance: " + (advance != null ? advance.getAmount() : "N/A"));
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Net basic salary: " + netbasicsalay);
        System.out.println("EPF 12% : " + cepf);
        System.out.println("Total leave days:" + totalLeaveDays);

        // Create EmployeeMonthlySalary object and set properties
        EmployeeMonthlySalary employeeMonthlySalary = new EmployeeMonthlySalary();
        employeeMonthlySalary.setEmp_id(employee);
        employeeMonthlySalary.setSalary_type(basicSalary1.getSalary_type());
        employeeMonthlySalary.setJob_role(jobRole);
        employeeMonthlySalary.setDate(date);
        employeeMonthlySalary.setBonus_amount(bonus);
        employeeMonthlySalary.setAllowancess_amount(allowanceamount);
        employeeMonthlySalary.setDeduction_amount(deductionamount);
        employeeMonthlySalary.setEpf(epf);
        employeeMonthlySalary.setEtf(etf);
        employeeMonthlySalary.setLoan_deduction(loan != null ? loan.getLoan_amount() : 0.0);
        employeeMonthlySalary.setAdvance_salary(advancesalary);
        employeeMonthlySalary.setGross_basic_salary(grossbasicsalary);
        employeeMonthlySalary.setNet_salary(netsalary);

        // Save EmployeeMonthlySalary object
        monthlySalaryRepo.save(employeeMonthlySalary);
    }



    public List<MonthlySalaryDto> MonthlySalaryDetails() {
        List<EmployeeMonthlySalary> salaryList = monthlySalaryRepo.findAll();
        return salaryList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private MonthlySalaryDto convertToDTO(EmployeeMonthlySalary employeeMonthlySalary) {
        MonthlySalaryDto monthlySalaryDto = new MonthlySalaryDto();
        monthlySalaryDto.setSalary_id(employeeMonthlySalary.getSalary_id());
        monthlySalaryDto.setEmp_id(employeeMonthlySalary.getEmp_id().getEmployee_id()); // Assuming getEmployee_id() returns the ID of the employee
        monthlySalaryDto.setSalary_type(employeeMonthlySalary.getSalary_type());
        monthlySalaryDto.setJob_role(employeeMonthlySalary.getJob_role());
        monthlySalaryDto.setDate(employeeMonthlySalary.getDate());
        monthlySalaryDto.setBonus_amount(employeeMonthlySalary.getBonus_amount());
        monthlySalaryDto.setAllowancess_amount(employeeMonthlySalary.getAllowancess_amount());
        monthlySalaryDto.setDeduction_amount(employeeMonthlySalary.getDeduction_amount());
        monthlySalaryDto.setEpf(employeeMonthlySalary.getEpf());
        monthlySalaryDto.setEtf(employeeMonthlySalary.getEtf());
        monthlySalaryDto.setLoan_deduction(employeeMonthlySalary.getLoan_deduction());
        monthlySalaryDto.setAdvance_salary(employeeMonthlySalary.getAdvance_salary());
        monthlySalaryDto.setGross_basic_salary(employeeMonthlySalary.getGross_basic_salary());
        monthlySalaryDto.setNet_salary(employeeMonthlySalary.getNet_salary());
        return monthlySalaryDto;
    }
}
