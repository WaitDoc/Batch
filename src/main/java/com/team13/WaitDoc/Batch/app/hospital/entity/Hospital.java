package com.team13.WaitDoc.Batch.app.hospital.entity;

import javax.persistence.*;

import com.team13.WaitDoc.Batch.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
@Table(indexes = @Index(name = "hpid_index", columnList = "hpid"))
public class Hospital extends BaseEntity {
    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;

    private String name;

    @Column(length = 1000)
    private String addr;

    @Column(length = 1000)
    private String introduction;

    @Column(length = 1000)
    private String department;

    private boolean hasER;

    private boolean canAdmit;

    private int bedCount;

    private String classify;

    @Column(name = "hpid", unique = true)
    private String hpid;

    private double latitude;

    private double longitude;

    private int waitingNumber;

    private String tel;

    @OneToOne(mappedBy = "hospital")
    private OperatingTime operatingTime;

}

