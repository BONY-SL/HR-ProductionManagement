package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.employee.*;
import com.example.luckySystem.dto.salary.AttendanceDto;
import com.example.luckySystem.entity.*;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.salary.AttendanceRepo;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.GatePassRepo;
import com.example.luckySystem.repo.salary.LeaveRepo;
import com.example.luckySystem.repo.salary.MedicalRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private MedicalRepo medicalRepo;

    @Autowired
    private GatePassRepo gatePassRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepo emprepo;

    @Autowired
    private LeaveRepo leaveRepo;


    //get value for start date
    private Date getStartDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //get value for end date
    private Date getEndDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }



    public List<AttendanceDto> getAttendanceDetails(){
        List<EmployeeAttendance>AttendanceList=attendanceRepo.findAll();
        return AttendanceList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AttendanceDto convertToDTO(EmployeeAttendance unit) {

        return new AttendanceDto(unit.getEmployee_attendance_id(),unit.getEmp_id().getEmployee_id(),unit.getAttendance_status(),unit.getIn_time(),unit.getOut_time(),unit.getDate());
    }


    public AttendanceDto addAttendance(AttendanceDto attendanceDto) throws AppException {
        Employee emp=emprepo.findById(String.valueOf(attendanceDto.getEmp_id())).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));


        EmployeeAttendance attendance = modelMapper.map(attendanceDto, EmployeeAttendance.class);
        attendance.setEmp_id(emp);
        attendanceRepo.save(attendance);
        return attendanceDto;
    }

    public int countDistinctEmployeesByDate(String date) {
        return attendanceRepo.countDistinctEmployeesByDate(date);
    }

    public List<AttendanceChartDTO> getAttendanceByMonthAndYear(String  empId, int month, int year) {

        System.out.println(empId);

        Date startDate = getStartDate(year, month);
        Date endDate = getEndDate(year, month);

        List<EmployeeAttendance> attendanceList = attendanceRepo.findByEmp_idAndDateBetweenOrderByDateAsc(empId,startDate,endDate);

        System.out.println(attendanceList);
        return attendanceList.stream().map(att ->
                new AttendanceChartDTO(att.getDate().toString(), att.getAttendance_status())
        ).collect(Collectors.toList());
    }

    public List<MedicalChartDTO> getMedicalByMonthAndYear(String  empId, int month, int year) {

        System.out.println(empId);

        Date startDate = getStartDate(year, month);
        Date endDate = getEndDate(year, month);

        List<EmployeeMedical> medicals = medicalRepo.findByEmployeeAndSubmit_dateBetweenOrderBySubmit_dateAsc(empId,startDate,endDate);

        System.out.println(medicals);
        return medicals.stream().map(matt ->
                new MedicalChartDTO(matt.getSubmit_date().toString(), matt.getMedical_status())
        ).collect(Collectors.toList());
    }

    public List<GatePassChartDTO> getGatePassByMonthAndYear(String  empId, int month, int year) {

        System.out.println(empId);

        Date startDate = getStartDate(year, month);
        Date endDate = getEndDate(year, month);

        List<EmployeeGatePass> gatePasses = gatePassRepo.findByEmp_idAndDateBetweenOrderByDateAsc(empId,startDate,endDate);

        System.out.println(gatePasses);
        return gatePasses.stream().map(gtt ->
                new GatePassChartDTO(gtt.getDate().toString(), gtt.getStatus())
        ).collect(Collectors.toList());
    }

    public List<LeaveChartDTO> getLeavesByMonthAndYear(String  empId, int year) {

        System.out.println(empId);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(year, 0, 1, 0, 0, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        Date startDate = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(year, 12, 1, 0, 0, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        calendar2.add(Calendar.DATE, -1);

        Date endDate = calendar2.getTime();

        List<EmployeeLeave> leaveChartDTOS =leaveRepo.findByEmp_idAAndStart_timeAndEnd_timeOOrderByStart_timeAsc(empId,startDate,endDate);

        System.out.println(leaveChartDTOS.size());

        System.out.println(startDate);
        System.out.println(endDate);

        return leaveChartDTOS.stream().map(ltt ->
                new LeaveChartDTO(ltt.getStart_time().toString(),ltt.getEnd_time().toString(), ltt.getStatus())
        ).collect(Collectors.toList());
    }


    public String updateEmployeePerformance(UpdateEmployeePerformance updateEmployeePerformance){

        Employee employee=emprepo.findById(updateEmployeePerformance.getEmployeeId())
                .orElseThrow( () -> new AppException("Unknown User",HttpStatus.BAD_REQUEST));

        System.out.println(employee);

        employee.setCompany_status(updateEmployeePerformance.getStatus());
        employee.setSalary_type(updateEmployeePerformance.getSalaryType());
        employee.setJob_role(updateEmployeePerformance.getJobRole());
        emprepo.save(employee);

        return "Employee Performance Update Successfully";
    }


    public int totalAbsent() {

        LocalDate localDate = LocalDate.now();
        Date currentDate= java.sql.Date.valueOf(localDate);
        System.out.println(currentDate);
        return attendanceRepo.countAbsentEmployeesByDate(currentDate);
    }

    public int totalWorking() {

        LocalDate localDate = LocalDate.now();
        Date currentDate= java.sql.Date.valueOf(localDate);
        System.out.println(currentDate);
        return attendanceRepo.countPresentEmployeesByDate(currentDate)+attendanceRepo.countLateEmployeesByDate(currentDate);
    }
}
