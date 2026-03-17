import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel token1 = mock(CoreLabel.class);
    when(token1.get(any())).thenReturn("John");
    when(token1.beginPosition()).thenReturn(0);
    when(token1.endPosition()).thenReturn(4);
    CoreLabel token2 = mock(CoreLabel.class);
    when(token2.get(any())).thenReturn("Doe");
    when(token2.beginPosition()).thenReturn(5);
    when(token2.endPosition()).thenReturn(8);
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(eq(TokensAnnotation.class))).thenReturn(tokens);
    Annotation annotation = mock(Annotation.class);
    when(annotation.get(eq(SentencesAnnotation.class))).thenReturn(Collections.singletonList(sentence));
    CorefMention mention = mock(CorefMention.class);
    when(mention.sentNum).thenReturn(1);
    when(mention.startIndex).thenReturn(1);
    when(mention.endIndex).thenReturn(3);
    CorefChain corefChain = mock(CorefChain.class);
    when(corefChain.getMentionsInTextualOrder()).thenReturn(Collections.singletonList(mention));
    CoreMap kbpMention = mock(CoreMap.class);
    when(kbpMention.get(any())).thenReturn("John Doe");
    HashMap<Pair<Integer, Integer>, CoreMap> kbpMentions = new HashMap<>();
    Pair<Integer, Integer> offset = new Pair<>(0, 8);
    kbpMentions.put(offset, kbpMention);
    KBPAnnotator annotator = new KBPAnnotator();
    Pair<List<CoreMap>, CoreMap> result = annotator.corefChainToKBPMentions(corefChain, annotation, kbpMentions);
    assertNotNull(result);
    assertEquals(1, result.first.size());
    assertEquals("John Doe", result.second.get(any()));
}

@Test
public void test2()
{
    KBPAnnotator annotator = new KBPAnnotator("en", true);
    CoreLabel token = new CoreLabel();
    token.setWord("Stanford");
    token.setIndex(1);
    token.setSentIndex(0);
    token.set(TextAnnotation.class, "Stanford");
    token.set(NamedEntityTagAnnotation.class, "ORGANIZATION");
    token.set(CharacterOffsetBeginAnnotation.class, 0);
    token.set(CharacterOffsetEndAnnotation.class, 8);
    CoreMap mention = mock(CoreMap.class);
    when(mention.get(TokensAnnotation.class)).thenReturn(Collections.singletonList(token));
    when(mention.get(TextAnnotation.class)).thenReturn("Stanford");
    when(mention.get(NamedEntityTagAnnotation.class)).thenReturn("ORGANIZATION");
    when(mention.get(CharacterOffsetBeginAnnotation.class)).thenReturn(0);
    when(mention.get(CharacterOffsetEndAnnotation.class)).thenReturn(8);
    when(mention.get(SentenceIndexAnnotation.class)).thenReturn(0);
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(Collections.singletonList(token));
    when(sentence.get(MentionsAnnotation.class)).thenReturn(Collections.singletonList(mention));
    List<CoreMap> sentences = Collections.singletonList(sentence);
    Annotation annotation = new Annotation("Stanford");
    annotation.set(SentencesAnnotation.class, sentences);
    annotator.annotate(annotation);
    List<?> outputTriples = annotation.get(SentencesAnnotation.class).get(0).get(KBPTriplesAnnotation.class);
    assertNotNull(outputTriples);
}

