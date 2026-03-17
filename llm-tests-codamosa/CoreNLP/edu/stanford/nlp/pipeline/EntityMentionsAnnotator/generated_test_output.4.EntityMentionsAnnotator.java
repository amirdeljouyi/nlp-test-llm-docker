import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
}
{
    try {
        Field field = EntityMentionsAnnotator.class.getDeclaredField("mentionsCoreAnnotationClass");
        field.setAccessible(true);
        field.set(this, mockClass);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

@Test
public void test2()
{
    CoreLabel token1 = new CoreLabel();
    Map<String, Double> probs1 = new HashMap<>();
    probs1.put("PERSON", 0.92);
    probs1.put("LOCATION", 0.5);
    token1.set(NamedEntityTagProbsAnnotation.class, probs1);
    CoreLabel token2 = new CoreLabel();
    Map<String, Double> probs2 = new HashMap<>();
    probs2.put("PERSON", 0.85);
    probs2.put("LOCATION", 0.6);
    token2.set(NamedEntityTagProbsAnnotation.class, probs2);
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(TokensAnnotation.class, tokens);
    Map<String, Double> result = EntityMentionsAnnotator.determineEntityMentionConfidences(entityMention);
    assertEquals(2, result.size());
    assertEquals(Double.valueOf(0.85), result.get("PERSON"));
    assertEquals(Double.valueOf(0.5), result.get("LOCATION"));
}

@Test
public void test3()
{
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "Barack");
    token1.set(NamedEntityTagAnnotation.class, "PERSON");
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token1.set(CharacterOffsetEndAnnotation.class, 6);
    token1.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Obama");
    token2.set(NamedEntityTagAnnotation.class, "PERSON");
    token2.set(CharacterOffsetBeginAnnotation.class, 7);
    token2.set(CharacterOffsetEndAnnotation.class, 12);
    token2.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    sentence.set(TokenBeginAnnotation.class, 0);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    Annotation annotation = new Annotation("Barack Obama");
    annotation.set(SentencesAnnotation.class, sentences);
    annotation.set(TextAnnotation.class, "Barack Obama");
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> entityMentions = annotation.get(mentionsCoreAnnotationClass);
    assertNotNull(entityMentions);
    assertFalse(entityMentions.isEmpty());
    CoreMap mention = entityMentions.get(0);
    List<CoreLabel> mentionTokens = mention.get(TokensAnnotation.class);
    assertEquals(tokens, mentionTokens);
    String entityType = mention.get(EntityTypeAnnotation.class);
    assertEquals("PERSON", entityType);
    String wikiEntity = mention.get(WikipediaEntityAnnotation.class);
    assertEquals("Barack_Obama", wikiEntity);
    Integer sentIdx = mention.get(SentenceIndexAnnotation.class);
    assertEquals(Integer.valueOf(0), sentIdx);
}


