import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Instance mockInstance = mock(Instance.class);
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("positive", true);
    labelAlphabet.lookupIndex("negative", true);
    MaxEnt maxEnt = spy(new MaxEnt(null, labelAlphabet, null));
    doReturn(labelAlphabet).when(maxEnt).getLabelAlphabet();
    doAnswer(( invocation) -> {
        double[] scores = invocation.getArgument(1);
        scores[0] = 0.65;
        scores[1] = 0.35;
        return null;
    }).when(maxEnt).getClassificationScores(eq(mockInstance), any(double[].class));
    Classification classification = maxEnt.classify(mockInstance);
    assertNotNull(classification);
    assertEquals(mockInstance, classification.getInstance());
    assertEquals(maxEnt, classification.getLabeling().getClassifier());
    LabelVector labelVector = ((LabelVector) (classification.getLabeling()));
    assertEquals(2, labelVector.size());
    assertEquals(labelAlphabet.lookupLabel(0), labelVector.getLabelAtRank(0));
    assertEquals(labelAlphabet.lookupLabel(1), labelVector.getLabelAtRank(1));
    assertEquals(0.65, labelVector.value(0), 1.0E-4);
    assertEquals(0.35, labelVector.value(1), 1.0E-4);
}

@Test
public void test2()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection fs = new FeatureSelection();
    MaxEnt returned = maxEnt.setFeatureSelection(fs);
    assertSame("Returned object should be the same instance as the original", maxEnt, returned);
    assertSame("FeatureSelection should be correctly assigned", fs, maxEnt.getFeatureSelection());
}

@Test
public void test3()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection fs1 = new FeatureSelection();
    FeatureSelection fs2 = new FeatureSelection();
    FeatureSelection[] featureSelections = new FeatureSelection[]{ fs1, fs2 };
    MaxEnt returned = maxEnt.setPerClassFeatureSelection(featureSelections);
    assertSame("Returned object should be the same instance", maxEnt, returned);
}

@Test
public void test4()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection expectedFeatureSelection = new FeatureSelection();
    try {
        Field field = MaxEnt.class.getDeclaredField("featureSelection");
        field.setAccessible(true);
        field.set(maxEnt, expectedFeatureSelection);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set featureSelection field: " + e.getMessage());
    }
    FeatureSelection actualFeatureSelection = maxEnt.getFeatureSelection();
    assertSame("FeatureSelection returned should match the one set", expectedFeatureSelection, actualFeatureSelection);
}

@Test
public void test5()
{
    MaxEnt maxEnt = new MaxEnt(null, null, null);
    double[] expectedParameters = new double[]{ 0.1, -0.2, 0.3 };
    Field parametersField = MaxEnt.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    parametersField.set(maxEnt, expectedParameters);
    double[] actualParameters = maxEnt.getParameters();
    assertArrayEquals(expectedParameters, actualParameters, 1.0E-6);
}

@Test
public void test6()
{
    MaxEnt maxEnt = new MaxEnt();
    try {
        Field field = MaxEnt.class.getDeclaredField("defaultFeatureIndex");
        field.setAccessible(true);
        field.setInt(maxEnt, 42);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Reflection error: " + e.getMessage());
    }
    assertEquals(42, maxEnt.getDefaultFeatureIndex());
}

@Test
public void test7()
{
    Pipe mockPipe = mock(Pipe.class);
    Alphabet mockDataAlphabet = new Alphabet();
    Alphabet mockTargetAlphabet = new Alphabet();
    when(mockPipe.getDataAlphabet()).thenReturn(mockDataAlphabet);
    when(mockPipe.getTargetAlphabet()).thenReturn(mockTargetAlphabet);
    mockDataAlphabet.lookupIndex("feature1");
    mockDataAlphabet.lookupIndex("feature2");
    mockTargetAlphabet.lookupIndex("class1");
    mockTargetAlphabet.lookupIndex("class2");
    MaxEnt maxEnt = new MaxEnt();
    maxEnt.instancePipe = mockPipe;
    int expected = mockDataAlphabet.size() * mockTargetAlphabet.size();
    assertEquals(expected, maxEnt.getNumParameters());
}

@Test
public void test8()
{
    Alphabet dataAlphabet = new Alphabet();
    Alphabet targetAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1");
    dataAlphabet.lookupIndex("feature2");
    dataAlphabet.lookupIndex("feature3");
    targetAlphabet.lookupIndex("label1");
    targetAlphabet.lookupIndex("label2");
    Pipe pipe = new Pipe(dataAlphabet, targetAlphabet) {
        @Override
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    ArrayList<Pipe> pipes = new ArrayList<>();
    pipes.add(pipe);
    Pipe serialPipe = new SerialPipes(pipes);
    MaxEnt classifier = new MaxEnt(serialPipe);
    int expectedParameters = dataAlphabet.size() * targetAlphabet.size();
    int actualParameters = classifier.getNumParameters();
    assertEquals(expectedParameters, actualParameters);
}

@Test
public void test9()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupIndex("positive", true);
    labelAlphabet.lookupIndex("negative", true);
    MaxEnt classifier = new MaxEnt() {
        @Override
        public void getUnnormalizedClassificationScores(Instance instance, double[] scores) {
            scores[0] = 2.0;
            scores[1] = 1.0;
        }

        @Override
        public Alphabet getLabelAlphabet() {
            return labelAlphabet;
        }
    };
    Instance instance = new Instance(null, null, null, null);
    double[] scores = new double[2];
    classifier.getClassificationScores(instance, scores);
    double exp0 = Math.exp(2.0);
    double exp1 = Math.exp(1.0);
    double sum = exp0 + exp1;
    double expected0 = exp0 / sum;
    double expected1 = exp1 / sum;
    assertEquals(expected0, scores[0], 1.0E-6);
    assertEquals(expected1, scores[1], 1.0E-6);
}

@Test
public void test10()
{
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("label1");
    labelAlphabet.lookupIndex("label2");
    labelAlphabet.lookupIndex("label3");
    Alphabet featureAlphabet = new Alphabet();
    featureAlphabet.lookupIndex("feature1");
    double[] featureValues = new double[]{ 1.0 };
    int[] featureIndices = new int[]{ 0 };
    FeatureVector fv = new FeatureVector(featureAlphabet, featureIndices, featureValues);
    Instance instance = new Instance(fv, null, null, null);
    MaxEnt classifier = new MaxEnt() {
        @Override
        public Alphabet getLabelAlphabet() {
            return labelAlphabet;
        }

        @Override
        public void getUnnormalizedClassificationScores(Instance inst, double[] scores) {
            scores[0] = 2.0;
            scores[1] = 1.0;
            scores[2] = 0.0;
        }
    };
    double[] scores = new double[3];
    double temperature = 1.0;
    classifier.getClassificationScoresWithTemperature(instance, temperature, scores);
    double sumExp = (Math.exp(2.0) + Math.exp(1.0)) + Math.exp(0.0);
    double[] expected = new double[]{ Math.exp(2.0) / sumExp, Math.exp(1.0) / sumExp, Math.exp(0.0) / sumExp };
    assertEquals(expected[0], scores[0], 1.0E-6);
    assertEquals(expected[1], scores[1], 1.0E-6);
    assertEquals(expected[2], scores[2], 1.0E-6);
}

@Test
public void test11()
{
    Alphabet dataAlphabet = new Alphabet();
    int featureIndex = dataAlphabet.lookupIndex("featureA");
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    int labelIndex = labelAlphabet.lookupIndex("labelX");
    FeatureVector fv = new FeatureVector(dataAlphabet, new int[]{ featureIndex }, new double[]{ 1.0 });
    Instance instance = new Instance(fv, null, null, null);
    Pipe dummyPipe = new Pipe(dataAlphabet, labelAlphabet) {
        @Override
        public Instance pipe(Instance inst) {
            return inst;
        }
    };
    MaxEnt maxEnt = new MaxEnt();
    maxEnt.instancePipe = dummyPipe;
    maxEnt.defaultFeatureIndex = featureIndex;
    int numLabels = labelAlphabet.size();
    int numFeatures = maxEnt.defaultFeatureIndex + 1;
    maxEnt.parameters = new double[numLabels * numFeatures];
    int paramIndex = (labelIndex * numFeatures) + maxEnt.defaultFeatureIndex;
    maxEnt.parameters[paramIndex] = 2.5;
    double[] scores = new double[numLabels];
    maxEnt.getUnnormalizedClassificationScores(instance, scores);
    assertEquals(2.5, scores[0], 1.0E-4);
}

@Test
public void test12()
{
}
{
    LabelAlphabet labelDict = new LabelAlphabet();
    labelDict.lookupIndex("label1", true);
    return labelDict;
}
{
    @Override
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1", true);
    return dict;
}


