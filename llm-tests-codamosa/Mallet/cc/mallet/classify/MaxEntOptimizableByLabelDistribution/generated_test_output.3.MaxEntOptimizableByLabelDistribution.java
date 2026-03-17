import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MaxEntOptimizableByLabelDistribution trainer = new MaxEntOptimizableByLabelDistribution();
    double expectedVariance = 0.5;
    MaxEntOptimizableByLabelDistribution returnedTrainer = trainer.setGaussianPriorVariance(expectedVariance);
    assertSame("Method should return the same trainer instance", trainer, returnedTrainer);
    assertEquals("Gaussian prior variance should be set correctly", expectedVariance, trainer.gaussianPriorVariance, 0.0);
}

@Test
public void test2()
{
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution();
    MaxEntOptimizableByLabelDistribution returned = instance.useGaussianPrior();
    assertSame("useGaussianPrior should return the same instance", instance, returned);
}

@Test
public void test3()
{
    LabelAlphabet targetAlphabet = new LabelAlphabet();
    Label labelA = targetAlphabet.lookupLabel("A", true);
    Label labelB = targetAlphabet.lookupLabel("B", true);
    Alphabet inputAlphabet = new Alphabet();
    int featureIndex = inputAlphabet.lookupIndex("feature1", true);
    FeatureVector featureVector = new FeatureVector(inputAlphabet, new int[]{ featureIndex }, new double[]{ 1.0 });
    Labeling labeling = new LabelVector(targetAlphabet, new int[]{ 0, 1 }, new double[]{ 0.7, 0.3 });
    Instance instance = new Instance(featureVector, labeling, "instance1", null);
    InstanceList trainingList = new InstanceList(inputAlphabet, targetAlphabet) {
        @Override
        public double getInstanceWeight(Instance inst) {
            return 1.0;
        }
    };
    trainingList.add(instance);
    double[] mockScores = new double[]{ 0.6, 0.4 };
    Classifier mockClassifier = new Classifier(inputAlphabet, targetAlphabet) {
        @Override
        public void getClassificationScores(Instance inst, double[] scores) {
            scores[0] = mockScores[0];
            scores[1] = mockScores[1];
        }
    };
    int numFeatures = inputAlphabet.size() + 1;
    int numLabels = targetAlphabet.size();
    double[] parameters = new double[numFeatures * numLabels];
    Arrays.fill(parameters, 0.1);
    MaxEntOptimizableByLabelDistribution opt = new MaxEntOptimizableByLabelDistribution(mockClassifier, trainingList, numFeatures, numLabels, parameters);
    opt.cachedValueStale = true;
    opt.cachedGradient = new double[numFeatures * numLabels];
    opt.numFeatures = numFeatures;
    opt.numLabels = numLabels;
    opt.parameters = parameters;
    opt.gaussianPriorVariance = 1.0;
    opt.defaultFeatureIndex = inputAlphabet.size();
    double result = opt.getValue();
    assertTrue("Expected result to be a finite negative value", Double.isFinite(result) && (result < 0.0));
}

@Test
public void test4()
{
    MaxEntOptimizableByLabelDistribution instance = new MaxEntOptimizableByLabelDistribution(null, null);
    Field parametersField = MaxEntOptimizableByLabelDistribution.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    parametersField.set(instance, new double[]{ 0.1, 0.2, 0.3, 0.4 });
    int result = instance.getNumParameters();
    assertEquals(4, result);
}

@Test
public void test5()
{
    MaxEntOptimizableByLabelDistribution optimizable = new MaxEntOptimizableByLabelDistribution();
    int numParams = 4;
    int numLabels = 2;
    int numFeatures = 2;
    optimizable.parameters = new double[]{ 0.2, -0.4, 0.6, -0.8 };
    optimizable.constraints = new double[]{ 1.0, 0.0, 0.5, -0.5 };
    optimizable.cachedGradient = new double[]{ -0.2, 0.4, -0.6, 0.8 };
    optimizable.cachedGradientStale = true;
    optimizable.cachedValueStale = false;
    optimizable.gaussianPriorVariance = 1.0;
    optimizable.featureSelection = new boolean[]{ true, true };
    optimizable.numFeatures = numFeatures;
    optimizable.numLabels = numLabels;
    optimizable.perLabelFeatureSelection = null;
    double[] buffer = new double[numParams];
    optimizable.getValueGradient(buffer);
    assertArrayEquals(new double[]{ 0.6, 0.8, -0.7, 1.1 }, buffer, 1.0E-9);
}

