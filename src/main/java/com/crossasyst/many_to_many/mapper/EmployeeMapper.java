package com.crossasyst.many_to_many.mapper;


import com.crossasyst.many_to_many.entity.EmployeeEntity;
import com.crossasyst.many_to_many.model.EmployeeRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface    EmployeeMapper {

    public EmployeeEntity modelToEntity(EmployeeRequest employeeRequest);

    public EmployeeRequest entityToModel(EmployeeEntity employeeEntity);

    public List<EmployeeRequest> entityToModels(List<EmployeeEntity> employeeEntity);

}

