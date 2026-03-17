import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Properties props = new Properties();
    props.setProperty("nthreads", "3");
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(props);
    assertEquals(3, annotator.nThreads());
}

@Test
public void test2()
{
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(false);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>();
    expected.add(NamedEntityTagAnnotation.class);
    expected.add(NormalizedNamedEntityTagAnnotation.class);
    expected.add(ValueAnnotation.class);
    expected.add(Annotation.class);
    expected.add(TimeIndexAnnotation.class);
    expected.add(DistSimAnnotation.class);
    expected.add(NumericCompositeTypeAnnotation.class);
    expected.add(TimexAnnotation.class);
    expected.add(NumericValueAnnotation.class);
    expected.add(ChildrenAnnotation.class);
    expected.add(NumericTypeAnnotation.class);
    expected.add(ShapeAnnotation.class);
    expected.add(TagsAnnotation.class);
    expected.add(NumerizedTokensAnnotation.class);
    expected.add(AnswerAnnotation.class);
    expected.add(NumericCompositeValueAnnotation.class);
    expected.add(CoarseNamedEntityTagAnnotation.class);
    expected.add(FineGrainedNamedEntityTagAnnotation.class);
    assertEquals(expected, result);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("The cost was $100.");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("The");
    token1.setNER("O");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("cost");
    token2.setNER("O");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("was");
    token3.setNER("O");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("$");
    token4.setNER("MONEY");
    token4.set(TimexAnnotation.class, "fake-timex-1");
    CoreLabel token5 = new CoreLabel();
    token5.setWord("100");
    token5.setNER("MONEY");
    token5.set(TimexAnnotation.class, "fake-timex-2");
    CoreLabel token6 = new CoreLabel();
    token6.setWord(".");
    token6.setNER("O");
    annotation.set(TokensAnnotation.class, Arrays.asList(token1, token2, token3, token4, token5, token6));
    NERCombinerAnnotator nerAnnotator = new NERCombinerAnnotator(false, false, false, false, false, null, null, null, null, false, false, false, null, null);
    nerAnnotator.annotate(annotation);
    assertNull(token4.get(TimexAnnotation.class));
    assertNull(token5.get(TimexAnnotation.class));
    assertNotNull(token1.get(NamedEntityTagProbsAnnotation.class));
    assertNotNull(token4.get(NamedEntityTagProbsAnnotation.class));
    Map<String, Double> probs = token4.get(NamedEntityTagProbsAnnotation.class);
    assertTrue(probs.containsKey("MONEY"));
    assertEquals(-1.0, probs.get("MONEY"), 1.0E-4);
}

@Test
public void test4()
{
    NERCombinerAnnotator annotator = mock(NERCombinerAnnotator.class);
    NERCombinerAnnotator realAnnotator = new NERCombinerAnnotator("edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz");
    CoreLabel token = new CoreLabel();
    token.setWord("Stanford");
    token.setNER(null);
    CoreMap sentence = mock(CoreMap.class);
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    Annotation annotation = new Annotation("");
    realAnnotator.doOneFailedSentence(annotation, sentence);
    assertEquals("O", token.ner());
}

@Test
public void test5()
{
    CoreMap mockSentence = mock(CoreMap.class);
    CoreLabel inputToken = new CoreLabel();
    inputToken.setWord("Stanford");
    List<CoreLabel> inputTokens = Arrays.asList(inputToken);
    when(mockSentence.get(TokensAnnotation.class)).thenReturn(inputTokens);
    CoreLabel outputToken = new CoreLabel();
    outputToken.set(NamedEntityTagAnnotation.class, "ORGANIZATION");
    outputToken.set(NormalizedNamedEntityTagAnnotation.class, "Stanford University");
    Map<String, Double> probMap = new HashMap<>();
    probMap.put("ORGANIZATION", 1.0);
    outputToken.set(NamedEntityTagProbsAnnotation.class, probMap);
    NERCombinerAnnotator annotator = new NERCombinerAnnotator() {
        {
            this.maxSentenceLength = 10;
            this.ner = new NERClassifierCombiner() {
                @Override
                public List<CoreLabel> classifySentenceWithGlobalInformation(List<CoreLabel> tokens, Annotation ann, CoreMap sentence) {
                    return Arrays.asList(outputToken);
                }
            };
        }

        @Override
        protected void doOneFailedSentence(Annotation annotation, CoreMap sentence) {
            throw new RuntimeException("Should not call failure path in this test.");
        }
    };
    Annotation doc = new Annotation("Stanford");
    annotator.doOneSentence(doc, mockSentence);
    CoreLabel updatedToken = inputTokens.get(0);
    assertEquals("ORGANIZATION", updatedToken.get(NamedEntityTagAnnotation.class));
    assertEquals("Stanford University", updatedToken.get(NormalizedNamedEntityTagAnnotation.class));
    assertEquals(probMap, updatedToken.get(NamedEntityTagProbsAnnotation.class));
    assertEquals("ORGANIZATION", updatedToken.get(CoarseNamedEntityTagAnnotation.class));
}

