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
    public MedicalDto addMedical(@RequestBody MedicalDto medicalDto) {

        System.out.println(medicalDto.getEmp_id());
        System.out.println(medicalDto.getMedical_status());
        System.out.println(medicalDto.getSubmit_date());
        System.out.println(medicalDto.getMedical_report().toString());

        Date submitDate = null;
        try {
            submitDate = new SimpleDateFormat("yyyy-MM-dd").parse(medicalDto.getSubmit_date().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (medicalDto.getMedical_report() != null) {
            try {
                byte[] reportBytes = medicalDto.getMedical_report();
                medicalDto.setMedical_report(reportBytes);
            } catch (Exception e) {
                // Handle the exception as needed
                e.printStackTrace();
            }
        }

        return medicalService.addMedicalDetails(medicalDto);
    }



}
