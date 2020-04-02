package com.avatarduel.model.player_component;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCardInField;

import java.util.*;

public class Field {
    private List<CharacterCardInField> charCardList;
    private List<Card> skillCardList;
    private int fieldSize;

    public Field (int size) {
        this.charCardList = new ArrayList<CharacterCardInField>();
        this.skillCardList = new ArrayList<Card>();
        this.fieldSize = size;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public List<Card> getSkillCardList() {
        return skillCardList;
    }

    public List<CharacterCardInField> getCharCardList() {
        return charCardList;
    }

    private boolean isContainCharacter(CharacterCardInField c) {
        return charCardList.contains(c);
    }

    private boolean isContainSkill(Card s) {
        return skillCardList.contains(s);
    }

    public boolean isAbleToAddChar() {
        return charCardList.size() < fieldSize;
    }

    public boolean isAbleToAddSkill() {
        return skillCardList.size() < fieldSize;
    }

    public void addCharacterCard(CharacterCardInField inField) {
        if (isAbleToAddChar()){
            charCardList.add(inField);
        }
    }

    public CharacterCardInField getCharacterCardByIdx(int index) {
        if (index < fieldSize && index < charCardList.size()) {
            return charCardList.get(index);
        }
        return null; // throw error
    }

    public Card getSkillCardByIdx(int index) {
        if(index < fieldSize && index < skillCardList.size()) {
            return skillCardList.get(index);
        }
        return null; // harusnya throw error
    }

    public void connectCards(CharacterCardInField card1, Card card2) {
        if (isAbleToAddSkill() && isContainCharacter(card1)) {
            card1.pair(card2);
            addSkillCard(card2);
        }
    }

    public void addSkillCard(Card card) {
        if (isAbleToAddSkill()) {
           skillCardList.add(card);
        }
    }

    public void removeCharacterCard(CharacterCardInField inField) {
        if (isContainCharacter(inField)){
            Iterator itr = charCardList.iterator();
            while (itr.hasNext())
            {
                CharacterCardInField itCard = (CharacterCardInField) itr.next();
                if (itCard.equals(inField)) {
                    List<Card> connectPair = new ArrayList<>(inField.getConnectedCard());
                    for (Card pairCard : connectPair) {
                        removeSkillCard(pairCard);
                    }
                    itr.remove();
                }
            }
        }
    }

    public void removeSkillCard(Card inField) {
        if (isContainSkill(inField)) {
            // delete from Field Reference
            Iterator itr = skillCardList.iterator();
            while (itr.hasNext())
            {
                Card itCard = (Card)itr.next();
                if (itCard.equals(inField)) {
                    itr.remove();
                }
            }

            // delete from the Character Card Reference
            for (CharacterCardInField inField2: charCardList) {
                if (inField2.getConnectedCard().contains(inField)){
                    inField2.removePair(inField);
                }
            }
        }
    }
}
