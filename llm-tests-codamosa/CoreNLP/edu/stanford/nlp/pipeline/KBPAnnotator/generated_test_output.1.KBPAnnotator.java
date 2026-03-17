import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation annotation = mock(Annotation.class);
    CoreMap sentence = mock(CoreMap.class);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    when(annotation.get(SentencesAnnotation.class)).thenReturn(sentences);
    CorefMention corefMention = mock(CorefMention.class);
    when(corefMention.sentNum).thenReturn(1);
    when(corefMention.startIndex).thenReturn(1);
    when(corefMention.endIndex).thenReturn(3);
    CorefChain corefChain = mock(CorefChain.class);
    when(corefChain.getMentionsInTextualOrder()).thenReturn(Collections.singletonList(corefMention));
    CoreLabel token1 = mock(CoreLabel.class);
    CoreLabel token2 = mock(CoreLabel.class);
    when(token1.get(any())).thenReturn(0);
    when(token2.get(any())).thenReturn(5);
    List<CoreLabel> tokenList = Arrays.asList(token1, token2);
    when(sentence.get(any())).thenReturn(tokenList);
    CoreMap kbpMention = mock(CoreMap.class);
    when(kbpMention.get(any())).thenReturn("LongestMention");
    HashMap<Pair<Integer, Integer>, CoreMap> kbpMentionMap = new HashMap<>();
    kbpMentionMap.put(new Pair<>(0, 5), kbpMention);
    KBPAnnotator annotator = new KBPAnnotator();
    Pair<List<CoreMap>, CoreMap> result = annotator.corefChainToKBPMentions(corefChain, annotation, kbpMentionMap);
    assertNotNull(result);
    assertEquals(1, result.first.size());
    assertEquals("LongestMention", result.second.get(any()));
}

@Test
public void test2()
{
    Annotation annotation = mock(Annotation.class);
    CoreMap sentence = mock(CoreMap.class);
    CoreMap mention = mock(CoreMap.class);
    CoreLabel token = mock(CoreLabel.class);
    List<CoreLabel> tokens = Arrays.asList(token);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    when(mention.get(TokensAnnotation.class)).thenReturn(tokens);
    when(token.sentIndex()).thenReturn(0);
    when(token.index()).thenReturn(1);
    when(token.word()).thenReturn("Barack");
    when(mention.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    when(mention.get(TextAnnotation.class)).thenReturn("Barack Obama");
    when(mention.get(SentenceIndexAnnotation.class)).thenReturn(0);
    when(mention.get(CharacterOffsetBeginAnnotation.class)).thenReturn(0);
    when(mention.get(CharacterOffsetEndAnnotation.class)).thenReturn(12);
    when(mention.get(WikipediaEntityAnnotation.class)).thenReturn("Barack_Obama");
    when(sentence.get(MentionsAnnotation.class)).thenReturn(Arrays.asList(mention));
    when(sentence.get(KBPTriplesAnnotation.class)).thenReturn(new ArrayList<>());
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    when(sentence.get(eq(KBPTriplesAnnotation.class))).thenReturn(new ArrayList<>());
    when(sentence.get(any())).thenReturn(null);
    List<CoreMap> sentenceList = Arrays.asList(sentence);
    when(annotation.get(SentencesAnnotation.class)).thenReturn(sentenceList);
    when(annotation.get(CorefChainAnnotation.class)).thenReturn(null);
    KBPAnnotator annotator = new KBPAnnotator("kbp.nom");
    annotator.annotate(annotation);
    verify(sentence, atLeastOnce()).set(eq(KBPTriplesAnnotation.class), any(List.class));
}

