package com.example.luckySystem.service.employee;
import com.example.luckySystem.dto.employee.*;
import com.example.luckySystem.dto.salary.LeaveDto;
import com.example.luckySystem.dto.salary.MedicalDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.depAndsec.DepartmentRepo;
import com.example.luckySystem.repo.depAndsec.SectionRepo;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.AttendanceRepo;
import com.example.luckySystem.repo.salary.GatePassRepo;
import com.example.luckySystem.repo.salary.LeaveRepo;
import com.example.luckySystem.repo.salary.MedicalRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private SectionRepo sectionRepo;

    @Autowired
    private MedicalRepo medicalRepo;
    @Autowired
    private LeaveRepo leaveRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private GatePassRepo gatePassRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public boolean employeeExists(String employeeID){
        return employeeRepo.findById(employeeID).isPresent();
    }



    @Autowired
    private ModelMapper modelMapper;


    public List<EmployeeDTO> getAllEmployee(){
        List<Employee>employeeList=employeeRepo.findAll();
        return employeeList.stream().map(this::convertEmployeeEntityToDTO).collect(Collectors.toList());
    }

    private EmployeeDTO convertEmployeeEntityToDTO(Employee unit) {

        return new EmployeeDTO(unit.getEmployee_id(),unit.getJob_role(),unit.getSalary_type(),unit.getEmployee_name(),
                unit.getDob(),unit.getAddress(),unit.getGender(),unit.getMa_uma(),unit.getContact(),
                unit.getCompany_status(),unit.getCv(),unit.getDepartment().getDepartment_id(),unit.getSec_id().getSection_id());
    }

    public Employee addEmployee(EmployeeDTO employeeDto, MultipartFile cv) throws IOException {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        if (cv != null && !cv.isEmpty()) {
            employee.setCv(cv.getBytes());
        }
        Department department = departmentRepo.findById(employeeDto.getDep_id())
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.BAD_REQUEST));
        Section section = sectionRepo.findById(employeeDto.getSec_id())
                .orElseThrow(() -> new AppException("Section not found", HttpStatus.BAD_REQUEST));

        employee.setEmployee_id(employeeDto.getEmployeeid());
        employee.setJob_role(employeeDto.getJob_role());
        employee.setSalary_type(employeeDto.getSalary_type());
        employee.setEmployee_name(employeeDto.getEmployee_name());
        employee.setDob(employeeDto.getDob());
        employee.setAddress(employeeDto.getAddress());
        employee.setGender(employeeDto.getGender());
        employee.setMa_uma(employeeDto.getMa_uma());
        employee.setContact(employeeDto.getContact());
        employee.setCompany_status(employeeDto.getCompany_status());
        employee.setDepartment(department);
        employee.setSec_id(section);

        employeeRepo.save(employee);
        return employee;
    }



    public List<Object[]> countActiveEmployeesByDepartment() {
        return employeeRepo.countActiveEmployeesByDepartment();
    }

    public int getTotalEmployeesCount() {
        return employeeRepo.getTotalEmployeesCount();
    }

    public Employee updateEmployeeDetails(UpdateEmployeeDTO updateEmployeeDTO) {
        System.out.println("Service request to update an employee with ID: " + updateEmployeeDTO.getEmployeeid());

        // Find the existing employee entity
        Employee existingEmployee = employeeRepo.findById(updateEmployeeDTO.getEmployeeid())
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));

        // Find the related department and section entities
        Department department = departmentRepo.findById(updateEmployeeDTO.getDep_id())
                .orElseThrow(() -> new AppException("Department not found", HttpStatus.BAD_REQUEST));
        Section section = sectionRepo.findById(updateEmployeeDTO.getSec_id())
                .orElseThrow(() -> new AppException("Section not found", HttpStatus.BAD_REQUEST));

        // Update the fields of the existing employee entity


        existingEmployee.setEmployee_name(updateEmployeeDTO.getEmployee_name());

        existingEmployee.setAddress(updateEmployeeDTO.getAddress());
        existingEmployee.setGender(updateEmployeeDTO.getGender());
        existingEmployee.setMa_uma(updateEmployeeDTO.getMa_uma());
        existingEmployee.setContact(updateEmployeeDTO.getContact());
        existingEmployee.setDepartment(department);
        existingEmployee.setSec_id(section);

        // Save the updated employee entity
        employeeRepo.save(existingEmployee);

        System.out.println(existingEmployee);
        return existingEmployee;
    }


    public boolean deleteEmployee(EmployeeDTO employeeDto){
        employeeRepo.delete(modelMapper.map(employeeDto,Employee.class));
        return true;
    }

/*
    public EmployeeDTO  getEmployeeByEmployeeID(String employeeId){
        Employee employee=employeeRepo.getEmployeeByEmployeeID(employeeId);
        return modelMapper.map(employee,EmployeeDTO.class);
    }

 */


    public EmployeeDTO  getEmployeeByEmployeeID(String employeeId){
        Employee employee=employeeRepo.getEmployeeByEmployeeID(employeeId);
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeid(employee.getEmployee_id());
            employeeDTO.setJob_role(employee.getJob_role());
            employeeDTO.setSalary_type(employee.getSalary_type());
            employeeDTO.setEmployee_name(employee.getEmployee_name());
            employeeDTO.setDob(employee.getDob());
            employeeDTO.setAddress(employee.getAddress());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setMa_uma(employee.getMa_uma());
            employeeDTO.setContact(employee.getContact());
            employeeDTO.setCompany_status(employee.getCompany_status());
            employeeDTO.setCv(employee.getCv());
            employeeDTO.setDep_id(employee.getDepartment().getDepartment_id());
            employeeDTO.setSec_id(employee.getSec_id().getSection_id());


            return employeeDTO;
        } else {
            // Handle case where loan is not found
            return null;
        }
    }

    public List<MedicalDto> getMedicalData() {
        List<EmployeeMedical> medical = medicalRepo.findAll();
        return medical.stream().map(this::convertMedicalEntityToDTO).collect(Collectors.toList());
    }

    private MedicalDto convertMedicalEntityToDTO(EmployeeMedical unit) {

        return new MedicalDto(unit.getEmployee_medical_id(), unit.getEmployee().getEmployee_id(),unit.getMedical_status(),unit.getSubmit_date(),unit.getMedical_report());
    }

    public List<LeaveDto> getLeaveData() {
        List<EmployeeLeave> leave=leaveRepo.findAll();
        return leave.stream().map(this::convertLeaveEntityToDo).collect(Collectors.toList());
    }

    private LeaveDto convertLeaveEntityToDo(EmployeeLeave unit) {
        return new LeaveDto(unit.getEmployee_leave_id(),unit.getEmp_id().getEmployee_id(),unit.getLeave_type(),unit.getReson(),unit.getStatus(),unit.getStart_time(),unit.getEnd_time());

    }


    //get All Employee list Today's Birthday

    public List<EmployeeBirthdayDTO> getEmployeesWithBirthdaysToday() {
        List<Employee> employees = employeeRepo.findEmployeesWithBirthdaysToday();
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private EmployeeBirthdayDTO convertToDTO(Employee employee) {
        EmployeeBirthdayDTO dto = new EmployeeBirthdayDTO();
        dto.setEmployee_id(employee.getEmployee_id());
        dto.setJob_role(employee.getJob_role());
        dto.setEmployee_name(employee.getEmployee_name());
        dto.setGender(employee.getGender());
        dto.setContact(employee.getContact());
        dto.setDep_id(employee.getDepartment().getDepartment_name());
        dto.setSec_id(employee.getSec_id().getSection_name());
        return dto;
    }

    //upcoming birthday lists
    public List<UpcommingBirthdayDTO> getUpcomingBirthdays() {

        List<Employee> employees = employeeRepo.findUpcomingBirthdays();
        return employees.stream().map(this::convertToDTOUpComing).collect(Collectors.toList());
    }

    public List<WorkingAndAbsentEmployeeDetailsDTO> WorkingAndAbsentEmployeeDetails() {

        List<EmployeeAttendance> employees = attendanceRepo.findCurrentMonthAttendance();
        return employees.stream().map(this::convertToDTOUWorkingAndAbsent).collect(Collectors.toList());
    }

    private WorkingAndAbsentEmployeeDetailsDTO convertToDTOUWorkingAndAbsent(EmployeeAttendance employee) {

        return WorkingAndAbsentEmployeeDetailsDTO.builder()
                .emp_id(employee.getEmp_id().getEmployee_id())
                .name(employee.getEmp_id().getEmployee_name())
                .department(employee.getEmp_id().getDepartment().getDepartment_name())
                .section(employee.getEmp_id().getSec_id().getSection_name())
                .jobRole(employee.getEmp_id().getJob_role())
                .attendance_status(employee.getAttendance_status())
                .build();
    }


    //current Gate Pass Employees

    public List<CurrentGatePassViewDTO> currentGatePassEmployeeCount(){

        List<EmployeeGatePass> employeeGatePasses=gatePassRepo.findCurrentCurrentGatePasses();

        return employeeGatePasses.stream().map(this::convertDTOGatePass).collect(Collectors.toList());

    }


    private CurrentGatePassViewDTO convertDTOGatePass(EmployeeGatePass employee) {

        return CurrentGatePassViewDTO.builder()
                .emp_id(employee.getEmp_id().getEmployee_id())
                .name(employee.getEmp_id().getEmployee_name())
                .department(employee.getEmp_id().getDepartment().getDepartment_name())
                .section(employee.getEmp_id().getSec_id().getSection_name())
                .jobRole(employee.getEmp_id().getJob_role())
                .status(employee.getStatus())
                .in_time(employee.getIn_time())
                .out_time(employee.getOut_time())
                .build();
    }

    private UpcommingBirthdayDTO convertToDTOUpComing(Employee employee) {

        // Convert Employee entity to UpcomingBirthdayDTO
        return UpcommingBirthdayDTO.builder()
                .employee_id(employee.getEmployee_id())
                .job_role(employee.getJob_role())
                .employee_name(employee.getEmployee_name())
                .dob(employee.getDob())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .ma_uma(employee.getMa_uma())
                .contact(employee.getContact())
                .dep_id(employee.getDepartment().getDepartment_name())
                .sec_id(employee.getSec_id().getSection_name())
                .build();
    }

    public List<DepartmentEmployeeGenderCountDto> getDepartmentEmployeeGenderCounts() {
        return employeeRepo.findDepartmentEmployeeGenderCounts();
    }

    public List<UpdatePromotionDTO> getEmployeeToPromotionUpdate() {

        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private UpdatePromotionDTO convertEntityToDTO(Employee employee) {
        return new UpdatePromotionDTO(
                employee.getEmployee_id(),
                employee.getJob_role(),
                employee.getSalary_type(),
                employee.getEmployee_name(),
                employee.getCompany_status(),
                employee.getDepartment().getDepartment_name(),
                employee.getSec_id().getSection_name(),
                employee.getGender()
        );
    }

    public EmployeeCvDTO getEmployeeCvById(String empid) {
        return convertToDto(employeeRepo.findById(empid).orElseThrow(() -> new RuntimeException("CV record not found")));
    }

    private EmployeeCvDTO convertToDto(Employee employee) {
        return EmployeeCvDTO.builder()
                .employee_id(employee.getEmployee_id())
                .cv(employee.getCv())
                .build();
    }

}
