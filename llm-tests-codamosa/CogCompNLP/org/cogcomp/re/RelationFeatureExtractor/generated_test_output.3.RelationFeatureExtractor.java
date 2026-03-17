import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getAttribute("EntityID")).thenReturn("E123");
    when(target.getAttribute("EntityID")).thenReturn("E123");
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
    String sentence = String.join(" ", tokens);
    TextAnnotation ta = new TextAnnotation("corpus", "id", new String[][]{ tokens });
    View view = new SpanLabelView("DummyView", "test", ta, 1.0);
    Constituent source = new Constituent("source", "DummyView", ta, 1, 4);
    view.addConstituent(source);
    Constituent target = new Constituent("target", "DummyView", ta, 5, 8);
    view.addConstituent(target);
    ta.addView("EntityHeads", view);
    Relation relation = new Relation("dummyRelation", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        public Constituent getEntityHeadForConstituent(Constituent c, TextAnnotation ta, String viewName) {
            return new Constituent(c.getLabel(), c.getViewName(), ta, c.getStartSpan(), c.getStartSpan() + 1);
        }
    };
    List<String> features = extractor.getCollocationsFeature(relation);
    assertNotNull(features);
    assertEquals(10, features.size());
    assertTrue(features.get(0).startsWith("s_m1_p1_"));
    assertTrue(features.get(5).startsWith("t_m1_p1_"));
}

@Test
public void test3()
{
    TextAnnotation ta = mock(TextAnnotation.class);
    when(ta.getToken(2)).thenReturn("The");
    when(ta.getToken(3)).thenReturn("quick");
    when(ta.getToken(4)).thenReturn("brown");
    Constituent source = mock(Constituent.class);
    when(source.getStartSpan()).thenReturn(2);
    when(source.getEndSpan()).thenReturn(5);
    when(source.getTextAnnotation()).thenReturn(ta);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getLexicalFeaturePartA(relation);
    List<String> expected = new ArrayList<String>();
    expected.add("The");
    expected.add("quick");
    expected.add("brown");
    assertEquals(expected, result);
}

@Test
public void test4()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(mockTextAnnotation.getToken(1)).thenReturn("quick");
    when(mockTextAnnotation.getToken(2)).thenReturn("brown");
    when(mockTextAnnotation.getToken(3)).thenReturn("fox");
    Constituent mockConstituent = mock(Constituent.class);
    when(mockConstituent.getStartSpan()).thenReturn(1);
    when(mockConstituent.getEndSpan()).thenReturn(4);
    when(mockConstituent.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Relation mockRelation = mock(Relation.class);
    when(mockRelation.getTarget()).thenReturn(mockConstituent);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getLexicalFeaturePartB(mockRelation);
    List<String> expected = Arrays.asList("quick", "brown", "fox");
    assertEquals(expected, result);
}

@Test
public void test5()
{
    String[] tokens = new String[]{ "John", "loves", "Mary" };
    TextAnnotation ta = new TextAnnotation("dummy_corpus", "dummy_view", "John loves Mary");
    for (String token : tokens) {
    }
    TextAnnotation mockTa = new TextAnnotation("corpus", "view", "John loves Mary") {
        @Override
        public String getToken(int index) {
            return tokens[index];
        }
    };
    Constituent source = new Constituent("John", "viewName", mockTa, 0, 1);
    Constituent target = new Constituent("Mary", "viewName", mockTa, 2, 3);
    Relation relation = new Relation("relationName", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartC(relation);
    List<String> expected = new ArrayList<>();
    expected.add("singleword_" + tokens[1]);
    assertEquals(expected, features);
}

@Test
public void test6()
{
    TextAnnotation mockTA = mock(TextAnnotation.class);
    when(mockTA.getToken(2)).thenReturn("word1");
    when(mockTA.getToken(3)).thenReturn("word2");
    Constituent mockSource = mock(Constituent.class);
    when(mockSource.getTextAnnotation()).thenReturn(mockTA);
    Constituent mockTarget = mock(Constituent.class);
    when(mockTarget.getTextAnnotation()).thenReturn(mockTA);
    Constituent mockSourceHead = mock(Constituent.class);
    when(mockSourceHead.getEndSpan()).thenReturn(2);
    when(mockSourceHead.getStartSpan()).thenReturn(1);
    Constituent mockTargetHead = mock(Constituent.class);
    when(mockTargetHead.getStartSpan()).thenReturn(4);
    when(mockTargetHead.getEndSpan()).thenReturn(5);
    Relation mockRelation = mock(Relation.class);
    when(mockRelation.getSource()).thenReturn(mockSource);
    when(mockRelation.getTarget()).thenReturn(mockTarget);
    mockStatic(RelationFeatureExtractor.class);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(mockSource, mockTA, "TEST")).thenReturn(mockSourceHead);
    when(RelationFeatureExtractor.getEntityHeadForConstituent(mockTarget, mockTA, "TEST")).thenReturn(mockTargetHead);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartCC(mockRelation);
    List<String> expectedFeatures = new ArrayList<>();
    expectedFeatures.add("bowbethead_word1");
    expectedFeatures.add("bowbethead_word2");
    assertEquals(expectedFeatures, features);
}

@Test
public void test7()
{
    TextAnnotation ta = mock(TextAnnotation.class);
    when(ta.getToken(3)).thenReturn("token3");
    when(ta.getToken(5)).thenReturn("token5");
    when(ta.getToken(4)).thenReturn("token4");
    Constituent source = mock(Constituent.class);
    when(source.getEndSpan()).thenReturn(2);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getTextAnnotation()).thenReturn(ta);
    Constituent target = mock(Constituent.class);
    when(target.getStartSpan()).thenReturn(6);
    when(target.getEndSpan()).thenReturn(7);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getLexicalFeaturePartD(relation);
    List<String> expected = Arrays.asList("between_first_token3", "between_first_token5", "in_between_token4");
    assertEquals(expected, result);
}

@Test
public void test8()
{
    TextAnnotation ta = mock(TextAnnotation.class);
    Sentence sentence = mock(Sentence.class);
    when(sentence.getStartSpan()).thenReturn(2);
    when(sentence.getEndSpan()).thenReturn(8);
    when(ta.getSentence(0)).thenReturn(sentence);
    when(ta.getToken(1)).thenReturn("beforeSource2");
    when(ta.getToken(0)).thenReturn("beforeSource3");
    when(ta.getToken(5)).thenReturn("afterTarget1");
    when(ta.getToken(6)).thenReturn("afterTarget2");
    Constituent source = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(source.getSentenceId()).thenReturn(0);
    when(source.getStartSpan()).thenReturn(3);
    Constituent target = mock(Constituent.class);
    when(target.getTextAnnotation()).thenReturn(ta);
    when(target.getSentenceId()).thenReturn(0);
    when(target.getEndSpan()).thenReturn(5);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartE(relation);
    List<String> expected = Arrays.asList("fwM1_beforeSource2", "swM1_NULL", "fwM2_afterTarget1", "swM2_afterTarget2");
    assertEquals(expected, features);
}

@Test
public void test9()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(source.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(target.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(sourceHead.toString()).thenReturn("sourceHeadWord");
    when(targetHead.toString()).thenReturn("targetHeadWord");
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartF(Relation r) {
            List<String> ret_features = new ArrayList<String>();
            Constituent s = r.getSource();
            Constituent t = r.getTarget();
            Constituent sHead = sourceHead;
            Constituent tHead = targetHead;
            String sHeadWord = sHead.toString();
            String tHeadWord = tHead.toString();
            ret_features.add("HM1_" + sHeadWord);
            ret_features.add("HM2_" + tHeadWord);
            ret_features.add((("HM12_" + sHeadWord) + "_") + tHeadWord);
            return ret_features;
        }
    };
    List<String> features = extractor.getLexicalFeaturePartF(relation);
    assertEquals(3, features.size());
    assertEquals("HM1_sourceHeadWord", features.get(0));
    assertEquals("HM2_targetHeadWord", features.get(1));
    assertEquals("HM12_sourceHeadWord_targetHeadWord", features.get(2));
}

@Test
public void test10()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getAttribute("EntityMentionType")).thenReturn("NAM");
    when(target.getAttribute("EntityMentionType")).thenReturn("NOM");
    when(source.getAttribute("EntityType")).thenReturn("ORG");
    when(target.getAttribute("EntityType")).thenReturn("PER");
    when(target.doesConstituentCover(source)).thenReturn(false);
    when(source.doesConstituentCover(target)).thenReturn(false);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getMentionFeature(relation);
    List<String> expected = Arrays.asList("source_mtype_ORG", "target_mtype_PER", "mlvl_NAM_NOM", "mt_ORG_PER", "mlvl_mt_NAM_ORG_NOM_PER");
    assertEquals(expected, features);
}

@Test
public void test11()
{
    Relation relationMock = mock(Relation.class);
    Constituent sourceMock = mock(Constituent.class);
    Constituent targetMock = mock(Constituent.class);
    TextAnnotation taMock = mock(TextAnnotation.class);
    View shallowParseViewMock = mock(View.class);
    Constituent sourceHeadMock = mock(Constituent.class);
    Constituent targetHeadMock = mock(Constituent.class);
    when(relationMock.getSource()).thenReturn(sourceMock);
    when(relationMock.getTarget()).thenReturn(targetMock);
    when(sourceMock.getTextAnnotation()).thenReturn(taMock);
    when(sourceMock.getStartSpan()).thenReturn(1);
    when(sourceMock.getEndSpan()).thenReturn(2);
    when(targetMock.getStartSpan()).thenReturn(4);
    when(targetMock.getEndSpan()).thenReturn(5);
    when(sourceHeadMock.getStartSpan()).thenReturn(1);
    when(sourceHeadMock.getEndSpan()).thenReturn(2);
    when(targetHeadMock.getStartSpan()).thenReturn(4);
    when(targetHeadMock.getEndSpan()).thenReturn(5);
    when(taMock.getView(SHALLOW_PARSE)).thenReturn(shallowParseViewMock);
    when(shallowParseViewMock.getLabelsCoveringSpan(2, 3)).thenReturn(Arrays.asList("NP", "VP"));
    when(shallowParseViewMock.getLabelsCoveringSpan(2, 3)).thenReturn(Arrays.asList("NP", "VP"));
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<Pair<String, String>> getShallowParseFeature(Relation r) {
            return super.getShallowParseFeature(r);
        }
    };
    RelationFeatureExtractor featureExtractor = new RelationFeatureExtractor() {
        public List<Pair<String, String>> getShallowParseFeature(Relation r) {
            return new RelationFeatureExtractor() {
                public List<Pair<String, String>> getShallowParseFeature(Relation r) {
                    return RelationFeatureExtractor.super.getShallowParseFeature(r);
                }
            }.getShallowParseFeature(r);
        }
    };
    List<Pair<String, String>> result = Arrays.asList(new Pair<>("chunker_between_heads_0", "NP"), new Pair<>("chunker_between_heads_1", "VP"), new Pair<>("chunker_between_extents_0", "NP"), new Pair<>("chunker_between_extents_1", "VP"));
    assertEquals(4, result.size());
    assertEquals("chunker_between_heads_0", result.get(0).getFirst());
    assertEquals("NP", result.get(0).getSecond());
    assertEquals("chunker_between_heads_1", result.get(1).getFirst());
    assertEquals("VP", result.get(1).getSecond());
    assertEquals("chunker_between_extents_0", result.get(2).getFirst());
    assertEquals("NP", result.get(2).getSecond());
    assertEquals("chunker_between_extents_1", result.get(3).getFirst());
    assertEquals("VP", result.get(3).getSecond());
}

@Test
public void test12()
{
    String[] tokens = new String[]{ "John", "lives", "in", "New", "York", "." };
    TextAnnotation ta = new TextAnnotation("dummy_corpus", "dummy_id", "", tokens, new int[][]{ new int[]{ 0, 4 }, new int[]{ 5, 10 }, new int[]{ 11, 13 }, new int[]{ 14, 17 }, new int[]{ 18, 22 }, new int[]{ 23, 24 } });
    Constituent source = new Constituent("SOURCE", "dummy_view", ta, 0, 1);
    Constituent target = new Constituent("TARGET", "dummy_view", ta, 3, 4);
    source.addAttribute("EnityType", "PER");
    target.addAttribute("EntityType", "LOC");
    View mentionView = new View(ViewNames.MENTION_ACE, "test_generator", ta, 1.0);
    Constituent mid1 = new Constituent("Mention", ViewNames.MENTION_ACE, ta, 1, 2);
    Constituent mid2 = new Constituent("Mention", ViewNames.MENTION_ACE, ta, 2, 3);
    mentionView.addConstituent(mid1);
    mentionView.addConstituent(mid2);
    ta.addView(MENTION_ACE, mentionView);
    Relation relation = new Relation("relatedTo", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getStructualFeature(relation);
    assertTrue(features.contains("middle_mention_size_2"));
    assertTrue(features.contains("middle_word_size_2"));
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
            List<String> ret_features = new ArrayList<String>();
            ret_features.add("is_formulaic_structure");
            ret_features.add("is_preposition_structure");
            ret_features.add("is_possessive_structure");
            ret_features.add("is_premodifier_structure");
            return ret_features;
        }
    };
    List<String> features = extractor.getTemplateFeature(mockRelation);
    List<String> expected = Arrays.asList("is_formulaic_structure", "is_preposition_structure", "is_possessive_structure", "is_premodifier_structure");
    assertEquals(expected, features);
}

@Test
public void test14()
{
    TextAnnotation textAnnotation = mock(TextAnnotation.class);
    View posView = mock(View.class);
    when(posView.getLabelsCoveringToken(2)).thenReturn(Collections.singletonList("NN"));
    when(posView.getLabelsCoveringToken(3)).thenReturn(Collections.singletonList("NN"));
    when(textAnnotation.getView(POS)).thenReturn(posView);
    Constituent source = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(textAnnotation);
    when(source.getAttribute("EntityType")).thenReturn("PER");
    Constituent target = mock(Constituent.class);
    when(target.getTextAnnotation()).thenReturn(textAnnotation);
    when(target.getAttribute("EntityType")).thenReturn("ORG");
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(sourceHead.getEndSpan()).thenReturn(2);
    when(targetHead.getStartSpan()).thenReturn(4);
    when(targetHead.getEndSpan()).thenReturn(5);
    boolean result = RelationFeatureExtractor.isFormulaic(new Relation("", source, target, 1.0F));
    assertTrue(result);
}

@Test
public void test15()
{
    String[] tokens = new String[]{ "John", "'s", "book" };
    TextAnnotation ta = new TextAnnotation("corpus", "id", tokens);
    View posView = new TokenLabelView(ViewNames.POS, "testGenerator", ta, 1.0);
    posView.addTokenLabel(0, "NNP", 1.0);
    posView.addTokenLabel(1, "POS", 1.0);
    posView.addTokenLabel(2, "NN", 1.0);
    ta.addView(POS, posView);
    Constituent source = new Constituent("PER", "testView", ta, 0, 1);
    Constituent target = new Constituent("OBJ", "testView", ta, 2, 3);
    Relation relation = new Relation("possessive", source, target, 1.0);
    Constituent sourceHead = new Constituent("PER_HEAD", "headView", ta, 0, 1);
    Constituent targetHead = new Constituent("OBJ_HEAD", "headView", ta, 2, 3);
    RelationFeatureExtractor.getEntityHeadForConstituent = ( constituent, textAnnotation, viewName) -> {
        if (constituent == source) {
            return sourceHead;
        } else {
            return targetHead;
        }
    };
    boolean result = RelationFeatureExtractor.isPossessive(relation);
    assertTrue(result);
}

@Test
public void test16()
{
    String[] tokens = new String[]{ "The", "fast", "car" };
    String text = String.join(" ", tokens);
    TextAnnotation ta = new TextAnnotation("testCorpus", "testView", text);
    ta.initializeTokenizedTextAnnotation(tokens);
    View posView = new View(ViewNames.POS, "testAnnotator", ta, 1.0);
    posView.addSpanLabel(0, 1, "DT", 1.0);
    posView.addSpanLabel(1, 2, "JJ", 1.0);
    posView.addSpanLabel(2, 3, "NN", 1.0);
    ta.addView(POS, posView);
    Constituent source = new Constituent("Entity1", "TestView", ta, 1, 2);
    Constituent target = new Constituent("Entity2", "TestView", ta, 2, 3);
    Relation r = new Relation("TestRelation", source, target, 1.0);
    Constituent sourceHead = new Constituent("Entity1Head", "TestView", ta, 1, 2);
    Constituent targetHead = new Constituent("Entity2Head", "TestView", ta, 2, 3);
    RelationFeatureExtractor.getEntityHeadForConstituent = ( c, taArg, dummy) -> {
        if (c == source) {
            return sourceHead;
        } else {
            return targetHead;
        }
    };
    RelationFeatureExtractor.onlyNounBetween = ( c1, c2) -> false;
    boolean result = RelationFeatureExtractor.isPremodifier(r);
    assertTrue(result);
}

@Test
public void test17()
{
    String[] tokens = new String[]{ "The", "cat", "on", "the", "mat" };
    int[] sentenceStartOffsets = new int[]{ 0 };
    TextAnnotationBuilder builder = new TokenLabelViewTextAnnotationBuilder();
    TextAnnotation ta = builder.createTextAnnotation("test_corpus", "test_text", tokens);
    TokenLabelView posView = new TokenLabelView(ViewNames.POS, "test", ta, 1.0);
    posView.addTokenLabel(0, "DT", 1.0);
    posView.addTokenLabel(1, "NN", 1.0);
    posView.addTokenLabel(2, "IN", 1.0);
    posView.addTokenLabel(3, "DT", 1.0);
    posView.addTokenLabel(4, "NN", 1.0);
    ta.addView(POS, posView);
    Constituent source = new Constituent("entity1", "test", ta, 0, 1);
    Constituent target = new Constituent("entity2", "test", ta, 4, 5);
    Relation relation = new Relation("testRel", source, target, 1.0F);
    boolean result = RelationFeatureExtractor.isPreposition(relation);
    assertTrue(result);
}

@Test
public void test18()
{
    Constituent front = mock(Constituent.class);
    when(front.getEndSpan()).thenReturn(1);
    Constituent back = mock(Constituent.class);
    when(back.getStartSpan()).thenReturn(3);
    TextAnnotation ta = mock(TextAnnotation.class);
    when(front.getTextAnnotation()).thenReturn(ta);
    View posView = mock(View.class);
    when(ta.getView(POS)).thenReturn(posView);
    when(posView.getLabelsCoveringToken(1)).thenReturn(Collections.singletonList("NN"));
    when(posView.getLabelsCoveringToken(2)).thenReturn(Collections.singletonList("NNS"));
    boolean result = RelationFeatureExtractor.onlyNounBetween(front, back);
    assertTrue(result);
}

@Test
public void test19()
{
    String[] tokens = new String[]{ "John", "Doe", "is", "a", "developer" };
    String text = String.join(" ", tokens);
    TextAnnotation ta = new TextAnnotation("test_corpus", "test_text", text);
    View dummyView = new View("dummyView", "testGenerator", ta, 1.0);
    int startCharOffset = text.indexOf("John");
    int endCharOffset = text.indexOf("Doe") + "Doe".length();
    int startToken = ta.getTokenIdFromCharacterOffset(startCharOffset);
    int endToken = ta.getTokenIdFromCharacterOffset(endCharOffset - 1);
    Constituent extentConstituent = new Constituent("PERSON", 1.0, "dummyView", ta, startToken, endToken + 1);
    extentConstituent.addAttribute(EntityHeadStartCharOffset, String.valueOf(startCharOffset));
    extentConstituent.addAttribute(EntityHeadEndCharOffset, String.valueOf(endCharOffset));
    Constituent head = RelationFeatureExtractor.getEntityHeadForConstituent(extentConstituent, ta, "dummyView");
    assertNotNull(head);
    assertEquals("PERSON", head.getLabel());
    assertEquals(startToken, head.getStartSpan());
    assertEquals(endToken + 1, head.getEndSpan());
    assertEquals(String.valueOf(startCharOffset), head.getAttribute(EntityHeadStartCharOffset));
    assertEquals(String.valueOf(endCharOffset), head.getAttribute(EntityHeadEndCharOffset));
}

@Test
public void test20()
{
    TextAnnotation textAnnotation = mock(TextAnnotation.class);
    TreeView dependencyView = mock(TreeView.class);
    View posView = mock(View.class);
    View annotatedView = mock(View.class);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    Constituent sourceParsed = mock(Constituent.class);
    Constituent targetParsed = mock(Constituent.class);
    Constituent pathConstituent = mock(Constituent.class);
    Constituent posTagged = mock(Constituent.class);
    Constituent annotatedTagged = mock(Constituent.class);
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(targetHead.getStartSpan()).thenReturn(1);
    when(sourceParsed.getStartSpan()).thenReturn(0);
    when(targetParsed.getStartSpan()).thenReturn(1);
    when(pathConstituent.getStartSpan()).thenReturn(0);
    when(pathConstituent.getLabel()).thenReturn("NN");
    when(posTagged.getLabel()).thenReturn("POS_LABEL");
    when(annotatedTagged.getAttribute("WORDNETTAG")).thenReturn("WNT");
    when(dependencyView.getConstituentsCoveringToken(0)).thenReturn(Collections.singletonList(sourceParsed));
    when(dependencyView.getConstituentsCoveringToken(1)).thenReturn(Collections.singletonList(targetParsed));
    when(posView.getConstituentsCoveringToken(0)).thenReturn(Collections.singletonList(posTagged));
    when(annotatedView.getConstituentsCoveringToken(0)).thenReturn(Collections.singletonList(annotatedTagged));
    when(textAnnotation.getView(DEPENDENCY_STANFORD)).thenReturn(dependencyView);
    when(textAnnotation.getView(POS)).thenReturn(posView);
    when(textAnnotation.getView("RE_ANNOTATED")).thenReturn(annotatedView);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(textAnnotation);
    when(target.getTextAnnotation()).thenReturn(textAnnotation);
    when(source.getSentenceId()).thenReturn(1);
    when(target.getSentenceId()).thenReturn(1);
    List<Constituent> pathList = new ArrayList<>();
    pathList.add(pathConstituent);
    mockStaticPathFeatureHelper(pathList);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    List<Pair<String, String>> features = RelationFeatureExtractor.getDependencyFeature(relation);
    assertEquals(3, features.size());
    assertEquals("tag_0", features.get(0).getFirst());
    assertEquals("NN", features.get(0).getSecond());
    assertEquals("pos_tag_0", features.get(1).getFirst());
    assertEquals("POS_LABEL", features.get(1).getSecond());
    assertEquals("wordnettag_0", features.get(2).getFirst());
    assertEquals("WNT", features.get(2).getSecond());
}

@Test
public void test21()
{
    TextAnnotation ta = new TextAnnotation("CORP", "GENERIC", new String[][]{ new String[]{ "John", "met", "Mary" } });
    TreeView tokensView = new TreeView(ViewNames.TOKENS, ta);
    for (int i = 0; i < 3; i++) {
        Constituent c = new Constituent("tok", ViewNames.TOKENS, ta, i, i + 1);
        tokensView.addConstituent(c);
    }
    ta.addView(TOKENS, tokensView);
    TreeView depView = new TreeView(ViewNames.DEPENDENCY_STANFORD, ta);
    Constituent depNode = new Constituent("dep", ViewNames.DEPENDENCY_STANFORD, ta, 0, 1);
    depView.addConstituent(depNode);
    ta.addView(DEPENDENCY_STANFORD, depView);
    Constituent source = new Constituent("PER", "NER", ta, 0, 1);
    Constituent target = new Constituent("PER", "NER", ta, 0, 1);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        public static Constituent getEntityHeadForConstituent(Constituent c, TextAnnotation ta, String type) {
            return new Constituent("HEAD", "HEAD_LABEL", ta, 0, 1);
        }
    };
    List<String> features = RelationFeatureExtractor.patternRecognition(source, target);
    assertEquals(1, features.size());
    assertTrue(features.contains("SAME_SOURCE_TARGET_EXCEPTION"));
}

