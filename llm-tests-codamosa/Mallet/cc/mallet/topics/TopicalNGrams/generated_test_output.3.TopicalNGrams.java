import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    File tempFile = File.createTempFile("testInstances", ".mallet");
    tempFile.deleteOnExit();
    ArrayList<Pipe> pipes = new ArrayList<Pipe>();
    pipes.add(new CharSequence2TokenSequence());
    Pipe pipe = new SerialPipes(pipes);
    InstanceList instanceList = new InstanceList(pipe);
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile));
    oos.writeObject(instanceList);
    oos.close();
    String[] args = new String[]{ tempFile.getAbsolutePath() };
    TopicalNGrams.main(args);
}

@Test
public void test2()
{
    Alphabet uniAlphabet = new Alphabet();
    Alphabet biAlphabet = new Alphabet();
    int indexA = uniAlphabet.lookupIndex("a", true);
    int indexB = uniAlphabet.lookupIndex("b", true);
    FeatureSequenceWithBigrams fs = new FeatureSequenceWithBigrams(uniAlphabet, biAlphabet, 2);
    fs.add("a");
    fs.add("b");
    biAlphabet.lookupIndex("a_b", true);
    Instance instance = new Instance(fs, null, "doc1", null);
    InstanceList instanceList = new InstanceList(uniAlphabet);
    instanceList.add(instance);
    TopicalNGrams model = new TopicalNGrams();
    model.setNumTopics(2);
    Randoms randoms = new Randoms(123);
    model.estimate(instanceList, 5, 2, 3, "topicalModel", randoms);
}

@Test
public void test3()
{
    TopicalNGrams topicalNGrams = new TopicalNGrams();
    File tempFile = File.createTempFile("test_doc_topics", ".txt");
    tempFile.deleteOnExit();
    topicalNGrams.printDocumentTopics(tempFile);
    BufferedReader reader = new BufferedReader(new FileReader(tempFile));
    StringBuilder contentBuilder = new StringBuilder();
    String line = reader.readLine();
    if (line != null) {
        contentBuilder.append(line);
    }
    reader.close();
    assertTrue("Expected non-empty output", (contentBuilder.length() > 0) || (tempFile.length() > 0));
}

@Test
public void test4()
{
    TopicalNGrams model = new TopicalNGrams();
    model.numTopics = 1;
    model.numTypes = 2;
    model.numBitypes = 1;
    model.uniAlphabet = new Alphabet();
    model.uniAlphabet.lookupIndex("apple");
    model.uniAlphabet.lookupIndex("banana");
    model.biAlphabet = new Alphabet();
    model.biAlphabet.lookupIndex("apple_banana");
    model.tokensPerTopic = new int[]{ 3 };
    model.unitypeTopicCounts = new int[][]{ new int[]{ 2 }, new int[]{ 1 } };
    model.bitypeTopicCounts = new int[][]{ new int[]{ 1 } };
    model.topics = new int[][]{ new int[]{ 0, 0 } };
    model.grams = new int[][]{ new int[]{ 1, 0 } };
    InstanceList ilist = new InstanceList(model.uniAlphabet);
    FeatureSequenceWithBigrams fs = new FeatureSequenceWithBigrams(model.uniAlphabet, new int[]{ 0, 1 });
    ilist.add(new Instance(fs, null, "test", null));
    model.ilist = ilist;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    model.printTopWords(2, true);
    System.setOut(System.out);
    String output = outContent.toString();
    assertTrue(output.contains("Topic 0 unigrams"));
    assertTrue(output.contains("apple"));
}

@Test
public void test5()
{
    TopicalNGrams original = new TopicalNGrams();
    File tempFile = File.createTempFile("topicalNGramsTest", ".ser");
    tempFile.deleteOnExit();
    original.write(tempFile);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
    Object deserialized = ois.readObject();
    ois.close();
    assertNotNull(deserialized);
    assertTrue(deserialized instanceof TopicalNGrams);
}

