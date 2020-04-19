package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to activate a skill card
 */
public class InvalidSkillActivationException extends InvalidPlayCardException {
    public InvalidSkillActivationException(ExceptionCause cause) {
        super("Invalid Activation of Skill Card", cause);
    }
}
