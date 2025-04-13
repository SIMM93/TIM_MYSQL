package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "Timt2out")
public class Timt2out {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Size(max = 255)
    @Column(name = "Q")
    private String q;

    @Size(max = 255)
    @Column(name = "itemNum")
    private String itemNum;

    @Column(name = "sumda")
    private Double sumda;

    @Size(max = 255)
    @Column(name = "itemString")
    private String itemString;

    @Column(name = "buyavgprice")
    private Double buyavgprice;

    @Column(name = "nowprice")
    private Double nowprice;

    @Column(name = "lr")
    private Double lr;

    @Column(name = "lrl")
    private Double lrl;

    @Size(max = 255)
    @Column(name = "who")
    private String who;



}