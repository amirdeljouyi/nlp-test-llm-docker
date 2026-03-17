import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    POSTaggerAnnotator annotator = new POSTaggerAnnotator("english-left3words-distsim.tagger");
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(PartOfSpeechAnnotation.class));
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("This is a test.");
    CoreMap mockSentence = new CoreMap() {
        private final Map<Class<?>, Object> map = new HashMap<>();

        @Override
        public <T> T get(Class<T> key) {
            return ((T) (map.get(key)));
        }

        @Override
        public <T> void set(Class<T> key, T value) {
            map.put(key, value);
        }

        @Override
        public <T> boolean containsKey(Class<T> key) {
            return map.containsKey(key);
        }

        @Override
        public Set<Class<?>> keySet() {
            return map.keySet();
        }
    };
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(mockSentence);
    annotation.set(SentencesAnnotation.class, sentences);
    POSTaggerAnnotator posTagger = new POSTaggerAnnotator("pos", false) {
        @Override
        protected void doOneSentence(CoreMap sentence) {
            sentence.set(PartOfSpeechAnnotation.class, "NN");
        }
    };
    posTagger.annotate(annotation);
    assertEquals("NN", annotation.get(SentencesAnnotation.class).get(0).get(PartOfSpeechAnnotation.class));
}

