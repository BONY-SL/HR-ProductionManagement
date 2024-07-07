package com.example.luckySystem.service.departmentAndsection;


import com.example.luckySystem.dto.salary.BasicSalaryDto;
import com.example.luckySystem.dto.salary.GatePassDto;
import com.example.luckySystem.dto.sectionanddepartment.DepartmentDto;
import com.example.luckySystem.dto.sectionanddepartment.SectionDto;
import com.example.luckySystem.entity.BasicSalary;
import com.example.luckySystem.entity.Department;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.entity.Section;
import com.example.luckySystem.repo.depAndsec.SectionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    public SectionRepo sectionRepo;

    @Autowired
    private ModelMapper modelMapper;

    public SectionDto addDepartment(SectionDto sectionDto) {
        Section section = new Section();
        section.setSection_id(sectionDto.getSection_id());
        section.setSection_name(sectionDto.getSection_name());
        section.setStart_date(sectionDto.getStart_date());

        Department department = new Department();
        department.setDepartment_id(sectionDto.getDep_id());
        section.setDep_id(department);

        /*
        Employee sectionHead = new Employee();
        sectionHead.setEmployee_id(sectionDto.getSection_of_head());
        section.setSection_of_head(sectionHead);

         */

        sectionRepo.save(section);
        return sectionDto;
    }

    public List<SectionDto> SectionDetails() {
        List<Section> SectionList = sectionRepo.findAll();
        return SectionList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SectionDto convertToDTO(Section section) {
        SectionDto sectionDto = new SectionDto();
        sectionDto.setSection_id(section.getSection_id());
        sectionDto.setSection_name(section.getSection_name());
        sectionDto.setStart_date(section.getStart_date());

        if (section.getDep_id() != null) {
            sectionDto.setDep_id(section.getDep_id().getDepartment_id());
        }

        if (section.getSection_of_head() != null) {
            sectionDto.setSection_of_head(section.getSection_of_head().getEmployee_id());
        }

        return sectionDto;
    }

    public SectionDto updateSectionDetails(SectionDto sectionDto) {
        Section section = modelMapper.map(sectionDto, Section.class);
        sectionRepo.save(section);
        return sectionDto;
    }



}

