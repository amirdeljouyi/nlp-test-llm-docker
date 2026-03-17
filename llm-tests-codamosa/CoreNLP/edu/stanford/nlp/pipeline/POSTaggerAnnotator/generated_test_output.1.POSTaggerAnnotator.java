import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    POSTaggerAnnotator posTaggerAnnotator = new POSTaggerAnnotator("english-left3words-distsim.tagger");
    Set<Class<? extends CoreAnnotation>> requirements = posTaggerAnnotator.requirementsSatisfied();
    assertNotNull(requirements);
    assertEquals(1, requirements.size());
    assertTrue(requirements.contains(PartOfSpeechAnnotation.class));
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("");
    CoreMap mockSentence = new CoreMap() {};
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(mockSentence);
    annotation.set(SentencesAnnotation.class, sentences);
    POSTaggerAnnotator posTaggerAnnotator = new POSTaggerAnnotator("pos.model", false) {
        {
            this.nThreads = 1;
        }

        @Override
        protected void doOneSentence(CoreMap sentence) {
            sentence.set(PartOfSpeechAnnotation.class, "mockPOS");
        }
    };
    posTaggerAnnotator.annotate(annotation);
    assertEquals("mockPOS", annotation.get(SentencesAnnotation.class).get(0).get(PartOfSpeechAnnotation.class));
}


