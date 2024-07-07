package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.salary.BasicSalaryDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.repo.salary.BasicSalaryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasicSalaryService {

    @Autowired
    private BasicSalaryRepo basicSalaryRepo;

    @Autowired
    private ModelMapper modelMapper;



    public BasicSalaryDto addSalaryDetails(BasicSalaryDto basicSalaryDto) {
        BasicSalary basicSalary = modelMapper.map(basicSalaryDto, BasicSalary.class);
        basicSalaryRepo.save(basicSalary);
        return basicSalaryDto;
    }


    public BasicSalaryDto updateSalaryDetails(BasicSalaryDto basicSalaryDto) {
        BasicSalary basicSalary = modelMapper.map(basicSalaryDto, BasicSalary.class);
        basicSalaryRepo.save(basicSalary);
        return basicSalaryDto;
    }


    public boolean deleteSalaryDetails(BasicSalaryDto basicSalaryDto){
        basicSalaryRepo.delete(modelMapper.map(basicSalaryDto,BasicSalary.class));
        return true;
    }

    public List<BasicSalaryDto> getSalaryDetails() {
        List<BasicSalary> salaryList = basicSalaryRepo.findAll();
        return modelMapper.map(salaryList, new TypeToken<List<BasicSalaryDto>>() {}.getType());
    }


    public BasicSalaryDto getSalaryDetailsByID(String salaryId){
        BasicSalary salary = basicSalaryRepo.getBasicSalariesByID(salaryId);
        return modelMapper.map(salary, BasicSalaryDto.class);
    }


}
