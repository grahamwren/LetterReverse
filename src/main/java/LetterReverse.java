import java.util.Stack;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function object for reversing only the letters in a String
 */
public class LetterReverse implements Function<String, String> {
  /**
   * Reverse the letters in a Unicode {@link String}, Mark characters are considered
   * part of the letter they modify
   * @param s the String that will be letter reversed
   * @return the input string with the letters reversed
   */
  @Override
  public String apply(String s) {
    StringBuilder result = new StringBuilder(s);
    Stack<String> letters = new Stack<>();

    // Build matcher to match any letter character then any number of mark characters
    Matcher matcher = Pattern.compile("\\p{L}\\p{M}*").matcher(s);

    // Push all matches onto FILO stack
    matcher.results().map(MatchResult::group).forEach(letters::push);

    // Reset the matcher to the beginning of the string
    matcher.reset();

    // Run through string, replacing letter characters with the first on the stack
    for (int offset = 0; matcher.find();) {
      int i_start = matcher.start() + offset,
          i_end   = matcher.end() + offset;
      String pop = letters.pop();
      result.replace(i_start, i_end, pop);
      // Compute the offset that will be necessary to index into the result String
      offset += pop.length() - matcher.group().length();
    }
    return result.toString();
  }
}
