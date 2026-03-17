import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator();
    Field field = NERCombinerAnnotator.class.getDeclaredField("nThreads");
    field.setAccessible(true);
    field.setInt(annotator, 3);
    int result = annotator.nThreads();
    assertEquals(3, result);
}

@Test
public void test2()
{
}
{
    this.buildEntityMentions = true;
}

@Test
public void test3()
{
    CoreLabel nerToken = new CoreLabel();
    nerToken.setWord("Obama");
    nerToken.set(NamedEntityTagAnnotation.class, "PERSON");
    nerToken.set(NormalizedNamedEntityTagAnnotation.class, "Barack Obama");
    Annotation nerTokenizedAnnotation = new Annotation("Obama");
    List<CoreLabel> nerTokens = Collections.singletonList(nerToken);
    nerTokenizedAnnotation.set(TokensAnnotation.class, nerTokens);
    CoreMap nerSentence = new ArrayCoreMap();
    nerSentence.set(TokensAnnotation.class, nerTokens);
    nerTokenizedAnnotation.set(SentencesAnnotation.class, Collections.singletonList(nerSentence));
    CoreLabel originalToken = new CoreLabel();
    originalToken.setWord("Obama");
    List<CoreLabel> originalTokens = Collections.singletonList(originalToken);
    Annotation originalAnnotation = new Annotation("Obama");
    originalAnnotation.set(TokensAnnotation.class, originalTokens);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, originalTokens);
    originalAnnotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    NERCombinerAnnotator.transferNERAnnotationsToAnnotation(nerTokenizedAnnotation, originalAnnotation);
    assertEquals("PERSON", originalToken.get(NamedEntityTagAnnotation.class));
    assertEquals("Barack Obama", originalToken.get(NormalizedNamedEntityTagAnnotation.class));
}

@Test
public void test4()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(false, false, false, null, false, false, false, false, false, false);
    Annotation annotation = new Annotation("Payment is $1000.");
    CoreLabel token = new CoreLabel();
    token.set(TextAnnotation.class, "$1000");
    token.set(NamedEntityTagAnnotation.class, "MONEY");
    token.set(TokensAnnotation.class, "dummy");
    token.set(TimexAnnotation.class, "2024-06-01");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token);
    annotation.set(TokensAnnotation.class, tokens);
    annotator.annotate(annotation);
    List<CoreLabel> annotatedTokens = annotation.get(TokensAnnotation.class);
    CoreLabel annotatedToken = annotatedTokens.get(0);
    assertNull("TimexAnnotation should be removed for MONEY NER", annotatedToken.get(TimexAnnotation.class));
    Map<String, Double> probs = annotatedToken.get(NamedEntityTagProbsAnnotation.class);
    assertNotNull("NER Probabilities map should be set", probs);
    assertEquals("Expected default probability of -1.0", Double.valueOf(-1.0), probs.get("MONEY"));
}

@Test
public void test5()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator();
    CoreLabel tokenWithNullNER = new CoreLabel();
    tokenWithNullNER.setWord("Hello");
    CoreLabel tokenWithNER = new CoreLabel();
    tokenWithNER.setWord("Stanford");
    tokenWithNER.setNER("ORGANIZATION");
    List<CoreLabel> tokens = Arrays.asList(tokenWithNullNER, tokenWithNER);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    Annotation annotation = new Annotation("");
    annotator.doOneFailedSentence(annotation, sentence);
    assertEquals(annotator.ner.backgroundSymbol(), tokenWithNullNER.ner());
    assertEquals("ORGANIZATION", tokenWithNER.ner());
}

@Test
public void test6()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(null);
    annotator.maxSentenceLength = 10;
    Annotation annotation = new Annotation("Some text");
    CoreLabel inputToken = new CoreLabel();
    inputToken.setWord("Stanford");
    List<CoreLabel> inputTokens = Collections.singletonList(inputToken);
    @SuppressWarnings("unchecked")
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(inputTokens);
    CoreLabel outputToken = new CoreLabel();
    outputToken.set(NamedEntityTagAnnotation.class, "ORGANIZATION");
    outputToken.set(NormalizedNamedEntityTagAnnotation.class, "Stanford University");
    Map<String, Double> probs = new HashMap<>();
    probs.put("ORGANIZATION", 1.0);
    outputToken.set(NamedEntityTagProbsAnnotation.class, probs);
    NERCombinerAnnotator spyAnnotator = spy(annotator);
    doReturn(Collections.singletonList(outputToken)).when(spyAnnotator).ner.classifySentenceWithGlobalInformation(inputTokens, annotation, sentence);
    spyAnnotator.doOneSentence(annotation, sentence);
    assertEquals("ORGANIZATION", inputToken.ner());
    assertEquals(probs, inputToken.get(NamedEntityTagProbsAnnotation.class));
    assertEquals("ORGANIZATION", inputToken.get(CoarseNamedEntityTagAnnotation.class));
    assertEquals("Stanford University", inputToken.get(NormalizedNamedEntityTagAnnotation.class));
}

