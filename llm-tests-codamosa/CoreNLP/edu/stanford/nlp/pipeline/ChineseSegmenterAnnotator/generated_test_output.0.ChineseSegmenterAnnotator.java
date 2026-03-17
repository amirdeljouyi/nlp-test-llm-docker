import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ChineseSegmenterAnnotator annotator = new ChineseSegmenterAnnotator(false, null);
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    ChineseSegmenterAnnotator annotator = new ChineseSegmenterAnnotator();
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.get(SentencesAnnotation.class)).thenReturn(null);
    ChineseSegmenterAnnotator spyAnnotator = spy(annotator);
    doNothing().when(spyAnnotator).doOneSentence(mockAnnotation);
    spyAnnotator.annotate(mockAnnotation);
    verify(spyAnnotator, times(1)).doOneSentence(mockAnnotation);
}

