package com.avatarduel.guicontroller.Server;

import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.Server.subscriber.Subscriber;

public interface EventChannel {
    // release event ke semua subscriber
    public void renderAll(Channel channel);
    public void addSubscriber(Channel channel, Subscriber subscriber);
}
