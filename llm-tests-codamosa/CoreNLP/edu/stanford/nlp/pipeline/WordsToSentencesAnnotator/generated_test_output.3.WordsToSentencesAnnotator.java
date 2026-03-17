import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(SentencesAnnotation.class, SentenceIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.newlineSplitter("\n");
    assertNotNull(annotator);
}

@Test
public void test3()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    token1.setOriginalText("This");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    token2.setOriginalText("is");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    token3.setOriginalText("a");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("test");
    token4.setOriginalText("test");
    CoreLabel token5 = new CoreLabel();
    token5.setWord(".");
    token5.setOriginalText(".");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    tokens.add(token5);
    Annotation annotation = new Annotation("");
    annotation.set(TokensAnnotation.class, tokens);
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.nonSplitter();
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(sentences);
    assertEquals(1, sentences.size());
    List<CoreLabel> sentenceTokens = sentences.get(0).get(TokensAnnotation.class);
    assertEquals(tokens, sentenceTokens);
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Hello world.");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.setOriginalText("Hello");
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token1.set(CharacterOffsetEndAnnotation.class, 5);
    token1.set(BeforeAnnotation.class, "");
    token1.set(AfterAnnotation.class, " ");
    token1.setNewline(false);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    token2.setOriginalText("world");
    token2.set(CharacterOffsetBeginAnnotation.class, 6);
    token2.set(CharacterOffsetEndAnnotation.class, 11);
    token2.set(BeforeAnnotation.class, " ");
    token2.set(AfterAnnotation.class, "");
    token2.setNewline(false);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(".");
    token3.setOriginalText(".");
    token3.set(CharacterOffsetBeginAnnotation.class, 11);
    token3.set(CharacterOffsetEndAnnotation.class, 12);
    token3.set(BeforeAnnotation.class, "");
    token3.set(AfterAnnotation.class, "");
    token3.setNewline(false);
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    annotation.set(TextAnnotation.class, "Hello world.");
    annotation.set(TokensAnnotation.class, tokens);
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(sentences);
    assertEquals(1, sentences.size());
    CoreMap sentence = sentences.get(0);
    String sentenceText = sentence.get(TextAnnotation.class);
    assertEquals("Hello world.", sentenceText);
}

