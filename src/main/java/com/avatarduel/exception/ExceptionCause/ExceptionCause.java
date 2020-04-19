package com.avatarduel.exception.ExceptionCause;

/**
 * Defines the cause of an exception
 * The difference between Exception and cause is,
 * the exception defines what the player cannot do
 * The cause describe why the player cannot do it
 */

public interface ExceptionCause {

    /**
     * get the cause of the exception
     * @return String that describe the exception
     */
    String getCause();
}
