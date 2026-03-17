import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>();
    expected.add(CorefChainAnnotation.class);
    expected.add(CanonicalEntityMentionIndexAnnotation.class);
    assertEquals("The requirementsSatisfied set should contain exactly the expected annotation classes.", expected, result);
}

@Test
public void test2()
{
    IntTuple pos1 = new IntTuple(3);
    pos1.set(0, 1);
    pos1.set(1, 1);
    pos1.set(2, 1);
    IntTuple pos2 = new IntTuple(3);
    pos2.set(0, 1);
    pos2.set(1, 2);
    pos2.set(2, 5);
    IntTuple pos3 = new IntTuple(3);
    pos3.set(0, 1);
    pos3.set(1, 3);
    pos3.set(2, 2);
    CorefMention mention1 = new CorefMention();
    mention1.position = pos1;
    CorefMention mention2 = new CorefMention();
    mention2.position = pos2;
    CorefMention mention3 = new CorefMention();
    mention3.position = pos3;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    mentions.add(mention3);
    CorefChain mockChain = new CorefChain(1, new HashMap<>());
    mockChain.getMentionsInTextualOrder().clear();
    mockChain.getMentionsInTextualOrder().addAll(mentions);
    Map<Integer, CorefChain> inputMap = new HashMap<>();
    inputMap.put(1, new CorefChain(1, null) {
        @Override
        public List<CorefMention> getMentionsInTextualOrder() {
            return mentions;
        }
    });
    List<Pair<IntTuple, IntTuple>> result = CorefAnnotator.getLinks(inputMap);
    assertEquals(3, result.size());
    assertTrue(result.contains(new Pair<>(pos2, pos1)));
    assertTrue(result.contains(new Pair<>(pos3, pos1)));
    assertTrue(result.contains(new Pair<>(pos3, pos2)));
}

@Test
public void test3()
{
    Annotation annotation = mock(Annotation.class);
    CoreMap sentence = mock(CoreMap.class);
    CoreMap mention1 = mock(CoreMap.class);
    CoreMap mention2 = mock(CoreMap.class);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    List<CoreMap> entityMentions = Arrays.asList(mention1, mention2);
    when(annotation.containsKey(SentencesAnnotation.class)).thenReturn(true);
    when(annotation.get(MentionsAnnotation.class)).thenReturn(entityMentions);
    CoreMap corefMention1 = mock(CoreMap.class);
    when(corefMention1.get(EntityMentionIndexAnnotation.class)).thenReturn(42);
    when(mention1.get(EntityMentionIndexAnnotation.class)).thenReturn(1);
    when(mention2.get(EntityMentionIndexAnnotation.class)).thenReturn(2);
    when(CorefAnnotator.findBestCoreferentEntityMention(eq(mention1), eq(annotation))).thenReturn(Optional.of(corefMention1));
    when(CorefAnnotator.findBestCoreferentEntityMention(eq(mention2), eq(annotation))).thenReturn(Optional.empty());
    CorefAnnotator annotator = new CorefAnnotator();
    Field performMentionDetectionField = CorefAnnotator.class.getDeclaredField("performMentionDetection");
    performMentionDetectionField.setAccessible(true);
    performMentionDetectionField.set(annotator, false);
    Field corefSystemField = CorefAnnotator.class.getDeclaredField("corefSystem");
    corefSystemField.setAccessible(true);
    CorefSystem mockCorefSystem = mock(CorefSystem.class);
    corefSystemField.set(annotator, mockCorefSystem);
    annotator.annotate(annotation);
    verify(mention1).set(CanonicalEntityMentionIndexAnnotation.class, 42);
    verify(mention2, never()).set(eq(CanonicalEntityMentionIndexAnnotation.class), any());
    verify(mockCorefSystem).annotate(annotation);
}

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<Class<? extends CoreAnnotation>>();
    expected.add(CorefChainAnnotation.class);
    expected.add(CanonicalEntityMentionIndexAnnotation.class);
    assertEquals("The returned set of satisfied requirements does not match the expected set.", expected, result);
}

@Test
public void test2()
{
    IntTuple position1 = new IntTuple(3);
    position1.set(0, 1);
    position1.set(1, 1);
    position1.set(2, 1);
    IntTuple position2 = new IntTuple(3);
    position2.set(0, 1);
    position2.set(1, 1);
    position2.set(2, 2);
    CorefMention mention1 = new CorefMention();
    mention1.position = position1;
    CorefMention mention2 = new CorefMention();
    mention2.position = position2;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    CorefChain chain = new CorefChain(1, new HashMap<>());
    chain.getMentionsInTextualOrder().addAll(mentions);
    Map<Integer, CorefChain> input = new HashMap<>();
    input.put(1, chain);
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(input);
    assertEquals(1, links.size());
    assertEquals(position2, links.get(0).first);
    assertEquals(position1, links.get(0).second);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("John saw his dog.");
    annotation.set(SentencesAnnotation.class, new ArrayList<CoreMap>());
    CoreMap mention = mock(CoreMap.class);
    CoreMap coreferentMention = mock(CoreMap.class);
    when(coreferentMention.get(EntityMentionIndexAnnotation.class)).thenReturn(42);
    when(mention.get(EntityMentionIndexAnnotation.class)).thenReturn(1);
    ArrayList<CoreMap> mentions = new ArrayList<>();
    mentions.add(mention);
    annotation.set(MentionsAnnotation.class, mentions);
    CorefAnnotator annotator = new CorefAnnotator();
    CorefAnnotator corefAnnotatorSpy = spy(annotator);
    doNothing().when(corefAnnotatorSpy).setNamedEntityTagGranularity(any(Annotation.class), anyString());
    doReturn(true).when(corefAnnotatorSpy).hasSpeakerAnnotations(annotation);
    doNothing().when(corefAnnotatorSpy).corefSystem.annotate(annotation);
    doReturn(Optional.of(coreferentMention)).when(corefAnnotatorSpy).findBestCoreferentEntityMention(mention, annotation);
    corefAnnotatorSpy.annotate(annotation);
    verify(mention).set(CanonicalEntityMentionIndexAnnotation.class, 42);
}

