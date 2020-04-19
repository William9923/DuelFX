package com.avatarduel.event;

import com.avatarduel.exception.ExceptionCause.AttackOnTheCreatedTurnCause;
import com.avatarduel.exception.ExceptionCause.InvalidPhaseCause;
import com.avatarduel.exception.ExceptionCause.MultipleAttackOnTheSameTurnCause;
import com.avatarduel.exception.InvalidAttackException;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.CharacterCardInField;
import com.avatarduel.model.card.SkillCard;
import com.avatarduel.model.card.SkillCardInField;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AttackEvent is a event for attacking other opponent character.
 *
 * If other opponent Attack / Defense is higher, the attack will still count, but the attacker character will not be destroyed
 * This event only valid in Battle Phase
 * If other opponent character that being attacked destroyed, all skill card equipped to that card will be destroyed
 *
 * IMPORTANT NOTE:
 * This event will communicate with game singleton instantly, so there are no need to validate
 * In case where event is not possible to do, we throw exception so that the GUI Board can give the
 * error message to the player playing the games
 *
 * Also, this event only handle attack other monster case. In case for direct attack, it will be handled by DirectAttackEvent
 * @author G10-K03-CardGameOOP
 */

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

    /**
     * execute method to run the event invoked by user action
     */
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

        Field f1 = Game.getInstance().getPlayerByType(attacker).getField();
        Field f2 = Game.getInstance().getPlayerByType(defender).getField();
        int currentTurn = Game.getInstance().getCurrentTurn();
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();

        if (!currPhase.equals(Phase.BATTLE)){
            throw new InvalidAttackException(new InvalidPhaseCause(Phase.BATTLE));
        }

        if (f1.getCharacterCardByID(attackCharacterId).getCreatedAtTurn() == currentTurn) {
            throw new InvalidAttackException(new AttackOnTheCreatedTurnCause());
        }

        if (f1.getCharacterCardByID(attackCharacterId).hasAttacked){
            throw new InvalidAttackException(new MultipleAttackOnTheSameTurnCause());
        }

        if (defender != Game.getInstance().getCurrentOpponent()) {
            throw new InvalidAttackException(new AttackOnTheCreatedTurnCause());
        }

        int diff = attackChar.getCurrentTotal() - defenseChar.getCurrentTotal();
        attackChar.hasAttacked = true; // nandain dia uda attack jd ga bisa attack lagi
        // artinya menang
        if (diff >= 0) {
            System.out.println("Attack Difference : " + diff);
            System.out.println("Defender : " + defender);
            if (defenseChar.getPosition().equals(CharacterState.ATTACK) || attackChar.isPowerUp()) { // pierce effect
                System.out.println("Reducing Health Point");
                Game.getInstance().getPlayerByType(defender).setHealthPoint(Game.getInstance().getPlayerByType(defender).getHealthPoint()- diff);
            }

            List<SkillCard> pairedSkillCard = defenseChar.getConnectedCard();
            for (SkillCard card: pairedSkillCard) {
                List<SkillCardInField> list1 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getSkillCardList();
                List<SkillCardInField> list2 = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().getSkillCardList();

                Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().setSkillCardList(list1.stream()
                        .filter(c -> c.getCard().getId() != card.getId())
                        .collect(Collectors.toList()));
                Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getField().setSkillCardList(list2.stream()
                        .filter(c -> c.getCard().getId() != card.getId())
                        .collect(Collectors.toList()));
            }
            Game.getInstance().getPlayerByType(defender).removeCharacterFromFieldByID(defenseCharacterId); // hancurin kartu lawan
        }
    }
}
