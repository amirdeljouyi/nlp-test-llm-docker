import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Reader input = new StringReader("This is a test.");
    Tokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(input);
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
public void test2()
{
    String inputText = "The quick brown fox.";
    StringReader reader = new StringReader(inputText);
    PTBTokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    List<String> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        tokens.add(tokenizer.next().word());
    } 
    List<String> expectedTokens = new ArrayList<>();
    expectedTokens.add("The");
    expectedTokens.add("quick");
    expectedTokens.add("brown");
    expectedTokens.add("fox");
    expectedTokens.add(".");
    assertEquals("Token list should match expected output", expectedTokens, tokens);
}

@Test
public void test3()
{
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    String input = "Hello, world!";
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(new StringReader(input));
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(4, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals(",", tokens.get(1).word());
    assertEquals("world", tokens.get(2).word());
    assertEquals("!", tokens.get(3).word());
}

@Test
public void test4()
{
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    assertNotNull("TokenizerFactory returned by coreLabelFactory() should not be null", factory);
}

@Test
public void test5()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("Hello world!");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    } 
    assertEquals(2, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals("world", tokens.get(1).word());
}

@Test
public void test6()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    assertNotNull("Factory should not be null", factory);
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader("Hello world!"));
    assertNotNull("Tokenizer should not be null", tokenizer);
    List<Word> tokens = tokenizer.tokenize();
    assertEquals("Unexpected number of tokens", 3, tokens.size());
    assertEquals("First token mismatch", "Hello", tokens.get(0).word());
    assertEquals("Second token mismatch", "world", tokens.get(1).word());
    assertEquals("Third token mismatch", "!", tokens.get(2).word());
}

@Test
public void test7()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    String input = "This is a test.";
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(input));
    List<Word> tokens = new ArrayList<Word>();
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    assertEquals("This", tokens.get(0).word());
    assertEquals("is", tokens.get(1).word());
    assertEquals("a", tokens.get(2).word());
    assertEquals("test", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test8()
{
    String expected = "NL";
    String actual = PTBTokenizer.getNewlineToken();
    assertEquals("The newline token should be 'NL'", expected, actual);
}

@Test
public void test9()
{
    HasWord word1 = new Word("This");
    HasWord word2 = new Word("is");
    HasWord word3 = new Word("a");
    HasWord word4 = new Word("test");
    HasWord word5 = new Word(".");
    List<HasWord> tokenized = Arrays.asList(word1, word2, word3, word4, word5);
    String result = PTBTokenizer.labelList2Text(tokenized);
    assertEquals("This is a test.", result);
}

@Test
public void test10()
{
    String ptbInput = "Hello , world ! It 's a beautiful day .";
    String expectedOutput = "Hello, world! It's a beautiful day.";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test11()
{
    String ptbEscapedInput = "I can not believe it !";
    String expectedOutput = "I cannot believe it!";
    String actualOutput = PTBTokenizer.ptb2Text(ptbEscapedInput);
    assertEquals(expectedOutput, actualOutput);
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
    String ptbInput = "This is a test , with PTB tokens .";
    String expectedOutput = "This is a test, with PTB tokens.";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test14()
{
    File inputFile = File.createTempFile("ptbtokenizer_test_input", ".txt");
    inputFile.deleteOnExit();
    String inputText = "This is a Test.";
    Files.write(inputFile.toPath(), inputText.getBytes(UTF_8));
    File outputFile = new File(inputFile.getAbsolutePath() + ".tok");
    outputFile.deleteOnExit();
    String[] args = new String[]{ "-lowerCase", inputFile.getAbsolutePath() };
    PTBTokenizer.main(args);
    String output = new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);
    assert output.contains("this");
    assert output.contains("is");
    assert output.contains("a");
    assert output.contains("test");
    assert output.contains(".");
}

