import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new TokenSequenceTokenizer(Pattern.compile("\\S+")));
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequenceRemoveStopwords(false, false));
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingList = new InstanceList(pipe);
    StringBuilder trainingData = new StringBuilder();
    trainingData.append("positive\tthis is a good product\n");
    trainingData.append("negative\tthis is a bad product\n");
    trainingList.addThruPipe(new CsvIterator(new StringReader(trainingData.toString()), Pattern.compile("(\\w+)\\s+(.*)"), 1, 0, -1));
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    NaiveBayes classifier = trainer.train(trainingList);
    Assert.assertNotNull("The returned classifier should not be null", classifier);
    Assert.assertEquals("Trainer should be reset after training", null, trainer.me);
}

@Test
public void test2()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new CharSequence2TokenSequence());
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequenceRemoveStopwords(false));
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipeList);
    pipe.setTargetProcessing(true);
    InstanceList trainingData = new InstanceList(pipe);
    String[] data = new String[]{ "This is a positive example." };
    String[] target = new String[]{ "positive" };
    trainingData.addThruPipe(new StringArrayIterator(data), target);
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    NaiveBayes classifier = trainer.trainIncremental(trainingData);
    Assert.assertNotNull("The classifier returned by trainIncremental should not be null.", classifier);
}

@Test
public void test3()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new CharSequence2TokenSequence("\\p{L}+"));
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    trainingData.addThruPipe(new Instance("This is a test", "positive", "test1", null));
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    NaiveBayes classifier = trainer.trainIncremental(trainingData);
    assertNotNull("Classifier should not be null", classifier);
    assertEquals("Alphabet size should match", pipe.getDataAlphabet().size(), classifier.getAlphabet().size());
    assertEquals("Label alphabet size should match", pipe.getTargetAlphabet().size(), classifier.getLabelAlphabet().size());
}

@Test
public void test4()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new CharSequence2TokenSequence("\\p{L}+"));
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequenceRemoveStopwords());
    pipeList.add(new TokenSequence2FeatureSequence());
    SerialPipes pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    trainingData.addThruPipe(new Instance("dog barks", "animal", null, null));
    trainingData.addThruPipe(new Instance("cat meows", "animal", null, null));
    trainingData.addThruPipe(new Instance("car drives", "vehicle", null, null));
    trainingData.addThruPipe(new Instance("bike pedals", "vehicle", null, null));
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    NaiveBayes classifier = trainer.train(trainingData);
    assertNotNull("Classifier should not be null", classifier);
    assertEquals("Number of labels should be 2", 2, classifier.getLabelAlphabet().size());
    Labeling labeling = classifier.classify(trainingData.get(0).getData()).getLabeling();
    assertTrue("Labeling probability must not be zero", labeling.getBestValue() > 0.0);
}

@Test
public void test5()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new Input2CharSequence("UTF-8"));
    pipeList.add(new CharSequenceLowercase());
    pipeList.add(new CharSequence2TokenSequence(Pattern.compile("\\p{L}+")));
    pipeList.add(new TokenSequence2FeatureSequence());
    Pipe instancePipe = new SerialPipes(pipeList);
    InstanceList trainingInstances = new InstanceList(instancePipe);
    String sampleData = "positive\tThis is a great movie.\n";
    trainingInstances.addThruPipe(new CsvIterator(new StringReader(sampleData), Pattern.compile("(\\w+)\\s+(.*)"), 1, 2, 0));
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    NaiveBayes classifier = trainer.trainIncremental(trainingInstances);
    assertNotNull(classifier);
    assertEquals(1, classifier.getInstancePipe().getDataAlphabet().size());
    assertTrue(classifier.getLabelAlphabet().lookupLabel("positive") != null);
}

@Test
public void test6()
{
    ArrayList<Pipe> pipeList = new ArrayList<>();
    pipeList.add(new CharSequence2TokenSequence());
    pipeList.add(new TokenSequence2FeatureSequence());
    pipeList.add(new Target2Label());
    Pipe pipe = new SerialPipes(pipeList);
    InstanceList trainingData = new InstanceList(pipe);
    String[] data = new String[]{ "dog barks", "cat meows" };
    String[] labels = new String[]{ "animal", "animal" };
    trainingData.addThruPipe(new Instance(data[0], labels[0], null, null));
    trainingData.addThruPipe(new Instance(data[1], labels[1], null, null));
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    NaiveBayes classifier = trainer.trainIncremental(trainingData);
    assertNotNull(classifier);
}

@Test
public void test7()
{
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    double normalizationValue = 0.75;
    NaiveBayesTrainer returnedTrainer = trainer.setDocLengthNormalization(normalizationValue);
    Assert.assertSame(trainer, returnedTrainer);
    Assert.assertEquals(normalizationValue, getPrivateDocLengthNormalization(trainer), 1.0E-5);
}

@Test
public void test8()
{
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    Multinomial.Estimator estimator = new Multinomial.LaplaceEstimator();
    NaiveBayesTrainer returnedTrainer = trainer.setFeatureMultinomialEstimator(estimator);
    assertSame("Expected the same trainer instance to be returned", trainer, returnedTrainer);
}

@Test
public void test9()
{
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    Multinomial.Estimator estimator = new Multinomial.LaplaceEstimator();
    NaiveBayesTrainer returnedTrainer = trainer.setPriorMultinomialEstimator(estimator);
    assertSame("Returned trainer should be the same instance", trainer, returnedTrainer);
}

@Test
public void test10()
{
    Alphabet expectedAlphabet = new Alphabet();
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    try {
        Field field = NaiveBayesTrainer.class.getDeclaredField("dataAlphabet");
        field.setAccessible(true);
        field.set(trainer, expectedAlphabet);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set dataAlphabet via reflection: " + e.getMessage());
    }
    Alphabet actualAlphabet = trainer.getAlphabet();
    assertSame("getAlphabet should return the same Alphabet instance", expectedAlphabet, actualAlphabet);
}

@Test
public void test11()
{
    Alphabet dataAlphabet = new Alphabet();
    Alphabet targetAlphabet = new Alphabet();
    dataAlphabet.lookupIndex("feature1");
    targetAlphabet.lookupIndex("classLabel");
    NaiveBayesTrainer trainer = new NaiveBayesTrainer(dataAlphabet, targetAlphabet);
    Alphabet[] alphabets = trainer.getAlphabets();
    assertNotNull("Returned alphabet array should not be null", alphabets);
    assertEquals("Alphabet array should have length 2", 2, alphabets.length);
    assertSame("First alphabet should be dataAlphabet", dataAlphabet, alphabets[0]);
    assertSame("Second alphabet should be targetAlphabet", targetAlphabet, alphabets[1]);
}

@Test
public void test12()
{
    NaiveBayesTrainer trainer = new NaiveBayesTrainer();
    String result = trainer.toString();
    assertEquals("NaiveBayesTrainer", result);
}

