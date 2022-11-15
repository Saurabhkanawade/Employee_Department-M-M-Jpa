package com.crossasyst.many_to_many.repository;

import com.crossasyst.many_to_many.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
