import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator("tokenize");
    Set<Class<? extends CoreAnnotations>> result = annotator.requirementsSatisfied();
    assertEquals(2, result.size());
    assertTrue(result.contains(SentencesAnnotation.class));
    assertTrue(result.contains(SentenceIndexAnnotation.class));
}

@Test
public void test2()
{
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.newlineSplitter("\n");
    Field field = WordsToSentencesAnnotator.class.getDeclaredField("wts");
    field.setAccessible(true);
    Object wtsProcessor = field.get(annotator);
    assertTrue(wtsProcessor instanceof WordToSentenceProcessor);
    Field boundaryField = WordToSentenceProcessor.class.getDeclaredField("boundaryTokenStrings");
    boundaryField.setAccessible(true);
    Object boundaryTokens = boundaryField.get(wtsProcessor);
    assertTrue(boundaryTokens instanceof Set);
    assertTrue(((Set<?>) (boundaryTokens)).contains("\n"));
}

@Test
public void test3()
{
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("!");
    tokens.add(token3);
    Annotation annotation = new Annotation("");
    annotation.set(TokensAnnotation.class, tokens);
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.nonSplitter();
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull("Sentences annotation should not be null", sentences);
    assertEquals("There should be exactly one sentence", 1, sentences.size());
    List<CoreLabel> sentenceTokens = sentences.get(0).get(TokensAnnotation.class);
    assertEquals("Sentence should contain all original tokens", tokens.size(), sentenceTokens.size());
    assertEquals("First token should match", "Hello", sentenceTokens.get(0).word());
    assertEquals("Second token should match", "world", sentenceTokens.get(1).word());
    assertEquals("Third token should match", "!", sentenceTokens.get(2).word());
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Hello world.");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.set(TextAnnotation.class, "Hello");
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token1.set(CharacterOffsetEndAnnotation.class, 5);
    token1.set(BeforeAnnotation.class, "");
    token1.set(AfterAnnotation.class, " ");
    token1.set(OriginalTextAnnotation.class, "Hello");
    token1.setNewline(false);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    token2.set(TextAnnotation.class, "world");
    token2.set(CharacterOffsetBeginAnnotation.class, 6);
    token2.set(CharacterOffsetEndAnnotation.class, 11);
    token2.set(BeforeAnnotation.class, " ");
    token2.set(AfterAnnotation.class, "");
    token2.set(OriginalTextAnnotation.class, "world");
    token2.setNewline(false);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(".");
    token3.set(TextAnnotation.class, ".");
    token3.set(CharacterOffsetBeginAnnotation.class, 11);
    token3.set(CharacterOffsetEndAnnotation.class, 12);
    token3.set(BeforeAnnotation.class, "");
    token3.set(AfterAnnotation.class, "");
    token3.set(OriginalTextAnnotation.class, ".");
    token3.setNewline(false);
    tokens.add(token3);
    annotation.set(TokensAnnotation.class, tokens);
    annotation.set(TextAnnotation.class, "Hello world.");
    WordsToSentencesAnnotator wtsa = new WordsToSentencesAnnotator(false);
    wtsa.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(sentences);
    assertEquals(1, sentences.size());
    CoreMap sentence = sentences.get(0);
    assertEquals("Hello world.", sentence.get(TextAnnotation.class));
    List<CoreLabel> sentenceTokens = sentence.get(TokensAnnotation.class);
    assertEquals(3, sentenceTokens.size());
    assertEquals("Hello", sentenceTokens.get(0).word());
    assertEquals("world", sentenceTokens.get(1).word());
    assertEquals(".", sentenceTokens.get(2).word());
}

