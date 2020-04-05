package com.avatarduel.model.player_component;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private List<CharacterCardInField> charCardList;
    private List<SkillCard> skillCardList;
    private int fieldSize;

    public Field (int size) {
        this.charCardList = new ArrayList<CharacterCardInField>();
        this.skillCardList = new ArrayList<SkillCard>();
        this.fieldSize = size;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public List<SkillCard> getSkillCardList() {
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

    public CharacterCardInField getCharacterCardByIdx(int index) throws InvalidOperationException{
        if (index < fieldSize && index < charCardList.size()) {
            return charCardList.get(index);
        } else {
            throw new InvalidOperationException("Get Char By Idx", "Invalid Index");
        }

    }

    public CharacterCardInField getCharacterCardByID(int cardID) {
        return charCardList.stream()
                .filter(card -> card.getCard().getId() == cardID)
                .findFirst()
                .orElse(null);
    }

    public SkillCard getSkillCardByIdx(int index) throws InvalidOperationException{
        if(index < fieldSize && index < skillCardList.size()) {
            return skillCardList.get(index);
        } else {
            throw new InvalidOperationException("Get Char By Idx", "Invalid Index");
        }

    }

    public SkillCard getSkillCardByID(int cardID) {
        return skillCardList.stream()
                .filter(card -> card.getId() == cardID)
                .findFirst()
                .orElse(null);
    }


    public void connectCards(CharacterCardInField card1, SkillCard card2) throws InvalidOperationException {
        if (isAbleToAddSkill() && isContainCharacter(card1)) {
            card1.pair(card2);
            addSkillCard(card2);
        }
    }

    public void addSkillCard(SkillCard card) throws InvalidOperationException{
        if (isAbleToAddSkill()) {
           skillCardList.add(card);
        } else {
            throw new InvalidOperationException("Add Skill Card", "Unable to play due to Full Skill Field!");
        }
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

    public void removeSkillCard(Card inField) {
        if (isContainSkill(inField)) {
            skillCardList = skillCardList
                    .stream()
                    .filter(card -> !card.equals(inField))
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
