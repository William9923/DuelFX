package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;

/**
 * IEvent is an interface which use to various event classes in event package.
 *
 * IMPORTANT NOTE:
 * IEvent only has one method "execute" that will throw InvalidOperationException
 * if and only if the method in the classes doesn't fullify the spesification.
 * @author G10-K03-CardGameOOP
 */

public interface IEvent {
    /**
     * execute method to run the event invoked by user action
     */
    public void execute() throws InvalidOperationException;
}
