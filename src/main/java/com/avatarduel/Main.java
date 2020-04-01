package com.avatarduel;

import com.avatarduel.model.*;
import com.avatarduel.util.Loader;

import java.util.List;

public class Main {

    public static void testField() {
        
    }

    public static void testHand() {
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
            hand1.addCard(deck1.draw());
            hand2.addCard(deck2.draw());
        }

        System.out.println("After Drawing : ");
        deck1.printDeck();
        System.out.println();
        deck2.printDeck();
        System.out.println();

        System.out.println("Card in Hand 1 : ");
        List<Card> cardList1 = hand1.getCardList();
        for(Card card : cardList1) {
            card.show();
        }
        System.out.println();

        System.out.println("Card in Hand 2 : ");
        List<Card> cardList2 = hand2.getCardList();
        for (Card card : cardList2) {
            card.show();
        }
        System.out.println();

        System.out.println("Test Finding Card : ");
        Card card1 = hand1.findCardByIndex(5);
        Card card2 = hand2.findCardByIndex(5);

        card1.show();
        card2.show();

        System.out.println();

        System.out.println("Testing Contains : ");
        if (hand1.contain(card1)) {
            System.out.println("Test contain successful");
        }

        if (!hand2.contain(card2)) {
            System.out.println("Test contain unsuccessful");
        }
        System.out.println();

        System.out.println("Testing Removing Cards : ");
        Card cardRemove1 = hand1.removeCard(card1);
        if (cardRemove1.equals(card1)){
            System.out.println("Test removing by Card successful");
        }

        Card cardRemove2 = hand2.removeCardByIndex(5);
        if (cardRemove2.equals(card2)) {
            System.out.println("Test removing by index successful");
        }
        System.out.println();

        System.out.println("Hand size : " + hand1.cardInHand());
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

        card1.doEffect();
        card2.doEffect();
        card3.doEffect();
    }

    public static void main(String[] args) {
        System.out.println("Testing Backend");
        Main.testLoader();
        Main.testCard();
        Main.testSkillCard();
        Main.testDeck();
        Main.testHand();
    }
}
