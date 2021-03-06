package com.avatarduel.model.player_component;

import com.avatarduel.dao.CSVCardDAO;
import com.avatarduel.dao.CardDAO;
import com.avatarduel.factory.CardFactory;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.PlayerType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Deck is one of component that used for to put stacks of cards before move to hands
 *
 * IMPORTANT NOTE:
 * Deck maximum number is 60 and minimum 40 cards.
 * If one of player that loses all the cards in the deck first
 * That player will automatically lose.
 * @author G10-K03-CardGameOOP
 */

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

    /**
     * select 1 random card from the list of cards
     * @param cardList list of cards
     * @return a card
     */
    private Card selectRandom(List<Card> cardList) {
        return cardList.get(new Random().nextInt(cardList.size()));
    }

    /**
     * shuffle the deck using collections.shuffle
     */
    public void shuffle() {
        Collections.shuffle(this);
    }

    /**
     * draw the card, using the operation pop() from stack
     * @return card
     * @throws EmptyStackException if there is no card left
     */
    public Card draw() throws EmptyStackException{
        return super.pop();
    }
}
