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
    assertNotNull(collinizer);
    assertTrue(collinizer instanceof TreeCollinizer);
    TreeCollinizer treeCollinizer = ((TreeCollinizer) (collinizer));
    assertEquals(params.tlp, treeCollinizer.treebankLanguagePack());
    assertTrue(treeCollinizer.forceCNF());
    assertEquals(params.englishTrain.splitBaseNP == 2, treeCollinizer.splitNPTMP());
    assertEquals(params.englishTrain.collapseWhCategories, treeCollinizer.collapseWH());
}

@Test
public void test3()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.englishTrain.splitBaseNP = 2;
    params.englishTrain.collapseWhCategories = true;
    AbstractCollinizer collinizer = params.collinizerEvalb();
    assertNotNull(collinizer);
    assertTrue(collinizer instanceof TreeCollinizer);
}

@Test
public void test4()
{
    Options options = new Options();
    options.lexOptions.uwModelTrainer = null;
    @SuppressWarnings("unchecked")
    Index<String> wordIndex = new Index<>();
    wordIndex.add("test");
    @SuppressWarnings("unchecked")
    Index<String> tagIndex = new Index<>();
    tagIndex.add("NN");
    EnglishTreebankParserParams parserParams = new EnglishTreebankParserParams();
    Lexicon lexicon = parserParams.lex(options, wordIndex, tagIndex);
    assertNotNull(lexicon);
    assertTrue(lexicon instanceof BaseLexicon);
    assertEquals("edu.stanford.nlp.parser.lexparser.EnglishUnknownWordModelTrainer", options.lexOptions.uwModelTrainer);
}

@Test
public void test5()
{
    EnglishTreebankParserParams parserParams = new EnglishTreebankParserParams();
    TreeReaderFactory expectedFactory = parserParams.treeReaderFactory();
    DiskTreebank diskTreebank = parserParams.diskTreebank();
    assertNotNull("DiskTreebank should not be null", diskTreebank);
    TreeReaderFactory actualFactory = diskTreebank.treeReaderFactory();
    assertNotNull("TreeReaderFactory inside DiskTreebank should not be null", actualFactory);
}

@Test
public void test6()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    try {
        Field field = EnglishTreebankParserParams.class.getDeclaredField("generateOriginalDependencies");
        field.setAccessible(true);
        field.setBoolean(params, true);
    } catch (Exception e) {
        fail("Failed to set generateOriginalDependencies via reflection: " + e.getMessage());
    }
    TreeFactory treeFactory = new LabeledScoredTreeFactory();
    Tree tree = treeFactory.newLeaf("TestLeaf");
    Predicate<String> filter = ( x) -> true;
    HeadFinder headFinder = new HeadFinder() {
        public Tree determineHead(Tree t) {
            return t;
        }

        public Tree determineHead(Tree t, Tree parent) {
            return t;
        }
    };
    GrammaticalStructure result = params.getGrammaticalStructure(tree, filter, headFinder);
    assertNotNull(result);
    assertTrue(result instanceof EnglishGrammaticalStructure);
}

@Test
public void test7()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    HeadFinder result = params.headFinder();
    assertNotNull("headFinder() should return a non-null HeadFinder", result);
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
    TreeReaderFactory expectedFactory = params.treeReaderFactory();
    MemoryTreebank memoryTreebank = params.memoryTreebank();
    Assert.assertNotNull(memoryTreebank);
}

@Test
public void test10()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    MemoryTreebank treebank = params.testMemoryTreebank();
    String sampleTree = "(ROOT (S (NP (DT The) (NN cat)) (VP (VBD sat) (PP (IN on) (NP (DT the) (NN mat)))) (. .)))";
    treebank.load(new StringReader(sampleTree));
    Tree firstTree = treebank.get(0);
    assertNotNull("Tree should not be null", firstTree);
    assertEquals("Root label should be ROOT", "ROOT", firstTree.label().value());
}

@Test
public void test11()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setInputEncoding("UTF-8");
    params.setOutputEncoding("UTF-8");
    params.setCorrectTags(true);
    CoreLabel wordLabel = new CoreLabel();
    wordLabel.setWord("a");
    wordLabel.setValue("IN");
    wordLabel.setTag("IN");
    TreeFactory tf = new LabeledScoredTreeFactory();
    Tree leaf = tf.newLeaf("a");
    Tree preTerminal = tf.newTreeNode(wordLabel, Collections.singletonList(leaf));
    CoreLabel parentLabel = new CoreLabel();
    parentLabel.setValue("NP");
    Tree parent = tf.newTreeNode(parentLabel, Collections.singletonList(preTerminal));
    leaf.setParent(preTerminal);
    preTerminal.setParent(parent);
    Tree transformed = params.transformTree(preTerminal, parent);
    Label resultLabel = transformed.label();
    assertEquals("DT", resultLabel.value());
}

@Test
public void test12()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreeReaderFactory factory = params.treeReaderFactory();
    String treeInput = "(ROOT (S (NP (DT The) (NN dog)) (VP (VBZ barks))))";
    TreeReader reader = factory.newTreeReader(new StringReader(treeInput));
    Tree tree = reader.read();
    assertNotNull("Tree should not be null", tree);
    assertEquals("ROOT", tree.label().value());
    assertEquals(2, tree.numChildren());
    Tree np = tree.getChild(0).getChild(0);
    assertEquals("NP", np.label().value());
    Tree vp = tree.getChild(0).getChild(1);
    assertEquals("VP", vp.label().value());
}

@Test
public void test13()
{
    EnglishTreebankParserParams parserParams = new EnglishTreebankParserParams();
    TreeTransformer transformer = parserParams.subcategoryStripper();
    Assert.assertNotNull(transformer);
    Assert.assertTrue(transformer instanceof EnglishTreebankParserParams.EnglishSubcategoryStripper);
}

@Test
public void test14()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreebankLanguagePack result = params.treebankLanguagePack();
    assertNotNull("Expected non-null TreebankLanguagePack instance", result);
    assertTrue("Expected result to be an instance of TreebankLanguagePack", result instanceof TreebankLanguagePack);
}

@Test
public void test15()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    String[] args = new String[]{ "-splitIN", "5" };
    int resultIndex = params.setOptionFlag(args, 0);
    assertEquals(2, resultIndex);
    assertEquals(5, params.englishTrain.splitIN);
}

@Test
public void test16()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    String[] expected = EnglishTreebankParserParams.RETAIN_TMP_ARGS;
    String[] actual = params.defaultCoreNLPFlags();
    assertNotNull("Returned array should not be null", actual);
    assertArrayEquals("Returned array should match RETAIN_TMP_ARGS", expected, actual);
}

@Test
public void test17()
{
    Options options = new Options();
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setOptionFlag("-sisterSplit", "2", options);
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
    File tempFile = File.createTempFile("testGrammars", ".conllx");
    FileWriter writer = new FileWriter(tempFile);
    writer.write("1\tThe\tthe\tDT\tDT\t_\t2\tdet\t_\t_\n");
    writer.write("2\tdog\tdog\tNN\tNN\t_\t3\tnsubj\t_\t_\n");
    writer.write("3\tran\trun\tVBD\tVBD\t_\t0\troot\t_\t_\n");
    writer.write("4\tfast\tfast\tRB\tRB\t_\t3\tadvmod\t_\t_\n\n");
    writer.close();
    EnglishTreebankParserParams parserParams = new EnglishTreebankParserParams();
    Field field = EnglishTreebankParserParams.class.getDeclaredField("generateOriginalDependencies");
    field.setAccessible(true);
    field.setBoolean(parserParams, true);
    List<GrammaticalStructure> structures = parserParams.readGrammaticalStructureFromFile(tempFile.getAbsolutePath());
    assertNotNull(structures);
    assertFalse(structures.isEmpty());
    assertNotNull(structures.get(0));
    tempFile.delete();
}

@Test
public void test20()
{
    File tempDir = Files.createTempDirectory("treebank").toFile();
    tempDir.deleteOnExit();
    File treeFile = new File(tempDir, "sample.tree");
    treeFile.deleteOnExit();
    FileWriter writer = new FileWriter(treeFile);
    writer.write("(S (NP (DT The) (NN cat)) (VP (VBD sat)))\n");
    writer.close();
    EnglishTreebankParserParams.main(new String[]{ tempDir.getAbsolutePath() });
}

@Test
public void test1()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    boolean result = params.supportsBasicDependencies();
    assertTrue("Expected supportsBasicDependencies() to return true", result);
}

@Test
public void test2()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.englishTrain.splitBaseNP = 2;
    params.englishTrain.collapseWhCategories = true;
    AbstractCollinizer collinizer = params.collinizer();
    Assert.assertNotNull(collinizer);
    Assert.assertTrue(collinizer instanceof TreeCollinizer);
}

@Test
public void test3()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    AbstractCollinizer collinizer = params.collinizerEvalb();
    assertNotNull(collinizer);
    assertTrue(collinizer instanceof TreeCollinizer);
    TreeCollinizer treeCollinizer = ((TreeCollinizer) (collinizer));
    assertEquals(params.tlp, treeCollinizer.tlp);
    assertTrue(treeCollinizer.removePunct);
    assertEquals(params.englishTrain.splitBaseNP == 2, treeCollinizer.splitBaseNP);
    assertEquals(params.englishTrain.collapseWhCategories, treeCollinizer.collapseWh);
}

@Test
public void test4()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    Options options = new Options();
    options.lexOptions.uwModelTrainer = null;
    Index<String> wordIndex = new Index<>();
    wordIndex.add("the");
    Index<String> tagIndex = new Index<>();
    tagIndex.add("DT");
    Lexicon lexicon = params.lex(options, wordIndex, tagIndex);
    assertEquals("edu.stanford.nlp.parser.lexparser.EnglishUnknownWordModelTrainer", options.lexOptions.uwModelTrainer);
    assertNotNull(lexicon);
    assertTrue(lexicon instanceof BaseLexicon);
}

@Test
public void test5()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    DiskTreebank treebank = params.diskTreebank();
    assertNotNull("diskTreebank() should not return null", treebank);
}

@Test
public void test6()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setGenerateOriginalDependencies(true);
    TreeFactory treeFactory = new LabeledScoredTreeFactory();
    Tree leaf1 = treeFactory.newLeaf("The");
    Tree leaf2 = treeFactory.newLeaf("cat");
    Tree np = treeFactory.newTreeNode("NP", Arrays.asList(leaf1, leaf2));
    Tree leaf3 = treeFactory.newLeaf("sleeps");
    Tree vp = treeFactory.newTreeNode("VP", Arrays.asList(leaf3));
    Tree root = treeFactory.newTreeNode("S", Arrays.asList(np, vp));
    Predicate<String> dummyFilter = ( s) -> true;
    HeadFinder headFinder = new PennTreebankLanguagePack().headFinder();
    GrammaticalStructure gs = params.getGrammaticalStructure(root, dummyFilter, headFinder);
}

@Test
public void test7()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    HeadFinder result = params.headFinder();
    assertNotNull("headFinder() should not return null", result);
    assertTrue("Returned object should be instance of HeadFinder", result instanceof HeadFinder);
}

@Test
public void test8()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.generateOriginalDependencies = true;
    params.englishTest = new EnglishTreebankParserParams.EnglishTest();
    params.englishTest.makeCopulaHead = false;
    HeadFinder headFinder = params.typedDependencyHeadFinder();
    assertTrue(headFinder instanceof SemanticHeadFinder);
}

@Test
public void test9()
{
    EnglishTreebankParserParams parserParams = new EnglishTreebankParserParams();
    MemoryTreebank memoryTreebank = parserParams.memoryTreebank();
    Assert.assertNotNull("MemoryTreebank should not be null", memoryTreebank);
    Assert.assertTrue("Returned object should be an instance of MemoryTreebank", memoryTreebank instanceof MemoryTreebank);
}

@Test
public void test10()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    Treebank tb = params.testMemoryTreebank();
    Assert.assertNotNull("MemoryTreebank should not be null", tb);
    Assert.assertTrue("Should be instance of MemoryTreebank", tb instanceof MemoryTreebank);
}

@Test
public void test11()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    params.setInputEncoding("UTF-8");
    params.setOutputEncoding("UTF-8");
    params.setOptionFlags(new String[]{ "-correctTags" });
    CoreLabel leafLabel = new CoreLabel();
    leafLabel.setWord("that");
    leafLabel.setTag("IN");
    leafLabel.setValue("IN");
    TreeFactory tf = new LabeledScoredTreeFactory();
    Tree leaf = tf.newLeaf("that");
    leaf.setLabel(leafLabel);
    Tree preTerminal = tf.newTreeNode("IN", Collections.singletonList(leaf));
    CoreLabel preTermLabel = new CoreLabel();
    preTermLabel.setWord("that");
    preTermLabel.setTag("IN");
    preTermLabel.setValue("IN");
    preTerminal.setLabel(preTermLabel);
    Tree parent = tf.newTreeNode("NP", Collections.singletonList(preTerminal));
    CoreLabel parentLabel = new CoreLabel();
    parentLabel.setValue("NP");
    parent.setLabel(parentLabel);
    Tree transformed = params.transformTree(preTerminal, parent);
    assertEquals("DT", transformed.label().value());
}

@Test
public void test12()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreeReaderFactory factory = params.treeReaderFactory();
    assertNotNull("TreeReaderFactory should not be null", factory);
    String sampleTree = "(ROOT (S (NP (DT The) (NN cat)) (VP (VBZ sits)) (. .)))";
    StringReader input = new StringReader(sampleTree);
    TreeReader reader = factory.newTreeReader(input);
    assertNotNull("TreeReader should not be null", reader);
    Tree tree = reader.readTree();
    assertNotNull("Parsed tree should not be null", tree);
    assertEquals("Tree label should be ROOT", "ROOT", tree.label().value());
}

@Test
public void test13()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreeTransformer transformer = params.subcategoryStripper();
    assertNotNull(transformer);
}

@Test
public void test14()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    TreebankLanguagePack languagePack = params.treebankLanguagePack();
    Assert.assertNotNull("treebankLanguagePack() should not return null", languagePack);
}

@Test
public void test15()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    String[] args = new String[]{ "-splitIN", "5" };
    int resultIndex = params.setOptionFlag(args, 0);
    assertEquals(5, params.englishTrain.splitIN);
    assertEquals(2, resultIndex);
}

@Test
public void test16()
{
    EnglishTreebankParserParams params = new EnglishTreebankParserParams();
    String[] expected = EnglishTreebankParserParams.RETAIN_TMP_ARGS;
    String[] actual = params.defaultCoreNLPFlags();
    assertNotNull("Returned array should not be null", actual);
    assertEquals("Array length should match", expected.length, actual.length);
    assertArrayEquals("Array contents should match RETAIN_TMP_ARGS", expected, actual);
}

@Test
public void test17()
{
    Options options = new Options();
    EnglishTreebankParserParams params = new EnglishTreebankParserParams(options);
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
    writer.write("3\truns\trun\tVBZ\tVBZ\t_\t0\troot\t_\t_\n\n");
    writer.close();
    EnglishTreebankParserParams parserParams = new EnglishTreebankParserParams();
    parserParams.setGenerateOriginalDependencies(true);
    List<GrammaticalStructure> result = parserParams.readGrammaticalStructureFromFile(tempFile.getAbsolutePath());
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertNotNull(result.get(0));
}


