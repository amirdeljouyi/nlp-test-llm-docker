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
    Map<String, Double> probs1 = new HashMap<>();
    probs1.put("PERSON", 0.6);
    probs1.put("LOCATION", 0.9);
    token1.set(NamedEntityTagProbsAnnotation.class, probs1);
    CoreLabel token2 = new CoreLabel();
    Map<String, Double> probs2 = new HashMap<>();
    probs2.put("PERSON", 0.4);
    probs2.put("LOCATION", 0.95);
    token2.set(NamedEntityTagProbsAnnotation.class, probs2);
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(TokensAnnotation.class, tokens);
    Map<String, Double> result = EntityMentionsAnnotator.determineEntityMentionConfidences(entityMention);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsKey("PERSON"));
    assertTrue(result.containsKey("LOCATION"));
    assertEquals(0.4, result.get("PERSON"), 1.0E-4);
    assertEquals(0.9, result.get("LOCATION"), 1.0E-4);
}

@Test
public void test3()
{
    CoreLabel token = mock(CoreLabel.class);
    when(token.get(WikipediaEntityAnnotation.class)).thenReturn("Barack_Obama");
    CoreMap mention = mock(CoreMap.class);
    List<CoreLabel> mentionTokens = Collections.singletonList(token);
    when(mention.get(TokensAnnotation.class)).thenReturn(mentionTokens);
    when(mention.get(TextAnnotation.class)).thenReturn("Barack Obama");
    when(mention.get(CharacterOffsetBeginAnnotation.class)).thenReturn(0);
    when(mention.get(CharacterOffsetEndAnnotation.class)).thenReturn(12);
    when(mention.get(WikipediaEntityAnnotation.class)).thenReturn(null);
    when(mention.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(Arrays.asList(token));
    when(sentence.get(TokenBeginAnnotation.class)).thenReturn(0);
    when(sentence.get(any())).thenReturn(null);
    doReturn(Collections.singletonList(mention)).when(sentence).get(any());
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    Annotation annotation = new Annotation("Barack Obama was the president.");
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    annotation.set(TextAnnotation.class, "Barack Obama was the president.");
    annotator.annotate(annotation);
    List<CoreMap> allMentions = annotation.get(mentionsCoreAnnotationClass);
    assertNotNull(allMentions);
}

