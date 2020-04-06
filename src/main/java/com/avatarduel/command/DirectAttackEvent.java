package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class DirectAttackEvent implements IEvent {

    private PlayerType player;
    private int attackCharacterId;

    public DirectAttackEvent(int idAttack, PlayerType attacker) {
        this.attackCharacterId = idAttack;
        this.player = attacker;
    }

    @Override
    public void execute()  {
        CharacterCardInField attackChar = Game.getInstance()
                .getPlayerByType(player)
                .getField()
                .getCharacterCardByID(attackCharacterId);

        attackChar.hasAttacked = true; // change state monster yang uda nyerang
        Player p2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()); // ambil reference player 2
        p2.setHealthPoint(p2.getHealthPoint() - attackChar.getTotalAttack()); // kurangin health point player lawan
    }

    @Override
    public boolean validate() {
        Field f1 = Game.getInstance()
                .getPlayerByType(player)
                .getField();
        int currentTurn = Game.getInstance().getCurrentTurn();
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        return (currPhase.equals(Phase.BATTLE)
                && currentTurn != 1 // not first turn
                && currPlayer.equals(player)
                && f1.getCharacterCardByID(attackCharacterId) != null  // ganti kalo uda ada trycatch
                && f1.getCharacterCardByID(attackCharacterId).canAttack()
                && f1.getCharacterCardByID(attackCharacterId).getCreatedAtTurn() != currentTurn
        );
    }
}
