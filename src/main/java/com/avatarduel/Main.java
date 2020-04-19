package com.avatarduel;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.player_component.Deck;
import com.avatarduel.model.type.PlayerType;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void testDeck() {
        Deck deck = new Deck(60, PlayerType.A);
        List<Integer> integerList = new ArrayList<>();
        for (Card card : deck){
            if (integerList.contains(card.getId())){
                System.out.println("ID Duplicate : " + card.getId());
                System.out.println("Card Name Duplicate : " + card.getName());
            } else {
                integerList.add(card.getId());
            }
        }
    }

    public static void main(String[] args)  {
        System.out.println("Testing Backend");

        try{
            testDeck();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
