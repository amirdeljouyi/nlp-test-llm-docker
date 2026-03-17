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
    assertEquals(expected, result);
}

@Test
public void test2()
{
    IntTuple pos1 = new IntTuple(3);
    pos1.set(0, 1);
    pos1.set(1, 2);
    pos1.set(2, 3);
    IntTuple pos2 = new IntTuple(3);
    pos2.set(0, 1);
    pos2.set(1, 2);
    pos2.set(2, 4);
    CorefMention mention1 = new CorefMention();
    mention1.position = pos1;
    CorefMention mention2 = new CorefMention();
    mention2.position = pos2;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    CorefChain corefChain = new CorefChain(1, new HashMap<>());
    corefChain.getMentionsInTextualOrder().addAll(mentions);
    Map<Integer, CorefChain> input = new HashMap<>();
    input.put(1, corefChain);
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(input);
    assertEquals(1, links.size());
    Pair<IntTuple, IntTuple> link = links.get(0);
    assertArrayEquals(new int[]{ 1, 2, 4 }, link.first.asArray());
    assertArrayEquals(new int[]{ 1, 2, 3 }, link.second.asArray());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("");
    annotation.set(SentencesAnnotation.class, new ArrayList<CoreMap>());
    annotation.set(MentionsAnnotation.class, new ArrayList<CoreMap>());
    CorefAnnotator annotator = new CorefAnnotator(new TypesafeMap.Key<String>(""), true);
    CorefAnnotator spyAnnotator = spy(annotator);
    doNothing().when(spyAnnotator).setNamedEntityTagGranularity(any(Annotation.class), anyString());
    doNothing().when(spyAnnotator).annotate(any(Annotation.class));
    doReturn(false).when(spyAnnotator).hasSpeakerAnnotations(any(Annotation.class));
    CoreMap mockMention = mock(CoreMap.class);
    ArrayList<CoreMap> mentionList = new ArrayList<CoreMap>();
    mentionList.add(mockMention);
    annotation.set(MentionsAnnotation.class, mentionList);
    CoreMap mockReferent = mock(CoreMap.class);
    when(mockReferent.get(EntityMentionIndexAnnotation.class)).thenReturn(1);
    doReturn(Optional.of(mockReferent)).when(spyAnnotator).findBestCoreferentEntityMention(mockMention, annotation);
    spyAnnotator.annotate(annotation);
    verify(mockMention).set(CanonicalEntityMentionIndexAnnotation.class, 1);
}

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<Class<? extends CoreAnnotation>>(Arrays.asList(CorefChainAnnotation.class, CanonicalEntityMentionIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    CorefMention mention1 = new CorefMention(0, 0, 0, 0, "mention1", null, null, 0, new IntTuple(3));
    IntTuple position1 = new IntTuple(3);
    position1.set(0, 1);
    position1.set(1, 1);
    position1.set(2, 1);
    mention1.position = position1;
    CorefMention mention2 = new CorefMention(0, 0, 0, 0, "mention2", null, null, 0, new IntTuple(3));
    IntTuple position2 = new IntTuple(3);
    position2.set(0, 1);
    position2.set(1, 1);
    position2.set(2, 2);
    mention2.position = position2;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention2);
    mentions.add(mention1);
    CorefChain corefChain = new CorefChain(1, mentions);
    Map<Integer, CorefChain> inputMap = new HashMap<>();
    inputMap.put(1, corefChain);
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(inputMap);
    assertEquals(1, links.size());
    Pair<IntTuple, IntTuple> link = links.get(0);
    assertArrayEquals(position1.elements(), link.first.elements());
    assertArrayEquals(position2.elements(), link.second.elements());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("John went to the store. He bought milk.");
    CoreMap sentence = mock(CoreMap.class);
    List<CoreMap> sentences = Arrays.asList(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    CoreMap mention1 = mock(CoreMap.class);
    CoreMap mention2 = mock(CoreMap.class);
    annotation.set(MentionsAnnotation.class, Arrays.asList(mention1, mention2));
    CoreMap bestCoreferent1 = mock(CoreMap.class);
    when(bestCoreferent1.get(EntityMentionIndexAnnotation.class)).thenReturn(1);
    CoreMap bestCoreferent2 = mock(CoreMap.class);
    when(bestCoreferent2.get(EntityMentionIndexAnnotation.class)).thenReturn(2);
    CorefAnnotator annotator = new CorefAnnotator(false, false);
    CorefAnnotator spyAnnotator = spy(annotator);
    doNothing().when(spyAnnotator).setNamedEntityTagGranularity(eq(annotation), anyString());
    doReturn(true).when(spyAnnotator).hasSpeakerAnnotations(annotation);
    MentionAnnotator mockMentionAnnotator = mock(MentionAnnotator.class);
    CorefSystem mockCorefSystem = mock(CorefSystem.class);
    spyAnnotator.mentionAnnotator = mockMentionAnnotator;
    spyAnnotator.corefSystem = mockCorefSystem;
    spyAnnotator.performMentionDetection = true;
    doReturn(Optional.of(bestCoreferent1)).when(spyAnnotator).findBestCoreferentEntityMention(mention1, annotation);
    doReturn(Optional.of(bestCoreferent2)).when(spyAnnotator).findBestCoreferentEntityMention(mention2, annotation);
    spyAnnotator.annotate(annotation);
    verify(mockMentionAnnotator).annotate(annotation);
    verify(mockCorefSystem).annotate(annotation);
    verify(mention1).set(eq(CanonicalEntityMentionIndexAnnotation.class), eq(1));
    verify(mention2).set(eq(CanonicalEntityMentionIndexAnnotation.class), eq(2));
}

