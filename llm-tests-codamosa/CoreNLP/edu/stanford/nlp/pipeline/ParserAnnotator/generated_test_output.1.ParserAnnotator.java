import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ParserAnnotator parserAnnotator = new ParserAnnotator();
    Field nThreadsField = ParserAnnotator.class.getDeclaredField("nThreads");
    nThreadsField.setAccessible(true);
    nThreadsField.setInt(parserAnnotator, 4);
    int result = parserAnnotator.nThreads();
    assertEquals(4, result);
}

@Test
public void test2()
{
    ParserAnnotator parserAnnotator = new ParserAnnotator();
    Field field = ParserAnnotator.class.getDeclaredField("maxParseTime");
    field.setAccessible(true);
    long expectedTime = 5000L;
    field.setLong(parserAnnotator, expectedTime);
    long actualTime = parserAnnotator.maxTime();
    assertEquals(expectedTime, actualTime);
}

@Test
public void test3()
{
    ParserAnnotator annotator = new ParserAnnotator("englishPCFG", new Properties());
    Annotation annotation = new Annotation("Test");
    CoreMap sentence = mock(CoreMap.class);
    Tree mockTree = mock(Tree.class);
    List<Tree> treeList = new ArrayList<>();
    treeList.add(mockTree);
    CoreLabel token = new CoreLabel();
    List<CoreLabel> tokens = Arrays.asList(token);
    List<ParserConstraint> constraints = new ArrayList<>();
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    when(sentence.get(TreeAnnotation.class)).thenReturn(null);
    when(sentence.get(ConstraintAnnotation.class)).thenReturn(constraints);
    ParserAnnotator spyAnnotator = spy(annotator);
    doReturn(treeList).when(spyAnnotator).doOneSentence(constraints, tokens);
    doNothing().when(spyAnnotator).finishSentence(sentence, treeList);
    spyAnnotator.doOneSentence(annotation, sentence);
    verify(spyAnnotator).finishSentence(sentence, treeList);
    verify(spyAnnotator, never()).doOneFailedSentence(annotation, sentence);
}

@Test
public void test4()
{
    ParserAnnotator annotator = new ParserAnnotator("english", new Properties());
    try {
        Field buildGraphsField = ParserAnnotator.class.getDeclaredField("BUILD_GRAPHS");
        buildGraphsField.setAccessible(true);
        buildGraphsField.setBoolean(annotator, true);
        Field saveBinaryTreesField = ParserAnnotator.class.getDeclaredField("saveBinaryTrees");
        saveBinaryTreesField.setAccessible(true);
        saveBinaryTreesField.setBoolean(annotator, true);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    Set<Class<?>> expected = Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(PartOfSpeechAnnotation.class, TreeAnnotation.class, BinarizedTreeAnnotation.class, BasicDependenciesAnnotation.class, CollapsedDependenciesAnnotation.class, CollapsedCCProcessedDependenciesAnnotation.class, EnhancedDependenciesAnnotation.class, EnhancedPlusPlusDependenciesAnnotation.class, BeginIndexAnnotation.class, EndIndexAnnotation.class, CategoryAnnotation.class)));
    assertEquals(expected, annotator.requirementsSatisfied());
}

@Test
public void test5()
{
    String annotatorName = "parse";
    Properties props = new Properties();
    props.setProperty("parse.model", "edu/models/englishPCFG.ser.gz");
    props.setProperty("parse.debug", "true");
    props.setProperty("parse.flags", "-annotatePunct");
    props.setProperty("parse.maxlen", "120");
    props.setProperty("parse.maxheight", "30");
    props.setProperty("parse.treemap", "treeMapConf");
    props.setProperty("parse.maxtime", "60");
    props.setProperty("parse.originalDependencies", "true");
    props.setProperty("parse.buildgraphs", "false");
    props.setProperty("parse.nthreads", "4");
    props.setProperty("nthreads", "2");
    props.setProperty("parse.nosquash", "true");
    props.setProperty("parse.keepPunct", "false");
    props.setProperty("parse.extradependencies", "CCPROCESSED");
    props.setProperty("parse.binaryTrees", "true");
    String expected = "" + ((((((((((((("parse.model:edu/models/englishPCFG.ser.gz" + "parse.debug:true") + "parse.flags:-annotatePunct") + "parse.maxlen:120") + "parse.maxheight:30") + "parse.treemap:treeMapConf") + "parse.maxtime:60") + "parse.originalDependencies:true") + "parse.buildgraphs:false") + "parse.nthreads:4") + "parse.nosquash:true") + "parse.keepPunct:false") + "parse.extradependencies:ccprocessed") + "parse.binaryTrees:true");
    String result = ParserAnnotator.signature(annotatorName, props);
    assertEquals(expected, result);
}

@Test
public void test6()
{
    ParserAnnotator parserAnnotator = mock(ParserAnnotator.class);
    CoreMap sentence = mock(CoreMap.class);
    CoreLabel word1 = new CoreLabel();
    CoreLabel word2 = new CoreLabel();
    word1.setWord("test1");
    word1.setTag(null);
    word2.setWord("test2");
    word2.setTag("NN");
    List<CoreLabel> wordList = new ArrayList<>();
    wordList.add(word1);
    wordList.add(word2);
    when(sentence.get(TokensAnnotation.class)).thenReturn(wordList);
    ParserAnnotator realAnnotator = new ParserAnnotator("relation", null) {
        @Override
        protected void finishSentence(CoreMap sentence, List<Tree> trees) {
        }
    };
    realAnnotator.doOneFailedSentence(null, sentence);
    assertEquals("XX", word1.tag());
    assertEquals("NN", word2.tag());
}

