package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.MonthlySalaryDto;
import com.example.luckySystem.dto.salary.MonthlySalaryReportDto;
import com.example.luckySystem.dto.sectionanddepartment.SectionDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
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



    private int acount;
    private double allowanceamount;
    private double deductionamount;
    private double grosssalary;
    private double netsalary;
    private double cepf;
    private double loanDeductionAmount;
    private double advancedeductionamount;

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

        String formattedDate = String.format("%04d-%02d-00", year, month);


        // Fetch data from repository
        List<EmployeeDailyPayRoll> employeeDailyPayRolls = employeeDailyPayrollRepo.findAllByByMonthYearAndEmpId(empId, month, year);

        if (employeeDailyPayRolls.isEmpty()) {
            System.out.println("No payroll records found for employee ID: " + empId + " in " + year + "-" + String.format("%02d", month));
            return;
        }

        // get jobrole,depname,section,othours,latehours,otamount
        String jobRole = employeeDailyPayRolls.get(0).getDaily_pay_id().getJob_role();
        String departmentName = employeeDailyPayRolls.get(0).getDaily_pay_id().getDepartment_name();
        String sectionName = employeeDailyPayRolls.get(0).getDaily_pay_id().getSection_name();
        double otAmount=employeeDailyPayRolls.stream().mapToDouble(EmployeeDailyPayRoll::getOt_amount).sum();
        double lateAmount =employeeDailyPayRolls.stream().mapToDouble(EmployeeDailyPayRoll::getLate_amount).sum();
        double gatepassAmount=employeeDailyPayRolls.stream().mapToDouble(EmployeeDailyPayRoll::getGatepass_amount).sum();


        System.out.println(jobRole);
        System.out.println(departmentName);
        System.out.println(sectionName);



        acount = employeeDailyPayrollRepo.countByMonthYearAndEmpId(empId, month, year);
        List<EmployeeLeave> employeeLeaveList = leaveRepo.findAllleave(empId, month, year);
        BasicSalary basicSalary = basicSalaryRepo.findByCustomQuery(jobRole, departmentName, sectionName);



        // Check leave days count and total leaves equal to initials days
        long totalLeaveDays = employeeLeaveList.stream()
                .filter(leaveRecord -> "approved".equalsIgnoreCase(leaveRecord.getStatus()))
                .mapToLong(leaveRecord -> ChronoUnit.DAYS.between(leaveRecord.getStart_time().toLocalDate(), leaveRecord.getEnd_time().toLocalDate()) + 1)
                .sum();

        System.out.println("Total leave days for approved leaves: " + totalLeaveDays);



        //no pay amount
        double nopayAmount=totalLeaveDays*basicSalary.getInitial_nopay_amount();






        double totalbasicsalary=basicSalary.getBasic_amount() + basicSalary.getBr_1() + basicSalary.getBr_2();
        double netbasicsalay =totalbasicsalary-(lateAmount+nopayAmount+gatepassAmount);

        System.out.println("total basic salary" +totalbasicsalary);
        System.out.println("net basic salary" +netbasicsalay);
        System.out.println("attendance count" +acount);
        System.out.println("total gatepass amount" +gatepassAmount);
        System.out.println("tot late amount" +lateAmount);
        System.out.println("tot Ot amount" +otAmount);






        // Fetch related allowance
        List<Allowances> allowancesList = allowanceRepo.findByCustomQuery(jobRole, departmentName, sectionName);

        if (allowancesList != null && !allowancesList.isEmpty()) {
            allowanceamount = allowancesList.stream()
                    .filter(allowance -> !(allowance.getAllowances_type().equalsIgnoreCase("Attendance Allowance") && acount < 0 ))
                    .mapToDouble(Allowances::getAllowances_amount)
                    .sum();

            System.out.println("Total Allowances amount: " + allowanceamount);
        } else {
            System.out.println("No allowances found for the given criteria.");

        }


        //Gross salary
        grosssalary=netbasicsalay+allowanceamount+otAmount+bonus;
        System.out.println("gross salary:" +grosssalary);





        double epf = (netbasicsalay * 8) / 100; // deduction
        cepf = (netbasicsalay * 12) / 100; // not deduction
        double etf = (netbasicsalay * 3) / 100; // not deduction


        System.out.println("epf" +epf);
        System.out.println("cpf" +cepf);
        System.out.println("etf" +etf);




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



// Fetch loans for an employee
        List<EmployeeLoan> loans = loanRepo.getloanbyid(empId);

        if (loans != null && !loans.isEmpty()) {
            // Loop through each loan in the list
            for (EmployeeLoan loan : loans) {
                System.out.println("Loan amount: " + loan.getLoan_amount());
                if(loan.getLoan_amount()>0) {
                    loanDeductionAmount = loan.getInterest_amount();
                    System.out.println("loan deduction amount" + loanDeductionAmount);
                    double newLoanAmount = loan.getLoan_amount() - loanDeductionAmount;
                    System.out.println(newLoanAmount);

                    // Update the loan amount in the database
                    loanRepo.updateLoanAmount(newLoanAmount, loan.getLoan_id());
                }else {
                    loanDeductionAmount=0.0;
                }
            }
        } else {
            System.out.println("No loan found for the given criteria.");
        }



        // Fetch advance
        List<EmployeeAdvanceSalary> advance = advanceRepo.getAdvancebyid(empId);

        if (advance != null) {

            for(EmployeeAdvanceSalary salaryadvance:advance){

                if ("approve".equalsIgnoreCase(salaryadvance.getStatus()) && salaryadvance.getAmount() > 0) {
                    System.out.println("Advance amount: " + salaryadvance.getAmount());
                    advancedeductionamount=salaryadvance.getAmount();
                    advanceRepo.updateAdvanceAmount(0.0, salaryadvance.getAdvance_salary_id());
                }else {
                    advancedeductionamount=0.0;
                }

            }

        } else {
            System.out.println("No advance found for the given criteria.");
        }



        // Net salary
        netsalary = (grosssalary-(epf+deductionamount+loanDeductionAmount+advancedeductionamount));

        System.out.println("netsalary:"+netsalary);


        // Print details
        System.out.println("Emp ID:" + empId);
        System.out.println("Emp Name:"+employee.getEmployee_name());
        System.out.println("Salary type:" + basicSalary.getSalary_type());
        System.out.println("Job Role: " + jobRole);
        System.out.println(formattedDate);
        System.out.println("Bonus:" + bonus);
        System.out.println("Allowance:" + allowanceamount);
        System.out.println("Deduction:" + deductionamount);
        System.out.println("EPF 8% : " + epf);
        System.out.println("ETF 3% : " + etf);
        System.out.println("Gross salary: " + grosssalary);
        System.out.println("Net salary:" + netsalary);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Section Name: " + sectionName);
        //System.out.println("Loan: " + (loan != null ? loan.getLoan_amount() : "N/A"));
       // System.out.println("Advance: " + (advance != null ? advance.getAmount() : "N/A"));



        // Create EmployeeMonthlySalary object and set properties
        EmployeeMonthlySalary employeeMonthlySalary = new EmployeeMonthlySalary();
        employeeMonthlySalary.setEmp_id(employee);
        employeeMonthlySalary.setSalary_type(basicSalary.getSalary_type());
        employeeMonthlySalary.setJob_role(jobRole);
        employeeMonthlySalary.setDate(formattedDate);
        employeeMonthlySalary.setTotal_basicsalary(totalbasicsalary);
        employeeMonthlySalary.setTotal_otamount(otAmount);
        employeeMonthlySalary.setBonus_amount(bonus);
        employeeMonthlySalary.setAllowancess_amount(allowanceamount);
        employeeMonthlySalary.setDeduction_amount(deductionamount);
        employeeMonthlySalary.setEpf(epf);
        employeeMonthlySalary.setEtf(etf);
        employeeMonthlySalary.setCetf(cepf);
        employeeMonthlySalary.setLoan_deduction(loanDeductionAmount);
        employeeMonthlySalary.setAdvance_salary(advancedeductionamount);
        employeeMonthlySalary.setGross_basic_salary(grosssalary);
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





    public MonthlySalaryReportDto getMonthlySalaryReport(String empId, String date) {
        // Trim the date string to remove any leading or trailing whitespace characters
        date = date.trim();

        // Extract year and month from the date string (format: YYYY-MM)
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);

        // Get the month name from the month integer value
        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);


        EmployeeMonthlySalary monthlySalary = monthlySalaryRepo.findAllByByMonthYearAndEmpId(empId, month, year);

        if (monthlySalary == null) {
            System.out.println("No salary report found for the given criteria.");
            return null;
        }

        MonthlySalaryReportDto reportDto = new MonthlySalaryReportDto();
        reportDto.setEmp_id(empId);
        reportDto.setEmp_Name(monthlySalary.getEmp_id().getEmployee_name());
        reportDto.setSalary_type(monthlySalary.getSalary_type());
        reportDto.setJob_role(monthlySalary.getJob_role());
        reportDto.setMonth(monthName);
        reportDto.setYear(year);
        reportDto.setTotal_basicsalary(monthlySalary.getTotal_basicsalary());
        reportDto.setBonus_amount(monthlySalary.getBonus_amount());
        reportDto.setTotal_otamount(monthlySalary.getTotal_otamount());
        reportDto.setAllowancess_amount(monthlySalary.getAllowancess_amount());
        reportDto.setDeduction_amount(monthlySalary.getDeduction_amount());
        reportDto.setEtf(monthlySalary.getEtf());
        reportDto.setEpf(monthlySalary.getEpf());
        reportDto.setCepf(monthlySalary.getCetf());
        reportDto.setLoan_deduction(monthlySalary.getLoan_deduction());
        reportDto.setGross_basic_salary(monthlySalary.getGross_basic_salary());
        reportDto.setNet_salary(monthlySalary.getNet_salary());

        return reportDto;
    }


    public List<MonthlySalaryReportDto> EPFReport(String date) {

        System.out.println(date);

        // Extract year and month from the date string (format: YYYY-MM)
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0].trim());
        int month = Integer.parseInt(dateParts[1].trim());


        System.out.println(year);
        System.out.println(month);

        // Fetch employee monthly salary from repository
        List<EmployeeMonthlySalary> etfreportlist = monthlySalaryRepo.EPFrepoart(month, year);

        System.out.println(etfreportlist);

        // Check if employeeMonthlySalary is null
        if (etfreportlist == null) {

        }
        return etfreportlist.stream().map(this::convertToDTO2).collect(Collectors.toList());

    }

    private MonthlySalaryReportDto convertToDTO2(EmployeeMonthlySalary employeeMonthlySalary) {

        MonthlySalaryReportDto monthlySalaryReportDto = new MonthlySalaryReportDto();

        // Assuming employeeMonthlySalary.getEmp_id() returns an Employee object
        Employee employee = employeeMonthlySalary.getEmp_id();

        monthlySalaryReportDto.setEmp_id(String.valueOf(employee.getEmployee_id()));
        monthlySalaryReportDto.setEmp_Name(employee.getEmployee_name());
        monthlySalaryReportDto.setEpf(employeeMonthlySalary.getEpf());
        monthlySalaryReportDto.setCepf(employeeMonthlySalary.getCetf());
        monthlySalaryReportDto.setEtf(employeeMonthlySalary.getEtf());
        monthlySalaryReportDto.setDate(employeeMonthlySalary.getDate());

        return monthlySalaryReportDto;
    }


}
