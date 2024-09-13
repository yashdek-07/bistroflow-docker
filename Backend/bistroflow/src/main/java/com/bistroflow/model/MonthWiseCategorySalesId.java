package com.bistroflow.model;

import java.io.Serializable;
import java.util.Objects;

public class MonthWiseCategorySalesId implements Serializable {
    private String month;
    private int categoryId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthWiseCategorySalesId that = (MonthWiseCategorySalesId) o;
        return categoryId == that.categoryId && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, categoryId);
    }
}
