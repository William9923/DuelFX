package com.avatarduel.model;

public class CharacterCardInField {
    private CharacterCard card;
    private int bonusAttack;
    private int bonusDefense;
    public boolean isPoweredUp;
    public boolean hasAttacked;
    private CharacterState state;
    // reference ke player mana

    public CharacterCardInField(CharacterCard card, CharacterState state) {
        this.card = card;
        this.state = state;
        this.bonusAttack = 0;
        this.bonusDefense = 0;
        this.isPoweredUp = false;
        this.hasAttacked = true; // karena kalo baru di summon ga bisa attack kan ya
    }
    public Card getCard() {
        return card;
    }

    public void setCard(CharacterCard card) {
        this.card = card;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public void setBonusAttack(int bonusAttack) {
        this.bonusAttack = bonusAttack;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }

    public int getTotalAttack() {
        return Math.max(0, card.getAttack() + getBonusAttack());
    }

    public int getTotalDefense() {
        return Math.max(0, card.getDefense() + getBonusDefense());
    }

    public void poweredUp() {
        this.isPoweredUp = true;
    }

    public void notPoweredUp() {
        this.isPoweredUp = false;
    }

    public void changeState() {
        if (CharacterState.ATTACK.equals(state)) {
            state = CharacterState.DEFENSE;
        } else {
            state = CharacterState.ATTACK;
        }
    }

    // remove --> manggil fungsi dari player
    public void remove() {
        // nanti dipake buat ngereference remove dari playernya
    }
}
