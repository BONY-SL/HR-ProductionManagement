package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.salary.LoanDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.LoanRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;



    public List<LoanDto> getLoanDetails(){
        List<EmployeeLoan>loanList=loanRepo.findAll();
        return loanList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private LoanDto convertToDTO(EmployeeLoan unit) {
        return new LoanDto(unit.getLoan_id(),unit.getEmp_id().getEmployee_id(),unit.getLoan_amount(),unit.getInterest_amount(),unit.getLoan_details());
    }


    public LoanDto addLoanDetails(LoanDto loanDto) {
        Employee emp = emprepo.findById(String.valueOf(loanDto.getEmp_id())).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        EmployeeLoan loan = new EmployeeLoan();
        loan.setLoan_id(loanDto.getLoan_id());
        loan.setEmp_id(emp);
        loan.setLoan_amount(loanDto.getLoan_amount());
        loan.setInterest_amount(loanDto.getInterest_amount());
        loan.setLoan_details(loanDto.getLoan_details());

        loanRepo.save(loan);
        return loanDto;
    }


    public LoanDto updateLoanDetails(LoanDto loanDto) {
        EmployeeLoan existingLoan = loanRepo.findById(loanDto.getLoan_id())
                .orElseThrow(() -> new AppException("Loan not found", HttpStatus.NOT_FOUND));

        existingLoan.setLoan_amount(loanDto.getLoan_amount());
        existingLoan.setInterest_amount(loanDto.getInterest_amount());
        existingLoan.setLoan_details(loanDto.getLoan_details());

        loanRepo.save(existingLoan);

        return loanDto;
    }





    public boolean deleteLoanDetails(LoanDto loanDto){
        loanRepo.delete(modelMapper.map(loanDto, EmployeeLoan.class));
        return true;
    }

    public LoanDto getLoanDetailsByID(String loanId){
        EmployeeLoan loan = loanRepo.getLoanById(loanId);
        if (loan != null) {
            LoanDto loanDto = new LoanDto();
            loanDto.setLoan_id(loan.getLoan_id());
            loanDto.setEmp_id(loan.getEmp_id().getEmployee_id()); // Assuming emp_id is of type Employee
            loanDto.setLoan_amount(loan.getLoan_amount());
            loanDto.setInterest_amount(loan.getInterest_amount());
            loanDto.setLoan_details(loan.getLoan_details());
            return loanDto;
        } else {
            // Handle case where loan is not found
            return null;
        }
    }

}


