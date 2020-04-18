package com.avatarduel.exception.ExceptionCause;

import com.avatarduel.model.type.Element;

public class NotEnoughPowerCause implements ExceptionCause {
    private Element element;

    public NotEnoughPowerCause(Element element) {
        this.element = element;
    }

    @Override
    public String getCause() {
        return "not enough " + element.toString().toLowerCase() + " power";
    }
}
