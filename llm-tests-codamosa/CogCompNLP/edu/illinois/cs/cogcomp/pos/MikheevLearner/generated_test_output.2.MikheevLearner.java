import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MikheevLearner originalLearner = new MikheevLearner();
    Learner clonedLearner = originalLearner.emptyClone();
    assertNotNull("Cloned learner should not be null", clonedLearner);
    assertTrue("Cloned learner should be instance of MikheevLearner", clonedLearner instanceof MikheevLearner);
    assertNotSame("Cloned learner should be a new instance", originalLearner, clonedLearner);
}

@Test
public void test2()
{
    MikheevLearner learner = new MikheevLearner();
    String suffix = "tion";
    TreeMap<String, Integer> tagMap = new TreeMap<>();
    tagMap.put("NN", 1);
    tagMap.put("VB", 2);
    learner.firstCapitalized = new HashMap<>();
    learner.firstCapitalized.put(suffix, tagMap);
    Word word = new Word("Creation");
    word.capitalized = true;
    word.previous = null;
    Set<String> result = learner.allowableTags(word);
    assertEquals(2, result.size());
    assertTrue(result.contains("NN"));
    assertTrue(result.contains("VB"));
}

@Test
public void test3()
{
    MikheevLearner learner = new MikheevLearner();
    Word mockWord = new Word();
    mockWord.form = "Testing";
    mockWord.capitalized = true;
    mockWord.previous = null;
    learner.extractor = new FeatureExtractor() {
        public Feature featureValue(Object o) {
            return new Feature("form", "Testing");
        }

        public String discreteValue(Object o) {
            return "Testing";
        }
    };
    learner.labeler = new FeatureExtractor() {
        public Feature featureValue(Object o) {
            return new Feature("label", "NN");
        }

        public String discreteValue(Object o) {
            return "NN";
        }
    };
    learner.firstCapitalized = new HashMap<>();
    learner.firstCapitalized.put("ing", new TreeMap<>());
    learner.firstCapitalized.put("ting", new TreeMap<>());
    learner.learn(mockWord);
    assertTrue(learner.firstCapitalized.containsKey("ing"));
    assertEquals(Integer.valueOf(1), learner.firstCapitalized.get("ing").get("NN"));
    assertTrue(learner.firstCapitalized.containsKey("ting"));
    assertEquals(Integer.valueOf(1), learner.firstCapitalized.get("ting").get("NN"));
}

@Test
public void test4()
{
    MikheevLearner learner = new MikheevLearner();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    objectOutputStream.writeObject("dummy_super_read_data");
    objectOutputStream.writeObject("dummy_firstCapitalized_data");
    objectOutputStream.writeObject("dummy_notFirstCapitalized_data");
    objectOutputStream.flush();
    byte[] data = byteArrayOutputStream.toByteArray();
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
    ExceptionlessInputStream exceptionlessInputStream = new ExceptionlessInputStream(byteArrayInputStream);
    learner.read(exceptionlessInputStream);
}


