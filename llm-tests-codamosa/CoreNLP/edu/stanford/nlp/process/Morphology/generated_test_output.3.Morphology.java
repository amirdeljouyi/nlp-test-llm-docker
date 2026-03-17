import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Morphology morphology = new Morphology();
    Object mockLexer = mock(Object.class);
    when(mockLexer.getClass().getMethod("next").invoke(mockLexer)).thenReturn("running");
    Field lexerField = Morphology.class.getDeclaredField("lexer");
    lexerField.setAccessible(true);
    lexerField.set(morphology, new Object() {
        public String next() {
            return "running";
        }
    });
    Word result = morphology.next();
    assertNotNull(result);
    assertEquals("running", result.word());
}

@Test
public void test2()
{
    Morphology morphology = new Morphology();
    Word input = new Word("running");
    Word result = morphology.stem(input);
    assertEquals("run", result.word());
}

@Test
public void test3()
{
    String word = "cars";
    String tag = "NN";
    boolean lowercase = true;
    Morpha lexer = new Morpha();
    Method method = Morphology.class.getDeclaredMethod("lemmatize", String.class, String.class, Morpha.class, boolean.class);
    method.setAccessible(true);
    String result = ((String) (method.invoke(null, word, tag, lexer, lowercase)));
    assertEquals("car", result);
}

@Test
public void test4()
{
    Morphology morphology = new Morphology();
    String word = "running";
    String tag = "VBG";
    String expectedLemma = "run";
    String actualLemma = morphology.lemma(word, tag);
    Assert.assertEquals(expectedLemma, actualLemma);
}

@Test
public void test5()
{
    Morphology morphology = new Morphology();
    String word = "running";
    String tag = "VBG";
    String expectedLemma = "run";
    String actualLemma = morphology.lemmatize(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test6()
{
    Morphology morphology = new Morphology();
    Word inputWord = new Word("running");
    Word stemmedWord = morphology.stem(inputWord);
    assertEquals("run", stemmedWord.word());
}

@Test
public void test7()
{
    WordTag input = new WordTag("running", "VBG");
    WordLemmaTag result = Morphology.lemmatizeStatic(input);
    assertEquals("running", result.word());
    assertEquals("run", result.lemma());
    assertEquals("VBG", result.tag());
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
    WordTag result = Morphology.stemStatic("running", "VBG");
    assertNotNull("Result should not be null", result);
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
    String word = "running";
    String tag = "VBG";
    String expectedLemma = "run";
    String actualLemma = Morphology.lemmaStatic(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test12()
{
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    String[] args = new String[]{ "-stem", "running_VBG" };
    Morphology.main(args);
    System.setOut(originalOut);
    String output = outContent.toString().trim();
    assertTrue("Output should contain ->", output.contains("running_VBG -->"));
}

@Test
public void test13()
{
    Morphology morphology = new Morphology();
    Word inputWord = new Word("running");
    Word result = morphology.stem(inputWord);
    assertEquals("run", result.word());
}


