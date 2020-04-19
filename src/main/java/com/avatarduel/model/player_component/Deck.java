package com.avatarduel.model.player_component;

import com.avatarduel.dao.CSVCardDAO;
import com.avatarduel.dao.CardDAO;
import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.PlayerType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Deck extends Stack<Card>{
    private CardDAO csvDao;
    private int deckSize;
    private PlayerType type;

    public Deck(int deckSize, PlayerType type) {
        super();
        this.deckSize = deckSize;
        this.csvDao = new CSVCardDAO();
        this.type = type;
        init();
    }

    // init
    private void init() {
        List<Card> charCards = csvDao.getAllCharacterCard();
        List<Card> landCards = csvDao.getAllLandCard();
        List<Card> skillCards = csvDao.getAllSkillCard();

        AtomicInteger j = (type.equals(PlayerType.A)) ? new AtomicInteger(100) : new AtomicInteger(200); // duar terlalu pinrat

        for (int i = 0; i < Math.round(deckSize * 0.4) ; i++) {
            Card card = CardFactory.createClone((CharacterCard) selectRandom(charCards));
            card.setId(j.incrementAndGet());
            super.push(card);
        }
        for (int i = 0; i < Math.round(deckSize * 0.4) ; i++) {
            Card card = CardFactory.createClone((LandCard) selectRandom(landCards));
            card.setId(j.incrementAndGet());
            super.push(card);
        }
        for (int i = 0; i < Math.round(deckSize * 0.2) ; i++) {
            Card card = selectRandom(skillCards);
            card.setId(j.incrementAndGet());
            switch(card.getType()){
                case SKILL_AURA: card = CardFactory.createClone((SkillAuraCard) card); break;
                case SKILL_DESTROY: card = CardFactory.createClone((SkillDestroyCard) card); break;
                case SKILL_POWER_UP: card = CardFactory.createClone((SkillPowerUpCard) card); break;
            }
            super.push(card);
        }
        shuffle();
    }

    private Card selectRandom(List<Card> cardList) {
        return cardList.get(new Random().nextInt(cardList.size()));
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public Card draw() throws EmptyStackException{
        return super.pop();
    }
}
