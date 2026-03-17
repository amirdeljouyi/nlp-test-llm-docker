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
    CoreLabel titleToken = new CoreLabel();
    titleToken.setWord("President");
    titleToken.set(FineGrainedNamedEntityTagAnnotation.class, "TITLE");
    CoreLabel joeToken = new CoreLabel();
    joeToken.setWord("Joe");
    joeToken.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel smithToken = new CoreLabel();
    smithToken.setWord("Smith");
    smithToken.set(FineGrainedNamedEntityTagAnnotation.class, "PERSON");
    CoreLabel possessiveToken = new CoreLabel();
    possessiveToken.setWord("'s");
    List<CoreLabel> cmTokens = Arrays.asList(titleToken, joeToken, smithToken, possessiveToken);
    CoreMap cmSentence = new ArrayCoreMap();
    cmSentence.set(TokensAnnotation.class, cmTokens);
    List<CoreMap> sentences = Arrays.asList(cmSentence);
    Annotation ann = new Annotation("President Joe Smith's");
    ann.set(SentencesAnnotation.class, sentences);
    Mention cmMention = new Mention();
    cmMention.sentNum = 0;
    cmMention.startIndex = 0;
    cmMention.endIndex = 4;
    CoreLabel emJoe = new CoreLabel();
    emJoe.setWord("Joe");
    CoreLabel emSmith = new CoreLabel();
    emSmith.setWord("Smith");
    List<CoreLabel> emTokens = Arrays.asList(emJoe, emSmith);
    CoreMap em = new ArrayCoreMap();
    em.set(TokensAnnotation.class, emTokens);
    em.set(EntityTypeAnnotation.class, "PERSON");
    boolean result = CorefMentionAnnotator.synchCorefMentionEntityMention(ann, cmMention, em);
    assertTrue(result);
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("John went to the store.");
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "John");
    token1.set(IndexAnnotation.class, 1);
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "went");
    token2.set(IndexAnnotation.class, 2);
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "to");
    token3.set(IndexAnnotation.class, 3);
    CoreLabel token4 = new CoreLabel();
    token4.set(TextAnnotation.class, "the");
    token4.set(IndexAnnotation.class, 4);
    CoreLabel token5 = new CoreLabel();
    token5.set(TextAnnotation.class, "store");
    token5.set(IndexAnnotation.class, 5);
    CoreLabel token6 = new CoreLabel();
    token6.set(TextAnnotation.class, ".");
    token6.set(IndexAnnotation.class, 6);
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3, token4, token5, token6);
    CoreMap sentence = new Annotation("John went to the store.");
    sentence.set(TokensAnnotation.class, tokens);
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    annotation.set(TokensAnnotation.class, tokens);
    annotation.set(DocIDAnnotation.class, "sample-doc-id");
    Mention m = new Mention(0, 0, 1, 2, "John", tokens.subList(0, 1), null);
    List<Mention> mentionList = Collections.singletonList(m);
    List<List<Mention>> mentionLists = Collections.singletonList(mentionList);
    RuleBasedCorefMentionFinder mockMd = new RuleBasedCorefMentionFinder(true);
    Properties props = new Properties();
    props.setProperty("coref.input.type", "raw");
    props.setProperty("coref.language", "english");
    CorefMentionAnnotator annotator = new CorefMentionAnnotator(mockMd, new Dictionaries(props), props) {
        @Override
        public List<List<Mention>> findMentions(Annotation ann, Dictionaries dictionaries, Properties props) {
            return mentionLists;
        }
    };
    annotation.set(MentionsAnnotation.class, new ArrayList<CoreMap>());
    annotator.annotate(annotation);
    List<Mention> resultMentions = annotation.get(CorefMentionsAnnotation.class);
    assertNotNull(resultMentions);
    assertEquals(1, resultMentions.size());
    Mention result = resultMentions.get(0);
    assertEquals("John", result.spanToString());
    Set<Integer> mentionIndexes = tokens.get(0).get(CorefMentionIndexesAnnotation.class);
    assertNotNull(mentionIndexes);
    assertTrue(mentionIndexes.contains(0));
}

