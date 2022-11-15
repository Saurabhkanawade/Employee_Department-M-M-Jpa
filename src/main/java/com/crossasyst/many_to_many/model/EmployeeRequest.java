package com.crossasyst.many_to_many.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;

    private List<DepartmentRequest>departments;
}
