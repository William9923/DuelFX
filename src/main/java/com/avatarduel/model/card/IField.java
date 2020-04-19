package com.avatarduel.model.card;

/**
 * IField is an interface that will used for card which will used in field.
 * @author G10-K03-CardGameOOP
 */

public interface IField {
    public int getIndex();
    public Card getCard();
    public int getCreatedAtTurn();
}
