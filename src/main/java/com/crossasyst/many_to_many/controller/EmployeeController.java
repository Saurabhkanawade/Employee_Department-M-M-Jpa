package com.crossasyst.many_to_many.controller;


import com.crossasyst.many_to_many.entity.DepartmentEntity;
import com.crossasyst.many_to_many.model.DepartmentRequest;
import com.crossasyst.many_to_many.model.EmployeeRequest;
import com.crossasyst.many_to_many.model.EmployeeResponse;
import com.crossasyst.many_to_many.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(value = "/employees")
    List<EmployeeRequest> getAllEmployee() {
        log.info("fetching the employees...................................................");
        return employeeService.getAllEmployee();
    }

    @GetMapping(value = "/employees/{empId}")
    public ResponseEntity<EmployeeRequest> getById(@PathVariable Long empId) {
        log.info("fetching the employee "+empId+"...................................................");
        EmployeeRequest employeeRequest = employeeService.getById(empId);
        return new ResponseEntity<>(employeeRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{empId}/departments")
    public ResponseEntity<List<DepartmentRequest>> getDepartmentsByEmployeeId(@PathVariable Long empId) {
        log.info("fetching the employee "+empId+"...................................................");
        List<DepartmentRequest> departmentRequests =
                (List<DepartmentRequest>) employeeService.getDepartmentsByEmployeeId(empId);
        return new ResponseEntity<>(departmentRequests, HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        log.info("creating the employee...................................................");
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/employees/{empId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long empId,
                                               @RequestBody EmployeeRequest employeeRequest) {
        log.info("updating the employee "+empId+"...................................................");
        employeeService.updateEmployee(empId, employeeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/employees/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long empId) {
        log.info("deleting the employee...................................................");
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Deleted the Employee where id :"+empId);
    }

    @PutMapping("/employees/{empId}/departments/{deptId}")
    public ResponseEntity<EmployeeRequest> assignDepartmentToEmployee(@PathVariable Long empId,@PathVariable Long deptId)
    {
        EmployeeRequest employeeRequest=employeeService.assignDepartmentToEmployee(empId,deptId);
        return new ResponseEntity<>(employeeRequest,HttpStatus.OK);
    }
}