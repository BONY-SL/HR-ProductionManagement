package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name = "employeeleave")
public class EmployeeLeave {

    @Id
    @Column(name="employee_leave_id",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long EmployeeLeaveID;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employee_id")
    private Employee employee;

    //Absent,Present,Late
    @Column(name="leave_type",length = 15)
    private String LeaveType;

    @Column(name="reson",length = 15)
    private String  Reson;

    @Column(name="status",length = 15)
    private String  Status;

    @Column(name="start_time",length = 15)
    private String StartTime;

    @Column(name="end_time",length = 15)
    private String EndTime;
}
