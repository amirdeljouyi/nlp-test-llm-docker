import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(SentencesAnnotation.class));
    assertTrue(result.contains(SentenceIndexAnnotation.class));
}

@Test
public void test2()
{
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    token1.set(TextAnnotation.class, "This");
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    token2.set(TextAnnotation.class, "is");
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("\n");
    token3.set(TextAnnotation.class, "\n");
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("a");
    token4.set(TextAnnotation.class, "a");
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("test");
    token5.set(TextAnnotation.class, "test");
    tokens.add(token5);
    Annotation annotation = new Annotation("");
    annotation.set(TokensAnnotation.class, tokens);
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.newlineSplitter("\n");
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertEquals(2, sentences.size());
    assertEquals("This is", sentenceToText(sentences.get(0)));
    assertEquals("a test", sentenceToText(sentences.get(1)));
}

@Test
public void test3()
{
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.nonSplitter();
    Annotation annotation = new Annotation("");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    token1.setValue("This");
    token1.setIndex(1);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    token2.setValue("is");
    token2.setIndex(2);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    token3.setValue("a");
    token3.setIndex(3);
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("test");
    token4.setValue("test");
    token4.setIndex(4);
    tokens.add(token4);
    annotation.set(TokensAnnotation.class, tokens);
    annotator.annotate(annotation);
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(sentences);
    assertEquals(1, sentences.size());
    List<CoreLabel> sentenceTokens = sentences.get(0).get(TokensAnnotation.class);
    assertNotNull(sentenceTokens);
    assertEquals(4, sentenceTokens.size());
    assertEquals("This", sentenceTokens.get(0).word());
    assertEquals("is", sentenceTokens.get(1).word());
    assertEquals("a", sentenceTokens.get(2).word());
    assertEquals("test", sentenceTokens.get(3).word());
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Hello world.");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.set(TextAnnotation.class, "Hello");
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token1.set(CharacterOffsetEndAnnotation.class, 5);
    token1.set(OriginalTextAnnotation.class, "Hello");
    token1.set(BeforeAnnotation.class, "");
    token1.set(AfterAnnotation.class, " ");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    token2.set(TextAnnotation.class, "world");
    token2.set(CharacterOffsetBeginAnnotation.class, 6);
    token2.set(CharacterOffsetEndAnnotation.class, 11);
    token2.set(OriginalTextAnnotation.class, "world");
    token2.set(BeforeAnnotation.class, " ");
    token2.set(AfterAnnotation.class, "");
    CoreLabel token3 = new CoreLabel();
    token3.setWord(".");
    token3.set(TextAnnotation.class, ".");
    token3.set(CharacterOffsetBeginAnnotation.class, 11);
    token3.set(CharacterOffsetEndAnnotation.class, 12);
    token3.set(OriginalTextAnnotation.class, ".");
    token3.set(BeforeAnnotation.class, "");
    token3.set(AfterAnnotation.class, "");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    annotation.set(TextAnnotation.class, "Hello world.");
    annotation.set(TokensAnnotation.class, tokens);
    WordsToSentencesAnnotator splitter = new WordsToSentencesAnnotator("tokenize", false);
    splitter.annotate(annotation);
    assertTrue(annotation.containsKey(SentencesAnnotation.class));
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertEquals(1, sentences.size());
    CoreMap sentence = sentences.get(0);
    List<CoreLabel> sentenceTokens = sentence.get(TokensAnnotation.class);
    assertEquals(3, sentenceTokens.size());
    assertEquals("Hello", sentenceTokens.get(0).word());
    assertEquals("world", sentenceTokens.get(1).word());
    assertEquals(".", sentenceTokens.get(2).word());
    Integer sentenceBegin = sentence.get(CharacterOffsetBeginAnnotation.class);
    Integer sentenceEnd = sentence.get(CharacterOffsetEndAnnotation.class);
    assertEquals(Integer.valueOf(0), sentenceBegin);
    assertEquals(Integer.valueOf(12), sentenceEnd);
}


