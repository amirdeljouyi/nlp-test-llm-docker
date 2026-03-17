import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Properties props = new Properties();
    props.setProperty("nThreads", "4");
    SentimentAnnotator annotator = new SentimentAnnotator(props);
    assertEquals(4, annotator.nThreads());
}

@Test
public void test2()
{
    Properties props = PropertiesUtils.asProperties("sentiment.model", "edu/stanford/nlp/models/sentiment/sentiment.ser.gz");
    SentimentAnnotator annotator = new SentimentAnnotator("sentiment", props);
    long expectedMaxTime = 0L;
    assertEquals(expectedMaxTime, annotator.maxTime());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Test");
    CoreMap sentence = mock(CoreMap.class);
    Tree binarizedTree = mock(Tree.class);
    Tree collapsedUnaryTree = mock(Tree.class);
    Tree mainTree = mock(Tree.class);
    RNNModel model = mock(RNNModel.class);
    SentimentAnnotator annotator = new SentimentAnnotator(model);
    SentimentAnnotator.TreeTransformer mockTransformer = mock(TreeTransformer.class);
    annotator.transformer = mockTransformer;
    when(sentence.get(BinarizedTreeAnnotation.class)).thenReturn(binarizedTree);
    when(mockTransformer.transformTree(binarizedTree)).thenReturn(collapsedUnaryTree);
    Tree treeNode1 = mock(Tree.class);
    Tree treeNode2 = mock(Tree.class);
    Iterator<Tree> collapsedUnaryIter = mock(Iterator.class);
    when(collapsedUnaryTree.iterator()).thenReturn(collapsedUnaryIter);
    when(collapsedUnaryIter.hasNext()).thenReturn(true, true, false);
    when(collapsedUnaryIter.next()).thenReturn(treeNode1, treeNode2);
    when(treeNode1.getSpan()).thenReturn(new IntPair(0, 1));
    when(treeNode2.getSpan()).thenReturn(new IntPair(1, 2));
    when(treeNode1.label()).thenReturn(new CoreLabel());
    when(treeNode2.label()).thenReturn(new CoreLabel());
    mockStaticSentimentUtilsGetPredictedClass(treeNode1, 2);
    mockStaticSentimentUtilsGetPredictedClass(treeNode2, 3);
    mockStaticSentimentUtilsSentimentString(2, "Positive");
    mockStaticSentimentUtilsSentimentString(3, "Very Positive");
    when(sentence.get(TreeAnnotation.class)).thenReturn(mainTree);
    when(((CoreLabel) (mainTree.label())).containsKey(SpanAnnotation.class)).thenReturn(false);
    Tree mainNode = mock(Tree.class);
    Iterator<Tree> mainTreeIter = mock(Iterator.class);
    when(mainTree.iterator()).thenReturn(mainTreeIter);
    when(mainTreeIter.hasNext()).thenReturn(false);
    annotator.doOneSentence(annotation, sentence);
    verify(sentence).set(eq(SentimentAnnotatedTree.class), eq(collapsedUnaryTree));
}

@Test
public void test4()
{
    SentimentAnnotator annotator = new SentimentAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertTrue(result.isEmpty());
    assertEquals(Collections.emptySet(), result);
}

@Test
public void test5()
{
    Properties props = new Properties();
    props.setProperty("sentiment.model", "edu/models/sentiment-model.ser.gz");
    props.setProperty("sentiment.nthreads", "4");
    props.setProperty("sentiment.maxtime", "1000");
    String expected = "sentiment.model:edu/models/sentiment-model.ser.gz" + ("sentiment.nthreads:4" + "sentiment.maxtime:1000");
    String actual = SentimentAnnotator.signature("sentiment", props);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    SentimentAnnotator annotator = new SentimentAnnotator("dummyPropPath");
    Annotation annotation = new Annotation("This is a test.");
    CoreMap sentence = new ArrayCoreMap();
    annotator.doOneFailedSentence(annotation, sentence);
}

