package com.example.luckySystem.service;

import com.example.luckySystem.dto.BasicSalaryDto;
import com.example.luckySystem.dto.LoanDto;
import com.example.luckySystem.dto.MedicalDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLoan;
import com.example.luckySystem.entity.EmployeeMedical;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.EmployeeRepo;
import com.example.luckySystem.repo.MedicalRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@Transactional
public class MedicalService {

    @Autowired
    public MedicalRepo medicalRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;
    public List<MedicalDto> getMedicalDetails() {
        List<EmployeeMedical> medicalListList = medicalRepo.findAll();
        return modelMapper.map(medicalListList, new TypeToken<List<MedicalDto>>() {}.getType());
    }

    public MedicalDto addMedicalDetails(MedicalDto medicalDto) {

        System.out.println(medicalDto.getMedical_report());
        System.out.println(medicalDto.getEmployee_medical_id());
        System.out.println(medicalDto.getSubmit_date());
        System.out.println(medicalDto.getEmp_id());
        // Retrieve employee information based on emp_id from the DTO
        Employee emp = emprepo.findById(medicalDto.getEmp_id())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        // Map the DTO to an entity object
        EmployeeMedical medical = modelMapper.map(medicalDto, EmployeeMedical.class);

        // Set the employee in the medical entity
        medical.setEmp_id(emp);

        // Check if a medical report file was provided
        if (medicalDto.getMedical_report() != null ) {
            // Convert the medical report file to byte array
            byte[] reportBytes = medicalDto.getMedical_report();
            // Set the byte array in the medical entity
            medical.setMedical_report(reportBytes);
        }

        // Save the medical entity in the repository
        medicalRepo.save(medical);

        // Return the DTO
        return medicalDto;
    }



}
