import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MikheevLearner originalLearner = new MikheevLearner();
    Learner clonedLearner = originalLearner.emptyClone();
    assertNotNull("The cloned learner should not be null.", clonedLearner);
    assertTrue("The cloned learner should be instance of MikheevLearner.", clonedLearner instanceof MikheevLearner);
    assertNotSame("The cloned learner should be a new instance, not the same as the original.", originalLearner, clonedLearner);
}

@Test
public void test2()
{
    MikheevLearner learner = new MikheevLearner();
    HashMap<String, TreeMap<String, Integer>> mockMap = new HashMap<>();
    TreeMap<String, Integer> suffixTags = new TreeMap<>();
    suffixTags.put("NNP", 1);
    suffixTags.put("NN", 1);
    mockMap.put("tion", suffixTags);
    learner.firstCapitalized = mockMap;
    Word word = new Word("Action");
    word.capitalized = true;
    word.previous = null;
    Set<String> result = learner.allowableTags(word);
    assertNotNull(result);
    assertTrue(result.contains("NNP"));
    assertTrue(result.contains("NN"));
    assertEquals(2, result.size());
}

@Test
public void test3()
{
    MikheevLearner learner = new MikheevLearner();
    learner.extractor = new FeatureExtractor() {
        @Override
        public String discreteValue(Object example) {
            return "Running";
        }
    };
    learner.labeler = new FeatureExtractor() {
        @Override
        public String discreteValue(Object example) {
            return "VBG";
        }
    };
    learner.firstCapitalized = new HashMap<>();
    learner.firstCapitalized.put("ing", new TreeMap<>());
    learner.firstCapitalized.get("ing").put("VBG", 0);
    Word word = new Word("Running");
    word.capitalized = true;
    word.previous = null;
    learner.learn(word);
    assertEquals(Integer.valueOf(1), learner.firstCapitalized.get("ing").get("VBG"));
}

@Test
public void test4()
{
    MikheevLearner learner = new MikheevLearner();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject("dummy-super-read-data");
    oos.writeObject("dummy-first-capitalized");
    oos.writeObject("dummy-not-first-capitalized");
    oos.flush();
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    ExceptionlessInputStream in = new ExceptionlessInputStream(bais);
    learner.read(in);
    assertTrue(true);
}

