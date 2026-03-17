import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    TokensRegexNERAnnotator annotator = new TokensRegexNERAnnotator("tokensregexner.rules");
    Set<Class<?>> mockedAnnotationFields = new HashSet<>();
    mockedAnnotationFields.add(NamedEntityTagAnnotation.class);
    mockedAnnotationFields.add(NormalizedNamedEntityTagAnnotation.class);
    Field field = TokensRegexNERAnnotator.class.getDeclaredField("annotationFields");
    field.setAccessible(true);
    field.set(annotator, mockedAnnotationFields);
    Set<Class<? extends CoreAnnotation>> expected = Collections.unmodifiableSet(new ArraySet<>(mockedAnnotationFields));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Test annotation");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Stanford");
    tokens.add(token1);
    annotation.set(TokensAnnotation.class, tokens);
    annotation.set(SentencesAnnotation.class, null);
    TokensRegexNERAnnotator annotator = new TokensRegexNERAnnotator("", false);
    annotator.annotate(annotation);
}

