package ProgramExceptions;

public class NoLoginedAccountException extends MyExceptions {
    public NoLoginedAccountException()
    {
        super("No Account is logined.");
    }
}
