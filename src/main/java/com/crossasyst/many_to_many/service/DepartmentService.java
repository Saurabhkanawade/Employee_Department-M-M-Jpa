package com.crossasyst.many_to_many.service;

import com.crossasyst.many_to_many.entity.DepartmentEntity;
import com.crossasyst.many_to_many.mapper.DepartmentMapper;
import com.crossasyst.many_to_many.model.DepartmentRequest;
import com.crossasyst.many_to_many.repository.DepartmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;


    public DepartmentService(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }


    public List<DepartmentRequest> getDepartmentByEmployeeId() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        List<DepartmentRequest> departmentRequests = departmentMapper.entityToModels(departmentEntities);

        return departmentRequests;
    }

    public DepartmentRequest getDepartmentById(Long deptId) {
        DepartmentEntity departmentEntity =departmentRepository.findById(deptId).get();
        DepartmentRequest departmentRequest=departmentMapper.entityToModel(departmentEntity);
        return departmentRequest;
    }

    public DepartmentRequest updateDepartmentById(Long deptId, DepartmentRequest departmentRequest) {
        DepartmentEntity departmentEntity=departmentRepository.findById(deptId).get();
        DepartmentEntity departmentEntity1=departmentMapper.modelToEntity(departmentRequest);
        departmentEntity1.setDepartmentId(deptId);
        departmentRepository.save(departmentEntity1);
        return departmentRequest;
    }
}