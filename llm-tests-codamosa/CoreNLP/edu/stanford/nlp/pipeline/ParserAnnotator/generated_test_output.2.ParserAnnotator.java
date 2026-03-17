import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Properties props = new Properties();
    props.setProperty("parse.nthreads", "4");
    ParserAnnotator annotator = new ParserAnnotator("parse", props);
    assertEquals(4, annotator.nThreads());
}

@Test
public void test2()
{
    ParserAnnotator parserAnnotator = new ParserAnnotator("englishPCFG", null);
    Field field = ParserAnnotator.class.getDeclaredField("maxParseTime");
    field.setAccessible(true);
    long expectedMaxTime = 5000L;
    field.setLong(parserAnnotator, expectedMaxTime);
    long actualMaxTime = parserAnnotator.maxTime();
    assertEquals(expectedMaxTime, actualMaxTime);
}

@Test
public void test3()
{
    ParserAnnotator parserAnnotator = new ParserAnnotator();
    try {
        Field field = ParserAnnotator.class.getDeclaredField("noSquash");
        field.setAccessible(true);
        field.set(parserAnnotator, true);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    CoreMap sentence = mock(CoreMap.class);
    Tree tree = mock(Tree.class);
    Label label = mock(Label.class);
    when(label.value()).thenReturn("NP");
    when(tree.label()).thenReturn(label);
    when(sentence.get(TreeAnnotation.class)).thenReturn(tree);
    Annotation annotation = new Annotation("");
    parserAnnotator.doOneSentence(annotation, sentence);
    verify(sentence, never()).get(TokensAnnotation.class);
}

@Test
public void test4()
{
    ParserAnnotator annotator = new ParserAnnotator();
    Field buildGraphsField = ParserAnnotator.class.getDeclaredField("BUILD_GRAPHS");
    buildGraphsField.setAccessible(true);
    buildGraphsField.setBoolean(annotator, true);
    Field saveBinaryTreesField = ParserAnnotator.class.getDeclaredField("saveBinaryTrees");
    saveBinaryTreesField.setAccessible(true);
    saveBinaryTreesField.setBoolean(annotator, true);
    Set<Class<?>> expected = new HashSet<>(Arrays.asList(PartOfSpeechAnnotation.class, TreeAnnotation.class, BinarizedTreeAnnotation.class, BasicDependenciesAnnotation.class, CollapsedDependenciesAnnotation.class, CollapsedCCProcessedDependenciesAnnotation.class, EnhancedDependenciesAnnotation.class, EnhancedPlusPlusDependenciesAnnotation.class, BeginIndexAnnotation.class, EndIndexAnnotation.class, CategoryAnnotation.class));
    Set<Class<?>> actual = new HashSet<>(annotator.requirementsSatisfied());
    assertEquals(expected, actual);
}

@Test
public void test5()
{
    String annotatorName = "parse";
    Properties props = new Properties();
    String expected = ((((((((((((((("parse.model:" + LexicalizedParser.DEFAULT_PARSER_LOC) + "parse.debug:false") + "parse.flags:") + "parse.maxlen:-1") + "parse.maxheight:") + Integer.toString(DEFAULT_MAX_HEIGHT)) + "parse.treemap:") + "parse.maxtime:-1") + "parse.originalDependencies:false") + "parse.buildgraphs:true") + "parse.nthreads:") + "parse.nosquash:false") + "parse.keepPunct:true") + "parse.extradependencies:none") + "parse.binaryTrees:") + StanfordCoreNLP.usesBinaryTrees(props);
    String actual = ParserAnnotator.signature(annotatorName, props);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    Annotation annotation = new Annotation("Dummy text");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.setTag(null);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    token2.setTag("NN");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    @SuppressWarnings("unchecked")
    CoreMap sentence = mock(CoreMap.class);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    ParserAnnotator annotator = new ParserAnnotator("stanford", null) {
        @Override
        protected void finishSentence(CoreMap sent, List<Tree> trees) {
            assert trees != null;
            assert trees.size() == 1;
            assert trees.get(0) != null;
        }
    };
    annotator.doOneFailedSentence(annotation, sentence);
    assert "XX".equals(token1.tag());
    assert "NN".equals(token2.tag());
}

