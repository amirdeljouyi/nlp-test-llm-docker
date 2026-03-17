import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel coreLabel = new CoreLabel();
    LabelFactory expectedFactory = CoreLabel.factory();
    LabelFactory actualFactory = coreLabel.labelFactory();
    assertSame("labelFactory should return the same instance as CoreLabel.factory()", expectedFactory, actualFactory);
}

@Test
public void test2()
{
    CoreLabel label = new CoreLabel();
    label.set(IndexAnnotation.class, 5);
    int result = label.index();
    Assert.assertEquals(5, result);
}

@Test
public void test3()
{
    CoreLabel label = new CoreLabel();
    String expectedAfter = " ";
    label.set(AfterAnnotation.class, expectedAfter);
    String actualAfter = label.after();
    assertEquals(expectedAfter, actualAfter);
}

@Test
public void test4()
{
    CoreLabel coreLabel = new CoreLabel();
    coreLabel.set(BeforeAnnotation.class, "  ");
    String beforeValue = coreLabel.before();
    assertEquals("  ", beforeValue);
}

@Test
public void test5()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford");
    String result = label.getString(TextAnnotation.class);
    assertEquals("Stanford", result);
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
    String expectedText = "Stanford NLP";
    label.set(OriginalTextAnnotation.class, expectedText);
    String actualText = label.originalText();
    assertEquals(expectedText, actualText);
}

@Test
public void test8()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford");
    label.set(PartOfSpeechAnnotation.class, "NNP");
    String expected = "Stanford/NNP";
    assertEquals(expected, label.toString());
}

@Test
public void test9()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "word");
    label.set(LemmaAnnotation.class, "lemma");
    label.set(PosAnnotation.class, "NN");
    String expected = label.toString(DEFAULT_FORMAT);
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test10()
{
    CoreLabel label = new CoreLabel();
    Map<String, Double> expectedProbs = new HashMap<>();
    expectedProbs.put("PERSON", 0.85);
    expectedProbs.put("LOCATION", 0.1);
    expectedProbs.put("ORGANIZATION", 0.05);
    label.set(NamedEntityTagProbsAnnotation.class, expectedProbs);
    Map<String, Double> actualProbs = label.nerConfidence();
    assertNotNull(actualProbs);
    assertEquals(3, actualProbs.size());
    assertEquals(Double.valueOf(0.85), actualProbs.get("PERSON"));
    assertEquals(Double.valueOf(0.1), actualProbs.get("LOCATION"));
    assertEquals(Double.valueOf(0.05), actualProbs.get("ORGANIZATION"));
}

@Test
public void test11()
{
    String input = "hello";
    CoreLabel coreLabel = CoreLabel.wordFromString(input);
    assertEquals("hello", coreLabel.word());
    assertEquals("hello", coreLabel.originalText());
    assertEquals("hello", coreLabel.value());
}

@Test
public void test12()
{
    LabelFactory factory = CoreLabel.factory();
    assertNotNull("Factory should not be null", factory);
    assertTrue("Factory should be instance of CoreLabelFactory", factory instanceof CoreLabelFactory);
}

@Test
public void test13()
{
    String[] inputKeys = new String[]{ "TextAnnotation" };
    Class[] result = CoreLabel.parseStringKeys(inputKeys);
    assertNotNull(result);
    assertEquals(1, result.length);
    assertNotNull(result[0]);
    assertEquals("edu.stanford.nlp.ling.CoreAnnotations$TextAnnotation", result[0].getName());
}

