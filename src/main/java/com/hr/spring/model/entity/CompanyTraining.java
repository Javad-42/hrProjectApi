package com.hr.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "company_training", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company companies;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training trainings;

    private Date buy_date;

}
