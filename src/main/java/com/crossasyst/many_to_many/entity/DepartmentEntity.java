package com.crossasyst.many_to_many.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @SequenceGenerator(initialValue = 1000,
            allocationSize = 1,
            name = "partner_sequence",
            sequenceName = "partner_sequence")
    @GeneratedValue(generator = "partner_sequence")
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "department_name")
    private String departmentName;

    @ManyToMany(mappedBy = "departments")
    private List<EmployeeEntity> employees;

}
