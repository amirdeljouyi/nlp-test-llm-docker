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
    ArrayList pipelist = new ArrayList();
    pipelist.add(new CharSequence2TokenSequence("\\p{L}+"));
    pipelist.add(new TokenSequenceLowercase());
    pipelist.add(new TokenSequenceRemoveStopwords(false, false));
    pipelist.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipelist);
    InstanceList trainingData = new InstanceList(pipe);
    pipe.setTargetProcessing(true);
    trainingData.addThruPipe(new Instance("This is a test sentence", "label", null, null));
    HMM hmm = new HMM(pipe, null);
    boolean result = hmm.train(trainingData);
    assertTrue(result);
}

@Test
public void test3()
{
    ArrayList pipeList = new ArrayList();
    pipeList.add(new SimpleTaggerSentence2TokenSequence());
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequenceRemoveNonAlpha());
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    String sentence = "Hello_World O\nHow_are_you B\n";
    trainingData.addThruPipe(new Instance(sentence, null, "instance1", null));
    HMM hmm = new HMM(pipe, null);
    boolean result = hmm.train(trainingData);
    assertTrue(result);
}

@Test
public void test4()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new Input2CharSequence("UTF-8"));
    pipeList.add(new CharSequence2TokenSequence());
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequenceRemoveStopwords(false));
    pipeList.add(new TokenSequence2FeatureSequence());
    pipeList.add(new Target2LabelSequence());
    pipeList.add(new TokenSequence2FeatureVectorSequence());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    trainingData.addThruPipe(new Instance("This is a test", "label", null, null));
    trainingData.addThruPipe(new Instance("Another test sentence", "label", null, null));
    HMM hmm = new HMM(pipe, null);
    boolean result = hmm.train(trainingData);
    assertTrue(result);
}

@Test
public void test5()
{
    HMM hmm = new HMM(null);
    State expectedState = hmm.new State("SAMPLE_STATE", false);
    Field name2stateField = HMM.class.getDeclaredField("name2state");
    name2stateField.setAccessible(true);
    HashMap<String, State> name2stateMap = new HashMap<>();
    name2stateMap.put("SAMPLE_STATE", expectedState);
    name2stateField.set(hmm, name2stateMap);
    State actualState = hmm.getState("SAMPLE_STATE");
    assertNotNull(actualState);
    assertEquals("SAMPLE_STATE", actualState.getName());
    assertSame(expectedState, actualState);
}

@Test
public void test6()
{
    HMM hmm = new HMM();
    State expectedState = hmm.new State("B", 0.0);
    hmm.addState(expectedState);
    State actualState = hmm.getState("B");
    Assert.assertNotNull(actualState);
    Assert.assertEquals("B", actualState.getName());
    Assert.assertSame(expectedState, actualState);
}

@Test
public void test7()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("START");
    alphabet.lookupIndex("MIDDLE");
    alphabet.lookupIndex("END");
    Multinomial expectedMultinomial = new Multinomial(alphabet);
    expectedMultinomial.setProbability("START", 0.8);
    expectedMultinomial.setProbability("MIDDLE", 0.1);
    expectedMultinomial.setProbability("END", 0.1);
    HMM hmm = new HMM(alphabet, null);
    try {
        Field field = HMM.class.getDeclaredField("initialMultinomial");
        field.setAccessible(true);
        field.set(hmm, expectedMultinomial);
    } catch (Exception e) {
        fail("Failed to set initialMultinomial via reflection: " + e.getMessage());
    }
    Multinomial actualMultinomial = hmm.getInitialMultinomial();
    assertNotNull(actualMultinomial);
    assertEquals(expectedMultinomial, actualMultinomial);
}

@Test
public void test8()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    int numStates = 2;
    HMM hmm = new HMM(inputAlphabet, outputAlphabet, numStates);
    Multinomial m0 = new Multinomial(outputAlphabet);
    m0.increment("word0");
    Multinomial m1 = new Multinomial(outputAlphabet);
    m1.increment("word1");
    Multinomial[] customEmissions = new Multinomial[]{ m0, m1 };
    try {
        Field field = HMM.class.getDeclaredField("emissionMultinomial");
        field.setAccessible(true);
        field.set(hmm, customEmissions);
    } catch (Exception e) {
        fail("Failed to set emissionMultinomial field: " + e.getMessage());
    }
    Multinomial[] result = hmm.getEmissionMultinomial();
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals(1.0, result[0].getProbability("word0"), 1.0E-4);
    assertEquals(1.0, result[1].getProbability("word1"), 1.0E-4);
}

@Test
public void test9()
{
    Multinomial m1 = new Multinomial(new double[]{ 0.5, 0.5 });
    Multinomial m2 = new Multinomial(new double[]{ 0.2, 0.3, 0.5 });
    HMM hmm = new HMM(null, null, null);
    Multinomial[] expected = new Multinomial[]{ m1, m2 };
    try {
        Field field = HMM.class.getDeclaredField("transitionMultinomial");
        field.setAccessible(true);
        field.set(hmm, expected);
    } catch (Exception e) {
        fail("Reflection error: " + e.getMessage());
    }
    Multinomial[] actual = hmm.getTransitionMultinomial();
    assertNotNull(actual);
    assertEquals(2, actual.length);
    assertSame(m1, actual[0]);
    assertSame(m2, actual[1]);
}

@Test
public void test10()
{
    HMM hmm = new HMM(null, null);
    hmm.addState("S1");
    hmm.addState("S2");
    hmm.addState("S3");
    assertEquals(3, hmm.numStates());
}

@Test
public void test11()
{
    HMM hmm = new HMM(new Alphabet(), null);
    hmm.getOutputAlphabet().lookupIndex("O");
    hmm.getOutputAlphabet().lookupIndex("I");
    Alphabet dataAlphabet = new Alphabet();
    InstanceList trainingSet = new InstanceList(dataAlphabet, hmm.getOutputAlphabet());
    trainingSet.add(new Instance(new String[]{ "feature1" }, "O", "name1", null));
    trainingSet.add(new Instance(new String[]{ "feature2" }, "I", "name2", null));
    int[] orders = null;
    boolean[] defaults = null;
    String startState = "<START>";
    Pattern forbidden = null;
    Pattern allowed = null;
    boolean fullyConnected = true;
    String result = hmm.addOrderNStates(trainingSet, orders, defaults, startState, forbidden, allowed, fullyConnected);
    assertEquals("<START>", result);
}

@Test
public void test12()
{
    HMM hmm = new HMM(null, null);
    List<State> expectedInitialStates = new ArrayList<>();
    State state1 = hmm.new State("state1", null);
    State state2 = hmm.new State("state2", null);
    expectedInitialStates.add(state1);
    expectedInitialStates.add(state2);
    hmm.initialStates.add(state1);
    hmm.initialStates.add(state2);
    Iterator<State> iterator = hmm.initialStateIterator();
    assertTrue(iterator.hasNext());
    assertSame(state1, iterator.next());
    assertTrue(iterator.hasNext());
    assertSame(state2, iterator.next());
    assertFalse(iterator.hasNext());
}

@Test
public void test13()
{
    HMM hmm = new HMM();
    String stateName = "state1";
    double initialWeight = 0.5;
    double finalWeight = 0.8;
    String[] destinationNames = new String[]{ "state2" };
    String[] labelNames = new String[]{ "labelA" };
    hmm.addState(stateName, initialWeight, finalWeight, destinationNames, labelNames);
    assertTrue(hmm.name2state.containsKey("state1"));
}

@Test
public void test14()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("A");
    outputAlphabet.lookupIndex("B");
    outputAlphabet.lookupIndex("C");
    Alphabet inputAlphabet = new Alphabet();
    InstanceList trainingSet = new InstanceList(inputAlphabet, outputAlphabet);
    ArrayList<String> seq1 = new ArrayList<>();
    seq1.add("x1");
    seq1.add("x2");
    seq1.add("x3");
    ArrayList<String> labels1 = new ArrayList<>();
    labels1.add("A");
    labels1.add("B");
    labels1.add("C");
    trainingSet.addThruPipe(new Instance(seq1, labels1, null, null));
    HMM hmm = new HMM(inputAlphabet, outputAlphabet);
    hmm.addStatesForBiLabelsConnectedAsIn(trainingSet);
    assertNotNull(hmm.getState("A~B"));
    assertNotNull(hmm.getState("B~C"));
    assertArrayEquals(new String[]{ "B~C" }, hmm.getState("A~B").getDestinationNames());
    assertArrayEquals(new String[]{ "C" }, hmm.getState("A~B").getLabelNames());
}

@Test
public void test15()
{
    Alphabet outputAlphabet = new Alphabet();
    outputAlphabet.lookupIndex("LabelA");
    outputAlphabet.lookupIndex("LabelB");
    HMM hmm = new HMM(null, outputAlphabet);
    InstanceList trainingSet = new InstanceList(null);
    ArrayList<String> dataSeq1 = new ArrayList<>();
    dataSeq1.add("LabelA");
    dataSeq1.add("LabelB");
    dataSeq1.add("LabelA");
    trainingSet.add(new Instance(dataSeq1, null, null, null));
    hmm.addStatesForHalfLabelsConnectedAsIn(trainingSet);
    assert hmm.getState("LabelA") != null;
    assert hmm.getState("LabelB") != null;
    String[] destinationsA = hmm.getState("LabelA").getDestinationStateNames();
    String[] destinationsB = hmm.getState("LabelB").getDestinationStateNames();
    assert destinationsA.length == 1;
    assert destinationsB.length == 1;
    assert destinationsA[0].equals("LabelB") || destinationsA[0].equals("LabelA");
    assert destinationsB[0].equals("LabelA") || destinationsB[0].equals("LabelB");
}

@Test
public void test16()
{
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    Alphabet dataAlphabet = new Alphabet();
    Label labelA = labelAlphabet.lookupLabel("A", true);
    Label labelB = labelAlphabet.lookupLabel("B", true);
    HMM hmm = new HMM(dataAlphabet, labelAlphabet);
    InstanceList trainingSet = new InstanceList(dataAlphabet, labelAlphabet);
    ArrayList<String> dataSequence = new ArrayList<>();
    dataSequence.add("feature1");
    dataSequence.add("feature2");
    ArrayList<String> labelSequence = new ArrayList<>();
    labelSequence.add("A");
    labelSequence.add("B");
    Instance instance = new Instance(dataSequence, labelSequence, null, null);
    trainingSet.addThruPipe(instance);
    hmm.addStatesForLabelsConnectedAsIn(trainingSet);
    assertNotNull(hmm.getState("A"));
    assertNotNull(hmm.getState("B"));
    assertTrue(hmm.getState("A").getDestinationStateNames().contains("B"));
    assertTrue(hmm.getState("B").getDestinationStateNames().isEmpty() || (!hmm.getState("B").getDestinationStateNames().contains("A")));
}

@Test
public void test17()
{
    HMM hmm = new HMM(new Alphabet(), null);
    Alphabet outputAlphabet = hmm.getOutputAlphabet();
    outputAlphabet.lookupIndex("A");
    outputAlphabet.lookupIndex("B");
    outputAlphabet.lookupIndex("C");
    InstanceList trainingSet = new InstanceList(outputAlphabet, null);
    trainingSet.add(new Instance(null, "A", null, null));
    trainingSet.add(new Instance(null, "B", null, null));
    trainingSet.add(new Instance(null, "C", null, null));
    HMM testHMM = new HMM(outputAlphabet, null) {
        @Override
        protected boolean[][] labelConnectionsIn(InstanceList trainingSet) {
            boolean[][] connections = new boolean[3][3];
            connections[0][1] = true;
            connections[0][2] = true;
            connections[1][2] = true;
            return connections;
        }
    };
    testHMM.addStatesForThreeQuarterLabelsConnectedAsIn(trainingSet);
    assertEquals(3, testHMM.numStates());
    assertNotNull(testHMM.getState("A"));
    assertNotNull(testHMM.getState("B"));
    assertNotNull(testHMM.getState("C"));
    assertArrayEquals(new String[]{ "B", "C" }, testHMM.getState("A").getDestinationNames());
    assertArrayEquals(new String[]{ "C" }, testHMM.getState("B").getDestinationNames());
    assertArrayEquals(new String[]{  }, testHMM.getState("C").getDestinationNames());
}

@Test
public void test18()
{
    Alphabet inputAlphabet = new Alphabet();
    Alphabet outputAlphabet = new Alphabet();
    HMM hmm = new HMM(inputAlphabet, outputAlphabet);
    hmm.addState("S1");
    hmm.initialEstimator = new LaplaceEstimator(hmm.getTransitionAlphabet());
    hmm.emissionEstimator = new LaplaceEstimator[1];
    hmm.emissionEstimator[0] = new LaplaceEstimator(inputAlphabet);
    hmm.transitionEstimator = new LaplaceEstimator[1];
    hmm.transitionEstimator[0] = new LaplaceEstimator(hmm.getTransitionAlphabet());
    hmm.estimate();
    assertNotNull(hmm.initialMultinomial);
    assertNotNull("emissionMultinomial[0] should not be null", hmm.emissionMultinomial[0]);
    assertNotNull("transitionMultinomial[0] should not be null", hmm.transitionMultinomial[0]);
    assertNotNull("New emissionEstimator[0] should not be null", hmm.emissionEstimator[0]);
    assertNotNull("New transitionEstimator[0] should not be null", hmm.transitionEstimator[0]);
}

@Test
public void test19()
{
    HMM hmm = new HMM(new Alphabet(), new Alphabet());
    hmm.addState(hmm.getInitialState(), "S1");
    hmm.addState(hmm.getInitialState(), "S2");
    hmm.getInputAlphabet().lookupIndex("word1");
    hmm.getInputAlphabet().lookupIndex("word2");
    hmm.getInputAlphabet().lookupIndex("word3");
    Random random = new Random(42);
    double noise = 0.1;
    hmm.initEmissions(random, noise);
    assertNotNull(hmm.emissionMultinomial);
    assertEquals(2, hmm.emissionMultinomial.length);
    assertNotNull(hmm.emissionMultinomial[0]);
    assertNotNull(hmm.emissionMultinomial[1]);
    assertNotNull(hmm.emissionEstimator);
    assertEquals(2, hmm.emissionEstimator.length);
    assertTrue(hmm.emissionEstimator[0] instanceof LaplaceEstimator);
    assertTrue(hmm.emissionEstimator[1] instanceof LaplaceEstimator);
    assertEquals(3, hmm.emissionMultinomial[0].size());
    assertEquals(3, hmm.emissionMultinomial[1].size());
}

@Test
public void test20()
{
    HMM hmm = new HMM();
    try {
        HMM.State state = hmm.new State("S1");
        Field destinationsField = State.class.getDeclaredField("destinations");
        destinationsField.setAccessible(true);
        destinationsField.set(state, new HMM.Transition[0]);
        Field initialWeightField = State.class.getDeclaredField("initialWeight");
        initialWeightField.setAccessible(true);
        initialWeightField.set(state, 0.5);
        Field finalWeightField = State.class.getDeclaredField("finalWeight");
        finalWeightField.setAccessible(true);
        finalWeightField.set(state, 0.8);
        Field statesField = HMM.class.getDeclaredField("states");
        statesField.setAccessible(true);
        List<HMM.State> stateList = new ArrayList<>();
        stateList.add(state);
        statesField.set(hmm, stateList);
        Field emissionMultinomialField = HMM.class.getDeclaredField("emissionMultinomial");
        emissionMultinomialField.setAccessible(true);
        cc[] emission = new Multinomial[1];
        emission[0] = new Multinomial(new String[]{ "word" }, new double[]{ 1.0 });
        emissionMultinomialField.set(hmm, emission);
        Field transitionMultinomialField = HMM.class.getDeclaredField("transitionMultinomial");
        transitionMultinomialField.setAccessible(true);
        cc[] transition = new Multinomial[1];
        transition[0] = new Multinomial(new String[]{ "S1" }, new double[]{ 1.0 });
        transitionMultinomialField.set(hmm, transition);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    hmm.print();
    System.setOut(originalOut);
    String output = outContent.toString();
    assertTrue(output.contains("STATE NAME=\"S1\""));
    assertTrue(output.contains("0 outgoing transitions"));
    assertTrue(output.contains("initialWeight= 0.5"));
    assertTrue(output.contains("finalWeight= 0.8"));
    assertTrue(output.contains("Emission distribution:"));
    assertTrue(output.contains("Transition distribution:"));
}

@Test
public void test21()
{
    HMM hmm = new HMM();
    File tempFile = File.createTempFile("hmm_test", ".ser");
    tempFile.deleteOnExit();
    hmm.write(tempFile);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
    Object deserialized = ois.readObject();
    ois.close();
    assertNotNull(deserialized);
    assertTrue(deserialized instanceof HMM);
}

