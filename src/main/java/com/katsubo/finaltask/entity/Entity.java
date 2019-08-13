package com.katsubo.finaltask.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Entity.
 */
abstract public class Entity implements Serializable {
    private Integer id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity1 = (Entity) o;
        return Objects.equals(id, entity1.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
