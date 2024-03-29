package com.team13.WaitDoc.Batch.app.hospital.repository;

import com.team13.WaitDoc.Batch.app.hospital.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<Department> findByName(String name);

}
