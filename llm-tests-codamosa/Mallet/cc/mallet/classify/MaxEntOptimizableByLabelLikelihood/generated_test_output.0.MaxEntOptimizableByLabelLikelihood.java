import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MaxEnt classifier = new MaxEnt(null, null);
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(classifier);
}

@Test
public void test2()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null);
    double expectedVariance = 2.5;
    MaxEntOptimizableByLabelLikelihood result = optimizable.setGaussianPriorVariance(expectedVariance);
    assertTrue("Gaussian prior should be enabled", optimizable.usingGaussianPrior);
    assertFalse("Hyperbolic prior should be disabled", optimizable.usingHyperbolicPrior);
    assertEquals("Gaussian prior variance should be set correctly", expectedVariance, optimizable.gaussianPriorVariance, 1.0E-5);
    assertSame("Method should return the same instance", optimizable, result);
}

@Test
public void test3()
{
    MaxEntOptimizableByLabelLikelihood obj = new MaxEntOptimizableByLabelLikelihood(null);
    double testSharpness = 2.5;
    MaxEntOptimizableByLabelLikelihood returned = obj.setHyperbolicPriorSharpness(testSharpness);
    assertFalse(obj.usingGaussianPrior);
    assertTrue(obj.usingHyperbolicPrior);
    assertEquals(testSharpness, obj.hyperbolicPriorSharpness, 1.0E-5);
    assertSame(obj, returned);
}

@Test
public void test4()
{
    MaxEntOptimizableByLabelLikelihood obj = new MaxEntOptimizableByLabelLikelihood();
    double testSlope = 2.5;
    MaxEntOptimizableByLabelLikelihood result = obj.setHyperbolicPriorSlope(testSlope);
    assertFalse("usingGaussianPrior should be false", obj.usingGaussianPrior);
    assertTrue("usingHyperbolicPrior should be true", obj.usingHyperbolicPrior);
    assertEquals("hyperbolicPriorSlope should be set to the input value", testSlope, obj.hyperbolicPriorSlope, 1.0E-6);
    assertSame("Method should return the same object instance", obj, result);
}

@Test
public void test5()
{
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(null, null, null);
    MaxEntOptimizableByLabelLikelihood result = optimizable.useGaussianPrior();
    assertSame(optimizable, result);
    assertTrue(getPrivateBooleanField(optimizable, "usingGaussianPrior"));
    assertFalse(getPrivateBooleanField(optimizable, "usingHyperbolicPrior"));
}

@Test
public void test6()
{
    MaxEntOptimizableByLabelLikelihood instance = new MaxEntOptimizableByLabelLikelihood();
    instance.usingGaussianPrior = true;
    instance.usingHyperbolicPrior = false;
    MaxEntOptimizableByLabelLikelihood result = instance.useHyperbolicPrior();
    assertFalse("usingGaussianPrior should be false", instance.usingGaussianPrior);
    assertTrue("usingHyperbolicPrior should be true", instance.usingHyperbolicPrior);
    assertSame("useHyperbolicPrior should return 'this'", instance, result);
}

@Test
public void test7()
{
    MaxEntOptimizableByLabelLikelihood optimizer = new MaxEntOptimizableByLabelLikelihood(null);
    optimizer.usingGaussianPrior = true;
    optimizer.usingHyperbolicPrior = true;
    MaxEntOptimizableByLabelLikelihood returned = optimizer.useNoPrior();
    assertFalse("Gaussian prior should be disabled", optimizer.usingGaussianPrior);
    assertFalse("Hyperbolic prior should be disabled", optimizer.usingHyperbolicPrior);
    assertSame("Method should return the same instance", optimizer, returned);
}

@Test
public void test8()
{
    double[] mockParameters = new double[]{ 0.5, -1.2, 3.3 };
    MaxEntOptimizableByLabelLikelihood instance = new MaxEntOptimizableByLabelLikelihood(null, null, null);
    try {
        Field parametersField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("parameters");
        parametersField.setAccessible(true);
        parametersField.set(instance, mockParameters);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set parameters field via reflection: " + e.getMessage());
    }
    double result = instance.getParameter(1);
    assertEquals(-1.2, result, 1.0E-6);
}

@Test
public void test9()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet targetAlphabet = new Alphabet();
    targetAlphabet.lookupIndex("positive");
    targetAlphabet.lookupIndex("negative");
    Label positiveLabel = new Label("positive", targetAlphabet.lookupIndex("positive"));
    Labeling labeling = new LabelVector(targetAlphabet, new int[]{ 0 }, new double[]{ 1.0 });
    FeatureVector fv = new FeatureVector(inputAlphabet, new int[]{ 0 }, new double[]{ 1.0 });
    Instance instance = mock(Instance.class);
    when(instance.getLabeling()).thenReturn(labeling);
    when(instance.getData()).thenReturn(fv);
    when(instance.getName()).thenReturn("instance1");
    when(instance.getSource()).thenReturn("source");
    Label[] labels = new Label[]{ positiveLabel };
    InstanceList instanceList = mock(InstanceList.class);
    when(instanceList.getTargetAlphabet()).thenReturn(targetAlphabet);
    when(instanceList.iterator()).thenReturn(Arrays.asList(instance).iterator());
    when(instanceList.getInstanceWeight(instance)).thenReturn(1.0);
    Classifier classifier = mock(Classifier.class);
    doAnswer(( invocation) -> {
        double[] scores = invocation.getArgument(1);
        scores[0] = 0.8;
        scores[1] = 0.2;
        return null;
    }).when(classifier).getClassificationScores(eq(instance), any(double[].class));
    MaxEntOptimizableByLabelLikelihood opt = new MaxEntOptimizableByLabelLikelihood(classifier, instanceList);
    opt.numLabels = 2;
    opt.numFeatures = 1;
    opt.defaultFeatureIndex = 0;
    opt.parameters = new double[]{ 0.5, -0.5 };
    opt.cachedGradient = new double[2];
    opt.cachedValueStale = true;
    opt.usingGaussianPrior = true;
    opt.gaussianPriorVariance = 1.0;
    double result = opt.getValue();
    assertEquals(-0.47314, result, 1.0E-4);
}

@Test
public void test10()
{
    Alphabet dataAlphabet = new Alphabet();
    Alphabet targetAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1");
    dataAlphabet.lookupIndex("feature2");
    targetAlphabet.lookupIndex("classA");
    targetAlphabet.lookupIndex("classB");
    MaxEnt classifier = new MaxEnt(dataAlphabet, targetAlphabet);
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(classifier, null);
    double[] parameters = new double[4];
    System.arraycopy(parameters, 0, optimizable.getParameters(), 0, 4);
    int expected = 4;
    int actual = optimizable.getNumParameters();
    assertEquals(expected, actual);
}

@Test
public void test11()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new Input2CharSequence("UTF-8"));
    pipeList.add(new CharSequence2TokenSequence());
    pipeList.add(new TokenSequence2FeatureSequence());
    pipeList.add(new Target2Label());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    MaxEntOptimizableByLabelLikelihood optimizable = new MaxEntOptimizableByLabelLikelihood(trainingData, false);
    int expected = 0;
    int actual = optimizable.getValueGradientCalls();
    assertEquals(expected, actual);
}

@Test
public void test12()
{
    MaxEntOptimizableByLabelLikelihood obj = new MaxEntOptimizableByLabelLikelihood();
    Field parametersField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("parameters");
    parametersField.setAccessible(true);
    parametersField.set(obj, new double[]{ 0.0, 0.0, 0.0 });
    double[] newParams = new double[]{ 1.1, 2.2, 3.3 };
    obj.setParameters(newParams);
    double[] updatedParams = ((double[]) (parametersField.get(obj)));
    assertEquals(3, updatedParams.length);
    assertEquals(1.1, updatedParams[0], 1.0E-4);
    assertEquals(2.2, updatedParams[1], 1.0E-4);
    assertEquals(3.3, updatedParams[2], 1.0E-4);
    Field cachedValueStaleField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedValueStale");
    cachedValueStaleField.setAccessible(true);
    assertTrue(cachedValueStaleField.getBoolean(obj));
    Field cachedGradientStaleField = MaxEntOptimizableByLabelLikelihood.class.getDeclaredField("cachedGradientStale");
    cachedGradientStaleField.setAccessible(true);
    assertTrue(cachedGradientStaleField.getBoolean(obj));
}

