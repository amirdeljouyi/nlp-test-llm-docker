import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation ann = new Annotation("Joe Smith");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Joe");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Smith");
    List<CoreLabel> sentenceTokens = Arrays.asList(token1, token2);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, sentenceTokens);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    ann.set(SentencesAnnotation.class, sentences);
    Mention cm = new Mention(0, 0, 2);
    cm.sentNum = 0;
    List<CoreLabel> emTokens = Arrays.asList(token1, token2);
    CoreMap em = new ArrayCoreMap();
    em.set(TokensAnnotation.class, emTokens);
    em.set(EntityTypeAnnotation.class, "PERSON");
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(ann, cm, em);
    assertTrue(result);
}

@Test
public void test2()
{
    Properties props = new Properties();
    props.setProperty("coref.input.type", "conll");
    props.setProperty("coref.language", "zh");
    props.setProperty("coref.specialCaseNewswire", "true");
    CorefMentionAnnotator annotator = new CorefMentionAnnotator(props);
    Annotation annotation = new Annotation("");
    CoreLabel token1 = new CoreLabel();
    CoreLabel token2 = new CoreLabel();
    annotation.set(TokensAnnotation.class, Arrays.asList(token1, token2));
    annotation.set(DocIDAnnotation.class, "nw_doc_1");
    CoreMap sentence = mock(CoreMap.class);
    List<CoreLabel> sentenceTokens = Arrays.asList(token1, token2);
    when(sentence.get(TokensAnnotation.class)).thenReturn(sentenceTokens);
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    Mention mention = new Mention(0, 0, 1, 1, "head", 0);
    List<List<Mention>> mentionsList = Collections.singletonList(Collections.singletonList(mention));
    annotator.md = mock(MentionDetector.class);
    annotator.dictionaries = null;
    when(annotator.md.findMentions(eq(annotation), any(), any())).thenReturn(mentionsList);
    List<CoreMap> entityMentions = new ArrayList<>();
    CoreMap entityMention = mock(CoreMap.class);
    entityMentions.add(entityMention);
    annotation.set(MentionsAnnotation.class, entityMentions);
    token1.set(EntityMentionIndexAnnotation.class, 0);
    annotator.annotate(annotation);
    List<Mention> resultMentions = annotation.get(CorefMentionsAnnotation.class);
    assertEquals(1, resultMentions.size());
    assertEquals(0, resultMentions.get(0).mentionID);
    Map<Integer, Integer> coref2entity = annotation.get(CorefMentionToEntityMentionMappingAnnotation.class);
    Map<Integer, Integer> entity2coref = annotation.get(EntityMentionToCorefMentionMappingAnnotation.class);
    assertNotNull(coref2entity);
    assertNotNull(entity2coref);
}

@Test
public void test1()
{
    CoreLabel cmToken1 = new CoreLabel();
    cmToken1.setWord("Joe");
    cmToken1.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel cmToken2 = new CoreLabel();
    cmToken2.setWord("Smith");
    cmToken2.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel cmToken3 = new CoreLabel();
    cmToken3.setWord("'s");
    List<CoreLabel> cmTokens = Arrays.asList(cmToken1, cmToken2, cmToken3);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, cmTokens);
    Annotation ann = new Annotation("");
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    ann.set(SentencesAnnotation.class, sentences);
    Mention cm = new Mention(0, 0, 2);
    CoreLabel emToken1 = cmToken1;
    CoreLabel emToken2 = cmToken2;
    List<CoreLabel> emTokens = Arrays.asList(emToken1, emToken2);
    CoreMap em = new ArrayCoreMap();
    em.set(TokensAnnotation.class, emTokens);
    em.set(EntityTypeAnnotation.class, "PERSON");
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(ann, cm, em);
    assertTrue(result);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("");
    List<CoreMap> sentences = new ArrayList<>();
    CoreMap sentence = new TypesafeMap();
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token = new CoreLabel();
    token.set(EntityMentionIndexAnnotation.class, 0);
    token.set(CorefMentionIndexesAnnotation.class, new ArraySet<>());
    tokens.add(token);
    sentence.set(TokensAnnotation.class, tokens);
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    annotation.set(DocIDAnnotation.class, "doc_nw_001");
    List<CoreLabel> allTokens = new ArrayList<>();
    allTokens.add(token);
    annotation.set(TokensAnnotation.class, allTokens);
    CoreMap entityMention = new TypesafeMap();
    List<CoreMap> mentions = new ArrayList<>();
    mentions.add(entityMention);
    annotation.set(MentionsAnnotation.class, mentions);
    Properties props = new Properties();
    props.setProperty("coref.input.type", "conll");
    props.setProperty("coref.language", "zh");
    props.setProperty("coref.specialCaseNewswire", "true");
    CorefMentionAnnotator annotator = new CorefMentionAnnotator(props);
    annotator.md = ( ann, dict, p) -> {
        Mention m = new Mention();
        m.startIndex = 0;
        m.endIndex = 1;
        m.originalSpan = Collections.singletonList(token);
        return Collections.singletonList(Collections.singletonList(m));
    };
    annotator.dictionaries = null;
    CorefMentionAnnotator.synchCorefMentionEntityMention = ( a, m, em) -> true;
    annotator.annotate(annotation);
    String expected = "false";
    String actual = annotator.corefProperties.getProperty("removeNestedMentions");
    assertEquals(expected, actual);
    List<Mention> resultMentions = annotation.get(CorefMentionsAnnotation.class);
    assertNotNull(resultMentions);
    assertEquals(1, resultMentions.size());
}

