package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "destroyingH")
public class DestroyingH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "destroyType")
    private String destroyType;

    @Size(max = 255)
    @Column(name = "backItemNum")
    private String backItemNum;

    @Size(max = 255)
    @Column(name = "destroyTargetItemNum")
    private String destroyTargetItemNum;

    @Size(max = 255)
    @Column(name = "time")
    private String time;



    @Size(max = 255)
    @Column(name = "backItemCount")
    private String backItemCount;

    @Size(max = 255)
    @Column(name = "hashCode")
    private String hashCode;

    @Size(max = 255)
    @Column(name = "batchNum")
    private String batchNum;

}