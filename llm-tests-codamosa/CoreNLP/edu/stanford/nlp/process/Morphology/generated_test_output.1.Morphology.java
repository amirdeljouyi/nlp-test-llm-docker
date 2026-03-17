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
    lexerField.set(morphology, mock(Object.class));
    Object customLexer = new Object() {
        public String next() {
            return "running";
        }
    };
    lexerField.set(morphology, customLexer);
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
    String inputWord = "running_fast now";
    String inputTag = "VBG";
    boolean lowercase = true;
    String expectedLemma = "run_fast now";
    Morpha lexer = new Morpha();
    String wordWithReplacements = inputWord.replaceAll("_", "ᳰ").replaceAll(" ", "ᳱ").replaceAll("\n", "ᳲ");
    String wordtag = (wordWithReplacements + "_") + inputTag;
    lexer.setOption(1, lowercase);
    lexer.yyreset(new StringReader(wordtag));
    lexer.yybegin(scan);
    String lemma = lexer.next();
    lexer.next();
    String restoredLemma = lemma.replaceAll("ᳰ", "_").replaceAll("ᳱ", " ").replaceAll("ᳲ", "\n");
    assertEquals(expectedLemma, restoredLemma);
}

@Test
public void test4()
{
    Morphology morphology = new Morphology();
    String word = "running";
    String tag = "VBG";
    String expectedLemma = "run";
    String actualLemma = morphology.lema(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test5()
{
    Morphology morphology = new Morphology();
    String word = "dogs";
    String tag = "NNS";
    String expectedLemma = "dog";
    String actualLemma = morphology.lemma(word, tag);
    Assert.assertEquals(expectedLemma, actualLemma);
}

@Test
public void test6()
{
    Morphology morphology = new Morphology();
    Word input = new Word("cars");
    Word result = morphology.stem(input);
    assertEquals("car", result.word());
}

@Test
public void test7()
{
    WordTag wordTag = new WordTag("walked", "VBD");
    WordLemmaTag result = Morphology.lemmatizeStatic(wordTag);
    assertEquals("walked", result.word());
    assertEquals("walk", result.lemma());
    assertEquals("VBD", result.tag());
}

@Test
public void test8()
{
    WordTag result = Morphology.stemStatic("running", "VBG");
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
    File tempFile = File.createTempFile("verbTable", ".txt");
    tempFile.deleteOnExit();
    FileWriter writer = new FileWriter(tempFile);
    writer.write("run jump swim");
    writer.close();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    String[] args = new String[]{ "-rebuildVerbTable", tempFile.getAbsolutePath() };
    Morphology.main(args);
    System.setOut(originalOut);
    String output = outContent.toString();
    assertTrue(output.contains("private static final String[] verbStems"));
    assertTrue(output.contains("\"run\""));
    assertTrue(output.contains("\"jump\""));
    assertTrue(output.contains("\"swim\""));
}

@Test
public void test13()
{
    Morphology morphology = new Morphology();
    Word input = new Word("cars");
    Word result = morphology.stem(input);
    assertEquals("car", result.word());
}

