package com.avatarduel.event;

import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;

public class PlayLandCardEvent implements IEvent {
    private PlayerType playerType;
    private int landCardID;

    public PlayLandCardEvent(int idCard, PlayerType playerType) {
        this.playerType = playerType;
        this.landCardID = idCard;
    }

    @Override
    public void execute() throws InvalidOperationException {
        LandCard landCard = (LandCard) Game.getInstance().getPlayerByType(playerType).getHand().stream()
                .filter(card -> card.getId() == landCardID && card.getType().equals(CardType.LAND))
                .findFirst()
                .orElse(null);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        int currentFieldSize = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList().size();

        if (currPhase != Phase.MAIN) {
            throw new InvalidOperationException("Play Land Card", "Not in the Main Phase");
        }

        if (currPlayer != playerType) {
            throw new InvalidOperationException("Play Land Card", "Not your turn");
        }

        if (landCard == null) {
            throw new InvalidOperationException("Play Land Card", "Invalid Card!!");
        }

        if (CardType.LAND != landCard.getType()) {
            throw new InvalidOperationException("Play Land Card", "Not A Land Card !");
        }


        if (Game.getInstance().getPlayerByType(currPlayer).hasPlayLand) {
            throw new InvalidOperationException("Play Land Card", "Unable To Play Multiple Land Card In the Same Turn !");
        }
        
        Player player = Game.getInstance().getPlayerByType(playerType);
        // remove card
        player.getHand().remove(landCard);
        // add power to player
        player.getPower().add(landCard.getElement(),1);
        // change state of hasPlayLand of player
        player.hasPlayLand = true;
    }

    @Override
    public boolean validate() {
        LandCard landCard = (LandCard) Game.getInstance().getPlayerByType(playerType).getHand().stream()
                .filter(card -> card.getId() == landCardID && card.getType().equals(CardType.LAND))
                .findFirst()
                .orElse(null);
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        PlayerType currPlayer = Game.getInstance().getCurrentPlayer();
        int currentFieldSize = Game.getInstance().getPlayerByType(playerType).getField().getSkillCardList().size();

        return (((currPhase == Phase.MAIN))
                        && (currPlayer == playerType)
                        && (landCard != null)
                        && (CardType.LAND == landCard.getType())
                        && (currentFieldSize < Game.getInstance().getPlayerByType(playerType).getField().getFieldSize())
                        && !(Game.getInstance().getPlayerByType(currPlayer).hasPlayLand)
                );
    }
}
