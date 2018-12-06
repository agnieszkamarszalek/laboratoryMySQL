package com.amarszalek.laboratory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="PATIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName = "NN";
    private String lastName = "NN";
    @Column(unique = true, nullable = false)
    private String pesel;
    @Enumerated(value = EnumType.STRING)
    private Gender gender = Gender.I;
    private String phoneNumber = "";
    @OneToMany(mappedBy = "patient")
    private List<TestsList> testsLists;

}
