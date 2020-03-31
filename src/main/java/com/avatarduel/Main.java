package com.avatarduel;

import com.avatarduel.Loader;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Loader loader = new Loader();
        List<String[]> characterCardList;
        characterCardList = loader.loadCharacter();

        List<String[]> landCardList;
        landCardList = loader.loadLand();

        List<String[]> skillCardList;
        skillCardList = loader.loadSkill();

        System.out.println("Character Card List : ");
        loader.printLoadResult(characterCardList);

        System.out.println("Land Card List");
        loader.printLoadResult(landCardList);

        System.out.println("Skill Card List");
        loader.printLoadResult(skillCardList);

    }
}
