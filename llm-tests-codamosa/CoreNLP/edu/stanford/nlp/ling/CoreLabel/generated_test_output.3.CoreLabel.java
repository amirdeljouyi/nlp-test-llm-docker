import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel coreLabel = new CoreLabel();
    LabelFactory factoryFromInstance = coreLabel.labelFactory();
    LabelFactory expectedFactory = CoreLabel.factory();
    assertNotNull(factoryFromInstance);
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
    label.set(AfterAnnotation.class, " ");
    String result = label.after();
    assertEquals(" ", result);
}

@Test
public void test4()
{
    CoreLabel coreLabel = new CoreLabel();
    coreLabel.set(BeforeAnnotation.class, " ");
    String result = coreLabel.before();
    assertEquals(" ", result);
}

@Test
public void test5()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford NLP");
    String result = label.getString(TextAnnotation.class);
    assertEquals("Stanford NLP", result);
}

@Test
public void test6()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "StanfordNLP");
    String result = label.getString(TextAnnotation.class);
    assertEquals("StanfordNLP", result);
}

@Test
public void test7()
{
    CoreLabel label = new CoreLabel();
    String expectedOriginalText = "Stanford NLP";
    label.set(OriginalTextAnnotation.class, expectedOriginalText);
    String actualOriginalText = label.originalText();
    assertEquals(expectedOriginalText, actualOriginalText);
}

@Test
public void test8()
{
    CoreLabel label = new CoreLabel();
    label.set(TextAnnotation.class, "Stanford");
    label.set(LemmaAnnotation.class, "stanford");
    label.set(PartOfSpeechAnnotation.class, "NNP");
    String expected = "Stanford/NNP/stanford";
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test9()
{
    CoreLabel label = new CoreLabel();
    label.setWord("hello");
    label.setLemma("hello");
    label.setTag("UH");
    String expected = label.toString(DEFAULT_FORMAT);
    String actual = label.toString();
    assertEquals(expected, actual);
}

@Test
public void test10()
{
    CoreLabel coreLabel = new CoreLabel();
    Map<String, Double> expectedMap = new HashMap<>();
    expectedMap.put("PERSON", 0.9);
    expectedMap.put("LOCATION", 0.05);
    expectedMap.put("ORGANIZATION", 0.05);
    coreLabel.set(NamedEntityTagProbsAnnotation.class, expectedMap);
    Map<String, Double> actualMap = coreLabel.nerConfidence();
    assertNotNull(actualMap);
    assertEquals(3, actualMap.size());
    assertEquals(Double.valueOf(0.9), actualMap.get("PERSON"));
    assertEquals(Double.valueOf(0.05), actualMap.get("LOCATION"));
    assertEquals(Double.valueOf(0.05), actualMap.get("ORGANIZATION"));
}

@Test
public void test11()
{
    String input = "language";
    CoreLabel result = CoreLabel.wordFromString(input);
    assertEquals("language", result.word());
    assertEquals("language", result.originalText());
    assertEquals("language", result.value());
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
    String[] inputKeys = new String[]{ "TextAnnotation", "LemmaAnnotation" };
    Class<?>[] result = CoreLabel.parseStringKeys(inputKeys);
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals("edu.stanford.nlp.ling.CoreAnnotations$TextAnnotation", result[0].getName());
    assertEquals("edu.stanford.nlp.ling.CoreAnnotations$LemmaAnnotation", result[1].getName());
}

