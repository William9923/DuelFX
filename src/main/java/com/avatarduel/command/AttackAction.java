package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import javax.swing.Action;

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
        // bungkus try catch block
        validate();
        execute();
        // end bungkusannya
    }

    @Override
    public void execute() {
        
    }

    @Override
    public boolean validate() {
        return (Game.getInstance().getCurrentPhase().equals(Phase.BATTLE_PHASE)
                && Game.getInstance().getCurrentTurn() != 1
                && Game.getInstance().getPlayerByType(attacker).getField().getCharacterCardByIdx(attackCharacterIdx) != null
                && Game.getInstance().getPlayerByType(defender).getField().getCharacterCardByIdx(defenseCharacterIdx) != null
        );
    }
}
