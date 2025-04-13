package com.tsm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "TsmScan", schema = "WOW", catalog = "")
public class TsmScan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "itemNum", nullable = false)
    private String itemNum;

    @Size(max = 255)
    @Column(name = "minBuyout")
    private String minBuyout;

    @Size(max = 255)
    @Column(name = "marketValue")
    private String marketValue;

    @Size(max = 255)
    @Column(name = "numAuctions")
    private String numAuctions;

    @Size(max = 255)
    @Column(name = "quantity")
    private String quantity;

    @Size(max = 255)
    @Column(name = "lastScan")
    private String lastScan;

    @Column(name = "isLast")
    private Integer isLast;

    @Size(max = 255)
    @Column(name = "Q")
    private String q;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TsmScan that = (TsmScan) o;
        return Objects.equals(id, that.id) && Objects.equals(itemNum, that.itemNum) && Objects.equals(minBuyout, that.minBuyout) && Objects.equals(marketValue, that.marketValue) && Objects.equals(numAuctions, that.numAuctions) && Objects.equals(quantity, that.quantity) && Objects.equals(lastScan, that.lastScan) && Objects.equals(isLast, that.isLast) && Objects.equals(q, that.q);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemNum, minBuyout, marketValue, numAuctions, quantity, lastScan, isLast, q);
    }

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

    public String getMinBuyout() {
        return minBuyout;
    }

    public void setMinBuyout(String minBuyout) {
        this.minBuyout = minBuyout;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getNumAuctions() {
        return numAuctions;
    }

    public void setNumAuctions(String numAuctions) {
        this.numAuctions = numAuctions;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLastScan() {
        return lastScan;
    }

    public void setLastScan(String lastScan) {
        this.lastScan = lastScan;
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}