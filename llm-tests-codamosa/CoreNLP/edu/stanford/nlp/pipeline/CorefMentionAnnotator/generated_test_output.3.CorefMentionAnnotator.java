import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorefMentionAnnotator annotator = new CorefMentionAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(4, result.size());
    assertTrue(result.contains(CorefMentionsAnnotation.class));
    assertTrue(result.contains(ParagraphAnnotation.class));
    assertTrue(result.contains(SpeakerAnnotation.class));
    assertTrue(result.contains(UtteranceAnnotation.class));
}

@Test
public void test2()
{
    Annotation ann = new Annotation("President Joe Smith's");
    CoreLabel titleToken = new CoreLabel();
    titleToken.setWord("President");
    titleToken.set(FineGrainedNamedEntityTagAnnotation.class, "TITLE");
    CoreLabel firstNameToken = new CoreLabel();
    firstNameToken.setWord("Joe");
    firstNameToken.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel lastNameToken = new CoreLabel();
    lastNameToken.setWord("Smith");
    lastNameToken.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel possessiveToken = new CoreLabel();
    possessiveToken.setWord("'s");
    List<CoreLabel> sentenceTokens = new ArrayList<>();
    sentenceTokens.add(titleToken);
    sentenceTokens.add(firstNameToken);
    sentenceTokens.add(lastNameToken);
    sentenceTokens.add(possessiveToken);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, sentenceTokens);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    ann.set(SentencesAnnotation.class, sentences);
    Mention cm = new Mention(0, 0, 4, 0, "President Joe Smith's");
    CoreLabel emFirstNameToken = new CoreLabel();
    emFirstNameToken.setWord("Joe");
    emFirstNameToken.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel emLastNameToken = new CoreLabel();
    emLastNameToken.setWord("Smith");
    emLastNameToken.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    List<CoreLabel> emTokens = new ArrayList<>();
    emTokens.add(emFirstNameToken);
    emTokens.add(emLastNameToken);
    CoreMap em = new ArrayCoreMap();
    em.set(EntityTypeAnnotation.class, "PERSON");
    em.set(TokensAnnotation.class, emTokens);
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(ann, cm, em);
    assertTrue(result);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("这是一个测试句子。");
    annotation.set(DocIDAnnotation.class, "document_nw_001");
    CoreLabel token = new CoreLabel();
    token.set(EntityMentionIndexAnnotation.class, 0);
    token.set(CorefMentionIndexesAnnotation.class, new ArraySet<Integer>());
    annotation.set(TokensAnnotation.class, Collections.singletonList(token));
    CoreMap sentence = new DummyCoreMap();
    sentence.set(TokensAnnotation.class, Collections.singletonList(token));
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    CoreMap entityMention = new DummyCoreMap();
    annotation.set(MentionsAnnotation.class, Collections.singletonList(entityMention));
    Properties props = new Properties();
    props.setProperty("coref.input.type", "conll");
    props.setProperty("coref.language", "zh");
    props.setProperty("coref.specialCaseNewswire", "true");
    CorefMentionAnnotator annotator = new CorefMentionAnnotator(props);
    Mention mention = new Mention(0, 1, sentence, 0);
    mention.originalRef = "测试";
    mention.headWord = token;
    annotator.md = ( ann, dictionaries, corefProps) -> Collections.singletonList(Collections.singletonList(mention));
    annotator.dictionaries = null;
    annotation.set(CorefMentionsAnnotation.class, new ArrayList<>());
    annotator.annotate(annotation);
    assertEquals("false", annotator.corefProperties.getProperty("removeNestedMentions"));
    List<Mention> corefMentions = annotation.get(CorefMentionsAnnotation.class);
    assertEquals(1, corefMentions.size());
    assertEquals(0, ((int) (corefMentions.get(0).sentNum)));
    assertEquals(0, ((int) (corefMentions.get(0).mentionID)));
    Map<Integer, Integer> corefToEntity = annotation.get(CorefMentionToEntityMentionMappingAnnotation.class);
    Map<Integer, Integer> entityToCoref = annotation.get(EntityMentionToCorefMentionMappingAnnotation.class);
    assertEquals(Integer.valueOf(0), corefToEntity.get(0));
    assertEquals(Integer.valueOf(0), entityToCoref.get(0));
}


