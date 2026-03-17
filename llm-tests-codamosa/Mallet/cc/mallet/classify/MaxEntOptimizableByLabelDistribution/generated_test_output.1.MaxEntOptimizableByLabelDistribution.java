import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MaxEnt expected = new MaxEnt(null, null, null);
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution(expected);
}

@Test
public void test2()
{
    MaxEntOptimizableByLabelDistribution maxEnt = new MaxEntOptimizableByLabelDistribution(null, null);
    double expectedVariance = 0.75;
    MaxEntOptimizableByLabelDistribution returned = maxEnt.setGaussianPriorVariance(expectedVariance);
    assertSame(maxEnt, returned);
    assertEquals(expectedVariance, maxEnt.getClass().getDeclaredField("gaussianPriorVariance").getDouble(maxEnt), 1.0E-6);
}

@Test
public void test3()
{
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution();
    MaxEntOptimizableByLabelDistribution result = instance.useGaussianPrior();
    assertSame("useGaussianPrior should return the same instance", instance, result);
}

@Test
public void test4()
{
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution(null);
    Field parametersField = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    double[] testParameters = new double[]{ 1.0, 2.5, -3.3 };
    parametersField.set(instance, testParameters);
    double result = instance.getParameter(1);
    assertEquals(2.5, result, 1.0E-5);
}

@Test
public void test5()
{
    LabelAlphabet targetAlphabet = new LabelAlphabet();
    Label labelA = targetAlphabet.lookupLabel("A", true);
    Label labelB = targetAlphabet.lookupLabel("B", true);
    int numLabels = targetAlphabet.size();
    Alphabet dataAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1", true);
    dataAlphabet.lookupIndex("feature2", true);
    int numFeatures = dataAlphabet.size();
    double[] labelProbs = new double[]{ 0.7, 0.3 };
    FeatureVector fv = new FeatureVector(dataAlphabet, new int[]{ 0, 1 }, new double[]{ 1.0, 2.0 });
    Labeling labeling = new LabelVector(targetAlphabet, new int[]{ 0, 1 }, labelProbs);
    Instance instance = new Instance(fv, labeling, "testInstance", "testSource");
    InstanceList trainingList = new InstanceList(dataAlphabet, targetAlphabet);
    trainingList.addThruPipe(instance);
    Classifier mockClassifier = new Classifier(null, null, null) {
        @Override
        public double[] getClassificationScores(Instance inst, double[] scores) {
            scores[0] = 0.6;
            scores[1] = 0.4;
            return scores;
        }
    };
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution(mockClassifier, trainingList, numFeatures, 1.0);
    optimizable.cachedValueStale = true;
    optimizable.cachedGradient = new double[numLabels * numFeatures];
    optimizable.parameters = new double[numLabels * numFeatures];
    optimizable.numFeatures = numFeatures;
    optimizable.numLabels = numLabels;
    optimizable.defaultFeatureIndex = 0;
    optimizable.gaussianPriorVariance = 1.0;
    double expectedLogLikelihood = -((0.7 * Math.log(0.6)) + (0.3 * Math.log(0.4)));
    double expectedPrior = 0;
    double expectedTotal = -(expectedLogLikelihood + expectedPrior);
    double actual = optimizable.getValue();
    assertEquals(expectedTotal, actual, 1.0E-6);
}

@Test
public void test6()
{
    Alphabet dataAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1");
    dataAlphabet.lookupIndex("feature2");
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupIndex("label1");
    labelAlphabet.lookupIndex("label2");
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution(dataAlphabet, labelAlphabet);
    double[] sampleParameters = new double[4];
    instance.parameters = sampleParameters;
    int expectedLength = 4;
    int actualLength = instance.getNumParameters();
    assertEquals(expectedLength, actualLength);
}

@Test
public void test7()
{
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution(null);
    Field field = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("numGetValueCalls");
    field.setAccessible(true);
    field.setInt(optimizable, 3);
    int result = optimizable.getValueCalls();
    assertEquals(3, result);
}

@Test
public void test8()
{
}
{
    return 0.0;
}
{
    this.numFeatures = 2;
    this.numLabels = 1;
    this.parameters = new double[]{ Double.NEGATIVE_INFINITY, 1.0 };
    this.cachedGradient = new double[]{ -0.2, 0.3 };
    this.constraints = new double[]{ 0.2, -0.3 };
    this.gaussianPriorVariance = 1.0;
    this.featureSelection = null;
    this.perLabelFeatureSelection = null;
    this.cachedGradientStale = true;
    this.cachedValueStale = false;
}

@Test
public void test9()
{
    MaxEntOptimizableByLabelDistribution obj = new MaxEntOptimizableByLabelDistribution();
    double[] initialParams = new double[]{ 1.0, 2.0, 3.0 };
    obj.setParameters(new double[]{ 0.5, 0.5 });
    obj.setParameters(initialParams);
    double[] newParams = new double[]{ 4.4, 5.5, 6.6, 7.7 };
    obj.setParameters(newParams);
    double[] updatedParams = obj.getParameters();
    assertArrayEquals(new double[]{ 4.4, 5.5, 6.6, 7.7 }, updatedParams, 1.0E-5);
    assertTrue(obj.isCachedValueStale());
    assertTrue(obj.isCachedGradientStale());
}

