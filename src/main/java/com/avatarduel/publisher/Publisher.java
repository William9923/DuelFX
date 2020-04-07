package com.avatarduel.publisher;

import com.avatarduel.event.IEvent;

public interface Publisher {
    public void publish (IEvent event);
}
