import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator("ssplit");
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Assert.assertEquals(2, result.size());
    Assert.assertTrue(result.contains(SentencesAnnotation.class));
    Assert.assertTrue(result.contains(SentenceIndexAnnotation.class));
}

@Test
public void test2()
{
    String newlineToken = "\n";
    WordsToSentencesAnnotator annotator = WordsToSentencesAnnotator.newlineSplitter(newlineToken);
    Field wtsField = WordsToSentencesAnnotator.class.getDeclaredField("wts");
    wtsField.setAccessible(true);
    Object wtsObject = wtsField.get(annotator);
    Field boundaryTokenField = WordToSentenceProcessor.class.getDeclaredField("boundaryTokenStrings");
    boundaryTokenField.setAccessible(true);
    @SuppressWarnings("unchecked")
    Set<String> boundaryTokens = ((Set<String>) (boundaryTokenField.get(wtsObject)));
    assertTrue(boundaryTokens.contains("\n"));
}

@Test
public void test3()
{
    Annotator nonSplitter = WordsToSentencesAnnotator.nonSplitter();
    Annotation annotation = new Annotation("");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    token1.setIndex(1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    token2.setIndex(2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    token3.setIndex(3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("test");
    token4.setIndex(4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord(".");
    token5.setIndex(5);
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    tokens.add(token5);
    annotation.set(CoreLabel.class, tokens);
    nonSplitter.annotate(annotation);
    List<Annotation> sentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(sentences);
    assertEquals(1, sentences.size());
    List<CoreLabel> sentenceTokens = sentences.get(0).get(TokensAnnotation.class);
    assertEquals(5, sentenceTokens.size());
    assertEquals("This", sentenceTokens.get(0).word());
    assertEquals("test", sentenceTokens.get(3).word());
    assertEquals(".", sentenceTokens.get(4).word());
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Hello world. This is a test.");
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
    token3.set(AfterAnnotation.class, " ");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("This");
    token4.set(TextAnnotation.class, "This");
    token4.set(CharacterOffsetBeginAnnotation.class, 13);
    token4.set(CharacterOffsetEndAnnotation.class, 17);
    token4.set(OriginalTextAnnotation.class, "This");
    token4.set(BeforeAnnotation.class, " ");
    token4.set(AfterAnnotation.class, " ");
    CoreLabel token5 = new CoreLabel();
    token5.setWord("is");
    token5.set(TextAnnotation.class, "is");
    token5.set(CharacterOffsetBeginAnnotation.class, 18);
    token5.set(CharacterOffsetEndAnnotation.class, 20);
    token5.set(OriginalTextAnnotation.class, "is");
    token5.set(BeforeAnnotation.class, " ");
    token5.set(AfterAnnotation.class, " ");
    CoreLabel token6 = new CoreLabel();
    token6.setWord("a");
    token6.set(TextAnnotation.class, "a");
    token6.set(CharacterOffsetBeginAnnotation.class, 21);
    token6.set(CharacterOffsetEndAnnotation.class, 22);
    token6.set(OriginalTextAnnotation.class, "a");
    token6.set(BeforeAnnotation.class, " ");
    token6.set(AfterAnnotation.class, " ");
    CoreLabel token7 = new CoreLabel();
    token7.setWord("test");
    token7.set(TextAnnotation.class, "test");
    token7.set(CharacterOffsetBeginAnnotation.class, 23);
    token7.set(CharacterOffsetEndAnnotation.class, 27);
    token7.set(OriginalTextAnnotation.class, "test");
    token7.set(BeforeAnnotation.class, " ");
    token7.set(AfterAnnotation.class, "");
    CoreLabel token8 = new CoreLabel();
    token8.setWord(".");
    token8.set(TextAnnotation.class, ".");
    token8.set(CharacterOffsetBeginAnnotation.class, 27);
    token8.set(CharacterOffsetEndAnnotation.class, 28);
    token8.set(OriginalTextAnnotation.class, ".");
    token8.set(BeforeAnnotation.class, "");
    token8.set(AfterAnnotation.class, "");
    List<CoreLabel> tokenList = new ArrayList<>();
    tokenList.add(token1);
    tokenList.add(token2);
    tokenList.add(token3);
    tokenList.add(token4);
    tokenList.add(token5);
    tokenList.add(token6);
    tokenList.add(token7);
    tokenList.add(token8);
    annotation.set(TokensAnnotation.class, tokenList);
    WordsToSentencesAnnotator annotator = new WordsToSentencesAnnotator("ssplit.simple");
    annotator.annotate(annotation);
    assertTrue(annotation.containsKey(SentencesAnnotation.class));
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    assertEquals(2, sentences.size());
    CoreMap firstSentence = sentences.get(0);
    CoreMap secondSentence = sentences.get(1);
    assertEquals("Hello world.", firstSentence.get(TextAnnotation.class));
    assertEquals("This is a test.", secondSentence.get(TextAnnotation.class));
}

