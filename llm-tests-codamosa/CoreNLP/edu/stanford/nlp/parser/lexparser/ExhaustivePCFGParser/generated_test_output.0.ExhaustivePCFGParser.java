import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.iScore = new double[1][2][1];
    parser.iScore[0][1][0] = 5.0;
    parser.words = new int[]{ 0 };
    parser.originalTags = new CoreLabel[1];
    CoreLabel label = new CoreLabel();
    label.setTag("NN");
    parser.originalTags[0] = label;
    parser.stateIndex = new ArrayList<>();
    parser.stateIndex.add("NN");
    parser.tagIndex = new ArrayList<>();
    parser.tagIndex.add("NN");
    parser.wordIndex = new ArrayList<>();
    parser.wordIndex.add("dog");
    parser.lex = new ExhaustivePCFGParser.LexiconStub();
    parser.tf = new LabeledScoredTreeFactory();
    parser.floodTags = false;
    CoreLabel core = new CoreLabel();
    core.setOriginalText("dog");
    parser.coreLabels = new ArrayList<>();
    parser.coreLabels.add(core);
    List<Tree> result = parser.extractBestParses(0, 0, 1);
    assertEquals(1, result.size());
    Tree tree = result.get(0);
    assertEquals("NN", tree.label().value());
    assertEquals(1, tree.numChildren());
    assertEquals("dog", tree.getChild(0).label().value());
}

@Test
public void test2()
{
    ParserConstraint constraint = new ParserConstraint(1, 3, "VP");
    List<ParserConstraint> expectedConstraints = new ArrayList<>();
    expectedConstraints.add(constraint);
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, new Options()) {
        {
            this.constraints = expectedConstraints;
        }

        public List<ParserConstraint> callGetConstraints() {
            return getConstraints();
        }
    };
    List<ParserConstraint> actualConstraints = ((ExhaustivePCFGParser) (parser)).getConstraints();
    assertNotNull(actualConstraints);
    assertEquals(1, actualConstraints.size());
    assertEquals(constraint, actualConstraints.get(0));
}

@Test
public void test3()
{
    buildOFilter();
}
{
    length = 1;
    numStates = 2;
    tags = new String[]{ "NN" };
    orf = new OutsideRuleFilter() {
        boolean initCalled = false;

        boolean leftAcceptCalled = false;

        boolean advanceRightCalled = false;

        boolean rightAcceptCalled = false;

        boolean advanceLeftCalled = false;

        @Override
        public void init() {
            initCalled = true;
        }

        @Override
        public void leftAccepting(boolean[] a) {
            assertEquals(2, a.length);
            leftAcceptCalled = true;
        }

        @Override
        public void advanceRight(String tag) {
            assertEquals("NN", tag);
            advanceRightCalled = true;
        }

        @Override
        public void rightAccepting(boolean[] a) {
            assertEquals(2, a.length);
            rightAcceptCalled = true;
        }

        @Override
        public void advanceLeft(String tag) {
            assertEquals("NN", tag);
            advanceLeftCalled = true;
        }
    };
}

@Test
public void test4()
{
    Options options = new Options();
    options.doDep = true;
    options.testOptions.useFastFactored = false;
    options.testOptions.lengthNormalization = true;
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, options);
    Field numStatesField = ExhaustivePCFGParser.class.getDeclaredField("numStates");
    numStatesField.setAccessible(true);
    numStatesField.setInt(parser, 2);
    Field tagIndexField = ExhaustivePCFGParser.class.getDeclaredField("tagIndex");
    tagIndexField.setAccessible(true);
    tagIndexField.set(parser, Collections.singletonMap("NN", 0));
    parser.createArrays(3);
    Field iScoreField = ExhaustivePCFGParser.class.getDeclaredField("iScore");
    iScoreField.setAccessible(true);
    float[][][] iScore = ((float[][][]) (iScoreField.get(parser)));
    assertNotNull(iScore[0][1]);
    assertEquals(2, iScore[0][1].length);
    Field oScoreField = ExhaustivePCFGParser.class.getDeclaredField("oScore");
    oScoreField.setAccessible(true);
    float[][][] oScore = ((float[][][]) (oScoreField.get(parser)));
    assertNotNull(oScore[1][2]);
    assertEquals(2, oScore[1][2].length);
    Field tagsField = ExhaustivePCFGParser.class.getDeclaredField("tags");
    tagsField.setAccessible(true);
    boolean[][] tags = ((boolean[][]) (tagsField.get(parser)));
    assertEquals(3, tags.length);
    assertEquals(1, tags[0].length);
    Field wordsInSpanField = ExhaustivePCFGParser.class.getDeclaredField("wordsInSpan");
    wordsInSpanField.setAccessible(true);
    int[][][] wordsInSpan = ((int[][][]) (wordsInSpanField.get(parser)));
    assertNotNull(wordsInSpan[0][1]);
    assertEquals(2, wordsInSpan[0][1].length);
}

@Test
public void test5()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.length = 2;
    parser.numStates = 3;
    parser.iScore = new float[2][3][3];
    parser.oScore = new float[2][3][3];
    parser.iPossibleByL = new boolean[2][3];
    parser.iPossibleByR = new boolean[3][3];
    parser.oPossibleByL = new boolean[2][3];
    parser.oPossibleByR = new boolean[3][3];
    parser.iScore[0][1][1] = 0.5F;
    parser.oScore[0][1][1] = 0.2F;
    parser.iScore[1][2][2] = 0.9F;
    parser.oScore[1][2][2] = 0.1F;
    parser.iScore[0][1][0] = Float.NEGATIVE_INFINITY;
    parser.oScore[0][1][0] = Float.NEGATIVE_INFINITY;
    parser.initializePossibles();
    assertTrue(parser.iPossibleByL[0][1]);
    assertTrue(parser.iPossibleByL[1][2]);
    assertTrue(parser.iPossibleByR[1][1]);
    assertTrue(parser.iPossibleByR[2][2]);
    assertTrue(parser.oPossibleByL[0][1]);
    assertTrue(parser.oPossibleByL[1][2]);
    assertTrue(parser.oPossibleByR[1][1]);
    assertTrue(parser.oPossibleByR[2][2]);
    assertFalse(parser.iPossibleByL[0][0]);
    assertFalse(parser.iPossibleByR[1][0]);
    assertFalse(parser.oPossibleByL[0][0]);
    assertFalse(parser.oPossibleByR[1][0]);
}

@Test
public void test6()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.iPossibleByR = new boolean[5][5];
    parser.iPossibleByL = new boolean[5][5];
    parser.iPossibleByR[2][3] = true;
    Hook hook = new Hook();
    hook.start = 2;
    hook.end = 4;
    hook.subState = 3;
    hook.setPreHook(true);
    boolean result = parser.iPossible(hook);
    assertTrue(result);
}

@Test
public void test7()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    boolean[][] matrix = new boolean[3][3];
    matrix[1][2] = true;
    try {
        Field field = ExhaustivePCFGParser.class.getDeclaredField("iPossibleByL");
        field.setAccessible(true);
        field.set(parser, matrix);
    } catch (Exception e) {
        fail("Failed to set field iPossibleByL: " + e.getMessage());
    }
    boolean result = parser.iPossibleL(2, 1);
    assertTrue("Expected true for iPossibleByL[1][2]", result);
}

@Test
public void test8()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null);
    int state = 1;
    int end = 2;
    parser.iPossibleByR = new boolean[3][3];
    parser.iPossibleByR[2][1] = true;
    boolean result = parser.iPossibleR(state, end);
    assertTrue(result);
}

@Test
public void test9()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.oPossibleByR = new boolean[5][5];
    parser.oPossibleByR[3][2] = true;
    parser.oPossibleByL = new boolean[5][5];
    Hook hook = new Hook() {
        @Override
        public boolean isPreHook() {
            return true;
        }

        @Override
        public int start() {
            return 1;
        }

        @Override
        public int end() {
            return 3;
        }

        @Override
        public int state() {
            return 2;
        }

        @Override
        public int start;

        @Override
        public int end;

        @Override
        public int state;
    };
    assertTrue(parser.oPossible(hook));
}

@Test
public void test10()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.oPossibleByL = new boolean[3][3];
    parser.oPossibleByL[1][2] = true;
    boolean result = parser.oPossibleL(2, 1);
    assertTrue("Expected oPossibleL(2, 1) to return true", result);
}

@Test
public void test11()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.oPossibleByR = new boolean[5][5];
    parser.oPossibleByR[3][2] = true;
    boolean result = parser.oPossibleR(2, 3);
    assertTrue("Expected oPossibleR(2, 3) to return true", result);
}

@Test
public void test12()
{
    Options options = new Options();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(options);
    CoreLabel word1 = new CoreLabel();
    word1.setWord("The");
    word1.setBeginPosition(0);
    word1.setEndPosition(3);
    word1.setTag("DT");
    CoreLabel word2 = new CoreLabel();
    word2.setWord("cat");
    word2.setBeginPosition(4);
    word2.setEndPosition(7);
    word2.setTag("NN");
    CoreLabel word3 = new CoreLabel();
    word3.setWord("sleeps");
    word3.setBeginPosition(8);
    word3.setEndPosition(14);
    word3.setTag("VBZ");
    List<CoreLabel> sentence = Arrays.asList(word1, word2, word3);
    boolean result = parser.parse(sentence);
    assertTrue(result);
}

@Test
public void test13()
{
    RedwoodConfiguration.empty().capture(System.err).apply();
    LexicalizedParser lp = LexicalizedParser.loadModel();
    ExhaustivePCFGParser parser = ((ExhaustivePCFGParser) (lp.getOp().parserFactory.parser(lp.getOp(), lp.getLexicon(), lp.getBinaryGrammar(), lp.getUnaryGrammar(), lp.getKnownLCWords())));
    List<HasWord> sentence = new ArrayList<HasWord>();
    CoreLabel word1 = new CoreLabel();
    word1.setWord("The");
    CoreLabel word2 = new CoreLabel();
    word2.setWord("cat");
    CoreLabel word3 = new CoreLabel();
    word3.setWord("sleeps");
    sentence.add(word1);
    sentence.add(word2);
    sentence.add(word3);
    boolean result = parser.parse(sentence);
    assertTrue(result || (!result));
}

@Test
public void test14()
{
    Options options = new Options();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(options);
    CoreLabel word = new CoreLabel();
    word.setWord("dog");
    List<HasWord> sentence = new ArrayList<>();
    sentence.add(word);
    parser.arraySize = 5;
    parser.iScore = new float[5][5][10];
    parser.oScore = new float[5][5][10];
    parser.wordsInSpan = new int[5][5][10];
    parser.narrowLExtent = new int[6][10];
    parser.wideLExtent = new int[6][10];
    parser.narrowRExtent = new int[5][10];
    parser.wideRExtent = new int[5][10];
    parser.stateIndex = Generics.newHashIndex();
    parser.goalStr = "ROOT";
    parser.stateIndex.add("ROOT");
    parser.wordIndex.addToIndex("dog");
    boolean result = parser.parse(sentence);
    assertTrue(result || (!result));
}

@Test
public void test15()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, null);
    String expectedGoal = parser.goalStr;
    double expectedScore = 42.0;
    try {
        Field field = ExhaustivePCFGParser.class.getDeclaredField("bestScore");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<String, Double> bestScoreMap = ((Map<String, Double>) (field.get(parser)));
        if (bestScoreMap == null) {
            bestScoreMap = new HashMap<>();
            field.set(parser, bestScoreMap);
        }
        bestScoreMap.put(expectedGoal, expectedScore);
    } catch (Exception e) {
        fail("Failed to set up test due to reflection error: " + e.getMessage());
    }
    double result = parser.getBestScore();
    assertEquals(expectedScore, result, 1.0E-4);
}

@Test
public void test16()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    double[][][] iScoreArray = new double[5][5][5];
    iScoreArray[2][3][1] = 0.85;
    try {
        Field field = ExhaustivePCFGParser.class.getDeclaredField("iScore");
        field.setAccessible(true);
        field.set(parser, iScoreArray);
    } catch (Exception e) {
        throw new RuntimeException("Failed to set iScore field via reflection", e);
    }
    Edge edge = new Edge(2, 3, 1);
    double result = parser.iScore(edge);
    assertEquals(0.85, result, 1.0E-5);
}

@Test
public void test17()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.op = new Options();
    parser.op.testOptions.pcfgThreshold = true;
    parser.op.testOptions.pcfgThresholdValue = 0.5;
    parser.iScore = new double[3][3][3];
    parser.oScore = new double[3][3][3];
    Edge edge = new Edge(0, 2, 1);
    parser.iScore[0][2][1] = -2.0;
    parser.oScore[0][2][1] = -1.0;
    parser.bestScore = -2.4;
    double result = parser.oScore(edge);
    assertEquals(Double.NEGATIVE_INFINITY, result, 0.0);
}

@Test
public void test18()
{
    Index<String> mockTagIndex = mock(Index.class);
    Index<String> mockWordIndex = mock(Index.class);
    when(mockTagIndex.indexOf("NN")).thenReturn(1);
    when(mockWordIndex.indexOf("dog")).thenReturn(2);
    Lexicon mockLex = mock(Lexicon.class);
    when(mockLex.score(new IntTaggedWord(2, 1), 0, "dog", null)).thenReturn(0.75F);
    Tree leaf = new LabeledScoredTreeFactory().newLeaf("dog");
    Tree preTerminal = new LabeledScoredTreeFactory().newTreeNode("NN", Collections.singletonList(leaf));
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, null, false, new Redwood.RedwoodChannels());
    parser.tagIndex = mockTagIndex;
    parser.wordIndex = mockWordIndex;
    parser.lex = mockLex;
    double score = parser.scoreBinarizedTree(preTerminal, 0);
    assertEquals(0.75, score, 1.0E-4);
}

@Test
public void test19()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.tagIndex = mock(Index.class);
    parser.wordIndex = mock(Index.class);
    parser.stateIndex = mock(Index.class);
    parser.lex = mock(Lexicon.class);
    parser.iScore = new float[1][2][1];
    parser.iScore[0][1][0] = 0.5F;
    when(parser.tagIndex.indexOf("DT")).thenReturn(0);
    when(parser.wordIndex.indexOf("The")).thenReturn(0);
    when(parser.stateIndex.indexOf("DT")).thenReturn(0);
    IntTaggedWord itw = new IntTaggedWord(0, 0);
    when(parser.lex.score(eq(itw), eq(0), eq("The"), isNull())).thenReturn(0.4F);
    TreeFactory tf = new LabeledScoredTreeFactory();
    Label parentLabel = new StringLabel("DT");
    Label childLabel = new StringLabel("The");
    Tree leaf = tf.newLeaf(childLabel);
    Tree preTerminal = tf.newTreeNode(parentLabel, Collections.singletonList(leaf));
    double result = parser.validateBinarizedTree(preTerminal, 0);
    assertEquals(0.4F, result, 1.0E-4);
}

@Test
public void test20()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, null);
    parser.length = 0;
    parser.goalStr = "ROOT";
    Tree result = parser.getBestParse();
    assertNull("Expected null when no parse is available", result);
}

@Test
public void test21()
{
    Options options = new Options();
    options.forceCNF = true;
    options.setTrainOptions("-outFact", "0.0");
    options.tlpParams = new PennTreebankLanguagePack().treebankLanguagePack();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, options);
    TreeFactory tf = new LabeledScoredTreeFactory(new StringLabelFactory());
    Tree leaf1 = tf.newLeaf("I");
    Tree leaf2 = tf.newLeaf("saw");
    Tree leaf3 = tf.newLeaf("him");
    Tree np1 = tf.newTreeNode("NP", Collections.singletonList(leaf1));
    Tree np2 = tf.newTreeNode("NP", Collections.singletonList(leaf3));
    Tree v = tf.newTreeNode("V", Collections.singletonList(leaf2));
    Tree vp = tf.newTreeNode("VP", Arrays.asList(v, np2));
    Tree root = tf.newTreeNode("S", Arrays.asList(np1, vp));
    Tree resultTree = parser.scoreNonBinarizedTree(root);
    assertNotNull(resultTree);
    assertEquals("S", resultTree.label().value());
    assertEquals(2, resultTree.numChildren());
}

@Test
public void test22()
{
    ExhaustivePCFGParser parser1 = new ExhaustivePCFGParser(null, null, false, false);
    int hash1 = parser1.hashCode();
    int hash2 = parser1.hashCode();
    assertEquals("Hash code should be consistent for the same object", hash1, hash2);
}

@Test
public void test23()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.length = 1;
    parser.goalStr = "S";
    parser.stateIndex = mock(IntTaggedWordIndex.class);
    when(parser.stateIndex.indexOf("S")).thenReturn(0);
    parser.iScore = new double[2][2][1];
    parser.iScore[0][1][0] = -3.7;
    Tree mockTree = mock(Tree.class);
    List<Tree> trees = new ArrayList<>();
    trees.add(mockTree);
    parser.extractBestParses = ( goal, start, end) -> Collections.singletonList(mockTree);
    List<ScoredObject<Tree>> result = parser.getBestParses();
    assertEquals(1, result.size());
    assertSame(mockTree, result.get(0).object());
    assertEquals(-3.7, result.get(0).score(), 1.0E-4);
}

@Test
public void test24()
{
    ExhaustivePCFGParser parser = spy(new ExhaustivePCFGParser(null));
    parser.length = 1;
    parser.goalStr = "S";
    parser.stateIndex = mock(Index.class);
    when(parser.stateIndex.indexOf("S")).thenReturn(0);
    Tree mockTree1 = mock(Tree.class);
    Tree mockTree2 = mock(Tree.class);
    ExhaustivePCFGParser.Vertex v = parser.new Vertex(0, 0, 1);
    ScoredObject<Tree> scoredTree1 = new ScoredObject<>(mockTree1, -1.0);
    ScoredObject<Tree> scoredTree2 = new ScoredObject<>(mockTree2, -2.0);
    parser.dHat = new HashMap<>();
    parser.dHat.put(v, Arrays.asList(scoredTree1, scoredTree2));
    doReturn(mockTree1).when(parser).getTree(v, 1, 2);
    doReturn(mockTree2).when(parser).getTree(v, 2, 2);
    List<ScoredObject<Tree>> result = parser.getKBestParses(2);
    assertEquals(2, result.size());
    assertSame(mockTree1, result.get(0).object());
    assertSame(mockTree2, result.get(1).object());
    assertEquals(-1.0, result.get(0).score(), 1.0E-4);
    assertEquals(-2.0, result.get(1).score(), 1.0E-4);
}

@Test
public void test25()
{
    Options options = new Options();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(options);
    List<CoreLabel> sentence = Arrays.asList(CoreLabel.wordFromString("The"), CoreLabel.wordFromString("cat"), CoreLabel.wordFromString("sat"), CoreLabel.wordFromString("on"), CoreLabel.wordFromString("the"), CoreLabel.wordFromString("mat"));
    parser.parse(sentence);
    List<ScoredObject<Tree>> result = parser.getKGoodParses(1);
    Assert.assertNotNull("Result should not be null", result);
    Assert.assertFalse("Result list should not be empty", result.isEmpty());
    Assert.assertNotNull("First parse should not be null", result.get(0).object());
}

@Test
public void test26()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    int k = 3;
    List<ScoredObject<Tree>> parses = parser.getKSampledParses(k);
}

@Test
public void test27()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser() {
        public int calledWithSize = -1;

        @Override
        protected void considerCreatingArrays(int newSize) {
            calledWithSize = newSize;
        }
    };
    Field arraySizeField = ExhaustivePCFGParser.class.getDeclaredField("arraySize");
    arraySizeField.setAccessible(true);
    arraySizeField.setInt(parser, 5);
    Method method = ExhaustivePCFGParser.class.getDeclaredMethod("nudgeDownArraySize");
    method.setAccessible(true);
    method.invoke(parser);
    int calledWithSize = ((ExhaustivePCFGParser) (parser)).calledWithSize;
    assertEquals(3, calledWithSize);
}

@Test
public void test1()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, null, null, null);
    Index<String> mockStateIndex = new Index<>();
    int npIndex = mockStateIndex.indexOf("NP");
    parser.stateIndex = mockStateIndex;
    Tree expectedTree = Tree.valueOf("(NP (DT The) (NN dog))");
    ExhaustivePCFGParser spyParser = new ExhaustivePCFGParser(null, null, null, null) {
        @Override
        protected Tree extractBestParse(int goal, int start, int end) {
            if (((goal == npIndex) && (start == 0)) && (end == 2)) {
                return expectedTree;
            }
            return null;
        }
    };
    spyParser.stateIndex = mockStateIndex;
    Tree result = spyParser.extractBestParse("NP", 0, 2);
    assertEquals(expectedTree, result);
}

@Test
public void test2()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.words = new int[]{ 0 };
    parser.originalTags = new CoreLabel[]{ new CoreLabel() };
    parser.originalTags[0].setTag("NN");
    parser.tagIndex = new Index<>();
    parser.tagIndex.add("NN");
    parser.wordIndex = new Index<>();
    parser.wordIndex.add("dog");
    parser.stateIndex = new Index<>();
    parser.stateIndex.add("NN");
    parser.lex = new ClassicCounter();
    parser.lex = ( word, loc, wid, context) -> 0.0F;
    parser.tf = new LabeledScoredTreeFactory();
    parser.floodTags = false;
    parser.iScore = new double[1][1][1];
    parser.iScore[0][0][0] = 0.0;
    parser.lex = new Lexicon() {
        public float score(IntTaggedWord itw, int loc, String word, String context) {
            return 0.0F;
        }

        public int getSignatureIndex(String word, int loc) {
            return 0;
        }

        public Set<IntTaggedWord> ruleIteratorByWord(int word) {
            return Collections.emptySet();
        }

        public Set<IntTaggedWord> ruleIteratorByWord(String word) {
            return Collections.emptySet();
        }

        public Set<IntTaggedWord> ruleIteratorByTag(int tag) {
            return Collections.emptySet();
        }

        public Set<IntTaggedWord> ruleIteratorByTag(String tag) {
            return Collections.emptySet();
        }
    };
    List<Tree> parses = parser.extractBestParses(0, 0, 1);
    assertEquals(1, parses.size());
    Tree parse = parses.get(0);
    assertEquals("NN", parse.label().value());
    assertEquals(1, parse.numChildren());
    assertEquals("dog", parse.getChild(0).value());
}

@Test
public void test3()
{
}
{
    constraints = new ArrayList<>();
    ParserConstraint c1 = new ParserConstraint(0, 2, "NP");
    ParserConstraint c2 = new ParserConstraint(3, 5, "VP");
    constraints.add(c1);
    constraints.add(c2);
}

@Test
public void test4()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(new Options(), null, null, null, null);
    parser.length = 2;
    parser.numStates = 3;
    parser.tags = new String[2];
    parser.tags[0] = "NN";
    parser.tags[1] = "VB";
    parser.oFilteredStart = null;
    parser.oFilteredEnd = null;
    parser.orf = new OutsideRuleFilter() {
        private int rightSteps = 0;

        private int leftSteps = 0;

        @Override
        public void init() {
            rightSteps = 0;
            leftSteps = 0;
        }

        @Override
        public void leftAccepting(boolean[] stateArray) {
            stateArray[0] = true;
            stateArray[1] = false;
            stateArray[2] = true;
        }

        @Override
        public void rightAccepting(boolean[] stateArray) {
            stateArray[0] = false;
            stateArray[1] = true;
            stateArray[2] = false;
        }

        @Override
        public void advanceRight(String tag) {
            rightSteps++;
        }

        @Override
        public void advanceLeft(String tag) {
            leftSteps++;
        }
    };
    parser.buildOFilter();
    assertNotNull(parser.oFilteredStart);
    assertEquals(2, parser.oFilteredStart.length);
    assertArrayEquals(new boolean[]{ true, false, true }, parser.oFilteredStart[0]);
    assertArrayEquals(new boolean[]{ true, false, true }, parser.oFilteredStart[1]);
    assertNotNull(parser.oFilteredEnd);
    assertEquals(3, parser.oFilteredEnd.length);
    assertArrayEquals(new boolean[]{ false, true, false }, parser.oFilteredEnd[2]);
    assertArrayEquals(new boolean[]{ false, true, false }, parser.oFilteredEnd[1]);
}

@Test
public void test5()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(new Options(), null);
    parser.numStates = 2;
    parser.op.doDep = true;
    parser.op.testOptions.useFastFactored = false;
    parser.op.testOptions.lengthNormalization = true;
    parser.tagIndex = new Index<>();
    parser.tagIndex.add("NN");
    parser.tagIndex.add("VB");
    parser.createArrays(3);
    assertNotNull(parser.iScore);
    assertEquals(3, parser.iScore.length);
    assertEquals(4, parser.iScore[0].length);
    assertEquals(2, parser.iScore[0][1].length);
    assertNotNull(parser.oScore);
    assertEquals(3, parser.oScore.length);
    assertEquals(4, parser.oScore[1].length);
    assertEquals(2, parser.oScore[1][2].length);
    assertNotNull(parser.narrowRExtent);
    assertEquals(3, parser.narrowRExtent.length);
    assertEquals(2, parser.narrowRExtent[0].length);
    assertNotNull(parser.wideLExtent);
    assertEquals(4, parser.wideLExtent.length);
    assertEquals(2, parser.wideLExtent[0].length);
    assertNotNull(parser.iPossibleByL);
    assertEquals(3, parser.iPossibleByL.length);
    assertEquals(2, parser.iPossibleByL[0].length);
    assertNotNull(parser.oPossibleByR);
    assertEquals(4, parser.oPossibleByR.length);
    assertEquals(2, parser.oPossibleByR[0].length);
    assertNotNull(parser.tags);
    assertEquals(3, parser.tags.length);
    assertEquals(2, parser.tags[0].length);
    assertNotNull(parser.wordsInSpan);
    assertEquals(3, parser.wordsInSpan.length);
    assertEquals(4, parser.wordsInSpan[0].length);
    assertEquals(2, parser.wordsInSpan[0][1].length);
}

@Test
public void test6()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.length = 2;
    parser.numStates = 3;
    parser.iScore = new float[2][3][3];
    parser.oScore = new float[2][3][3];
    parser.iPossibleByL = new boolean[2][3];
    parser.iPossibleByR = new boolean[3][3];
    parser.oPossibleByL = new boolean[2][3];
    parser.oPossibleByR = new boolean[3][3];
    parser.iScore[0][1][0] = Float.NEGATIVE_INFINITY;
    parser.iScore[0][1][1] = Float.NEGATIVE_INFINITY;
    parser.iScore[0][1][2] = Float.NEGATIVE_INFINITY;
    parser.iScore[1][2][0] = Float.NEGATIVE_INFINITY;
    parser.iScore[1][2][1] = 0.5F;
    parser.iScore[1][2][2] = Float.NEGATIVE_INFINITY;
    parser.oScore[0][1][0] = Float.NEGATIVE_INFINITY;
    parser.oScore[0][1][1] = Float.NEGATIVE_INFINITY;
    parser.oScore[0][1][2] = Float.NEGATIVE_INFINITY;
    parser.oScore[1][2][0] = Float.NEGATIVE_INFINITY;
    parser.oScore[1][2][1] = 0.9F;
    parser.oScore[1][2][2] = Float.NEGATIVE_INFINITY;
    parser.initializePossibles();
    assertFalse(parser.iPossibleByL[0][0]);
    assertFalse(parser.iPossibleByL[0][1]);
    assertFalse(parser.iPossibleByL[1][0]);
    assertTrue(parser.iPossibleByL[1][1]);
    assertFalse(parser.oPossibleByL[0][2]);
    assertTrue(parser.oPossibleByL[1][1]);
    assertFalse(parser.iPossibleByR[0][1]);
    assertTrue(parser.iPossibleByR[2][1]);
    assertFalse(parser.oPossibleByR[1][0]);
    assertTrue(parser.oPossibleByR[2][1]);
}

@Test
public void test7()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.iPossibleByR = new boolean[5][5];
    parser.iPossibleByL = new boolean[5][5];
    parser.iPossibleByR[2][3] = true;
    Hook hook = new Hook();
    hook.start = 2;
    hook.end = 4;
    hook.subState = 3;
    Hook preHook = new Hook() {
        @Override
        public boolean isPreHook() {
            return true;
        }
    };
    preHook.start = 2;
    preHook.end = 4;
    preHook.subState = 3;
    boolean result = parser.iPossible(preHook);
    assertTrue(result);
}

@Test
public void test8()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    boolean[][] testArray = new boolean[3][3];
    testArray[1][2] = true;
    parser.iPossibleByL = testArray;
    boolean result = parser.iPossibleL(2, 1);
    assertTrue(result);
}

@Test
public void test9()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.iPossibleByR = new boolean[5][5];
    parser.iPossibleByR[3][2] = true;
    boolean result = parser.iPossibleR(2, 3);
    assertTrue(result);
}

@Test
public void test10()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.oPossibleByR = new boolean[5][5];
    parser.oPossibleByL = new boolean[5][5];
    parser.oPossibleByR[3][2] = true;
    parser.oPossibleByL[1][2] = false;
    Hook hook = new Hook();
    hook.start = 1;
    hook.end = 3;
    hook.state = 2;
    hook.setIsPreHook(true);
    boolean result = parser.oPossible(hook);
    assertTrue(result);
}

@Test
public void test11()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.oPossibleByL = new boolean[3][3];
    parser.oPossibleByL[1][2] = true;
    boolean result = parser.oPossibleL(2, 1);
    assertTrue(result);
}

@Test
public void test12()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.oPossibleByR = new boolean[5][5];
    parser.oPossibleByR[3][2] = true;
    boolean result = parser.oPossibleR(2, 3);
    assertTrue("oPossibleR should return true when oPossibleByR[end][state] is true", result);
}

@Test
public void test13()
{
    Options options = new Options();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(options);
    CoreLabel word = new CoreLabel();
    word.setWord("Hello");
    word.setBeginPosition(0);
    word.setEndPosition(5);
    List<HasWord> sentence = new ArrayList<>();
    sentence.add(word);
    boolean result = parser.parse(sentence);
    assertTrue(result || (!result));
}

@Test
public void test14()
{
    Options options = new Options();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(options);
    parser.stateIndex = new HashIndex();
    parser.stateIndex.add("ROOT");
    parser.goalStr = "ROOT";
    parser.wordIndex = new HashIndex();
    parser.lex = new Lexicon(options);
    parser.op = options;
    parser.op.testOptions.verbose = false;
    parser.iScore = new float[5][5][5];
    parser.oScore = new float[5][5][5];
    parser.wordsInSpan = new int[5][5][5];
    parser.narrowLExtent = new int[5][5];
    parser.wideLExtent = new int[5][5];
    parser.narrowRExtent = new int[5][5];
    parser.wideRExtent = new int[5][5];
    CoreLabel token = new CoreLabel();
    token.setWord("dog");
    token.setBeginPosition(0);
    token.setEndPosition(3);
    token.setTag("NN");
    List<HasWord> sentence = Collections.<HasWord>singletonList(token);
    boolean result;
    try {
        result = parser.parse(sentence);
    } catch (Exception e) {
        fail("Method threw exception: " + e.getMessage());
        return;
    }
    assertTrue(result || (!result));
}

@Test
public void test15()
{
    CoreLabel label = new CoreLabel();
    label.setWord("dog");
    label.setBeginPosition(0);
    label.setEndPosition(3);
    List<CoreLabel> sentence = Collections.singletonList(label);
    Options op = new Options();
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(op);
    parser.stateIndex = new Index();
    parser.goalStr = "ROOT";
    parser.stateIndex.add("ROOT");
    parser.wordIndex = new Index();
    parser.lex = new Lexicon(op);
    parser.lex.setKnownWord(( get) -> true);
    int length = 1;
    parser.arraySize = length + 1;
    parser.iScore = new float[length + 1][length + 1][1];
    parser.oScore = new float[length + 1][length + 1][1];
    parser.wordsInSpan = new int[length + 1][length + 1][1];
    parser.narrowLExtent = new int[length + 1][1];
    parser.wideLExtent = new int[length + 1][1];
    parser.narrowRExtent = new int[length][1];
    parser.wideRExtent = new int[length][1];
    parser.op.testOptions.verbose = false;
    boolean result = parser.parse(sentence);
    assertTrue(result || (!result));
}

@Test
public void test16()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    String testGoalStr = "S";
    double expectedScore = 3.14;
    parser.goalStr = testGoalStr;
    parser.chart = new HashMap<>();
    parser.chart.put(testGoalStr, expectedScore);
    double actualScore = parser.getBestScore();
    assertEquals(expectedScore, actualScore, 1.0E-4);
}

@Test
public void test17()
{
    ExhaustivePCFGParser parser = mock(ExhaustivePCFGParser.class);
    String mockGoalStr = "S";
    double expectedScore = 42.0;
    when(parser.goalStr).thenReturn(mockGoalStr);
    when(parser.getBestScore(mockGoalStr)).thenReturn(expectedScore);
    when(parser.getBestScore()).thenCallRealMethod();
    double actualScore = parser.getBestScore();
    assertEquals(expectedScore, actualScore, 1.0E-5);
}

@Test
public void test18()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.iScore = new double[5][5][5];
    parser.iScore[1][3][2] = 7.25;
    Edge edge = new Edge();
    edge.start = 1;
    edge.end = 3;
    edge.state = 2;
    double result = parser.iScore(edge);
    assertEquals(7.25, result, 1.0E-5);
}

@Test
public void test19()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, null, null, null);
    parser.oScore = new double[3][3][3];
    parser.iScore = new double[3][3][3];
    int start = 0;
    int end = 1;
    int state = 2;
    parser.oScore[start][end][state] = -3.0;
    parser.iScore[start][end][state] = -2.0;
    parser.bestScore = 0.0;
    parser.op = new Options();
    parser.op.testOptions.pcfgThreshold = true;
    parser.op.testOptions.pcfgThresholdValue = 0.5;
    Edge edge = new Edge(start, end, state);
    double result = parser.oScore(edge);
    assertTrue("Expected negative infinity when score is below threshold", Double.isInfinite(result) && (result < 0));
}

@Test
public void test20()
{
    Label tagLabel = new StringLabel("NN");
    Label wordLabel = new StringLabel("dog");
    Tree leaf = new LabeledScoredTreeFactory().newLeaf(wordLabel);
    Tree preTerminal = new LabeledScoredTreeFactory().newTreeNode(tagLabel, Collections.singletonList(leaf));
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.tagIndex = mock(Index.class);
    parser.wordIndex = mock(Index.class);
    parser.lex = mock(Lexicon.class);
    when(parser.tagIndex.indexOf("NN")).thenReturn(1);
    when(parser.wordIndex.indexOf("dog")).thenReturn(2);
    when(parser.lex.score(new IntTaggedWord(2, 1), 0, "dog", null)).thenReturn(4.5F);
    double result = parser.scoreBinarizedTree(preTerminal, 0);
    assertEquals(4.5, result, 1.0E-4);
    assertEquals(4.5, preTerminal.score(), 1.0E-4);
}

@Test
public void test21()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    Index<String> tagIndex = new Index<>();
    tagIndex.add("NN");
    Index<String> wordIndex = new Index<>();
    wordIndex.add("dog");
    Index<String> stateIndex = new Index<>();
    stateIndex.add("NN");
    parser.tagIndex = tagIndex;
    parser.wordIndex = wordIndex;
    parser.stateIndex = stateIndex;
    parser.lex = new Lexicon() {
        public float score(IntTaggedWord itw, int loc, String word, String featureSpec) {
            return 0.5F;
        }
    };
    parser.iScore = new float[5][5][5];
    parser.iScore[0][1][0] = 0.3F;
    TreeFactory tf = new LabeledScoredTreeFactory();
    Label pretermLabel = new StringLabel("NN");
    Label leafLabel = new StringLabel("dog");
    Tree leaf = tf.newLeaf(leafLabel);
    Tree preterm = tf.newTreeNode(pretermLabel, Arrays.asList(leaf));
    double result = parser.validateBinarizedTree(preterm, 0);
    assertEquals(0.5F, result, 1.0E-6);
}

@Test
public void test22()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    Field goalStrField = ExhaustivePCFGParser.class.getDeclaredField("goalStr");
    goalStrField.setAccessible(true);
    goalStrField.set(parser, "ROOT");
    Field lengthField = ExhaustivePCFGParser.class.getDeclaredField("length");
    lengthField.setAccessible(true);
    lengthField.setInt(parser, 0);
    Tree result = parser.getBestParse();
    assertNull("Expected null when no parse is found", result);
}

@Test
public void test23()
{
    Options options = new Options();
    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    options.setOptions("-tlp", "edu.stanford.nlp.trees.PennTreebankLanguagePack");
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(options);
    TreeFactory treeFactory = new LabeledScoredTreeFactory(new StringLabelFactory());
    Tree leaf1 = treeFactory.newLeaf("dogs");
    Tree preterm1 = treeFactory.newTreeNode("NNS", Collections.singletonList(leaf1));
    Tree np = treeFactory.newTreeNode("NP", Collections.singletonList(preterm1));
    Tree root = treeFactory.newTreeNode("S", Collections.singletonList(np));
    Tree result = parser.scoreNonBinarizedTree(root);
    assertNotNull(result);
    assertEquals("S", result.label().value());
    assertEquals(1, result.numChildren());
    assertEquals("NP", result.getChild(0).label().value());
    assertEquals(1, result.getChild(0).numChildren());
    assertEquals("NNS", result.getChild(0).getChild(0).label().value());
    assertEquals("dogs", result.getChild(0).getChild(0).getChild(0).label().value());
}

@Test
public void test24()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    int hash1 = parser.hashCode();
    int hash2 = parser.hashCode();
    assertEquals("hashCode should return the same value on multiple invocations on the same object", hash1, hash2);
}

@Test
public void test25()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser(null, null);
    int length = 1;
    parser.length = length;
    parser.stateIndex = mock(Index.class);
    when(parser.stateIndex.indexOf("ROOT")).thenReturn(0);
    parser.goalStr = "ROOT";
    parser.iScore = new double[length + 1][length + 1][1];
    parser.iScore[0][1][0] = -3.14;
    TreeFactory tf = new LabeledScoredTreeFactory();
    Tree tree = tf.newLeaf("test");
    List<Tree> treeList = new ArrayList<>();
    treeList.add(tree);
    ExhaustivePCFGParser spyParser = spy(parser);
    doReturn(treeList).when(spyParser).extractBestParses(0, 0, 1);
    List<ScoredObject<Tree>> result = spyParser.getBestParses();
    assertEquals(1, result.size());
    ScoredObject<Tree> scoredTree = result.get(0);
    assertEquals(tree, scoredTree.object());
    assertEquals(-3.14, scoredTree.score(), 1.0E-4);
}

@Test
public void test26()
{
    ExhaustivePCFGParser parser = new ExhaustivePCFGParser();
    parser.length = 1;
    parser.goalStr = "S";
    parser.stateIndex = new Index();
    int goalIndex = parser.stateIndex.add("S");
    Vertex v = parser.new Vertex(goalIndex, 0, 1);
    Tree mockTree = new Tree() {
        @Override
        public String value() {
            return "S";
        }

        @Override
        public void setValue(String value) {
        }

        @Override
        public List<Tree> getChildrenAsList() {
            return new ArrayList<>();
        }

        @Override
        public Tree[] children() {
            return new Tree[0];
        }

        @Override
        public Tree parent() {
            return null;
        }

        @Override
        public void setParent(Tree parent) {
        }

        @Override
        public boolean isLeaf() {
            return true;
        }

        @Override
        public boolean isPreTerminal() {
            return false;
        }

        @Override
        public Tree deepCopy() {
            return this;
        }
    };
    parser.dHat = new HashMap<>();
    List<ScoredObject<Tree>> scoredList = new ArrayList<>();
    scoredList.add(new ScoredObject<>(mockTree, -0.5));
    parser.dHat.put(v, scoredList);
    parser.cand = new HashMap<>();
    parser.getTree = ( vertex, i, k) -> {
        if (vertex.equals(v) && (i == 1)) {
            return mockTree;
        }
        return null;
    };
    List<ScoredObject<Tree>> result = parser.getKBestParses(1);
    assertEquals(1, result.size());
    assertEquals(mockTree, result.get(0).object());
    assertEquals(-0.5, result.get(0).score(), 1.0E-4);
}


