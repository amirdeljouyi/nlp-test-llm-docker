import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ChineseSegmenterAnnotator annotator = new ChineseSegmenterAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertTrue(result.contains(TextAnnotation.class));
    assertTrue(result.contains(TokensAnnotation.class));
    assertTrue(result.contains(CharacterOffsetBeginAnnotation.class));
    assertTrue(result.contains(CharacterOffsetEndAnnotation.class));
    assertTrue(result.contains(BeforeAnnotation.class));
    assertTrue(result.contains(AfterAnnotation.class));
    assertTrue(result.contains(TokenBeginAnnotation.class));
    assertTrue(result.contains(TokenEndAnnotation.class));
    assertTrue(result.contains(PositionAnnotation.class));
    assertTrue(result.contains(IndexAnnotation.class));
    assertTrue(result.contains(OriginalTextAnnotation.class));
    assertTrue(result.contains(ValueAnnotation.class));
    assertEquals(12, result.size());
}

@Test
public void test2()
{
    Annotation mockAnnotation = mock(Annotation.class);
    CoreMap mockSentence = mock(CoreMap.class);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(mockSentence);
    when(mockAnnotation.get(SentencesAnnotation.class)).thenReturn(sentences);
    ChineseSegmenterAnnotator annotator = new ChineseSegmenterAnnotator() {
        @Override
        protected void doOneSentence(Object sentence) {
            if (sentence != mockSentence) {
                throw new AssertionError("doOneSentence called with unexpected argument");
            }
        }
    };
    annotator.annotate(mockAnnotation);
}

