package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.Element;

/**
 * Defines an exception cause, caused by not enough power
 */
public class NotEnoughPowerCause implements ExceptionCause {
    /**
     * Element used to specify what element is not enough to do an action
     */
    private Element element;

    /**
     * @param element initialize private Element element
     */
    public NotEnoughPowerCause(Element element) {
        this.element = element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCause() {
        return "not enough " + element.toString().toLowerCase() + " element power";
    }
}
