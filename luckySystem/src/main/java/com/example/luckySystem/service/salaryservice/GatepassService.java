package com.example.luckySystem.service.salaryservice;
import com.example.luckySystem.dto.employee.GatePassesHistorySummaryDto;
import com.example.luckySystem.dto.salary.GatePassDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.EmployeeGatePass;
import com.example.luckySystem.exceptions.AppException;
import com.example.luckySystem.repo.employee.EmployeeRepo;
import com.example.luckySystem.repo.salary.GatePassRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GatepassService {

    @Autowired
    public GatePassRepo gatePassRepo;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public EmployeeRepo emprepo;

    public GatePassDto addGatepass(GatePassDto gatePassDto) {

        Employee emp=emprepo.findById(gatePassDto.getEmp_id()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        EmployeeGatePass gatePass = modelMapper.map(gatePassDto, EmployeeGatePass.class);
       gatePass.setEmp_id(emp);
        gatePassRepo.save(gatePass);
        return gatePassDto;
    }




    public List<GatePassDto> getGatepassDetails(){
        List<EmployeeGatePass>gatepassList=gatePassRepo.findAll();
        return gatepassList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private GatePassDto convertToDTO(EmployeeGatePass unit) {

        return new GatePassDto(unit.getEmployee_gate_pass_id(),unit.getEmp_id().getEmployee_id(),unit.getIn_time(),unit.getOut_time(),unit.getDate(),unit.getReson(),unit.getStatus());
    }

    public void updateGatePassStatus(Long employee_leave_id, String status) {
        EmployeeGatePass gatepass = gatePassRepo.findById(employee_leave_id).orElseThrow(() -> new AppException("GatePass request not found", HttpStatus.NOT_FOUND));
        gatepass.setStatus(status);
        gatePassRepo.save(gatepass);
    }

    public GatePassesHistorySummaryDto gatePassesHistorySummary(String empId) {
        Employee employee = emprepo.findById(empId).orElse(null);
        if (employee == null) {
            return null;
        }
        long approvedCount = gatePassRepo.countByEmpIdAndStatus(employee, "approved");
        long rejectedCount = gatePassRepo.countByEmpIdAndStatus(employee, "rejected");

        return new GatePassesHistorySummaryDto(approvedCount, rejectedCount, employee.getEmployee_id(),employee.getEmployee_name(),employee.getJob_role());
    }


}
