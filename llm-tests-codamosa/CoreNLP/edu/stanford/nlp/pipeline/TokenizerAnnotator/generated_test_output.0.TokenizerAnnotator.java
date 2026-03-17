import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String inputText = "Stanford NLP is great.";
    Reader reader = new StringReader(inputText);
    TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
    TokenizerAnnotator annotator = new TokenizerAnnotator(false, tokenizerFactory);
    Tokenizer<CoreLabel> tokenizer = annotator.getTokenizer(reader);
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertNotNull("Tokenizer should not be null", tokenizer);
    assertEquals("Expected 5 tokens", 5, tokens.size());
    assertEquals("First token should be 'Stanford'", "Stanford", tokens.get(0).word());
    assertEquals("Second token should be 'NLP'", "NLP", tokens.get(1).word());
    assertEquals("Third token should be 'is'", "is", tokens.get(2).word());
    assertEquals("Fourth token should be 'great'", "great", tokens.get(3).word());
    assertEquals("Fifth token should be '.'", ".", tokens.get(4).word());
}

@Test
public void test2()
{
    TokenizerAnnotator tokenizerAnnotator = new TokenizerAnnotator("en");
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>();
    expected.add(TextAnnotation.class);
    expected.add(TokensAnnotation.class);
    expected.add(CharacterOffsetBeginAnnotation.class);
    expected.add(CharacterOffsetEndAnnotation.class);
    expected.add(BeforeAnnotation.class);
    expected.add(AfterAnnotation.class);
    expected.add(TokenBeginAnnotation.class);
    expected.add(TokenEndAnnotation.class);
    expected.add(PositionAnnotation.class);
    expected.add(IndexAnnotation.class);
    expected.add(OriginalTextAnnotation.class);
    expected.add(ValueAnnotation.class);
    expected.add(IsNewlineAnnotation.class);
    expected.add(SentencesAnnotation.class);
    expected.add(SentenceIndexAnnotation.class);
    Set<Class<? extends CoreAnnotation>> actual = tokenizerAnnotator.requirementsSatisfied();
    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
}

@Test
public void test3()
{
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    CoreLabel token = new CoreLabel();
    token.set(AfterAnnotation.class, " ");
    tokens.add(token);
    TokenizerAnnotator.adjustFinalToken(tokens);
    assertEquals("", tokens.get(0).get(AfterAnnotation.class));
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Stanford NLP is great.");
    tokenizerAnnotator.annotate(annotation);
    List<CoreLabel> tokens = annotation.get(TokensAnnotation.class);
    assertNotNull("Tokens should not be null", tokens);
    assertEquals("Unexpected number of tokens", 5, tokens.size());
    assertEquals("First token text mismatch", "Stanford", tokens.get(0).word());
    assertEquals("Second token text mismatch", "NLP", tokens.get(1).word());
    assertEquals("Third token text mismatch", "is", tokens.get(2).word());
    assertEquals("Fourth token text mismatch", "great", tokens.get(3).word());
    assertEquals("Fifth token text mismatch", ".", tokens.get(4).word());
}

