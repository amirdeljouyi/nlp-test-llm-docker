import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(CorefChainAnnotation.class, CanonicalEntityMentionIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
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
    CorefMention mention1 = new CorefMention();
    mention1.position = position1;
    CorefMention mention2 = new CorefMention();
    mention2.position = position2;
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    CorefChain chain = new CorefChain(1, Collections.<CorefMention>emptyList()) {
        @Override
        public List<CorefMention> getMentionsInTextualOrder() {
            return mentions;
        }
    };
    Map<Integer, CorefChain> corefResult = new HashMap<>();
    corefResult.put(1, chain);
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(corefResult);
    assertEquals(1, links.size());
    Pair<IntTuple, IntTuple> link = links.get(0);
    assertEquals(position2, link.first);
    assertEquals(position1, link.second);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("He met John. John is tall.");
    annotation.set(SentencesAnnotation.class, new ArrayList<CoreMap>());
    annotation.set(MentionsAnnotation.class, new ArrayList<CoreMap>());
    CoreMap mention1 = mock(CoreMap.class);
    CoreMap bestMention = mock(CoreMap.class);
    when(bestMention.get(EntityMentionIndexAnnotation.class)).thenReturn(1);
    List<CoreMap> mentions = new ArrayList<>();
    mentions.add(mention1);
    annotation.set(MentionsAnnotation.class, mentions);
    CorefAnnotator annotator = new CorefAnnotator();
    annotator.performMentionDetection = true;
    MentionAnnotator mockMentionAnnotator = mock(MentionAnnotator.class);
    CorefSystem mockCorefSystem = mock(CorefSystem.class);
    annotator.mentionAnnotator = mockMentionAnnotator;
    annotator.corefSystem = mockCorefSystem;
    CorefAnnotator spyAnnotator = spy(annotator);
    doReturn(Optional.of(bestMention)).when(spyAnnotator).findBestCoreferentEntityMention(mention1, annotation);
    spyAnnotator.annotate(annotation);
    verify(mockMentionAnnotator).annotate(annotation);
    verify(mockCorefSystem).annotate(annotation);
    verify(mention1).set(eq(CanonicalEntityMentionIndexAnnotation.class), eq(1));
}

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(CorefChainAnnotation.class, CanonicalEntityMentionIndexAnnotation.class));
    assertEquals(2, result.size());
    assertTrue(result.contains(CorefChainAnnotation.class));
    assertTrue(result.contains(CanonicalEntityMentionIndexAnnotation.class));
}

@Test
public void test2()
{
    CorefMention mention1 = new CorefMention();
    mention1.position = new IntTuple(3);
    mention1.position.set(0, 0);
    mention1.position.set(1, 1);
    mention1.position.set(2, 2);
    CorefMention mention2 = new CorefMention();
    mention2.position = new IntTuple(3);
    mention2.position.set(0, 0);
    mention2.position.set(1, 1);
    mention2.position.set(2, 3);
    List<CorefMention> mentions = new ArrayList<>();
    mentions.add(mention1);
    mentions.add(mention2);
    CorefChain chain = new CorefChain(1, new HashMap<>());
    try {
        Field field = CorefChain.class.getDeclaredField("mentionsInTextualOrder");
        field.setAccessible(true);
        field.set(chain, mentions);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    Map<Integer, CorefChain> corefResult = new HashMap<>();
    corefResult.put(1, chain);
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(corefResult);
    assertEquals(1, links.size());
    Pair<IntTuple, IntTuple> link = links.get(0);
    assertArrayEquals(new int[]{ 0, 1, 2 }, link.first.elements());
    assertArrayEquals(new int[]{ 0, 1, 3 }, link.second.elements());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Barack Obama was born in Hawaii. He was elected president.");
    List<CoreMap> sentences = new ArrayList<>();
    annotation.set(SentencesAnnotation.class, sentences);
    CoreMap mention1 = new CoreMapMock();
    CoreMap mention2 = new CoreMapMock();
    mention1.set(EntityMentionIndexAnnotation.class, 0);
    mention2.set(EntityMentionIndexAnnotation.class, 1);
    List<CoreMap> mentions = Arrays.asList(mention1, mention2);
    annotation.set(MentionsAnnotation.class, mentions);
    CorefAnnotator annotator = new CorefAnnotator(new Properties()) {
        {
            this.performMentionDetection = false;
            this.mentionAnnotator = new MentionAnnotator(new Properties());
            this.corefSystem = new CorefSystem() {
                @Override
                public void annotate(Annotation ann) {
                }
            };
        }

        @Override
        public Optional<CoreMap> findBestCoreferentEntityMention(CoreMap mention, Annotation ann) {
            return Optional.of(mention == mention1 ? mention2 : mention1);
        }
    };
    annotator.annotate(annotation);
    assertEquals(Integer.valueOf(1), mention1.get(CanonicalEntityMentionIndexAnnotation.class));
    assertEquals(Integer.valueOf(0), mention2.get(CanonicalEntityMentionIndexAnnotation.class));
}

