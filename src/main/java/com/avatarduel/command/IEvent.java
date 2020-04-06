package com.avatarduel.command;

import com.avatarduel.exception.InvalidOperationException;

public interface IEvent {
    public void execute();
    public boolean validate();
}
