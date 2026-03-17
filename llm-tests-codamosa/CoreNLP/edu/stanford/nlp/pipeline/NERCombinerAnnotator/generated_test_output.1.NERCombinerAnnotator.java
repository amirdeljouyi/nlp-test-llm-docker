import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Properties props = PropertiesUtils.asProperties("ner.nthreads", "5");
    Constructor<NERCombinerAnnotator> constructor = NERCombinerAnnotator.class.getDeclaredConstructor(Properties.class);
    constructor.setAccessible(true);
    NERCombinerAnnotator annotator = constructor.newInstance(props);
    Field nThreadsField = NERCombinerAnnotator.class.getDeclaredField("nThreads");
    nThreadsField.setAccessible(true);
    nThreadsField.setInt(annotator, 5);
    int result = annotator.nThreads();
    assertEquals(5, result);
}

@Test
public void test2()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(true);
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
    assertTrue(result.contains(TagsAnnotation.class));
    assertTrue(result.contains(NumerizedTokensAnnotation.class));
    assertTrue(result.contains(AnswerAnnotation.class));
    assertTrue(result.contains(NumericCompositeValueAnnotation.class));
    assertTrue(result.contains(CoarseNamedEntityTagAnnotation.class));
    assertTrue(result.contains(FineGrainedNamedEntityTagAnnotation.class));
    assertTrue(result.contains(MentionsAnnotation.class));
    assertTrue(result.contains(EntityTypeAnnotation.class));
    assertTrue(result.contains(EntityMentionIndexAnnotation.class));
    assertEquals(21, result.size());
}

@Test
public void test3()
{
    CoreLabel nerToken = new CoreLabel();
    nerToken.setWord("Stanford");
    nerToken.set(NamedEntityTagAnnotation.class, "ORGANIZATION");
    nerToken.set(NormalizedNamedEntityTagAnnotation.class, "Stanford University");
    nerToken.set(NamedEntityTagProbsAnnotation.class, Collections.singletonMap("ORGANIZATION", 1.0));
    nerToken.set(FineGrainedNamedEntityTagAnnotation.class, "University");
    nerToken.set(CoarseNamedEntityTagAnnotation.class, "ORG");
    nerToken.set(TimexAnnotation.class, null);
    nerToken.set(NumericValueAnnotation.class, null);
    nerToken.set(NumericTypeAnnotation.class, null);
    nerToken.set(NumericCompositeValueAnnotation.class, null);
    nerToken.set(NumericCompositeTypeAnnotation.class, null);
    List<CoreLabel> nerTokens = Arrays.asList(nerToken);
    CoreMap nerSentence = new ArrayCoreMap();
    nerSentence.set(TokensAnnotation.class, nerTokens);
    List<CoreMap> nerSentences = Arrays.asList(nerSentence);
    Annotation nerAnnotation = new Annotation("Stanford");
    nerAnnotation.set(TokensAnnotation.class, nerTokens);
    nerAnnotation.set(SentencesAnnotation.class, nerSentences);
    CoreLabel origToken = new CoreLabel();
    origToken.setWord("Stanford");
    List<CoreLabel> origTokens = Arrays.asList(origToken);
    CoreMap origSentence = new ArrayCoreMap();
    origSentence.set(TokensAnnotation.class, origTokens);
    List<CoreMap> origSentences = Arrays.asList(origSentence);
    Annotation origAnnotation = new Annotation("Stanford");
    origAnnotation.set(TokensAnnotation.class, origTokens);
    origAnnotation.set(SentencesAnnotation.class, origSentences);
    NERCombinerAnnotator.transferNERAnnotationsToAnnotation(nerAnnotation, origAnnotation);
    assertEquals("ORGANIZATION", origToken.get(NamedEntityTagAnnotation.class));
    assertEquals("Stanford University", origToken.get(NormalizedNamedEntityTagAnnotation.class));
    assertEquals(Collections.singletonMap("ORGANIZATION", 1.0), origToken.get(NamedEntityTagProbsAnnotation.class));
    assertEquals("University", origToken.get(FineGrainedNamedEntityTagAnnotation.class));
    assertEquals("ORG", origToken.get(CoarseNamedEntityTagAnnotation.class));
    assertNull(origToken.get(TimexAnnotation.class));
    assertNull(origToken.get(NumericValueAnnotation.class));
    assertNull(origToken.get(NumericTypeAnnotation.class));
    assertNull(origToken.get(NumericCompositeValueAnnotation.class));
    assertNull(origToken.get(NumericCompositeTypeAnnotation.class));
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("There are 5 apples");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token = new CoreLabel();
    token.setWord("5");
    token.setNER("NUMBER");
    token.set(TimexAnnotation.class, "2024-05-01");
    tokens.add(token);
    annotation.set(TokensAnnotation.class, tokens);
    NERCombinerAnnotator nerCombinerAnnotator = new NERCombinerAnnotator(false, false, false, false, false, false, false, false, null, null, null, null, null, null, null, null, null);
    nerCombinerAnnotator.annotate(annotation);
    List<CoreLabel> resultTokens = annotation.get(TokensAnnotation.class);
    CoreLabel resultToken = resultTokens.get(0);
    assertNull(resultToken.get(TimexAnnotation.class));
    Map<String, Double> probs = resultToken.get(NamedEntityTagProbsAnnotation.class);
    assertNotNull(probs);
    assertEquals(-1.0, probs.get("NUMBER"), 0.001);
}

@Test
public void test5()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.setNER(null);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("John");
    token2.setNER("PERSON");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    Annotation annotation = new Annotation("");
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(false, false);
    annotator.doOneFailedSentence(annotation, sentence);
    assertEquals("O", token1.ner());
    assertEquals("PERSON", token2.ner());
}

@Test
public void test6()
{
    Annotation annotation = new Annotation("Barack Obama");
    CoreMap sentence = mock(CoreMap.class);
    CoreLabel inputToken = new CoreLabel();
    inputToken.setWord("Obama");
    List<CoreLabel> tokens = Collections.singletonList(inputToken);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    CoreLabel outputToken = new CoreLabel();
    outputToken.set(NamedEntityTagAnnotation.class, "PERSON");
    outputToken.set(NormalizedNamedEntityTagAnnotation.class, "Barack Obama");
    Map<String, Double> neProbs = new HashMap<>();
    neProbs.put("PERSON", 1.0);
    outputToken.set(NamedEntityTagProbsAnnotation.class, neProbs);
    NERCombinerAnnotator.NERClassifier nerClassifierMock = mock(NERClassifier.class);
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(null);
    annotator.ner = nerClassifierMock;
    annotator.maxSentenceLength = 10;
    when(nerClassifierMock.classifySentenceWithGlobalInformation(tokens, annotation, sentence)).thenReturn(Collections.singletonList(outputToken));
    annotator.doOneSentence(annotation, sentence);
    assertEquals("PERSON", tokens.get(0).ner());
    assertEquals("Barack Obama", tokens.get(0).get(NormalizedNamedEntityTagAnnotation.class));
    assertEquals(neProbs, tokens.get(0).get(NamedEntityTagProbsAnnotation.class));
    assertEquals("PERSON", tokens.get(0).get(CoarseNamedEntityTagAnnotation.class));
}


