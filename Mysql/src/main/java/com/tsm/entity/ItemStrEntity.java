package com.tsm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "itemStr", schema = "WOW", catalog = "")
public class ItemStrEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "itemNum")
    private String itemNum;

    @Size(max = 255)
    @Column(name = "itemString")
    private String itemString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemString() {
        return itemString;
    }

    public void setItemString(String itemString) {
        this.itemString = itemString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStrEntity that = (ItemStrEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(itemNum, that.itemNum) && Objects.equals(itemString, that.itemString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemNum, itemString);
    }
}