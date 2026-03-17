import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator(new Properties());
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Assert.assertEquals(1, result.size());
}

@Test
public void test2()
{
    CoreLabel token1 = mock(CoreLabel.class);
    Map<String, Double> probs1 = new HashMap<>();
    probs1.put("PERSON", 0.9);
    probs1.put("LOCATION", 0.7);
    when(token1.get(NamedEntityTagProbsAnnotation.class)).thenReturn(probs1);
    CoreLabel token2 = mock(CoreLabel.class);
    Map<String, Double> probs2 = new HashMap<>();
    probs2.put("PERSON", 0.6);
    probs2.put("LOCATION", 0.8);
    when(token2.get(NamedEntityTagProbsAnnotation.class)).thenReturn(probs2);
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    when(token1.get(NamedEntityTagProbsAnnotation.class)).thenReturn(probs1);
    CoreMap entityMention = mock(CoreMap.class);
    when(entityMention.get(TokensAnnotation.class)).thenReturn(tokens);
    HashMap<String, Double> result = EntityMentionsAnnotator.determineEntityMentionConfidences(entityMention);
    Assert.assertEquals(2, result.size());
    Assert.assertTrue(result.containsKey("PERSON"));
    Assert.assertTrue(result.containsKey("LOCATION"));
    Assert.assertEquals(Double.valueOf(0.6), result.get("PERSON"));
    Assert.assertEquals(Double.valueOf(0.7), result.get("LOCATION"));
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Barack Obama was the 44th President.");
    CoreLabel token = new CoreLabel();
    token.set(TextAnnotation.class, "Obama");
    token.set(BeginPositionAnnotation.class, 7);
    token.set(EndPositionAnnotation.class, 12);
    token.set(NamedEntityTagAnnotation.class, "PERSON");
    token.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    List<CoreLabel> tokenList = Collections.singletonList(token);
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokenList);
    when(sentence.get(TokenBeginAnnotation.class)).thenReturn(0);
    doAnswer(( invocation) -> {
        if (invocation.getArgument(0).equals(.class)) {
            return null;
        }
        return null;
    }).when(sentence).get(any());
    Annotation mockedAnnotation = spy(annotation);
    when(mockedAnnotation.get(SentencesAnnotation.class)).thenReturn(Collections.singletonList(sentence));
    when(mockedAnnotation.get(TextAnnotation.class)).thenReturn("Barack Obama was the 44th President.");
    EntityMentionsAnnotator annotator = new EntityMentionsAnnotator();
    EntityMentionsAnnotator.matchTokenText = true;
    annotator.annotate(mockedAnnotation);
    List<CoreMap> allMentions = mockedAnnotation.get(MentionsAnnotation.class);
    assertNotNull("Mentions should not be null", allMentions);
    assertTrue("Should contain at least one mention", allMentions.size() >= 1);
}

