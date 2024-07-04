package com.example.luckySystem.dto.employee;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePromotionDTO {

    private String employee_id;
    private String job_role;
    private String salary_type;
    private String employee_name;
    private String company_status;
    private String department;
    private String sec_id;
    private String gender;

}
