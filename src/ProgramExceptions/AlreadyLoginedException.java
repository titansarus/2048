package ProgramExceptions;

import View.ConstantMessages;

public class AlreadyLoginedException extends  MyExceptions{
    public AlreadyLoginedException() {
        super(ConstantMessages.ALREADY_LOGIND.getMessage());
    }
}
