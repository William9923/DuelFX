package com.avatarduel.dao;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.type.CardType;
import com.avatarduel.util.Loader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVCardDAO implements CardDAO {

    private List<Card> cardDatabase;
    private Loader loader;

    public CSVCardDAO() {
        this.cardDatabase = new ArrayList<>();
        this.loader = new Loader();
        cardDatabase.addAll(loader.loadCharacter());
        cardDatabase.addAll(loader.loadLand());
        cardDatabase.addAll(loader.loadSkillAura());
    }

    @Override
    public List<Card> getAllCard() {
        return cardDatabase;
    }

    @Override
    public Card getCardById(int id) {
        return cardDatabase.stream()
                .filter(card -> card.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Card> getAllCharacterCard() {
        return cardDatabase.stream()
                .filter(card -> card.getType() == CardType.CHARACTER)
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> getAllSkillCard() {
        return cardDatabase.stream()
                .filter(card -> (card.getType() == CardType.SKILL_AURA) || (card.getType() == CardType.SKILL_DESTROY) ||(card.getType() == CardType.SKILL_POWER_UP))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> getAllLandCard() {
        return cardDatabase.stream()
                .filter(card -> card.getType() == CardType.LAND)
                .collect(Collectors.toList());
    }
}
