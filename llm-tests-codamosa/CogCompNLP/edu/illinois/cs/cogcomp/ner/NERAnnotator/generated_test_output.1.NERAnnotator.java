import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    NERAnnotator annotator = spy(new NERAnnotator("test"));
    doReturn(false).when(annotator).isInitialized();
    doAnswer(( invocation) -> {
        SparseNetworkLearner learner = mock(.class);
        Feature feature = mock(.class);
        Map<Feature, Integer> featureMap = new HashMap<>();
        featureMap.put(feature, 0);
        when(learner.getLexicon()).thenReturn(() -> featureMap);
        SparseAveragedPerceptron perceptron = mock(.class);
        AveragedWeightVector vector = mock(.class);
        Map<Integer, Double> weights = new HashMap<>();
        weights.put(0, 5.0);
        when(vector.getRawWeights()).thenReturn(weights);
        when(perceptron.getAveragedWeightVector()).thenReturn(vector);
        OVector oVector = mock(.class);
        when(oVector.size()).thenReturn(1);
        when(oVector.get(0)).thenReturn(perceptron);
        when(learner.getNetwork()).thenReturn(oVector);
        NERAnnotator.Parameters params = mock(.class);
        params.taggerLevel1 = learner;
        annotator.params = params;
        return null;
    }).when(annotator).doInitialize();
    HashMap<Feature, double[]> result = annotator.getL1FeatureWeights();
    assertEquals(1, result.size());
    double[] weights = result.values().iterator().next();
    assertArrayEquals(new double[]{ 5.0 }, weights, 1.0E-4);
}

@Test
public void test2()
{
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    String[] args = new String[]{ "only-one-arg" };
    NERAnnotator.main(args);
    System.setOut(originalOut);
    String output = outContent.toString();
    Assert.assertTrue(output.contains("FeatureWeightsMatrix requires two arguments:"));
}

@Test
public void test3()
{
    TokenLabelView dummyView = new TokenLabelView(ViewNames.TOKENS, "testGenerator", null, 1.0);
    Sentence dummySentence = new Sentence(new String[]{ "Barack", "Obama", "visited", "Paris" }, dummyView);
    TextAnnotation ta = new TextAnnotation("testCorpus", "testId", "Barack Obama visited Paris", new String[][]{ new String[]{ "Barack", "Obama", "visited", "Paris" } }, new int[][]{ new int[]{ 0, 7, 13, 21 } }, new int[][]{ new int[]{ 6, 12, 20, 26 } });
    dummyView.setTokens(ta.getTokens());
    ta.addView(TOKENS, dummyView);
    NERAnnotator nerAnnotator = new NERAnnotator("NER_CONLL");
    nerAnnotator.addView(ta);
    assertTrue("NER view should be added to TextAnnotation", ta.hasView(NER_CONLL));
    View nerView = ta.getView(NER_CONLL);
    assertNotNull("NER view should not be null", nerView);
    assertTrue("NER view should contain at least one constituent", nerView.getNumberOfConstituents() > 0);
}

@Test
public void test4()
{
    Properties props = new Properties();
    props.setProperty("modelDirectory", "testModelDir");
    ResourceManager rm = new ResourceManager(props);
    NERAnnotator annotator = new NERAnnotator("DummyNER", ViewNames.NER, false) {
        @Override
        public void initialize(ResourceManager nerRm) {
            if (NER_ONTONOTES.equals(getViewName())) {
                nerRm = new NerOntonotesConfigurator().getConfig(nerRm);
            } else {
                nerRm = new NerBaseConfigurator().getConfig(nerRm);
            }
            this.params = Parameters.readConfigAndLoadExternalData(nerRm);
            this.params.forceNewSentenceOnLineBreaks = false;
            synchronized(NERAnnotator.LOADING_MODELS) {
                ModelLoader.load(nerRm, viewName, false, this.params);
            }
        }
    };
    annotator.initialize(rm);
    assertNotNull("Parameters should be initialized", annotator.getParameters());
    assertFalse("forceNewSentenceOnLineBreaks should be false", annotator.getParameters().forceNewSentenceOnLineBreaks);
}

