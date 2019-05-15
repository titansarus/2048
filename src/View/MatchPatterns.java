package View;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MatchPatterns {
    public static final int CREATE_ACCOUNT=0;
    public static ArrayList<Pattern> patterns = new ArrayList<>();
    {
        patterns.add(Pattern.compile("(?i)create account"));
    }
}
