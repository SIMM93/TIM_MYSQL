package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "dirnew")
public class Dirnew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "pathurl")
    private String pathurl;

    @Size(max = 255)
    @Column(name = "lastfix")
    private String lastfix;

    @Size(max = 255)
    @Column(name = "pathhash")
    private String pathhash;

    @Size(max = 255)
    @Column(name = "lastfixhash")
    private String lastfixhash;

}