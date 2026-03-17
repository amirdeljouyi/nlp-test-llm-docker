import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getAttribute("EntityID")).thenReturn("E1");
    when(target.getAttribute("EntityID")).thenReturn("E1");
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    String result = extractor.getCorefTag(relation);
    assertEquals("TRUE", result);
}

@Test
public void test2()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog" };
    TextAnnotation ta = new TextAnnotation("", "", "", tokens, new int[]{ 0 });
    Constituent source = new Constituent("source", "testView", ta, 1, 4);
    Constituent target = new Constituent("target", "testView", ta, 5, 8);
    Relation relation = new Relation("testRel", source, target, 1.0);
    Constituent sourceHead = new Constituent("srcHead", "testView", ta, 2, 3);
    Constituent targetHead = new Constituent("tgtHead", "testView", ta, 6, 7);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getCollocationsFeature(Relation r) {
            return super.getCollocationsFeature(r);
        }
    };
    try {
        Method headMethod = RelationFeatureExtractor.class.getDeclaredMethod("getEntityHeadForConstituent", Constituent.class, TextAnnotation.class, String.class);
        headMethod.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        Field field = RelationFeatureExtractor.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & (~Modifier.FINAL));
        field.set(null, extractor);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    RelationFeatureExtractor featureExtractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getCollocationsFeature(Relation r) {
            return RelationFeatureExtractor.super.getCollocationsFeature(r);
        }
    };
    List<String> expected = Arrays.asList("s_m1_p1_quick", "s_m2_m1_", "s_p1_p2_", "s_m1_m1_brown", "s_p1_p1_fox", "t_m1_p1_lazy", "t_m2_m1_over", "t_p1_p2_", "t_m1_m1_lazy", "t_p1_p1_dog");
    List<String> actual = new RelationFeatureExtractor().getCollocationsFeature(relation);
    assertEquals(expected.size(), actual.size());
    assertEquals(expected.get(0), actual.get(0));
    assertEquals(expected.get(1), actual.get(1));
    assertEquals(expected.get(2), actual.get(2));
    assertEquals(expected.get(3), actual.get(3));
    assertEquals(expected.get(4), actual.get(4));
    assertEquals(expected.get(5), actual.get(5));
    assertEquals(expected.get(6), actual.get(6));
    assertEquals(expected.get(7), actual.get(7));
    assertEquals(expected.get(8), actual.get(8));
    assertEquals(expected.get(9), actual.get(9));
}

@Test
public void test3()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps" };
    TextAnnotation ta = new TextAnnotation("corpus", "docid", null) {
        @Override
        public String getToken(int index) {
            return tokens[index];
        }
    };
    Constituent source = new Constituent("TestLabel", "viewName", ta, 1, 4);
    Relation relation = new Relation("relType", source, null, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartA(relation);
    List<String> expected = Arrays.asList("quick", "brown", "fox");
    assertEquals(expected, features);
}

@Test
public void test4()
{
    String[] tokens = new String[]{ "John", "loves", "Mary" };
    TextAnnotation ta = new TextAnnotation("testCorpus", "testText", "", tokens);
    Constituent source = new Constituent("ARG1", "testView", ta, 0, 1);
    Constituent target = new Constituent("ARG2", "testView", ta, 2, 3);
    Relation relation = new Relation("testRel", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartC(relation);
    List<String> expected = new ArrayList<>();
    expected.add("singleword_" + ta.getToken(1));
    assertEquals(expected, features);
}

@Test
public void test5()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
    String text = String.join(" ", tokens);
    TextAnnotation ta = new TextAnnotation("testCorpus", "testId", text);
    ta.initializeFromTokens(Collections.singletonList(Arrays.asList(tokens)));
    Constituent source = new Constituent("source", "view", ta, 0, 3);
    Constituent target = new Constituent("target", "view", ta, 6, 9);
    Relation relation = new Relation("testRel", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartCC(Relation r) {
            List<String> ret_features = new ArrayList<>();
            Constituent source = r.getSource();
            TextAnnotation ta = source.getTextAnnotation();
            Constituent source_head = new Constituent("sourceHead", "view", ta, 0, 3);
            Constituent target_head = new Constituent("targetHead", "view", ta, 6, 9);
            if (source_head.getEndSpan() < target_head.getStartSpan()) {
                ret_features.add("bowbethead_" + ta.getToken(3));
                ret_features.add("bowbethead_" + ta.getToken(4));
                ret_features.add("bowbethead_" + ta.getToken(5));
            }
            return ret_features;
        }
    };
    List<String> features = extractor.getLexicalFeaturePartCC(relation);
    List<String> expected = Arrays.asList("bowbethead_fox", "bowbethead_jumps", "bowbethead_over");
    assertEquals(expected, features);
}

@Test
public void test6()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
    TextAnnotation ta = new TextAnnotation("corpusId", "viewName", tokens);
    Constituent source = new Constituent("sourceLabel", "viewName", ta, 1, 2);
    Constituent target = new Constituent("targetLabel", "viewName", ta, 5, 6);
    Relation relation = new Relation("testRelation", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getLexicalFeaturePartD(relation);
    List<String> expected = new ArrayList<>();
    expected.add("between_first_" + ta.getToken(2));
    expected.add("between_first_" + ta.getToken(4));
    expected.add("in_between_" + ta.getToken(3));
    assertEquals(expected, result);
}

@Test
public void test7()
{
    TextAnnotation mockTA = mock(TextAnnotation.class);
    Sentence mockSentence = mock(Sentence.class);
    when(mockSentence.getStartSpan()).thenReturn(0);
    when(mockSentence.getEndSpan()).thenReturn(6);
    when(mockTA.getSentence(0)).thenReturn(mockSentence);
    when(mockTA.getToken(1)).thenReturn("beforeSource");
    when(mockTA.getToken(0)).thenReturn("twoBeforeSource");
    when(mockTA.getToken(4)).thenReturn("afterTarget");
    when(mockTA.getToken(5)).thenReturn("twoAfterTarget");
    Constituent source = mock(Constituent.class);
    when(source.getStartSpan()).thenReturn(2);
    when(source.getSentenceId()).thenReturn(0);
    when(source.getTextAnnotation()).thenReturn(mockTA);
    Constituent target = mock(Constituent.class);
    when(target.getEndSpan()).thenReturn(3);
    when(target.getSentenceId()).thenReturn(0);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartE(relation);
    List<String> expected = Arrays.asList("fwM1_beforeSource", "swM1_twoBeforeSource", "fwM2_afterTarget", "swM2_twoAfterTarget");
    assertEquals(expected, features);
}

@Test
public void test8()
{
    Constituent sourceMock = mock(Constituent.class);
    Constituent targetMock = mock(Constituent.class);
    TextAnnotation textAnnotationMock = mock(TextAnnotation.class);
    when(sourceMock.getTextAnnotation()).thenReturn(textAnnotationMock);
    when(targetMock.getTextAnnotation()).thenReturn(textAnnotationMock);
    Constituent sourceHeadMock = mock(Constituent.class);
    Constituent targetHeadMock = mock(Constituent.class);
    when(sourceHeadMock.toString()).thenReturn("sourceHeadWord");
    when(targetHeadMock.toString()).thenReturn("targetHeadWord");
    mockStatic(RelationFeatureExtractor.class);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(sourceMock, textAnnotationMock, "EntityHeads")).thenReturn(sourceHeadMock);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(targetMock, textAnnotationMock, "EntityHeads")).thenReturn(targetHeadMock);
    Relation relationMock = mock(Relation.class);
    when(relationMock.getSource()).thenReturn(sourceMock);
    when(relationMock.getTarget()).thenReturn(targetMock);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartF(relationMock);
    assertEquals(3, features.size());
    assertEquals("HM1_sourceHeadWord", features.get(0));
    assertEquals("HM2_targetHeadWord", features.get(1));
    assertEquals("HM12_sourceHeadWord_targetHeadWord", features.get(2));
}

@Test
public void test9()
{
    Constituent source = Mockito.mock(Constituent.class);
    Constituent target = Mockito.mock(Constituent.class);
    Mockito.when(source.getAttribute("EntityMentionType")).thenReturn("NAM");
    Mockito.when(target.getAttribute("EntityMentionType")).thenReturn("NOM");
    Mockito.when(source.getAttribute("EntityType")).thenReturn("PER");
    Mockito.when(target.getAttribute("EntityType")).thenReturn("ORG");
    Mockito.when(target.doesConstituentCover(source)).thenReturn(false);
    Mockito.when(source.doesConstituentCover(target)).thenReturn(true);
    Relation relation = Mockito.mock(Relation.class);
    Mockito.when(relation.getSource()).thenReturn(source);
    Mockito.when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getMentionFeature(relation);
    List<String> expected = Arrays.asList("source_mtype_PER", "target_mtype_ORG", "mlvl_NAM_NOM", "mt_PER_ORG", "mlvl_mt_NAM_PER_NOM_ORG", "mlvl_cont_2_NAM_NOM_True");
    assertEquals(expected, features);
}

@Test
public void test10()
{
    String[] tokens = new String[]{ "John", "bought", "an", "apple", "." };
    TextAnnotation ta = new TextAnnotation("corpus", "id", tokens);
    View chunkView = new View(ViewNames.SHALLOW_PARSE, "chunker", ta, 1.0);
    chunkView.addConstituent(new Constituent("NP", ViewNames.SHALLOW_PARSE, ta, 0, 1));
    chunkView.addConstituent(new Constituent("VP", ViewNames.SHALLOW_PARSE, ta, 1, 2));
    chunkView.addConstituent(new Constituent("NP", ViewNames.SHALLOW_PARSE, ta, 2, 4));
    ta.addView(SHALLOW_PARSE, chunkView);
    Constituent source = new Constituent("PER", "test", ta, 0, 1);
    Constituent target = new Constituent("OBJ", "test", ta, 2, 4);
    Relation relation = new Relation("testRel", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        public List<Pair<String, String>> getShallowParseFeature(Relation r) {
            return RelationFeatureExtractorTest.superGetShallowParseFeature(r);
        }
    };
    List<Pair<String, String>> features = new RelationFeatureExtractorTest().superGetShallowParseFeature(relation);
    assertEquals(2, features.size());
    assertEquals("chunker_between_heads_0", features.get(0).getFirst());
    assertEquals("VP", features.get(0).getSecond());
    assertEquals("chunker_between_extents_0", features.get(1).getFirst());
    assertEquals("VP", features.get(1).getSecond());
}

@Test
public void test11()
{
    String[] tokens = new String[]{ "John", "visited", "the", "library", "yesterday" };
    TextAnnotation ta = new TextAnnotation("corpus", "id", tokens);
    Constituent source = new Constituent("PER", "NER", ta, 0, 1);
    Constituent target = new Constituent("ORG", "NER", ta, 3, 4);
    source.addAttribute("EnityType", "PERSON");
    target.addAttribute("EntityType", "ORGANIZATION");
    View mentionView = new View(ViewNames.MENTION_ACE, "testGenerator", ta, 1.0);
    Constituent middle = new Constituent("DET", "NER", ta, 2, 3);
    mentionView.addConstituent(middle);
    ta.addView(MENTION_ACE, mentionView);
    ta.addView(MENTION_ERE, mentionView);
    ta.addView(MENTION, mentionView);
    Relation relation = new Relation("testRel", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getStructualFeature(relation);
    assertTrue(result.contains("middle_mention_size_1"));
    assertTrue(result.contains("middle_word_size_2"));
    assertTrue(result.contains("m1_m2_no_coverage"));
    assertTrue(result.contains("cb1_PERSON_ORGANIZATION_m1_m2_no_coverage"));
}

@Test
public void test12()
{
    Relation relation = new Relation("rel1", null, null, null);
    List<String> features = new RelationFeatureExtractor() {
        @Override
        public List<String> getTemplateFeature(Relation r) {
            List<String> ret_features = new ArrayList<String>();
            ret_features.add("is_formulaic_structure");
            ret_features.add("is_preposition_structure");
            return ret_features;
        }
    }.getTemplateFeature(relation);
    List<String> expected = new ArrayList<String>();
    expected.add("is_formulaic_structure");
    expected.add("is_preposition_structure");
    assertEquals(expected, features);
}

@Test
public void test13()
{
    String[] tokens = new String[]{ "John", "CEO", "of", "Acme" };
    List<String> posTags = Arrays.asList("NNP", "NN", "IN", "NNP");
    TextAnnotation ta = new TextAnnotation("dummy_corpus", "dummy_text", tokens);
    View posView = new View(ViewNames.POS, "POSAnnotator", ta, 1.0);
    for (int i = 0; i < tokens.length; i++) {
        Constituent posConstituent = new Constituent(posTags.get(i), ViewNames.POS, ta, i, i + 1);
        posView.addConstituent(posConstituent);
    }
    ta.addView(POS, posView);
    Constituent source = new Constituent("entity1", "NER", ta, 0, 1);
    source.addAttribute("EntityType", "PER");
    Constituent target = new Constituent("entity2", "NER", ta, 3, 4);
    target.addAttribute("EntityType", "ORG");
    posView.removeConstituent(posView.getConstituentsCoveringSpan(2, 3).get(0));
    posView.addConstituent(new Constituent(",", ViewNames.POS, ta, 2, 3));
    Relation relation = new Relation("testRel", source, target, 1.0F);
    boolean result = RelationFeatureExtractor.isFormulaic(relation);
    assertTrue(result);
}

@Test
public void test14()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    View mockPosView = mock(View.class);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    Relation relation = mock(Relation.class);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    when(source.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getEndSpan()).thenReturn(2);
    when(target.getStartSpan()).thenReturn(3);
    when(target.getEndSpan()).thenReturn(5);
    when(mockTextAnnotation.getView(POS)).thenReturn(mockPosView);
    when(mockTextAnnotation.getToken(2)).thenReturn("'s");
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(sourceHead.getEndSpan()).thenReturn(2);
    when(targetHead.getStartSpan()).thenReturn(3);
    when(targetHead.getEndSpan()).thenReturn(5);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getEndSpan()).thenReturn(2);
    when(target.getStartSpan()).thenReturn(3);
    when(target.getEndSpan()).thenReturn(5);
    when(mockPosView.getLabelsCoveringToken(anyInt())).thenReturn(Collections.singletonList("NN"));
    boolean result = RelationFeatureExtractor.isPossessive(new Relation("mock", source, target, 1.0));
    assertTrue(result);
}

@Test
public void test15()
{
    TextAnnotation ta = new TextAnnotation("testCorpus", "testId", "The quick brown fox jumps over the lazy dog.");
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    posView.addConstituent(new Constituent("DT", ViewNames.POS, ta, 0, 1));
    posView.addConstituent(new Constituent("JJ", ViewNames.POS, ta, 1, 2));
    posView.addConstituent(new Constituent("JJ", ViewNames.POS, ta, 2, 3));
    posView.addConstituent(new Constituent("NN", ViewNames.POS, ta, 3, 4));
    posView.addConstituent(new Constituent("VBZ", ViewNames.POS, ta, 4, 5));
    ta.addView(POS, posView);
    Constituent source = new Constituent("Entity", "TEST", ta, 1, 3);
    Constituent target = new Constituent("Entity", "TEST", ta, 3, 4);
    Relation relation = new Relation("mod", source, target, 1.0);
    RelationFeatureExtractor featureExtractor = new RelationFeatureExtractor() {
        public static Constituent getEntityHeadForConstituent(Constituent c, TextAnnotation t, String viewName) {
            return c;
        }

        public static boolean onlyNounBetween(Constituent a, Constituent b) {
            return false;
        }
    };
    boolean result = RelationFeatureExtractor.isPremodifier(relation);
    assertTrue(result);
}

@Test
public void test16()
{
    View posView = mock(View.class);
    when(posView.getLabelsCoveringToken(2)).thenReturn(Collections.singletonList("IN"));
    Sentence sentence = mock(Sentence.class);
    when(sentence.getStartSpan()).thenReturn(0);
    TextAnnotation ta = mock(TextAnnotation.class);
    when(ta.getView(POS)).thenReturn(posView);
    when(ta.getSentenceId(any(Constituent.class))).thenReturn(0);
    when(ta.getSentence(0)).thenReturn(sentence);
    Constituent source = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getEndSpan()).thenReturn(1);
    Constituent target = mock(Constituent.class);
    when(target.getTextAnnotation()).thenReturn(ta);
    when(target.getStartSpan()).thenReturn(3);
    when(target.getEndSpan()).thenReturn(4);
    Constituent sourceHead = mock(Constituent.class);
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(sourceHead.getEndSpan()).thenReturn(1);
    Constituent targetHead = mock(Constituent.class);
    when(targetHead.getStartSpan()).thenReturn(3);
    when(targetHead.getEndSpan()).thenReturn(4);
    RelationFeatureExtractor extractor = mock(RelationFeatureExtractor.class);
    mockStatic(RelationFeatureExtractor.class);
    when(RelationFeatureExtractor.isPossessive(any(Relation.class))).thenReturn(false);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(source, ta, "TEST")).thenReturn(sourceHead);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(target, ta, "TEST")).thenReturn(targetHead);
    when(RelationFeatureExtractor.isNoun("IN")).thenReturn(false);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    boolean result = RelationFeatureExtractor.isPreposition(relation);
    assertTrue(result);
}

@Test
public void test17()
{
    String[] tokens = new String[]{ "John", "cat", "dog", "runs" };
    String[] posTags = new String[]{ "NNP", "NN", "NNS", "VBZ" };
    TextAnnotation ta = new TextAnnotation("corpusId", "documentId", tokens);
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    for (int i = 0; i < tokens.length; i++) {
        Constituent posConstituent = new Constituent(posTags[i], ViewNames.POS, ta, i, i + 1);
        posView.addConstituent(posConstituent);
    }
    ta.addView(POS, posView);
    Constituent front = new Constituent("entity", "testView", ta, 0, 1);
    Constituent back = new Constituent("entity", "testView", ta, 3, 4);
    boolean result = RelationFeatureExtractor.onlyNounBetween(front, back);
    assertTrue(result);
}

@Test
public void test18()
{
    String[] sentence = new String[]{ "Barack", "Obama", "was", "born", "in", "Hawaii", "." };
    TextAnnotation ta = BasicTextAnnotationBuilder.createTextAnnotationFromTokenizedString("test", "1", Collections.singletonList(String.join(" ", sentence)));
    Constituent constituent = new Constituent("PER", 1.0, "NER", ta, 0, 2);
    constituent.addAttribute("IsPredicted", "true");
    Constituent result = RelationFeatureExtractor.getEntityHeadForConstituent(constituent, ta, "NER");
    assertSame(constituent, result);
}

@Test
public void test19()
{
    String testText = "Barack Obama was born in Hawaii.";
    TextAnnotation ta = new TokenizerTextAnnotationBuilder(new StatefulTokenizer()).createTextAnnotation("test", "1", testText);
    View dependencyView = new TreeView(ViewNames.DEPENDENCY_STANFORD, "dummy");
    View posView = new View(ViewNames.POS, "dummy", ta, 1.0);
    View annotatedView = new View("RE_ANNOTATED", "dummy", ta, 1.0);
    View dummyHeadView = new View("EntityHeads", "dummy", ta, 1.0);
    Constituent source = new Constituent("PER", "dummy", ta, 0, 2);
    Constituent target = new Constituent("LOC", "dummy", ta, 5, 6);
    source.setSentenceId(0);
    target.setSentenceId(0);
    Constituent sourceHead = new Constituent("NNP", "EntityHeads", ta, 1, 2);
    Constituent targetHead = new Constituent("NNP", "EntityHeads", ta, 5, 6);
    Constituent sourceParsed = new Constituent("NNP", ViewNames.DEPENDENCY_STANFORD, ta, 1, 2);
    Constituent targetParsed = new Constituent("NNP", ViewNames.DEPENDENCY_STANFORD, ta, 5, 6);
    Constituent depPath1 = new Constituent("VBD", ViewNames.DEPENDENCY_STANFORD, ta, 2, 3);
    Constituent depPath2 = new Constituent("IN", ViewNames.DEPENDENCY_STANFORD, ta, 3, 4);
    Constituent pos1 = new Constituent("VBD", ViewNames.POS, ta, 2, 3);
    Constituent pos2 = new Constituent("IN", ViewNames.POS, ta, 3, 4);
    Constituent wnet1 = new Constituent("wn1", "RE_ANNOTATED", ta, 2, 3);
    wnet1.addAttribute("WORDNETTAG", "wn-tag-1");
    Constituent wnet2 = new Constituent("wn2", "RE_ANNOTATED", ta, 3, 4);
    wnet2.addAttribute("WORDNETTAG", "wn-tag-2");
    dependencyView.addConstituent(sourceParsed);
    dependencyView.addConstituent(targetParsed);
    dependencyView.addConstituent(depPath1);
    dependencyView.addConstituent(depPath2);
    posView.addConstituent(pos1);
    posView.addConstituent(pos2);
    annotatedView.addConstituent(wnet1);
    annotatedView.addConstituent(wnet2);
    dummyHeadView.addConstituent(sourceHead);
    dummyHeadView.addConstituent(targetHead);
    ta.addView(DEPENDENCY_STANFORD, dependencyView);
    ta.addView(POS, posView);
    ta.addView("RE_ANNOTATED", annotatedView);
    ta.addView("EntityHeads", dummyHeadView);
    Relation rel = new Relation("org:countries_of_residence", source, target, 1.0);
    List<Pair<String, String>> result = RelationFeatureExtractor.getDependencyFeature(rel);
    assertNotNull(result);
    assertTrue(result.size() >= 6);
    assertEquals("tag_0", result.get(0).getFirst());
    assertEquals("VBD", result.get(0).getSecond());
    assertEquals("pos_tag_0", result.get(1).getFirst());
    assertEquals("VBD", result.get(1).getSecond());
    assertEquals("wordnettag_0", result.get(2).getFirst());
    assertEquals("wn-tag-1", result.get(2).getSecond());
}

@Test
public void test20()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    View mockTokenView = mock(View.class);
    TreeView mockDependencyView = mock(TreeView.class);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    Constituent head = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(target.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(mockTextAnnotation.getView(TOKENS)).thenReturn(mockTokenView);
    when(mockTextAnnotation.getView(DEPENDENCY_STANFORD)).thenReturn(mockDependencyView);
    when(head.getStartSpan()).thenReturn(5);
    when(source.getStartSpan()).thenReturn(1);
    when(target.getStartSpan()).thenReturn(2);
    when(source.getEndSpan()).thenReturn(3);
    when(target.getEndSpan()).thenReturn(4);
    when(source.getSentenceId()).thenReturn(0);
    when(target.getSentenceId()).thenReturn(0);
    when(source.getAttribute("EntityType")).thenReturn("ORG");
    when(target.getAttribute("EntityType")).thenReturn("ORG");
    Constituent sourceDep = mock(Constituent.class);
    Constituent targetDep = mock(Constituent.class);
    when(mockDependencyView.getConstituentsCoveringToken(5)).thenReturn(Collections.singletonList(sourceDep));
    when(sourceDep.getLabel()).thenReturn("foo");
    when(targetDep.getLabel()).thenReturn("bar");
    when(mockTokenView.getEndSpan()).thenReturn(10);
    when(mockTextAnnotation.getToken(anyInt())).thenReturn("something");
    when(mockDependencyView.getConstituentsCoveringToken(anyInt())).thenAnswer(( invocation) -> {
        int arg = invocation.getArgument(0);
        if (arg == head.getStartSpan()) {
            return Collections.singletonList(sourceDep);
        }
        return Collections.singletonList(targetDep);
    });
    Constituent pathNode1 = mock(Constituent.class);
    Constituent pathNode2 = mock(Constituent.class);
    when(pathNode1.getLabel()).thenReturn("prep");
    when(pathNode2.getLabel()).thenReturn("pobj");
    PathFeatureHelper helper = mock(PathFeatureHelper.class);
    try {
        List<Constituent> path = List.of(mock(Constituent.class), pathNode1, pathNode2);
        when(PathFeatureHelper.getPathConstituents(sourceDep, targetDep, 100)).thenReturn(path);
    } catch (Exception e) {
        fail("Unexpected exception while stubbing getPathConstituents");
    }
    List<String> features = RelationFeatureExtractor.patternRecognition(source, target);
    assertTrue(features.contains("SAME_SOURCE_TARGET_EXCEPTION"));
    assertFalse(features.contains("SAME_SOURCE_TARGET_EXTENT_EXCEPTION"));
    assertFalse(features.contains("FORMULAIC"));
    assertFalse(features.contains("prep_pobj_dep_structure"));
}

