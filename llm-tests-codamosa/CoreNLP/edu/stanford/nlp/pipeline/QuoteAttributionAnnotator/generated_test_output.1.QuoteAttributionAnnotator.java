import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>();
    expected.add(MentionAnnotation.class);
    expected.add(MentionBeginAnnotation.class);
    expected.add(MentionEndAnnotation.class);
    expected.add(CanonicalMentionAnnotation.class);
    expected.add(CanonicalMentionBeginAnnotation.class);
    expected.add(CanonicalMentionEndAnnotation.class);
    expected.add(MentionTypeAnnotation.class);
    expected.add(MentionSieveAnnotation.class);
    expected.add(SpeakerAnnotation.class);
    expected.add(SpeakerSieveAnnotation.class);
    expected.add(ParagraphIndexAnnotation.class);
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("“I am the king,” said Robert Baratheon.");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Robert");
    token1.set(TextAnnotation.class, "Robert");
    token1.set(TokenBeginAnnotation.class, 0);
    token1.set(EntityMentionIndexAnnotation.class, 0);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Baratheon");
    token2.set(TextAnnotation.class, "Baratheon");
    token2.set(TokenBeginAnnotation.class, 1);
    tokens.add(token2);
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap mention = new Annotation("Robert Baratheon");
    mention.set(TextAnnotation.class, "Robert Baratheon");
    List<CoreLabel> mentionTokens = new ArrayList<>();
    mentionTokens.add(token1);
    mentionTokens.add(token2);
    mention.set(TokensAnnotation.class, mentionTokens);
    mention.set(CanonicalEntityMentionIndexAnnotation.class, 0);
    List<CoreMap> mentions = new ArrayList<>();
    mentions.add(mention);
    annotation.set(MentionsAnnotation.class, mentions);
    CoreMap quote = new Annotation("“I am the king,”");
    quote.set(MentionBeginAnnotation.class, 0);
    List<CoreMap> quotes = new ArrayList<>();
    quotes.add(quote);
    annotation.set(QuoteAnnotation.class, quotes);
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator(new Properties());
    annotator.buildCharacterMapPerAnnotation = true;
    annotator.qmSieveList = "";
    annotator.msSieveList = "";
    annotator.annotate(annotation);
    CoreMap enrichedQuote = annotation.get(QuoteAnnotation.class).get(0);
    String canonicalMention = enrichedQuote.get(CanonicalMentionAnnotation.class);
    Integer canonicalBegin = enrichedQuote.get(CanonicalMentionBeginAnnotation.class);
    Integer canonicalEnd = enrichedQuote.get(CanonicalMentionEndAnnotation.class);
    assertEquals("Robert Baratheon", canonicalMention);
    assertEquals(Integer.valueOf(0), canonicalBegin);
    assertEquals(Integer.valueOf(0), canonicalEnd);
}

@Test
public void test3()
{
    CoreMap personEntity = mock(CoreMap.class);
    when(personEntity.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    when(personEntity.toString()).thenReturn("John  Doe");
    List<CoreMap> mentions = Arrays.asList(personEntity);
    Annotation annotation = new Annotation("Sample text");
    annotation.set(MentionsAnnotation.class, mentions);
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    annotator.entityMentionsToCharacterMap(annotation);
    Map<String, List<Person>> characterMap = annotator.characterMap;
    assertEquals(1, characterMap.size());
    assertTrue(characterMap.containsKey("John Doe"));
    List<Person> personList = characterMap.get("John Doe");
    assertNotNull(personList);
    assertEquals(1, personList.size());
    Person person = personList.get(0);
    assertEquals("John Doe", person.name);
    assertEquals("UNK", person.gender);
    assertNotNull(person.mentions);
    assertTrue(person.mentions.isEmpty());
}

