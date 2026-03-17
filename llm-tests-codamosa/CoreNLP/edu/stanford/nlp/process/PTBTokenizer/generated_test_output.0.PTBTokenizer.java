import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String inputText = "Hello, world!";
    StringReader reader = new StringReader(inputText);
    PTBTokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    List<String> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        tokens.add(tokenizer.next().word());
    } 
    assertEquals(3, tokens.size());
    assertEquals("Hello", tokens.get(0));
    assertEquals(",", tokens.get(1));
    assertEquals("world", tokens.get(2));
}

@Test
public void test2()
{
    Reader reader = new StringReader("Hello, world!");
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
    String inputText = "Stanford NLP is powerful!";
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(new StringReader(inputText));
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(4, tokens.size());
    assertEquals("Stanford", tokens.get(0).word());
    assertEquals("NLP", tokens.get(1).word());
    assertEquals("is", tokens.get(2).word());
    assertEquals("powerful", tokens.get(3).word());
}

@Test
public void test4()
{
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(new StringReader("The quick brown fox."));
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(5, tokens.size());
    assertEquals("The", tokens.get(0).word());
    assertEquals("quick", tokens.get(1).word());
    assertEquals("brown", tokens.get(2).word());
    assertEquals("fox", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test5()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader("Hello, world!"));
    List<Word> tokens = tokenizer.tokenize();
    assertEquals(4, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals(",", tokens.get(1).word());
    assertEquals("world", tokens.get(2).word());
    assertEquals("!", tokens.get(3).word());
}

@Test
public void test6()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader("The quick brown fox."));
    List<Word> tokenList = tokenizer.tokenize();
    assertEquals(5, tokenList.size());
    assertEquals("The", tokenList.get(0).word());
    assertEquals("quick", tokenList.get(1).word());
    assertEquals("brown", tokenList.get(2).word());
    assertEquals("fox", tokenList.get(3).word());
    assertEquals(".", tokenList.get(4).word());
}

@Test
public void test7()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    String inputText = "Stanford NLP is great.";
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(inputText));
    List<Word> tokens = tokenizer.tokenize();
    assertEquals(5, tokens.size());
    assertEquals(new Word("Stanford"), tokens.get(0));
    assertEquals(new Word("NLP"), tokens.get(1));
    assertEquals(new Word("is"), tokens.get(2));
    assertEquals(new Word("great"), tokens.get(3));
    assertEquals(new Word("."), tokens.get(4));
}

@Test
public void test8()
{
    String expected = "NEWLINE";
    String actual = PTBTokenizer.getNewlineToken();
    assertEquals("The returned newline token should match the expected value.", expected, actual);
}

@Test
public void test9()
{
    HasWord word1 = new Word("Hello");
    HasWord word2 = new Word("-LRB-");
    HasWord word3 = new Word("world");
    HasWord word4 = new Word("-RRB-");
    HasWord word5 = new Word("!");
    List<HasWord> ptbWords = Arrays.asList(word1, word2, word3, word4, word5);
    String result = PTBTokenizer.labelList2Text(ptbWords);
    assertEquals("Hello (world)!", result);
}

@Test
public void test10()
{
    String ptbInput = "This is a test .";
    String expectedOutput = "This is a test.";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test11()
{
    String ptbInput = "Hello , world !";
    String expectedOutput = "Hello, world!";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
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
    String input = "This is a test sentence .";
    String expected = "This is a test sentence.";
    String result = PTBTokenizer.ptb2Text(input);
    assertEquals(expected, result);
}

@Test
public void test14()
{
    File inputFile = File.createTempFile("ptbtokenizer_input", ".txt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, StandardCharsets.UTF_8));
    writer.write("This is a test.");
    writer.close();
    File expectedOutputFile = new File(inputFile.getAbsolutePath() + ".tok");
    if (expectedOutputFile.exists()) {
        expectedOutputFile.delete();
    }
    PTBTokenizer.main(new String[]{ inputFile.getAbsolutePath() });
    assertTrue("Expected output file not created", expectedOutputFile.exists());
    List<String> lines = Files.readAllLines(expectedOutputFile.toPath(), UTF_8);
    assertTrue(lines.contains("This"));
    assertTrue(lines.contains("is"));
    assertTrue(lines.contains("a"));
    assertTrue(lines.contains("test"));
    assertTrue(lines.contains("."));
    inputFile.delete();
    expectedOutputFile.delete();
}

