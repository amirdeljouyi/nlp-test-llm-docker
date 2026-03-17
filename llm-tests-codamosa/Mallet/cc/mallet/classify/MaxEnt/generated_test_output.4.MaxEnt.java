import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("positive", true);
    labelAlphabet.lookupIndex("negative", true);
    Instance instance = new Instance("example text", null, "instance-1", null);
    MaxEnt classifier = new MaxEnt() {
        @Override
        public Alphabet getLabelAlphabet() {
            return labelAlphabet;
        }

        @Override
        public void getClassificationScores(Instance inst, double[] scores) {
            scores[0] = 0.7;
            scores[1] = 0.3;
        }
    };
    Classification classification = classifier.classify(instance);
    assertNotNull(classification);
    assertEquals(instance, classification.getInstance());
    LabelVector labelVector = classification.getLabelVector();
    assertEquals(labelAlphabet, labelVector.getAlphabet());
    assertEquals(0.7, labelVector.value(0), 1.0E-4);
    assertEquals(0.3, labelVector.value(1), 1.0E-4);
}

@Test
public void test2()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection mockFeatureSelection = new FeatureSelection();
    MaxEnt returnedInstance = maxEnt.setFeatureSelection(mockFeatureSelection);
    assertSame("Returned instance should be the same as original", maxEnt, returnedInstance);
    assertSame("FeatureSelection should be set correctly", mockFeatureSelection, returnedInstance.getFeatureSelection());
}

@Test
public void test3()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection featureSelection1 = new FeatureSelection();
    FeatureSelection featureSelection2 = new FeatureSelection();
    FeatureSelection[] featureSelectionArray = new FeatureSelection[]{ featureSelection1, featureSelection2 };
    MaxEnt result = maxEnt.setPerClassFeatureSelection(featureSelectionArray);
    assertSame("Returned instance should be the same as the input MaxEnt instance", maxEnt, result);
}

@Test
public void test4()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection expectedFeatureSelection = new FeatureSelection();
    Field featureSelectionField = MaxEnt.class.getDeclaredField("featureSelection");
    featureSelectionField.setAccessible(true);
    featureSelectionField.set(maxEnt, expectedFeatureSelection);
    FeatureSelection actualFeatureSelection = maxEnt.getFeatureSelection();
    assertSame("getFeatureSelection should return the correct FeatureSelection instance", expectedFeatureSelection, actualFeatureSelection);
}

@Test
public void test5()
{
    MaxEnt maxEnt = new MaxEnt();
    Pipe mockPipe = new Pipe() {
        private Alphabet dataAlphabet = new Alphabet();

        private Alphabet targetAlphabet = new Alphabet();

        @Override
        public Alphabet getDataAlphabet() {
            return dataAlphabet;
        }

        @Override
        public Alphabet getTargetAlphabet() {
            return targetAlphabet;
        }
    };
    maxEnt.instancePipe = mockPipe;
    mockPipe.getDataAlphabet().lookupIndex("feature1");
    mockPipe.getDataAlphabet().lookupIndex("feature2");
    mockPipe.getTargetAlphabet().lookupIndex("label1");
    mockPipe.getTargetAlphabet().lookupIndex("label2");
    mockPipe.getTargetAlphabet().lookupIndex("label3");
    int expectedParameters = 2 * 3;
    int actualParameters = maxEnt.getNumParameters();
    assertEquals(expectedParameters, actualParameters);
}

@Test
public void test6()
{
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("label1");
    labelAlphabet.lookupIndex("label2");
    labelAlphabet.lookupIndex("label3");
    MaxEnt classifier = new MaxEnt() {
        @Override
        public void getUnnormalizedClassificationScores(Instance instance, double[] scores) {
            scores[0] = 2.0;
            scores[1] = 1.0;
            scores[2] = 0.0;
        }

        @Override
        public Alphabet getLabelAlphabet() {
            return labelAlphabet;
        }
    };
    Instance instance = new Instance(null, null, null, null);
    double[] scores = new double[3];
    double temperature = 2.0;
    classifier.getClassificationScoresWithTemperature(instance, temperature, scores);
    assertEquals(3, scores.length);
    assertTrue(scores[0] > scores[1]);
    assertTrue(scores[1] > scores[2]);
    double sum = (scores[0] + scores[1]) + scores[2];
    assertEquals(1.0, sum, 1.0E-6);
    assertFalse(Double.isNaN(scores[0]));
    assertFalse(Double.isNaN(scores[1]));
    assertFalse(Double.isNaN(scores[2]));
    assertTrue(scores[0] > 0);
    assertTrue(scores[1] > 0);
    assertTrue(scores[2] > 0);
}

@Test
public void test7()
{
    Alphabet dataAlphabet = new Alphabet();
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    int f1 = dataAlphabet.lookupIndex("feat1");
    int f2 = dataAlphabet.lookupIndex("feat2");
    int labelIndex0 = labelAlphabet.lookupIndex("label0");
    int labelIndex1 = labelAlphabet.lookupIndex("label1");
    Pipe dummyPipe = new Pipe(dataAlphabet, labelAlphabet) {
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    int[] indices = new int[]{ f1, f2 };
    double[] values = new double[]{ 1.0, 2.0 };
    FeatureVector fv = new FeatureVector(dataAlphabet, indices, values);
    Instance instance = new Instance(fv, null, null, null);
    MaxEnt classifier = new MaxEnt();
    try {
        Field pipeField = MaxEnt.class.getDeclaredField("instancePipe");
        pipeField.setAccessible(true);
        pipeField.set(classifier, dummyPipe);
        Field labelField = MaxEnt.class.getDeclaredField("labelAlphabet");
        labelField.setAccessible(true);
        labelField.set(classifier, labelAlphabet);
        Field defaultFeatField = MaxEnt.class.getDeclaredField("defaultFeatureIndex");
        defaultFeatField.setAccessible(true);
        defaultFeatField.setInt(classifier, 2);
        Field paramField = MaxEnt.class.getDeclaredField("parameters");
        paramField.setAccessible(true);
        int numLabels = labelAlphabet.size();
        int numFeatures = 3;
        double[] parameters = new double[numLabels * numFeatures];
        parameters[(0 * numFeatures) + 0] = 1.0;
        parameters[(0 * numFeatures) + 1] = 0.5;
        parameters[(0 * numFeatures) + 2] = 2.0;
        parameters[(1 * numFeatures) + 0] = 0.3;
        parameters[(1 * numFeatures) + 1] = 0.6;
        parameters[(1 * numFeatures) + 2] = -1.0;
        paramField.set(classifier, parameters);
        Field featSelField = MaxEnt.class.getDeclaredField("featureSelection");
        featSelField.setAccessible(true);
        featSelField.set(classifier, null);
        Field perFeatSelField = MaxEnt.class.getDeclaredField("perClassFeatureSelection");
        perFeatSelField.setAccessible(true);
        perFeatSelField.set(classifier, null);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    double[] scores = new double[2];
    classifier.getUnnormalizedClassificationScores(instance, scores);
    assertEquals(4.0, scores[0], 1.0E-4);
    assertEquals(0.5, scores[1], 1.0E-4);
}

