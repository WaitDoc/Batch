package com.team13.WaitDoc.Batch.app.hospital.repository;

import com.team13.WaitDoc.Batch.app.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByHpid(String hpid);




}
