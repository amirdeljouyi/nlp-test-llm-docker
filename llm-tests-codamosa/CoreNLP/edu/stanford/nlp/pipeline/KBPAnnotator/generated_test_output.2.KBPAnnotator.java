import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    KBPAnnotator annotator = new KBPAnnotator();
    Annotation annotation = new Annotation("Sample text");
    List<CoreMap> sentences = new ArrayList<>();
    CoreMap sentence = new Annotation("John Smith went to the store.");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setIndex(1);
    token1.setCharacterOffsetBegin(0);
    token1.setCharacterOffsetEnd(4);
    token1.set(TextAnnotation.class, "John");
    CoreLabel token2 = new CoreLabel();
    token2.setIndex(2);
    token2.setCharacterOffsetBegin(5);
    token2.setCharacterOffsetEnd(10);
    token2.set(TextAnnotation.class, "Smith");
    CoreLabel token3 = new CoreLabel();
    token3.setIndex(3);
    token3.setCharacterOffsetBegin(11);
    token3.setCharacterOffsetEnd(15);
    token3.set(TextAnnotation.class, "went");
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    sentence.set(TokensAnnotation.class, tokens);
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    CorefMention mention = new CorefMention(1, 0, 1, 3, "John Smith");
    List<CorefMention> mentionList = new ArrayList<>();
    mentionList.add(mention);
    CorefChain corefChain = new CorefChain(1, mentionList);
    CoreMap kbpMention = new Annotation("John Smith");
    kbpMention.set(TextAnnotation.class, "John Smith");
    HashMap<Pair<Integer, Integer>, CoreMap> kbpMentions = new HashMap<>();
    kbpMentions.put(new Pair<>(0, 10), kbpMention);
    token1.set(CharacterOffsetBeginAnnotation.class, 0);
    token2.set(CharacterOffsetEndAnnotation.class, 10);
    Pair<List<CoreMap>, CoreMap> result = annotator.corefChainToKBPMentions(corefChain, annotation, kbpMentions);
    assertNotNull(result);
    assertEquals(1, result.first.size());
    assertEquals("John Smith", result.second.get(TextAnnotation.class));
}

@Test
public void test2()
{
    KBPAnnotator annotator = new KBPAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(KBPTriplesAnnotation.class));
    boolean isUnmodifiable = false;
    try {
        result.add(TokensAnnotation.class);
    } catch (UnsupportedOperationException e) {
        isUnmodifiable = true;
    }
    assertTrue("Set should be unmodifiable", isUnmodifiable);
}

@Test
public void test3()
{
    KBPAnnotator annotator = new KBPAnnotator();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Barack");
    token1.setIndex(1);
    token1.setSentIndex(0);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Obama");
    token2.setIndex(2);
    token2.setSentIndex(0);
    CoreMap mention1 = mock(CoreMap.class);
    when(mention1.get(TokensAnnotation.class)).thenReturn(Collections.singletonList(token1));
    when(mention1.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    when(mention1.get(TextAnnotation.class)).thenReturn("Barack");
    when(mention1.get(SentenceIndexAnnotation.class)).thenReturn(0);
    when(mention1.get(CharacterOffsetBeginAnnotation.class)).thenReturn(0);
    when(mention1.get(CharacterOffsetEndAnnotation.class)).thenReturn(6);
    CoreMap mention2 = mock(CoreMap.class);
    when(mention2.get(TokensAnnotation.class)).thenReturn(Collections.singletonList(token2));
    when(mention2.get(NamedEntityTagAnnotation.class)).thenReturn("PERSON");
    when(mention2.get(TextAnnotation.class)).thenReturn("Obama");
    when(mention2.get(SentenceIndexAnnotation.class)).thenReturn(0);
    when(mention2.get(CharacterOffsetBeginAnnotation.class)).thenReturn(7);
    when(mention2.get(CharacterOffsetEndAnnotation.class)).thenReturn(12);
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(Arrays.asList(token1, token2));
    when(sentence.get(MentionsAnnotation.class)).thenReturn(Arrays.asList(mention1, mention2));
    Annotation annotation = mock(Annotation.class);
    when(annotation.get(SentencesAnnotation.class)).thenReturn(Collections.singletonList(sentence));
    when(annotation.get(CorefChainAnnotation.class)).thenReturn(null);
    annotator.annotate(annotation);
    verify(sentence).set(eq(KBPTriplesAnnotation.class), any(List.class));
}

