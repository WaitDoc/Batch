package com.team13.WaitDoc.Batch.app.hospital.repository;

import com.team13.WaitDoc.Batch.app.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    public Optional<Hospital> findByHpid(String hpid);




}
