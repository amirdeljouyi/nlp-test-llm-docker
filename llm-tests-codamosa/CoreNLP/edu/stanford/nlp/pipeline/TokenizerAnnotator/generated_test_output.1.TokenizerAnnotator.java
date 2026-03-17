import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String text = "Hello, world!";
    Reader reader = new StringReader(text);
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.factory(CoreLabel.class);
    TokenizerAnnotator annotator = new TokenizerAnnotator(false, factory);
    Tokenizer<CoreLabel> tokenizer = annotator.getTokenizer(reader);
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(3, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals(",", tokens.get(1).word());
    assertEquals("world", tokens.get(2).word());
}

@Test
public void test2()
{
    TokenizerAnnotator annotator = new TokenizerAnnotator(false, "en");
    Set<Class<? extends CoreAnnotations>> result = annotator.requirementsSatisfied();
    assertEquals(16, result.size());
    assertTrue(result.contains(TextAnnotation.class));
    assertTrue(result.contains(TokensAnnotation.class));
    assertTrue(result.contains(CharacterOffsetBeginAnnotation.class));
    assertTrue(result.contains(CharacterOffsetEndAnnotation.class));
    assertTrue(result.contains(BeforeAnnotation.class));
    assertTrue(result.contains(AfterAnnotation.class));
    assertTrue(result.contains(TokenBeginAnnotation.class));
    assertTrue(result.contains(TokenEndAnnotation.class));
    assertTrue(result.contains(PositionAnnotation.class));
    assertTrue(result.contains(IndexAnnotation.class));
    assertTrue(result.contains(OriginalTextAnnotation.class));
    assertTrue(result.contains(ValueAnnotation.class));
    assertTrue(result.contains(IsNewlineAnnotation.class));
    assertTrue(result.contains(SentencesAnnotation.class));
    assertTrue(result.contains(SentenceIndexAnnotation.class));
}

@Test
public void test3()
{
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token = new CoreLabel();
    token.setWord("example");
    token.set(AfterAnnotation.class, " ");
    tokens.add(token);
    TokenizerAnnotator.adjustFinalToken(tokens);
    String after = tokens.get(0).get(AfterAnnotation.class);
    assertEquals("", after);
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Hello world.");
    TokenizerAnnotator tokenizerAnnotator = new TokenizerAnnotator(false, PropertiesUtils.asProperties("tokenize.language", "en"));
    tokenizerAnnotator.annotate(annotation);
    List<CoreLabel> tokens = annotation.get(TokensAnnotation.class);
    assertNotNull(tokens);
    assertEquals(3, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals("world", tokens.get(1).word());
    assertEquals(".", tokens.get(2).word());
}

