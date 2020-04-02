package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class AttackAction implements ICommand, IValidate{

    private int attackCharacterIdx;
    private int defenseCharacterIdx;
    private PlayerType attacker;
    private PlayerType defender;

    public AttackAction(int indexAttack, int indexDefense, PlayerType attacker, PlayerType defender) {
        this.attackCharacterIdx = indexAttack;
        this.defenseCharacterIdx = indexDefense;
        this.attacker = attacker;
        this.defender = defender;

        if (validate()) {
            execute();
        }
        // else : throw error bangsat
        // end bungkusannya
    }

    @Override
    public void execute() {
        CharacterCardInField attackChar = Game.getInstance().getPlayerByType(attacker).getField().getCharacterCardByIdx(attackCharacterIdx);
        CharacterCardInField defenseChar = Game.getInstance().getPlayerByType(defender).getField().getCharacterCardByIdx(defenseCharacterIdx);
        int diff = attackChar.getCurrentTotal() - defenseChar.getCurrentTotal();

        // artinya menang
        if (diff >= 0) {
            if (defenseChar.getState().equals(CharacterState.ATTACK) || attackChar.isPowerUp()) {
                Player p2 = Game.getInstance().getPlayerByType(defender);
                p2.setHealthPoint(p2.getHealthPoint() - diff);
                p2.checkLose(); // check lose
            }
        }
        // else : kalah, do nothing , ato bisa send message biar interaktif
    }

    @Override
    public boolean validate() {
        Field f1 = Game.getInstance().getPlayerByType(attacker).getField();
        Field f2 = Game.getInstance().getPlayerByType(defender).getField();
        int currentTurn = Game.getInstance().getCurrentTurn();
        Phase currPhase = Game.getInstance().getCurrentPhase();
        return (currPhase.equals(Phase.BATTLE_PHASE)
                && currentTurn != 1
                && f1.getCharacterCardByIdx(attackCharacterIdx) != null  // ganti kalo uda ada trycatch
                && f2.getCharacterCardByIdx(defenseCharacterIdx) != null // ganti kalo uda ada trycatch
                && f1.getCharacterCardByIdx(attackCharacterIdx).canAttack() && f1.getCharacterCardByIdx(attackCharacterIdx).getCreatedAtTurn() != currentTurn
        );
    }
}
