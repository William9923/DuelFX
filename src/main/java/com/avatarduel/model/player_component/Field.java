package com.avatarduel.model.player_component;

import com.avatarduel.factory.CardInFieldFactory;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.card.SkillCardInField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Field is one of component that used for to put all the cards that we used to play the game
 *
 * IMPORTANT NOTE:
 * Field able to filter which card can be in field which can't.
 * Also there is limit number card in Field.
 * @author G10-K03-CardGameOOP
 */

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


    public CharacterCardInField getCharacterCardByID(int cardID) {
        return charCardList.stream()
                .filter(card -> card.getCard().getId() == cardID)
                .findFirst()
                .orElse(null);
    }

    public void setSkillCardList(List<SkillCardInField> skillCardList) {
        this.skillCardList = skillCardList;
    }

    public void addSkillCard(SkillCard card, int index, int createdAt){
        if (isAbleToAddSkill()) {
           skillCardList.add((SkillCardInField) cardFactory.createCardInField(card, createdAt,index, null));
        } else {
            System.out.println("Unable to add skill card");
        }
    }

    public int getEmptyCharacterIndex() {
        List<Integer> notAvailableIndex= new ArrayList<Integer>();
        for(CharacterCardInField characterCardInField : charCardList) {
            notAvailableIndex.add(characterCardInField.getIndex());
        }
        for(int i = 0 ; i < 6 ; i++ ) {
            if(!notAvailableIndex.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public int getEmptySkillCardIndex()  {
        System.out.println("Getting the skill index");
        List<Integer> notAvailableIndex = new ArrayList<>();
        for(SkillCardInField skillCardInField : skillCardList) {
            notAvailableIndex.add(skillCardInField.getIndex());
        }
        for(int i = 0 ; i < 6 ; i++) {
            if(!notAvailableIndex.contains(i)) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    public void removeCharacterCard(CharacterCardInField inField) {
        if (isContainCharacter(inField)){
            CharacterCardInField cardInField = charCardList
                    .stream()
                    .filter(c -> c.equals(inField))
                    .findFirst()
                    .orElse(null); // throw Error
            charCardList = charCardList
                    .stream()
                    .filter(card -> !card.equals(inField))
                    .collect(Collectors.toList());
        }
    }

}
