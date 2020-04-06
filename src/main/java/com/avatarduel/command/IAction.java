package com.avatarduel.command;

import com.avatarduel.exception.InvalidOperationException;

public interface IAction {
    public void execute();
    public boolean validate();
}
