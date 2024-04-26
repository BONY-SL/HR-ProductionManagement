package com.example.luckySystem.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Component

@Entity
@Table(name = "productionstoloading")
public class GoodProductsForLoading {

    @Id
    @Column(name="loading_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loading_id;

    @Column(name = "amount",nullable = false)
    private long amount;

    @Column(name = "batch_code",length = 50,nullable = false)
    private String batch_code;

    @Column(name = "submit_time",nullable = false)
    private LocalTime submit_time;

    @Column(name = "submit_date",length = 50,nullable = false)
    private Date submit_date;

    @ManyToOne
    @JoinColumn(name = "ag_id",referencedColumnName = "agent_id")
    private Agent ag_id;
}
