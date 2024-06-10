package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.service.salaryservice.MedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    public MedicalDto addMedical(@RequestParam("emp_id") String empId,
                                 @RequestParam("submit_date") String submitDateString,
                                 @RequestParam("medical_status") String medicalStatus,
                                 @RequestParam("medical_report") MultipartFile medicalReport) {

        Date submitDate = null;
        try {
            submitDate = new SimpleDateFormat(" yyyy-MM-DD ").parse(submitDateString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Create a MedicalDto object and set its fields
        MedicalDto medicalDto = new MedicalDto();
        medicalDto.setEmp_id(empId);
        medicalDto.setSubmit_date(submitDate);
        medicalDto.setMedical_status(medicalStatus);

        // Check if a medical report file was provided
        if (medicalReport != null && !medicalReport.isEmpty()) {
            try {
                byte[] reportBytes = medicalReport.getBytes();
                medicalDto.setMedical_report(reportBytes);
            } catch (IOException e) {
                // Handle the exception as needed
            }
        }

        // Call the service method to add medical details, passing the DTO
        return medicalService.addMedicalDetails(medicalDto);
    }


}
