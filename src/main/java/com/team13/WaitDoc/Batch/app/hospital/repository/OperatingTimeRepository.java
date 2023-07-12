package com.team13.WaitDoc.Batch.app.hospital.repository;

import com.team13.WaitDoc.Batch.app.hospital.entity.Hospital;
import com.team13.WaitDoc.Batch.app.hospital.entity.OperatingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatingTimeRepository extends JpaRepository<OperatingTime, Long> {
    Optional<OperatingTime> findByHospital(Hospital hospital);
}
