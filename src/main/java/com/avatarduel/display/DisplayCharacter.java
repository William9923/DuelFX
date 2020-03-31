package com.avatarduel.display;

import com.avatarduel.model.Card;

import java.util.HashMap;

public class DisplayCharacter implements IDisplay{

    @Override
    public HashMap<String,String> display(Card card) {
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("id", String.valueOf(card.getId()));
        map.put("name", card.getName());
        map.put("description", card.getDescription());
        map.put("image_url",card.getImage());
        map.put("element", card.getElement().toString());
        map.put("type", card.getType().toString());
        return map;
    }
}
