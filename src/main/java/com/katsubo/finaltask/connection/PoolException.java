package com.katsubo.finaltask.connection;

/**
 * The type Pool exception.
 */
public class PoolException extends Exception {
    /**
     * Instantiates a new Pool exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Pool exception.
     *
     * @param message the message
     */
    public PoolException(String message) {
        super(message);
    }
}
