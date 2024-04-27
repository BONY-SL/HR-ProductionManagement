package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.AllowanceDto;
import com.example.luckySystem.entity.Allowances;
import com.example.luckySystem.repo.salary.AllowanceRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AllowanceService  {

    @Autowired
    private AllowanceRepo allowanceRepo;

    @Autowired
    private ModelMapper modelMapper;

    public AllowanceDto addAllowanceDetails(AllowanceDto allowanceDto) {
        Allowances allowances = modelMapper.map(allowanceDto, Allowances.class);
        allowanceRepo.save(allowances);
        return allowanceDto;
    }

    public List<AllowanceDto> getAllowanceDetails() {
        List<Allowances> allowanceList = allowanceRepo.findAll();
        return modelMapper.map(allowanceList, new TypeToken<List<AllowanceDto>>() {}.getType());
    }

    public AllowanceDto updateAllowanceDetails(AllowanceDto allowanceDto) {
        Allowances allowances = modelMapper.map(allowanceDto, Allowances.class);
        allowanceRepo.save(allowances);
        return allowanceDto;
    }

    public boolean deleteAllowanceDetails(AllowanceDto allowanceDto){
        allowanceRepo.delete(modelMapper.map(allowanceDto,Allowances.class));
        return true;
    }

    public AllowanceDto getAllowancesById(String allowanceID){
        Allowances allowances=allowanceRepo.getAllowancesById(allowanceID);
        return modelMapper.map(allowances,AllowanceDto.class);
    }

}

