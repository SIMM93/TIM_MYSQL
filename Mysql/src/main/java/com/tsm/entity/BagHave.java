package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "bagHave")
public class BagHave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "itemNum")
    private String itemNum;

    @Size(max = 255)
    @Column(name = "itemCount")
    private String itemCount;

    @Size(max = 255)
    @Column(name = "Q")
    private String q;

    @Size(max = 255)
    @Column(name = "role")
    private String role;

    @Size(max = 255)
    @Column(name = "comeType")
    private String ComeType;

}