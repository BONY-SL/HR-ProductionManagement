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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@Transactional
public class MedicalService {

    @Autowired
    private  MedicalRepo medicalRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepo emprepo;


    public List<MedicalDto> getMedicalDetails() {
        List<EmployeeMedical> medicalListList = medicalRepo.findAll();
        return modelMapper.map(medicalListList, new TypeToken<List<MedicalDto>>() {}.getType());
    }


    public EmployeeMedical addMedicalDetails(MedicalDto medicalDto, MultipartFile medicalReport) {

        System.out.println("Service" + medicalDto.getEmp_id());
        Employee emp = emprepo.findById(medicalDto.getEmp_id())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        System.out.println(emp);

        EmployeeMedical medical = new EmployeeMedical();
        medical.setEmployee(emp);
        medical.setSubmit_date(medicalDto.getSubmit_date());
        medical.setMedical_status(medicalDto.getMedical_status());  // Corrected this line

        if (medicalReport != null && !medicalReport.isEmpty()) {
            try {
                medical.setMedical_report(medicalReport.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("last"+medical);
        medicalRepo.save(medical);
        return medical;
    }


}
