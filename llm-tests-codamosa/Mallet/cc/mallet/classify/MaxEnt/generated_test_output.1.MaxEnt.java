import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupLabel("positive", true);
    labelAlphabet.lookupLabel("negative", true);
    MaxEnt classifier = new MaxEnt() {
        @Override
        public LabelAlphabet getLabelAlphabet() {
            return labelAlphabet;
        }

        @Override
        public void getClassificationScores(Instance instance, double[] scores) {
            scores[0] = 0.8;
            scores[1] = 0.2;
        }
    };
    Instance instance = new Instance("sample text", null, "test-instance", null);
    Classification classification = classifier.classify(instance);
    assertNotNull(classification);
    assertEquals(instance, classification.getInstance());
    assertEquals(classifier, classification.getClassifier());
    LabelVector labelVector = classification.getLabelVector();
    assertEquals(0.8, labelVector.value(0), 1.0E-4);
    assertEquals(0.2, labelVector.value(1), 1.0E-4);
    assertEquals("positive", labelVector.getLabelAlphabet().lookupLabel(0).toString());
    assertEquals("negative", labelVector.getLabelAlphabet().lookupLabel(1).toString());
}

@Test
public void test2()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection mockFeatureSelection = new FeatureSelection();
    MaxEnt result = maxEnt.setFeatureSelection(mockFeatureSelection);
    assertSame("The returned object should be the same instance of MaxEnt", maxEnt, result);
}

@Test
public void test3()
{
    MaxEnt maxEnt = new MaxEnt();
    FeatureSelection mockFeatureSelection1 = new FeatureSelection("feature1", 1);
    FeatureSelection mockFeatureSelection2 = new FeatureSelection("feature2", 2);
    FeatureSelection[] inputArray = new FeatureSelection[]{ mockFeatureSelection1, mockFeatureSelection2 };
    MaxEnt result = maxEnt.setPerClassFeatureSelection(inputArray);
    assertSame("Returned instance should be the same as input object", maxEnt, result);
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
    assertSame("The returned FeatureSelection should be the one that was assigned", expectedFeatureSelection, actualFeatureSelection);
}

@Test
public void test5()
{
    MaxEnt maxEnt = new MaxEnt(null, null, null);
    double[] expected = new double[]{ 0.5, -1.2, 3.3 };
    Field parametersField = MaxEnt.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    parametersField.set(maxEnt, expected);
    double[] actual = maxEnt.getParameters();
    Assert.assertArrayEquals(expected, actual, 0.0);
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
        fail("Failed to set defaultFeatureIndex using reflection: " + e.getMessage());
    }
    int result = maxEnt.getDefaultFeatureIndex();
    assertEquals(42, result);
}

@Test
public void test7()
{
    Alphabet dataAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1", true);
    dataAlphabet.lookupIndex("feature2", true);
    Alphabet targetAlphabet = new Alphabet();
    targetAlphabet.lookupIndex("label1", true);
    targetAlphabet.lookupIndex("label2", true);
    targetAlphabet.lookupIndex("label3", true);
    Pipe mockPipe = new Pipe() {
        @Override
        public Alphabet getDataAlphabet() {
            return dataAlphabet;
        }

        @Override
        public Alphabet getTargetAlphabet() {
            return targetAlphabet;
        }
    };
    MaxEnt maxEnt = new MaxEnt();
    maxEnt.instancePipe = mockPipe;
    int expectedNumParameters = dataAlphabet.size() * targetAlphabet.size();
    assertEquals(expectedNumParameters, maxEnt.getNumParameters());
}

@Test
public void test8()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupIndex("positive", true);
    labelAlphabet.lookupIndex("negative", true);
    MaxEnt maxEntClassifier = new MaxEnt() {
        @Override
        public LabelAlphabet getLabelAlphabet() {
            return labelAlphabet;
        }

        @Override
        public void getUnnormalizedClassificationScores(Instance instance, double[] scores) {
            scores[0] = 2.0;
            scores[1] = 1.0;
        }
    };
    Instance instance = new Instance("test data", null, "test instance", null);
    double[] scores = new double[2];
    maxEntClassifier.getClassificationScores(instance, scores);
    double exp0 = Math.exp(2.0 - 2.0);
    double exp1 = Math.exp(1.0 - 2.0);
    double sum = exp0 + exp1;
    double expected0 = exp0 / sum;
    double expected1 = exp1 / sum;
    assertEquals(expected0, scores[0], 1.0E-6);
    assertEquals(expected1, scores[1], 1.0E-6);
    assertFalse(Double.isNaN(scores[0]));
    assertFalse(Double.isNaN(scores[1]));
}

@Test
public void test9()
{
    Alphabet dataAlphabet = new Alphabet();
    int featureIndex = dataAlphabet.lookupIndex("feature1");
    Alphabet labelAlphabet = new Alphabet();
    int labelIndex = labelAlphabet.lookupIndex("label1");
    FeatureVector fv = new FeatureVector(dataAlphabet, new int[]{ featureIndex }, new double[]{ 1.0 });
    Instance instance = new Instance(fv, null, null, null);
    MaxEnt classifier = new MaxEnt();
    classifier.instancePipe = new Pipe(dataAlphabet, labelAlphabet) {
        @Override
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    classifier.defaultFeatureIndex = 0;
    classifier.parameters = new double[]{ 2.5 };
    double[] scores = new double[1];
    classifier.getUnnormalizedClassificationScores(instance, scores);
    assertEquals(2.5 + (1.0 * 0.0), scores[0], 1.0E-6);
}

@Test
public void test10()
{
    MaxEnt maxEnt = new MaxEnt();
    Alphabet featureAlphabet = new Alphabet();
    featureAlphabet.lookupIndex("feature1", true);
    featureAlphabet.lookupIndex("feature2", true);
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupLabel("label0");
    labelAlphabet.lookupLabel("label1");
    try {
        Field dictField = MaxEnt.class.getDeclaredField("dict");
        dictField.setAccessible(true);
        dictField.set(maxEnt, featureAlphabet);
        Field labelDictField = MaxEnt.class.getDeclaredField("labelDict");
        labelDictField.setAccessible(true);
        labelDictField.set(maxEnt, labelAlphabet);
        Field defaultFeatureIndexField = MaxEnt.class.getDeclaredField("defaultFeatureIndex");
        defaultFeatureIndexField.setAccessible(true);
        defaultFeatureIndexField.setInt(maxEnt, 2);
        Field parametersField = MaxEnt.class.getDeclaredField("parameters");
        parametersField.setAccessible(true);
        parametersField.set(maxEnt, new double[]{ 0.8, -0.5, 0.1, -0.2, 0.9, -0.3 });
    } catch (Exception e) {
        fail("Failed to setup test: " + e.getMessage());
    }
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    maxEnt.printExtremeFeatures(pw, 1);
    pw.flush();
    String output = sw.toString();
    assertTrue(output.contains("FEATURES FOR CLASS label0"));
    assertTrue(output.contains("FEATURES FOR CLASS label1"));
    assertTrue(output.contains("feature1"));
    assertTrue(output.contains("feature2"));
    assertTrue(output.contains("<default> 0.1"));
    assertTrue(output.contains("<default> -0.3"));
}

@Test
public void test11()
{
    MaxEnt maxEnt = new MaxEnt();
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1", true);
    dict.lookupIndex("feature2", true);
    LabelAlphabet labelDict = new LabelAlphabet();
    labelDict.lookupIndex("labelA", true);
    try {
        Field dictField = MaxEnt.class.getDeclaredField("dict");
        dictField.setAccessible(true);
        dictField.set(maxEnt, dict);
        Field labelDictField = MaxEnt.class.getDeclaredField("labelDict");
        labelDictField.setAccessible(true);
        labelDictField.set(maxEnt, labelDict);
        int numFeatures = dict.size() + 1;
        int numLabels = labelDict.size();
        double[] parameters = new double[numLabels * numFeatures];
        parameters[0] = 0.5;
        parameters[1] = -1.2;
        parameters[2] = 2.0;
        Field parametersField = MaxEnt.class.getDeclaredField("parameters");
        parametersField.setAccessible(true);
        parametersField.set(maxEnt, parameters);
        Field defaultFeatureIndexField = MaxEnt.class.getDeclaredField("defaultFeatureIndex");
        defaultFeatureIndexField.setAccessible(true);
        defaultFeatureIndexField.setInt(maxEnt, 2);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    maxEnt.printRank(printWriter);
    printWriter.flush();
    String output = stringWriter.toString();
    assertTrue(output.contains("FEATURES FOR CLASS labelA"));
    assertTrue(output.contains("feature1"));
    assertTrue(output.contains("feature2"));
    assertTrue(output.contains("<default> 2.0"));
}

