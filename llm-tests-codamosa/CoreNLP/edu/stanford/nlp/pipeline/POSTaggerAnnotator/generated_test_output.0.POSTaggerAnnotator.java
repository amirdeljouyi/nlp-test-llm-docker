import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    POSTaggerAnnotator annotator = new POSTaggerAnnotator("english-left3words-distsim.tagger");
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = Collections.singleton(PartOfSpeechAnnotation.class);
    assertEquals(expected, result);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Test sentence.");
    CoreLabel token = new CoreLabel();
    token.setWord("Test");
    token.setValue("Test");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    POSTaggerAnnotator annotator = new POSTaggerAnnotator(false, "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
    annotator.nThreads = 1;
    annotator.annotate(annotation);
    List<CoreMap> resultSentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(resultSentences);
    assertEquals(1, resultSentences.size());
    CoreMap resultSentence = resultSentences.get(0);
    List<CoreLabel> resultTokens = resultSentence.get(TokensAnnotation.class);
    assertNotNull(resultTokens);
    assertEquals(1, resultTokens.size());
    assertNotNull(resultTokens.get(0).tag());
}

