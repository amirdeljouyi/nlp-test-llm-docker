import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ParserAnnotator annotator = new ParserAnnotator("depparse", null);
    Field field = ParserAnnotator.class.getDeclaredField("nThreads");
    field.setAccessible(true);
    field.setInt(annotator, 3);
    int result = annotator.nThreads();
    assertEquals(3, result);
}

@Test
public void test2()
{
    ParserAnnotator annotator = new ParserAnnotator("englishPCFG", null);
    Field field = ParserAnnotator.class.getDeclaredField("maxParseTime");
    field.setAccessible(true);
    field.setLong(annotator, 5000L);
    long result = annotator.maxTime();
    assertEquals(5000L, result);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("");
    CoreMap sentence = mock(CoreMap.class);
    Tree mockTree = mock(Tree.class);
    when(mockTree.label()).thenReturn(() -> "NP");
    when(sentence.get(TreeAnnotation.class)).thenReturn(mockTree);
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token = new CoreLabel();
    token.setWord("test");
    tokens.add(token);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokens);
    when(sentence.get(ConstraintAnnotation.class)).thenReturn(Collections.emptyList());
    ParserAnnotator parserAnnotator = new ParserAnnotator("englishPCFG", false);
    parserAnnotator.noSquash = true;
    ParserAnnotator spyAnnotator = Mockito.spy(parserAnnotator);
    doReturn(Collections.singletonList(mock(Tree.class))).when(spyAnnotator).doOneSentence(anyList(), anyList());
    spyAnnotator.doOneSentence(annotation, sentence);
    verify(spyAnnotator, never()).doOneSentence(anyList(), anyList());
    verify(spyAnnotator, never()).doOneFailedSentence(any(), any());
    verify(spyAnnotator, never()).finishSentence(any(), any());
}

@Test
public void test4()
{
    ParserAnnotator parserAnnotator = new ParserAnnotator();
    Field buildGraphsField = ParserAnnotator.class.getDeclaredField("BUILD_GRAPHS");
    buildGraphsField.setAccessible(true);
    buildGraphsField.set(parserAnnotator, true);
    Field saveBinaryTreesField = ParserAnnotator.class.getDeclaredField("saveBinaryTrees");
    saveBinaryTreesField.setAccessible(true);
    saveBinaryTreesField.set(parserAnnotator, true);
    Set<Class<?>> expected = Collections.unmodifiableSet(new ArraySet<>(Arrays.asList(PartOfSpeechAnnotation.class, TreeAnnotation.class, BinarizedTreeAnnotation.class, BasicDependenciesAnnotation.class, CollapsedDependenciesAnnotation.class, CollapsedCCProcessedDependenciesAnnotation.class, EnhancedDependenciesAnnotation.class, EnhancedPlusPlusDependenciesAnnotation.class, BeginIndexAnnotation.class, EndIndexAnnotation.class, CategoryAnnotation.class)));
    assertEquals(expected, parserAnnotator.requirementsSatisfied());
}

@Test
public void test5()
{
    String annotatorName = "parse";
    Properties props = new Properties();
    props.setProperty("parse.model", "englishPCFG.ser.gz");
    props.setProperty("parse.debug", "true");
    props.setProperty("parse.flags", "-flagA -flagB");
    props.setProperty("parse.maxlen", "500");
    props.setProperty("parse.maxheight", "200");
    props.setProperty("parse.treemap", "true");
    props.setProperty("parse.maxtime", "60");
    props.setProperty("parse.originalDependencies", "true");
    props.setProperty("parse.buildgraphs", "false");
    props.setProperty("parse.nthreads", "4");
    props.setProperty("nthreads", "2");
    props.setProperty("parse.nosquash", "true");
    props.setProperty("parse.keepPunct", "false");
    props.setProperty("parse.extradependencies", "CCPROCESSED");
    props.setProperty("parse.binaryTrees", "true");
    String expected = "" + ((((((((((((("parse.model:englishPCFG.ser.gz" + "parse.debug:true") + "parse.flags:-flagA -flagB") + "parse.maxlen:500") + "parse.maxheight:200") + "parse.treemap:true") + "parse.maxtime:60") + "parse.originalDependencies:true") + "parse.buildgraphs:false") + "parse.nthreads:4") + "parse.nosquash:true") + "parse.keepPunct:false") + "parse.extradependencies:ccprocessed") + "parse.binaryTrees:true");
    String actual = ParserAnnotator.signature(annotatorName, props);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    CoreMap sentence = mock(CoreMap.class);
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Test");
    token1.setTag(null);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("case");
    token2.setTag("NN");
    List<CoreLabel> tokenList = new ArrayList<>();
    tokenList.add(token1);
    tokenList.add(token2);
    when(sentence.get(TokensAnnotation.class)).thenReturn(tokenList);
    ParserAnnotator parser = new ParserAnnotator() {
        @Override
        protected void finishSentence(CoreMap s, List<Tree> trees) {
            assertEquals(1, trees.size());
            Tree tree = trees.get(0);
            assertNotNull(tree);
            List<Tree> leaves = tree.getLeaves();
            assertEquals(2, leaves.size());
            assertEquals("Test", leaves.get(0).value());
            assertEquals("case", leaves.get(1).value());
        }
    };
    Annotation dummyAnnotation = new Annotation("text");
    parser.doOneFailedSentence(dummyAnnotation, sentence);
    assertEquals("XX", token1.tag());
    assertEquals("NN", token2.tag());
}


