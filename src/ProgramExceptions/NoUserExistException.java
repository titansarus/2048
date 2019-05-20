package ProgramExceptions;

import ViewFXML.ConstantMessages;

public class NoUserExistException extends  MyExceptions {
    public NoUserExistException()
    {
        super(ConstantMessages.NO_USER_EXIST.getMessage());
    }
}
