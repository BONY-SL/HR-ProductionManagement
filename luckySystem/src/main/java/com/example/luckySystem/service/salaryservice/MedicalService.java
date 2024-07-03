package com.example.luckySystem.service.salaryservice;

import com.example.luckySystem.dto.employee.LeaveHistorySummaryDto;
import com.example.luckySystem.dto.employee.MedicalHistorySummaryDto;
import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeLeave;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalService {

    @Autowired
    private MedicalRepo medicalRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepo emprepo;


    public List<MedicalDto> getMedicalData() {
        return medicalRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public MedicalDto getMedicalById(Long id) {
        return convertToDto(medicalRepo.findById(id).orElseThrow(() -> new RuntimeException("Medical record not found")));
    }

    private MedicalDto convertToDto(EmployeeMedical employeeMedical) {
        return MedicalDto.builder()
                .employee_medical_id(employeeMedical.getEmployee_medical_id())
                .emp_id(employeeMedical.getEmployee().getEmployee_id())
                .medical_status(employeeMedical.getMedical_status())
                .submit_date(employeeMedical.getSubmit_date())
                .medical_report(employeeMedical.getMedical_report())
                .build();
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

        System.out.println("last" + medical);
        medicalRepo.save(medical);
        return medical;
    }

    public MedicalHistorySummaryDto getMedicalHistorySummary(String empId) {
        Employee employee = emprepo.findById(empId).orElse(null);
        if (employee == null) {
            return null;
        }
        long approvedCount = medicalRepo.countByEmpIdAndStatus(employee, "approved");
        long rejectedCount = medicalRepo.countByEmpIdAndStatus(employee, "rejected");

        return new MedicalHistorySummaryDto(approvedCount, rejectedCount, employee.getEmployee_id(), employee.getEmployee_name(), employee.getJob_role());
    }

    public void updateMedicalStatus(Long employee_medical_id, String status) {
        EmployeeMedical medical = medicalRepo.findById(employee_medical_id).orElseThrow(() -> new AppException("Medical request not found", HttpStatus.NOT_FOUND));
        medical.setMedical_status(status);
        medicalRepo.save(medical);
    }
}