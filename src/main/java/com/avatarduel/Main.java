package com.avatarduel;

import com.avatarduel.model.*;
import com.avatarduel.util.Loader;

import java.util.List;

public class Main {

    public static void testDeck() {
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
        Loader loader = new Loader();
        List<Card> characterCardList;
        characterCardList = loader.loadCharacter();

        List<Card> landCardList;
        landCardList = loader.loadLand();

        List<Card> skillCardList;
        skillCardList = loader.loadSkill();

        System.out.println("Character Card List : ");
        loader.printLoadResult(characterCardList);

        System.out.println("Land Card List");
        loader.printLoadResult(landCardList);

        System.out.println("Skill Card List");
        loader.printLoadResult(skillCardList);

    }

    public static void testCard() {
        Card card1;
        card1 = new CharacterCard("17",  "Katara"  ,"WATER"  ,"Waterbending master from Southern Water Tribe, sister of Sokka, and friend of Aang."  ,"src/res/image/character/Katara.png"  ,"13"  ,"7"  ,"5");

        Card card2;
        card2 = new LandCard("1"  ,"Eastern Water Temple"  ,"AIR"  ,"One of the two temples exclusively housing female airbenders."  ,"src/res/image/Eastern Water Temple.png");

        Card card3;
        card3 = new SkillCard("77"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3"  ,"-1"  ,"5");

        card1.show();
        card2.show();
        card3.show();
    }

    public static void testSkillCard() {
        SkillCard card1;
        SkillCard card2;
        SkillCard card3;

        card1 = new SkillCard("77"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3"  ,"-1"  ,"5");
        card2 = new SkillCard("78"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3"  );
        card3 = new SkillCard("79"  ,"Slinky Tank"  ,"EARTH"  ,"Stone vehicles provided both transportation and protection during the invasion of the Fire Nation."  ,"src/res/image/Slinky Tank.png"  ,"3","5");

        card1.show();
        card2.show();
        card3.show();

        card1.doEffect();
        card2.doEffect();
        card3.doEffect();
    }

    public static void main(String[] args) {
        System.out.println("Testing Backend");
//        Main.testLoader();
//        Main.testCard();
//        Main.testSkillCard();
        Main.testDeck();
    }
}
