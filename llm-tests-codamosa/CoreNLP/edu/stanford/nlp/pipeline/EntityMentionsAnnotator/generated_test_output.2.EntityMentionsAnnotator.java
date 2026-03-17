import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
}

@Test
public void test2()
{
    CoreLabel token1 = new CoreLabel();
    Map<String, Double> probs1 = new HashMap<>();
    probs1.put("PERSON", 0.8);
    probs1.put("LOCATION", 0.3);
    token1.set(NamedEntityTagProbsAnnotation.class, probs1);
    CoreLabel token2 = new CoreLabel();
    Map<String, Double> probs2 = new HashMap<>();
    probs2.put("PERSON", 0.6);
    probs2.put("LOCATION", 0.5);
    token2.set(NamedEntityTagProbsAnnotation.class, probs2);
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(TokensAnnotation.class, tokens);
    Map<String, Double> result = EntityMentionsAnnotator.determineEntityMentionConfidences(entityMention);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(Double.valueOf(0.6), result.get("PERSON"));
    assertEquals(Double.valueOf(0.3), result.get("LOCATION"));
}

@Test
public void test3()
{
    CoreLabel token = new CoreLabel();
    token.set(TextAnnotation.class, "Barack");
    token.set(NamedEntityTagAnnotation.class, "PERSON");
    token.set(CharacterOffsetBeginAnnotation.class, 0);
    token.set(CharacterOffsetEndAnnotation.class, 6);
    token.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token);
    CoreMap sentence = new Annotation("Barack");
    sentence.set(TokensAnnotation.class, tokens);
    sentence.set(TokenBeginAnnotation.class, 0);
    CoreMap mentionChunk = new Annotation("Barack");
    mentionChunk.set(TextAnnotation.class, "Barack");
    mentionChunk.set(TokensAnnotation.class, tokens);
    mentionChunk.set(CharacterOffsetBeginAnnotation.class, 0);
    mentionChunk.set(CharacterOffsetEndAnnotation.class, 6);
    mentionChunk.set(NamedEntityTagAnnotation.class, "PERSON");
    List<CoreMap> chunks = new ArrayList<>();
    chunks.add(mentionChunk);
    List<CoreMap> sentenceList = new ArrayList<>();
    sentenceList.add(sentence);
    Annotation annotation = new Annotation("Barack");
    annotation.set(SentencesAnnotation.class, sentenceList);
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> annotatedMentions = annotation.get(mentionsCoreAnnotationClass);
    assertNotNull(annotatedMentions);
    assertEquals(1, annotatedMentions.size());
    CoreMap mention = annotatedMentions.get(0);
    assertEquals("PERSON", mention.get(EntityTypeAnnotation.class));
    assertEquals("Barack", mention.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(0), mention.get(SentenceIndexAnnotation.class));
    assertEquals("Barack_Obama", mention.get(WikipediaEntityAnnotation.class));
}

