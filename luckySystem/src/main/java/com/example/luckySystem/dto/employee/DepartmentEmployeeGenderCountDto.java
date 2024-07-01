package com.example.luckySystem.dto.employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentEmployeeGenderCountDto {

    private String departmentName;
    private Long maleCount;
    private Long femaleCount;
}
