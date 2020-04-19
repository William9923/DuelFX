package com.avatarduel.exception;

import com.avatarduel.exception.ExceptionCause.ExceptionCause;

/**
 * Exception because the player is trying to remove a skill card
 */
public class InvalidRemoveSkillcardException extends InvalidOperationException {
    public InvalidRemoveSkillcardException(ExceptionCause cause) {
        super("Invalid Remove Skil Card", cause);
    }
}
