package com.katsubo.finaltask.entity;

import java.util.Objects;

public class Value extends Entity {
    private String value;

    public Value(String value) {
        this.value = value;
    }

    public Value(Integer id) {
        setId(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return value.equalsIgnoreCase(value1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
