package com.crossasyst.many_to_many.mapper;

import com.crossasyst.many_to_many.entity.DepartmentEntity;
import com.crossasyst.many_to_many.model.DepartmentRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    public DepartmentEntity modelToEntity(DepartmentRequest departmentRequest);

    public List<DepartmentEntity> modelToEntities(List<DepartmentRequest> departmentRequests);

    public DepartmentRequest entityToModel(DepartmentEntity departmentEntity);

    public List<DepartmentRequest> entityToModels(List<DepartmentEntity> departmentEntities);

}
