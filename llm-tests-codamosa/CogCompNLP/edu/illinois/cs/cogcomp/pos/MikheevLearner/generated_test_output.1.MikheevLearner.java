import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MikheevLearner original = new MikheevLearner();
    Learner clone = original.emptyClone();
    assertNotNull("Cloned learner should not be null", clone);
    assertNotSame("Cloned learner should be a new instance", original, clone);
}

@Test
public void test2()
{
    MikheevLearner learner = new MikheevLearner();
    Word word = new Word("Example");
    word.capitalized = true;
    word.previous = null;
    learner.firstCapitalized = new HashMap<>();
    learner.notFirstCapitalized = new HashMap<>();
    Set<String> tags = learner.allowableTags(word);
    assertNotNull(tags);
    assertEquals(1, tags.size());
    assertTrue(tags.contains("NNP"));
}

@Test
public void test3()
{
    MikheevLearner learner = new MikheevLearner();
    learner.extractor = new Classifier() {
        @Override
        public String discreteValue(Object example) {
            return "Runs";
        }
    };
    learner.labeler = new Classifier() {
        @Override
        public String discreteValue(Object example) {
            return "VBZ";
        }
    };
    learner.firstCapitalized = new HashMap<>();
    learner.notFirstCapitalized = new HashMap<>();
    learner.table = new HashMap<>();
    Word word = new Word("Runs");
    word.capitalized = true;
    word.previous = null;
    learner.learn(word);
    TreeMap<String, Integer> suffixMap = learner.firstCapitalized.get("uns");
    assertNotNull("Suffix map for 'uns' should not be null", suffixMap);
    Integer count = suffixMap.get("VBZ");
    assertNotNull("Label 'VBZ' count should not be null", count);
    assertEquals("Expected count for label 'VBZ'", Integer.valueOf(1), count);
}

@Test
public void test4()
{
    byte[] dummyData = new byte[]{ 0, 1, 2, 3 };
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dummyData);
    ExceptionlessInputStream exceptionlessInputStream = new ExceptionlessInputStream(byteArrayInputStream);
    MikheevLearner mikheevLearner = new MikheevLearner();
    mikheevLearner.read(exceptionlessInputStream);
}

