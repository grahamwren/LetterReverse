import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LetterReverseTest {

  @Parameters
  public static Collection<String[]> testData() {
    return Arrays.asList(new String[][] {
            // Simple cases
            {"1a2b", "1b2a"},
            {"", ""},
            {"13213213", "13213213"},
            {"abcdefg", "gfedcba"},
            // Test simple unicode switch, no length change
            // Unicode: [Letter1, Symbol, Letter2, Format] => [Letter2, Symbol, Letter1, Format]
            {"\u01FF\u00A5\u05D6\u0604", "\u05D6\u00A5\u01FF\u0604"},
            // Test unicode with length change when removing a long string and inserting a short one
            // Unicode: [Letter1, Mark, Symbol, Letter2] => [Letter2, Symbol, Letter1, Mark]
            {"\u01FF\u0305\u00A5\u05D6", "\u05D6\u00A5\u01FF\u0305"},
            // Test unicode with large length change
            // Unicode: [Letter1, Mark1, Mark2, Mark3, Symbol, Letter2] => [Letter2, Symbol, Letter1, Mark1, Mark2, Mark3]
            {"\u01FF\u0305\u030C\u0314\u00A5\u05D6", "\u05D6\u00A5\u01FF\u0305\u030C\u0314"},
    });
  }

  private String input;
  private String output;

  public LetterReverseTest(String input, String output) {
    this.input = input;
    this.output = output;
  }

  @Test
  public void testFunction() {
    assertEquals(output, new LetterReverse().apply(input));
  }
}