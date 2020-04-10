package com.avatarduel.event;

public abstract class OneWayEvent implements IEvent {
    public abstract void execute();
    public abstract boolean validate();
}
