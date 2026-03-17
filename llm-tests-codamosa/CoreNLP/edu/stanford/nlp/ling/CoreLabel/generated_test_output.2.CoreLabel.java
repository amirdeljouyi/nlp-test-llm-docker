import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel coreLabel = new CoreLabel();
    LabelFactory factoryFromInstance = coreLabel.labelFactory();
    LabelFactory expectedFactory = CoreLabel.factory();
    assertNotNull("The returned LabelFactory should not be null", factoryFromInstance);
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
    Assert.assertEquals(" ", result);
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
    CoreLabel label = new CoreLabel();
    String expectedDocID = "doc-123";
    label.set(DocIDAnnotation.class, expectedDocID);
    String actualDocID = label.docID();
    assertEquals(expectedDocID, actualDocID);
}

@Test
public void test6()
{
    CoreLabel coreLabel = new CoreLabel();
    coreLabel.set(TextAnnotation.class, "StanfordNLP");
    String result = coreLabel.getString(TextAnnotation.class);
    assertEquals("StanfordNLP", result);
}

@Test
public void test7()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Hello");
    String result = label.getString(TextAnnotation.class);
    assertEquals("Hello", result);
}

@Test
public void test8()
{
    CoreLabel coreLabel = new CoreLabel();
    coreLabel.set(OriginalTextAnnotation.class, "Stanford NLP");
    String result = coreLabel.originalText();
    assertEquals("Stanford NLP", result);
}

@Test
public void test9()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford");
    label.set(PartOfSpeechAnnotation.class, "NNP");
    String expected = "Text=Stanford PartOfSpeech=NNP";
    String actual = label.toString();
    assertTrue(actual.contains("Text=Stanford"));
    assertTrue(actual.contains("PartOfSpeech=NNP"));
}

@Test
public void test10()
{
    CoreLabel label = new CoreLabel();
    label.setWord("Stanford");
    label.setTag("NNP");
    String expected = label.toString(DEFAULT_FORMAT);
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test11()
{
    CoreLabel label = new CoreLabel();
    Map<String, Double> expectedConfidences = new HashMap<>();
    expectedConfidences.put("PERSON", 0.85);
    expectedConfidences.put("LOCATION", 0.1);
    expectedConfidences.put("ORGANIZATION", 0.05);
    label.set(NamedEntityTagProbsAnnotation.class, expectedConfidences);
    Map<String, Double> actualConfidences = label.nerConfidence();
    assertNotNull(actualConfidences);
    assertEquals(3, actualConfidences.size());
    assertEquals(Double.valueOf(0.85), actualConfidences.get("PERSON"));
    assertEquals(Double.valueOf(0.1), actualConfidences.get("LOCATION"));
    assertEquals(Double.valueOf(0.05), actualConfidences.get("ORGANIZATION"));
}

@Test
public void test12()
{
    String input = "example";
    CoreLabel label = CoreLabel.wordFromString(input);
    assertEquals("Word annotation should match input", input, label.word());
    assertEquals("OriginalText annotation should match input", input, label.originalText());
    assertEquals("Value annotation should match input", input, label.value());
}

@Test
public void test13()
{
    LabelFactory factory = CoreLabel.factory();
    assertNotNull("Factory should not be null", factory);
    Label label = factory.newLabel("test");
    assertNotNull("Label created by factory should not be null", label);
    assertTrue("Label created by factory should be instance of CoreLabel", label instanceof CoreLabel);
    assertEquals("Label value should match input", "test", label.value());
}

@Test
public void test14()
{
    String[] input = new String[]{ "TextAnnotation" };
    Class<?>[] result = CoreLabel.parseStringKeys(input);
    assertNotNull(result);
    assertEquals(1, result.length);
    assertEquals(TextAnnotation.class, result[0]);
}

