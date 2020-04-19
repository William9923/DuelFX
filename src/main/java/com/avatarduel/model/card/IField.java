package com.avatarduel.model.card;

/**
 * IField is an interface that will used for card which will used in field.
 * @author G10-K03-CardGameOOP
 */

public interface IField {
    int getIndex();
    Card getCard();
    int getCreatedAtTurn();
}
