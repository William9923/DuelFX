# IF2210 Project Template
## Semester 2 Tahun 2019/2020

## Kelompok 10 / K03
* 13518090 / Arthur Edgar Yunanto
* 13518108 / Vincent Hasiholan
* 13518111 / Muhamad Mirza Fathan Al Arsyad
* 13518114 / Mario Gunawan
* 13518138 / William

## Code Structure
CardGameOOP
├───src
├───main
    ├───java
    |   └───com.avatarduel
            └───dao
                └───CardDAO.java
                └───CSVCardDAO.java
            └───event
                └───ActivateDestroyEvent.java
                └───ActivateSkillEvent.java
                └───AttackEvent.java
                └───ChangePositionEvent.java
                └───DirectAttackEvent.java
                └───DrawEvent.java
                └───EndTurnEvent.java
                └───IEvent.java
                └───NextPhaseEvent.java
                └───PlayLandCardEvent.java
                └───RemoveSkillCardEvent.java
                └───SummonEvent.java
            └───exception
                └───ExceptionCause
                    └───AttackCause.java
                    └───AttackOnTheCreatedTurncause.java
                    └───ExceptionCause.java
                    └───FullBoardCause.java
                    └───InvalidPhaseCause.java
                    └───InvalidTargetCause.java
                    └───MultipleAttackOnTheSameTurnCause.java
                    └───MultipleLandCardPlayedOnTheSameTurnCause.java
                    └───NoCharacterCardInFieldCause.java
                    └───NoCharactercardToDestroyCause.java
                    └───NotEnoughPowerCause.java
                └───EmptyFieldException.java
                └───InvalidAttackException.java
                └───InvalidOperationException.java
                └───InvalidPhaseException.java
                └───InvalidTargetException.java
                └───NotEnoughPowerException.java
                └───NotEnoughSpaceException.java
                └───UniquePlayCardException.java
            └───factory
                └───CardFactory.java
                └───CardInFieldFactory.java
            └───guicontroller
                └───Board
                    └───BoardController.java
                    └───DeckController.java
                    └───FieldController.java
                    └───GameStatusController.java
                    └───HandController.java
                    └───PlayerStatusController.java
                └───Card
                    └───CardController.java
                    └───CardInFieldController.java
                    └───CardInHandController.java
                    └───CharacterCardInFieldController.java
                    └───DisplayCardController.java
                    └───SkillCardInFieldController.java
                └───MainMenu
                    └───CardLibraryController.java
                    └───HowToPlayController.java
                    └───MainMenuController.java
                └───RenderQuest
                    └───AttackRequest.java
                    └───ChangeTurnRenderRequest.java
                    └───CheckWinRequest.java
                    └───DeckDrawAndRenderRequest.java
                    └───DeckRenderRequest.java
                    └───FieldRenderRequest.java
                    └───GameStatusRenderRequest.java
                    └───HandRenderRequest.java
                    └───PlayerStatusRenderRequest.java
                    └───RenderRequest.java
                    └───ShowSelectedCardRequest.java
            └───model
                └───card
                └───player_component
                └───type
                └───Game.java
            └───phase
                └───BattlePhase.java
                └───DrawPhase.java
                └───EndPhase.java
                └───IPhase.java
                └───MainPhase.java
            └───util
                └───CSVReader.java
                └───Loader.java
            └───AvatarDuel.java
            └───Main.java
    └───resources
    |   └───com.avatarduel
           └───card
                └───border
                    └───air.jpg
                    └───fire.jpg
                    └───water.jpg
                └───data
                    └───character.csv
                    └───land.csv
                    └───skill_aura.csv
                    └───skill_destroy.csv
                    └───skill_power_up.csv
                └───icon
                └───image
            └───character
                └───kira_yoshikage.png
                └───Light_Yagami.png
            └───GUI
                └───Board
                └───Card
                └───Main Menu
                └───Popup
            └───music
                └───main_menu_song.mp3
└───test
    └───java
        └───com.avatarduel
            └───dao
            └───event
            └───exception
            └───factory
            └───model
            └───phase
            └───util
                └───LoaderTest.java

## How To Compile & Run

### Requirements
1. Download and Install Java 8 (JDK 1.8) [here](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

### How to Run
Here is an example of project using gradle as the build tools.
Try running these commands in your terminals : <br>
Linux : <br>
```bash
./gradlew run
```
Windows : <br>
```bash
gradlew run
```

You will notice that it will open a window that display 'Avatar Duel'.
In the command line you can see the data that is being read by `CSVReader.java`

What happen is when you use `./gradlew run`, it will start the main function in your app.
For this app, the main function lives in `AvatarDuel.java`.

You can explore more about gradle [here](https://guides.gradle.org/creating-new-gradle-builds/)

## Credit
All images and description are taken from [Avatar Wikia](https://avatar.fandom.com/wiki/Avatar_Wiki)
