import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String inputText = "Stanford NLP is powerful.";
    Reader reader = new StringReader(inputText);
    TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(false, false);
    TokenizerAnnotator annotator = new TokenizerAnnotator(true, tokenizerFactory);
    Tokenizer<CoreLabel> tokenizer = annotator.getTokenizer(reader);
    assertNotNull(tokenizer);
    assertTrue(tokenizer.hasNext());
    CoreLabel token1 = tokenizer.next();
    assertEquals("Stanford", token1.word());
    assertTrue(tokenizer.hasNext());
    CoreLabel token2 = tokenizer.next();
    assertEquals("NLP", token2.word());
    assertTrue(tokenizer.hasNext());
    CoreLabel token3 = tokenizer.next();
    assertEquals("is", token3.word());
    assertTrue(tokenizer.hasNext());
    CoreLabel token4 = tokenizer.next();
    assertEquals("powerful", token4.word());
    assertTrue(tokenizer.hasNext());
    CoreLabel token5 = tokenizer.next();
    assertEquals(".", token5.word());
    assertFalse(tokenizer.hasNext());
}

@Test
public void test2()
{
    TokenizerAnnotator annotator = new TokenizerAnnotator(false, "");
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
    Annotation annotation = new Annotation("This is a test.");
    Properties props = PropertiesUtils.asProperties("tokenize.language", "en");
    TokenizerAnnotator tokenizerAnnotator = new TokenizerAnnotator(false, props);
    tokenizerAnnotator.annotate(annotation);
    List<CoreLabel> tokens = annotation.get(TokensAnnotation.class);
    assertNotNull("TokensAnnotation should not be null after annotation", tokens);
    assertEquals("Token count does not match expected", 5, tokens.size());
    assertEquals("First token text mismatch", "This", tokens.get(0).word());
    assertEquals("Second token text mismatch", "is", tokens.get(1).word());
    assertEquals("Third token text mismatch", "a", tokens.get(2).word());
    assertEquals("Fourth token text mismatch", "test", tokens.get(3).word());
    assertEquals("Fifth token text mismatch", ".", tokens.get(4).word());
}

