package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field {
    private List<CharacterCardInField> charCardList;
    private List<SkillCardInField> skillCardList;
    private int fieldSize;

    public Field (int size) {
        this.charCardList = new ArrayList<CharacterCardInField>();
        this.skillCardList = new ArrayList<SkillCardInField>();
        this.fieldSize = size;
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

    public void addSkillCard(SkillCardInField inField) {
        if (isAbleToAddSkill()) {
           skillCardList.add(inField);
        }
    }

    public void removeCharacterCard(CharacterCardInField inField) {
        Iterator itr = charCardList.iterator();
        while (itr.hasNext())
        {
            Card itCard = (Card)itr.next();
            if (itCard.equals(inField)) {
                itr.remove();
            }
        }
    }

    public void removeSkillCard(SkillCardInField inField) {
        Iterator itr = skillCardList.iterator();
        while (itr.hasNext())
        {
            Card itCard = (Card)itr.next();
            if (itCard.equals(inField)) {
                itr.remove();
            }
        }
    }
}
