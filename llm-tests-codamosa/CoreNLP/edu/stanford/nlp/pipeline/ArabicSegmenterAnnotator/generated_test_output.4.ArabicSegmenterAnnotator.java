import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ArabicSegmenterAnnotator annotator = new ArabicSegmenterAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(TextAnnotation.class, TokensAnnotation.class, CharacterOffsetBeginAnnotation.class, CharacterOffsetEndAnnotation.class, BeforeAnnotation.class, AfterAnnotation.class, TokenBeginAnnotation.class, TokenEndAnnotation.class, PositionAnnotation.class, IndexAnnotation.class, OriginalTextAnnotation.class, ValueAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    CoreMap mockSentence1 = mock(CoreMap.class);
    CoreMap mockSentence2 = mock(CoreMap.class);
    List<CoreMap> mockSentences = Arrays.asList(mockSentence1, mockSentence2);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.get(SentencesAnnotation.class)).thenReturn(mockSentences);
    ArabicSegmenterAnnotator annotator = new ArabicSegmenterAnnotator() {
        @Override
        protected void doOneSentence(Object sentenceOrAnnotation) {
            assertTrue((sentenceOrAnnotation == mockSentence1) || (sentenceOrAnnotation == mockSentence2));
        }
    };
    annotator.annotate(mockAnnotation);
}

