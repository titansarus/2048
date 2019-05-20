package ViewFXML;

public enum ConstantMessages {
    USER_CREATED("User Created with this Username: "),
    NO_USER_EXIST("No user exists with username: "), INVALID_PASSWORD("Invalid Password"),
    ALREADY_LOGIND("This account is already logined!"),
    INVALID_BOARDSIZE("Enter a valid number between 4 to 10"),
    LOGIN_SUCCESSFUL("You successfully logined");

    private String message;

    ConstantMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
