import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefAnnotator annotator = new CorefAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(CorefChainAnnotation.class));
    assertTrue(result.contains(CanonicalEntityMentionIndexAnnotation.class));
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
    CorefChain chain = new CorefChain(1, Collections.emptyMap()) {
        @Override
        public List<CorefMention> getMentionsInTextualOrder() {
            return mentions;
        }
    };
    Map<Integer, CorefChain> result = new HashMap<>();
    result.put(1, chain);
    List<Pair<IntTuple, IntTuple>> links = CorefAnnotator.getLinks(result);
    assertEquals(1, links.size());
    assertEquals(position2, links.get(0).second);
    assertEquals(position1, links.get(0).first);
}

