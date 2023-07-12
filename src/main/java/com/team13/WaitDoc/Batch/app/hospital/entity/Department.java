package com.team13.WaitDoc.Batch.app.hospital.entity;

import javax.persistence.*;

import com.team13.WaitDoc.Batch.app.base.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@Entity
public class Department extends BaseEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;

}
