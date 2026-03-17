import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel coreLabel = new CoreLabel();
    LabelFactory factory = coreLabel.labelFactory();
    assertNotNull("LabelFactory should not be null", factory);
    assertTrue("LabelFactory should be instance of CoreLabelFactory", factory instanceof CoreLabelFactory);
}

@Test
public void test2()
{
    CoreLabel label = new CoreLabel();
    label.set(IndexAnnotation.class, 5);
    int result = label.index();
    assertEquals(5, result);
}

@Test
public void test3()
{
    CoreLabel label = new CoreLabel();
    label.set(AfterAnnotation.class, " ");
    String result = label.after();
    assertEquals(" ", result);
}

@Test
public void test4()
{
    CoreLabel label = new CoreLabel();
    label.set(BeforeAnnotation.class, " ");
    String result = label.before();
    assertEquals(" ", result);
}

@Test
public void test5()
{
    CoreLabel token = new CoreLabel();
    token.set(DocIDAnnotation.class, "doc123");
    String result = token.docID();
    assertEquals("doc123", result);
}

@Test
public void test6()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford");
    String result = label.getString(TextAnnotation.class);
    assertEquals("Stanford", result);
}

@Test
public void test7()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "exampleText");
    String result = label.getString(TextAnnotation.class);
    assertEquals("exampleText", result);
}

@Test
public void test8()
{
    CoreLabel label = new CoreLabel();
    label.set(OriginalTextAnnotation.class, "The quick brown fox");
    String result = label.originalText();
    assertEquals("The quick brown fox", result);
}

@Test
public void test9()
{
    CoreLabel label = new CoreLabel();
    label.setWord("Stanford");
    label.setTag("NNP");
    String expected = label.toString(DEFAULT_FORMAT);
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test10()
{
    CoreLabel label = new CoreLabel();
    label.setWord("Stanford");
    label.setTag("NNP");
    label.setLemma("stanford");
    String expected = label.toString(DEFAULT_FORMAT);
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test11()
{
    CoreLabel coreLabel = new CoreLabel();
    Map<String, Double> expectedConfidenceMap = new HashMap<>();
    expectedConfidenceMap.put("PERSON", 0.85);
    expectedConfidenceMap.put("LOCATION", 0.1);
    expectedConfidenceMap.put("ORGANIZATION", 0.05);
    coreLabel.set(NamedEntityTagProbsAnnotation.class, expectedConfidenceMap);
    Map<String, Double> actualConfidenceMap = coreLabel.nerConfidence();
    assertNotNull(actualConfidenceMap);
    assertEquals(3, actualConfidenceMap.size());
    assertEquals(Double.valueOf(0.85), actualConfidenceMap.get("PERSON"));
    assertEquals(Double.valueOf(0.1), actualConfidenceMap.get("LOCATION"));
    assertEquals(Double.valueOf(0.05), actualConfidenceMap.get("ORGANIZATION"));
}

@Test
public void test12()
{
    String input = "Stanford";
    CoreLabel coreLabel = CoreLabel.wordFromString(input);
    assertEquals("Stanford", coreLabel.word());
    assertEquals("Stanford", coreLabel.originalText());
    assertEquals("Stanford", coreLabel.value());
}

@Test
public void test13()
{
    LabelFactory factory = CoreLabel.factory();
    assertNotNull(factory);
    Label label = factory.newLabel("test");
    assertNotNull(label);
    assertTrue(label instanceof CoreLabel);
    assertEquals("test", label.value());
}

@Test
public void test14()
{
    String[] keys = new String[]{ "TextAnnotation" };
    Class[] result = CoreLabel.parseStringKeys(keys);
    assertNotNull(result);
    assertEquals(1, result.length);
    assertEquals(TextAnnotation.class, result[0]);
}

