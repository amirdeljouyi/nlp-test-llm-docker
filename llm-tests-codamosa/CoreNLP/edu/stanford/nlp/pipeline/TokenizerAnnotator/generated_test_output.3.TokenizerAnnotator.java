import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    TokenizerAnnotator annotator = new TokenizerAnnotator(false, "en");
    Set<Class<? extends CoreAnnotations>> expected = new HashSet<>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class, IsNewlineAnnotation.class, SentencesAnnotation.class, SentenceIndexAnnotation.class));
    Set<Class<? extends CoreAnnotations>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Properties props = PropertiesUtils.asProperties("annotators", "tokenize");
    TokenizerAnnotator tokenizerAnnotator = new TokenizerAnnotator(false, props);
    String inputText = "Hello Stanford!";
    Annotation annotation = new Annotation(inputText);
    annotation.set(TextAnnotation.class, inputText);
    tokenizerAnnotator.annotate(annotation);
    List<CoreLabel> tokens = annotation.get(TokensAnnotation.class);
    assertNotNull("Tokens should not be null", tokens);
    assertEquals("Expected number of tokens", 3, tokens.size());
    assertEquals("First token text mismatch", "Hello", tokens.get(0).originalText());
    assertEquals("Second token text mismatch", "Stanford", tokens.get(1).originalText());
    assertEquals("Third token text mismatch", "!", tokens.get(2).originalText());
}


