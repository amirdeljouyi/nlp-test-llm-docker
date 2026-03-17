import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MikheevLearner originalLearner = new MikheevLearner();
    Learner clonedLearner = originalLearner.emptyClone();
    assertNotNull("Cloned learner should not be null", clonedLearner);
    assertTrue("Cloned learner should be a new instance of MikheevLearner", clonedLearner instanceof MikheevLearner);
    assertNotSame("Cloned learner should not be the same instance as original", originalLearner, clonedLearner);
}

@Test
public void test2()
{
    MikheevLearner learner = new MikheevLearner();
    String suffix = "Corp".toLowerCase();
    TreeMap<String, Integer> tagMap = new TreeMap<>();
    tagMap.put("NNP", 1);
    HashMap<String, TreeMap<String, Integer>> mockSuffixMap = new HashMap<>();
    mockSuffixMap.put(suffix, tagMap);
    learner.firstCapitalized = mockSuffixMap;
    Word word = new Word("TechCorp");
    word.capitalized = true;
    word.previous = null;
    Set<String> result = learner.allowableTags(word);
    assertNotNull(result);
    assertTrue(result.contains("NNP"));
    assertEquals(1, result.size());
}

@Test
public void test3()
{
    MikheevLearner learner = new MikheevLearner();
    learner.extractor = new Classifier() {
        @Override
        public String discreteValue(Object o) {
            return "Running";
        }
    };
    learner.labeler = new Classifier() {
        @Override
        public String discreteValue(Object o) {
            return "VBG";
        }
    };
    Word word = new Word("Running");
    word.capitalized = true;
    word.previous = null;
    learner.firstCapitalized = new HashMap<>();
    learner.learn(word);
    TreeMap<String, Integer> suffixMap = learner.firstCapitalized.get("running".substring("running".length() - 3));
    assertNotNull(suffixMap);
    assertEquals(Integer.valueOf(1), suffixMap.get("VBG"));
    TreeMap<String, Integer> suffixMap4 = learner.firstCapitalized.get("running".substring("running".length() - 4));
    assertNotNull(suffixMap4);
    assertEquals(Integer.valueOf(1), suffixMap4.get("VBG"));
}

@Test
public void test4()
{
    MikheevLearner learner = new MikheevLearner();
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOut = new ObjectOutputStream(byteOutStream);
    objectOut.writeObject("dummySuperData");
    objectOut.writeObject(new HashMap<String, String>());
    objectOut.writeObject(new HashMap<String, String>());
    objectOut.flush();
    ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteOutStream.toByteArray());
    ExceptionlessInputStream in = new ExceptionlessInputStream(byteInStream);
    learner.read(in);
    assertNotNull(learner);
}

