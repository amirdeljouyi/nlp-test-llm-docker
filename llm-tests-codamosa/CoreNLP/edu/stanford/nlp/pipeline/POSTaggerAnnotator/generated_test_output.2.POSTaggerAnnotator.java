import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    POSTaggerAnnotator posTaggerAnnotator = new POSTaggerAnnotator("english-left3words-distsim.tagger");
    Set<Class<? extends CoreAnnotation>> result = posTaggerAnnotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(PartOfSpeechAnnotation.class));
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("The quick brown fox jumps over the lazy dog.");
    CoreMap sentence = new CoreMap() {};
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    POSTaggerAnnotator posTagger = new POSTaggerAnnotator("pos.model", false) {
        @Override
        protected void doOneSentence(CoreMap sentence) {
            sentence.set(TokensAnnotation.class, Collections.emptyList());
        }
    };
    posTagger.annotate(annotation);
    List<CoreMap> resultSentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(resultSentences);
    assertEquals(1, resultSentences.size());
    assertNotNull(resultSentences.get(0).get(TokensAnnotation.class));
}

