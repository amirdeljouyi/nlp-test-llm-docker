import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefMention mention1 = mock(CorefMention.class);
    when(mention1.sentNum).thenReturn(1);
    when(mention1.startIndex).thenReturn(1);
    when(mention1.endIndex).thenReturn(3);
    CorefMention mention2 = mock(CorefMention.class);
    when(mention2.sentNum).thenReturn(1);
    when(mention2.startIndex).thenReturn(4);
    when(mention2.endIndex).thenReturn(6);
    CorefChain chain = mock(CorefChain.class);
    when(chain.getMentionsInTextualOrder()).thenReturn(Arrays.asList(mention1, mention2));
    Annotation ann = mock(Annotation.class);
    CoreMap sentence = mock(CoreMap.class);
    when(ann.get(SentencesAnnotation.class)).thenReturn(Collections.singletonList(sentence));
    CoreLabel token0 = mock(CoreLabel.class);
    when(token0.get(any())).thenReturn(0);
    CoreLabel token1 = mock(CoreLabel.class);
    when(token1.get(any())).thenReturn(4);
    CoreLabel token2 = mock(CoreLabel.class);
    when(token2.get(any())).thenReturn(10);
    CoreLabel token3 = mock(CoreLabel.class);
    when(token3.get(any())).thenReturn(15);
    CoreLabel token4 = mock(CoreLabel.class);
    when(token4.get(any())).thenReturn(18);
    List<CoreLabel> tokens = Arrays.asList(token0, token1, token2, token3, token4);
    when(sentence.get(any())).thenReturn(tokens);
    CoreMap kbpMention1 = mock(CoreMap.class);
    when(kbpMention1.get(any())).thenReturn("Short");
    CoreMap kbpMention2 = mock(CoreMap.class);
    when(kbpMention2.get(any())).thenReturn("LongerMention");
    Pair<Integer, Integer> offset1 = new Pair<>(0, 10);
    Pair<Integer, Integer> offset2 = new Pair<>(10, 18);
    HashMap<Pair<Integer, Integer>, CoreMap> kbpMentionMap = new HashMap<>();
    kbpMentionMap.put(offset1, kbpMention1);
    kbpMentionMap.put(offset2, kbpMention2);
    when(token0.get(any())).thenReturn(0);
    when(token1.get(any())).thenReturn(4);
    when(token2.get(any())).thenReturn(10);
    when(token3.get(any())).thenReturn(15);
    when(token4.get(any())).thenReturn(18);
    when(sentence.get(any())).thenReturn(tokens);
    KBPAnnotator annotator = new KBPAnnotator();
    Pair<List<CoreMap>, CoreMap> result = annotator.corefChainToKBPMentions(chain, ann, kbpMentionMap);
    assertNotNull(result);
    assertEquals(2, result.first.size());
    assertSame(kbpMention2, result.second);
}

@Test
public void test2()
{
    KBPAnnotator annotator = new KBPAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = Collections.unmodifiableSet(new HashSet<Class<? extends CoreAnnotation>>() {
        {
            add(KBPTriplesAnnotation.class);
        }
    });
    assertEquals(expected, result);
}

@Test
public void test3()
{
    InputStream originalIn = System.in;
    PrintStream originalErr = System.err;
    ByteArrayInputStream testInput = new ByteArrayInputStream("Barack Obama was born in Hawaii.\n\n".getBytes());
    ByteArrayOutputStream testErrOutput = new ByteArrayOutputStream();
    PrintStream testErr = new PrintStream(testErrOutput);
    System.setIn(testInput);
    System.setErr(testErr);
    KBPAnnotator.main(new String[]{ "-kbp.model", "none" });
    System.setIn(originalIn);
    System.setErr(originalErr);
    String output = testErrOutput.toString("UTF-8");
}

@Test
public void test4()
{
    Annotation annotation = mock(Annotation.class);
    CoreLabel token = new CoreLabel();
    token.setWord("Obama");
    token.setIndex(1);
    token.setSentIndex(0);
    token.set(NamedEntityTagAnnotation.class, "PERSON");
    token.set(WikipediaEntityAnnotation.class, "Barack_Obama");
    CoreMap mention = mock(CoreMap.class);
    when(mention.get(TokensAnnotation.class)).thenReturn(Collections.singletonList(token));
    when(mention.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    when(mention.get(TextAnnotation.class)).thenReturn("Obama");
    when(mention.get(CharacterOffsetBeginAnnotation.class)).thenReturn(0);
    when(mention.get(CharacterOffsetEndAnnotation.class)).thenReturn(5);
    when(mention.get(SentenceIndexAnnotation.class)).thenReturn(0);
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(MentionsAnnotation.class)).thenReturn(Collections.singletonList(mention));
    when(sentence.get(TokensAnnotation.class)).thenReturn(Collections.singletonList(token));
    List<CoreMap> sentences = Collections.singletonList(sentence);
    when(annotation.get(SentencesAnnotation.class)).thenReturn(sentences);
    when(annotation.get(CorefChainAnnotation.class)).thenReturn(null);
    KBPAnnotator annotator = new KBPAnnotator("en", true);
    annotator.annotate(annotation);
    verify(sentence).set(eq(KBPTriplesAnnotation.class), any(List.class));
}

