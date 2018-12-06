package com.amarszalek.laboratory.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Test {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "test")
    private List<TestParameter> parameters;
    @ManyToOne
    @JoinColumn(name="tests_list_id", nullable=false)
    private TestsList testsList;
}
