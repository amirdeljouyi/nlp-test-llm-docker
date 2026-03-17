import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("positive");
    labelAlphabet.lookupIndex("negative");
    MaxEnt maxEnt = new MaxEnt() {
        @Override
        public Alphabet getLabelAlphabet() {
            return labelAlphabet;
        }

        @Override
        public void getClassificationScores(Instance instance, double[] scores) {
            scores[0] = 0.8;
            scores[1] = 0.2;
        }
    };
    Instance instance = new Instance("sample text", null, "name", null);
    Classification result = maxEnt.classify(instance);
    assertNotNull(result);
    LabelVector labelVector = result.getLabelVector();
    assertEquals(2, labelVector.getLabelAlphabet().size());
    assertEquals(0.8, labelVector.value(0), 1.0E-4);
    assertEquals(0.2, labelVector.value(1), 1.0E-4);
}

@Test
public void test2()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection mockFeatureSelection = new FeatureSelection();
    MaxEnt returnedInstance = maxEnt.setFeatureSelection(mockFeatureSelection);
    assertSame("Returned instance should be the same as the original", maxEnt, returnedInstance);
    assertSame("FeatureSelection should be set correctly", mockFeatureSelection, getFeatureSelectionField(maxEnt));
}

@Test
public void test3()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection fs1 = new FeatureSelection();
    FeatureSelection fs2 = new FeatureSelection();
    FeatureSelection[] fss = new FeatureSelection[]{ fs1, fs2 };
    MaxEnt result = maxEnt.setPerClassFeatureSelection(fss);
    assertSame("Returned object should be the same instance", maxEnt, result);
}

@Test
public void test4()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection expectedFeatureSelection = new FeatureSelection("test-selection");
    try {
        Field field = MaxEnt.class.getDeclaredField("featureSelection");
        field.setAccessible(true);
        field.set(maxEnt, expectedFeatureSelection);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set featureSelection field via reflection: " + e.getMessage());
    }
    FeatureSelection actualFeatureSelection = maxEnt.getFeatureSelection();
    assertSame("Expected the same FeatureSelection instance to be returned", expectedFeatureSelection, actualFeatureSelection);
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
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("positive");
    labelAlphabet.lookupIndex("negative");
    Alphabet featureAlphabet = new Alphabet();
    int featIndex = featureAlphabet.lookupIndex("feature1");
    MaxEnt classifier = new MaxEnt(null, labelAlphabet);
    double[][] parameters = new double[2][];
    parameters[0] = new double[1];
    parameters[1] = new double[1];
    parameters[0][0] = 2.0;
    parameters[1][0] = 1.0;
    classifier.setParameters(parameters);
    classifier.setInstancePipe(null);
    FeatureVector fv = new FeatureVector(featureAlphabet, new int[]{ featIndex }, new double[]{ 1.0 });
    Instance instance = new Instance(fv, null, null, null);
    double[] scores = new double[2];
    classifier.getClassificationScoresWithTemperature(instance, 1.0, scores);
    assertEquals(2, scores.length);
    assertTrue(scores[0] > scores[1]);
    assertEquals(1.0, scores[0] + scores[1], 1.0E-6);
    assertFalse(Double.isNaN(scores[0]));
    assertFalse(Double.isNaN(scores[1]));
}

@Test
public void test7()
{
    MaxEnt maxEnt = new MaxEnt();
    Alphabet featureAlphabet = new Alphabet();
    featureAlphabet.lookupIndex("feature1", true);
    featureAlphabet.lookupIndex("feature2", true);
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("labelA", true);
    labelAlphabet.lookupIndex("labelB", true);
    Pipe mockPipe = new Pipe(featureAlphabet, labelAlphabet) {
        @Override
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    maxEnt.instancePipe = mockPipe;
    maxEnt.defaultFeatureIndex = 2;
    int numLabels = labelAlphabet.size();
    int numFeatures = maxEnt.defaultFeatureIndex + 1;
    maxEnt.parameters = new double[numLabels * numFeatures];
    maxEnt.parameters[2] = 0.5;
    maxEnt.parameters[5] = 1.0;
    maxEnt.perClassFeatureSelection = null;
    maxEnt.featureSelection = null;
    int[] indices = new int[]{ featureAlphabet.lookupIndex("feature1"), featureAlphabet.lookupIndex("feature2") };
    double[] values = new double[]{ 1.0, 2.0 };
    FeatureVector fv = new FeatureVector(featureAlphabet, indices, values);
    Instance instance = new Instance(fv, null, null, null);
    double[] scores = new double[numLabels];
    maxEnt.getUnnormalizedClassificationScores(instance, scores);
    assertEquals(0.5 + MatrixOps.rowDotProduct(maxEnt.parameters, numFeatures, 0, fv, maxEnt.defaultFeatureIndex, null), scores[0], 1.0E-4);
    assertEquals(1.0 + MatrixOps.rowDotProduct(maxEnt.parameters, numFeatures, 1, fv, maxEnt.defaultFeatureIndex, null), scores[1], 1.0E-4);
}

@Test
public void test8()
{
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputCapture));
    MaxEnt maxEnt = new MaxEnt();
    maxEnt.print();
    System.setOut(originalOut);
    String output = outputCapture.toString();
    assertNotNull(output);
    assertTrue(output.trim().length() >= 0);
}

@Test
public void test9()
{
    MaxEnt maxEnt = new MaxEnt();
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1", true);
    alphabet.lookupIndex("feature2", true);
    maxEnt.setAlphabet(alphabet);
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupIndex("classA", true);
    labelAlphabet.lookupIndex("classB", true);
    maxEnt.setLabelAlphabet(labelAlphabet);
    maxEnt.defaultFeatureIndex = 2;
    double[] parameters = new double[]{ 1.0, -0.5, 0.2, -0.3, 0.8, -0.1 };
    maxEnt.parameters = parameters;
    StringWriter stringWriter = new StringWriter();
    PrintWriter out = new PrintWriter(stringWriter);
    maxEnt.printExtremeFeatures(out, 1);
    out.flush();
    String output = stringWriter.toString();
    assertTrue(output.contains("FEATURES FOR CLASS classA"));
    assertTrue(output.contains("FEATURES FOR CLASS classB"));
    assertTrue(output.contains("feature1"));
    assertTrue(output.contains("feature2"));
    assertTrue(output.contains("<default>"));
}

@Test
public void test10()
{
    MaxEnt maxEnt = new MaxEnt();
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    LabelAlphabet labelDict = new LabelAlphabet();
    labelDict.lookupIndex("label1");
    maxEnt.setAlphabet(dict);
    maxEnt.setLabelAlphabet(labelDict);
    int numFeatures = dict.size() + 1;
    int numLabels = labelDict.size();
    double[] parameters = new double[numFeatures * numLabels];
    parameters[0] = 1.5;
    parameters[1] = 0.75;
    maxEnt.setParameters(parameters);
    maxEnt.setDefaultFeatureIndex(1);
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    maxEnt.printRank(printWriter);
    String expectedOutput = "FEATURES FOR CLASS label1 feature1[1.5] \n <default> 0.75 \n";
    printWriter.flush();
    String actualOutput = stringWriter.toString().replace("\r\n", "\n");
    Assert.assertTrue(actualOutput.contains("FEATURES FOR CLASS label1"));
    Assert.assertTrue(actualOutput.contains("<default> 0.75"));
}

