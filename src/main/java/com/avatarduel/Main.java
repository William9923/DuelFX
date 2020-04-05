package com.avatarduel;

import com.avatarduel.command.EndTurnAction;
import com.avatarduel.command.IAction;
import com.avatarduel.model.Game;
import com.avatarduel.model.card.*;
import com.avatarduel.model.player_component.Deck;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Hand;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.Phase;
import com.avatarduel.model.type.PlayerType;
import com.avatarduel.util.Loader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void testAttackCommand() {
        // First all player should draw their cards

        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.DRAW) : "Wrong Phase";
        Game.getInstance().getPlayerByType(PlayerType.A).startGameDraw(); // start game draw
        Game.getInstance().getPlayerByType(PlayerType.B).startGameDraw(); // start game draw
        boolean canProceed = true;
        // Next Phase
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.MAIN1);

        // Player 1 Turn : Player A
        CharacterCard cardToSummon1 = (CharacterCard) Game.getInstance().getPlayerByType(PlayerType.A).getHand()
                .stream()
                .filter(card -> card.getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null); // subtyping

        if (cardToSummon1 != null) {
            Game.getInstance().getPlayerByType(PlayerType.A).getField().addCharacterCard(new CharacterCardInField(cardToSummon1, CharacterState.ATTACK, Game.getInstance().getCurrentTurn()));     // summoning first Monster
        } else {
            canProceed = false;
        }

        // Next Phase : Battle Phase
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.BATTLE) : "Wrong Phase";
        // Next Phase : Main Phase 2
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.MAIN2) : "Wrong Phase";
        // Next Phase : End Turn
        // bungkus try catch ya ntar
        IAction newAction = new EndTurnAction(PlayerType.A);
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.DRAW) : "End Turn Action Wrong";
        assert Game.getInstance().getCurrentPlayer().equals(PlayerType.B) : "End Turn Action Wrong";






    }

    public static void testGame() {

        // how to get player based on types from
        Player p1 = Game.getInstance().getPlayerByType(PlayerType.A); // A : First Player
        Player p2 = Game.getInstance().getPlayerByType(PlayerType.B); // B : Second Player

        assert p1.getType().equals(PlayerType.A) : "Player 1 error";
        assert p2.getType().equals(PlayerType.B) : "Player 2 error";

        p1.setHealthPoint(p1.getHealthPoint() - 10); // using the copied reference to make the change inside the Game Instance
        assert Game.getInstance().getPlayerByType(PlayerType.A).getHealthPoint() == 70 : "Checking object reference :)";

        Game.getInstance().getPlayerByType(PlayerType.A).startGameDraw();
        assert Game.getInstance().getPlayerByType(PlayerType.A).getHand().size() == 7 : "Drawing error";

        List<Card> cardList = Game.getInstance().getPlayerByType(PlayerType.A).getHand().stream()
                .filter(c -> c.getType().equals(CardType.LAND))
                .collect(Collectors.toList());
        System.out.println("Land Card In Hand : " + cardList.size());

        AtomicInteger i = new AtomicInteger();

        List<Integer> cardListIndex = cardList.stream()
                .map(c -> Game.getInstance().getPlayerByType(PlayerType.A).getHand().indexOf(c) - i.getAndIncrement())
                .collect(Collectors.toList());

        cardListIndex.forEach(index -> Game.getInstance().getPlayerByType(PlayerType.A).playLandCard(index));
        System.out.println("Total Power For Player : " + Game.getInstance().getPlayerByType(PlayerType.A).getPower().getTotal());


        // how to next phase
        System.out.println("Testing on Phase Transition in game : ");
        Phase currPhase = Game.getInstance().getCurrentPhase().getPhase();
        System.out.println("Current Phase : " + currPhase);
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.MAIN1) : "Error in Phase Transition";
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.BATTLE) : "Error in Phase Transition";
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.MAIN2) : "Error in Phase Transition";
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.END) : "Error in Phase Transition";
        Game.getInstance().nextPhase();
        assert Game.getInstance().getCurrentPhase().getPhase().equals(Phase.DRAW) : "Error in Phase Transition";

    }

    public static void testPlayer() {
        // Skenario for Player Functionality

        Player p1 = new Player(PlayerType.A); // first Player
        Player p2 = new Player(PlayerType.B); // second Player

        // init game + draw method
        p1.startGameDraw();
        p2.startGameDraw();

        p1.getHand().print();

        assert p1.getHand().size() == 7 ;
        assert p2.getHand().size() == 7 : "Wrong card in player 2 hand";

        assert p1.getDeck().size() == (60 - 7) : p1.getDeck().size();
        assert p2.getDeck().size() == (60 - 7) : p2.getDeck().size();

        // play a character card :
        CharacterCard cardChar1  = (CharacterCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.CHARACTER))
                .findFirst()
                .orElse(null);

        CharacterCard cardChar2  = (CharacterCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.CHARACTER) && !card.equals(cardChar1))
                .findFirst()
                .orElse(null);

        int countMonster = 0;
        int countSpell = 0;
        int countLand = 0;
        // summoning process test
        if (cardChar1 != null) {
            System.out.println("First Monster Usage : ");
            cardChar1.show();
            countMonster++;
            int index1  = p1.getHand().indexOf(cardChar1);
            p1.playCharacterCard(index1, CharacterState.ATTACK, 1); // summon in Attack Position
            assert p1.getHand().size() == 7 - (countMonster + countSpell) : "Summon Wrong";
            assert p1.getField().getCharCardList().size() == countMonster : "Summon Wrong";

        }

        if (cardChar2 != null) {
            System.out.println("Second Monster Usage : ");
            cardChar2.show();
            countMonster++;
            int index2 = p1.getHand().indexOf(cardChar2);
            p1.playCharacterCard(index2, CharacterState.DEFENSE,1); // summon in Defense Position
            assert p1.getHand().size() == 7 - (countMonster + countSpell) : "Summon second time gone wrong";
            assert p1.getField().getCharCardList().size() == countMonster : "Summon second time gone wrong";
        }

        // using aura test
        SkillCard cardSkill1 = (SkillCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.SKILL_AURA))
                .findFirst()
                .orElse(null);

        SkillCard cardSkill2 = (SkillCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.SKILL_AURA) && !card.equals(cardSkill1))
                .findFirst()
                .orElse(null);

        if (cardSkill1 != null && cardChar1 != null) {
            System.out.println("Skill First Usage : ");
            cardSkill1.show();
            countSpell++;
            int index1 = p1.getHand().indexOf(cardSkill1);
            p1.playSkillAuraCard(index1, 0); // uwu semoga ada KWKWKW

            assert p1.getHand().size() ==  7 - (countMonster + countSpell) : "Card Skill usage error"; // wlwkw, ini asumsi doang kalo atas atasnya bener
            assert p1.getField().getCharacterCardByIdx(0).getConnectedCard().size() == countSpell : "Card skill not paired with monsta";
            assert p1.getField().getSkillCardList().size() == countSpell : "Skill not utilized in field";
        }
        if (cardSkill2 != null && cardChar1 != null) {
            System.out.println("Second Skill Usage : ");
            cardSkill2.show();
            countSpell++;
            int index2 = p1.getHand().indexOf(cardSkill2);
            p1.playSkillAuraCard(index2,0);

            assert p1.getHand().size() ==  7 - (countMonster + countSpell) : "Card Skill usage error"; // wlwkw, ini asumsi doang kalo atas atasnya bener
            assert p1.getField().getCharacterCardByIdx(0).getConnectedCard().size() == countSpell : "Card skill not paired with monsta";
            assert p1.getField().getSkillCardList().size() == countSpell : "Skill not utilized in field";

        }

        // test to play land card

        LandCard landCard1 = (LandCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.LAND))
                .findFirst()
                .orElse(null);

        LandCard landCard2 = (LandCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.LAND) && !card.equals(landCard1))
                .findFirst()
                .orElse(null);

        if (landCard1 != null) {
            System.out.println("First Land Usage : ");
            landCard1.show();
            countLand++;

            int index1 = p1.getHand().indexOf(landCard1);
            p1.playLandCard(index1);

            assert p1.getHand().size() == 7 - (countMonster + countLand + countSpell) : "Land Card Usage Error";
            System.out.println(p1.getPower().getCurrent(landCard1.getElement()));
            assert p1.getPower().getCurrent(landCard1.getElement()) == 1 : "Waw error";
        }

        // test to remove character card from field
        if (p1.getField().getCharCardList().size() > 1) {
            p1.getField().removeCharacterCard(p1.getField().getCharacterCardByIdx(1));  // remove second character
            assert p1.getField().getCharCardList().size() == 1 : "Waw bakaaa ... remove Character gone wrong";
        }

        // test to remove skill card from field
        if (p1.getField().getSkillCardList().size() > 0) {
            int size =p1.getField().getSkillCardList().size();
            p1.getField().removeSkillCard(p1.getField().getSkillCardByIdx(0));
            assert p1.getField().getSkillCardList().size() == size - 1 : "Removing card skill test unsucessful";
        }

        // test to decrease health point
        p1.setHealthPoint(p1.getHealthPoint() - 10);
        assert p1.getHealthPoint() == 70 : "Error decreasing health point";

        // test to refresh state of player
        if (landCard1 != null) {
            System.out.println("First Element Power Manager Usage : ");
            int power = p1.getPower().getCurrent(landCard1.getElement());
            p1.getPower().reduce(landCard1.getElement(),1);
            p1.getPower().refresh();

            assert  p1.getPower().getCurrent(landCard1.getElement()) == power : "Salah refresh power manager";
        }

    }

    public static void testField() {
        // Skenario for Field Functionality
        System.out.println("\nTest Field");
        Deck deck = new Deck(20);
        Hand hand = new Hand();
        Field field = new Field(8); // ikutin contoh spek dl, yakni 8 besarny

        for (int i = 0; i < 7; i++) {
            hand.add(deck.draw());
        }

        List<Card> cardList1 = new ArrayList<>(hand);  // inget reference di objek
        for(Card card : cardList1) {
            card.show();
        }
        System.out.println();

        for (Card card : cardList1) {
            if (card.getType().equals(CardType.CHARACTER)){
                field.addCharacterCard(new CharacterCardInField((CharacterCard) card, CharacterState.ATTACK, 1));  // cara panggil character ke arena
            }
        }

        for (Card card : cardList1) {
            if (card.getType().equals(CardType.CHARACTER)){
                hand.remove(card);                          // cara hapus kartu dari tangan user
            }
        }

        List<Card> cardList2 = new ArrayList<>(hand);  // tes liat isinya
        for(Card card : cardList2) {
            card.show();
        }
        System.out.println();

        if (field.getCharCardList().size() > 0) {
            for (Card card : cardList2) {
                if (card.getType().equals(CardType.SKILL_AURA)){
                    field.connectCards(field.getCharacterCardByIdx(0),(SkillCard) card);  // ini nunjukin cara akses via gui lewat index, dan juga kasi tau cara konekin kartu di field (buat power up dan aura)
                    // dan nunjukkin bisa banyak koneknya
                }
            }

            // mao nunjukkin perbedaan
            System.out.println("Before giving aura : ");
            CharacterCardInField holder1 = field.getCharacterCardByIdx(0);  // kasi tau cara ngakses kalo via gui, nanti kan guinya cuman ngasi via index aja infonya jadinya
            CharacterCard holder2 = (CharacterCard) holder1.getCard();
            System.out.println("Attack Before Adding AURA : " + holder2.getAttack());
            System.out.println("Defense Before Adding AURA : " + holder2.getDefense());

            System.out.println("After give aura effect : ");
            System.out.println("Attack After Adding AURA : " + holder1.getTotalAttack());
            System.out.println("Defense After Adding AURA : " + holder1.getTotalDefense());

            System.out.println("Aura Card  : " + field.getSkillCardList().size() );

            System.out.println("\nAura Card List : ");
            List<SkillCard> cardList = holder1.getConnectedCard();
            SkillAuraCard cardAura; int bonusAttack = 0; int bonusDefense = 0;
            for (Card card: cardList) {
                 cardAura = (SkillAuraCard) card;
                 cardAura.show();
                 System.out.println("Aura Bonus Attack : " + cardAura.getAttack());
                 System.out.println("Aura Bonus Defense : " + cardAura.getDefense());
                 bonusAttack += cardAura.getAttack();
                 bonusDefense += cardAura.getDefense();
            }
            System.out.println("Total Aura Bonus Attack : " + bonusAttack);
            System.out.println("Total Aura Bonus Defense : " + bonusDefense);

            // Example of when a character die:
            field.removeCharacterCard(holder1); // bubay
            System.out.println("Size : (harusnya 0) " + field.getSkillCardList().size());
            System.out.println("Size : " + field.getCharCardList().size());

            System.out.println("Aura Card  : " + field.getSkillCardList().size() );
        }
    }

    public static void testHand() {
        // Skenario for Hand Functionality
        System.out.println("\nTest Hand : ");
        Deck deck1 = new Deck (10);
        Deck deck2 = new Deck (10);

        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        System.out.println("Before Drawing : ");
        deck1.printDeck();
        System.out.println();
        deck2.printDeck();
        System.out.println();

        for (int i = 0; i < 7; i++) {
            hand1.add(deck1.draw());
            hand2.add(deck2.draw());
        }

        System.out.println("After Drawing : ");
        deck1.printDeck();
        System.out.println();
        deck2.printDeck();
        System.out.println();

        System.out.println("Card in Hand 1 : ");;
        for(Card card : hand1) {
            card.show();
        }
        System.out.println();

        System.out.println("Card in Hand 2 : ");

        for (Card card : hand2) {
            card.show();
        }
        System.out.println();

        System.out.println("Test Finding Card : ");
        Card card1 = hand1.get(5);
        Card card2 = hand2.get(5);

        card1.show();
        card2.show();

        System.out.println();

        System.out.println("Testing Contains : ");
        if (hand1.contains(card1)) {
            System.out.println("Test contain successful");
        }

        if (!hand2.contains(card2)) {
            System.out.println("Test contain unsuccessful");
        }
        System.out.println();

        System.out.println("Testing Removing Cards : ");
        Card cardRemove1 = hand1.remove(hand1.indexOf(card1));
        if (cardRemove1.equals(card1)){
            System.out.println("Test removing by Card successful");
        }

        Card cardRemove2 = hand2.remove(5);
        if (cardRemove2.equals(card2)) {
            System.out.println("Test removing by index successful");
        }
        System.out.println();

        System.out.println("Hand size : " + hand1.size());
    }

    public static void testDeck() {
        System.out.println("\nTest Deck : ");
        Deck deck1 = new Deck(10);
        Deck deck2 = new Deck(5);

        System.out.println("Test Print Out Deck 1:");
        deck1.printDeck();
        System.out.println("Deck1 size : " + deck1.size());
        System.out.println();
        System.out.println("Test Print Out Deck 2:");
        deck2.printDeck();
        System.out.println("Deck2 size : " + deck2.size());
        System.out.println();

        System.out.println("Test Functionality dari Deck:");
        System.out.println("Drawing Test:");
        Card checkFirstCard = deck1.showFirstCard();
        Card firstCard = deck1.draw();

        assert checkFirstCard.equals(firstCard) : "Checking checkFirst or draw error";

        firstCard.show();
        System.out.println();

        System.out.println("Shuffle Test:");
        deck2.shuffle();
        deck2.printDeck();
        System.out.println();
    }

    public static void testLoader() {
        System.out.println("\nTest Loader : ");
        Loader loader = new Loader();
        List<Card> characterCardList;
        characterCardList = loader.loadCharacter();

        List<Card> landCardList;
        landCardList = loader.loadLand();

        List<Card> skillCardList;
        skillCardList = loader.loadSkillAura();

        System.out.println("Character Card List : ");
        loader.printLoadResult(characterCardList);

        System.out.println("Land Card List : ");
        loader.printLoadResult(landCardList);

        System.out.println("Skill Card List : ");
        loader.printLoadResult(skillCardList);

    }

    public static void testCard() {
        System.out.println("\nTest Card : ");
        Card card1;
        card1 = new CharacterCard("17",  "Katara"  ,"WATER"  ,"Waterbending master from Southern Water Tribe, sister of Sokka, and friend of Aang."  ,"src/res/image/character/Katara.png"  ,"13"  ,"7"  ,"5");

        Card card2;
        card2 = new LandCard("1"  ,"Eastern Water Temple"  ,"AIR"  ,"One of the two temples exclusively housing female airbenders."  ,"src/res/image/Eastern Water Temple.png");

        Card card3;
        card3 = new SkillAuraCard("77"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3"  ,"-1"  ,"5");

        card1.show();
        card2.show();
        card3.show();
    }

    public static void testSkillCard() {
        System.out.println("\nTest Skill Card : ");
        Card card1;
        Card card2;
        Card card3;

        card1 = new SkillAuraCard("77"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3"  ,"-1"  ,"5");
        card2 = new SkillDestroyCard("78"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3"  );
        card3 = new SkillPowerUpCard("79"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3");

        card1.show();
        card2.show();
        card3.show();

    }

    public static void main(String[] args) {
        System.out.println("Testing Backend");
//        Main.testLoader();
//        Main.testCard();
//        Main.testSkillCard();
//        Main.testDeck();
//        Main.testHand();
//        Main.testField();
//        Main.testPlayer();
//        Main.testGame();
        Main.testAttackCommand();
    }
}
