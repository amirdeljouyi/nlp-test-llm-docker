import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

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
    CoreLabel token3 = new CoreLabel();
    token3.setWord("Smith");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("'s");
    List<CoreLabel> cmTokens = Arrays.asList(token1, token2, token3, token4);
    CoreMap cmSentence = new ArrayCoreMap();
    cmSentence.set(TokensAnnotation.class, cmTokens);
    Annotation annotation = new Annotation("");
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(cmSentence);
    annotation.set(SentencesAnnotation.class, sentences);
    Mention corefMention = new Mention();
    corefMention.sentNum = 0;
    corefMention.startIndex = 0;
    corefMention.endIndex = 4;
    CoreLabel emToken1 = token2;
    CoreLabel emToken2 = token3;
    List<CoreLabel> emTokens = Arrays.asList(emToken1, emToken2);
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(EntityTypeAnnotation.class, "PERSON");
    entityMention.set(TokensAnnotation.class, emTokens);
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(annotation, corefMention, entityMention);
    assertTrue(result);
}

@Test
public void test1()
{
    CorefMentionAnnotator annotator = new CorefMentionAnnotator();
    Set<Class<? extends CoreAnnotation>> requirements = annotator.requirementsSatisfied();
    assertNotNull(requirements);
    assertEquals(4, requirements.size());
    assertTrue(requirements.contains(CorefMentionsAnnotation.class));
    assertTrue(requirements.contains(ParagraphAnnotation.class));
    assertTrue(requirements.contains(SpeakerAnnotation.class));
    assertTrue(requirements.contains(UtteranceAnnotation.class));
}

@Test
public void test2()
{
    CoreLabel cmToken1 = new CoreLabel();
    cmToken1.setWord("President");
    cmToken1.set(FineGrainedNamedEntityTagAnnotation.class, "TITLE");
    CoreLabel cmToken2 = new CoreLabel();
    cmToken2.setWord("Joe");
    CoreLabel cmToken3 = new CoreLabel();
    cmToken3.setWord("Smith");
    CoreLabel cmToken4 = new CoreLabel();
    cmToken4.setWord("'s");
    List<CoreLabel> cmTokens = Arrays.asList(cmToken1, cmToken2, cmToken3, cmToken4);
    CoreMap cmSentence = new ArrayCoreMap();
    cmSentence.set(TokensAnnotation.class, cmTokens);
    List<CoreMap> sentences = Collections.singletonList(cmSentence);
    Annotation ann = new Annotation("President Joe Smith's");
    ann.set(SentencesAnnotation.class, sentences);
    Mention cm = new Mention();
    cm.sentNum = 0;
    cm.startIndex = 0;
    cm.endIndex = 4;
    CoreLabel emToken1 = cmToken2;
    CoreLabel emToken2 = cmToken3;
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
    CorefMentionAnnotator annotator = new CorefMentionAnnotator();
    Properties properties = new Properties();
    properties.setProperty("coref.input.type", "conll");
    properties.setProperty("coref.language", "zh");
    properties.setProperty("coref.specialCaseNewswire", "true");
    annotator.corefProperties = properties;
    annotator.dictionaries = mock(Dictionaries.class);
    annotator.md = mock(MentionExtractor.class);
    CoreMap sentence = mock(CoreMap.class);
    List<CoreMap> sentenceList = Arrays.asList(sentence);
    annotation.set(SentencesAnnotation.class, sentenceList);
    annotation.set(DocIDAnnotation.class, "nw_doc");
    CoreLabel token1 = new CoreLabel();
    CoreLabel token2 = new CoreLabel();
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    annotation.set(TokensAnnotation.class, tokens);
    annotation.set(MentionsAnnotation.class, new ArrayList<>());
    token1.set(EntityMentionIndexAnnotation.class, null);
    token2.set(EntityMentionIndexAnnotation.class, null);
    Mention mention = new Mention(0, 0, 1, 0, 1, null, null, null);
    List<Mention> mentionsForSentence = Arrays.asList(mention);
    List<List<Mention>> allMentions = Arrays.asList(mentionsForSentence);
    when(annotator.md.findMentions(annotation, annotator.dictionaries, properties)).thenReturn(allMentions);
    List<CoreLabel> sentTokens = new ArrayList<>();
    CoreLabel sentToken = new CoreLabel();
    sentTokens.add(sentToken);
    when(sentence.get(TokensAnnotation.class)).thenReturn(sentTokens);
    annotator.annotate(annotation);
    List<Mention> resultingMentions = annotation.get(CorefMentionsAnnotation.class);
    assertNotNull(resultingMentions);
    assertEquals(1, resultingMentions.size());
    assertEquals(0, resultingMentions.get(0).mentionID);
    assertEquals(0, resultingMentions.get(0).sentNum);
    assertEquals("false", properties.getProperty("removeNestedMentions"));
    assertNotNull(annotation.get(CorefMentionToEntityMentionMappingAnnotation.class));
    assertNotNull(annotation.get(EntityMentionToCorefMentionMappingAnnotation.class));
}

