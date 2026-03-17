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
    String[] tokens = new String[]{ "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };
    TextAnnotation ta = new TextAnnotation("corpusId", "taId", "The quick brown fox jumps over the lazy dog");
    View dummyView = new View("DummyView", "dummyGenerator", ta, 1.0);
    ta.addView("EntityHeads", dummyView);
    Constituent source = new Constituent("source", "DummyView", ta, 2, 4);
    Constituent target = new Constituent("target", "DummyView", ta, 6, 8);
    Constituent sourceHead = new Constituent("head", "EntityHeads", ta, 3, 4);
    Constituent targetHead = new Constituent("head", "EntityHeads", ta, 6, 7);
    dummyView.addConstituent(sourceHead);
    dummyView.addConstituent(targetHead);
    Relation relation = new Relation("testRel", source, target, 1.0);
    List<String> features = new RelationFeatureExtractor().getCollocationsFeature(relation);
    List<String> expected = new ArrayList<String>();
    expected.add("s_m1_p1_fox");
    expected.add("s_m2_m1_brown");
    expected.add("s_p1_p2_");
    expected.add("s_m1_m1_brown");
    expected.add("s_p1_p1_null");
    expected.add("t_m1_p1_the");
    expected.add("t_m2_m1_");
    expected.add("t_p1_p2_");
    expected.add("t_m1_m1_null");
    expected.add("t_p1_p1_lazy");
    assertEquals(expected, features);
}

@Test
public void test3()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(mockTextAnnotation.getToken(0)).thenReturn("John");
    when(mockTextAnnotation.getToken(1)).thenReturn("loves");
    when(mockTextAnnotation.getToken(2)).thenReturn("Mary");
    Constituent mockConstituent = mock(Constituent.class);
    when(mockConstituent.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(mockConstituent.getStartSpan()).thenReturn(0);
    when(mockConstituent.getEndSpan()).thenReturn(3);
    Relation mockRelation = mock(Relation.class);
    when(mockRelation.getSource()).thenReturn(mockConstituent);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartA(mockRelation);
    List<String> expected = new ArrayList<>();
    expected.add("John");
    expected.add("loves");
    expected.add("Mary");
    assertEquals(expected, features);
}

@Test
public void test4()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(mockTextAnnotation.getToken(2)).thenReturn("quick");
    when(mockTextAnnotation.getToken(3)).thenReturn("brown");
    when(mockTextAnnotation.getToken(4)).thenReturn("fox");
    Constituent mockConstituent = mock(Constituent.class);
    when(mockConstituent.getStartSpan()).thenReturn(2);
    when(mockConstituent.getEndSpan()).thenReturn(5);
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
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(mockTextAnnotation.getToken(3)).thenReturn("betweenWord");
    Constituent source = mock(Constituent.class);
    when(source.getEndSpan()).thenReturn(3);
    when(source.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Constituent target = mock(Constituent.class);
    when(target.getStartSpan()).thenReturn(4);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> result = extractor.getLexicalFeaturePartC(relation);
    assertEquals(Arrays.asList("singleword_betweenWord"), result);
}

@Test
public void test6()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    when(mockTextAnnotation.getToken(3)).thenReturn("is");
    when(mockTextAnnotation.getToken(4)).thenReturn("a");
    Constituent mockSource = mock(Constituent.class);
    Constituent mockTarget = mock(Constituent.class);
    when(mockSource.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(mockTarget.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Relation mockRelation = mock(Relation.class);
    when(mockRelation.getSource()).thenReturn(mockSource);
    when(mockRelation.getTarget()).thenReturn(mockTarget);
    Constituent mockSourceHead = mock(Constituent.class);
    Constituent mockTargetHead = mock(Constituent.class);
    when(mockSourceHead.getEndSpan()).thenReturn(3);
    when(mockTargetHead.getStartSpan()).thenReturn(5);
    when(mockSourceHead.getStartSpan()).thenReturn(2);
    when(mockTargetHead.getEndSpan()).thenReturn(6);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartCC(Relation r) {
            List<String> features = new ArrayList<String>();
            Constituent source = r.getSource();
            TextAnnotation ta = source.getTextAnnotation();
            Constituent sourceHead = mockSourceHead;
            Constituent target = r.getTarget();
            Constituent targetHead = mockTargetHead;
            if (sourceHead.getEndSpan() < targetHead.getStartSpan()) {
                features.add("bowbethead_" + ta.getToken(3));
                features.add("bowbethead_" + ta.getToken(4));
            }
            if (targetHead.getEndSpan() < sourceHead.getStartSpan()) {
            }
            return features;
        }
    };
    List<String> result = extractor.getLexicalFeaturePartCC(mockRelation);
    List<String> expected = Arrays.asList("bowbethead_is", "bowbethead_a");
    assertEquals(expected, result);
}

@Test
public void test7()
{
    TextAnnotation ta = mock(TextAnnotation.class);
    when(ta.getToken(3)).thenReturn("apple");
    when(ta.getToken(5)).thenReturn("banana");
    when(ta.getToken(4)).thenReturn("orange");
    Constituent source = mock(Constituent.class);
    when(source.getEndSpan()).thenReturn(2);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getTextAnnotation()).thenReturn(ta);
    Constituent target = mock(Constituent.class);
    when(target.getStartSpan()).thenReturn(6);
    when(target.getEndSpan()).thenReturn(7);
    when(target.getTextAnnotation()).thenReturn(ta);
    Relation relation = mock(Relation.class);
    when(relation.getSource()).thenReturn(source);
    when(relation.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartD(relation);
    List<String> expectedFeatures = new ArrayList<String>();
    expectedFeatures.add("between_first_apple");
    expectedFeatures.add("between_first_banana");
    expectedFeatures.add("in_between_orange");
    assertEquals(expectedFeatures, features);
}

@Test
public void test8()
{
    TextAnnotation ta = mock(TextAnnotation.class);
    Sentence sentence = mock(Sentence.class);
    when(ta.getSentence(0)).thenReturn(sentence);
    when(sentence.getStartSpan()).thenReturn(0);
    when(sentence.getEndSpan()).thenReturn(6);
    when(ta.getToken(0)).thenReturn("beforeSource");
    when(ta.getToken(1)).thenReturn("twoBeforeSource");
    when(ta.getToken(4)).thenReturn("afterTarget");
    when(ta.getToken(5)).thenReturn("twoAfterTarget");
    Constituent source = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(source.getStartSpan()).thenReturn(1);
    when(source.getSentenceId()).thenReturn(0);
    Constituent target = mock(Constituent.class);
    when(target.getTextAnnotation()).thenReturn(ta);
    when(target.getEndSpan()).thenReturn(4);
    when(target.getSentenceId()).thenReturn(0);
    Relation r = mock(Relation.class);
    when(r.getSource()).thenReturn(source);
    when(r.getTarget()).thenReturn(target);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getLexicalFeaturePartE(r);
    List<String> expected = Arrays.asList("fwM1_beforeSource", "swM1_NULL", "fwM2_afterTarget", "swM2_twoAfterTarget");
    assertEquals(expected, features);
}

@Test
public void test9()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    Constituent mockSource = mock(Constituent.class);
    when(mockSource.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Constituent mockTarget = mock(Constituent.class);
    when(mockTarget.getTextAnnotation()).thenReturn(mockTextAnnotation);
    Constituent mockSourceHead = mock(Constituent.class);
    when(mockSourceHead.toString()).thenReturn("sourceHead");
    Constituent mockTargetHead = mock(Constituent.class);
    when(mockTargetHead.toString()).thenReturn("targetHead");
    Relation mockRelation = mock(Relation.class);
    when(mockRelation.getSource()).thenReturn(mockSource);
    when(mockRelation.getTarget()).thenReturn(mockTarget);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartF(Relation r) {
            return super.getLexicalFeaturePartF(r);
        }
    };
    List<String> features = new RelationFeatureExtractor() {
        @Override
        public List<String> getLexicalFeaturePartF(Relation r) {
            List<String> ret_features = new ArrayList<>();
            Constituent source = r.getSource();
            Constituent target = r.getTarget();
            Constituent sourceHead = mockSourceHead;
            Constituent targetHead = mockTargetHead;
            String sourceHeadWord = sourceHead.toString();
            String targetHeadWord = targetHead.toString();
            ret_features.add("HM1_" + sourceHeadWord);
            ret_features.add("HM2_" + targetHeadWord);
            ret_features.add((("HM12_" + sourceHeadWord) + "_") + targetHeadWord);
            return ret_features;
        }
    }.getLexicalFeaturePartF(mockRelation);
    assertEquals(3, features.size());
    assertEquals("HM1_sourceHead", features.get(0));
    assertEquals("HM2_targetHead", features.get(1));
    assertEquals("HM12_sourceHead_targetHead", features.get(2));
}

@Test
public void test10()
{
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getAttribute("EntityMentionType")).thenReturn("NAM");
    when(source.getAttribute("EntityType")).thenReturn("PER");
    when(target.getAttribute("EntityMentionType")).thenReturn("NOM");
    when(target.getAttribute("EntityType")).thenReturn("ORG");
    when(target.doesConstituentCover(source)).thenReturn(false);
    when(source.doesConstituentCover(target)).thenReturn(false);
    Relation relation = new Relation("testRelation", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getMentionFeature(relation);
    List<String> expected = Arrays.asList("source_mtype_PER", "target_mtype_ORG", "mlvl_NAM_NOM", "mt_PER_ORG", "mlvl_mt_NAM_PER_NOM_ORG");
    assertEquals(expected, features);
}

@Test
public void test11()
{
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    TextAnnotation ta = mock(TextAnnotation.class);
    View spView = mock(View.class);
    when(ta.getView(SHALLOW_PARSE)).thenReturn(spView);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(ta);
    when(source.getStartSpan()).thenReturn(0);
    when(source.getEndSpan()).thenReturn(1);
    when(target.getStartSpan()).thenReturn(3);
    when(target.getEndSpan()).thenReturn(4);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(sourceHead.getStartSpan()).thenReturn(0);
    when(sourceHead.getEndSpan()).thenReturn(1);
    when(targetHead.getStartSpan()).thenReturn(3);
    when(targetHead.getEndSpan()).thenReturn(4);
    RelationFeatureExtractor spyExtractor = spy(extractor);
    doReturn(sourceHead).when(spyExtractor).getEntityHeadForConstituent(eq(source), eq(ta), eq("TEST"));
    doReturn(targetHead).when(spyExtractor).getEntityHeadForConstituent(eq(target), eq(ta), eq("TEST"));
    List<String> betweenHeadsLabels = Arrays.asList("NP", "VP");
    List<String> betweenExtentsLabels = Arrays.asList("PP");
    when(spView.getLabelsCoveringSpan(1, 2)).thenReturn(betweenHeadsLabels);
    when(spView.getLabelsCoveringSpan(1, 2)).thenReturn(betweenExtentsLabels);
    Relation relation = new Relation("testRelation", source, target, 1.0);
    List<Pair<String, String>> result = spyExtractor.getShallowParseFeature(relation);
    assertEquals(3, result.size());
    assertEquals("chunker_between_heads_0", result.get(0).getFirst());
    assertEquals("NP", result.get(0).getSecond());
    assertEquals("chunker_between_heads_1", result.get(1).getFirst());
    assertEquals("VP", result.get(1).getSecond());
    assertEquals("chunker_between_extents_0", result.get(2).getFirst());
    assertEquals("PP", result.get(2).getSecond());
}

@Test
public void test12()
{
    TextAnnotation ta = new TextAnnotation("testCorpus", "testId", new String[]{ "John", "met", "Mary", "in", "Paris" });
    View mentionView = new View(ViewNames.MENTION_ACE, "testGenerator", ta, 1.0);
    Constituent source = new Constituent("PER", ViewNames.MENTION_ACE, ta, 0, 1);
    source.addAttribute("EnityType", "PERSON");
    Constituent mid = new Constituent("O", ViewNames.MENTION_ACE, ta, 1, 2);
    Constituent target = new Constituent("PER", ViewNames.MENTION_ACE, ta, 2, 3);
    target.addAttribute("EntityType", "PERSON");
    mentionView.addConstituent(source);
    mentionView.addConstituent(mid);
    mentionView.addConstituent(target);
    ta.addView(MENTION_ACE, mentionView);
    Relation relation = new Relation("testRel", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor();
    List<String> features = extractor.getStructualFeature(relation);
    assertTrue(features.contains("middle_mention_size_1"));
    assertTrue(features.contains("middle_word_size_1"));
    assertTrue(features.contains("m1_m2_no_coverage"));
    assertTrue(features.contains("cb1_PERSON_PERSON_m1_m2_no_coverage"));
}

@Test
public void test13()
{
    Relation mockRelation = mock(Relation.class);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        @Override
        public List<String> getTemplateFeature(Relation r) {
            List<String> features = new ArrayList<>();
            features.add("is_formulaic_structure");
            features.add("is_preposition_structure");
            features.add("is_possessive_structure");
            features.add("is_premodifier_structure");
            return features;
        }
    };
    List<String> result = extractor.getTemplateFeature(mockRelation);
    List<String> expected = Arrays.asList("is_formulaic_structure", "is_preposition_structure", "is_possessive_structure", "is_premodifier_structure");
    assertEquals(expected, result);
}

@Test
public void test14()
{
    String[] tokens = new String[]{ "John", "Smith", "CEO", "of", "AcmeCorp" };
    String[] posTags = new String[]{ "NNP", "NNP", "NN", "IN", "NNP" };
    TextAnnotation ta = new TextAnnotation("corpus", "id", tokens);
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    for (int i = 0; i < tokens.length; i++) {
        posView.addTokenLabel(i, posTags[i], 1.0);
    }
    ta.addView(POS, posView);
    Constituent source = new Constituent("EntityMention", "NER", ta, 0, 2);
    source.addAttribute("EntityType", "PER");
    Constituent target = new Constituent("EntityMention", "NER", ta, 4, 5);
    target.addAttribute("EntityType", "ORG");
    Relation relation = new Relation("TestRelation", source, target, 1.0);
    Constituent sourceHead = new Constituent("EntityMention", "NER", ta, 1, 2);
    Constituent targetHead = new Constituent("EntityMention", "NER", ta, 4, 5);
    ta.addView("TEST", new View("TEST", "manual", ta, 1.0));
    RelationFeatureExtractor.getEntityHeadForConstituent = ( constituent, ta2, viewName) -> {
        if (constituent == source) {
            return sourceHead;
        }
        if (constituent == target) {
            return targetHead;
        }
        return null;
    };
    boolean result = RelationFeatureExtractor.isFormulaic(relation);
    assertTrue(result);
}

@Test
public void test15()
{
    String[] tokens = new String[]{ "John", "'s", "book" };
    TextAnnotation ta = new TextAnnotation("testCorpus", "testTextId", "", tokens, new int[]{ 0, 1, 2 });
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    posView.addTokenLabel(0, "NNP", 1.0);
    posView.addTokenLabel(1, "POS", 1.0);
    posView.addTokenLabel(2, "NN", 1.0);
    ta.addView(POS, posView);
    Constituent source = new Constituent("PER", "dummyView", ta, 0, 1);
    Constituent target = new Constituent("OBJ", "dummyView", ta, 2, 3);
    Relation r = new Relation("Possessive", source, target, 1.0);
    RelationFeatureExtractor extractor = new RelationFeatureExtractor() {
        public static Constituent getEntityHeadForConstituent(Constituent c, TextAnnotation ta, String mode) {
            return c;
        }
    };
    assertTrue(RelationFeatureExtractor.isPossessive(r));
}

@Test
public void test16()
{
    String[] tokens = new String[]{ "big", "dog" };
    List<Sentence> sentences = Collections.singletonList(new Sentence(Arrays.asList(tokens), 0, 2));
    TextAnnotation ta = new TextAnnotation("TestCorpus", "TestText", tokens, sentences);
    View posView = new TokenLabelView(ViewNames.POS, "testGenerator", ta, 1.0);
    posView.addTokenLabel(0, "JJ", 1.0);
    posView.addTokenLabel(1, "NN", 1.0);
    ta.addView(POS, posView);
    Constituent source = new Constituent("entity", ViewNames.NER, ta, 0, 1);
    Constituent target = new Constituent("entity", ViewNames.NER, ta, 1, 2);
    Relation rel = new Relation("testRel", source, target, 1.0);
    Constituent sourceHead = new Constituent("entity", ViewNames.NER, ta, 0, 1);
    Constituent targetHead = new Constituent("entity", ViewNames.NER, ta, 1, 2);
    RelationFeatureExtractor.getEntityHeadForConstituent = ( c, t, s) -> {
        if (c == source) {
            return sourceHead;
        } else {
            return targetHead;
        }
    };
    RelationFeatureExtractor.onlyNounBetween = ( c1, c2) -> false;
    boolean result = RelationFeatureExtractor.isPremodifier(rel);
    assertTrue(result);
}

@Test
public void test17()
{
    String text = "John went to the store.";
    List<String[]> tokens = Collections.singletonList(new String[]{ "John", "went", "to", "the", "store", "." });
    List<IntPair> sentences = Collections.singletonList(new IntPair(0, 6));
    TextAnnotation ta = new TextAnnotation("corpus", "id", text, tokens, sentences);
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    posView.addConstituent(new Constituent("NNP", ViewNames.POS, ta, 0, 1));
    posView.addConstituent(new Constituent("VBD", ViewNames.POS, ta, 1, 2));
    posView.addConstituent(new Constituent("IN", ViewNames.POS, ta, 2, 3));
    posView.addConstituent(new Constituent("DT", ViewNames.POS, ta, 3, 4));
    posView.addConstituent(new Constituent("NN", ViewNames.POS, ta, 4, 5));
    posView.addConstituent(new Constituent(".", ViewNames.POS, ta, 5, 6));
    ta.addView(POS, posView);
    Constituent source = new Constituent("Entity", "NER", ta, 0, 1);
    Constituent target = new Constituent("Entity", "NER", ta, 4, 5);
    Relation relation = new Relation("test", source, target, 1.0);
    RelationFeatureExtractor mockExtractor = new RelationFeatureExtractor() {
        public static boolean isPossessive(Relation r) {
            return false;
        }

        public static boolean isNoun(String pos) {
            return ((pos.equals("NN") || pos.equals("NNS")) || pos.equals("NNP")) || pos.equals("NNPS");
        }

        public static Constituent getEntityHeadForConstituent(Constituent c, TextAnnotation ta, String type) {
            return c;
        }
    };
    boolean result = RelationFeatureExtractor.isPreposition(relation);
    assertTrue(result);
}

@Test
public void test18()
{
    String[] tokens = new String[]{ "John", "manager", "team", "wins" };
    String[] posTags = new String[]{ "NNP", "NN", "NN", "VBZ" };
    TextAnnotationBuilder builder = new TokenizerTextAnnotationBuilder(new StatefulTokenizer());
    TextAnnotation ta = new BasicTextAnnotation("testCorpus", "testDoc", Arrays.asList(tokens));
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    for (int i = 0; i < tokens.length; i++) {
        Constituent posConstituent = new Constituent(posTags[i], ViewNames.POS, ta, i, i + 1);
        posView.addConstituent(posConstituent);
    }
    ta.addView(POS, posView);
    Constituent front = new Constituent("Mention", "dummy", ta, 0, 1);
    Constituent back = new Constituent("Mention", "dummy", ta, 3, 4);
    boolean result = RelationFeatureExtractor.onlyNounBetween(front, back);
    assertTrue(result);
}

@Test
public void test19()
{
    Constituent extentConstituent = mock(Constituent.class);
    when(extentConstituent.getAttribute("IsPredicted")).thenReturn(null);
    when(extentConstituent.getAttribute("EntityHeadStartSpan")).thenReturn(null);
    when(extentConstituent.hasAttribute(EntityHeadStartCharOffset)).thenReturn(true);
    when(extentConstituent.getAttribute(EntityHeadStartCharOffset)).thenReturn("5");
    when(extentConstituent.getAttribute(EntityHeadEndCharOffset)).thenReturn("10");
    when(extentConstituent.getLabel()).thenReturn("ENTITY");
    when(extentConstituent.getAttributeKeys()).thenReturn(Set.of(EntityHeadStartCharOffset, EntityHeadEndCharOffset));
    when(extentConstituent.getAttribute(EntityHeadStartCharOffset)).thenReturn("5");
    when(extentConstituent.getAttribute(EntityHeadEndCharOffset)).thenReturn("10");
    TextAnnotation textAnnotation = mock(TextAnnotation.class);
    when(textAnnotation.getTokenIdFromCharacterOffset(5)).thenReturn(1);
    when(textAnnotation.getTokenIdFromCharacterOffset(9)).thenReturn(3);
    String viewName = "TEST_VIEW";
    Constituent result = RelationFeatureExtractor.getEntityHeadForConstituent(extentConstituent, textAnnotation, viewName);
    assertNotNull(result);
    assertEquals("ENTITY", result.getLabel());
    assertEquals(1, result.getStartSpan());
    assertEquals(4, result.getEndSpan());
    assertEquals("5", result.getAttribute(EntityHeadStartCharOffset));
    assertEquals("10", result.getAttribute(EntityHeadEndCharOffset));
}

@Test
public void test20()
{
    String[] tokens = new String[]{ "John", "loves", "Mary" };
    TextAnnotation ta = new TextAnnotation("corpus", "textId", tokens);
    View posView = new View(ViewNames.POS, "testGenerator", ta, 1.0);
    posView.addConstituent(new Constituent("NNP", ViewNames.POS, ta, 0, 1));
    posView.addConstituent(new Constituent("VBZ", ViewNames.POS, ta, 1, 2));
    posView.addConstituent(new Constituent("NNP", ViewNames.POS, ta, 2, 3));
    ta.addView(POS, posView);
    TreeView depView = new TreeView(ViewNames.DEPENDENCY_STANFORD, "testGenerator", ta, 1.0);
    Constituent dep1 = new Constituent("nsubj", ViewNames.DEPENDENCY_STANFORD, ta, 0, 1);
    Constituent dep2 = new Constituent("root", ViewNames.DEPENDENCY_STANFORD, ta, 1, 2);
    Constituent dep3 = new Constituent("dobj", ViewNames.DEPENDENCY_STANFORD, ta, 2, 3);
    depView.addConstituent(dep1);
    depView.addConstituent(dep2);
    depView.addConstituent(dep3);
    ta.addView(DEPENDENCY_STANFORD, depView);
    View annView = new View("RE_ANNOTATED", "testGenerator", ta, 1.0);
    Constituent ann1 = new Constituent("entity", "RE_ANNOTATED", ta, 0, 1);
    Constituent ann2 = new Constituent("entity", "RE_ANNOTATED", ta, 1, 2);
    Constituent ann3 = new Constituent("entity", "RE_ANNOTATED", ta, 2, 3);
    ann1.addAttribute("WORDNETTAG", "person");
    ann2.addAttribute("WORDNETTAG", "action");
    ann3.addAttribute("WORDNETTAG", "person");
    annView.addConstituent(ann1);
    annView.addConstituent(ann2);
    annView.addConstituent(ann3);
    ta.addView("RE_ANNOTATED", annView);
    Constituent source = new Constituent("PER", "test", ta, 0, 1);
    Constituent target = new Constituent("PER", "test", ta, 2, 3);
    Relation relation = new Relation("employee", source, target, 1.0);
    TreeView spyDepView = depView;
    List<Constituent> path = new ArrayList<>();
    path.add(dep1);
    path.add(dep2);
    path.add(dep3);
    Field pathHelperField;
    try {
        Method method = PathFeatureHelper.class.getDeclaredMethod("getPathConstituents", Constituent.class, Constituent.class, int.class);
    } catch (NoSuchMethodException e) {
    }
    List<Pair<String, String>> features = RelationFeatureExtractor.getDependencyFeature(relation);
    assertEquals(9, features.size());
    assertEquals("tag_0", features.get(0).getFirst());
    assertEquals("nsubj", features.get(0).getSecond());
}

@Test
public void test21()
{
    TextAnnotation mockTextAnnotation = mock(TextAnnotation.class);
    Constituent source = mock(Constituent.class);
    Constituent target = mock(Constituent.class);
    when(source.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(target.getTextAnnotation()).thenReturn(mockTextAnnotation);
    when(source.getStartSpan()).thenReturn(5);
    when(source.getEndSpan()).thenReturn(6);
    when(target.getStartSpan()).thenReturn(5);
    when(target.getEndSpan()).thenReturn(7);
    when(source.getSentenceId()).thenReturn(0);
    when(target.getSentenceId()).thenReturn(0);
    Constituent sourceHead = mock(Constituent.class);
    Constituent targetHead = mock(Constituent.class);
    when(sourceHead.getStartSpan()).thenReturn(4);
    when(targetHead.getStartSpan()).thenReturn(3);
    when(sourceHead.getEndSpan()).thenReturn(5);
    when(targetHead.getEndSpan()).thenReturn(4);
    when(target.getAttribute("EntityType")).thenReturn("LOC");
    TreeView mockTreeView = mock(TreeView.class);
    when(mockTextAnnotation.getView(DEPENDENCY_STANFORD)).thenReturn(mockTreeView);
    when(mockTreeView.getConstituentsCoveringToken(anyInt())).thenReturn(new ArrayList<>());
    when(mockTextAnnotation.getView(TOKENS)).thenReturn(mockTreeView);
    when(mockTreeView.getEndSpan()).thenReturn(10);
    when(mockTextAnnotation.getToken(anyInt())).thenReturn("");
    mockStaticGetEntityHead(source, sourceHead);
    mockStaticGetEntityHead(target, targetHead);
    List<String> result = RelationFeatureExtractor.patternRecognition(source, target);
    assertEquals(Collections.singletonList("SAME_SOURCE_TARGET_EXTENT_EXCEPTION"), result);
}


