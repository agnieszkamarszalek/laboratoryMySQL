package com.amarszalek.laboratory.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Result {
    @Id
    private Long id;
    private String result;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @OneToOne(mappedBy = "result")
    private TestParameter testParameter;
}
