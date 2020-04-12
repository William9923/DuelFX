package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class AttackEvent implements IEvent {

    private int attackCharacterId;
    private int defenseCharacterId;
    private PlayerType attacker;
    private PlayerType defender;

    public AttackEvent(int idAttack, int idDefense, PlayerType attacker, PlayerType defender) {
        this.attackCharacterId = idAttack;
        this.defenseCharacterId = idDefense;
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void execute() throws InvalidOperationException{
        CharacterCardInField attackChar = Game.getInstance()
                .getPlayerByType(attacker)
                .getField()
                .getCharacterCardByID(attackCharacterId);

        CharacterCardInField defenseChar = Game.getInstance()
                .getPlayerByType(defender)
                .getField()
                .getCharacterCardByID(defenseCharacterId);

        int diff = attackChar.getCurrentTotal() - defenseChar.getCurrentTotal();
        attackChar.hasAttacked = true; // nandain dia uda attack jd ga bisa attack lagi
        // artinya menang
        if (diff >= 0) {
            Player p2 = Game.getInstance().getPlayerByType(defender);
            if (defenseChar.getPosition().equals(CharacterState.ATTACK) || attackChar.isPowerUp()) { // pierce effect
                p2.setHealthPoint(p2.getHealthPoint() - diff);
            }
            p2.removeCharacterFromFieldByID(defenseCharacterId); // hancurin kartu lawan
        }
    }

    @Override
    public boolean validate() {
        Field f1 = Game.getInstance().getPlayerByType(attacker).getField();
        Field f2 = Game.getInstance().getPlayerByType(defender).getField();
        int currentTurn = Game.getInstance().getCurrentTurn();
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        return (currPhase.equals(Phase.BATTLE)
                && currentTurn != 1
                && currPlayer.equals(attacker)
                && f1.getCharacterCardByID(attackCharacterId) != null  // ganti kalo uda ada trycatch
                && f2.getCharacterCardByID(defenseCharacterId) != null // ganti kalo uda ada trycatch
                && f1.getCharacterCardByID(attackCharacterId).getCreatedAtTurn() != currentTurn
                && !f1.getCharacterCardByID(attackCharacterId).hasAttacked
        );
    }
}
