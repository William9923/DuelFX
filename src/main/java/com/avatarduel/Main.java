package com.avatarduel;

import com.avatarduel.command.EndTurnEvent;
import com.avatarduel.command.IEvent;
import com.avatarduel.exception.InvalidOperationException;
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


    public static void main(String[] args)  {
        System.out.println("Testing Backend");

        try{
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
