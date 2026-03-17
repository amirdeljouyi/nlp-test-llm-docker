import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ArabicSegmenterAnnotator annotator = new ArabicSegmenterAnnotator();
    Set<Class<? extends CoreAnnotations.CoreAnnotation>> expected = new HashSet<>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class));
    Set<Class<? extends CoreAnnotations.CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("هذا اختبار");
    ArabicSegmenterAnnotator annotator = new ArabicSegmenterAnnotator() {
        boolean called = false;

        @Override
        protected void doOneSentence(Object sentence) {
            called = sentence == annotation;
            assertTrue("Expected doOneSentence to be called with the annotation", called);
        }
    };
    annotation.set(SentencesAnnotation.class, null);
    annotator.annotate(annotation);
}


