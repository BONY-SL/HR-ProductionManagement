package com.example.luckySystem.controller.salary;
import com.example.luckySystem.dto.employee.MedicalHistorySummaryDto;
import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.entity.EmployeeMedical;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.service.salaryservice.MedicalService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        return medicalService.getMedicalData();
    }


    private final Tika tika = new Tika();

    @GetMapping("/downloadMedicalReport/{id}")
    public ResponseEntity<ByteArrayResource> downloadMedicalReport(@PathVariable Long id) {

        System.out.println(id);
        MedicalDto medicalDto = medicalService.getMedicalById(id);
        byte[] medicalReport = medicalDto.getMedical_report();

        String mimeType = tika.detect(medicalReport);
        String fileExtension = getFileExtension(mimeType);

        ByteArrayResource resource = new ByteArrayResource(medicalReport);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"medical_report_" + id + "." + fileExtension + "\"")
                .body(resource);
    }

    private String getFileExtension(String mimeType) {
        return switch (mimeType) {
            case "image/jpeg" -> "jpg";
            case "image/png" -> "png";
            case "image/gif" -> "gif";
            // Add more cases as necessary
            default -> "jpg";
        };
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

    @GetMapping("/getMedicalHistorySummary")
    public ResponseEntity<MedicalHistorySummaryDto> getMedicalHistorySummary(@RequestParam String empId) {

        System.out.println(empId);
        MedicalHistorySummaryDto summaryDto = medicalService.getMedicalHistorySummary(empId);
        if (summaryDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summaryDto);
    }

    @PutMapping("/updateMedicalStatus")
    public ResponseEntity<String> updateMedicalStatus(@RequestParam Long employee_medical_id, @RequestParam String status) {
        medicalService.updateMedicalStatus(employee_medical_id, status);
        return ResponseEntity.ok("Medical status updated successfully.");
    }


}
