import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator("default");
    Set<Class<? extends CoreAnnotations>> result = annotator.requirementsSatisfied();
    assertEquals(2, result.size());
    assertTrue(result.contains(SentencesAnnotation.class));
    assertTrue(result.contains(SentenceIndexAnnotation.class));
}

@Test
public void test2()
{
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.newlineSplitter("\n");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.setOriginalText("Hello");
    token1.setBeginPosition(0);
    token1.setEndPosition(5);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("\n");
    token2.setOriginalText("\n");
    token2.setBeginPosition(5);
    token2.setEndPosition(6);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("World");
    token3.setOriginalText("World");
    token3.setBeginPosition(6);
    token3.setEndPosition(11);
    tokens.add(token3);
    List<List<CoreLabel>> sentences = annotator.getProcessor().process(tokens);
    assertEquals(2, sentences.size());
    assertEquals(1, sentences.get(0).size());
    assertEquals("Hello", sentences.get(0).get(0).word());
    assertEquals(1, sentences.get(1).size());
    assertEquals("World", sentences.get(1).get(0).word());
}

@Test
public void test3()
{
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("test");
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord(".");
    tokens.add(token5);
    Annotation annotation = new Annotation("");
    annotation.set(TokensAnnotation.class, tokens);
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.nonSplitter();
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull("Sentences should not be null", sentences);
    assertEquals("There should be exactly one sentence", 1, sentences.size());
    List<CoreLabel> sentenceTokens = sentences.get(0).get(TokensAnnotation.class);
    assertNotNull("Sentence tokens should not be null", sentenceTokens);
    assertEquals("Sentence should contain all original tokens", tokens.size(), sentenceTokens.size());
}

@Test
public void test4()
{
    RedwoodConfiguration.empty().capture(System.err).apply();
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator(false);
    String text = "This is a test sentence.";
    Annotation annotation = new Annotation(text);
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token1.set(CharacterOffsetEndAnnotation.class, 4);
    token1.set(OriginalTextAnnotation.class, "This");
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    token2.set(CharacterOffsetBeginAnnotation.class, 5);
    token2.set(CharacterOffsetEndAnnotation.class, 7);
    token2.set(OriginalTextAnnotation.class, "is");
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    token3.set(CharacterOffsetBeginAnnotation.class, 8);
    token3.set(CharacterOffsetEndAnnotation.class, 9);
    token3.set(OriginalTextAnnotation.class, "a");
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("test");
    token4.set(CharacterOffsetBeginAnnotation.class, 10);
    token4.set(CharacterOffsetEndAnnotation.class, 14);
    token4.set(OriginalTextAnnotation.class, "test");
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("sentence");
    token5.set(CharacterOffsetBeginAnnotation.class, 15);
    token5.set(CharacterOffsetEndAnnotation.class, 23);
    token5.set(OriginalTextAnnotation.class, "sentence");
    tokens.add(token5);
    CoreLabel token6 = new CoreLabel();
    token6.setWord(".");
    token6.set(CharacterOffsetBeginAnnotation.class, 23);
    token6.set(CharacterOffsetEndAnnotation.class, 24);
    token6.set(OriginalTextAnnotation.class, ".");
    tokens.add(token6);
    annotation.set(TokensAnnotation.class, tokens);
    annotation.set(TextAnnotation.class, text);
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(sentences);
    assertEquals(1, sentences.size());
    CoreMap sentence = sentences.get(0);
    String sentenceText = sentence.get(TextAnnotation.class);
    assertEquals("This is a test sentence.", sentenceText);
    List<CoreLabel> sentenceTokens = sentence.get(TokensAnnotation.class);
    assertEquals(6, sentenceTokens.size());
    assertEquals("This", sentenceTokens.get(0).word());
    assertEquals(".", sentenceTokens.get(5).word());
}

