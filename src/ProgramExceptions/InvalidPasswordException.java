package ProgramExceptions;

import View.ConstantMessages;

public class InvalidPasswordException extends MyExceptions {
    public InvalidPasswordException() {
        super(ConstantMessages.INVALID_PASSWORD.getMessage());
    }
}
