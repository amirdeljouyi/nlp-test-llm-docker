import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MikheevLearner original = new MikheevLearner();
    Learner clone = original.emptyClone();
    assertNotNull("The returned clone should not be null", clone);
    assertNotSame("The clone should be a new instance", original, clone);
}

@Test
public void test2()
{
    MikheevLearner learner = new MikheevLearner();
    HashMap<String, TreeMap<String, Integer>> suffixMap = new HashMap<>();
    TreeMap<String, Integer> tagMap = new TreeMap<>();
    tagMap.put("NNP", 1);
    suffixMap.put("ious", tagMap);
    learner.firstCapitalized = suffixMap;
    Word word = new Word();
    word.form = "Curious";
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
    Word example = new Word();
    example.form = "Testing";
    example.label = "NN";
    example.capitalized = true;
    example.previous = null;
    learner.extractor = new Lexiconer() {
        @Override
        public String discreteValue(Object o) {
            return ((Word) (o)).form;
        }
    };
    learner.labeler = new Lexiconer() {
        @Override
        public String discreteValue(Object o) {
            return ((Word) (o)).label;
        }
    };
    learner.firstCapitalized = new HashMap<>();
    learner.learn(example);
    TreeMap<String, Integer> suffixMap = learner.firstCapitalized.get("ing");
    assertNotNull(suffixMap);
    assertEquals(Integer.valueOf(1), suffixMap.get("NN"));
    TreeMap<String, Integer> suffixMap4 = learner.firstCapitalized.get("ting");
    assertNotNull(suffixMap4);
    assertEquals(Integer.valueOf(1), suffixMap4.get("NN"));
}

@Test
public void test4()
{
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
    DataOutputStream dataOut = new DataOutputStream(byteOutStream);
    dataOut.writeInt(0);
    dataOut.writeInt(1);
    dataOut.writeUTF("NN");
    dataOut.writeInt(3);
    dataOut.writeInt(1);
    dataOut.writeUTF("VB");
    dataOut.writeInt(5);
    dataOut.flush();
    ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteOutStream.toByteArray());
    ExceptionlessInputStream exceptionlessInputStream = new ExceptionlessInputStream(byteInStream);
    MikheevLearner learner = new MikheevLearner();
    learner.read(exceptionlessInputStream);
}

