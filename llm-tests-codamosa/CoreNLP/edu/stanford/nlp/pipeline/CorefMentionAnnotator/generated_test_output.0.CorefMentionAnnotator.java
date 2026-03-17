import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefMentionAnnotator annotator = new CorefMentionAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(CorefMentionsAnnotation.class, ParagraphAnnotation.class, SpeakerAnnotation.class, UtteranceAnnotation.class));
    assertEquals(expected, result);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("President Joe Smith's");
    CoreLabel token0 = new CoreLabel();
    token0.setWord("President");
    token0.set(FineGrainedNamedEntityTagAnnotation.class, "TITLE");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Joe");
    token1.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Smith");
    token2.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("'s");
    List<CoreLabel> sentenceTokens = Arrays.asList(token0, token1, token2, token3);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, sentenceTokens);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    Mention cm = new Mention(0, 0, 4, 0, "President Joe Smith's", null);
    CoreLabel emToken1 = new CoreLabel();
    emToken1.setWord("Joe");
    CoreLabel emToken2 = new CoreLabel();
    emToken2.setWord("Smith");
    CoreMap em = new ArrayCoreMap();
    em.set(TokensAnnotation.class, Arrays.asList(emToken1, emToken2));
    em.set(EntityTypeAnnotation.class, "PERSON");
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(annotation, cm, em);
    assertTrue(result);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("This is a test.");
    annotation.set(DocIDAnnotation.class, "nw_doc_001");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("John");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("went");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("home");
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3);
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap sentence = new TypesafeMap.CoreMapImpl();
    sentence.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    Mention mention = new Mention(0, 0, 1, 0, "John", null, 0);
    List<List<Mention>> mentionList = Collections.singletonList(Collections.singletonList(mention));
    CoreMap mockEntityMention = new TypesafeMap.CoreMapImpl();
    annotation.set(MentionsAnnotation.class, Arrays.asList(mockEntityMention));
    token1.set(EntityMentionIndexAnnotation.class, 0);
    CorefMentionAnnotator annotator = new CorefMentionAnnotator() {
        @Override
        protected List<List<Mention>> findMentions(Annotation ann, Object dictionaries, Properties props) {
            return mentionList;
        }

        @Override
        protected boolean synchCorefMentionEntityMention(Annotation ann, Mention m, CoreMap cm) {
            return true;
        }
    };
    Properties props = new Properties();
    props.setProperty("coref.input.type", "conll");
    props.setProperty("coref.language", "zh");
    props.setProperty("coref.specialCaseNewswire", "true");
    annotator.corefProperties = props;
    annotator.md = annotator;
    annotator.dictionaries = null;
    annotator.annotate(annotation);
    Map<Integer, Integer> expectedCorefToEntity = new HashMap<>();
    expectedCorefToEntity.put(0, 0);
    Map<Integer, Integer> expectedEntityToCoref = new HashMap<>();
    expectedEntityToCoref.put(0, 0);
    assertEquals(expectedCorefToEntity, annotation.get(CorefMentionToEntityMentionMappingAnnotation.class));
    assertEquals(expectedEntityToCoref, annotation.get(EntityMentionToCorefMentionMappingAnnotation.class));
}

@Test
public void test1()
{
    CorefMentionAnnotator annotator = new CorefMentionAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertEquals(4, result.size());
    assertTrue(result.contains(CorefMentionsAnnotation.class));
    assertTrue(result.contains(ParagraphAnnotation.class));
    assertTrue(result.contains(SpeakerAnnotation.class));
    assertTrue(result.contains(UtteranceAnnotation.class));
}

@Test
public void test2()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("President");
    token1.set(FineGrainedNamedEntityTagAnnotation.class, "TITLE");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Joe");
    token2.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("Smith");
    token3.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("'s");
    List<CoreLabel> sentenceTokens = Arrays.asList(token1, token2, token3, token4);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, sentenceTokens);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    Annotation ann = new Annotation("President Joe Smith's");
    ann.set(SentencesAnnotation.class, sentences);
    Mention cm = new Mention(0, 0, 4, ann, null);
    CoreLabel emToken1 = token2;
    CoreLabel emToken2 = token3;
    List<CoreLabel> emTokens = Arrays.asList(emToken1, emToken2);
    CoreMap em = new ArrayCoreMap();
    em.set(TokensAnnotation.class, emTokens);
    em.set(EntityTypeAnnotation.class, "PERSON");
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(ann, cm, em);
    assertTrue(result);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("");
    List<CoreMap> sentences = new ArrayList<>();
    CoreMap sentence = new TypesafeMap<>();
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    CoreLabel token2 = new CoreLabel();
    tokens.add(token1);
    tokens.add(token2);
    sentence.set(TokensAnnotation.class, tokens);
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    annotation.set(DocIDAnnotation.class, "nw123");
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap mentionMap = new TypesafeMap<>();
    annotation.set(MentionsAnnotation.class, Collections.singletonList(mentionMap));
    token1.set(EntityMentionIndexAnnotation.class, 0);
    token1.set(CorefMentionIndexesAnnotation.class, new ArraySet<>());
    CorefMentionAnnotator annotator = new CorefMentionAnnotator();
    annotator.corefProperties = PropertiesUtils.asProperties("coref.input.type", "conll", "coref.language", "zh", "coref.specialCaseNewswire", "true");
    annotator.dictionaries = null;
    annotator.md = new MentionDetector() {
        @Override
        public List<List<Mention>> findMentions(Annotation doc, Object dictionaries, Properties props) {
            Mention m = new Mention(0, 0, 1, 0, 0);
            m.setSentence(sentence);
            List<Mention> mentionList = new ArrayList<>();
            mentionList.add(m);
            return Collections.singletonList(mentionList);
        }
    };
    annotation.set(CorefMentionsAnnotation.class, new ArrayList<>());
    annotation.set(MentionsAnnotation.class, Collections.singletonList(mentionMap));
    annotator.annotate(annotation);
    List<Mention> corefMentions = annotation.get(CorefMentionsAnnotation.class);
    assertEquals(1, corefMentions.size());
    Mention resultMention = corefMentions.get(0);
    assertEquals(0, resultMention.mentionID);
    assertEquals(0, resultMention.sentNum);
    Map<Integer, Integer> corefToEntity = annotation.get(CorefMentionToEntityMentionMappingAnnotation.class);
    Map<Integer, Integer> entityToCoref = annotation.get(EntityMentionToCorefMentionMappingAnnotation.class);
    assertNotNull(corefToEntity);
    assertNotNull(entityToCoref);
    assertTrue(corefToEntity.containsKey(0));
    assertEquals(Integer.valueOf(0), corefToEntity.get(0));
    assertTrue(entityToCoref.containsKey(0));
    assertEquals(Integer.valueOf(0), entityToCoref.get(0));
}

