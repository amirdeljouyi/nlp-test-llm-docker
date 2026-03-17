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
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Annotation annotation = mock(Annotation.class);
    CoreMap sentence1 = mock(CoreMap.class);
    CoreMap sentence2 = mock(CoreMap.class);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence1);
    sentences.add(sentence2);
    when(annotation.get(SentencesAnnotation.class)).thenReturn(sentences);
    ChineseSegmenterAnnotator annotator = new ChineseSegmenterAnnotator("edu/stanford/nlp/models/segmenter/chinese/ctb.gz", false);
    ChineseSegmenterAnnotator spyAnnotator = spy(annotator);
    doNothing().when(spyAnnotator).doOneSentence(sentence1);
    doNothing().when(spyAnnotator).doOneSentence(sentence2);
    spyAnnotator.annotate(annotation);
    verify(spyAnnotator).doOneSentence(sentence1);
    verify(spyAnnotator).doOneSentence(sentence2);
}

