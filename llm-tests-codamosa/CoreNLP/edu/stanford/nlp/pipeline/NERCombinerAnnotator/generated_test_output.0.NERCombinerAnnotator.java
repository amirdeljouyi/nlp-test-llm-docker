import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator();
    Field nThreadsField = NERCombinerAnnotator.class.getDeclaredField("nThreads");
    nThreadsField.setAccessible(true);
    nThreadsField.setInt(annotator, 5);
    int result = annotator.nThreads();
    assertEquals(5, result);
}

@Test
public void test2()
{
    Properties props = new Properties();
    props.setProperty("ner.buildEntityMentions", "true");
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(props);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertTrue(result.contains(NamedEntityTagAnnotation.class));
    assertTrue(result.contains(NormalizedNamedEntityTagAnnotation.class));
    assertTrue(result.contains(ValueAnnotation.class));
    assertTrue(result.contains(Annotation.class));
    assertTrue(result.contains(TimeIndexAnnotation.class));
    assertTrue(result.contains(DistSimAnnotation.class));
    assertTrue(result.contains(NumericCompositeTypeAnnotation.class));
    assertTrue(result.contains(TimexAnnotation.class));
    assertTrue(result.contains(NumericValueAnnotation.class));
    assertTrue(result.contains(ChildrenAnnotation.class));
    assertTrue(result.contains(NumericTypeAnnotation.class));
    assertTrue(result.contains(ShapeAnnotation.class));
    assertTrue(result.contains(NumerizedTokensAnnotation.class));
    assertTrue(result.contains(AnswerAnnotation.class));
    assertTrue(result.contains(NumericCompositeValueAnnotation.class));
    assertTrue(result.contains(CoarseNamedEntityTagAnnotation.class));
    assertTrue(result.contains(FineGrainedNamedEntityTagAnnotation.class));
    assertTrue(result.contains(TagsAnnotation.class));
    assertTrue(result.contains(MentionsAnnotation.class));
    assertTrue(result.contains(EntityTypeAnnotation.class));
    assertTrue(result.contains(EntityMentionIndexAnnotation.class));
}

@Test
public void test3()
{
    Annotation originalAnnotation = new Annotation("Barack");
    CoreLabel originalToken = new CoreLabel();
    originalToken.setWord("Barack");
    originalToken.set(TextAnnotation.class, "Barack");
    originalToken.set(IndexAnnotation.class, 1);
    List<CoreLabel> originalTokens = new ArrayList<>();
    originalTokens.add(originalToken);
    CoreMap originalSentence = new Annotation("Barack");
    originalSentence.set(TokensAnnotation.class, originalTokens);
    originalAnnotation.set(TokensAnnotation.class, originalTokens);
    originalAnnotation.set(SentencesAnnotation.class, Collections.singletonList(originalSentence));
    Annotation nerTokenizedAnnotation = new Annotation("Barack");
    CoreLabel nerToken = new CoreLabel();
    nerToken.setWord("Barack");
    nerToken.set(TextAnnotation.class, "Barack");
    nerToken.set(IndexAnnotation.class, 1);
    nerToken.set(NamedEntityTagAnnotation.class, "PERSON");
    List<CoreLabel> nerTokens = new ArrayList<>();
    nerTokens.add(nerToken);
    nerTokenizedAnnotation.set(TokensAnnotation.class, nerTokens);
    NERCombinerAnnotator.transferNERAnnotationsToAnnotation(nerTokenizedAnnotation, originalAnnotation);
    String transferredNER = originalTokens.get(0).get(NamedEntityTagAnnotation.class);
    assertEquals("PERSON", transferredNER);
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Barack Obama was born in Hawaii.");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Barack");
    token1.setNER("PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Obama");
    token2.setNER("PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("was");
    token3.setNER("O");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("born");
    token4.setNER("O");
    CoreLabel token5 = new CoreLabel();
    token5.setWord("in");
    token5.setNER("O");
    CoreLabel token6 = new CoreLabel();
    token6.setWord("Hawaii");
    token6.setNER("LOCATION");
    CoreLabel token7 = new CoreLabel();
    token7.setWord(".");
    token7.setNER("O");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    tokens.add(token5);
    tokens.add(token6);
    tokens.add(token7);
    annotation.set(TokensAnnotation.class, tokens);
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(false, false, false, false, false, false, null, null, null, null, null, null);
    annotator.annotate(annotation);
    List<CoreLabel> annotatedTokens = annotation.get(TokensAnnotation.class);
    assertNotNull(annotatedTokens);
    assertEquals(7, annotatedTokens.size());
    for (CoreLabel token : annotatedTokens) {
        assertNotNull(token.get(NamedEntityTagProbsAnnotation.class));
    }
    assertEquals(((Double) (-1.0)), annotatedTokens.get(0).get(NamedEntityTagProbsAnnotation.class).get("PERSON"));
    assertEquals(((Double) (-1.0)), annotatedTokens.get(5).get(NamedEntityTagProbsAnnotation.class).get("LOCATION"));
}

@Test
public void test5()
{
    CoreLabel inputToken = new CoreLabel();
    inputToken.setWord("Stanford");
    CoreLabel classifiedToken = new CoreLabel();
    classifiedToken.set(NamedEntityTagAnnotation.class, "ORGANIZATION");
    classifiedToken.set(NormalizedNamedEntityTagAnnotation.class, "Stanford University");
    Map<String, Double> probs = new HashMap<>();
    probs.put("ORGANIZATION", 1.0);
    classifiedToken.set(NamedEntityTagProbsAnnotation.class, probs);
    CoreMap sentence = mock(CoreMap.class);
    List<CoreLabel> tokens = Arrays.asList(inputToken);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    NERCombinerAnnotator annotator = mock(NERCombinerAnnotator.class, CALLS_REAL_METHODS);
    annotator.maxSentenceLength = 10;
    annotator.ner = mock(NamedEntityRecognizerAdapter.class);
    when(annotator.ner.classifySentenceWithGlobalInformation(tokens, null, sentence)).thenReturn(Arrays.asList(classifiedToken));
    Annotation annotation = new Annotation("Stanford");
    annotator.doOneSentence(annotation, sentence);
    assertEquals("ORGANIZATION", tokens.get(0).ner());
    assertEquals("Stanford University", tokens.get(0).get(NormalizedNamedEntityTagAnnotation.class));
    assertEquals(probs, tokens.get(0).get(NamedEntityTagProbsAnnotation.class));
    assertEquals("ORGANIZATION", tokens.get(0).get(CoarseNamedEntityTagAnnotation.class));
}

