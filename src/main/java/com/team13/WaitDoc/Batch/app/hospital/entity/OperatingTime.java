package com.team13.WaitDoc.Batch.app.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.team13.WaitDoc.Batch.app.base.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
public class OperatingTime extends BaseEntity {
    @OneToOne
    private Hospital hospital;

    private LocalTime monStartTime;
    private LocalTime monEndTime;

    private LocalTime tueStartTime;
    private LocalTime tueEndTime;

    private LocalTime wedStartTime;
    private LocalTime wedEndTime;

    private LocalTime thuStartTime;
    private LocalTime thuEndTime;

    private LocalTime friStartTime;
    private LocalTime friEndTime;

    private LocalTime satStartTime;
    private LocalTime satEndTime;

    private LocalTime sunStartTime;
    private LocalTime sunEndTime;

    private LocalTime holidayStartTime;
    private LocalTime holidayEndTime;

    private String nightDays;
}
