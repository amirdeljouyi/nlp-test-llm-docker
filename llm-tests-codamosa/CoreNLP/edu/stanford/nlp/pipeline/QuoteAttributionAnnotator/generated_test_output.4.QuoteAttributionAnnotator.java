import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
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
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    String text = "\"I am Iron Man,\" said Tony.";
    Annotation annotation = new Annotation(text);
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,quote");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.annotate(annotation);
    CoreLabel token = annotation.get(TokensAnnotation.class).get(5);
    token.set(NamedEntityTagAnnotation.class, "PERSON");
    List<CoreMap> mentions = new ArrayList<>();
    Annotation mention = new Annotation("Tony");
    List<CoreLabel> mentionTokens = new ArrayList<>();
    mentionTokens.add(token);
    mention.set(TokensAnnotation.class, mentionTokens);
    mention.set(TextAnnotation.class, "Tony");
    mention.set(MentionBeginAnnotation.class, 5);
    mention.set(MentionEndAnnotation.class, 5);
    mentions.add(mention);
    annotation.set(MentionsAnnotation.class, mentions);
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> quotes = QuoteAnnotator.gatherQuotes(annotation);
    boolean foundCanonical = false;
    for (int i = 0; i < quotes.size(); i++) {
        CoreMap quote = quotes.get(i);
        String canonical = quote.get(CanonicalMentionAnnotation.class);
        if ("Tony".equals(canonical)) {
            foundCanonical = true;
            break;
        }
    }
    assertTrue("Canonical mention 'Tony' should be assigned to the quote", foundCanonical);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("");
    ArrayCoreMap personMention = new ArrayCoreMap();
    personMention.set(NamedEntityTagAnnotation.class, "PERSON");
    personMention.set(TextAnnotation.class, "John Doe");
    CoreMap coreMap = new CoreMap() {
        @Override
        public <T> T get(Class<?> key) {
            if (key == NamedEntityTagAnnotation.class) {
                return ((T) ("PERSON"));
            }
            return null;
        }

        @Override
        public <T> void set(Class<T> key, T value) {
        }

        @Override
        public <T> T remove(Class<T> key) {
            return null;
        }

        @Override
        public List<Class<?>> getKeySet() {
            return null;
        }

        @Override
        public String toString() {
            return "John   Doe";
        }
    };
    annotation.set(MentionsAnnotation.class, Collections.singletonList(coreMap));
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    annotator.entityMentionsToCharacterMap(annotation);
    HashMap<String, List<Person>> characterMap = annotator.characterMap;
    assertNotNull(characterMap);
    assertTrue(characterMap.containsKey("John Doe"));
    List<Person> personList = characterMap.get("John Doe");
    assertEquals(1, personList.size());
    assertEquals("John Doe", personList.get(0).name);
    assertEquals("UNK", personList.get(0).gender);
    assertTrue(personList.get(0).mentions.isEmpty());
}

