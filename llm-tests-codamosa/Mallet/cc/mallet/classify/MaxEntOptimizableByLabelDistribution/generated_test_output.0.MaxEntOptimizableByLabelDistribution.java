import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MaxEntOptimizableByLabelDistribution optimizer = new MaxEntOptimizableByLabelDistribution(null);
    double expectedVariance = 0.75;
    MaxEntOptimizableByLabelDistribution returnedOptimizer = optimizer.setGaussianPriorVariance(expectedVariance);
    assertSame("Returned object should be same as original", optimizer, returnedOptimizer);
    assertEquals("Gaussian prior variance should be set correctly", expectedVariance, optimizer.gaussianPriorVariance, 1.0E-6);
}

@Test
public void test2()
{
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution(null, null);
    MaxEntOptimizableByLabelDistribution result = instance.useGaussianPrior();
    Assert.assertSame("useGaussianPrior should return the same instance", instance, result);
}

@Test
public void test3()
{
    double[] testParameters = new double[]{ 1.0, -2.5, 3.14 };
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution(null, null);
    try {
        Field parametersField = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("parameters");
        parametersField.setAccessible(true);
        parametersField.set(instance, testParameters);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Reflection error setting parameters field: " + e.getMessage());
    }
    double result = instance.getParameter(1);
    assertEquals(-2.5, result, 1.0E-6);
}

@Test
public void test4()
{
    Alphabet inputAlphabet = new Alphabet();
    LabelAlphabet targetAlphabet = new LabelAlphabet();
    inputAlphabet.lookupIndex("feature1", true);
    int labelIndex = targetAlphabet.lookupIndex("labelA", true);
    Label label = targetAlphabet.lookupLabel(labelIndex);
    double[] values = new double[]{ 1.0 };
    int[] indices = new int[]{ inputAlphabet.lookupIndex("feature1") };
    FeatureVector fv = new FeatureVector(inputAlphabet, indices, values);
    Instance instance = new Instance(fv, label, "instance1", null);
    Labeling labeling = new Labeling() {
        public int numLocations() {
            return 1;
        }

        public int indexAtLocation(int pos) {
            return label.getIndex();
        }

        public double valueAtLocation(int pos) {
            return 1.0;
        }

        public Label getLabelAtRank(int rank) {
            return label;
        }

        public double getValueAtRank(int rank) {
            return 1.0;
        }

        public int getBestIndex() {
            return label.getIndex();
        }

        public double getBestValue() {
            return 1.0;
        }

        public String toString() {
            return label.toString();
        }
    };
    instance.setLabeling(labeling);
    InstanceList trainingList = new InstanceList(inputAlphabet, targetAlphabet) {
        public double getInstanceWeight(Instance i) {
            return 1.0;
        }
    };
    trainingList.addThruPipe(instance);
    Classifier classifier = new Classifier(inputAlphabet, targetAlphabet, null) {
        public double[] getClassificationScores(Instance inst, double[] scores) {
            if ((scores == null) || (scores.length != targetAlphabet.size())) {
                scores = new double[targetAlphabet.size()];
            }
            scores[label.getIndex()] = 1.0;
            return scores;
        }
    };
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution(classifier, trainingList);
    optimizable.cachedValueStale = true;
    optimizable.parameters = new double[]{ 0.0 };
    optimizable.numFeatures = 1;
    optimizable.numLabels = 1;
    optimizable.gaussianPriorVariance = 1.0;
    optimizable.cachedGradient = new double[1 * 1];
    optimizable.defaultFeatureIndex = 0;
    double value = optimizable.getValue();
    assertTrue("Returned value should be a finite number", Double.isFinite(value));
}

@Test
public void test5()
{
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution(null);
    Field field = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("numGetValueCalls");
    field.setAccessible(true);
    field.setInt(optimizable, 3);
    int result = optimizable.getValueCalls();
    assertEquals(3, result);
}

@Test
public void test6()
{
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution();
    int numParams = 4;
    double[] parameters = new double[]{ 0.5, -1.0, 2.0, Double.NEGATIVE_INFINITY };
    double[] constraints = new double[]{ 1.0, 0.0, -0.5, 2.0 };
    double[] cachedGradient = new double[]{ -0.2, 0.3, -1.3, 0.7 };
    double gaussianPriorVariance = 2.0;
    try {
        Field f1 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("parameters");
        f1.setAccessible(true);
        f1.set(optimizable, parameters);
        Field f2 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("constraints");
        f2.setAccessible(true);
        f2.set(optimizable, constraints);
        Field f3 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("cachedGradient");
        f3.setAccessible(true);
        f3.set(optimizable, cachedGradient.clone());
        Field f4 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("gaussianPriorVariance");
        f4.setAccessible(true);
        f4.set(optimizable, gaussianPriorVariance);
        Field f5 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("cachedGradientStale");
        f5.setAccessible(true);
        f5.setBoolean(optimizable, true);
        Field f6 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("cachedValueStale");
        f6.setAccessible(true);
        f6.setBoolean(optimizable, false);
        Field f7 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("numLabels");
        f7.setAccessible(true);
        f7.setInt(optimizable, 1);
        Field f8 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("numFeatures");
        f8.setAccessible(true);
        f8.setInt(optimizable, 4);
        Field f9 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("featureSelection");
        f9.setAccessible(true);
        f9.set(optimizable, null);
        Field f10 = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("perLabelFeatureSelection");
        f10.setAccessible(true);
        f10.set(optimizable, null);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    double[] buffer = new double[4];
    optimizable.getValueGradient(buffer);
    double[] expectedGradient = new double[4];
    double[] baseGradient = new double[]{ -0.2, 0.3, -1.3, 0.7 };
    expectedGradient[0] = (baseGradient[0] + constraints[0]) + (((-1.0) / gaussianPriorVariance) * parameters[0]);
    expectedGradient[1] = (baseGradient[1] + constraints[1]) + (((-1.0) / gaussianPriorVariance) * parameters[1]);
    expectedGradient[2] = (baseGradient[2] + constraints[2]) + (((-1.0) / gaussianPriorVariance) * parameters[2]);
    expectedGradient[3] = baseGradient[3] + constraints[3];
    expectedGradient[3] = 0.0;
    assertArrayEquals(expectedGradient, buffer, 1.0E-8);
}

@Test
public void test7()
{
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution(null);
    Field parametersField = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    parametersField.set(optimizable, new double[]{ 1.0, 2.0, 3.0 });
    Field valueStaleField = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("cachedValueStale");
    valueStaleField.setAccessible(true);
    valueStaleField.set(optimizable, false);
    Field gradientStaleField = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("cachedGradientStale");
    gradientStaleField.setAccessible(true);
    gradientStaleField.set(optimizable, false);
    double[] input = new double[]{ 4.0, 5.0, 6.0 };
    optimizable.setParameters(input);
    double[] result = ((double[]) (parametersField.get(optimizable)));
    assertArrayEquals(new double[]{ 4.0, 5.0, 6.0 }, result, 1.0E-5);
    assertTrue(((Boolean) (valueStaleField.get(optimizable))));
    assertTrue(((Boolean) (gradientStaleField.get(optimizable))));
}

