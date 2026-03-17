import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation annotation = new Annotation("Barack Obama was born in Hawaii.");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Barack");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Obama");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("was");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("born");
    CoreLabel token5 = new CoreLabel();
    token5.setWord("in");
    CoreLabel token6 = new CoreLabel();
    token6.setWord("Hawaii");
    CoreLabel token7 = new CoreLabel();
    token7.setWord(".");
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    tokens.add(token5);
    tokens.add(token6);
    tokens.add(token7);
    CoreMap sentence = new Annotation("Barack Obama was born in Hawaii.");
    sentence.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    TokensRegexNERAnnotator annotator = new TokensRegexNERAnnotator("edu/stanford/nlp/models/kbp/english/gazetteers", false);
    annotator.annotate(annotation);
    List<CoreMap> resultSentences = annotation.get(SentencesAnnotation.class);
    assertNotNull(resultSentences);
    assertEquals(1, resultSentences.size());
    List<CoreLabel> resultTokens = resultSentences.get(0).get(TokensAnnotation.class);
    assertNotNull(resultTokens);
    assertEquals(7, resultTokens.size());
    assertEquals("Barack", resultTokens.get(0).word());
}

