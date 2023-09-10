package com.laioffer.staybooking.model;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;
@Embeddable
public class StayReservedDateKey implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long stay_id;
    private LocalDate date;

    //hibernate call需要一个没有参数的构造函数
    public StayReservedDateKey() {}

    public StayReservedDateKey(Long stay_id, LocalDate date) {
        this.stay_id = stay_id;
        this.date = date;
    }

    public Long getStay_id() {
        return stay_id;
    }

    public StayReservedDateKey setStay_id(Long stay_id) {
        this.stay_id = stay_id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public StayReservedDateKey setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    //判断这个class生成的两个object是否相等
    //这个class作为primary key，数据库每次存储都需要比较primary key是否存在
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StayReservedDateKey that = (StayReservedDateKey) o;
        return stay_id.equals(that.stay_id) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stay_id, date);
    }

}

