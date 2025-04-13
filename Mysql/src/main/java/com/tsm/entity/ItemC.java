package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "ItemC")
public class ItemC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "itemNum")
    private String itemNum;

    @Size(max = 255)
    @Column(name = "itemNeed")
    private String itemNeed;

    @Size(max = 255)
    @Column(name = "itemNeedCount")
    private String itemNeedCount;

    @Size(max = 255)
    @Column(name = "Q")
    private String q;

    @Size(max = 255)
    @Column(name = "Ctype")
    private String ctype;

    @Size(max = 255)
    @Column(name = "itemString")
    private String itemString;

}