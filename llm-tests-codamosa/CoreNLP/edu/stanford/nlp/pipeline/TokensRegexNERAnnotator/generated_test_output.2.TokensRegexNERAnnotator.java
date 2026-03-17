import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    TokensRegexNERAnnotator annotator = new TokensRegexNERAnnotator("tokensregex.rules", false);
    Annotation annotation = new Annotation("Example input");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token = new CoreLabel();
    token.setWord("Stanford");
    tokens.add(token);
    annotation.set(TokensAnnotation.class, tokens);
    annotator.annotate(annotation);
    assertNotNull(annotation.get(TokensAnnotation.class));
}

