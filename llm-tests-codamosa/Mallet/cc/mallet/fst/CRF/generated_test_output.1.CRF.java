import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CRF crf = new CRF(null, null);
    String name = "state1";
    int index = 0;
    double initialWeight = 1.5;
    double finalWeight = 2.5;
    String[] destinationNames = new String[]{ "dest1", "dest2" };
    String[] labelNames = new String[]{ "labelA", "labelB" };
    String[][] weightNames = new String[][]{ new String[]{ "w1", "w2" }, new String[]{ "w3", "w4" } };
    State state = crf.newState(name, index, initialWeight, finalWeight, destinationNames, labelNames, weightNames, crf);
    assertNotNull(state);
    assertEquals(name, state.getName());
    assertEquals(index, state.getIndex());
    assertEquals(initialWeight, state.getInitialWeight(), 1.0E-4);
    assertEquals(finalWeight, state.getFinalWeight(), 1.0E-4);
    assertArrayEquals(destinationNames, state.getDestinationNames());
    assertArrayEquals(labelNames, state.getLabelNames());
}

@Test
public void test2()
{
    CRF crf = new CRF(null, null);
    boolean result = crf.isTrainable();
    assertTrue("Expected isTrainable() to return true", result);
}

@Test
public void test3()
{
    CRF crf = new CRF(null, null);
    Factors mockFactors = new CRF.Factors();
    try {
        Field field = CRF.class.getDeclaredField("parameters");
        field.setAccessible(true);
        field.set(crf, mockFactors);
    } catch (Exception e) {
        fail("Failed to set private field 'parameters': " + e.getMessage());
    }
    Factors returnedFactors = crf.getParameters();
    assertSame("getParameters should return the same Factors instance assigned", mockFactors, returnedFactors);
}

@Test
public void test4()
{
    CRF crf = new CRF(null, null);
    State expectedState = crf.new State("B-NP", null, false);
    Map<String, State> name2stateMap = new HashMap<>();
    name2stateMap.put("B-NP", expectedState);
    try {
        Field field = CRF.class.getDeclaredField("name2state");
        field.setAccessible(true);
        field.set(crf, name2stateMap);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    State actualState = crf.getState("B-NP");
    assertSame("The returned state should be the same as the inserted one", expectedState, actualState);
}

@Test
public void test5()
{
    CRF crf = new CRF(null, null);
    State expectedState = crf.new State("B-PER", false);
    HashMap<String, State> stateMap = new HashMap<>();
    stateMap.put("B-PER", expectedState);
    try {
        Field field = CRF.class.getDeclaredField("name2state");
        field.setAccessible(true);
        field.set(crf, stateMap);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to inject name2state via reflection: " + e.getMessage());
    }
    State actualState = crf.getState("B-PER");
    assertSame("getState should return the correct State object for existing name", expectedState, actualState);
}

@Test
public void test6()
{
    Sequence<String> inputSequence = new SimpleSequence<>();
    inputSequence.add("feature1");
    inputSequence.add("feature2");
    Sequence<String> targetSequence = new SimpleSequence<>();
    targetSequence.add("label1");
    targetSequence.add("label2");
    Instance instance = new Instance(inputSequence, targetSequence, null, null);
    Pipe dummyPipe = new Pipe() {
        @Override
        public Instance pipe(Instance carrier) {
            return carrier;
        }
    };
    InstanceList instanceList = new InstanceList(dummyPipe);
    instanceList.add(instance);
    CRF crf = new CRF(dummyPipe, null);
    crf.globalFeatureSelection = new FeatureSelection(null);
    crf.featureInducers = new ArrayList<>();
    MaxLatticeDefault maxLattice = new MaxLatticeDefault(crf, inputSequence) {
        @Override
        public Sequence<String> bestOutputSequence() {
            Sequence<String> predicted = new SimpleSequence<>();
            predicted.add("label1");
            predicted.add("label2");
            return predicted;
        }
    };
    Sequence[] predicted = crf.predict(instanceList);
    assertNotNull(predicted);
    assertEquals(1, predicted.length);
    assertEquals(2, predicted[0].size());
}

@Test
public void test7()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    inputAlphabet.lookupIndex("feature");
    outputAlphabet.lookupIndex("label");
    CRF crf = new CRF(inputAlphabet, outputAlphabet);
    crf.addState(crf.getState("label"), new Transducer.Transition[0]);
    InstanceList trainingData = new InstanceList(crf.getInputAlphabet(), crf.getOutputAlphabet());
    Instance instance = new Instance(new String[]{ "feature" }, new String[]{ "label" }, null, null);
    trainingData.add(instance);
    CRFOptimizableByLabelLikelihood opt = new CRFOptimizableByLabelLikelihood(crf, trainingData);
    crf.setWeightsDimensions(trainingData);
    SparseVector result = crf.getWeights("weights");
    assertNotNull(result);
    assertTrue(result.numLocations() >= 0);
}

@Test
public void test8()
{
    CRF crf = new CRF(null, null);
    SparseVector expectedVector = new SparseVector(new int[]{ 1, 3 }, new double[]{ 0.5, -1.2 }, 2);
    crf.parameters = new CRF.Parameters();
    crf.parameters.weights = new SparseVector[1];
    crf.parameters.weights[0] = expectedVector;
    CRF spyCrf = new CRF(null, null) {
        @Override
        protected int getWeightsIndex(String weightName) {
            assertEquals("testWeight", weightName);
            return 0;
        }
    };
    spyCrf.parameters = crf.parameters;
    SparseVector actualVector = spyCrf.getWeights("testWeight");
    assertEquals(expectedVector, actualVector);
}

@Test
public void test9()
{
    CRF crf = new CRF(null, null);
    SparseVector expectedVector = new SparseVector(new int[]{ 0, 2 }, new double[]{ 1.0, 3.5 });
    crf.parameters = new CRF.Parameters();
    crf.parameters.weights = new SparseVector[1];
    crf.parameters.weights[0] = expectedVector;
    CRF finalCrf = crf;
    String weightName = "myWeight";
    int weightIndex = 0;
    crf.getWeightsIndex = ( name) -> {
        assertEquals("myWeight", name);
        return weightIndex;
    };
    SparseVector actualVector = crf.getWeights(weightName);
    assertEquals(expectedVector, actualVector);
}

@Test
public void test10()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    CRF crf = new CRF(inputAlphabet, outputAlphabet);
    crf.addStates(new String[]{ "state0", "state1" });
    int sourceState = 0;
    int destState = 1;
    int featureIndex = 2;
    double expectedValue = 0.75;
    crf.setParameter(sourceState, destState, featureIndex, 0, expectedValue);
    double actualValue = crf.getParameter(sourceState, destState, featureIndex);
    assertEquals(expectedValue, actualValue, 1.0E-6);
}

@Test
public void test11()
{
    CRF crf = new CRF(new Alphabet(), new LabelAlphabet());
    State dummyState1 = crf.addState("s1");
    State dummyState2 = crf.addState("s2");
    int numStates = 2;
    CRF.Parameters parameters = crf.new Parameters();
    parameters.initialWeights = new double[]{ 1.0, -2.0 };
    parameters.finalWeights = new double[]{ -3.0, 4.0 };
    parameters.defaultWeights = new double[]{ 0.5, -1.5 };
    parameters.weights = new MatrixOps.Matrix1D[]{ new MatrixOps.Matrix1D(new double[]{ 2.0, -2.0 }), new MatrixOps.Matrix1D(new double[]{ -1.0, 3.0 }) };
    crf.setParameters(parameters);
    double expected = ((((((Math.abs(1.0) + Math.abs(-3.0)) + Math.abs(-2.0)) + Math.abs(4.0)) + Math.abs(0.5)) + parameters.weights[0].absNorm()) + Math.abs(-1.5)) + parameters.weights[1].absNorm();
    double actual = crf.getParametersAbsNorm();
    assertEquals(expected, actual, 1.0E-6);
}

@Test
public void test12()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    CRF crf = new CRF(inputAlphabet, outputAlphabet);
    double[] expectedWeights = new double[]{ 0.5, -1.2, 3.0 };
    crf.parameters.defaultWeights = expectedWeights;
    double[] actualWeights = crf.getDefaultWeights();
    assertNotNull("Returned weights should not be null", actualWeights);
    assertEquals("Array length should match", expectedWeights.length, actualWeights.length);
    assertEquals("First weight should be 0.5", 0.5, actualWeights[0], 1.0E-5);
    assertEquals("Second weight should be -1.2", -1.2, actualWeights[1], 1.0E-5);
    assertEquals("Third weight should be 3.0", 3.0, actualWeights[2], 1.0E-5);
}

@Test
public void test13()
{
    Alphabet inputAlphabet = new Alphabet();
    LabelAlphabet outputAlphabet = new LabelAlphabet();
    CRF crf = new CRF(inputAlphabet, outputAlphabet);
    State s0 = crf.addState(0.0);
    State s1 = crf.addState(0.0);
    State s2 = crf.addState(0.0);
    crf.weightsStructureChangeStamp = crf.cachedNumParametersStamp + 1;
    CRF.Parameters params = crf.new Parameters();
    params.defaultWeights = new double[]{ 0.1, 0.2 };
    CRF[] weights = new CRF.Weight[2];
    weights[0] = crf.new Weight() {
        @Override
        public int numLocations() {
            return 5;
        }
    };
    weights[1] = crf.new Weight() {
        @Override
        public int numLocations() {
            return 3;
        }
    };
    params.weights = weights;
    crf.parameters = params;
    int expected = (((2 * crf.numStates()) + params.defaultWeights.length) + 5) + 3;
    int actual = crf.getNumParameters();
    assertEquals(expected, actual);
}

@Test
public void test14()
{
    CRF crf = new CRF(new Alphabet(), null);
    crf.parameters.weightAlphabet.lookupIndex("testWeight");
    int index = crf.getWeightsIndex("testWeight");
    assertEquals(0, index);
    assertNotNull(crf.parameters.weights);
    assertEquals(1, crf.parameters.weights.length);
    assertTrue(crf.parameters.weights[0] instanceof IndexedSparseVector);
    assertArrayEquals(new double[]{ 0.0 }, crf.parameters.defaultWeights, 0.0);
    assertNull(crf.featureSelections[0]);
}

@Test
public void test15()
{
    CRF crf = new CRF(null, null);
    try {
        Field field = CRF.class.getDeclaredField("weightsStructureChangeStamp");
        field.setAccessible(true);
        field.setInt(crf, 42);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(e);
    }
    assertEquals(42, crf.getWeightsStructureChangeStamp());
}

@Test
public void test16()
{
    CRF crf = new CRF(null, null);
    Field field = CRF.class.getDeclaredField("weightsValueChangeStamp");
    field.setAccessible(true);
    field.setInt(crf, 42);
    int result = crf.getWeightsValueChangeStamp();
    assertEquals(42, result);
}

@Test
public void test17()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    CRF crf = new CRF(inputAlphabet, outputAlphabet);
    State state1 = crf.addState("S1", null);
    State state2 = crf.addState("S2", null);
    State state3 = crf.addState("S3", null);
    assertEquals(3, crf.numStates());
}

@Test
public void test18()
{
    CRF crf = new CRF(new Alphabet(), null);
    Alphabet outputAlphabet = crf.getOutputAlphabet();
    outputAlphabet.lookupIndex("A");
    outputAlphabet.lookupIndex("B");
    Alphabet dataAlphabet = new Alphabet();
    InstanceList trainingSet = new InstanceList(dataAlphabet);
    trainingSet.add(new Instance("dummy", "A", null, null));
    int[] orders = null;
    boolean[] defaults = null;
    String start = "START";
    Pattern forbidden = null;
    Pattern allowed = null;
    boolean fullyConnected = true;
    String actualStartState = crf.addOrderNStates(trainingSet, orders, defaults, start, forbidden, allowed, fullyConnected);
    assertEquals("START", actualStartState);
}

@Test
public void test19()
{
    Alphabet weightAlphabet = new Alphabet();
    weightAlphabet.lookupIndex("weight_label_0");
    weightAlphabet.lookupIndex("weight_label_1");
    LabelAlphabet outputAlphabet = new LabelAlphabet();
    CRF crf = new CRF(new Alphabet(), outputAlphabet);
    crf.parameters = new CRF.CRFParameters(weightAlphabet, 2);
    String result = crf.getWeightsName(1);
    assertEquals("weight_label_1", result);
}

@Test
public void test20()
{
    CRF crf = new CRF(null, null);
    State stateA = new State("StateA", null);
    State stateB = new State("StateB", null);
    List<State> initialStates = new ArrayList<>();
    initialStates.add(stateA);
    initialStates.add(stateB);
    crf.initialStates = initialStates;
    Iterator<State> iterator = crf.initialStateIterator();
    assertTrue(iterator.hasNext());
    assertEquals(stateA, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(stateB, iterator.next());
    assertFalse(iterator.hasNext());
}

@Test
public void test21()
{
    CRF crf = new CRF(null, null);
    crf.parameters = new Parameters();
    crf.parameters.initialWeights = new double[0];
    crf.parameters.finalWeights = new double[0];
    crf.name2state = new HashMap<>();
    crf.states = new ArrayList<>();
    crf.initialStates = new ArrayList<>();
    String name = "state1";
    double initialWeight = 0.5;
    double finalWeight = 0.7;
    String[] destinationNames = new String[]{ "state2" };
    String[] labelNames = new String[]{ "label1" };
    String[][] weightNames = new String[][]{ new String[]{ "weight1" } };
    crf.addState(name, initialWeight, finalWeight, destinationNames, labelNames, weightNames);
    assertEquals(1, crf.states.size());
    assertTrue(crf.name2state.containsKey(name));
    assertEquals("state1", crf.states.get(0).name);
}

@Test
public void test22()
{
    CRF crf = new CRF(null);
    crf.parameters.initialWeights = new double[0];
    crf.parameters.finalWeights = new double[0];
    crf.name2state = new HashMap<String, State>();
    crf.states = new ArrayList<State>();
    crf.initialStates = new ArrayList<State>();
    String stateName = "S1";
    double initialWeight = 0.5;
    double finalWeight = 1.0;
    String[] destinationNames = new String[]{ "S2" };
    String[] labelNames = new String[]{ "label1" };
    String[][] weightNames = new String[][]{ new String[]{ "w1" } };
    crf.addState(stateName, initialWeight, finalWeight, destinationNames, labelNames, weightNames);
    assertEquals(1, crf.states.size());
    assertEquals("S1", crf.states.get(0).name);
    assertTrue(crf.name2state.containsKey("S1"));
    assertEquals(crf.states.get(0), crf.name2state.get("S1"));
    assertTrue(crf.initialStates.contains(crf.states.get(0)));
}

@Test
public void test23()
{
    CRF crf = new CRF(null);
    crf.parameters.initialWeights = new double[0];
    crf.parameters.finalWeights = new double[0];
    crf.name2state = new HashMap<String, CRF.State>();
    crf.states = new ArrayList<CRF.State>();
    crf.initialStates = new ArrayList<CRF.State>();
    String name = "state1";
    double initialWeight = 0.0;
    double finalWeight = 1.0;
    String[] destinationNames = new String[]{ "dest1" };
    String[] labelNames = new String[]{ "label1" };
    String[][] weightNames = new String[][]{ new String[]{ "w1" } };
    crf.addState(name, initialWeight, finalWeight, destinationNames, labelNames, weightNames);
}

@Test
public void test24()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    Label labelA = ((Label) (labelAlphabet.lookupLabel("A", true)));
    Label labelB = ((Label) (labelAlphabet.lookupLabel("B", true)));
    Label labelC = ((Label) (labelAlphabet.lookupLabel("C", true)));
    InstanceList trainingSet = new InstanceList(null);
    trainingSet.setTargetAlphabet(labelAlphabet);
    ArrayList<Label> labelSequence = new ArrayList<Label>();
    labelSequence.add(labelA);
    labelSequence.add(labelB);
    labelSequence.add(labelC);
    trainingSet.addThruPipe(new Instance(null, labelSequence, null, null));
    CRF crf = new CRF(null, labelAlphabet);
    crf.addStatesForBiLabelsConnectedAsIn(trainingSet);
    assertNotNull(crf.getState("A=B"));
    assertNotNull(crf.getState("B=C"));
    assertNull(crf.getState("A=C"));
}

@Test
public void test25()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("A");
    outputAlphabet.lookupIndex("B");
    CRF crf = new CRF(null, outputAlphabet);
    Alphabet dataAlphabet = new Alphabet();
    InstanceList trainingSet = new InstanceList(dataAlphabet, outputAlphabet);
    trainingSet.add(new Instance(new int[]{ 0 }, "A", null, null));
    trainingSet.add(new Instance(new int[]{ 1 }, "B", null, null));
    CRF customCRF = new CRF(null, outputAlphabet) {
        @Override
        protected boolean[][] labelConnectionsIn(InstanceList ilist) {
            return new boolean[][]{ new boolean[]{ false, true }, new boolean[]{ true, false } };
        }

        @Override
        protected void addState(String name, double initialWeight, double finalWeight, String[] defaultFeatureStates, String[] defaultTransitionStates, String[] defaultEndStates) {
            assertNotNull("State name should not be null", name);
            assertEquals(1, defaultFeatureStates.length);
            assertEquals(1, defaultTransitionStates.length);
            assertEquals(1, defaultEndStates.length);
            assertTrue(defaultFeatureStates[0].equals("A") || defaultFeatureStates[0].equals("B"));
        }
    };
    customCRF.addStatesForHalfLabelsConnectedAsIn(trainingSet);
}

@Test
public void test26()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    int labelAIndex = labelAlphabet.lookupIndex("A");
    int labelBIndex = labelAlphabet.lookupIndex("B");
    Alphabet inputAlphabet = new Alphabet();
    inputAlphabet.lookupIndex("feature1");
    CRF crf = new CRF(inputAlphabet, labelAlphabet);
    InstanceList trainingSet = new InstanceList(inputAlphabet, labelAlphabet);
    ArrayList<FeatureVector> inputFeatures = new ArrayList<FeatureVector>();
    inputFeatures.add(new FeatureVector(inputAlphabet, new String[]{ "feature1" }));
    inputFeatures.add(new FeatureVector(inputAlphabet, new String[]{ "feature1" }));
    FeatureVectorSequence fvs = new FeatureVectorSequence(inputFeatures.toArray(new FeatureVector[0]));
    ArrayList<String> targetLabels = new ArrayList<String>();
    targetLabels.add("A");
    targetLabels.add("B");
    Sequence<String> outputSequence = new LabelSequence(labelAlphabet, targetLabels.toArray(new String[0]));
    Instance instance = new Instance(fvs, outputSequence, null, null);
    trainingSet.addThruPipe(instance);
    crf.addStatesForLabelsConnectedAsIn(trainingSet);
    assertNotNull(crf.getState("A"));
    assertNotNull(crf.getState("B"));
    String[] destinations = crf.getState("A").getDestinationLabels();
    assertEquals(1, destinations.length);
    assertEquals("B", destinations[0]);
}

@Test
public void test27()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("B");
    outputAlphabet.lookupIndex("I");
    outputAlphabet.lookupIndex("O");
    Alphabet dataAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("word=hello");
    InstanceList trainingSet = new InstanceList(dataAlphabet, outputAlphabet);
    trainingSet.addThruPipe(new Instance("hello", "B", null, null));
    trainingSet.addThruPipe(new Instance("hello", "I", null, null));
    trainingSet.addThruPipe(new Instance("hello", "O", null, null));
    CRF crf = new CRF(dataAlphabet, outputAlphabet);
    CRF crfSpy = new CRF(dataAlphabet, outputAlphabet) {
        @Override
        protected boolean[][] labelConnectionsIn(InstanceList instanceList) {
            return new boolean[][]{ new boolean[]{ true, true, true }, new boolean[]{ true, true, true }, new boolean[]{ true, true, true } };
        }
    };
    crfSpy.setWeightsDimension(9);
    crfSpy.addStatesForThreeQuarterLabelsConnectedAsIn(trainingSet);
    assertEquals(3, crfSpy.numStates());
    assertEquals("B", crfSpy.getState(0).getName());
    assertEquals("I", crfSpy.getState(1).getName());
    assertEquals("O", crfSpy.getState(2).getName());
    int index = crfSpy.getWeightsIndex("B->I");
    assertNotNull(crfSpy.featureSelections[index]);
    index = crfSpy.getWeightsIndex("I->O");
    assertNotNull(crfSpy.featureSelections[index]);
    index = crfSpy.getWeightsIndex("O->B");
    assertNotNull(crfSpy.featureSelections[index]);
}

@Test
public void test28()
{
    InstanceList mockInstances = mock(InstanceList.class);
    FeatureSelection mockFeatureSelection = mock(FeatureSelection.class);
    FeatureInducer mockInducer1 = mock(FeatureInducer.class);
    FeatureInducer mockInducer2 = mock(FeatureInducer.class);
    CRF crf = new CRF(null, null);
    crf.globalFeatureSelection = mockFeatureSelection;
    crf.featureInducers = new ArrayList<>();
    crf.featureInducers.add(mockInducer1);
    crf.featureInducers.add(mockInducer2);
    crf.induceFeaturesFor(mockInstances);
    verify(mockInstances).setFeatureSelection(mockFeatureSelection);
    verify(mockInducer1).induceFeaturesFor(mockInstances, false, false);
    verify(mockInducer2).induceFeaturesFor(mockInstances, false, false);
}

@Test
public void test29()
{
    CRF crf = new CRF(null, null);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintWriter testWriter = new PrintWriter(new OutputStreamWriter(outputStream), true);
    System.setOut(new PrintStream(outputStream));
    crf.print();
    String output = outputStream.toString();
    assertTrue("CRF print output should not be empty", (output != null) && (!output.trim().isEmpty()));
}

@Test
public void test30()
{
    CRF crf = new CRF(null, null);
    crf.addStates(new String[]{ "s0", "s1" });
    int sourceStateIndex = 0;
    int destStateIndex = 1;
    int featureIndex = 2;
    double expectedValue = 3.5;
    crf.setParameter(sourceStateIndex, destStateIndex, featureIndex, expectedValue);
    double actualValue = crf.getParameters().get(sourceStateIndex, destStateIndex, featureIndex, 0);
    assertEquals(expectedValue, actualValue, 1.0E-5);
}

@Test
public void test31()
{
    CRF crf = new CRF(null, null);
    int sourceStateIndex = 0;
    int destStateIndex = 1;
    int featureIndex = 2;
    double expectedValue = 3.14;
    crf.setParameter(sourceStateIndex, destStateIndex, featureIndex, expectedValue);
    double actualValue = crf.getParameters().get(sourceStateIndex, destStateIndex, featureIndex, 0);
    assertEquals(expectedValue, actualValue, 1.0E-5);
}

@Test
public void test32()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    CRF crf = new CRF(inputAlphabet, outputAlphabet);
    inputAlphabet.lookupIndex("feature1");
    outputAlphabet.lookupIndex("label1");
    Instance instance = new Instance(new String[]{ "feature1" }, "label1", null, null);
    InstanceList trainingData = new InstanceList(inputAlphabet, outputAlphabet);
    trainingData.addThruPipe(instance);
    int expectedInputSize = inputAlphabet.size();
    int expectedOutputSize = outputAlphabet.size();
    crf.setWeightsDimensionAsIn(trainingData);
    assertEquals(expectedInputSize, crf.getInputAlphabet().size());
    assertEquals(expectedOutputSize, crf.getOutputAlphabet().size());
}

@Test
public void test33()
{
    CRF crf = new CRF(new Alphabet(), new Alphabet());
    crf.inputAlphabet.lookupIndex("feature1");
    crf.inputAlphabet.lookupIndex("feature2");
    crf.inputAlphabet.lookupIndex("feature3");
    crf.parameters.weights = new SparseVector[2];
    crf.parameters.weights[0] = new SparseVector(new int[]{ 0, 1 }, new double[]{ 1.0, 2.0 }, 2, 3, false, false, false);
    BitSet bitSet = new BitSet();
    bitSet.set(1);
    bitSet.set(2);
    FeatureSelection featureSelection = new FeatureSelection(bitSet) {
        @Override
        public int nextSelectedIndex(int fromIndex) {
            return bitSet.nextSetBit(fromIndex);
        }
    };
    crf.featureSelections = new FeatureSelection[2];
    crf.featureSelections[0] = null;
    crf.featureSelections[1] = featureSelection;
    crf.parameters.weights[1] = new SparseVector(new int[]{ 1 }, new double[]{ 3.0 }, 1, 3, false, false, false);
    crf.setWeightsDimensionDensely();
    assertEquals(2, crf.parameters.weights.length);
    assertTrue(crf.parameters.weights[0] instanceof SparseVector);
    assertTrue(crf.parameters.weights[1] instanceof IndexedSparseVector);
    assertEquals(1.0, crf.parameters.weights[0].value(0), 1.0E-4);
    assertEquals(2.0, crf.parameters.weights[0].value(1), 1.0E-4);
    assertEquals(3.0, crf.parameters.weights[1].value(1), 1.0E-4);
    assertEquals(3, crf.parameters.weights[0].numLocations());
    assertEquals(2, crf.parameters.weights[1].numLocations());
}

@Test
public void test34()
{
    File tempFile = File.createTempFile("crf_test", ".ser");
    tempFile.deleteOnExit();
    CRF crf = new CRF(null, null);
    crf.write(tempFile);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
    Object deserialized = ois.readObject();
    ois.close();
    assertNotNull(deserialized);
    assertTrue(deserialized instanceof CRF);
}

