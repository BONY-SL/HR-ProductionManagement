package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeMedical;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.MedicalRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
@Service
@Transactional
public class MedicalService {

    @Autowired
    public  MedicalRepo medicalRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;


    public List<MedicalDto> getMedicalDetails() {
        List<EmployeeMedical> medicalListList = medicalRepo.findAll();
        return modelMapper.map(medicalListList, new TypeToken<List<MedicalDto>>() {}.getType());
    }


    public MedicalDto addMedicalDetails(MedicalDto medicalDto) {
        Employee emp = emprepo.findById(medicalDto.getEmp_id())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        EmployeeMedical medical = modelMapper.map(medicalDto, EmployeeMedical.class);
        medical.setEmp_id(emp);

        if (medicalDto.getMedical_report() != null) {
            byte[] reportBytes = medicalDto.getMedical_report();
            medical.setMedical_report(reportBytes);
        }

        medicalRepo.save(medical);
        return medicalDto;
    }







}
