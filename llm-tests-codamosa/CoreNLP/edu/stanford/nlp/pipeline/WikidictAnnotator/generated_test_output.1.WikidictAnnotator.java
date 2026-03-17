import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Properties props = new Properties();
    props.setProperty("threads", "4");
    WikidictAnnotator annotator = new WikidictAnnotator(props);
    int expectedThreads = 4;
    int actualThreads = annotator.nThreads();
    assertEquals(expectedThreads, actualThreads);
}

@Test
public void test2()
{
    WikidictAnnotator annotator = new WikidictAnnotator();
    Annotation annotation = new Annotation("Sample text.");
    CoreMap sentence = new CoreMap() {};
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    List<CoreMap> before = annotation.get(SentencesAnnotation.class);
    annotator.doOneFailedSentence(annotation, sentence);
    List<CoreMap> after = annotation.get(SentencesAnnotation.class);
    assertSame("Sentences list should be unchanged", before, after);
    assertEquals("Sentences list size should remain the same", 1, after.size());
    assertSame("Sentence object should be unchanged", sentence, after.get(0));
}

@Test
public void test3()
{
    CoreMap mention = mock(CoreMap.class);
    when(mention.get(NamedEntityTagAnnotation.class)).thenReturn("DATE");
    when(mention.get(OriginalTextAnnotation.class)).thenReturn(null);
    when(mention.get(TextAnnotation.class)).thenReturn("January 1, 2022");
    Timex timex = mock(Timex.class);
    when(timex.value()).thenReturn("2022-01-01");
    when(mention.get(TimexAnnotation.class)).thenReturn(timex);
    WikidictAnnotator annotator = new WikidictAnnotator();
    annotator.dictionary = new HashMap<>();
    annotator.wikidictCaseless = false;
    Optional<String> result = annotator.link(mention);
    assertTrue(result.isPresent());
    assertEquals("2022-01-01", result.get());
}

@Test
public void test4()
{
    WikidictAnnotator annotator = new WikidictAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(WikipediaEntityAnnotation.class));
}

@Test
public void test5()
{
    String input = "2023-05-15T14:30";
    String expected = "2023-05-15";
    String actual = WikidictAnnotator.normalizeTimex(input);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    String input = "Barack Obama was the 44th President of the United States.\n";
    InputStream originalIn = System.in;
    PrintStream originalErr = System.err;
    ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes("UTF-8"));
    ByteArrayOutputStream testErr = new ByteArrayOutputStream();
    System.setIn(testIn);
    System.setErr(new PrintStream(testErr, true, "UTF-8"));
    String[] args = new String[0];
    try {
        WikidictAnnotator.main(args);
    } catch (IOException e) {
    } finally {
        System.setIn(originalIn);
        System.setErr(originalErr);
    }
    String errOutput = testErr.toString("UTF-8");
    assert errOutput.contains("Barack") || errOutput.contains("Obama");
}

