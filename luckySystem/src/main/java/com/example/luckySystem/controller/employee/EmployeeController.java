package com.example.luckySystem.controller.employee;
import com.example.luckySystem.dto.employee.*;
import com.example.luckySystem.dto.salary.LeaveDto;
import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hrandproduction")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeeDetails() {
        System.out.println("Received request to get all employees.");
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployee();
        return ResponseEntity.ok().body(employeeDTOS);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(
            @RequestParam("employeeid") String employeeid,
            @RequestParam("job_role") String jobRole,
            @RequestParam("salary_type") String salaryType,
            @RequestParam("employee_name") String employeeName,
            @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dob,
            @RequestParam("address") String address,
            @RequestParam("gender") String gender,
            @RequestParam("ma_uma") String maUma,
            @RequestParam("contact") String contact,
            @RequestParam("company_status") String companyStatus,
            @RequestParam("dep_id") String depId,
            @RequestParam("sec_id") String secId,
            @RequestPart(value = "cv", required = false) MultipartFile cv) throws IOException {

        System.out.println(employeeid);
        EmployeeDTO employeeDto = new EmployeeDTO(employeeid, jobRole, salaryType, employeeName, dob, address, gender, maUma, contact, companyStatus,null, depId, secId);
        Employee employee = employeeService.addEmployee(employeeDto, cv);
        return ResponseEntity.ok(employee);
    }


    @GetMapping("/employeeCountByDepartment")
    public List<Object[]> getEmployeeCountByDepartment() {
        return employeeService.countActiveEmployeesByDepartment();
    }

    @GetMapping("/totalCount")
    public int getTotalEmployeeCount() {
        return employeeService.getTotalEmployeesCount();
    }


    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        System.out.println(updateEmployeeDTO);
        System.out.println("Received request to add an employee with ID: " + updateEmployeeDTO.getEmployeeid());
        Employee employee=employeeService.updateEmployeeDetails(updateEmployeeDTO);
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping("/deleteEmployee")
    public boolean deleteEmployee(@RequestBody EmployeeDTO employeeDto) {
        return employeeService.deleteEmployee(employeeDto);
    }

    @GetMapping("/getEmployeeByID/{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable String employeeID){
        return employeeService.getEmployeeByEmployeeID(employeeID);
    }

    @GetMapping("/getMedicalData")
    public ResponseEntity<List<MedicalDto>> getMedicalData() {
        List<MedicalDto> agent = employeeService.getMedicalData();
        return ResponseEntity.ok().body(agent);
    }
    @GetMapping("/getLeaveData")
    public ResponseEntity<List<LeaveDto>> getLeaveData() {
        List<LeaveDto> agent=employeeService.getLeaveData();
        return ResponseEntity.ok().body(agent);
    }


    @GetMapping("/todayBirthdays")
    public List<EmployeeBirthdayDTO> getEmployeesWithBirthdaysToday() {
        return employeeService.getEmployeesWithBirthdaysToday();
    }

    @GetMapping("/upcomingBirthdays")
    public List<UpcommingBirthdayDTO> getUpcomingBirthdays() {
        return employeeService.getUpcomingBirthdays();
    }

    @GetMapping("/employeeCountsByGender")
    public List<DepartmentEmployeeGenderCountDto> getEmployeeCountsByGender() {
        return employeeService.getDepartmentEmployeeGenderCounts();
    }

    @GetMapping("/getEmployeeToPromotionUpdate")
    public ResponseEntity<List<UpdatePromotionDTO>> getEmployeeToPromotionUpdate() {
        List<UpdatePromotionDTO> employee = employeeService.getEmployeeToPromotionUpdate();
        return ResponseEntity.ok().body(employee);
    }

    private final Tika tika = new Tika();

    @GetMapping("/downloadCvReport/{empid}")
    public ResponseEntity<ByteArrayResource> downloadCvReport(@PathVariable String empid) {

        System.out.println(empid);
        EmployeeCvDTO employeeCvDTO = employeeService.getEmployeeCvById(empid);
        byte[] cvReport = employeeCvDTO.getCv();

        String mimeType = tika.detect(cvReport);

        ByteArrayResource resource = new ByteArrayResource(cvReport);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"medical_report_" + empid + "." + mimeType + "\"")
                .body(resource);
    }

}
