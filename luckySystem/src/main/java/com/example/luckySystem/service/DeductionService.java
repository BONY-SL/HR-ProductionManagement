package com.example.luckySystem.service;

import com.example.luckySystem.dto.DeductionDto;
import com.example.luckySystem.entity.Deduction;
import com.example.luckySystem.repo.DeductionRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DeductionService {

    @Autowired
    private DeductionRepo deductionRepo;

    @Autowired
    private ModelMapper modelMapper;




    public List<DeductionDto> getDeductionDetails() {
        List<Deduction> deductionList = deductionRepo.findAll();
        return modelMapper.map(deductionList, new TypeToken<List<DeductionDto>>() {}.getType());
    }

    public DeductionDto updateDeductionDetails(DeductionDto deductionDto) {
        Deduction deduction = modelMapper.map(deductionDto, Deduction.class);
        deductionRepo.save(deduction);
        return deductionDto;
    }

    public DeductionDto addDeductionDetails(DeductionDto deductionDto) {
        Deduction deduction = modelMapper.map(deductionDto, Deduction.class);
        deductionRepo.save(deduction);
        return deductionDto;
    }

    public boolean deleteDeductionDetails(DeductionDto deductionDto){
        deductionRepo.delete(modelMapper.map(deductionDto,Deduction.class));
        return true;
    }

    public DeductionDto getDeductionByDeductionID(String deductionId){
        Deduction deduction=deductionRepo.getDeductionByDeductionID(deductionId);
        return modelMapper.map(deduction,DeductionDto.class);
    }

}
