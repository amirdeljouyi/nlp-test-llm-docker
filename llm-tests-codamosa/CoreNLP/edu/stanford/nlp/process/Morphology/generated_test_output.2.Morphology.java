import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Morphology morphology = new Morphology();
    Object mockLexer = mock(Object.class);
    when(mockLexer.toString()).thenReturn("mockLexer");
    when(mockLexer.getClass().getMethod("next").invoke(mockLexer)).thenReturn("testing");
    Field lexerField = Morphology.class.getDeclaredField("lexer");
    lexerField.setAccessible(true);
    lexerField.set(morphology, new Object() {
        public String next() {
            return "testing";
        }
    });
    Word result = morphology.next();
    assertNotNull(result);
    assertEquals("testing", result.word());
}

@Test
public void test2()
{
    Morphology morphology = new Morphology();
    Word inputWord = new Word("running");
    Word expectedStemmedWord = new Word("run");
    Word actualStemmedWord = morphology.stem(inputWord);
    assertEquals(expectedStemmedWord, actualStemmedWord);
}

@Test
public void test3()
{
    String inputWord = "dogs";
    String tag = "NNS";
    boolean lowercase = true;
    Morpha lexer = new Morpha(new StringReader("dogs_NNS"));
    Method method = Morphology.class.getDeclaredMethod("lemmatize", String.class, String.class, Morpha.class, boolean.class);
    method.setAccessible(true);
    String result = ((String) (method.invoke(null, inputWord, tag, lexer, lowercase)));
    assertEquals("dog", result);
}

@Test
public void test4()
{
    Morphology morphology = new Morphology();
    String result = morphology.lemmatize("ran", "VBD");
    assertEquals("run", result);
}

@Test
public void test5()
{
    Morphology morphology = new Morphology();
    String word = "dogs";
    String tag = "NNS";
    String expectedLemma = "dog";
    String actualLemma = morphology.lemma(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test6()
{
    Word input = new Word("cars");
    Morphology morphology = new Morphology();
    Word result = morphology.stem(input);
    Assert.assertEquals("car", result.word());
}

@Test
public void test7()
{
    WordTag input = new WordTag("dogs", "NNS");
    WordLemmaTag result = Morphology.lemmatizeStatic(input);
    assertEquals("dogs", result.word());
    assertEquals("dog", result.lemma());
    assertEquals("NNS", result.tag());
}

@Test
public void test8()
{
    WordTag result = Morphology.stemStatic("running", "VBG");
    assertEquals("run", result.word());
    assertEquals("VBG", result.tag());
}

@Test
public void test9()
{
    String inputWord = "running";
    String inputTag = "VBG";
    WordTag result = Morphology.stemStatic(inputWord, inputTag);
    assertEquals("run", result.word());
    assertEquals("VBG", result.tag());
}

@Test
public void test10()
{
    String word = "running";
    String tag = "VBG";
    String expectedLemma = "run";
    String actualLemma = Morphology.lemmaStatic(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test11()
{
    String word = "ran";
    String tag = "VBD";
    String expectedLemma = "run";
    String actualLemma = Morphology.lemmaStatic(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test12()
{
    Word input = new Word("running");
    Morphology morphology = new Morphology();
    Word result = morphology.stem(input);
    assertEquals("run", result.word());
}

