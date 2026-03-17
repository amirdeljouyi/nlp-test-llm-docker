import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MorphaAnnotator annotator = new MorphaAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(LemmaAnnotation.class));
}

@Test
public void test2()
{
    CoreLabel token = new CoreLabel();
    token.set(TextAnnotation.class, "running");
    token.set(PartOfSpeechAnnotation.class, "VBG");
    List<CoreLabel> tokens = Collections.singletonList(token);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    Annotation annotation = new Annotation("Sample text");
    annotation.set(SentencesAnnotation.class, sentences);
    MorphaAnnotator annotator = new MorphaAnnotator();
    annotator.annotate(annotation);
    String lemma = token.get(LemmaAnnotation.class);
    assertNotNull("Lemma should be assigned", lemma);
    assertEquals("run", lemma);
}

