package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.entity.EmployeeMedical;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.service.salaryservice.MedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")

public class MedicalController {

    @Autowired
    public MedicalService medicalService;



    @GetMapping("/getmedical")
    public List<MedicalDto> getMedicalDetails(){
        System.out.println("Received request to save allowance data.");
        return medicalService.getMedicalDetails();
    }


    @PostMapping("/addMedical")
    public ResponseEntity<?> addMedical(@RequestParam("emp_id") String empId,
                                        @RequestParam("submit_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date submitDate,
                                        @RequestParam("medical_status") String medicalStatus,
                                        @RequestParam(value = "medical_report", required = false) MultipartFile medicalReport) {

        MedicalDto medicalDto = new MedicalDto();
        medicalDto.setEmp_id(empId);
        medicalDto.setMedical_status(medicalStatus);
        medicalDto.setSubmit_date(submitDate);

        System.out.println(medicalDto);
        System.out.println(medicalReport);
        try {
            EmployeeMedical medical = medicalService.addMedicalDetails(medicalDto, medicalReport);
            System.out.println(medical);
            return new ResponseEntity<>("Medical data added successfully", HttpStatus.CREATED);
        } catch (AppException e) {
            return ResponseEntity.status(e.getStatus()).body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "An unexpected error occurred"));
        }
    }


}
