import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ArabicSegmenterAnnotator annotator = new ArabicSegmenterAnnotator();
    Set<Class<? extends CoreAnnotations>> expected = new HashSet<>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class));
    Set<Class<? extends CoreAnnotations>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

