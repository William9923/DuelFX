package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;

public interface IEvent {
    public void execute() throws InvalidOperationException;
}
