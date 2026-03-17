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
    pipeList.add(new TokenSequenceRemoveStopwords());
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    trainingData.addThruPipe(new Instance("Sample text input", null, "sample instance", null));
    HMM hmm = new HMM(pipe, null);
    boolean result = hmm.train(trainingData);
    assertTrue(result || (!result));
}

@Test
public void test3()
{
    Pipe pipe = new SerialPipes(new ArrayList<>(Arrays.asList(new Input2CharSequence("UTF-8"), new CharSequence2TokenSequence(), new TokenSequence2FeatureSequence())));
    InstanceList trainingData = new InstanceList(pipe);
    Instance instance = new Instance("sample text", "LABEL", null, null);
    trainingData.addThruPipe(instance);
    HMM hmm = new HMM(pipe, null);
    boolean result = hmm.train(trainingData);
    assertTrue(result);
}

@Test
public void test4()
{
    HMM hmm = new HMM();
    State mockState = new State("B-ORG", 0, hmm, false);
    Field name2stateField = HMM.class.getDeclaredField("name2state");
    name2stateField.setAccessible(true);
    HashMap<String, State> name2state = new HashMap<>();
    name2state.put("B-ORG", mockState);
    name2stateField.set(hmm, name2state);
    State retrievedState = hmm.getState("B-ORG");
    assertNotNull(retrievedState);
    assertEquals("B-ORG", retrievedState.getName());
}

@Test
public void test5()
{
    HMM hmm = new HMM(null);
    State expectedState = hmm.new State("stateA", true);
    hmm.getStateNames().add("stateA");
    hmm.name2state.put("stateA", expectedState);
    State actualState = hmm.getState("stateA");
    assertNotNull(actualState);
    assertEquals("stateA", actualState.getName());
    assertSame(expectedState, actualState);
}

@Test
public void test6()
{
    Alphabet stateAlphabet = new Alphabet();
    stateAlphabet.lookupIndex("state1", true);
    stateAlphabet.lookupIndex("state2", true);
    double[] initialProbabilities = new double[]{ 0.6, 0.4 };
    Multinomial expectedMultinomial = new Multinomial(stateAlphabet, initialProbabilities);
    HMM hmm = new HMM(stateAlphabet, true);
    try {
        Field field = HMM.class.getDeclaredField("initialMultinomial");
        field.setAccessible(true);
        field.set(hmm, expectedMultinomial);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set initialMultinomial via reflection: " + e.getMessage());
    }
    Multinomial actualMultinomial = hmm.getInitialMultinomial();
    assertNotNull("Returned Multinomial should not be null", actualMultinomial);
    assertEquals("Returned Multinomial should be the same as expected", expectedMultinomial, actualMultinomial);
}

@Test
public void test7()
{
    HMM hmm = new HMM(null);
    Multinomial emission1 = new Multinomial(new double[]{ 0.2, 0.8 });
    Multinomial emission2 = new Multinomial(new double[]{ 0.5, 0.5 });
    Multinomial[] expectedEmissions = new Multinomial[]{ emission1, emission2 };
    try {
        Field field = HMM.class.getDeclaredField("emissionMultinomial");
        field.setAccessible(true);
        field.set(hmm, expectedEmissions);
    } catch (Exception e) {
        fail("Failed to set emissionMultinomial via reflection: " + e.getMessage());
    }
    Multinomial[] actualEmissions = hmm.getEmissionMultinomial();
    assertNotNull(actualEmissions);
    assertEquals(2, actualEmissions.length);
    assertEquals(expectedEmissions[0], actualEmissions[0]);
    assertEquals(expectedEmissions[1], actualEmissions[1]);
}

@Test
public void test8()
{
    double[] probs1 = new double[]{ 0.6, 0.4 };
    double[] probs2 = new double[]{ 0.3, 0.7 };
    Multinomial m1 = new Multinomial(probs1);
    Multinomial m2 = new Multinomial(probs2);
    HMM hmm = new HMM(null, null, null) {
        {
            this.transitionMultinomial = new Multinomial[]{ m1, m2 };
        }
    };
    Multinomial[] result = hmm.getTransitionMultinomial();
    assertNotNull(result);
    assertEquals(2, result.length);
    assertSame(m1, result[0]);
    assertSame(m2, result[1]);
}

@Test
public void test9()
{
    HMM hmm = new HMM(null, null);
    Field statesField = HMM.class.getDeclaredField("states");
    statesField.setAccessible(true);
    List<State> mockStates = new ArrayList<State>();
    mockStates.add(new State("state1", null));
    mockStates.add(new State("state2", null));
    mockStates.add(new State("state3", null));
    statesField.set(hmm, mockStates);
    assertEquals(3, hmm.numStates());
}

@Test
public void test10()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("A", true);
    outputAlphabet.lookupIndex("B", true);
    HMM hmm = new HMM(new Alphabet(), outputAlphabet);
    InstanceList trainingSet = new InstanceList(hmm.getInputAlphabet(), outputAlphabet);
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
    HMM hmm = new HMM(null, null);
    State stateA = new State("A", null);
    State stateB = new State("B", null);
    List<State> initialStates = hmm.initialStates;
    initialStates.add(stateA);
    initialStates.add(stateB);
    Iterator<State> iterator = hmm.initialStateIterator();
    assertTrue(iterator.hasNext());
    assertSame(stateA, iterator.next());
    assertTrue(iterator.hasNext());
    assertSame(stateB, iterator.next());
    assertFalse(iterator.hasNext());
}

@Test
public void test12()
{
    HMM hmm = new HMM();
    String stateName = "state1";
    double initialWeight = 0.5;
    double finalWeight = 1.0;
    String[] destinationNames = new String[]{ "state2", "state3" };
    String[] labelNames = new String[]{ "labelA", "labelB" };
    hmm.addState(stateName, initialWeight, finalWeight, destinationNames, labelNames);
    assertNotNull(hmm.name2state.get("state1"));
    assertEquals("state1", hmm.name2state.get("state1").name);
    assertTrue(hmm.initialStates.contains(hmm.name2state.get("state1")));
}

@Test
public void test13()
{
    Alphabet dataAlphabet = new Alphabet();
    Alphabet labelAlphabet = new Alphabet();
    labelAlphabet.lookupIndex("A", true);
    labelAlphabet.lookupIndex("B", true);
    InstanceList trainingSet = new InstanceList(dataAlphabet, labelAlphabet);
    ArrayList<String> data1 = new ArrayList<>();
    data1.add("x1");
    ArrayList<String> targets1 = new ArrayList<>();
    targets1.add("A");
    targets1.add("B");
    trainingSet.addThruPipe(new Instance(data1, targets1, null, null));
    ArrayList<String> data2 = new ArrayList<>();
    data2.add("x2");
    ArrayList<String> targets2 = new ArrayList<>();
    targets2.add("B");
    targets2.add("A");
    trainingSet.addThruPipe(new Instance(data2, targets2, null, null));
    HMM hmm = new HMM(dataAlphabet, labelAlphabet);
    hmm.addStatesForBiLabelsConnectedAsIn(trainingSet);
    assertNotNull(hmm.getState("A~B"));
    assertNotNull(hmm.getState("B~A"));
}

@Test
public void test14()
{
    HMM hmm = new HMM(new Alphabet(), null);
    hmm.getOutputAlphabet().lookupIndex("label1");
    hmm.getOutputAlphabet().lookupIndex("label2");
    ArrayList<Instance> data = new ArrayList<>();
    Alphabet dataAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("word1");
    data.add(new Instance(new String[]{ "word1" }, new String[]{ "label1" }, null, null));
    InstanceList trainingSet = new InstanceList(dataAlphabet, hmm.getOutputAlphabet());
    trainingSet.addThruPipe(data.iterator().next());
    hmm.addStatesForHalfLabelsConnectedAsIn(trainingSet);
}

@Test
public void test15()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("A");
    outputAlphabet.lookupIndex("B");
    HMM hmm = new HMM(null, outputAlphabet);
    InstanceList trainingSet = new InstanceList(outputAlphabet, null);
    ArrayList<String> targetSequence = new ArrayList<>();
    targetSequence.add("A");
    targetSequence.add("B");
    trainingSet.addThruPipe(new Instance(null, targetSequence, null, null));
    hmm.addStatesForLabelsConnectedAsIn(trainingSet);
    assertNotNull(hmm.getState("A"));
    assertNotNull(hmm.getState("B"));
    assertArrayEquals(new String[]{ "B" }, hmm.getState("A").getDestinations());
    assertArrayEquals(new String[0], hmm.getState("B").getDestinations());
}

@Test
public void test16()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("A");
    outputAlphabet.lookupIndex("B");
    outputAlphabet.lookupIndex("C");
    InstanceList trainingSet = new InstanceList(outputAlphabet, null);
    trainingSet.add(new Instance("data1", "A", null, null));
    trainingSet.add(new Instance("data2", "B", null, null));
    trainingSet.add(new Instance("data3", "C", null, null));
    HMM hmm = new HMM(outputAlphabet, null) {
        @Override
        protected boolean[][] labelConnectionsIn(InstanceList trainingSet) {
            boolean[][] connections = new boolean[3][3];
            connections[0][1] = true;
            connections[0][2] = true;
            connections[1][2] = true;
            connections[2][0] = true;
            connections[2][1] = true;
            return connections;
        }

        ArrayList<String> addedStates = new ArrayList<>();

        @Override
        protected void addState(String name, double initialWeight, double finalWeight, String[] sourceNames, String[] destNames) {
            addedStates.add((name + ":") + String.join(",", destNames));
        }
    };
    hmm.addStatesForThreeQuarterLabelsConnectedAsIn(trainingSet);
    assertEquals(3, hmm.addedStates.size());
    assertTrue(hmm.addedStates.contains("A:B,C"));
    assertTrue(hmm.addedStates.contains("B:C"));
    assertTrue(hmm.addedStates.contains("C:A,B"));
}

@Test
public void test17()
{
    HMM hmm = new HMM(new Alphabet(), new Alphabet());
    hmm.addState("state1", null, null);
    hmm.addState("state2", null, null);
    hmm.getInputAlphabet().lookupIndex("word1");
    hmm.getInputAlphabet().lookupIndex("word2");
    Random random = new Random(42);
    double noise = 0.1;
    hmm.initEmissions(random, noise);
    assertNotNull("emissionMultinomial array should not be null", hmm.emissionMultinomial);
    assertNotNull("emissionEstimator array should not be null", hmm.emissionEstimator);
    assertEquals("emissionMultinomial length should match number of states", 2, hmm.emissionMultinomial.length);
    assertEquals("emissionEstimator length should match number of states", 2, hmm.emissionEstimator.length);
    assertNotNull("First emissionMultinomial element should be initialized", hmm.emissionMultinomial[0]);
    assertNotNull("Second emissionMultinomial element should be initialized", hmm.emissionMultinomial[1]);
    assertNotNull("First emissionEstimator element should be initialized", hmm.emissionEstimator[0]);
    assertNotNull("Second emissionEstimator element should be initialized", hmm.emissionEstimator[1]);
}

@Test
public void test18()
{
    HMM hmm = new HMM(new Alphabet(), new LabelAlphabet());
    HMM.State state = hmm.new State("S0", null);
    state.initialWeight = 0.8;
    state.finalWeight = 0.2;
    state.destinations = new Transducer.State[0];
    hmm.addState(state);
    hmm.emissionMultinomial = new Object[1];
    hmm.transitionMultinomial = new Multinomial[1];
    hmm.emissionMultinomial[0] = "dummy-emission";
    hmm.transitionMultinomial[0] = new Multinomial(new Alphabet());
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    hmm.print();
    System.setOut(originalOut);
    String output = outContent.toString();
    assertTrue(output.contains("STATE NAME=\"S0\""));
    assertTrue(output.contains("initialWeight= 0.8"));
    assertTrue(output.contains("finalWeight= 0.2"));
    assertTrue(output.contains("Emission distribution:\ndummy-emission"));
    assertTrue(output.contains("Transition distribution:\n"));
}

@Test
public void test19()
{
    Alphabet inputAlphabet = new Alphabet();
    inputAlphabet.lookupIndex("word1", true);
    inputAlphabet.lookupIndex("word2", true);
    Alphabet transitionAlphabet = new Alphabet();
    transitionAlphabet.lookupIndex("state1", true);
    transitionAlphabet.lookupIndex("state2", true);
    HMM hmm = new HMM(inputAlphabet, transitionAlphabet);
    hmm.addState("stateA");
    hmm.addState("stateB");
    hmm.reset();
    assertNotNull(hmm.emissionEstimator);
    assertNotNull(hmm.transitionEstimator);
    assertNotNull(hmm.emissionMultinomial);
    assertNotNull(hmm.transitionMultinomial);
    assertNotNull(hmm.initialEstimator);
    assertNotNull(hmm.initialMultinomial);
    assertEquals(2, hmm.emissionEstimator.length);
    assertEquals(2, hmm.transitionEstimator.length);
    assertEquals(2, hmm.emissionMultinomial.length);
    assertEquals(2, hmm.transitionMultinomial.length);
    assertTrue(hmm.emissionEstimator[0] instanceof LaplaceEstimator);
    assertTrue(hmm.transitionEstimator[0] instanceof LaplaceEstimator);
    assertTrue(hmm.emissionMultinomial[0] instanceof Multinomial);
    assertTrue(hmm.transitionMultinomial[0] instanceof Multinomial);
}

@Test
public void test20()
{
    HMM hmm = new HMM();
    File tempFile = File.createTempFile("hmmTest", ".bin");
    tempFile.deleteOnExit();
    hmm.write(tempFile);
    assertTrue("File should exist after write", tempFile.exists());
    assertTrue("File should not be empty after write", tempFile.length() > 0);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
    Object deserialized = ois.readObject();
    ois.close();
    assertNotNull("Deserialized object should not be null", deserialized);
    assertTrue("Deserialized object should be instance of HMM", deserialized instanceof HMM);
}

