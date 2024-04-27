package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.salary.LoanDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.LoanRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;


    public List<LoanDto> getLoanDetails() {
        List<EmployeeLoan> loanListList = loanRepo.findAll();
        return modelMapper.map(loanListList, new TypeToken<List<LoanDto>>() {}.getType());
    }

    public LoanDto addLoanDetails(LoanDto loanDto) {
        Employee emp=emprepo.findById(String.valueOf(loanDto.getEmp_id())).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        EmployeeLoan loan = modelMapper.map(loanDto, EmployeeLoan.class);
        loan.setEmp_id(emp);
        loanRepo.save(loan);
        return loanDto;
    }

    public LoanDto updatelone(LoanDto loanDto) {
        EmployeeLoan lone = modelMapper.map(loanDto, EmployeeLoan.class);
        loanRepo.save(lone);
        return loanDto;
    }


    public boolean deleteLoanDetails(LoanDto loanDto){
        loanRepo.delete(modelMapper.map(loanDto, EmployeeLoan.class));
        return true;
    }

    public LoanDto getLoanDetailsByID(String loanId){
        EmployeeLoan loan = loanRepo.getLoanById(loanId);
        return modelMapper.map(loan, LoanDto.class);
    }

}


