package com.avatarduel;

import com.avatarduel.model.card.*;
import com.avatarduel.model.player_component.Deck;
import com.avatarduel.model.player_component.Field;
import com.avatarduel.model.player_component.Hand;
import com.avatarduel.model.player_component.Player;
import com.avatarduel.model.type.CardType;
import com.avatarduel.model.type.CharacterState;
import com.avatarduel.model.type.PlayerType;
import com.avatarduel.util.Loader;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void testAttackCommand() {

    }

    public static void testGame() {

    }

    public static void testPlayer() {
        // Skenario for Player Functionality

        Player p1 = new Player(PlayerType.A); // first Player
        Player p2 = new Player(PlayerType.B); // second Player

        // init game + draw method
        p1.startGameDraw();
        p2.startGameDraw();

        assert p1.getHand().size() == 7 ;
        assert p2.getHand().size() == 7 : "Wrong card in player 2 hand";

        assert p1.getDeck().size() == (60 - 7) : p1.getDeck().size();
        assert p2.getDeck().size() == (60 - 7) : p2.getDeck().size();

        // play a character card :
        CharacterCard cardChar  = (CharacterCard) p1.getHand().stream()
                .filter(card -> card.getType().equals(CardType.CHARACTER))
                .findAny()
                .orElse(null);

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
                    field.connectCards(field.getCharacterCardByIdx(0),card);  // ini nunjukin cara akses via gui lewat index, dan juga kasi tau cara konekin kartu di field (buat power up dan aura)
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

            System.out.println("\nAura Card List : ");
            List<Card> cardList = holder1.getConnectedCard();
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
        System.out.println();
        System.out.println("Test Print Out Deck 2:");
        deck2.printDeck();
        System.out.println();

        System.out.println("Test Functionality dari Deck:");
        System.out.println("Drawing Test:");
        Card checkFirstCard = deck1.showFirstCard();
        Card firstCard = deck1.draw();

        if (checkFirstCard.equals(firstCard)){
            System.out.println("Checking successful");
        }
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
        Main.testDeck();
//        Main.testHand();
//        Main.testField();
//        Main.testPlayer();
    }
}
