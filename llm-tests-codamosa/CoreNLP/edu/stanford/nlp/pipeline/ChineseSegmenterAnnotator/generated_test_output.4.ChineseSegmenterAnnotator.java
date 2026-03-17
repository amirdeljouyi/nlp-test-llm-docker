import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ChineseSegmenterAnnotator annotator = new ChineseSegmenterAnnotator();
    Set<Class<? extends CoreAnnotations>> expected = new HashSet<>();
    expected.add(TextAnnotation.class);
    expected.add(TokensAnnotation.class);
    expected.add(CharacterOffsetBeginAnnotation.class);
    expected.add(CharacterOffsetEndAnnotation.class);
    expected.add(BeforeAnnotation.class);
    expected.add(AfterAnnotation.class);
    expected.add(TokenBeginAnnotation.class);
    expected.add(TokenEndAnnotation.class);
    expected.add(PositionAnnotation.class);
    expected.add(IndexAnnotation.class);
    expected.add(OriginalTextAnnotation.class);
    expected.add(ValueAnnotation.class);
    Set<Class<? extends CoreAnnotations>> actual = annotator.requirementsSatisfied();
    Assert.assertEquals(expected, actual);
}

@Test
public void test2()
{
    Annotation mockAnnotation = mock(Annotation.class);
    CoreMap mockSentence = mock(CoreMap.class);
    when(mockAnnotation.get(SentencesAnnotation.class)).thenReturn(Collections.singletonList(mockSentence));
    ChineseSegmenterAnnotator annotator = spy(new ChineseSegmenterAnnotator("segment"));
    doNothing().when(annotator).doOneSentence(mockSentence);
    annotator.annotate(mockAnnotation);
    verify(annotator, times(1)).doOneSentence(mockSentence);
    verify(annotator, never()).doOneSentence(mockAnnotation);
}

