import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SentimentAnnotator annotator = new SentimentAnnotator();
    Field field = SentimentAnnotator.class.getDeclaredField("nThreads");
    field.setAccessible(true);
    field.setInt(annotator, 4);
    int result = annotator.nThreads();
    assertEquals(4, result);
}

@Test
public void test2()
{
    SentimentAnnotator annotator = new SentimentAnnotator();
    Field maxTimeField = SentimentAnnotator.class.getDeclaredField("maxTime");
    maxTimeField.setAccessible(true);
    long expectedMaxTime = 123456789L;
    maxTimeField.setLong(annotator, expectedMaxTime);
    long actualMaxTime = annotator.maxTime();
    assertEquals(expectedMaxTime, actualMaxTime);
}

@Test
public void test3()
{
    SentimentAnnotator annotator = Mockito.spy(new SentimentAnnotator("edu/stanford/nlp/models/sentiment/sentiment.ser.gz", false));
    CoreMap sentence = Mockito.mock(CoreMap.class);
    Annotation annotation = new Annotation("");
    Tree binarizedTree = Mockito.mock(Tree.class);
    Tree collapsedTree = Mockito.mock(Tree.class);
    Tree rootTree = Mockito.mock(Tree.class);
    CoreLabel treeLabel = new CoreLabel();
    Tree treeNode = Mockito.mock(Tree.class);
    CoreLabel treeNodeLabel = new CoreLabel();
    IntPair span = new IntPair(0, 1);
    when(sentence.get(BinarizedTreeAnnotation.class)).thenReturn(binarizedTree);
    doReturn(collapsedTree).when(annotator.transformer).transformTree(binarizedTree);
    doNothing().when(collapsedTree).setSpans();
    when(sentence.get(TreeAnnotation.class)).thenReturn(rootTree);
    when(rootTree.label()).thenReturn(treeLabel);
    when(collapsedTree.iterator()).thenReturn(Collections.singletonList(treeNode).iterator());
    when(treeNode.getSpan()).thenReturn(span);
    when(rootTree.iterator()).thenReturn(Collections.singletonList(treeNode).iterator());
    when(treeNode.label()).thenReturn(treeNodeLabel);
    when(treeNode.getSpan()).thenReturn(span);
    when(treeLabel.containsKey(SpanAnnotation.class)).thenReturn(false);
    doNothing().when(rootTree).setSpans();
    when(treeNodeLabel.set(SentimentClass.class, "Positive")).thenReturn(null);
    doNothing().when(treeNodeLabel).remove(SpanAnnotation.class);
    when(RNNCoreAnnotations.getPredictedClass(collapsedTree)).thenReturn(3);
    when(RNNCoreAnnotations.getPredictedClass(treeNode)).thenReturn(3);
    when(SentimentUtils.sentimentString(any(), eq(3))).thenReturn("Positive");
    annotator.doOneSentence(annotation, sentence);
    verify(sentence).set(SentimentAnnotatedTree.class, collapsedTree);
    verify(sentence).set(SentimentClass.class, "Positive");
    verify(treeNodeLabel).set(SentimentClass.class, "Positive");
    verify(treeNodeLabel).remove(SpanAnnotation.class);
}

@Test
public void test4()
{
    SentimentAnnotator annotator = new SentimentAnnotator(null);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull("Returned set should not be null", result);
    assertTrue("Returned set should be empty", result.isEmpty());
}

@Test
public void test5()
{
    Properties props = new Properties();
    props.setProperty("sentiment.model", "edu/models/sentiment.ser.gz");
    props.setProperty("sentiment.nthreads", "4");
    props.setProperty("sentiment.maxtime", "1000");
    String result = SentimentAnnotator.signature("sentiment", props);
    String expected = "sentiment.model:edu/models/sentiment.ser.gz" + ("sentiment.nthreads:4" + "sentiment.maxtime:1000");
    assertEquals(expected, result);
}


