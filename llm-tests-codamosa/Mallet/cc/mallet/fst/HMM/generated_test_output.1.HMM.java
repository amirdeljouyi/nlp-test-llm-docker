import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    HMM hmm = new HMM();
    boolean result = hmm.isTrainable();
    assertTrue("Expected isTrainable() to return true", result);
}

@Test
public void test2()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new Input2CharSequence("UTF-8"));
    pipeList.add(new CharSequence2TokenSequence());
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList instanceList = new InstanceList(pipe);
    Instance instance = new Instance("This is a test sequence", "label", null, null);
    instanceList.addThruPipe(instance);
    HMM hmm = new HMM(pipe, null);
    boolean result = hmm.train(instanceList);
    assertTrue(result);
}

@Test
public void test3()
{
    Alphabet dataAlphabet = new Alphabet();
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    String[] features = new String[]{ "word1", "word2" };
    int[] labelIndices = new int[]{ labelAlphabet.lookupIndex("A"), labelAlphabet.lookupIndex("B") };
    FeatureVectorSequence fvs = new FeatureVectorSequence(new String[][]{ new String[]{ "word1" }, new String[]{ "word2" } }, dataAlphabet);
    LabelSequence labels = new LabelSequence(labelAlphabet, labelIndices);
    InstanceList trainingList = new InstanceList(dataAlphabet, labelAlphabet);
    Instance instance = new Instance(fvs, labels, "instance1", null);
    trainingList.addThruPipe(instance);
    HMM hmm = new HMM(dataAlphabet, labelAlphabet);
    boolean result = hmm.train(trainingList);
    assertTrue(result);
}

@Test
public void test4()
{
    HMM hmm = new HMM(null);
    HMM.State state = hmm.new State("stateA", null);
    hmm.name2state.put("stateA", state);
    HMM.State result = hmm.getState("stateA");
    assertNotNull(result);
    assertEquals("stateA", result.getName());
    assertSame(state, result);
}

@Test
public void test5()
{
    HMM hmm = new HMM(null);
    State expectedState = hmm.new State("STATE_A", 0.0);
    Field name2stateField = HMM.class.getDeclaredField("name2state");
    name2stateField.setAccessible(true);
    HashMap<String, State> name2stateMap = new HashMap<>();
    name2stateMap.put("STATE_A", expectedState);
    name2stateField.set(hmm, name2stateMap);
    State actualState = hmm.getState("STATE_A");
    assertSame(expectedState, actualState);
}

@Test
public void test6()
{
    double[] probabilities = new double[]{ 0.5, 0.3, 0.2 };
    Multinomial expectedMultinomial = new Multinomial(probabilities);
    HMM hmm = new HMM(null, null);
    try {
        Field field = HMM.class.getDeclaredField("initialMultinomial");
        field.setAccessible(true);
        field.set(hmm, expectedMultinomial);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set initialMultinomial via reflection: " + e.getMessage());
    }
    Multinomial actualMultinomial = hmm.getInitialMultinomial();
    assertSame("getInitialMultinomial should return the same Multinomial instance", expectedMultinomial, actualMultinomial);
}

@Test
public void test7()
{
    HMM hmm = new HMM(null);
    Multinomial m1 = new Multinomial(new double[]{ 0.2, 0.8 });
    Multinomial m2 = new Multinomial(new double[]{ 0.5, 0.5 });
    Multinomial[] expectedArray = new Multinomial[]{ m1, m2 };
    try {
        Field field = HMM.class.getDeclaredField("emissionMultinomial");
        field.setAccessible(true);
        field.set(hmm, expectedArray);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(e);
    }
    Multinomial[] result = hmm.getEmissionMultinomial();
    Assert.assertArrayEquals("Returned Multinomial array does not match the expected array", expectedArray, result);
}

@Test
public void test8()
{
    HMM hmm = new HMM(null, null);
    Multinomial m1 = new Multinomial(new double[]{ 0.2, 0.8 });
    Multinomial m2 = new Multinomial(new double[]{ 0.5, 0.5 });
    Multinomial[] expected = new Multinomial[]{ m1, m2 };
    try {
        Field field = HMM.class.getDeclaredField("transitionMultinomial");
        field.setAccessible(true);
        field.set(hmm, expected);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    Multinomial[] actual = hmm.getTransitionMultinomial();
    assertNotNull(actual);
    assertEquals(2, actual.length);
    assertEquals(0.2, actual[0].getProbability(0), 1.0E-4);
    assertEquals(0.8, actual[0].getProbability(1), 1.0E-4);
    assertEquals(0.5, actual[1].getProbability(0), 1.0E-4);
    assertEquals(0.5, actual[1].getProbability(1), 1.0E-4);
}

@Test
public void test9()
{
    HMM hmm = new HMM(null);
    Field statesField = HMM.class.getDeclaredField("states");
    statesField.setAccessible(true);
    List<State> mockStates = new ArrayList<State>();
    mockStates.add(null);
    mockStates.add(null);
    mockStates.add(null);
    statesField.set(hmm, mockStates);
    int expected = 3;
    int actual = hmm.numStates();
    assertEquals(expected, actual);
}

@Test
public void test10()
{
    HMM hmm = new HMM(new Alphabet(), null);
    hmm.outputAlphabet.lookupIndex("A");
    hmm.outputAlphabet.lookupIndex("B");
    InstanceList trainingSet = new InstanceList(hmm.getInputAlphabet(), hmm.getOutputAlphabet());
    trainingSet.add(new Instance("input1", "A", null, null));
    trainingSet.add(new Instance("input2", "B", null, null));
    int[] orders = null;
    boolean[] defaults = null;
    String start = "START";
    Pattern forbidden = null;
    Pattern allowed = null;
    boolean fullyConnected = true;
    String result = hmm.addOrderNStates(trainingSet, orders, defaults, start, forbidden, allowed, fullyConnected);
    assertEquals("START", result);
}

@Test
public void test11()
{
    State mockState1 = mock(State.class);
    State mockState2 = mock(State.class);
    HMM hmm = new HMM(null, null) {
        {
            initialStates = Arrays.asList(mockState1, mockState2);
        }
    };
    Iterator<State> iterator = hmm.initialStateIterator();
    assertTrue(iterator.hasNext());
    assertSame(mockState1, iterator.next());
    assertTrue(iterator.hasNext());
    assertSame(mockState2, iterator.next());
    assertFalse(iterator.hasNext());
}

@Test
public void test12()
{
    HMM hmm = new HMM();
    hmm.states = new ArrayList<>();
    hmm.initialStates = new ArrayList<>();
    hmm.name2state = new HashMap<>();
    String name = "state1";
    double initialWeight = 0.5;
    double finalWeight = 0.8;
    String[] destinationNames = new String[]{ "state2", "state3" };
    String[] labelNames = new String[]{ "labelA", "labelB" };
    hmm.addState(name, initialWeight, finalWeight, destinationNames, labelNames);
    assert hmm.name2state.containsKey("state1");
    assert hmm.states.size() == 1;
    assert hmm.initialStates.contains(hmm.name2state.get("state1"));
}

@Test
public void test13()
{
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("B");
    labelAlphabet.lookupIndex("I");
    labelAlphabet.lookupIndex("O");
    Alphabet inputAlphabet = new Alphabet();
    inputAlphabet.lookupIndex("word1");
    inputAlphabet.lookupIndex("word2");
    inputAlphabet.lookupIndex("word3");
    ArrayList<Instance> instances = new ArrayList<>();
    TokenSequence tokenSequence = new TokenSequence();
    tokenSequence.add("word1");
    tokenSequence.add("word2");
    tokenSequence.add("word3");
    LabelSequence labelSequence = new LabelSequence(labelAlphabet, new int[]{ labelAlphabet.lookupIndex("B"), labelAlphabet.lookupIndex("I"), labelAlphabet.lookupIndex("O") });
    instances.add(new Instance(tokenSequence, labelSequence, null, null));
    InstanceList trainingList = new InstanceList(inputAlphabet, labelAlphabet);
    trainingList.addThruPipe(instances.get(0));
    HMM hmm = new HMM(inputAlphabet, labelAlphabet);
    hmm.addStatesForBiLabelsConnectedAsIn(trainingList);
    boolean stateBIExists = hmm.getState("B::I") != null;
    boolean stateIOExists = hmm.getState("I::O") != null;
    Assert.assertTrue("Expected state B::I to be added", stateBIExists);
    Assert.assertTrue("Expected state I::O to be added", stateIOExists);
}

@Test
public void test14()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    labelAlphabet.lookupIndex("A");
    labelAlphabet.lookupIndex("B");
    InstanceList trainingSet = new InstanceList(labelAlphabet, null);
    ArrayList<String> data1 = new ArrayList<>();
    data1.add("w1");
    data1.add("w2");
    ArrayList<String> target1 = new ArrayList<>();
    target1.add("A");
    target1.add("B");
    trainingSet.addThruPipe(new Instance(data1, target1, null, null));
    HMM hmm = new HMM(labelAlphabet, null);
    hmm.addStatesForHalfLabelsConnectedAsIn(trainingSet);
    assertEquals(2, hmm.numStates());
    assertNotNull(hmm.getState("A"));
    assertNotNull(hmm.getState("B"));
    String[] transitionsFromA = hmm.getState("A").getDestinationNames();
    String[] transitionsFromB = hmm.getState("B").getDestinationNames();
    assertArrayEquals(new String[]{ "B" }, transitionsFromA);
    assertArrayEquals(new String[]{  }, transitionsFromB);
}

@Test
public void test15()
{
    Alphabet inputAlphabet = new Alphabet();
    LabelAlphabet outputAlphabet = new LabelAlphabet();
    HMM hmm = new HMM(inputAlphabet, outputAlphabet);
    outputAlphabet.lookupIndex("A", true);
    outputAlphabet.lookupIndex("B", true);
    InstanceList trainingSet = new InstanceList(inputAlphabet, outputAlphabet);
    TokenSequence data = new TokenSequence();
    data.add("x1");
    data.add("x2");
    Sequence<String> target = new LabelSequence(outputAlphabet, new String[]{ "A", "B" });
    Instance instance = new Instance(data, target, null, null);
    trainingSet.addThruPipe(instance);
    hmm.addStatesForLabelsConnectedAsIn(trainingSet);
    assertNotNull(hmm.getState("A"));
    assertNotNull(hmm.getState("B"));
    String[] aTransitions = hmm.getState("A").getDestinationNames();
    assertEquals(1, aTransitions.length);
    assertEquals("B", aTransitions[0]);
}

@Test
public void test16()
{
    HMM hmm = new HMM(new Alphabet(), null);
    Alphabet alphabet = hmm.getOutputAlphabet();
    alphabet.lookupIndex("A");
    alphabet.lookupIndex("B");
    alphabet.lookupIndex("C");
    InstanceList trainingSet = new InstanceList(hmm.getInputPipe());
    trainingSet.add(new Instance("data1", "A", null, null));
    trainingSet.add(new Instance("data2", "B", null, null));
    trainingSet.add(new Instance("data3", "C", null, null));
    HMM testHMM = new HMM(alphabet, null) {
        @Override
        protected boolean[][] labelConnectionsIn(InstanceList trainingData) {
            boolean[][] connections = new boolean[3][3];
            connections[0][1] = true;
            connections[1][2] = true;
            connections[2][0] = true;
            return connections;
        }
    };
    testHMM.addStatesForThreeQuarterLabelsConnectedAsIn(trainingSet);
    assert testHMM.getStates().size() == 3;
    assert "A".equals(testHMM.getState(0).getName());
    assert "B".equals(testHMM.getState(1).getName());
    assert "C".equals(testHMM.getState(2).getName());
}

@Test
public void test17()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet transitionAlphabet = new Alphabet();
    inputAlphabet.lookupIndex("feature1");
    transitionAlphabet.lookupIndex("state1");
    transitionAlphabet.lookupIndex("state2");
    HMM hmm = new HMM(inputAlphabet, transitionAlphabet);
    State state0 = hmm.addState("state1");
    State state1 = hmm.addState("state2");
    LaplaceEstimator initialEstimator = new LaplaceEstimator(transitionAlphabet);
    initialEstimator.add("state1");
    initialEstimator.add("state2");
    hmm.initialEstimator = initialEstimator;
    LaplaceEstimator emissionEstimator0 = new LaplaceEstimator(inputAlphabet);
    LaplaceEstimator emissionEstimator1 = new LaplaceEstimator(inputAlphabet);
    emissionEstimator0.add("feature1");
    emissionEstimator1.add("feature1");
    LaplaceEstimator transitionEstimator0 = new LaplaceEstimator(transitionAlphabet);
    LaplaceEstimator transitionEstimator1 = new LaplaceEstimator(transitionAlphabet);
    transitionEstimator0.add("state1");
    transitionEstimator1.add("state2");
    hmm.emissionEstimator = new MultinomialEstimator[]{ emissionEstimator0, emissionEstimator1 };
    hmm.transitionEstimator = new MultinomialEstimator[]{ transitionEstimator0, transitionEstimator1 };
    hmm.estimate();
    assertNotNull(hmm.initialMultinomial);
    assertTrue(hmm.initialMultinomial.logProbability("state1") < 0.0);
    assertTrue(hmm.initialMultinomial.logProbability("state2") < 0.0);
    assertNotNull(hmm.emissionMultinomial[0]);
    assertNotNull(hmm.emissionMultinomial[1]);
    assertNotNull(hmm.transitionMultinomial[0]);
    assertNotNull(hmm.transitionMultinomial[1]);
    assertNotNull(hmm.emissionEstimator[0]);
    assertNotNull(hmm.emissionEstimator[1]);
    assertEquals(0, hmm.emissionEstimator[0].getTotalCount());
    assertEquals(0, hmm.transitionEstimator[1].getTotalCount());
    assertEquals(hmm.initialMultinomial.logProbability("state1"), state0.getInitialWeight(), 1.0E-4);
    assertEquals(hmm.initialMultinomial.logProbability("state2"), state1.getInitialWeight(), 1.0E-4);
}

@Test
public void test18()
{
    HMM hmm = new HMM(new Alphabet(), new Alphabet());
    hmm.getInputAlphabet().lookupIndex("word1");
    hmm.getInputAlphabet().lookupIndex("word2");
    hmm.getInputAlphabet().lookupIndex("word3");
    hmm.addState("state1");
    hmm.addState("state2");
    Random random = new Random(42);
    double noise = 0.1;
    hmm.initEmissions(random, noise);
    Assert.assertNotNull("emissionMultinomial should not be null", hmm.emissionMultinomial);
    Assert.assertNotNull("emissionEstimator should not be null", hmm.emissionEstimator);
    Assert.assertEquals("emissionMultinomial length should match number of states", 2, hmm.emissionMultinomial.length);
    Assert.assertEquals("emissionEstimator length should match number of states", 2, hmm.emissionEstimator.length);
    Assert.assertNotNull("First emission multinomial should not be null", hmm.emissionMultinomial[0]);
    Assert.assertNotNull("First emission estimator should not be null", hmm.emissionEstimator[0]);
}

@Test
public void test19()
{
    HMM hmm = new HMM(null);
    HMM.State state = hmm.new State("S0", hmm);
    state.initialWeight = 0.7;
    state.finalWeight = 0.2;
    state.destinations = new HMM.Transition[0];
    hmm.addState(state);
    hmm.emissionMultinomial = new Object[1];
    hmm.transitionMultinomial = new Object[1];
    hmm.emissionMultinomial[0] = "EmissionS0";
    hmm.transitionMultinomial[0] = new Object() {
        @Override
        public String toString() {
            return "TransitionS0";
        }
    };
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    hmm.print();
    System.setOut(originalOut);
    String output = outContent.toString();
    assertTrue(output.contains("STATE NAME=\"S0\" (0 outgoing transitions)"));
    assertTrue(output.contains("initialWeight= 0.7"));
    assertTrue(output.contains("finalWeight= 0.2"));
    assertTrue(output.contains("Emission distribution:\nEmissionS0"));
    assertTrue(output.contains("Transition distribution:\nTransitionS0"));
}

@Test
public void test20()
{
    Alphabet inputAlphabet = new Alphabet();
    inputAlphabet.lookupIndex("word1");
    inputAlphabet.lookupIndex("word2");
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("tag1");
    outputAlphabet.lookupIndex("tag2");
    HMM hmm = new HMM(inputAlphabet, outputAlphabet);
    hmm.addState("tag1");
    hmm.addState("tag2");
    hmm.reset();
    assertNotNull("Emission estimators should be initialized", getPrivateFieldValue(hmm, "emissionEstimator"));
    assertNotNull("Transition estimators should be initialized", getPrivateFieldValue(hmm, "transitionEstimator"));
    assertNotNull("Emission multinomials should be initialized", getPrivateFieldValue(hmm, "emissionMultinomial"));
    assertNotNull("Transition multinomials should be initialized", getPrivateFieldValue(hmm, "transitionMultinomial"));
    assertNotNull("Initial multinomial should be initialized", getPrivateFieldValue(hmm, "initialMultinomial"));
    assertNotNull("Initial estimator should be initialized", getPrivateFieldValue(hmm, "initialEstimator"));
    LaplaceEstimator[] emissionEstimators = ((LaplaceEstimator[]) (getPrivateFieldValue(hmm, "emissionEstimator")));
    LaplaceEstimator[] transitionEstimators = ((LaplaceEstimator[]) (getPrivateFieldValue(hmm, "transitionEstimator")));
    Multinomial[] emissionMultinomials = ((Multinomial[]) (getPrivateFieldValue(hmm, "emissionMultinomial")));
    Multinomial[] transitionMultinomials = ((Multinomial[]) (getPrivateFieldValue(hmm, "transitionMultinomial")));
    Multinomial initialMultinomial = ((Multinomial) (getPrivateFieldValue(hmm, "initialMultinomial")));
    LaplaceEstimator initialEstimator = ((LaplaceEstimator) (getPrivateFieldValue(hmm, "initialEstimator")));
    assertEquals("Emission estimators array length mismatch", 2, emissionEstimators.length);
    assertEquals("Transition estimators array length mismatch", 2, transitionEstimators.length);
    assertEquals("Emission multinomials array length mismatch", 2, emissionMultinomials.length);
    assertEquals("Transition multinomials array length mismatch", 2, transitionMultinomials.length);
    assertTrue("Initial multinomial should be instance of Multinomial", initialMultinomial instanceof Multinomial);
    assertTrue("Initial estimator should be instance of LaplaceEstimator", initialEstimator instanceof LaplaceEstimator);
}

@Test
public void test21()
{
    HMM hmm = new HMM();
    File tempFile = File.createTempFile("hmm_test", ".ser");
    tempFile.deleteOnExit();
    hmm.write(tempFile);
    assertTrue(tempFile.exists());
    assertTrue(tempFile.length() > 0);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
    Object deserialized = ois.readObject();
    ois.close();
    assertNotNull(deserialized);
    assertTrue(deserialized instanceof HMM);
}

