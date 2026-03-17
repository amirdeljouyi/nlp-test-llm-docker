import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel coreLabel = new CoreLabel();
    LabelFactory factoryFromInstance = coreLabel.labelFactory();
    LabelFactory expectedFactory = CoreLabel.factory();
    assertNotNull("LabelFactory from instance should not be null", factoryFromInstance);
    assertNotNull("Expected CoreLabel factory should not be null", expectedFactory);
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
    label.set(IsFirstWordOfMWTAnnotation.class, Boolean.TRUE);
    Boolean result = label.isMWTFirst();
    assertNotNull("Expected non-null result when annotation is set", result);
    assertTrue("Expected isMWTFirst to return true when annotation is set to true", result);
}

@Test
public void test4()
{
    CoreLabel label = new CoreLabel();
    label.set(IsNewlineAnnotation.class, true);
    Boolean result = label.isNewline();
    assertNotNull(result);
    assertTrue(result);
}

@Test
public void test5()
{
    CoreLabel label = new CoreLabel();
    label.set(AfterAnnotation.class, " ");
    String result = label.after();
    assertEquals(" ", result);
}

@Test
public void test6()
{
    CoreLabel label = new CoreLabel();
    String expectedBefore = " ";
    label.set(BeforeAnnotation.class, expectedBefore);
    String actualBefore = label.before();
    assertEquals(expectedBefore, actualBefore);
}

@Test
public void test7()
{
    CoreLabel coreLabel = new CoreLabel();
    String expectedDocID = "doc123";
    coreLabel.set(DocIDAnnotation.class, expectedDocID);
    String actualDocID = coreLabel.docID();
    Assert.assertEquals(expectedDocID, actualDocID);
}

@Test
public void test8()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford");
    String result = label.getString(TextAnnotation.class);
    assertEquals("Stanford", result);
}

@Test
public void test9()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "NLP");
    String result = label.getString(TextAnnotation.class);
    assertEquals("NLP", result);
}

@Test
public void test10()
{
    CoreLabel label = new CoreLabel();
    String expectedOriginalText = "Natural Language Processing";
    label.set(OriginalTextAnnotation.class, expectedOriginalText);
    String actualOriginalText = label.originalText();
    assertEquals(expectedOriginalText, actualOriginalText);
}

@Test
public void test11()
{
    CoreLabel label = new CoreLabel();
    label.setWord("hello");
    label.setTag("NN");
    label.setLemma("hello");
    String expected = "hello-NN";
    assertTrue(label.toString().contains("hello"));
    assertTrue(label.toString().contains("NN"));
}

@Test
public void test12()
{
    CoreLabel label = new CoreLabel();
    label.setWord("example");
    label.setTag("NN");
    label.setLemma("example");
    String expected = label.toString(DEFAULT_FORMAT);
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test13()
{
    CoreLabel coreLabel = new CoreLabel();
    Map<String, Double> expectedConfidences = new HashMap<>();
    expectedConfidences.put("PERSON", 0.85);
    expectedConfidences.put("LOCATION", 0.1);
    expectedConfidences.put("ORGANIZATION", 0.05);
    coreLabel.set(NamedEntityTagProbsAnnotation.class, expectedConfidences);
    Map<String, Double> actualConfidences = coreLabel.nerConfidence();
    assertNotNull(actualConfidences);
    assertEquals(3, actualConfidences.size());
    assertEquals(Double.valueOf(0.85), actualConfidences.get("PERSON"));
    assertEquals(Double.valueOf(0.1), actualConfidences.get("LOCATION"));
    assertEquals(Double.valueOf(0.05), actualConfidences.get("ORGANIZATION"));
}

@Test
public void test14()
{
    String input = "hello";
    CoreLabel label = CoreLabel.wordFromString(input);
    assertEquals("hello", label.word());
    assertEquals("hello", label.originalText());
    assertEquals("hello", label.value());
}

@Test
public void test15()
{
    LabelFactory factory = CoreLabel.factory();
    assertNotNull("Factory should not be null", factory);
    Label label = factory.newLabel("test");
    assertNotNull("Label created by factory should not be null", label);
    assertTrue("Label should be instance of CoreLabel", label instanceof CoreLabel);
    assertEquals("Label value should be 'test'", "test", label.value());
}

@Test
public void test16()
{
    String[] keys = new String[]{ "TextAnnotation" };
    Class<?>[] result = CoreLabel.parseStringKeys(keys);
    assertNotNull(result);
    assertEquals(1, result.length);
    assertEquals(TextAnnotation.class, result[0]);
}

