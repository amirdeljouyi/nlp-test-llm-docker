import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    MorphaAnnotator annotator = new MorphaAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull("Returned set should not be null", result);
    assertEquals("Returned set should contain exactly one element", 1, result.size());
    assertTrue("Set should contain only CoreAnnotations.LemmaAnnotation", result.contains(LemmaAnnotation.class));
}

