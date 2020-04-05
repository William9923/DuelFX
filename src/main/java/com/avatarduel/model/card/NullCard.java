package com.avatarduel.model.card;

import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Element;

public class NullCard extends Card {
    private CardType type;
    public NullCard() {
        super(-1, "", Element.AIR, "", "");
        this.type = CardType.NULL;
    }

    @Override
    public CardType getType() {
        return type;
    }
}
