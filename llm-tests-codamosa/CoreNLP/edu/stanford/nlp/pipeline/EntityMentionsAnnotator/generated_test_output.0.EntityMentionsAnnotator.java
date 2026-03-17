import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
}

@Test
public void test2()
{
    CoreLabel token1 = new CoreLabel();
    Map<String, Double> token1Probs = new HashMap<>();
    token1Probs.put("PERSON", 0.8);
    token1Probs.put("LOCATION", 0.5);
    token1.set(NamedEntityTagProbsAnnotation.class, token1Probs);
    CoreLabel token2 = new CoreLabel();
    Map<String, Double> token2Probs = new HashMap<>();
    token2Probs.put("PERSON", 0.6);
    token2Probs.put("LOCATION", 0.9);
    token2.set(NamedEntityTagProbsAnnotation.class, token2Probs);
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(TokensAnnotation.class, tokens);
    Map<String, Double> result = EntityMentionsAnnotator.determineEntityMentionConfidences(entityMention);
    assertEquals(2, result.size());
    assertEquals(Double.valueOf(0.6), result.get("PERSON"));
    assertEquals(Double.valueOf(0.5), result.get("LOCATION"));
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Barack Obama was the 44th President of the United States.");
    CoreMap sentence = new ArrayCoreMap();
    List<CoreMap> sentenceList = Collections.singletonList(sentence);
    annotation.set(SentencesAnnotation.class, sentenceList);
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "Barack");
    token1.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Obama");
    token2.set(NamedEntityTagAnnotation.class, "PERSON");
    token1.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    token2.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    sentence.set(TokensAnnotation.class, tokens);
    sentence.set(TokenBeginAnnotation.class, 0);
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(TokensAnnotation.class, tokens);
    entityMention.set(TextAnnotation.class, "Barack Obama");
    entityMention.set(CharacterOffsetBeginAnnotation.class, 0);
    entityMention.set(CharacterOffsetEndAnnotation.class, 12);
    entityMention.set(NamedEntityTagAnnotation.class, "PERSON");
    sentence.set(mentionsCoreAnnotationClass, Collections.singletonList(entityMention));
    annotation.set(TextAnnotation.class, "Barack Obama was the 44th President of the United States.");
    annotator.annotate(annotation);
    List<CoreMap> annotatedMentions = annotation.get(mentionsCoreAnnotationClass);
    assertNotNull(annotatedMentions);
    assertEquals(1, annotatedMentions.size());
    CoreMap annotatedMention = annotatedMentions.get(0);
    assertEquals("PERSON", annotatedMention.get(EntityTypeAnnotation.class));
    assertEquals("Barack Obama", annotatedMention.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(0), annotatedMention.get(SentenceIndexAnnotation.class));
    assertEquals("Barack_Obama", annotatedMention.get(WikipediaEntityAnnotation.class));
    assertNotNull(annotatedMention.get(NamedEntityTagProbsAnnotation.class));
    assertTrue(((HashMap<?, ?>) (annotatedMention.get(NamedEntityTagProbsAnnotation.class))).containsKey("PERSON"));
}

