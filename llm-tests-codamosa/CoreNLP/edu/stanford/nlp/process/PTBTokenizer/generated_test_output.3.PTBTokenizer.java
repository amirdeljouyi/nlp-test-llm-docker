import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Reader reader = new StringReader("Hello, world!");
    PTBTokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    List<Word> tokens = new ArrayList<>();
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    Assert.assertEquals(3, tokens.size());
    Assert.assertEquals("Hello", tokens.get(0).word());
    Assert.assertEquals(",", tokens.get(1).word());
    Assert.assertEquals("world", tokens.get(2).word());
}

@Test
public void test2()
{
    String input = "Hello, world!";
    StringReader reader = new StringReader(input);
    PTBTokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    List<Word> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    } 
    assertEquals(4, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals(",", tokens.get(1).word());
    assertEquals("world", tokens.get(2).word());
    assertEquals("!", tokens.get(3).word());
}

@Test
public void test3()
{
    String input = "Hello, World!";
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(new StringReader(input));
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(4, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals(",", tokens.get(1).word());
    assertEquals("World", tokens.get(2).word());
    assertEquals("!", tokens.get(3).word());
    assertTrue(tokens.get(0) instanceof CoreLabel);
    assertTrue(tokens.get(1) instanceof CoreLabel);
    assertTrue(tokens.get(2) instanceof CoreLabel);
    assertTrue(tokens.get(3) instanceof CoreLabel);
}

@Test
public void test4()
{
    String input = "This is a test.";
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(new StringReader(input));
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(5, tokens.size());
    assertEquals("This", tokens.get(0).word());
    assertEquals("is", tokens.get(1).word());
    assertEquals("a", tokens.get(2).word());
    assertEquals("test", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test5()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("Hello world!");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = tokenizer.tokenize();
    assertEquals(2, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals("world", tokens.get(1).word());
}

@Test
public void test6()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("This is a test.");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    } 
    assertEquals(5, tokens.size());
    assertEquals("This", tokens.get(0).word());
    assertEquals("is", tokens.get(1).word());
    assertEquals("a", tokens.get(2).word());
    assertEquals("test", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test7()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader("Hello world!"));
    Word first = tokenizer.next();
    Word second = tokenizer.next();
    assertEquals("Hello", first.word());
    assertEquals("world", second.word());
}

@Test
public void test8()
{
    String expected = "NL";
    String actual = PTBTokenizer.getNewlineToken();
    assertEquals("The newline token should match the expected literal", expected, actual);
}

@Test
public void test9()
{
    HasWord token1 = new Word("``");
    HasWord token2 = new Word("Hello");
    HasWord token3 = new Word("world");
    HasWord token4 = new Word("''");
    HasWord token5 = new Word("!");
    List<HasWord> inputTokens = Arrays.asList(token1, token2, token3, token4, token5);
    String result = PTBTokenizer.labelList2Text(inputTokens);
    assertEquals("\"Hello world\"!", result);
}

@Test
public void test10()
{
    String ptbText = "This is a test ''sentence'' with ``quotes'', dashes -- and escaped characters like -LRB- example -RRB- .";
    String expected = "This is a test \"sentence\" with \"quotes\", dashes -- and escaped characters like ( example ) .";
    String actual = PTBTokenizer.ptb2Text(ptbText);
    assertEquals(expected, actual);
}

@Test
public void test11()
{
    String ptbText = "The quick brown fox -LRB- not lazy -RRB- jumps over the lazy dog .";
    String expected = "The quick brown fox (not lazy) jumps over the lazy dog.";
    String actual = PTBTokenizer.ptb2Text(ptbText);
    assertEquals(expected, actual);
}

@Test
public void test12()
{
    String input = "-LRB-";
    String expectedOutput = "(";
    String actualOutput = PTBTokenizer.ptbToken2Text(input);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test13()
{
    String ptbEscaped = "The quick brown fox said , `` Hello World ! ''";
    String expected = "The quick brown fox said, \"Hello World!\"";
    String actual = PTBTokenizer.ptb2Text(ptbEscaped);
    assertEquals(expected, actual);
}

@Test
public void test14()
{
    File inputFile = File.createTempFile("ptbtok_input", ".txt");
    inputFile.deleteOnExit();
    PrintWriter inputWriter = new PrintWriter(inputFile, "UTF-8");
    inputWriter.println("This is a test.");
    inputWriter.close();
    File ioMappingFile = File.createTempFile("ptbtok_mapping", ".list");
    ioMappingFile.deleteOnExit();
    PrintWriter mapWriter = new PrintWriter(ioMappingFile, "UTF-8");
    File outputFile = new File(inputFile.getAbsolutePath() + ".tok");
    outputFile.deleteOnExit();
    mapWriter.println(inputFile.getAbsolutePath());
    mapWriter.close();
    String[] args = new String[]{ "-ioFileList", ioMappingFile.getAbsolutePath() };
    PTBTokenizer.main(args);
    assertTrue("Output file should exist", outputFile.exists());
    List<String> lines = Files.readAllLines(outputFile.toPath(), UTF_8);
    assertEquals("First token should be 'This'", "This", lines.get(0));
    assertEquals("Second token should be 'is'", "is", lines.get(1));
    assertEquals("Third token should be 'a'", "a", lines.get(2));
    assertEquals("Fourth token should be 'test.'", "test.", lines.get(3));
}

