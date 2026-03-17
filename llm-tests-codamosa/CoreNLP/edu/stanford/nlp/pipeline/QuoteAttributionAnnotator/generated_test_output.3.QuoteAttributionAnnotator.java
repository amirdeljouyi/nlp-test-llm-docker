import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(MentionAnnotation.class, MentionBeginAnnotation.class, MentionEndAnnotation.class, CanonicalMentionAnnotation.class, CanonicalMentionBeginAnnotation.class, CanonicalMentionEndAnnotation.class, MentionTypeAnnotation.class, MentionSieveAnnotation.class, SpeakerAnnotation.class, SpeakerSieveAnnotation.class, ParagraphIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Properties props = new Properties();
    props.setProperty("quoteattribution.qmsieves", "Pattern");
    props.setProperty("quoteattribution.mssieves", "Heuristic");
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator(props);
    Annotation annotation = new Annotation("“Hello,” said John.");
    try {
        annotator.annotate(annotation);
    } catch (Exception e) {
        fail("annotate should not throw an exception for minimal valid annotation text");
    }
    assertNotNull(annotation.get(TextAnnotation.class));
}

@Test
public void test3()
{
    Annotation mockAnnotation = mock(Annotation.class);
    CoreMap mockEntityMention = mock(CoreMap.class);
    when(mockEntityMention.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    when(mockEntityMention.toString()).thenReturn("John   Doe");
    List<CoreMap> mentionList = Collections.singletonList(mockEntityMention);
    when(mockAnnotation.get(MentionsAnnotation.class)).thenReturn(mentionList);
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    annotator.entityMentionsToCharacterMap(mockAnnotation);
    Map<String, List<Person>> characterMap = annotator.characterMap;
    assertTrue(characterMap.containsKey("John Doe"));
    List<Person> persons = characterMap.get("John Doe");
    assertNotNull(persons);
    assertEquals(1, persons.size());
    assertEquals("John Doe", persons.get(0).name);
    assertEquals("UNK", persons.get(0).gender);
    assertNotNull(persons.get(0).mentions);
    assertTrue(persons.get(0).mentions.isEmpty());
}

