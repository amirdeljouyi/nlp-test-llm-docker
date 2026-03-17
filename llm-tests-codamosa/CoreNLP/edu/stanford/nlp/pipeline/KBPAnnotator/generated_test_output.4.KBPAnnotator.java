import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel token1 = new CoreLabel();
    token1.setBeginPosition(0);
    token1.setEndPosition(4);
    token1.set(TextAnnotation.class, "John");
    CoreLabel token2 = new CoreLabel();
    token2.setBeginPosition(5);
    token2.setEndPosition(10);
    token2.set(TextAnnotation.class, "Smith");
    CoreMap sentence = new Annotation("John Smith");
    sentence.set(TokensAnnotation.class, Arrays.asList(token1, token2));
    Annotation annotation = new Annotation("John Smith");
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    CorefMention corefMention = new CorefMention(1, 0, 1, 3, 0, "John Smith");
    List<CorefMention> corefMentionsList = Collections.singletonList(corefMention);
    CorefChain corefChain = new CorefChain(1, corefMentionsList);
    CoreMap kbpMention = new Annotation("John Smith");
    kbpMention.set(TextAnnotation.class, "John Smith");
    HashMap<Pair<Integer, Integer>, CoreMap> kbpMentions = new HashMap<>();
    kbpMentions.put(new Pair<>(0, 10), kbpMention);
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token1.set(CharacterOffsetEndAnnotation.class, 4);
    token2.set(CharacterOffsetBeginAnnotation.class, 5);
    token2.set(CharacterOffsetEndAnnotation.class, 10);
    sentence.set(TokensAnnotation.class, Arrays.asList(token1, token2));
    KBPAnnotator annotator = new KBPAnnotator("", new Properties());
    Pair<List<CoreMap>, CoreMap> result = annotator.corefChainToKBPMentions(corefChain, annotation, kbpMentions);
    assertNotNull(result);
    List<CoreMap> mentions = result.first;
    CoreMap bestMention = result.second;
    assertEquals(1, mentions.size());
    assertEquals("John Smith", bestMention.get(TextAnnotation.class));
}

@Test
public void test2()
{
    KBPAnnotator annotator = new KBPAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = Collections.unmodifiableSet(new HashSet<Class<? extends CoreAnnotation>>(Arrays.asList(KBPTriplesAnnotation.class)));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test3()
{
    String simulatedInput = "Barack Obama was born in Hawaii.\nexit\n";
    InputStream originalIn = System.in;
    PrintStream originalErr = System.err;
    ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
    ByteArrayOutputStream testErr = new ByteArrayOutputStream();
    System.setIn(testIn);
    System.setErr(new PrintStream(testErr));
    try {
        KBPAnnotator.main(new String[]{ "-ner.useSUTime", "false" });
    } catch (Exception e) {
    } finally {
        System.setIn(originalIn);
        System.setErr(originalErr);
    }
    String output = testErr.toString();
    assert !output.isEmpty();
}

@Test
public void test4()
{
    Annotation annotation = new Annotation("Barack Obama was born in Hawaii.");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Barack");
    token1.setIndex(1);
    token1.setSentIndex(0);
    token1.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Obama");
    token2.setIndex(2);
    token2.setSentIndex(0);
    token2.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("was");
    token3.setIndex(3);
    token3.setSentIndex(0);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("born");
    token4.setIndex(4);
    token4.setSentIndex(0);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("in");
    token5.setIndex(5);
    token5.setSentIndex(0);
    CoreLabel token6 = new CoreLabel();
    token6.setWord("Hawaii");
    token6.setIndex(6);
    token6.setSentIndex(0);
    token6.set(NamedEntityTagAnnotation.class, "LOCATION");
    CoreLabel token7 = new CoreLabel();
    token7.setWord(".");
    token7.setIndex(7);
    token7.setSentIndex(0);
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3, token4, token5, token6, token7);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    sentence.set(SentenceIndexAnnotation.class, 0);
    CoreMap mention = new ArrayCoreMap();
    mention.set(TextAnnotation.class, "Barack Obama");
    mention.set(NamedEntityTagAnnotation.class, "PERSON");
    mention.set(TokensAnnotation.class, Arrays.asList(token1, token2));
    mention.set(CharacterOffsetBeginAnnotation.class, 0);
    mention.set(CharacterOffsetEndAnnotation.class, 12);
    mention.set(SentenceIndexAnnotation.class, 0);
    mention.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    sentence.set(MentionsAnnotation.class, Collections.singletonList(mention));
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    annotation.set(CorefChain.class, null);
    KBPAnnotator annotator = new KBPAnnotator("en", new Properties());
    annotator.annotate(annotation);
    List<CoreLabel> updatedTokens = sentence.get(TokensAnnotation.class);
    boolean hasWikipediaLink = false;
    for (CoreLabel token : updatedTokens) {
        if ("Barack".equals(token.word()) || "Obama".equals(token.word())) {
            String link = token.get(WikipediaEntityAnnotation.class);
            if ("Barack_Obama".equals(link)) {
                hasWikipediaLink = true;
                break;
            }
        }
    }
    assertTrue("Expected WikipediaEntityAnnotation to be propagated to mention tokens", hasWikipediaLink);
}

