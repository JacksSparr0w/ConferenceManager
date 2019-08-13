package com.katsubo.finaltask.validate;

import com.katsubo.finaltask.entity.Event;

import java.util.Date;

/**
 * The type Event validator.
 */
public class EventValidator implements Validator<Event> {
    private static final String ENTITY_IS_NULL = "entity_is_null";
    private static final String DATE_IS_INCORRECT = "date_is_incorrect";
    private static final String CAPACITY_IS_INVALID = "capacity_is_invalid";

    /**
     *
     * @param entity to valid
     * @return null if entity is valid, or string with describe of first found invalid field
     */
    @Override
    public String isValid(Event entity) {
        if (entity == null) {
            return ENTITY_IS_NULL;
        }
        if (!entity.getDate().after(new Date())) {
            return DATE_IS_INCORRECT;
        }

        if (entity.getCapacity() < 0) {
            return CAPACITY_IS_INVALID;
        }


        return null;

    }
}
