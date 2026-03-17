import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Morphology morphology = new Morphology();
    Field lexerField = Morphology.class.getDeclaredField("lexer");
    lexerField.setAccessible(true);
    Object mockLexer = new Object() {
        public String next() {
            return "running";
        }
    };
    lexerField.set(morphology, mockLexer);
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
    Assert.assertEquals("run", result.word());
}

@Test
public void test3()
{
    Morpha lexer = new Morpha();
    String word = "dogs_are";
    String tag = "NNS";
    boolean lowercase = true;
    String modifiedWord = word.replaceAll("_", "ᳰ");
    modifiedWord = modifiedWord.replaceAll(" ", "ᳱ");
    modifiedWord = modifiedWord.replaceAll("\n", "ᳲ");
    String inputToLexer = (modifiedWord + "_") + tag;
    lexer.setOption(1, lowercase);
    lexer.yyreset(new StringReader(inputToLexer));
    lexer.yybegin(scan);
    String lemma = lexer.next();
    lexer.next();
    lemma = lemma.replaceAll("ᳰ", "_");
    lemma = lemma.replaceAll("ᳱ", " ");
    lemma = lemma.replaceAll("ᳲ", "\n");
    assertEquals(lemma, invokeLemmatize(word, tag, lowercase));
}

@Test
public void test4()
{
    Morphology morphology = new Morphology();
    String result = morphology.lemma("running", "VBG");
    assertEquals("run", result);
}

@Test
public void test5()
{
    Morphology morphology = new Morphology();
    String word = "cars";
    String tag = "NNS";
    String expectedLemma = "car";
    String actualLemma = morphology.lemmatize(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test6()
{
    Morphology morphology = new Morphology();
    Word inputWord = new Word("running");
    Word result = morphology.stem(inputWord);
    assertEquals("run", result.word());
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
    String inputWord = "running";
    String posTag = "VBG";
    WordTag result = Morphology.stemStatic(inputWord, posTag);
    assertNotNull(result);
    assertEquals("run", result.word());
    assertEquals("VBG", result.tag());
}

@Test
public void test9()
{
    String word = "running";
    String tag = "VBG";
    WordTag result = Morphology.stemStatic(word, tag);
    assertNotNull(result);
    assertEquals("run", result.word());
    assertEquals("VBG", result.tag());
}

@Test
public void test10()
{
    String word = "running";
    String tag = "VBG";
    String expected = "run";
    String actual = Morphology.lemmaStatic(word, tag);
    assertEquals(expected, actual);
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
    PrintStream originalOut = System.out;
    PrintStream originalErr = System.err;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));
    Morphology.main(new String[0]);
    System.setOut(originalOut);
    System.setErr(originalErr);
    String output = outContent.toString();
    assert output != null;
}

@Test
public void test13()
{
    Morphology morphology = new Morphology();
    Word input = new Word("running");
    Word result = morphology.stem(input);
    assertEquals(new Word("run"), result);
}

