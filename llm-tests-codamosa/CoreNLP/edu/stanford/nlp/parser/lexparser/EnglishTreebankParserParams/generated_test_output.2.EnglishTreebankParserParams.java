import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    boolean result = params.supportsBasicDependencies();
    assertTrue("supportsBasicDependencies should return true", result);
}

@Test
public void test2()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    AbstractCollinizer collinizer = params.collinizer();
    assertNotNull("Collinizer should not be null", collinizer);
    assertTrue("Collinizer should be instance of TreeCollinizer", collinizer instanceof TreeCollinizer);
}

@Test
public void test3()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    AbstractCollinizer collinizer = params.collinizerEvalb();
    assertNotNull("collinizerEvalb() should not return null", collinizer);
    assertTrue("collinizerEvalb() should return instance of TreeCollinizer", collinizer instanceof TreeCollinizer);
}

@Test
public void test4()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    Options options = new Options();
    options.lexOptions.uwModelTrainer = null;
    Index<String> wordIndex = new Index<>();
    wordIndex.add("dog");
    Index<String> tagIndex = new Index<>();
    tagIndex.add("NN");
    Lexicon lexicon = params.lex(options, wordIndex, tagIndex);
    assertEquals("edu.stanford.nlp.parser.lexparser.EnglishUnknownWordModelTrainer", options.lexOptions.uwModelTrainer);
    assertNotNull(lexicon);
    assertTrue(lexicon instanceof BaseLexicon);
}

@Test
public void test5()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    DiskTreebank diskTreebank = params.diskTreebank();
    assertNotNull(diskTreebank);
    TreeReaderFactory expectedFactory = params.treeReaderFactory();
}

@Test
public void test6()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setGenerateOriginalDependencies(true);
    TreeFactory treeFactory = new LabeledScoredTreeFactory();
    Tree tree = treeFactory.newLeaf("test");
    Predicate<String> filter = ( s) -> true;
    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    HeadFinder headFinder = tlp.headFinder();
    GrammaticalStructure gs = params.getGrammaticalStructure(tree, filter, headFinder);
}

@Test
public void test7()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    HeadFinder result = params.headFinder();
    assertNotNull("headFinder() should not return null", result);
    assertTrue("headFinder() should return an instance of HeadFinder", result instanceof HeadFinder);
}

@Test
public void test8()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.generateOriginalDependencies = true;
    params.englishTest.makeCopulaHead = false;
    HeadFinder headFinder = params.typedDependencyHeadFinder();
    assertTrue(headFinder instanceof SemanticHeadFinder);
}

@Test
public void test9()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    MemoryTreebank memoryTreebank = params.memoryTreebank();
    assertNotNull("memoryTreebank() should not return null", memoryTreebank);
    assertTrue("Returned object should be instance of MemoryTreebank", memoryTreebank instanceof MemoryTreebank);
}

@Test
public void test10()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    MemoryTreebank treebank = params.testMemoryTreebank();
    String treeString = "(ROOT (S (NP (DT The) (NN cat)) (VP (VBZ sleeps))))";
    treebank.load(new StringReader(treeString));
    Tree tree = treebank.get(0);
    assertNotNull("Parsed tree should not be null", tree);
    assertEquals("ROOT", tree.label().value());
    assertEquals(2, tree.numChildren());
}

@Test
public void test11()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setInputEncoding("UTF-8");
    params.setOutputEncoding("UTF-8");
    params.getTLPParams().setOptions("");
    CoreLabel leafLabel = new CoreLabel();
    leafLabel.setValue("a");
    leafLabel.setWord("a");
    leafLabel.setTag("IN");
    Tree leaf = new LabeledScoredTreeFactory().newLeaf(leafLabel);
    CoreLabel preterminalLabel = new CoreLabel();
    preterminalLabel.setValue("IN");
    preterminalLabel.setWord("a");
    preterminalLabel.setTag("IN");
    Tree preterminal = new LabeledScoredTreeFactory().newTreeNode(preterminalLabel, Collections.singletonList(leaf));
    CoreLabel parentLabel = new CoreLabel();
    parentLabel.setValue("NP");
    Tree parent = new LabeledScoredTreeFactory().newTreeNode(parentLabel, Collections.singletonList(preterminal));
    preterminal.setParent(parent);
    leaf.setParent(preterminal);
    params.setTraining(true);
    params.englishTrain.correctTags = true;
    Tree result = params.transformTree(preterminal, parent);
    assertEquals("DT", result.label().value());
    assertEquals("a", ((CoreLabel) (result.label())).word());
}

@Test
public void test12()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreeReaderFactory factory = params.treeReaderFactory();
    assertNotNull("TreeReaderFactory should not be null", factory);
    String treebankLine = "(ROOT (S (NP (DT The) (NN cat)) (VP (VBD sat) (PP (IN on) (NP (DT the) (NN mat)))) (. .)))";
    StringReader input = new StringReader(treebankLine);
    TreeReader reader = factory.newTreeReader(input);
    assertNotNull("TreeReader should not be null", reader);
    Tree tree = reader.readTree();
    assertNotNull("Parsed tree should not be null", tree);
    assertEquals("Root label should be ROOT", "ROOT", tree.label().value());
}

@Test
public void test13()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreeTransformer transformer = params.subcategoryStripper();
    assertNotNull("Returned transformer should not be null", transformer);
    assertTrue("Transformer should be instance of EnglishSubcategoryStripper", transformer instanceof EnglishSubcategoryStripper);
}

@Test
public void test14()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreebankLanguagePack tlp = params.treebankLanguagePack();
    assertNotNull("treebankLanguagePack() should not return null", tlp);
}

@Test
public void test15()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    String[] args = new String[]{ "-splitIN", "5" };
    int startIndex = 0;
    int returnedIndex = params.setOptionFlag(args, startIndex);
    assertEquals("Expected index to advance by 2 after processing '-splitIN'", 2, returnedIndex);
    assertEquals("Expected splitIN to be set to 5", 5, params.englishTrain.splitIN);
}

@Test
public void test16()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    String[] expected = EnglishTreebankParserParams.RETAIN_TMP_ARGS;
    String[] actual = params.defaultCoreNLPFlags();
    assertArrayEquals("defaultCoreNLPFlags() should return RETAIN_TMP_ARGS array", expected, actual);
}

@Test
public void test17()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setOptionFlag("sisterSplitLevel", "2", new Options.LexOptions());
    params.englishTrain.sisterSplitLevel = 2;
    String[] expected = EnglishTreebankParserParams.sisterSplit2;
    String[] actual = params.sisterSplitters();
    assertArrayEquals(expected, actual);
}

@Test
public void test18()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    List<Word> result = params.defaultTestSentence();
    Assert.assertEquals(6, result.size());
    Assert.assertEquals(new Word("This"), result.get(0));
    Assert.assertEquals(new Word("is"), result.get(1));
    Assert.assertEquals(new Word("just"), result.get(2));
    Assert.assertEquals(new Word("a"), result.get(3));
    Assert.assertEquals(new Word("test"), result.get(4));
    Assert.assertEquals(new Word("."), result.get(5));
}

@Test
public void test19()
{
    File tempFile = File.createTempFile("test", ".conllx");
    tempFile.deleteOnExit();
    FileWriter writer = new FileWriter(tempFile);
    writer.write("1\tThe\tthe\tDT\tDT\t_\t2\tdet\t_\t_\n");
    writer.write("2\tdog\tdog\tNN\tNN\t_\t3\tnsubj\t_\t_\n");
    writer.write("3\tran\tran\tVBD\tVBD\t_\t0\troot\t_\t_\n\n");
    writer.close();
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    try {
        Field field = EnglishTreebankParserParams.class.getDeclaredField("generateOriginalDependencies");
        field.setAccessible(true);
        field.setBoolean(params, false);
    } catch (Exception e) {
        throw new RuntimeException("Could not set generateOriginalDependencies field", e);
    }
    List<GrammaticalStructure> result = params.readGrammaticalStructureFromFile(tempFile.getAbsolutePath());
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertTrue(result.get(0) instanceof GrammaticalStructure);
}

@Test
public void test20()
{
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "treebank_test_dir");
    tempDir.mkdir();
    File treeFile = new File(tempDir, "sample.trees");
    PrintWriter writer = new PrintWriter(new FileWriter(treeFile));
    writer.println("(ROOT (S (NP (DT A) (NN cat)) (VP (VBZ sleeps)) (. .)))");
    writer.close();
    String[] args = new String[]{ tempDir.getAbsolutePath() };
    EnglishTreebankParserParams.main(args);
    treeFile.delete();
    tempDir.delete();
}

