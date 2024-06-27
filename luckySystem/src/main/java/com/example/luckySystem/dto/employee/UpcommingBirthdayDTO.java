package com.example.luckySystem.dto.employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpcommingBirthdayDTO {

    private String employee_id;
    private String job_role;
    private String employee_name;
    private Date dob;
    private String address;
    private String gender;
    private String ma_uma;
    private String contact;
    private String dep_id;
    private String sec_id;
}
