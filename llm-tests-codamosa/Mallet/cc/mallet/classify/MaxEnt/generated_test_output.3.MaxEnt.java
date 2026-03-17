import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MaxEnt maxEntMock = mock(MaxEnt.class);
    Instance mockInstance = mock(Instance.class);
    Alphabet mockAlphabet = mock(Alphabet.class);
    when(maxEntMock.getLabelAlphabet()).thenReturn(mockAlphabet);
    when(mockAlphabet.size()).thenReturn(3);
    doAnswer(( invocation) -> {
        Object[] args = invocation.getArguments();
        double[] scores = ((double[]) (args[1]));
        scores[0] = 0.3;
        scores[1] = 0.5;
        scores[2] = 0.2;
        return null;
    }).when(maxEntMock).getClassificationScores(eq(mockInstance), any(double[].class));
    MaxEnt classifier = new MaxEnt() {
        @Override
        public Alphabet getLabelAlphabet() {
            return mockAlphabet;
        }

        @Override
        public void getClassificationScores(Instance instance, double[] scores) {
            scores[0] = 0.3;
            scores[1] = 0.5;
            scores[2] = 0.2;
        }
    };
    Classification classification = classifier.classify(mockInstance);
    assertNotNull(classification);
    assertEquals(3, classification.getLabelVector().length());
    LabelVector labelVector = classification.getLabelVector();
    assertEquals(0.3, labelVector.value(0), 1.0E-6);
    assertEquals(0.5, labelVector.value(1), 1.0E-6);
    assertEquals(0.2, labelVector.value(2), 1.0E-6);
}

@Test
public void test2()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection mockFeatureSelection = new FeatureSelection();
    MaxEnt result = maxEnt.setFeatureSelection(mockFeatureSelection);
    assertSame("Returned object should be the same instance", maxEnt, result);
    assertEquals("FeatureSelection should be assigned correctly via reflection", mockFeatureSelection, getPrivateFeatureSelection(maxEnt));
}

@Test
public void test3()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection fs1 = new FeatureSelection();
    FeatureSelection fs2 = new FeatureSelection();
    FeatureSelection[] fssArray = new FeatureSelection[]{ fs1, fs2 };
    MaxEnt returned = maxEnt.setPerClassFeatureSelection(fssArray);
    assertSame("Returned object should be the same instance", maxEnt, returned);
}

@Test
public void test4()
{
    FeatureSelection expectedFeatureSelection = new FeatureSelection();
    MaxEnt maxEnt = new MaxEnt();
    try {
        Field field = MaxEnt.class.getDeclaredField("featureSelection");
        field.setAccessible(true);
        field.set(maxEnt, expectedFeatureSelection);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set featureSelection field via reflection: " + e.getMessage());
    }
    FeatureSelection actualFeatureSelection = maxEnt.getFeatureSelection();
    assertSame("The returned FeatureSelection should be the same instance as set", expectedFeatureSelection, actualFeatureSelection);
}

@Test
public void test5()
{
    MaxEnt maxEnt = new MaxEnt();
    Field field = MaxEnt.class.getDeclaredField("defaultFeatureIndex");
    field.setAccessible(true);
    field.setInt(maxEnt, 42);
    int result = maxEnt.getDefaultFeatureIndex();
    assertEquals(42, result);
}

@Test
public void test6()
{
    Alphabet dataAlphabet = new Alphabet();
    Alphabet targetAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1");
    dataAlphabet.lookupIndex("feature2");
    targetAlphabet.lookupIndex("classA");
    targetAlphabet.lookupIndex("classB");
    Pipe pipe = new Pipe(dataAlphabet, targetAlphabet) {
        @Override
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    MaxEnt maxEnt = new MaxEnt();
    maxEnt.instancePipe = pipe;
    int expectedParameters = MaxEnt.getNumParameters(pipe);
    int actualParameters = maxEnt.getNumParameters();
    assertEquals(expectedParameters, actualParameters);
}

@Test
public void test7()
{
    Alphabet dataAlphabet = new Alphabet();
    Alphabet targetAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1");
    dataAlphabet.lookupIndex("feature2");
    targetAlphabet.lookupIndex("label1");
    targetAlphabet.lookupIndex("label2");
    Pipe dummyPipe = new Pipe(dataAlphabet, targetAlphabet) {
        @Override
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    MaxEnt maxEnt = new MaxEnt();
    maxEnt.instancePipe = dummyPipe;
    int expected = MaxEnt.getNumParameters(dummyPipe);
    int actual = maxEnt.getNumParameters();
    assertEquals(expected, actual);
}

@Test
public void test8()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupIndex("positive", true);
    labelAlphabet.lookupIndex("negative", true);
    MaxEnt classifier = new MaxEnt() {
        @Override
        public void getUnnormalizedClassificationScores(Instance instance, double[] scores) {
            scores[0] = 3.0;
            scores[1] = 1.0;
        }

        @Override
        public Alphabet getLabelAlphabet() {
            return labelAlphabet;
        }
    };
    Instance instance = new Instance("sample", null, "name", null);
    double[] scores = new double[2];
    classifier.getClassificationScores(instance, scores);
    double exp0 = Math.exp(3.0 - 3.0);
    double exp1 = Math.exp(1.0 - 3.0);
    double sum = exp0 + exp1;
    double expected0 = exp0 / sum;
    double expected1 = exp1 / sum;
    assertEquals(expected0, scores[0], 1.0E-6);
    assertEquals(expected1, scores[1], 1.0E-6);
}

@Test
public void test9()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    int labelIndex1 = labelAlphabet.lookupIndex("positive");
    int labelIndex2 = labelAlphabet.lookupIndex("negative");
    MaxEnt classifier = new MaxEnt() {
        @Override
        public void getUnnormalizedClassificationScores(Instance instance, double[] scores) {
            scores[0] = 3.0;
            scores[1] = 1.0;
        }

        @Override
        public LabelAlphabet getLabelAlphabet() {
            return labelAlphabet;
        }
    };
    Instance dummyInstance = new Instance("sample", null, null, null);
    double[] scores = new double[2];
    double temperature = 2.0;
    classifier.getClassificationScoresWithTemperature(dummyInstance, temperature, scores);
    double s0 = Math.exp((3.0 / temperature) - (3.0 / temperature));
    double s1 = Math.exp((1.0 / temperature) - (3.0 / temperature));
    double sum = s0 + s1;
    double expected0 = s0 / sum;
    double expected1 = s1 / sum;
    assertEquals(expected0, scores[0], 1.0E-6);
    assertEquals(expected1, scores[1], 1.0E-6);
    assertFalse(Double.isNaN(scores[0]));
    assertFalse(Double.isNaN(scores[1]));
}

@Test
public void test10()
{
    Alphabet dataAlphabet = new Alphabet();
    int featureIndex = dataAlphabet.lookupIndex("feature1");
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    int labelIndex = labelAlphabet.lookupIndex("label1");
    FeatureVector fv = new FeatureVector(dataAlphabet, new int[]{ featureIndex }, new double[]{ 2.0 });
    Instance instance = new Instance(fv, null, null, null);
    MaxEnt classifier = new MaxEnt();
    classifier.instancePipe = new SerialPipes(new ArrayList<>());
    classifier.instancePipe.setDataAlphabet(dataAlphabet);
    classifier.setLabelAlphabet(labelAlphabet);
    classifier.defaultFeatureIndex = featureIndex;
    int numLabels = labelAlphabet.size();
    int numFeatures = classifier.defaultFeatureIndex + 1;
    classifier.parameters = new double[numLabels * numFeatures];
    classifier.parameters[(0 * numFeatures) + featureIndex] = 1.5;
    double[] scores = new double[numLabels];
    classifier.getUnnormalizedClassificationScores(instance, scores);
    double expectedScore = 1.5 + (2.0 * 1.5);
    assertEquals(expectedScore, scores[0], 1.0E-4);
}

@Test
public void test11()
{
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    MaxEnt classifier = new MaxEnt();
    classifier.print();
    String output = outContent.toString();
    assertTrue("Output should contain class label or header", (output != null) && (!output.trim().isEmpty()));
    System.setOut(originalOut);
}

@Test
public void test12()
{
}
{
    return this.alphabet;
}
{
    return this.labelAlphabet;
}
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    LabelAlphabet labelDict = new LabelAlphabet();
    labelDict.lookupIndex("class1");
    this.alphabet = dict;
    this.labelAlphabet = labelDict;
    this.parameters = new double[]{ 1.5, -0.5 };
    this.defaultFeatureIndex = 1;
}

@Test
public void test13()
{
    MaxEnt maxEnt = new MaxEnt();
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1", true);
    dict.lookupIndex("feature2", true);
    dict.lookupIndex("feature3", true);
    LabelAlphabet labelDict = new LabelAlphabet();
    labelDict.lookupIndex("labelA", true);
    labelDict.lookupIndex("labelB", true);
    maxEnt.setAlphabet(dict);
    maxEnt.setLabelAlphabet(labelDict);
    maxEnt.defaultFeatureIndex = 3;
    maxEnt.parameters = new double[]{ 1.0, 0.5, -0.1, 0.3, 0.8, 0.2 };
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    maxEnt.printRank(printWriter);
    printWriter.flush();
    String output = stringWriter.toString();
    assertTrue(output.contains("FEATURES FOR CLASS labelA"));
    assertTrue(output.contains("FEATURES FOR CLASS labelB"));
    assertTrue(output.contains("<default> -0.1"));
    assertTrue(output.contains("<default> 0.2"));
}

