import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String inputText = "Stanford NLP is great.";
    Reader reader = new StringReader(inputText);
    TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(CoreLabel.class);
    TokenizerAnnotator tokenizerAnnotator = new TokenizerAnnotator(false, tokenizerFactory);
    Tokenizer<CoreLabel> tokenizer = tokenizerAnnotator.getTokenizer(reader);
    assertNotNull("Tokenizer should not be null", tokenizer);
    assertTrue("Returned object should be an instance of Tokenizer", tokenizer instanceof Tokenizer);
}

@Test
public void test2()
{
    TokenizerAnnotator annotator = new TokenizerAnnotator("en");
    Set<Class<? extends CoreAnnotations>> expected = new HashSet<Class<? extends CoreAnnotations>>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class, IsNewlineAnnotation.class, SentencesAnnotation.class, SentenceIndexAnnotation.class));
    Set<Class<? extends CoreAnnotations>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test3()
{
    CoreLabel token = new CoreLabel();
    token.set(AfterAnnotation.class, "  ");
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    tokens.add(token);
    TokenizerAnnotator.adjustFinalToken(tokens);
    assertEquals(" ", token.get(AfterAnnotation.class));
}

@Test
public void test4()
{
    Properties props = PropertiesUtils.asProperties("annotators", "tokenize");
    TokenizerAnnotator tokenizerAnnotator = new TokenizerAnnotator(false, props);
    Annotation annotation = new Annotation("Hello, Stanford NLP!");
    tokenizerAnnotator.annotate(annotation);
    List<?> tokens = annotation.get(TokensAnnotation.class);
    assertNotNull("Token list should not be null", tokens);
    assertEquals("Expected number of tokens", 4, tokens.size());
    assertEquals("First token should be 'Hello'", "Hello", tokens.get(0).toString().split("\\b")[0].trim());
    assertEquals("Second token should be ','", ",", tokens.get(1).toString().trim());
    assertEquals("Third token should be 'Stanford'", "Stanford", tokens.get(2).toString().split("\\b")[0].trim());
    assertEquals("Fourth token should be 'NLP!'", "NLP", tokens.get(3).toString().split("\\b")[0].trim());
}

