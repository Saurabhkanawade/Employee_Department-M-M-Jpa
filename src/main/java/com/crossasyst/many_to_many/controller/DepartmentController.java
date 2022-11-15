package com.crossasyst.many_to_many.controller;

import com.crossasyst.many_to_many.model.DepartmentRequest;
import com.crossasyst.many_to_many.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<List<DepartmentRequest>> getDepartmentByEmployeeId() {
        log.info("fetching the departments .......................");
        return new ResponseEntity<>(departmentService.getDepartmentByEmployeeId(), HttpStatus.OK);

    }

    @GetMapping(value = "/departments/{deptId}")
    public ResponseEntity<DepartmentRequest> getDepartmentById(@PathVariable Long deptId) {
        return new ResponseEntity<>(departmentService.getDepartmentById(deptId),HttpStatus.OK);
    }

    @PutMapping(value = "/departments/{deptId}")
    public ResponseEntity<DepartmentRequest> updateDepartmentById(@PathVariable Long deptId,
                                                                  @RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(departmentService.updateDepartmentById(deptId,departmentRequest),HttpStatus.OK);
    }
}
