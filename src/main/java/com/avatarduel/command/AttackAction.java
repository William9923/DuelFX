package com.avatarduel.command;

import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class AttackAction implements IAction{

    private int attackCharacterId;
    private int defenseCharacterId;
    private PlayerType attacker;
    private PlayerType defender;

    public AttackAction(int idAttack, int idDefense, PlayerType attacker, PlayerType defender) {
        this.attackCharacterId = idAttack;
        this.defenseCharacterId = idDefense;
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
        CharacterCardInField attackChar = Game.getInstance()
                .getPlayerByType(attacker)
                .getField()
                .getCharacterCardByID(attackCharacterId);

        CharacterCardInField defenseChar = Game.getInstance()
                .getPlayerByType(defender)
                .getField()
                .getCharacterCardByID(defenseCharacterId);

        int diff = attackChar.getCurrentTotal() - defenseChar.getCurrentTotal();
        attackChar.attack(); // nandain dia uda attack jd ga bisa attack lagi
        // artinya menang
        if (diff >= 0) {
            Player p2 = Game.getInstance().getPlayerByType(defender);
            if (defenseChar.getPosition().equals(CharacterState.ATTACK) || attackChar.isPowerUp()) { // pierce effect
                p2.setHealthPoint(p2.getHealthPoint() - diff);
//                p2.checkLose(); // check lose --> ini di game manager aja kali ya ??
            }
            p2.getField().removeCharacterCard(defenseChar); // hancurin kartu lawan
        }
        // else : kalah, do nothing , ato bisa send message biar interaktif
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
                && f1.getCharacterCardByID(attackCharacterId).canAttack()
                && f1.getCharacterCardByID(attackCharacterId).getCreatedAtTurn() != currentTurn
        );
    }
}
