package com.crossasyst.many_to_many.service;


import com.crossasyst.many_to_many.entity.DepartmentEntity;
import com.crossasyst.many_to_many.entity.EmployeeEntity;
import com.crossasyst.many_to_many.mapper.DepartmentMapper;
import com.crossasyst.many_to_many.mapper.EmployeeMapper;
import com.crossasyst.many_to_many.model.DepartmentRequest;
import com.crossasyst.many_to_many.model.EmployeeRequest;
import com.crossasyst.many_to_many.model.EmployeeResponse;
import com.crossasyst.many_to_many.repository.DepartmentRepository;
import com.crossasyst.many_to_many.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
        
@Service
@Log4j2
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeRequest> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<EmployeeRequest> employeeRequest = employeeMapper.entityToModels(employeeEntities);
        log.info("fetched success all the employee ............................");
        return employeeRequest;
    }

    public EmployeeRequest getById(Long empId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(empId);
        EmployeeRequest employeeRequest = null;

        if (employeeEntity.isPresent()) {
            employeeRequest = employeeMapper.entityToModel(employeeEntity.get());
            log.info("The employee " + empId + " is successfully found....................");
        } else {
            log.info("The employee " + empId + " is not found.............................");
        }
        return employeeRequest;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeRepository.save(employeeMapper.modelToEntity(employeeRequest));
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employeeEntity.getEmployeeId());
        log.info("created employee success.....with id " + employeeResponse.getEmployeeId());
        return employeeResponse;

    }

    public void updateEmployee(Long empId, EmployeeRequest employeeRequest) {

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(empId);
        EmployeeEntity cart = employeeMapper.modelToEntity(employeeRequest);

        if (employeeEntity.isPresent()) {
            cart.setEmployeeId(employeeEntity.get().getEmployeeId());
            log.info("the id id :" + empId + " is updated successfully");
            employeeRepository.save(cart);
        } else {
            log.info("cannot found the cart with id" + empId);
        }
    }


    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
        log.info("Deleted success employee " + empId + " from the database....................");
    }


    public List<DepartmentRequest> getDepartmentsByEmployeeId(Long empId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(empId);
        List<DepartmentEntity> departmentEntities = null;
        if (employeeEntity.isPresent()) {
            departmentEntities = employeeEntity.get().getDepartments();
        } else {
            log.info("department is not found for employee :" + empId);
        }
        return departmentMapper.entityToModels(departmentEntities);
    }

    public EmployeeRequest assignDepartmentToEmployee(Long empId, Long deptId) {

        List<DepartmentEntity> departmentEntities = null;
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();
        departmentEntities = employeeEntity.getDepartments();
        departmentEntities.add(departmentEntity);
        employeeEntity.setDepartments(departmentEntities);
        employeeRepository.save(employeeEntity);

        EmployeeRequest employeeRequest = employeeMapper.entityToModel(employeeEntity);
        return employeeRequest;
    }
}
