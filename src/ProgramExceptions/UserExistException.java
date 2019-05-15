package ProgramExceptions;

public class UserExistException extends MyExceptions {
    public UserExistException()
    {
        super("A User Exists with this name");
    }
}
