package com.team13.WaitDoc.Batch.app.hospital.service;


import com.team13.WaitDoc.Batch.app.base.api.HospitalXml;
import com.team13.WaitDoc.Batch.app.hospital.entity.Hospital;
import com.team13.WaitDoc.Batch.app.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public Optional<Hospital> findByHpid (String hpid) {
        return hospitalRepository.findByHpid(hpid);
    }

    public Hospital findByIdElseThrow(Long hospitalId) {
        return hospitalRepository.findById(hospitalId).orElseThrow();
    }



    public Hospital toEntity(HospitalXml.Item item){
        if(findByHpid(item.getHpid()).isEmpty())
            return Hospital.builder()
                    .name(item.getDutyName())
                    .addr(item.getDutyAddr())
                    .department(item.getDgidIdName())
                    .latitude(item.getWgs84Lat())
                    .longitude(item.getWgs84Lon())
                    .canAdmit(item.getDutyHayn() == 1)
                    .hpid(item.getHpid())
                    .tel(item.getDutyTel1())
                    .introduction(item.getDutyInf())
                    .build();
        return null;
    }

    public void saveAll(List<Hospital> hospitals) {
        hospitalRepository.saveAll(hospitals);
    }
}
