package com.avatarduel;

import com.avatarduel.event.*;
import com.avatarduel.exception.InvalidOperationException;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.*;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;

public class Main {

//    public static void testGame() throws InvalidOperationException {
//        Game.getInstance(); // instantiate game instance
//        Player p1 = Game.getInstance().getPlayerByType(PlayerType.A);
//        Player p2 = Game.getInstance().getPlayerByType(PlayerType.B);
//
//        int p1CardUsage = 0;
//        int p2CardUsage = 0;
//
//        // lets make 10 turn of only playing landCard :
//        for (int i = 0; i < 20; i++) {
//            System.out.println(Game.getInstance().getCurrentPlayer());
//            IEvent event1 = new DrawEvent(Game.getInstance().getCurrentPlayer());
//            int currentCard = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand().size();
//            if (event1.validate()) {
//                event1.execute();
//                if (Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand().size() <= 10)
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand().size() == currentCard+1 : "Draw Event Wrong";
//            }
//            LandCard landCard = (LandCard) Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand()
//                    .stream()
//                    .filter(c -> c.getType().equals(CardType.LAND))
//                    .findFirst()
//                    .orElse(null);
//
//            if (landCard != null) {
//                // we can play the card
//                if (Game.getInstance().getCurrentPlayer().equals(PlayerType.A)){
//                    p1CardUsage++;
//                } else {
//                    p2CardUsage++;
//                }
//                int power = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getPower().getCurrent(landCard.getElement());
//                IEvent event2 = new PlayLandCardEvent(landCard.getId(), Game.getInstance().getCurrentPlayer());
//                if (event2.validate()) { // validate and execute
//                    event2.execute();
//                    assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getPower().getCurrent(landCard.getElement()) == power + 1 : "Event LandCard Wrong";
//                }
//
//            }
//            // Test Playing another LandCard
//            LandCard landCard2 = (LandCard) Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand()
//                    .stream()
//                    .filter(c -> c.getType().equals(CardType.LAND))
//                    .findFirst()
//                    .orElse(null);
//
//            if (landCard2 != null) {
////                System.out.println("Hit here landcard 2");
//                IEvent event3 = new PlayLandCardEvent(landCard2.getId(), Game.getInstance().getCurrentPlayer());
//                if (event3.validate()) {
//                    throw new InvalidOperationException("Playing Land Card" , "Cannot Play Land Card more than 1 a game!!");
//                }
//            }
//
//            IEvent event5 = new NextPhaseEvent(); // double change pahase, tested
//            if (event5.validate()) {
//                event5.execute();
//            }
//            if (event5.validate()) {
//                event5.execute();
//            }
//            if (event5.validate()) {
//                event5.execute();
//            }
//            System.out.println("CurrentPhase : " + Game.getInstance().getCurrentPhase().getPhase());
//            IEvent event4 = new EndTurnEvent(); // tested
//            if (event4.validate()) {
//                event4.execute();
//            }
//        }
//
//        System.out.println("Card In Hand Player 1 : " + p1.getHand().size());
//        System.out.println("Card In Hand Player 2 : " + p2.getHand().size());
//
//        System.out.println("Card In Deck Player 1 : " + p1.getDeck().size());
//        System.out.println("Card In Deck Player 2 : " + p2.getDeck().size());
//
//        System.out.println("Card Usage Player 1 : " + p1CardUsage);
//        System.out.println("Card Usage Player 2 : " + p2CardUsage);
//
//        System.out.println("Power Player 1 : ");
//        System.out.println("Water : " + Game.getInstance().getPlayerByType(PlayerType.A).getPower().getTotal_water());
//        System.out.println("Fire : " + Game.getInstance().getPlayerByType(PlayerType.A).getPower().getTotal_fire());
//        System.out.println("Earth : " + Game.getInstance().getPlayerByType(PlayerType.A).getPower().getTotal_earth());
//        System.out.println("Air : " + Game.getInstance().getPlayerByType(PlayerType.A).getPower().getTotal_air());
//
//        System.out.println("Power Player 2 : ");
//        System.out.println("Water : " + Game.getInstance().getPlayerByType(PlayerType.B).getPower().getTotal_water());
//        System.out.println("Fire : " + Game.getInstance().getPlayerByType(PlayerType.B).getPower().getTotal_fire());
//        System.out.println("Earth : " + Game.getInstance().getPlayerByType(PlayerType.B).getPower().getTotal_earth());
//        System.out.println("Air : " + Game.getInstance().getPlayerByType(PlayerType.B).getPower().getTotal_air());
//
//        System.out.println("Summoning Test : ");
//        IEvent drawEvent1 = new DrawEvent(Game.getInstance().getCurrentPlayer());
//        if(drawEvent1.validate()) {
//            drawEvent1.execute();
//        }
//
//        CharacterCard card = (CharacterCard) Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand()
//                .stream()
//                .filter(c -> c.getType().equals(CardType.CHARACTER) && c.getPower() <= Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getPower().getCurrent(c.getElement()))
//                .findFirst()
//                .orElse(null);
//
//        if (card !=null) {
//            System.out.println("Summon : " + card.getName());
//            IEvent summonEvent = new SummonEvent(card.getId(), Game.getInstance().getCurrentPlayer(), CharacterState.ATTACK, 1);
//            if (summonEvent.validate()){
//                int currentElem = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getPower().getCurrent(card.getElement());
//                summonEvent.execute();
//                p1CardUsage++;
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList().size() == 1 : "Summon Error";
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHand().size() + Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getDeck().size() + p1CardUsage == 60 : "Duar salah oi";
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getPower().getCurrent(card.getElement()) == currentElem - card.getPower() : "Duar kurang power nya salah";
//            }
//        }
//
//        if (card != null) {
//            System.out.println("Change position Event : ");
//            System.out.println(Game.getInstance().getCurrentPhase().getPhase());
//            IEvent changePositionEvent = new ChangePositionEvent(Game.getInstance().getCurrentPlayer(), card.getId());
//            if (changePositionEvent.validate()) {
//                changePositionEvent.execute();
//                System.out.println("Uwu Change Posistion");
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList()
//                        .stream()
//                        .filter(c -> c.getCard().getId() == card.getId())
//                        .findFirst()
//                        .orElse(null).getPosition().equals(CharacterState.DEFENSE) : "Failed To Change the position";
//            }
//            if (changePositionEvent.validate()) {
//                changePositionEvent.execute();
//                System.out.println("Uwu Change Posistion");
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList()
//                        .stream()
//                        .filter(c -> c.getCard().getId() == card.getId())
//                        .findFirst()
//                        .orElse(null).getPosition().equals(CharacterState.DEFENSE) : "Failed To Change the position";
//            } // uwu bisa berkali kali duar
//            if (changePositionEvent.validate()) {
//                changePositionEvent.execute();
//                System.out.println("Uwu Change Posistion");
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList()
//                        .stream()
//                        .filter(c -> c.getCard().getId() == card.getId())
//                        .findFirst()
//                        .orElse(null).getPosition().equals(CharacterState.DEFENSE) : "Failed To Change the position";
//            }
//        }
//
//        IEvent nextPhaseEvent = new NextPhaseEvent();
//        if (nextPhaseEvent.validate()) {
//            nextPhaseEvent.execute(); // uwu lets go to the fighting
//        }
//
//        System.out.println("Current Phase : " + Game.getInstance().getCurrentPhase().getPhase());
//
//        if (card != null) {
//            // lets go do the direct attack to the opponent
//            IEvent directAttackEvent = new DirectAttackEvent(card.getId(),Game.getInstance().getCurrentPlayer());
//            IEvent endTurn = new EndTurnEvent();
//            if (endTurn.validate()){
//                System.out.println(Game.getInstance().getCurrentPlayer());
//                endTurn.execute();
//            }
//            IEvent drawPhase = new DrawEvent(Game.getInstance().getCurrentPlayer());
//            System.out.println(Game.getInstance().getCurrentPlayer());
//            if(drawPhase.validate()){
//                System.out.println(Game.getInstance().getCurrentPlayer());
//                drawPhase.execute();
//            }
////            System.out.println(Game.getInstance().getCurrentPhase().getPhase());
//            IEvent endTurn2 = new EndTurnEvent();
//            if (endTurn2.validate()) {
//                System.out.println(Game.getInstance().getCurrentPlayer());
//                endTurn2.execute();
//            }
//            IEvent drawPhase2 = new DrawEvent(Game.getInstance().getCurrentPlayer());
//
//            if(drawPhase2.validate()){
//                System.out.println(Game.getInstance().getCurrentPlayer());
//                drawPhase2.execute();
//            }
//            System.out.println("Halo : ini lewat loh " + Game.getInstance().getCurrentPhase().getPhase());
//            if (nextPhaseEvent.validate()){
//                System.out.println("Hit this");
//                nextPhaseEvent.execute();
//            }
//            CharacterCardInField oi = Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getField().getCharCardList()
//                    .stream()
//                    .filter(c -> c.getCard().getId() == card.getId())
//                    .findFirst()
//                    .orElse(null);
//            System.out.println("Game Current Turn : " + Game.getInstance().getCurrentTurn());
//            System.out.println("Card Created At : " + oi.getCreatedAtTurn());
//            if (directAttackEvent.validate()) {
//                System.out.println("Waw berhasil direct attack");
//                directAttackEvent.execute();
//                assert Game.getInstance().getPlayerByType(Game.getInstance().getCurrentPlayer()).getHealthPoint() > Game.getInstance().getPlayerByType(Game.getInstance().getCurrentOpponent()).getHealthPoint() : "Woi salah attack mas";
//            }
//        }
//
//        System.out.println("");
//    }
    public static void main(String[] args)  {
        System.out.println("Testing Backend");

        try{
//            testGame();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
