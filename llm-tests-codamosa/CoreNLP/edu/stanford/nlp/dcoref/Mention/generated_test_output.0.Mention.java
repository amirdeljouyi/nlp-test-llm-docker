import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Mention mention = new Mention(0, 0, null, null, null, null);
    mention.mentionType = MentionType.NOMINAL;
    mention.setSpanToString("The dog");
    boolean result = mention.isTheCommonNoun();
    assertTrue(result);
}

@Test
public void test2()
{
    Mention mention = new Mention();
    mention.mentionType = MentionType.PRONOMINAL;
    mention.headString = "he";
    mention.headWord = new CoreLabel();
    mention.nerString = "O";
    Dictionaries dictionaries = new Dictionaries();
    dictionaries.singularPronouns = new HashSet<>(Collections.singleton("he"));
    dictionaries.pluralPronouns = new HashSet<>(Collections.singleton("they"));
    dictionaries.singularWords = new HashSet<>();
    dictionaries.pluralWords = new HashSet<>();
    mention.setNumber(dictionaries);
    assertEquals(Number.SINGULAR, mention.number);
}

@Test
public void test3()
{
    Mention mention1 = new Mention(0);
    mention1.animacy = Animacy.ANIMATE;
    Mention mention2 = new Mention(1);
    mention2.animacy = Animacy.ANIMATE;
    boolean result = mention1.animaciesAgree(mention2);
    assertTrue(result);
}

@Test
public void test4()
{
    Mention mention1 = new Mention(0, 0, 0, 0, "entity1", null, null, null);
    Mention mention2 = new Mention(0, 0, 0, 0, "entity2", null, null, null);
    mention1.animacy = Animacy.ANIMATE;
    mention2.animacy = Animacy.ANIMATE;
    boolean result = mention1.animaciesAgree(mention2);
    assertTrue("Expected animacies to agree when both mentions have ANIMATE animacy", result);
}

@Test
public void test5()
{
    Mention mention = mock(Mention.class);
    Mention antecedent = mock(Mention.class);
    Dictionaries dict = mock(Dictionaries.class);
    when(mention.animaciesAgree(antecedent)).thenReturn(true);
    when(mention.entityTypesAgree(antecedent, dict)).thenReturn(true);
    when(mention.gendersAgree(antecedent)).thenReturn(true);
    when(mention.numbersAgree(antecedent)).thenReturn(true);
    when(mention.attributesAgree(antecedent, dict)).thenCallRealMethod();
    boolean result = mention.attributesAgree(antecedent, dict);
    assertTrue(result);
}

@Test
public void test6()
{
    Mention mention1 = new Mention();
    mention1.nerString = "PERSON";
    Mention mention2 = new Mention();
    mention2.nerString = "PERSON";
    Dictionaries dictionaries = new Dictionaries();
    boolean result = mention1.entityTypesAgree(mention2, dictionaries);
    assertTrue("Expected entity types to agree when both are PERSON", result);
}

@Test
public void test7()
{
    Dictionaries mockDict = new Dictionaries();
    Mention mention1 = new Mention(1, 0, 0, 0, "Barack Obama");
    mention1.entityType = "PERSON";
    Mention mention2 = new Mention(1, 0, 1, 1, "Obama");
    mention2.entityType = "PERSON";
    boolean result = mention1.entityTypesAgree(mention2, mockDict);
    assertTrue("Entity types should agree when both mentions have the same entityType", result);
}

@Test
public void test8()
{
    Mention m1 = new Mention(1, 2, 3, "John");
    m1.mentionType = Type.PROPER;
    m1.number = Number.SINGULAR;
    m1.gender = Gender.MALE;
    m1.animacy = Animacy.ANIMATE;
    m1.person = Person.THIRD;
    m1.headString = "John";
    m1.nerString = "PERSON";
    m1.startIndex = 1;
    m1.endIndex = 2;
    m1.headIndex = 1;
    m1.mentionID = 100;
    m1.originalRef = null;
    m1.headIndexedWord = new CoreLabel();
    m1.dependingVerb = new CoreLabel();
    m1.headWord = new CoreLabel();
    m1.goldCorefClusterID = 200;
    m1.corefClusterID = 300;
    m1.mentionNum = 1;
    m1.sentNum = 1;
    m1.utter = 0;
    m1.paragraph = 0;
    m1.isSubject = true;
    m1.isDirectObject = false;
    m1.isIndirectObject = false;
    m1.isPrepositionObject = false;
    m1.hasTwin = false;
    m1.generic = false;
    m1.isSingleton = true;
    m1.originalSpan = Arrays.asList(new CoreLabel(), new CoreLabel());
    m1.sentenceWords = Arrays.asList(new CoreLabel(), new CoreLabel());
    m1.basicDependency = new SemanticGraph();
    m1.enhancedDependency = new SemanticGraph();
    m1.contextParseTree = Tree.valueOf("(NP (NN John))");
    m1.dependents = Collections.emptyList();
    m1.preprocessedTerms = Arrays.asList("john");
    Mention m2 = new Mention(1, 2, 3, "John");
    m2.mentionType = Type.PROPER;
    m2.number = Number.SINGULAR;
    m2.gender = Gender.MALE;
    m2.animacy = Animacy.ANIMATE;
    m2.person = Person.THIRD;
    m2.headString = "John";
    m2.nerString = "PERSON";
    m2.startIndex = 1;
    m2.endIndex = 2;
    m2.headIndex = 1;
    m2.mentionID = 100;
    m2.originalRef = null;
    m2.headIndexedWord = new CoreLabel();
    m2.dependingVerb = new CoreLabel();
    m2.headWord = new CoreLabel();
    m2.goldCorefClusterID = 200;
    m2.corefClusterID = 300;
    m2.mentionNum = 1;
    m2.sentNum = 1;
    m2.utter = 0;
    m2.paragraph = 0;
    m2.isSubject = true;
    m2.isDirectObject = false;
    m2.isIndirectObject = false;
    m2.isPrepositionObject = false;
    m2.hasTwin = false;
    m2.generic = false;
    m2.isSingleton = true;
    m2.originalSpan = Arrays.asList(new CoreLabel(), new CoreLabel());
    m2.sentenceWords = Arrays.asList(new CoreLabel(), new CoreLabel());
    m2.basicDependency = new SemanticGraph();
    m2.enhancedDependency = new SemanticGraph();
    m2.contextParseTree = Tree.valueOf("(NP (NN John))");
    m2.dependents = Collections.emptyList();
    m2.preprocessedTerms = Arrays.asList("john");
    assertTrue(m1.equals(m2));
}

@Test
public void test9()
{
    Mention mention1 = new Mention(1);
    Mention mention2 = new Mention(2);
    mention1.gender = Gender.MALE;
    mention2.gender = Gender.MALE;
    mention1.headWord = new Word("He");
    mention2.headWord = new Word("John");
    mention1.startIndex = 0;
    mention1.endIndex = 1;
    mention2.startIndex = 1;
    mention2.endIndex = 2;
    mention1.extentString = "He";
    mention2.extentString = "John";
    assertTrue(mention1.gendersAgree(mention2));
}

@Test
public void test10()
{
    Mention mention1 = new Mention(0, 0, "George");
    Mention mention2 = new Mention(0, 0, "Bush");
    mention1.nerString = "PERSON";
    mention2.nerString = "PERSON";
    mention1.headString = "George";
    mention2.headString = "Bush";
    mention1.headWord = new CoreLabel();
    mention1.headWord.setIndex(2);
    mention1.originalSpan = Arrays.asList(2, 3);
    mention2.headWord = new CoreLabel();
    mention2.headWord.setIndex(3);
    mention2.originalSpan = Arrays.asList(2, 3);
    boolean result = mention1.headsAgree(mention2);
    assertTrue(result);
}

@Test
public void test11()
{
    Mention mention = new Mention();
    IndexedWord headWord = mock(IndexedWord.class);
    mention.headIndexedWord = headWord;
    GrammaticalRelation relation = mock(GrammaticalRelation.class);
    when(relation.getShortName()).thenReturn("cc");
    IndexedWord childWord = mock(IndexedWord.class);
    Pair<GrammaticalRelation, IndexedWord> childPair = new Pair<>(relation, childWord);
    List<Pair<GrammaticalRelation, IndexedWord>> childPairs = Arrays.asList(childPair);
    GrammaticalStructure mockStructure = mock(GrammaticalStructure.class);
    when(mockStructure.childPairs(headWord)).thenReturn(childPairs);
    mention.enhancedDependency = mockStructure;
    assertTrue(mention.isCoordinated());
}

@Test
public void test12()
{
    Mention mention1 = new Mention(1);
    Mention mention2 = new Mention(2);
    Mention mentionThis = new Mention(0) {
        @Override
        public String spanToString() {
            return "California";
        }
    };
    Mention mentionArg = new Mention(1) {
        @Override
        public String spanToString() {
            return "California";
        }
    };
    Dictionaries dict = new Dictionaries() {
        @Override
        public String lookupCanonicalAmericanStateName(String str) {
            if ("California".equals(str)) {
                return "California";
            }
            return null;
        }

        @Override
        public Set<String> getDemonyms(String str) {
            return Collections.emptySet();
        }
    };
    boolean result = mentionThis.isDemonym(mentionArg, dict);
    assertTrue(result);
}

@Test
public void test13()
{
    Mention listMention = new Mention(1, 0, 2, 3, "NP", null, null, null);
    listMention.mentionType = MentionType.LIST;
    Mention memberMention = new Mention(1, 0, 2, 2, "NN", null, null, null);
    memberMention.mentionType = MentionType.NOMINAL;
    listMention.originalSpan = new int[]{ 2, 3 };
    memberMention.originalSpan = new int[]{ 2, 2 };
    listMention.startIndex = 2;
    listMention.endIndex = 3;
    memberMention.startIndex = 2;
    memberMention.endIndex = 2;
    assertTrue(memberMention.isListMemberOf(listMention));
}

@Test
public void test14()
{
    Mention mention1 = new Mention();
    Mention mention2 = new Mention();
    Mention sharedMention = new Mention();
    Set<Mention> list1 = new HashSet<>();
    list1.add(sharedMention);
    list1.add(mention1);
    Set<Mention> list2 = new HashSet<>();
    list2.add(sharedMention);
    list2.add(mention2);
    mention1.belongToLists = list1;
    mention2.belongToLists = list2;
    assertTrue(mention1.isMemberOfSameList(mention2));
}

@Test
public void test15()
{
    Mention mention = new Mention(1, "who");
    Set<Mention> relativePronouns = new HashSet<>();
    relativePronouns.add(mention);
    Object testObject = new Object() {
        Set<Mention> relativePronounsField = relativePronouns;

        public boolean isRelativePronoun(Mention m) {
            return (relativePronounsField != null) && relativePronounsField.contains(m);
        }
    };
    boolean result = ((Boolean) (testObject.getClass().getDeclaredMethod("isRelativePronoun", Mention.class).invoke(testObject, mention)));
    assertTrue(result);
}

@Test
public void test16()
{
    Mention m1 = new Mention();
    m1.nerString = "PER";
    m1.animacy = Animacy.ANIMATE;
    m1.gender = Gender.MALE;
    m1.number = Number.SINGULAR;
    m1.spanToString = () -> "President John";
    m1.lowercaseNormalizedSpanString = () -> "president john";
    m1.isPronominal = () -> false;
    m1.sameSentence = ( other) -> true;
    m1.animaciesAgree = ( other) -> true;
    m1.numbersAgree = ( other) -> true;
    Mention m2 = new Mention();
    m2.nerString = "PER";
    m2.animacy = Animacy.ANIMATE;
    m2.gender = Gender.MALE;
    m2.spanToString = () -> "President";
    m2.lowercaseNormalizedSpanString = () -> "president";
    m2.isPronominal = () -> false;
    m2.sameSentence = ( other) -> true;
    m2.animaciesAgree = ( other) -> true;
    m2.numbersAgree = ( other) -> true;
    Dictionaries dict = new Dictionaries();
    dict.allPronouns = new HashSet<>();
    dict.demonymSet = new HashSet<>();
    boolean result = m2.isRoleAppositive(m1, dict);
    assertTrue(result);
}

@Test
public void test17()
{
    Mention mention = new Mention(1, 0, 0, new IntTuple(2));
    mention.mentionType = MentionType.PROPER;
    mention.nerString = "PERSON";
    mention.headIndex = 5;
    mention.startIndex = 2;
    mention.sentNum = 1;
    mention.originalSpan = Arrays.asList(1, 2, 3);
    boolean result = mention.moreRepresentativeThan(null);
    assertTrue(result);
}

@Test
public void test18()
{
    Mention mention = new Mention();
    IndexedWord head = new IndexedWord();
    head.setLemma("say");
    mention.headIndexedWord = head;
    IndexedWord modalChild = new IndexedWord();
    modalChild.setLemma("can");
    SemanticGraph mockGraph = new SemanticGraph() {
        @Override
        public Collection<IndexedWord> getChildren(IndexedWord vertex) {
            return Arrays.asList(modalChild);
        }
    };
    mention.enhancedDependency = mockGraph;
    Dictionaries dict = new Dictionaries();
    dict.modals = new HashSet<>(Collections.singletonList("can"));
    assertEquals(1, mention.getModal(dict));
}

@Test
public void test19()
{
    Mention mention = new Mention(0);
    IndexedWord head = mock(IndexedWord.class);
    when(head.toString()).thenReturn("not");
    mention.headIndexedWord = head;
    SemanticGraph mockGraph = mock(SemanticGraph.class);
    mention.enhancedDependency = mockGraph;
    IndexedWord child = mock(IndexedWord.class);
    when(child.lemma()).thenReturn("not");
    Collection<IndexedWord> children = Arrays.asList(child);
    when(mockGraph.getChildren(head)).thenReturn(children);
    Dictionaries dict = new Dictionaries();
    dict.negations = new HashSet<>(Arrays.asList("not"));
    mention.getHeadSiblings = new ArrayList<>();
    when(mockGraph.hasParentWithReln(any(), any())).thenReturn(false);
    when(mockGraph.parentPairs(head)).thenReturn(Collections.emptyList());
    int result = mention.getNegation(dict);
    assertEquals(1, result);
}

@Test
public void test20()
{
    Mention mention = new Mention(0, null, null, null, null);
    IndexedWord sibling = mock(IndexedWord.class);
    when(sibling.lemma()).thenReturn("say");
    Dictionaries dict = mock(Dictionaries.class);
    dict.reportVerb = new HashSet<>(Arrays.asList("say"));
    GrammaticalStructure dependency = mock(GrammaticalStructure.class);
    when(dependency.hasParentWithReln(eq(sibling), eq(ADV_CLAUSE_MODIFIER))).thenReturn(true);
    IndexedWord marker = mock(IndexedWord.class);
    when(marker.lemma()).thenReturn("as");
    when(dependency.getChildWithReln(eq(sibling), eq(MARKER))).thenReturn(marker);
    mention.headIndexedWord = mock(IndexedWord.class);
    mention.enhancedDependency = dependency;
    Mention spyMention = spy(mention);
    doReturn(Arrays.asList(sibling)).when(spyMention).getHeadSiblings();
    doReturn(Arrays.asList()).when(spyMention).getHeadPathToRoot();
    int result = spyMention.getReportEmbedding(dict);
    assertEquals(1, result);
}

@Test
public void test21()
{
    Mention mention = new Mention();
    ArrayList<IndexedWord> premod1 = new ArrayList<>();
    IndexedWord premod1Word = new IndexedWord();
    premod1Word.setWord("The");
    premod1.add(premod1Word);
    ArrayList<IndexedWord> premod2 = new ArrayList<>();
    IndexedWord premod2Word = new IndexedWord();
    premod2Word.setWord("red");
    premod2.add(premod2Word);
    ArrayList<ArrayList<IndexedWord>> premodList = new ArrayList<>();
    premodList.add(premod1);
    premodList.add(premod2);
    mention.setPremodifiers(premodList);
    IndexedWord head = new IndexedWord();
    head.setWord("car");
    mention.headWord = head;
    ArrayList<IndexedWord> postmod1 = new ArrayList<>();
    IndexedWord postmod1Word = new IndexedWord();
    postmod1Word.setWord("on");
    postmod1.add(postmod1Word);
    ArrayList<IndexedWord> postmod2 = new ArrayList<>();
    IndexedWord postmod2Word = new IndexedWord();
    postmod2Word.setWord("the");
    postmod2.add(postmod2Word);
    ArrayList<IndexedWord> postmod3 = new ArrayList<>();
    IndexedWord postmod3Word = new IndexedWord();
    postmod3Word.setWord("hill");
    postmod3.add(postmod3Word);
    ArrayList<ArrayList<IndexedWord>> postmodList = new ArrayList<>();
    postmodList.add(postmod1);
    postmodList.add(postmod2);
    postmodList.add(postmod3);
    mention.setPostmodifiers(postmodList);
    String expectedPattern = "The red car on the hill";
    Mention testMention = new Mention() {
        @Override
        public String getPattern(List<AbstractCoreLabel> patternList) {
            StringBuilder result = new StringBuilder();
            for (AbstractCoreLabel token : patternList) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(token.word());
            }
            return result.toString();
        }

        @Override
        public List<ArrayList<IndexedWord>> getPremodifiers() {
            return premodList;
        }

        @Override
        public List<ArrayList<IndexedWord>> getPostmodifiers() {
            return postmodList;
        }
    };
    testMention.headWord = head;
    assertEquals(expectedPattern, testMention.getPattern());
}


