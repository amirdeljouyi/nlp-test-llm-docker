import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Map<String, String> props = new HashMap<>();
    props.put("ner.tokensregex.rules", "dummy.rules");
    TokensRegexNERAnnotator annotator = new TokensRegexNERAnnotator(props);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertTrue(result.contains(NamedEntityTagAnnotation.class));
}

@Test
public void test2()
{
    Annotation mockAnnotation = mock(Annotation.class);
    CoreLabel mockToken = new CoreLabel();
    List<CoreLabel> tokenList = new ArrayList<>();
    tokenList.add(mockToken);
    when(mockAnnotation.get(SentencesAnnotation.class)).thenReturn(null);
    when(mockAnnotation.get(TokensAnnotation.class)).thenReturn(tokenList);
    TokensRegexNERAnnotator annotator = new TokensRegexNERAnnotator("");
    TokensRegexNERAnnotator spyAnnotator = spy(annotator);
    doNothing().when(spyAnnotator).annotateMatched(tokenList);
    spyAnnotator.annotate(mockAnnotation);
    verify(spyAnnotator).annotateMatched(tokenList);
}

