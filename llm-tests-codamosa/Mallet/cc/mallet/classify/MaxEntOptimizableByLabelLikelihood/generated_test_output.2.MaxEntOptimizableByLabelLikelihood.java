import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null);
    Field classifierField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("theClassifier");
    classifierField.setAccessible(true);
}

@Test
public void test2()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood();
    double variance = 1.5;
    MaxEntOptimizableByLabelLikelihood returned = optimizable.setGaussianPriorVariance(variance);
    assertTrue("usingGaussianPrior should be true", optimizable.usingGaussianPrior);
    assertFalse("usingHyperbolicPrior should be false", optimizable.usingHyperbolicPrior);
    assertEquals("gaussianPriorVariance should be set correctly", variance, optimizable.gaussianPriorVariance, 1.0E-5);
    assertSame("Returned object should be the same as input", optimizable, returned);
}

@Test
public void test3()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null);
    double sharpnessValue = 2.5;
    MaxEntOptimizableByLabelLikelihood result = optimizable.setHyperbolicPriorSharpness(sharpnessValue);
    Assert.assertSame("Returned instance should be the same as original", optimizable, result);
    Assert.assertFalse("usingGaussianPrior should be false", optimizable.usingGaussianPrior);
    Assert.assertTrue("usingHyperbolicPrior should be true", optimizable.usingHyperbolicPrior);
    Assert.assertEquals("hyperbolicPriorSharpness should be set correctly", sharpnessValue, optimizable.hyperbolicPriorSharpness, 1.0E-6);
}

@Test
public void test4()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null);
    double testSlope = 3.14;
    MaxEntOptimizableByLabelLikelihood result = optimizable.setHyperbolicPriorSlope(testSlope);
    assertSame("Returned object should be the same instance", optimizable, result);
    assertFalse("Gaussian prior should be disabled", result.usingGaussianPrior);
    assertTrue("Hyperbolic prior should be enabled", result.usingHyperbolicPrior);
    assertEquals("Hyperbolic prior slope should be set correctly", testSlope, result.hyperbolicPriorSlope, 1.0E-5);
}

@Test
public void test5()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null);
    MaxEntOptimizableByLabelLikelihood returned = optimizable.useGaussianPrior();
    assertTrue("Gaussian prior should be enabled", getPrivateBooleanField(optimizable, "usingGaussianPrior"));
    assertFalse("Hyperbolic prior should be disabled", getPrivateBooleanField(optimizable, "usingHyperbolicPrior"));
    assertSame("Method should return the same instance", optimizable, returned);
}

@Test
public void test6()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood();
    optimizable.usingGaussianPrior = true;
    optimizable.usingHyperbolicPrior = false;
    MaxEntOptimizableByLabelLikelihood result = optimizable.useHyperbolicPrior();
    Assert.assertSame(optimizable, result);
    Assert.assertFalse(result.usingGaussianPrior);
    Assert.assertTrue(result.usingHyperbolicPrior);
}

@Test
public void test7()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood();
    optimizable.useNoPrior();
    Assert.assertFalse(optimizable.usingGaussianPrior);
    Assert.assertFalse(optimizable.usingHyperbolicPrior);
}

@Test
public void test8()
{
    Alphabet dataAlphabet = new Alphabet();
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupLabel("positive");
    labelAlphabet.lookupLabel("negative");
    int featIndex1 = dataAlphabet.lookupIndex("feature1");
    int featIndex2 = dataAlphabet.lookupIndex("feature2");
    FeatureVector fv = new FeatureVector(dataAlphabet, new int[]{ featIndex1, featIndex2 }, new double[]{ 1.0, 1.0 });
    Label label = labelAlphabet.lookupLabel("positive");
    Labeling labeling = labelAlphabet.getLabelVector(label);
    Instance instance = new Instance(fv, labeling, "inst1", null);
    InstanceList trainingList = new InstanceList(dataAlphabet, labelAlphabet);
    trainingList.add(instance);
    Classifier classifier = new Classifier(dataAlphabet, labelAlphabet) {
        @Override
        public Classification classify(Instance inst) {
            double[] scores = new double[]{ 0.8, 0.2 };
            return new Classification(inst, this, scores);
        }

        @Override
        public double[] getClassificationScores(Instance inst, double[] scores) {
            scores[0] = 0.8;
            scores[1] = 0.2;
            return scores;
        }
    };
    int numLabels = labelAlphabet.size();
    int numFeatures = dataAlphabet.size() + 1;
    double[] parameters = new double[numLabels * numFeatures];
    double[] cachedGradient = new double[numLabels * numFeatures];
    MaxEntOptimizableByLabelLikelihood opt = new MaxEntOptimizableByLabelLikelihood(classifier, trainingList);
    opt.cachedValueStale = true;
    opt.cachedGradient = cachedGradient;
    opt.numFeatures = numFeatures;
    opt.numLabels = numLabels;
    opt.parameters = parameters;
    opt.defaultFeatureIndex = dataAlphabet.size();
    opt.usingGaussianPrior = false;
    opt.usingHyperbolicPrior = false;
    opt.gaussianPriorVariance = 1.0;
    opt.hyperbolicPriorSlope = 1.0;
    opt.hyperbolicPriorSharpness = 1.0;
    double expectedNegLogLikelihood = -Math.log(0.8);
    double result = opt.getValue();
    assertEquals(expectedNegLogLikelihood, result, 1.0E-6);
}

@Test
public void test9()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood();
    double[] parameters = new double[]{ 1.0, 2.0, 3.0 };
    double[] constraints = new double[]{ 0.5, 1.5, -0.5 };
    double[] cachedGradient = new double[]{ -0.1, -0.2, -0.3 };
    boolean usingGaussianPrior = true;
    double gaussianPriorVariance = 2.0;
    try {
        Field f1 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("parameters");
        f1.setAccessible(true);
        f1.set(optimizable, parameters);
        Field f2 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("constraints");
        f2.setAccessible(true);
        f2.set(optimizable, constraints);
        Field f3 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedGradient");
        f3.setAccessible(true);
        f3.set(optimizable, cachedGradient.clone());
        Field f4 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("usingGaussianPrior");
        f4.setAccessible(true);
        f4.setBoolean(optimizable, usingGaussianPrior);
        Field f5 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("gaussianPriorVariance");
        f5.setAccessible(true);
        f5.setDouble(optimizable, gaussianPriorVariance);
        Field f6 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedValueStale");
        f6.setAccessible(true);
        f6.setBoolean(optimizable, false);
        Field f7 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedGradientStale");
        f7.setAccessible(true);
        f7.setBoolean(optimizable, true);
        Field f8 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("numLabels");
        f8.setAccessible(true);
        f8.setInt(optimizable, 1);
        Field f9 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("numFeatures");
        f9.setAccessible(true);
        f9.setInt(optimizable, 3);
        Field f10 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("featureSelection");
        f10.setAccessible(true);
        f10.set(optimizable, null);
        Field f11 = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("perLabelFeatureSelection");
        f11.setAccessible(true);
        f11.set(optimizable, null);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    double[] buffer = new double[3];
    optimizable.getValueGradient(buffer);
    double[] expected = new double[3];
    expected[0] = -0.1;
    expected[1] = 0.3;
    expected[2] = -2.3;
    assertArrayEquals(expected, buffer, 1.0E-4);
}

@Test
public void test10()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null);
    Field parametersField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    parametersField.set(optimizable, new double[]{ 0.1, 0.2, 0.3 });
    double[] originalParameters = ((double[]) (parametersField.get(optimizable)));
    assertArrayEquals(new double[]{ 0.1, 0.2, 0.3 }, originalParameters, 1.0E-5);
    double[] newParams = new double[]{ 1.0, 2.0 };
    optimizable.setParameters(newParams);
    double[] updatedParameters = ((double[]) (parametersField.get(optimizable)));
    assertArrayEquals(new double[]{ 1.0, 2.0 }, updatedParameters, 1.0E-5);
    Field cachedValueStaleField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedValueStale");
    cachedValueStaleField.setAccessible(true);
    assertTrue(cachedValueStaleField.getBoolean(optimizable));
    Field cachedGradientStaleField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedGradientStale");
    cachedGradientStaleField.setAccessible(true);
    assertTrue(cachedGradientStaleField.getBoolean(optimizable));
}


