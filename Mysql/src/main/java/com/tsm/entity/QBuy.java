package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "QBuy", schema = "WOW", catalog = "")
public class QBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "itemNum")
    private String itemNum;


    @Size(max = 255)
    @Column(name = "stackSize")
    private String stackSize;

    @Size(max = 255)
    @Column(name = "quantity")
    private String quantity;

    @Size(max = 255)
    @Column(name = "price")
    private String price;

    @Size(max = 255)
    @Column(name = "otherPlayer")
    private String otherPlayer;

    @Size(max = 255)
    @Column(name = "player")
    private String player;

    @Size(max = 255)
    @Column(name = "time")
    private String time;

    @Size(max = 255)
    @Column(name = "source")
    private String source;

    @Size(max = 255)
    @Column(name = "Q")
    private String q;

    @Size(max = 255)
    @Column(name = "hashCode")
    private String hashCode;

}