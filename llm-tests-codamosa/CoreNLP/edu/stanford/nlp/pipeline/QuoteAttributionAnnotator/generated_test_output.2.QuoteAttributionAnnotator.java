import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(MentionAnnotation.class, MentionBeginAnnotation.class, MentionEndAnnotation.class, CanonicalMentionAnnotation.class, CanonicalMentionBeginAnnotation.class, CanonicalMentionEndAnnotation.class, MentionTypeAnnotation.class, MentionSieveAnnotation.class, SpeakerAnnotation.class, SpeakerSieveAnnotation.class, ParagraphIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("John said, \"I\'ll be there.\"");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("John");
    token1.set(TokenBeginAnnotation.class, 0);
    token1.set(EntityMentionIndexAnnotation.class, 0);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("said");
    token2.set(TokenBeginAnnotation.class, 1);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(",");
    token3.set(TokenBeginAnnotation.class, 2);
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("\"");
    token4.set(TokenBeginAnnotation.class, 3);
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("I'll");
    token5.set(TokenBeginAnnotation.class, 4);
    tokens.add(token5);
    CoreLabel token6 = new CoreLabel();
    token6.setWord("be");
    token6.set(TokenBeginAnnotation.class, 5);
    tokens.add(token6);
    CoreLabel token7 = new CoreLabel();
    token7.setWord("there");
    token7.set(TokenBeginAnnotation.class, 6);
    tokens.add(token7);
    CoreLabel token8 = new CoreLabel();
    token8.setWord(".");
    token8.set(TokenBeginAnnotation.class, 7);
    tokens.add(token8);
    CoreLabel token9 = new CoreLabel();
    token9.setWord("\"");
    token9.set(TokenBeginAnnotation.class, 8);
    tokens.add(token9);
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap mention = new ArrayCoreMap();
    mention.set(CanonicalEntityMentionIndexAnnotation.class, 0);
    mention.set(TokensAnnotation.class, tokens.subList(0, 1));
    mention.set(TextAnnotation.class, "John");
    List<CoreMap> mentions = new ArrayList<>();
    mentions.add(mention);
    annotation.set(MentionsAnnotation.class, mentions);
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    annotator.buildCharacterMapPerAnnotation = true;
    annotator.characterMap = new HashMap<>();
    annotator.qmSieveList = "";
    annotator.msSieveList = "";
    annotator.annotate(annotation);
    assertNotNull(annotation.get(TokensAnnotation.class));
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
    Map<String, List<Person>> result = annotator.characterMap;
    assertEquals(1, result.size());
    assertTrue(result.containsKey("John Doe"));
    List<Person> people = result.get("John Doe");
    assertNotNull(people);
    assertEquals(1, people.size());
    Person person = people.get(0);
    assertEquals("John Doe", person.name);
    assertEquals("UNK", person.gender);
    assertNotNull(person.quotes);
    assertTrue(person.quotes.isEmpty());
}


