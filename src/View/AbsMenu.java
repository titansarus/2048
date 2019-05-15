package View;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.ScannerStatic.scanner;

public abstract class AbsMenu {

    public abstract void getInput();

    public void getInputFromPattern(ArrayList<Pattern> patterns)
    {
        boolean isMatched = false;
        String s = scanner.nextLine();
        for (int i = 0; i < patterns.size(); i++) {
            Matcher matcher = patterns.get(i).matcher(s);
            if (matcher.matches()) {
                isMatched = true;
                functionDeterminer(matcher, i);
            }
        }
        if (!isMatched)
        {
            showInvalid();
        }
    }

    public abstract void showMenu();

    public abstract void showHelp();

    public abstract void functionDeterminer(Matcher matcher, int i);

    public void showInvalid() {
        System.out.println(ConstantMessages.INVALID_COMMAND.getMessage());
    }

    public static void notificationPrinter(String message)
    {
        System.out.println(message);
    }

    public static void errorPrinter(Exception e)
    {
        System.out.println(e.getMessage());
    }

}
