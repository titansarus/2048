package ProgramExceptions;

import ViewFXML.ConstantMessages;

public class InvalidPasswordException extends MyExceptions {
    public InvalidPasswordException() {
        super(ConstantMessages.INVALID_PASSWORD.getMessage());
    }
}
