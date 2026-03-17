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
    String[] tokens = new String[]{ "John", "loves", "Mary", "." };
    TextAnnotation ta = new TextAnnotation("viewId", "corpusId", "John loves Mary .");
    View dummyView = new View("DummyView", "testGenerator", ta, 1.0);
    for (int i = 0; i < tokens.length; i++) {
        ta.addToken(tokens[i], i);
    }
    Constituent source = new Constituent("PER", "DummyView", ta, 0, 1);
    Constituent target = new Constituent("PER", "DummyView", ta, 2, 3);
    View entityHeadsView = new View("EntityHeads", "testGenerator", ta, 1.0);
    Constituent sourceHead = new Constituent("PER", "EntityHeads", ta, 0, 1);
    Constituent targetHead = new Constituent("PER", "EntityHeads", ta, 2, 3);
    entityHeadsView.addConstituent(sourceHead);
    entityHeadsView.addConstituent(targetHead);
    ta.addView("EntityHeads", entityHeadsView);
    Relation relation = new Relation("testRel", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getCollocationsFeature(relation);
    List<String> expectedFeatures = new ArrayList<>();
    expectedFeatures.add("s_m1_p1_John");
    expectedFeatures.add("s_m2_m1_");
    expectedFeatures.add("s_p1_p2_");
    expectedFeatures.add("s_m1_m1_null");
    expectedFeatures.add("s_p1_p1_null");
    expectedFeatures.add("t_m1_p1_Mary");
    expectedFeatures.add("t_m2_m1_");
    expectedFeatures.add("t_p1_p2_");
    expectedFeatures.add("t_m1_m1_null");
    expectedFeatures.add("t_p1_p1_null");
    assertEquals(expectedFeatures, features);
}

@Test
public void test3()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
    TextAnnotation ta = new TextAnnotation("corpusId", "viewName", new String[][]{ tokens });
    Constituent mockConstituent = new Constituent("label", "viewName", ta, 2, 5);
    Relation mockRelation = new Relation("rel", mockConstituent, mockConstituent, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartA(mockRelation);
    assertEquals(3, features.size());
    assertEquals("brown", features.get(0));
    assertEquals("fox", features.get(1));
    assertEquals("jumps", features.get(2));
}

@Test
public void test4()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox" };
    TextAnnotation ta = new TextAnnotation("test_corpus", "test_text", "", tokens);
    Constituent target = new Constituent("TestLabel", "viewName", ta, 1, 3);
    Relation relation = new Relation("testRel", null, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartB(relation);
    List<String> expected = Arrays.asList("quick", "brown");
    assertEquals(expected, features);
}

@Test
public void test5()
{
    String[] tokens = new String[]{ "John", "loves", "Mary" };
    String rawText = String.join(" ", tokens);
    int[] tokenOffsets = new int[]{ 0, 1, 2 };
    TextAnnotation ta = new TextAnnotation("Corpus", "Test", rawText, tokens, null);
    Constituent source = new Constituent("ARG0", "dummyView", ta, 0, 1);
    Constituent target = new Constituent("ARG1", "dummyView", ta, 2, 3);
    Relation relation = new Relation("testRelation", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartC(relation);
    List<String> expected = new ArrayList<>();
    expected.add("singleword_" + ta.getToken(1));
    assertEquals(expected, features);
}

@Test
public void test6()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(mockTextAnnotation.getToken(3)).thenReturn("is");
    when(mockTextAnnotation.getToken(4)).thenReturn("a");
    when(mockTextAnnotation.getToken(5)).thenReturn("test");
    Constituent mockSource = mock(Constituent.class);
    when(mockSource.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Constituent mockSourceHead = mock(Constituent.class);
    when(mockSourceHead.getEndSpan()).thenReturn(3);
    Constituent mockTarget = mock(Constituent.class);
    Constituent mockTargetHead = mock(Constituent.class);
    when(mockTargetHead.getStartSpan()).thenReturn(6);
    Relation mockRelation = mock(Relation.class);
    when(mockRelation.getSource()).thenReturn(mockSource);
    when(mockRelation.getTarget()).thenReturn(mockTarget);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartCC(Relation r) {
            List<String> ret_features = new ArrayList<>();
            Constituent source = r.getSource();
            TextAnnotation ta = source.getTextAnnotation();
            Constituent source_head = mockSourceHead;
            Constituent target = r.getTarget();
            Constituent target_head = mockTargetHead;
            if (source_head.getEndSpan() < target_head.getStartSpan()) {
                ret_features.add("bowbethead_" + ta.getToken(3));
                ret_features.add("bowbethead_" + ta.getToken(4));
                ret_features.add("bowbethead_" + ta.getToken(5));
            }
            if (target_head.getEndSpan() < source_head.getStartSpan()) {
            }
            return ret_features;
        }
    };
    List<String> features = extractor.getLexicalFeaturePartCC(mockRelation);
    List<String> expected = new ArrayList<>();
    expected.add("bowbethead_is");
    expected.add("bowbethead_a");
    expected.add("bowbethead_test");
    assertEquals(expected, features);
}

@Test
public void test7()
{
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps", "over", "lazy", "dog" };
    TextAnnotation ta = new TextAnnotation("corpus", "view", "The quick brown fox jumps over lazy dog", new String[][]{ tokens });
    Constituent source = new Constituent("", "", ta, 1, 2);
    Constituent target = new Constituent("", "", ta, 5, 6);
    Relation r = new Relation("", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getLexicalFeaturePartD(r);
    List<String> expected = Arrays.asList("between_first_brown", "between_first_jumps", "in_between_fox");
    assertEquals(expected, result);
}

@Test
public void test8()
{
    List<String> tokens = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");
    Sentence sentence = mock(Sentence.class);
    when(sentence.getStartSpan()).thenReturn(0);
    when(sentence.getEndSpan()).thenReturn(9);
    TextAnnotation ta = mock(TextAnnotation.class);
    when(ta.getSentence(0)).thenReturn(sentence);
    for (int i = 0; i < tokens.size(); i++) {
        when(ta.getToken(i)).thenReturn(tokens.get(i));
    }
    Constituent source = mock(Constituent.class);
    when(source.getStartSpan()).thenReturn(3);
    when(source.getSentenceId()).thenReturn(0);
    when(source.getTextAnnotation()).thenReturn(ta);
    Constituent target = mock(Constituent.class);
    when(target.getEndSpan()).thenReturn(6);
    when(target.getSentenceId()).thenReturn(0);
    when(target.getTextAnnotation()).thenReturn(ta);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartE(relation);
    assertEquals(4, features.size());
    assertEquals("fwM1_" + tokens.get(2), features.get(0));
    assertEquals("swM1_" + tokens.get(1), features.get(1));
    assertEquals("fwM2_" + tokens.get(6), features.get(2));
    assertEquals("swM2_" + tokens.get(7), features.get(3));
}

@Test
public void test9()
{
    TextAnnotation ta = new TextAnnotation("", "", "", new String[0][], new String[0][0]);
    Constituent source = new Constituent("sourceLabel", "viewName", ta, 0, 1);
    Constituent target = new Constituent("targetLabel", "viewName", ta, 2, 3);
    Constituent sourceHead = new Constituent("sourceHead", "EntityHeads", ta, 0, 1) {
        @Override
        public String toString() {
            return "SOURCE_HEAD";
        }
    };
    Constituent targetHead = new Constituent("targetHead", "EntityHeads", ta, 2, 3) {
        @Override
        public String toString() {
            return "TARGET_HEAD";
        }
    };
    Relation relation = new Relation("relationLabel", source, target, 1.0) {
        @Override
        public Constituent getSource() {
            return source;
        }

        @Override
        public Constituent getTarget() {
            return target;
        }
    };
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartF(Relation r) {
            List<String> ret_features = new ArrayList<String>();
            Constituent source = r.getSource();
            Constituent target = r.getTarget();
            Constituent sourceHead = sourceHead = new Constituent("fake", "", ta, 0, 0) {
                @Override
                public String toString() {
                    return "SOURCE_HEAD";
                }
            };
            Constituent targetHead = targetHead = new Constituent("fake", "", ta, 0, 0) {
                @Override
                public String toString() {
                    return "TARGET_HEAD";
                }
            };
            String sourceHeadWord = sourceHead.toString();
            String targetHeadWord = targetHead.toString();
            ret_features.add("HM1_" + sourceHeadWord);
            ret_features.add("HM2_" + targetHeadWord);
            ret_features.add((("HM12_" + sourceHeadWord) + "_") + targetHeadWord);
            return ret_features;
        }
    };
    List<String> features = extractor.getLexicalFeaturePartF(relation);
    List<String> expected = Arrays.asList("HM1_SOURCE_HEAD", "HM2_TARGET_HEAD", "HM12_SOURCE_HEAD_TARGET_HEAD");
    assertEquals(expected, features);
}

@Test
public void test10()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getAttribute("EntityMentionType")).thenReturn("NAM");
    when(target.getAttribute("EntityMentionType")).thenReturn("NOM");
    when(source.getAttribute("EntityType")).thenReturn("PER");
    when(target.getAttribute("EntityType")).thenReturn("ORG");
    when(target.doesConstituentCover(source)).thenReturn(false);
    when(source.doesConstituentCover(target)).thenReturn(false);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getMentionFeature(relation);
    List<String> expectedFeatures = new ArrayList<>();
    expectedFeatures.add("source_mtype_PER");
    expectedFeatures.add("target_mtype_ORG");
    expectedFeatures.add("mlvl_NAM_NOM");
    expectedFeatures.add("mt_PER_ORG");
    expectedFeatures.add("mlvl_mt_NAM_PER_NOM_ORG");
    assertEquals(expectedFeatures, features);
}

@Test
public void test11()
{
    TextAnnotation mockTa = mock(TextAnnotation.class);
    View mockView = mock(View.class);
    when(mockTa.getView(SHALLOW_PARSE)).thenReturn(mockView);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getStartSpan()).thenReturn(1);
    when(source.getEndSpan()).thenReturn(2);
    when(target.getStartSpan()).thenReturn(4);
    when(target.getEndSpan()).thenReturn(5);
    when(source.getTextAnnotation()).thenReturn(mockTa);
    when(target.getTextAnnotation()).thenReturn(mockTa);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(sourceHead.getStartSpan()).thenReturn(1);
    when(sourceHead.getEndSpan()).thenReturn(2);
    when(targetHead.getStartSpan()).thenReturn(4);
    when(targetHead.getEndSpan()).thenReturn(5);
    when(sourceHead.getTextAnnotation()).thenReturn(mockTa);
    when(targetHead.getTextAnnotation()).thenReturn(mockTa);
    mockStaticGetEntityHeadForConstituent(source, mockTa, "TEST", sourceHead);
    mockStaticGetEntityHeadForConstituent(target, mockTa, "TEST", targetHead);
    when(mockView.getLabelsCoveringSpan(2, 3)).thenReturn(Arrays.asList("VP"));
    when(mockView.getLabelsCoveringSpan(2, 3)).thenReturn(Arrays.asList("VP"));
    when(mockView.getLabelsCoveringSpan(2, 4)).thenReturn(Arrays.asList("NP"));
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<Pair<String, String>> result = extractor.getShallowParseFeature(relation);
    assertEquals(2, result.size());
    assertEquals("chunker_between_heads_0", result.get(0).getFirst());
    assertEquals("VP", result.get(0).getSecond());
    assertEquals("chunker_between_extents_0", result.get(1).getFirst());
    assertEquals("NP", result.get(1).getSecond());
}

@Test
public void test12()
{
    TextAnnotation ta = new TextAnnotation("testCorpus", "testId", new String[]{ "John", "lives", "in", "New", "York" });
    View mentionAceView = new View(ViewNames.MENTION_ACE, "testGenerator", ta, 1.0);
    Constituent source = new Constituent("Person", ViewNames.MENTION_ACE, ta, 1, 3);
    source.addAttribute("EnityType", "PER");
    Constituent target = new Constituent("Location", ViewNames.MENTION_ACE, ta, 4, 5);
    target.addAttribute("EntityType", "LOC");
    mentionAceView.addConstituent(source);
    mentionAceView.addConstituent(target);
    Constituent middleMention = new Constituent("Location", ViewNames.MENTION_ACE, ta, 3, 4);
    mentionAceView.addConstituent(middleMention);
    ta.addView(MENTION_ACE, mentionAceView);
    Relation relation = new Relation("LocatedIn", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getStructualFeature(relation);
    assertTrue(features.contains("middle_mention_size_1"));
    assertTrue(features.contains("middle_word_size_1"));
    assertTrue(features.contains("m1_m2_no_coverage"));
    assertTrue(features.contains("cb1_PER_LOC_m1_m2_no_coverage"));
}

@Test
public void test13()
{
    Relation mockRelation = mock(Relation.class);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getTemplateFeature(Relation r) {
            List<String> features = new ArrayList<String>();
            features.add("is_formulaic_structure");
            features.add("is_preposition_structure");
            features.add("is_possessive_structure");
            features.add("is_premodifier_structure");
            return features;
        }
    };
    List<String> expected = Arrays.asList("is_formulaic_structure", "is_preposition_structure", "is_possessive_structure", "is_premodifier_structure");
    List<String> actual = extractor.getTemplateFeature(mockRelation);
    assertEquals(expected, actual);
}

@Test
public void test14()
{
    String[] tokens = new String[]{ "John", "manager", "Microsoft" };
    TextAnnotation ta = new TextAnnotation("corpus", "id", tokens);
    View posView = new View(ViewNames.POS, "POS", ta, 1.0);
    posView.addTokenLabel(0, "NNP", 1.0);
    posView.addTokenLabel(1, "NN", 1.0);
    posView.addTokenLabel(2, "NNP", 1.0);
    ta.addView(POS, posView);
    Constituent source = new Constituent("PER", "NER_CONLL", ta, 0, 1);
    source.addAttribute("EntityType", "PER");
    Constituent target = new Constituent("ORG", "NER_CONLL", ta, 2, 3);
    target.addAttribute("EntityType", "ORG");
    Relation rel = new Relation("testRel", source, target, 1.0);
    boolean result = RelationFeatureExtractor.isFormulaic(rel);
    assertTrue(result);
}

@Test
public void test15()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    View mockPosView = mock(View.class);
    when(mockTextAnnotation.getToken(1)).thenReturn("'s");
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(target.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(mockTextAnnotation.getView("POS")).thenReturn(mockPosView);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getEndSpan()).thenReturn(2);
    when(target.getStartSpan()).thenReturn(2);
    when(target.getEndSpan()).thenReturn(3);
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(sourceHead.getEndSpan()).thenReturn(1);
    when(targetHead.getStartSpan()).thenReturn(2);
    when(targetHead.getEndSpan()).thenReturn(3);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    when(mockPosView.getLabelsCoveringToken(anyInt())).thenReturn(Collections.singletonList("NN"));
    boolean result = RelationFeatureExtractor.isPossessive(new Relation(relation.getSource(), relation.getTarget(), "test", 1.0));
    assertTrue(result);
}

@Test
public void test16()
{
    TextAnnotation ta = mock(TextAnnotation.class);
    View posView = mock(View.class);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(target.getTextAnnotation()).thenReturn(ta);
    when(ta.getView(POS)).thenReturn(posView);
    when(source.getStartSpan()).thenReturn(0);
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(sourceHead.getEndSpan()).thenReturn(1);
    when(target.getStartSpan()).thenReturn(1);
    when(targetHead.getStartSpan()).thenReturn(1);
    when(targetHead.getEndSpan()).thenReturn(2);
    when(ta.getToken(1)).thenReturn(".");
    when(posView.getLabelsCoveringToken(0)).thenReturn(Collections.singletonList("JJ"));
    when(posView.getLabelsCoveringToken(1)).thenReturn(Collections.singletonList("NN"));
    Relation r = mock(Relation.class);
    when(r.getSource()).thenReturn(source);
    when(r.getTarget()).thenReturn(target);
    mockStatic(RelationFeatureExtractor.class);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(source, ta, "TEST")).thenReturn(sourceHead);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(target, ta, "TEST")).thenReturn(targetHead);
    when(RelationFeatureExtractor.onlyNounBetween(sourceHead, targetHead)).thenReturn(true);
    assertTrue(RelationFeatureExtractor.isPremodifier(r));
}

@Test
public void test17()
{
    String[] tokens = new String[]{ "John", "runs", "into", "the", "house" };
    String[] posTags = new String[]{ "NNP", "VBZ", "IN", "DT", "NN" };
    TextAnnotationBuilder tab = new TokenLabelViewTestHelper.TextAnnotationBuilder("testCorpus", "testText");
    for (int i = 0; i < tokens.length; i++) {
        tab.addTokenWithPOS(tokens[i], posTags[i]);
    }
    TextAnnotation ta = tab.createTextAnnotation();
    Constituent source = new Constituent("Person", "dummyView", ta, 0, 1);
    Constituent target = new Constituent("Location", "dummyView", ta, 4, 5);
    Relation r = new Relation("Located", source, target, 1.0);
    TokenLabelView posView = new TokenLabelView(ViewNames.POS, "testGenerator", ta, 1.0);
    for (int i = 0; i < tokens.length; i++) {
        posView.addTokenLabel(i, posTags[i], 1.0);
    }
    ta.addView(POS, posView);
    boolean result = RelationFeatureExtractor.isPreposition(r);
    assertTrue(result);
}

@Test
public void test18()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    View mockPosView = mock(View.class);
    when(mockTextAnnotation.getView(POS)).thenReturn(mockPosView);
    when(mockPosView.getLabelsCoveringToken(1)).thenReturn(Collections.singletonList("NN"));
    when(mockPosView.getLabelsCoveringToken(2)).thenReturn(Collections.singletonList("NNS"));
    Constituent front = mock(Constituent.class);
    Constituent back = mock(Constituent.class);
    when(front.getEndSpan()).thenReturn(1);
    when(back.getStartSpan()).thenReturn(3);
    when(front.getTextAnnotation()).thenReturn(mockTextAnnotation);
    boolean result = RelationFeatureExtractor.onlyNounBetween(front, back);
    assertTrue(result);
}

@Test
public void test19()
{
    String[] tokens = new String[]{ "Barack", "Obama", "was", "born", "in", "Hawaii", "." };
    String[][] sentence = new String[][]{ tokens };
    TextAnnotation textAnnotation = BasicTextAnnotationBuilder.createTextAnnotationFromTokens("testCorpus", "testId", sentence);
    Constituent extentConstituent = new Constituent("PER", 1.0, ViewNames.MENTION, textAnnotation, 0, 2);
    extentConstituent.addAttribute(EntityHeadStartCharOffset, String.valueOf(textAnnotation.getTokenCharacterOffset(0).getFirst()));
    extentConstituent.addAttribute(EntityHeadEndCharOffset, String.valueOf(textAnnotation.getTokenCharacterOffset(1).getSecond()));
    Constituent head = RelationFeatureExtractor.getEntityHeadForConstituent(extentConstituent, textAnnotation, MENTION);
    assertNotNull(head);
    assertEquals("PER", head.getLabel());
    assertEquals(0, head.getStartSpan());
    assertEquals(2, head.getEndSpan());
    assertEquals(extentConstituent.getAttribute(EntityHeadStartCharOffset), head.getAttribute(EntityHeadStartCharOffset));
}

@Test
public void test20()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    TextAnnotation ta = mock(TextAnnotation.class);
    View depView = mock(TreeView.class);
    View reAnnotatedView = mock(View.class);
    View posView = mock(View.class);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    Constituent depConstituentSource = mock(Constituent.class);
    Constituent depConstituentTarget = mock(Constituent.class);
    Constituent pathConstituent = mock(Constituent.class);
    Constituent posConstituent = mock(Constituent.class);
    Constituent wnConstituent = mock(Constituent.class);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(source.getSentenceId()).thenReturn(0);
    when(target.getSentenceId()).thenReturn(0);
    when(ta.getView(DEPENDENCY_STANFORD)).thenReturn(depView);
    when(ta.getView("RE_ANNOTATED")).thenReturn(reAnnotatedView);
    when(ta.getView(POS)).thenReturn(posView);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(target.getTextAnnotation()).thenReturn(ta);
    when(source.getStartSpan()).thenReturn(1);
    when(target.getStartSpan()).thenReturn(3);
    when(sourceHead.getStartSpan()).thenReturn(1);
    when(targetHead.getStartSpan()).thenReturn(3);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    when(depView.getConstituentsCoveringToken(1)).thenReturn(Arrays.asList(depConstituentSource));
    when(depView.getConstituentsCoveringToken(3)).thenReturn(Arrays.asList(depConstituentTarget));
    when(PathFeatureHelper.getPathConstituents(depConstituentSource, depConstituentTarget, 100)).thenReturn(Arrays.asList(pathConstituent));
    when(pathConstituent.getLabel()).thenReturn("NN");
    when(pathConstituent.getStartSpan()).thenReturn(2);
    when(posView.getConstituentsCoveringToken(2)).thenReturn(Arrays.asList(posConstituent));
    when(reAnnotatedView.getConstituentsCoveringToken(2)).thenReturn(Arrays.asList(wnConstituent));
    when(posConstituent.getLabel()).thenReturn("NNP");
    when(wnConstituent.getAttribute("WORDNETTAG")).thenReturn("person");
    List<Pair<String, String>> result = RelationFeatureExtractor.getDependencyFeature(relation);
    assertEquals(3, result.size());
    assertEquals(new Pair<>("tag_0", "NN"), result.get(0));
    assertEquals(new Pair<>("pos_tag_0", "NNP"), result.get(1));
    assertEquals(new Pair<>("wordnettag_0", "person"), result.get(2));
}

@Test
public void test21()
{
    List<String> tokens = Arrays.asList("John", "visited", ",", "Paris");
    List<String> sentence = new ArrayList<>(tokens);
    TextAnnotation ta = new TextAnnotation("testCorpus", "testId", sentence);
    ta.addView(TOKENS, new TokenLabelView(ViewNames.TOKENS, "testGenerator", ta, 1.0));
    TreeView depView = new TreeView(ViewNames.DEPENDENCY_STANFORD, "mockDepView", ta, 1.0);
    Constituent head = new Constituent("NNP", ViewNames.DEPENDENCY_STANFORD, ta, 0, 1);
    depView.addConstituent(head);
    ta.addView(DEPENDENCY_STANFORD, depView);
    Constituent source = new Constituent("PER", "dummyView", ta, 0, 1);
    source.addAttribute("EntityType", "PERSON");
    Constituent target = new Constituent("LOC", "dummyView", ta, 0, 1);
    target.addAttribute("EntityType", "GPE");
    List<String> features = RelationFeatureExtractor.patternRecognition(source, target);
    assertEquals(1, features.size());
    assertTrue(features.contains("SAME_SOURCE_TARGET_EXCEPTION"));
}

