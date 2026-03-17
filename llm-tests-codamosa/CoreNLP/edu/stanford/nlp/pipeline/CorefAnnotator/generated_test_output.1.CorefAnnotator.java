import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<Class<? extends CoreAnnotation>>(Arrays.asList(CorefChainAnnotation.class, CanonicalEntityMentionIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals("requirementsSatisfied should return the correct set of annotation classes", expected, actual);
}

@Test
public void test2()
{
    IntTuple position1 = new IntTuple(3);
    position1.set(0, 0);
    position1.set(1, 1);
    position1.set(2, 2);
    IntTuple position2 = new IntTuple(3);
    position2.set(0, 0);
    position2.set(1, 1);
    position2.set(2, 3);
    CorefMention mention1 = new CorefMention(0, 0, 0, "mention1", null);
    mention1.position = position1;
    CorefMention mention2 = new CorefMention(0, 0, 0, "mention2", null);
    mention2.position = position2;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    CorefChain corefChain = new CorefChain(1, mention1);
    Map<Integer, CorefChain> resultMap = new HashMap<Integer, CorefChain>() {
        {
            put(1, new CorefChain(1, mention1) {
                @Override
                public List<CorefMention> getMentionsInTextualOrder() {
                    return mentions;
                }
            });
        }
    };
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(resultMap);
    assertEquals(1, links.size());
    Pair<IntTuple, IntTuple> link = links.get(0);
    assertArrayEquals(new int[]{ 0, 1, 2 }, link.first.elements());
    assertArrayEquals(new int[]{ 0, 1, 3 }, link.second.elements());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Barack Obama was born in Hawaii. He was elected president in 2008.");
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(new DummyCoreMap());
    annotation.set(SentencesAnnotation.class, sentences);
    DummyCoreMap mention1 = new DummyCoreMap();
    mention1.set(EntityMentionIndexAnnotation.class, 0);
    DummyCoreMap mention2 = new DummyCoreMap();
    mention2.set(EntityMentionIndexAnnotation.class, 1);
    List<CoreMap> mentions = Arrays.asList(mention1, mention2);
    annotation.set(MentionsAnnotation.class, mentions);
    CorefAnnotator corefAnnotator = new CorefAnnotator(false, null, new DummyMentionAnnotator(), new DummyCorefSystem());
    corefAnnotator.annotate(annotation);
    assertNotNull(mention1.get(CanonicalEntityMentionIndexAnnotation.class));
    assertEquals(Integer.valueOf(1), mention1.get(CanonicalEntityMentionIndexAnnotation.class));
    assertNotNull(mention2.get(CanonicalEntityMentionIndexAnnotation.class));
    assertEquals(Integer.valueOf(1), mention2.get(CanonicalEntityMentionIndexAnnotation.class));
}

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator(null);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(CorefChainAnnotation.class));
    assertTrue(result.contains(CanonicalEntityMentionIndexAnnotation.class));
}

@Test
public void test2()
{
    IntTuple pos1 = new IntTuple(3);
    pos1.set(0, 0);
    pos1.set(1, 1);
    pos1.set(2, 1);
    IntTuple pos2 = new IntTuple(3);
    pos2.set(0, 0);
    pos2.set(1, 1);
    pos2.set(2, 2);
    CorefMention mention1 = new CorefMention(1, 0, 0, 1, 0, "mention1", null);
    mention1.position = pos1;
    CorefMention mention2 = new CorefMention(1, 0, 0, 2, 0, "mention2", null);
    mention2.position = pos2;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    CorefChain chain = new CorefChain(1, Collections.emptyMap()) {
        @Override
        public List<CorefMention> getMentionsInTextualOrder() {
            return mentions;
        }
    };
    Map<Integer, CorefChain> corefMap = new HashMap<>();
    corefMap.put(1, chain);
    List<Pair<IntTuple, IntTuple>> result = CorefAnnotator.getLinks(corefMap);
    assertEquals(1, result.size());
    Pair<IntTuple, IntTuple> link = result.get(0);
    assertArrayEquals(new int[]{ 0, 1, 1 }, link.first.elements());
    assertArrayEquals(new int[]{ 0, 1, 2 }, link.second.elements());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Barack Obama was born in Hawaii. He was elected president in 2008.");
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(mock(CoreMap.class));
    annotation.set(SentencesAnnotation.class, sentences);
    List<CoreMap> mentions = new ArrayList<>();
    CoreMap mention1 = mock(CoreMap.class);
    when(mention1.get(EntityMentionIndexAnnotation.class)).thenReturn(0);
    CoreMap mention2 = mock(CoreMap.class);
    when(mention2.get(EntityMentionIndexAnnotation.class)).thenReturn(1);
    mentions.add(mention1);
    mentions.add(mention2);
    annotation.set(MentionsAnnotation.class, mentions);
    CorefAnnotator annotator = spy(new CorefAnnotator());
    doNothing().when(annotator).corefSystem.annotate(annotation);
    doReturn(false).when(annotation).containsKey(UseMarkedDiscourseAnnotation.class);
    doNothing().when(annotator).setNamedEntityTagGranularity(annotation, "coarse");
    doNothing().when(annotator).setNamedEntityTagGranularity(annotation, "fine");
    doReturn(Optional.of(mention1)).when(annotator).findBestCoreferentEntityMention(mention2, annotation);
    doReturn(Optional.empty()).when(annotator).findBestCoreferentEntityMention(mention1, annotation);
    annotator.annotate(annotation);
    verify(mention2).set(eq(CanonicalEntityMentionIndexAnnotation.class), eq(mention1.get(EntityMentionIndexAnnotation.class)));
    verify(mention1, never()).set(eq(CanonicalEntityMentionIndexAnnotation.class), any());
}

