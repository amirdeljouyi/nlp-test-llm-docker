import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Alphabet labelAlphabet = new Alphabet();
    Alphabet featureAlphabet = new Alphabet();
}

@Test
public void test2()
{
    MaxEntOptimizableByLabelLikelihood trainer = new MaxEntOptimizableByLabelLikelihood(null, null);
    double expectedVariance = 2.5;
    MaxEntOptimizableByLabelLikelihood returnedTrainer = trainer.setGaussianPriorVariance(expectedVariance);
    Assert.assertSame("Returned object should be the same instance", trainer, returnedTrainer);
    Assert.assertTrue("Gaussian prior should be enabled", trainer.usingGaussianPrior);
    Assert.assertFalse("Hyperbolic prior should be disabled", trainer.usingHyperbolicPrior);
    Assert.assertEquals("Gaussian prior variance should be set correctly", expectedVariance, trainer.gaussianPriorVariance, 1.0E-6);
}

@Test
public void test3()
{
    MaxEntOptimizableByLabelLikelihood instance = new MaxEntOptimizableByLabelLikelihood(null, null);
    double testSharpness = 2.5;
    MaxEntOptimizableByLabelLikelihood returnedInstance = instance.setHyperbolicPriorSharpness(testSharpness);
    assertFalse("usingGaussianPrior should be false", instance.usingGaussianPrior);
    assertTrue("usingHyperbolicPrior should be true", instance.usingHyperbolicPrior);
    assertEquals("hyperbolicPriorSharpness should match input", testSharpness, instance.hyperbolicPriorSharpness, 1.0E-5);
    assertSame("Returned instance should be the same", instance, returnedInstance);
}

@Test
public void test4()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null);
    double testSlope = 2.5;
    MaxEntOptimizableByLabelLikelihood returned = optimizable.setHyperbolicPriorSlope(testSlope);
    assertFalse("Gaussian prior should be disabled", optimizable.usingGaussianPrior);
    assertTrue("Hyperbolic prior should be enabled", optimizable.usingHyperbolicPrior);
    assertEquals("Hyperbolic prior slope should be set correctly", testSlope, optimizable.hyperbolicPriorSlope, 1.0E-4);
    assertSame("Method should return the same instance", optimizable, returned);
}

@Test
public void test5()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null, null);
    MaxEntOptimizableByLabelLikelihood result = optimizable.useGaussianPrior();
    assertSame(optimizable, result);
    assertTrue(getBooleanFieldValue(optimizable, "usingGaussianPrior"));
    assertFalse(getBooleanFieldValue(optimizable, "usingHyperbolicPrior"));
}

@Test
public void test6()
{
    MaxEntOptimizableByLabelLikelihood instance = new MaxEntOptimizableByLabelLikelihood(null);
    instance.usingGaussianPrior = true;
    instance.usingHyperbolicPrior = false;
    MaxEntOptimizableByLabelLikelihood result = instance.useHyperbolicPrior();
    assertSame("Returned object should be the same instance", instance, result);
    assertFalse("usingGaussianPrior should be set to false", instance.usingGaussianPrior);
    assertTrue("usingHyperbolicPrior should be set to true", instance.usingHyperbolicPrior);
}

@Test
public void test7()
{
    MaxEntOptimizableByLabelLikelihood optimizer = new MaxEntOptimizableByLabelLikelihood(null, null);
    optimizer.useNoPrior();
    assertFalse("Gaussian prior should be disabled", optimizer.usingGaussianPrior);
    assertFalse("Hyperbolic prior should be disabled", optimizer.usingHyperbolicPrior);
}

@Test
public void test8()
{
}
{
    parameters = new double[]{ 1.5, -2.0, 3.14 };
}

@Test
public void test9()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    int labelIndex = labelAlphabet.lookupIndex("positive", true);
    Alphabet dataAlphabet = new Alphabet();
    int featureIndex = dataAlphabet.lookupIndex("feature1", true);
    FeatureVector fv = new FeatureVector(dataAlphabet, new int[]{ featureIndex }, new double[]{ 1.0 });
    Label label = labelAlphabet.lookupLabel(labelIndex);
    Labeling labeling = labelAlphabet.lookupLabel(labelIndex).getLabeling();
    Instance instance = new Instance(fv, labeling, "instance-1", null);
    InstanceList trainingList = new InstanceList(dataAlphabet, labelAlphabet) {
        @Override
        public double getInstanceWeight(Instance instance) {
            return 1.0;
        }

        @Override
        public Iterator<Instance> iterator() {
            ArrayList<Instance> list = new ArrayList<>();
            list.add(instance);
            return list.iterator();
        }
    };
    trainingList.add(instance);
    Classifier classifier = new Classifier(dataAlphabet, labelAlphabet, null) {
        @Override
        public double[] getClassificationScores(Instance instance, double[] scores) {
            scores[0] = 0.8;
            return scores;
        }
    };
    int numFeatures = 1;
    int numLabels = 1;
    MaxEntOptimizableByLabelLikelihood likelihood = new MaxEntOptimizableByLabelLikelihood(classifier, trainingList, numFeatures, numLabels);
    likelihood.cachedValueStale = true;
    likelihood.numFeatures = numFeatures;
    likelihood.numLabels = numLabels;
    likelihood.usingGaussianPrior = true;
    likelihood.gaussianPriorVariance = 1.0;
    likelihood.parameters = new double[]{ 0.5 };
    likelihood.cachedGradient = new double[numFeatures * numLabels];
    likelihood.defaultFeatureIndex = 0;
    likelihood.theClassifier = classifier;
    double result = likelihood.getValue();
    double expectedLikelihood = -Math.log(0.8);
    double expectedPrior = (0.5 * 0.5) / 2.0;
    double expected = -(expectedLikelihood + expectedPrior);
    assertEquals(expected, result, 1.0E-6);
}

@Test
public void test10()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(new double[]{ 0.1, 0.2, 0.3 });
    int result = optimizable.getNumParameters();
    assertEquals(3, result);
}

@Test
public void test11()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null);
    Field field = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("numGetValueCalls");
    field.setAccessible(true);
    field.setInt(optimizable, 3);
    int result = optimizable.getValueCalls();
    assertEquals(3, result);
}

@Test
public void test12()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null);
    double[] initialParams = new double[2];
    optimizable.setParameters(initialParams);
    double[] newParams = new double[]{ 1.0, 2.0, 3.0 };
    optimizable.setParameters(newParams);
    double[] internalParams = null;
    try {
        Field paramField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("parameters");
        paramField.setAccessible(true);
        internalParams = ((double[]) (paramField.get(optimizable)));
    } catch (Exception e) {
        fail("Reflection error: " + e.getMessage());
    }
    assertNotNull(internalParams);
    assertArrayEquals(newParams, internalParams, 0.0);
}

