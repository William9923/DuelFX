package com.avatarduel;

import com.avatarduel.event.*;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.*;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.PlayerType;

public class Main {

    public static void testGame() throws InvalidOperationException {
        Game.getInstance(); // instantiate game instance

        // draw event
        IEvent event1 = new DrawEvent(PlayerType.A); // first Player Draw
        if (event1.validate()) {
            event1.execute();
            assert Game.getInstance().getPlayerByType(PlayerType.A).getHand().size() == 8 : "Draw Event Wrong";
        }
        // Play A LandCard
        LandCard landCard = (LandCard) Game.getInstance().getPlayerByType(PlayerType.A).getHand()
                .stream()
                .filter(c -> c.getType().equals(CardType.LAND))
                .findFirst()
                .orElse(null);

        if (landCard != null) {
            // we can play the card
            IEvent event2 = new PlayLandCardEvent(landCard.getId(), PlayerType.A);
            if (event2.validate()) { // validate and execute
                event2.execute();
            }
            assert Game.getInstance().getPlayerByType(PlayerType.A).getPower().getCurrent(landCard.getElement()) == 1 : "Event LandCard Wrong";
        }

        // Test Playing another LandCard
        LandCard landCard2 = (LandCard) Game.getInstance().getPlayerByType(PlayerType.A).getHand()
                .stream()
                .filter(c -> c.getType().equals(CardType.LAND))
                .findFirst()
                .orElse(null);

        if (landCard2 != null) {
            System.out.println("Hit here landcard 2");
            IEvent event3 = new PlayLandCardEvent(landCard2.getId(), PlayerType.A);
            if (event3.validate()) {
                throw new InvalidOperationException("Playing Land Card" , "Cannot Play Land Card more than 1 a game!!");
            }
        }

        // Testing To Play Any CharacterCard if enough energy
        CharacterCard charCard = (CharacterCard) Game.getInstance().getPlayerByType(PlayerType.A).getHand()
                .stream()
                .filter(c -> c.getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null);

//        if (charCard != null) {
//            IEvent event4 = new SummonEvent();
//        }


    }
    public static void main(String[] args)  {
        System.out.println("Testing Backend");

        try{
            testGame();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
