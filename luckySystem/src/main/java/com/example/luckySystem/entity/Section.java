package com.example.luckySystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity(name="section")
public class Section {

    @Id
    @Column(name="section_id",length = 5,unique = true,nullable = false)
    private String section_id;

    @Column(name="section_name",length = 50,nullable = false)
    private String sectionName;

    @Column(name="start_date",length = 20,nullable = false)
    private String startDate;

    @ManyToOne
    @JoinColumn(name = "dep_id",referencedColumnName = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "section_of_head",referencedColumnName = "employee_id")
    private Employee employee;


}
