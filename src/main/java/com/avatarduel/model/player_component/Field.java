package com.avatarduel.model.player_component;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.card.SkillCardInField;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private List<CharacterCardInField> charCardList;
    private List<SkillCardInField> skillCardList;
    private int fieldSize;
    private CardInFieldFactory cardFactory;

    public Field (int size) {
        this.charCardList = new ArrayList<CharacterCardInField>();
        this.skillCardList = new ArrayList<SkillCardInField>();
        this.fieldSize = size;
        this.cardFactory = new CardInFieldFactory();
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public List<SkillCardInField> getSkillCardList() {
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

    public void addCharacterCard(CharacterCardInField inField) throws InvalidOperationException {
        if (isAbleToAddChar()){
            charCardList.add(inField);
        } else {
            throw new InvalidOperationException("Summon Character", "Unable to summon due to Full Field Size!");
        }
    }


    public CharacterCardInField getCharacterCardByID(int cardID) {
        return charCardList.stream()
                .filter(card -> card.getCard().getId() == cardID)
                .findFirst()
                .orElse(null);
    }


    public SkillCardInField getSkillCardByID(int cardID) {
        return skillCardList.stream()
                .filter(cardInField -> cardInField.getCard().getId() == cardID)
                .findFirst()
                .orElse(null);
    }


    public void connectCards(CharacterCardInField card1, SkillCard card2, int index, int createdAt) throws InvalidOperationException {
        if (isAbleToAddSkill()) {
            card1.pair(card2);
            addSkillCard(card2, index, createdAt);
        }
    }

    public void addSkillCard(SkillCard card, int index, int createdAt) throws InvalidOperationException{
        if (isAbleToAddSkill()) {
           skillCardList.add((SkillCardInField) cardFactory.createCardInField(card, index, createdAt, null));
        } else {
            throw new InvalidOperationException("Add Skill Card", "Unable to play due to Full Skill Field!");
        }
    }

    public int getEmptyCharacterIndex() throws InvalidOperationException {
        List<Integer> notAvailableIndex= new ArrayList<Integer>();
        for(CharacterCardInField characterCardInField : charCardList) {
            notAvailableIndex.add(characterCardInField.getIndex());
        }
        for(int i = 0 ; i < 9 ; i++ ) {
            if(!notAvailableIndex.contains(i)) {
                return i;
            }
        }
        throw new InvalidOperationException("Full Board", "No empty character spaces available because board is full");
    }

    public int getEmptySkillCardIndex() throws InvalidOperationException {
        List<Integer> notAvailableIndex = new ArrayList<>();
        for(SkillCardInField skillCardInField : skillCardList) {
            notAvailableIndex.add(skillCardInField.getIndex());
        }
        for(int i = 0 ; i < 9 ; i++) {
            if(!notAvailableIndex.contains(i)) {
                return i;
            }
        }
        throw new InvalidOperationException("Full board", "No empty skill spaces available because board is full");
    }

    public void removeCharacterCard(CharacterCardInField inField) {
        if (isContainCharacter(inField)){
            CharacterCardInField cardInField = charCardList
                    .stream()
                    .filter(c -> c.equals(inField))
                    .findFirst()
                    .orElse(null); // throw Error
            skillCardList = skillCardList
                    .stream()
                    .filter(c -> !cardInField.getConnectedCard().contains(c))
                    .collect(Collectors.toList());
            charCardList = charCardList
                    .stream()
                    .filter(card -> !card.equals(inField))
                    .collect(Collectors.toList());
        }
    }

    public void removeSkillCard(SkillCard inField) {
        if (isContainSkill(inField)) {
            skillCardList = skillCardList
                    .stream()
                    .filter(cardInField -> cardInField.getCard().getId() != inField.getId())
                    .collect(Collectors.toList());

            charCardList.forEach(
                    c -> {
                        if (c.getConnectedCard().contains(inField)) {
                            c.removePair(inField);
                        }
                    }
            );

        }
    }
}
