import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String[] args = new String[]{ "config/path/to/config.properties" };
    NERAnnotator.main(args);
    File l1Output = new File("L1FeatureWeights.tsv");
    File l2Output = new File("L2FeatureWeights.tsv");
    Assert.assertFalse(l1Output.exists());
    Assert.assertFalse(l2Output.exists());
}

@Test
public void test2()
{
    String[] tokens = new String[]{ "Barack", "Obama", "visited", "Berlin" };
    String sentence = String.join(" ", tokens);
    TokenizerTextAnnotationBuilder tokenizer = new TokenizerTextAnnotationBuilder(new DummyTokenizer());
    TextAnnotation ta = tokenizer.createTextAnnotation("testCorpus", "testId", sentence);
    NERAnnotator annotator = new NERAnnotator("NER_CONLL", true);
    annotator.addView(ta);
    assertTrue(ta.hasView("NER_CONLL"));
    View nerView = ta.getView("NER_CONLL");
    assertNotNull(nerView);
    assertTrue(nerView.getConstituents().size() >= 0);
}

@Test
public void test3()
{
    ResourceManager inputConfig = new NerBaseConfigurator().getDefaultConfig();
    NERAnnotator annotator = new NERAnnotator("NER_CONLL") {
        @Override
        public String getViewName() {
            return "NER_CONLL";
        }
    };
    annotator.initialize(inputConfig);
    assertNotNull("Parameters should be initialized", annotator.params);
    assertFalse("forceNewSentenceOnLineBreaks should be false", annotator.params.forceNewSentenceOnLineBreaks);
}


