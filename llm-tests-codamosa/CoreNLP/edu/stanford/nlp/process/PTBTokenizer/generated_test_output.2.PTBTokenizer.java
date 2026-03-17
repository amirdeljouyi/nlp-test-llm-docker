import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String input = "Hello, world!";
    StringReader reader = new StringReader(input);
    Tokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    Word firstToken = tokenizer.next();
    Word secondToken = tokenizer.next();
    Word thirdToken = tokenizer.next();
    boolean hasMore = tokenizer.hasNext();
    assertEquals("Hello", firstToken.word());
    assertEquals(",", secondToken.word());
    assertEquals("world", thirdToken.word());
    assertTrue("Tokenizer should have another token", hasMore);
    Word fourthToken = tokenizer.next();
    assertEquals("!", fourthToken.word());
    assertFalse("Tokenizer should not have more tokens", tokenizer.hasNext());
}

@Test
public void test2()
{
    Reader reader = new StringReader("Stanford NLP works.");
    PTBTokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    List<Word> tokens = new ArrayList<Word>();
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    if (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    }
    assertEquals(4, tokens.size());
    assertEquals(new Word("Stanford"), tokens.get(0));
    assertEquals(new Word("NLP"), tokens.get(1));
    assertEquals(new Word("works"), tokens.get(2));
    assertEquals(new Word("."), tokens.get(3));
}

@Test
public void test3()
{
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    StringReader reader = new StringReader("Stanford NLP.");
    List<CoreLabel> tokens = factory.getTokenizer(reader).tokenize();
    assertEquals(3, tokens.size());
    assertEquals("Stanford", tokens.get(0).word());
    assertEquals("NLP", tokens.get(1).word());
    assertEquals(".", tokens.get(2).word());
}

@Test
public void test4()
{
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    StringReader input = new StringReader("The quick brown fox.");
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(input);
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
    Word token1 = tokenizer.next();
    Word token2 = tokenizer.next();
    Word token3 = tokenizer.next();
    assertEquals("Hello", token1.word());
    assertEquals(",", token2.word());
    assertEquals("world", token3.word());
}

@Test
public void test6()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("The quick brown fox.");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = tokenizer.tokenize();
    assertEquals(5, tokens.size());
    assertEquals("The", tokens.get(0).word());
    assertEquals("quick", tokens.get(1).word());
    assertEquals("brown", tokens.get(2).word());
    assertEquals("fox", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test7()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    assertNotNull(factory);
    String input = "Hello world!";
    Tokenizer<Word> tokenizer = factory.getTokenizer(new StringReader(input));
    assertNotNull(tokenizer);
    List<Word> tokens = tokenizer.tokenize();
    assertEquals(3, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals("world", tokens.get(1).word());
    assertEquals("!", tokens.get(2).word());
}

@Test
public void test8()
{
    String expected = "NEWLINE";
    String actual = PTBTokenizer.getNewlineToken();
    assertEquals("The returned newline token should match the expected literal.", expected, actual);
}

@Test
public void test9()
{
    HasWord token1 = new Word("Hello");
    HasWord token2 = new Word("-LRB-");
    HasWord token3 = new Word("World");
    HasWord token4 = new Word("-RRB-");
    HasWord token5 = new Word(".");
    List<HasWord> ptbWords = Arrays.asList(token1, token2, token3, token4, token5);
    String result = PTBTokenizer.labelList2Text(ptbWords);
    assertEquals("Hello (World).", result);
}

@Test
public void test10()
{
    String ptbInput = "This is a test . It includes parentheses -LRB- like this -RRB- and quotes `` quoted text '' .";
    String expectedOutput = "This is a test. It includes parentheses (like this) and quotes \"quoted text\".";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test11()
{
    String ptbText = "Hello , world ! This is a test .";
    String expected = "Hello, world! This is a test.";
    String actual = PTBTokenizer.ptb2Text(ptbText);
    assertEquals(expected, actual);
}

@Test
public void test12()
{
    String ptbToken = "-LRB-";
    String expected = "(";
    String actual = PTBTokenizer.ptbToken2Text(ptbToken);
    assertEquals(expected, actual);
}

@Test
public void test13()
{
    String ptbInput = "He said , `` I can\\\'t go . \'\'";
    String expectedOutput = "He said, \"I can\'t go.\"";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test14()
{
    File tempInput = File.createTempFile("ptbtokenizer_input", ".txt");
    File tempOutput = new File(tempInput.getAbsolutePath() + ".tok");
    tempInput.deleteOnExit();
    tempOutput.deleteOnExit();
    String inputText = "This is a sample sentence.";
    Files.write(tempInput.toPath(), inputText.getBytes(UTF_8));
    PTBTokenizer.main(new String[]{ tempInput.getAbsolutePath() });
    List<String> outputLines = Files.readAllLines(tempOutput.toPath(), UTF_8);
    assert outputLines.contains("This");
    assert outputLines.contains("is");
    assert outputLines.contains("a");
    assert outputLines.contains("sample");
    assert outputLines.contains("sentence");
    assert outputLines.contains(".");
}

