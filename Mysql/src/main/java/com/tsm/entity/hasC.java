package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "HasC", schema = "WOW", catalog = "")
public class hasC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "ver")
    private String ver;

    @Size(max = 255)
    @Column(name = "checktt")
    private String checktt;

}