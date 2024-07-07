package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.entity.*;
import com.example.luckySystem.repo.salary.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlySalaryService {

    private final EmployeeDailyPayrollRepo employeeDailyPayrollRepo;
    private final AllowanceRepo allowanceRepo;
    private final AdvanceRepo advanceRepo;
    private final DeductionRepo deductionRepo;
    private final LeaveRepo leaveRepo;
    private final LoanRepo loanRepo;
    private final MedicalRepo medicalRepo;


    private String sectionName;
    private String departmentName;
    private String jobRole;

    private String salarytype;
    private double totalAmount;

    public MonthlySalaryService(EmployeeDailyPayrollRepo employeeDailyPayrollRepo, AllowanceRepo allowanceRepo, AdvanceRepo advanceRepo, DeductionRepo deductionRepo, LeaveRepo leaveRepo, LoanRepo loanRepo, MedicalRepo medicalRepo) {
        this.employeeDailyPayrollRepo = employeeDailyPayrollRepo;
        this.allowanceRepo = allowanceRepo;
        this.advanceRepo = advanceRepo;
        this.deductionRepo = deductionRepo;
        this.leaveRepo = leaveRepo;
        this.loanRepo = loanRepo;
        this.medicalRepo = medicalRepo;
    }

    public void createMonthlySalary(String empId, String date, double bonus) {
        // Extract year and month from the date string (format: YYYY-MM)
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);

        // Fetch data from repository
        List<EmployeeDailyPayRoll> employeeDailyPayRolls = employeeDailyPayrollRepo.findAllByByMonthYearAndEmpId(empId, month, year);

        // Print the retrieved list
        if (employeeDailyPayRolls.isEmpty()) {
            System.out.println("No payroll records found for employee ID: " + empId + " in " + year + "-" + String.format("%02d", month));
            return;
        }

        System.out.println("Fetched employee daily payroll records: " + employeeDailyPayRolls.get(0));

        // Calculate the total amount
        totalAmount = employeeDailyPayRolls.stream()
                .mapToDouble(EmployeeDailyPayRoll::getTotal_amount)
                .sum();

        // Print each total amount and details
        for (EmployeeDailyPayRoll payroll : employeeDailyPayRolls) {
            // Assuming these fields are available in the related DailyPayRoll entity
            sectionName = payroll.getDaily_pay_id().getSection_name();
            departmentName = payroll.getDaily_pay_id().getDepartment_name();
            jobRole = payroll.getDaily_pay_id().getJob_role();
            salarytype = payroll.getDaily_pay_id().getSalary_type();

            System.out.println("Total amount for record: " + payroll.getTotal_amount());
            System.out.println("Section Name: " + sectionName);
            System.out.println("Department Name: " + departmentName);
            System.out.println("Job Role: " + jobRole);
            System.out.println("Salary Type: " + salarytype);
        }

        // Print the total amount
        System.out.println("Total amount for employee ID: " + empId + " in " + year + "-" + String.format("%02d", month) + " is: " + totalAmount);
        System.out.println(totalAmount);

        // Fetch allowances and deduction
        Allowances allowances = allowanceRepo.findByCustomQuery(jobRole, departmentName, sectionName);
        Deduction deduction = deductionRepo.findByCustomQuery(jobRole, departmentName, sectionName);
        EmployeeLoan loan = loanRepo.getloanbyid(empId);
        EmployeeAdvanceSalary advance=advanceRepo.getAdvancebyid(empId);
        EmployeeLeave leave=leaveRepo.getleaveybyid(empId);
        EmployeeMedical medical=medicalRepo.getMedicalbyid(empId);

        // Print allowances and deduction amounts if they are not null
        if (allowances != null) {
            System.out.println("Allowances amount: " + allowances.getAllowances_amount());
        } else {
            System.out.println("No allowances found for the given criteria.");
        }

        if (deduction != null) {
            System.out.println("Deduction amount: " + deduction.getDeduction_amount());
        } else {
            System.out.println("No deduction found for the given criteria.");
        }

        if (loan != null) {
            System.out.println("Loan amount: " + loan.getLoan_amount());
        } else {
            System.out.println("No loan found for the given criteria.");
        }

        System.out.println(bonus);

        if (advance != null) {
            System.out.println("advance amount: " + advance.getAmount());
        } else {
            System.out.println("No advance found for the given criteria.");
        }

        if (leave != null) {
            System.out.println("leave amount: " + leave.getStatus());
        } else {
            System.out.println("No leave found for the given criteria.");
        }

        if (medical != null) {
            System.out.println("medical amount: " + medical.getMedical_status());
        } else {
            System.out.println("No medical found for the given criteria.");
        }



    }
}
