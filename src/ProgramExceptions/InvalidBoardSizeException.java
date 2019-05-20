package ProgramExceptions;

import ViewFXML.ConstantMessages;

public class InvalidBoardSizeException extends MyExceptions {
    public InvalidBoardSizeException() { super(ConstantMessages.INVALID_BOARDSIZE.getMessage()); }
}
