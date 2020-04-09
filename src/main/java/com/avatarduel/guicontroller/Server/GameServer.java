package com.avatarduel.guicontroller.Server;

import com.avatarduel.event.IEvent;
import com.avatarduel.guicontroller.Server.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServer implements EventChannel {

    private Map<Channel, List<Subscriber>> subscriberChannelMap;

    public GameServer() {
        this.subscriberChannelMap = new HashMap<Channel, List<Subscriber>>();
    }

    public void executeEvent(IEvent event, Channel channel) {
        if (event.validate()) {
            event.execute();
        }
        renderAll(channel);
    }

    @Override
    public void renderAll(Channel channel) {
        if (subscriberChannelMap.containsKey(channel)) {
            subscriberChannelMap.get(channel).forEach(
                    subscriber -> subscriber.render()
            );
        }
    }

    @Override
    public void addSubscriber(Channel channel, Subscriber subscriber) {
        if (!subscriberChannelMap.containsKey(channel)){
            subscriberChannelMap.put(channel, new ArrayList<Subscriber>());
        }
        subscriberChannelMap.get(channel).add(subscriber);
    }
}
