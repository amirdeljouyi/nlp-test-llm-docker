import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    NERCombinerAnnotator annotator = ((NERCombinerAnnotator) (Constructor.class.getDeclaredConstructor(Class.class, Class[].class).newInstance(NERCombinerAnnotator.class, new Class[0]).newInstance()));
    Field field = NERCombinerAnnotator.class.getDeclaredField("nThreads");
    field.setAccessible(true);
    field.setInt(annotator, 4);
    Field methodField = NERCombinerAnnotator.class.getDeclaredMethod("nThreads").getDeclaringClass().getDeclaredField("nThreads");
    methodField.setAccessible(true);
    assertEquals(4, annotator.nThreads());
}

@Test
public void test2()
{
    Properties props = new Properties();
    props.setProperty("ner.buildEntityMentions", "true");
    NERCombinerAnnotator annotator = new NERCombinerAnnotator("ner", props);
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(NamedEntityTagAnnotation.class, NormalizedNamedEntityTagAnnotation.class, ValueAnnotation.class, Annotation.class, TimeIndexAnnotation.class, DistSimAnnotation.class, NumericCompositeTypeAnnotation.class, TimexAnnotation.class, NumericValueAnnotation.class, ChildrenAnnotation.class, NumericTypeAnnotation.class, ShapeAnnotation.class, TagsAnnotation.class, NumerizedTokensAnnotation.class, AnswerAnnotation.class, NumericCompositeValueAnnotation.class, CoarseNamedEntityTagAnnotation.class, FineGrainedNamedEntityTagAnnotation.class, MentionsAnnotation.class, EntityTypeAnnotation.class, EntityMentionIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test3()
{
    CoreLabel token = new CoreLabel();
    token.setNER("MONEY");
    token.set(TimexAnnotation.class, "dummyTimex");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token);
    Annotation annotation = new Annotation("");
    annotation.set(TokensAnnotation.class, tokens);
    NERCombinerAnnotator annotator = new NERCombinerAnnotator() {
        {
            this.useNERSpecificTokenization = false;
            this.setDocDate = false;
            this.applyNumericClassifiers = false;
            this.statisticalOnly = true;
            this.applyFineGrained = false;
            this.applyAdditionalRules = false;
            this.applyTokensRegexRules = false;
            this.buildEntityMentions = false;
            this.language = HumanLanguage.ENGLISH;
            this.ner = new NERCombiner() {
                @Override
                public void finalizeAnnotation(Annotation annotation) {
                }

                @Override
                public void annotate(Annotation annotation) {
                }
            };
        }
    };
    annotator.annotate(annotation);
    CoreLabel resultingToken = annotation.get(TokensAnnotation.class).get(0);
    assertNull(resultingToken.get(TimexAnnotation.class));
    Map<String, Double> probs = resultingToken.get(NamedEntityTagProbsAnnotation.class);
    assertNotNull(probs);
    assertTrue(probs.containsKey("MONEY"));
    assertEquals(Double.valueOf(-1.0), probs.get("MONEY"));
}

@Test
public void test4()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Alice");
    token1.setNER(null);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("went");
    token2.setNER("O");
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    tokens.add(token1);
    tokens.add(token2);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    NERCombinerAnnotator annotator = new NERCombinerAnnotator(false);
    String backgroundSymbol = annotator.ner.backgroundSymbol();
    annotator.doOneFailedSentence(new Annotation("Alice went home."), sentence);
    assertEquals(backgroundSymbol, tokens.get(0).ner());
    assertEquals("O", tokens.get(1).ner());
}

@Test
public void test5()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Barack");
    token1.set(TextAnnotation.class, "Barack");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Obama");
    token2.set(TextAnnotation.class, "Obama");
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    CoreLabel outToken1 = new CoreLabel();
    outToken1.set(NamedEntityTagAnnotation.class, "PERSON");
    outToken1.set(NormalizedNamedEntityTagAnnotation.class, "Barack");
    outToken1.set(NamedEntityTagProbsAnnotation.class, Collections.singletonMap("PERSON", 1.0));
    CoreLabel outToken2 = new CoreLabel();
    outToken2.set(NamedEntityTagAnnotation.class, "PERSON");
    outToken2.set(NormalizedNamedEntityTagAnnotation.class, "Obama");
    outToken2.set(NamedEntityTagProbsAnnotation.class, Collections.singletonMap("PERSON", 1.0));
    List<CoreLabel> classifiedTokens = Arrays.asList(outToken1, outToken2);
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    NERCombinerAnnotator annotator = new NERCombinerAnnotator();
    annotator.maxSentenceLength = 10;
    NERClassifierCombiner ner = mock(NERClassifierCombiner.class);
    annotator.ner = ner;
    try {
        when(ner.classifySentenceWithGlobalInformation(tokens, null, sentence)).thenReturn(classifiedTokens);
    } catch (Exception e) {
        fail("Setup exception: " + e.getMessage());
    }
    annotator.doOneSentence(null, sentence);
    assertEquals("PERSON", token1.ner());
    assertEquals("PERSON", token2.ner());
    assertEquals("Barack", token1.get(NormalizedNamedEntityTagAnnotation.class));
    assertEquals("Obama", token2.get(NormalizedNamedEntityTagAnnotation.class));
    assertEquals(Double.valueOf(1.0), token1.get(NamedEntityTagProbsAnnotation.class).get("PERSON"));
    assertEquals(Double.valueOf(1.0), token2.get(NamedEntityTagProbsAnnotation.class).get("PERSON"));
    assertEquals("PERSON", token1.get(CoarseNamedEntityTagAnnotation.class));
    assertEquals("PERSON", token2.get(CoarseNamedEntityTagAnnotation.class));
}

