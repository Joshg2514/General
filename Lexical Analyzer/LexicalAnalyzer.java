
package lexical;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *
 * @author Joshg
 */
public class LexicalAnalyzer {

    public static void main(String[] args) throws IOException {
    
    String path = "testcase.txt";

        try {
            // default StandardCharsets.UTF_8
            String content = Files.readString(Paths.get(path));
            Lexer Analyzer = new Lexer();
            Analyzer.Tokenize(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

}
  // Do something with your character
}

    

