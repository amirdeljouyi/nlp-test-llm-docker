import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ArabicSegmenterAnnotator annotator = new ArabicSegmenterAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals("The requirementsSatisfied() set should match the expected annotations", expected, actual);
}

@Test
public void test2()
{
    Annotation mockAnnotation = mock(Annotation.class);
    CoreMap mockSentence = mock(CoreMap.class);
    List<CoreMap> mockSentences = new ArrayList<>();
    mockSentences.add(mockSentence);
    when(mockAnnotation.get(SentencesAnnotation.class)).thenReturn(mockSentences);
    ArabicSegmenterAnnotator annotator = spy(new ArabicSegmenterAnnotator());
    doNothing().when(annotator).doOneSentence(mockSentence);
    annotator.annotate(mockAnnotation);
    verify(annotator).doOneSentence(mockSentence);
    verify(annotator, never()).doOneSentence(mockAnnotation);
}

