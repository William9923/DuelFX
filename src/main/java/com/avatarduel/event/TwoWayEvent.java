package com.avatarduel.event;

public abstract class TwoWayEvent implements IEvent{
    public abstract void execute();
    public abstract boolean validate();
}
