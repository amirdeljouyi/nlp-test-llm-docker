import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    NERAnnotator annotator = spy(new NERAnnotator(null, null));
    doReturn(true).when(annotator).isInitialized();
    NERAnnotator.Parameters params = new NERAnnotator.Parameters();
    SparseNetworkLearner mockLearner = mock(SparseNetworkLearner.class);
    Lexicon mockLexicon = mock(Lexicon.class);
    Map<Object, Object> lexMap = new HashMap<>();
    Feature mockFeature = mock(Feature.class);
    lexMap.put(mockFeature, 0);
    when(mockLexicon.getMap()).thenReturn(lexMap);
    when(mockLearner.getLexicon()).thenReturn(mockLexicon);
    SparseAveragedPerceptron mockSAP = mock(SparseAveragedPerceptron.class);
    AveragedWeightVector mockAWV = mock(AveragedWeightVector.class);
    Map<Integer, Double> rawWeights = new HashMap<>();
    rawWeights.put(0, 2.0);
    when(mockAWV.getRawWeights()).thenReturn(rawWeights);
    when(mockSAP.getAveragedWeightVector()).thenReturn(mockAWV);
    OVector mockOV = mock(OVector.class);
    when(mockOV.size()).thenReturn(1);
    when(mockOV.get(0)).thenReturn(mockSAP);
    when(mockLearner.getNetwork()).thenReturn(mockOV);
    params.taggerLevel2 = mockLearner;
    annotator.params = params;
    HashMap<Feature, double[]> result = annotator.getL2FeatureWeights();
    assertEquals(1, result.size());
    assertTrue(result.containsKey(mockFeature));
    double[] weights = result.get(mockFeature);
    assertNotNull(weights);
    assertEquals(1, weights.length);
    assertEquals(2.0, weights[0], 1.0E-6);
}

@Test
public void test2()
{
    String configFilePath = "src/test/resources/ner-default.config";
    String modelLevel = "L1";
    String[] args = new String[]{ configFilePath, modelLevel };
    NERAnnotator.main(args);
    File outputFile = new File("L1FeatureWeights.tsv");
    Assert.assertTrue("L1FeatureWeights.tsv file should exist after main execution", outputFile.exists());
    outputFile.delete();
}

@Test
public void test3()
{
    String[] tokens = new String[]{ "Barack", "Obama", "was", "president" };
    Sentence sentence = mock(Sentence.class);
    when(sentence.getTokens()).thenReturn(new String[]{ "Barack", "Obama", "was", "president" });
    TextAnnotation ta = mock(TextAnnotation.class);
    when(ta.getTokens()).thenReturn(tokens);
    when(ta.getNumberOfSentences()).thenReturn(1);
    when(ta.getSentence(0)).thenReturn(sentence);
    final TextAnnotation realTA = new TextAnnotation("corpus", "id", tokens);
    when(ta.getCorpusId()).thenReturn(realTA.getCorpusId());
    when(ta.getId()).thenReturn(realTA.getId());
    when(ta.getText()).thenReturn(realTA.getText());
    doAnswer(( invocation) -> {
        String viewName = invocation.getArgument(0);
        SpanLabelView view = invocation.getArgument(1);
        realTA.addView(viewName, view);
        return null;
    }).when(ta).addView(anyString(), any(SpanLabelView.class));
    NERAnnotator annotator = new NERAnnotator("NER", false, false) {
        @Override
        public void addView(TextAnnotation inputTa) {
            super.addView(inputTa);
        }
    };
    annotator.initialize(new String[]{ "-model", "testModelPath" });
    annotator.addView(ta);
    assertTrue(realTA.hasView("NER"));
    SpanLabelView view = ((SpanLabelView) (realTA.getView("NER")));
    assertNotNull(view);
}

@Test
public void test4()
{
    Properties properties = new Properties();
    ResourceManager resourceManager = new ResourceManager(properties);
    NERAnnotator annotator = new NERAnnotator("NER_ONTONOTES") {
        @Override
        protected String getViewName() {
            return "NER_ONTONOTES";
        }
    };
    annotator.initialize(resourceManager);
    Field paramsField = NERAnnotator.class.getDeclaredField("params");
    paramsField.setAccessible(true);
    Parameters params = ((Parameters) (paramsField.get(annotator)));
    assertNotNull("Parameters should be initialized", params);
    assertFalse("forceNewSentenceOnLineBreaks should be false", params.forceNewSentenceOnLineBreaks);
}

