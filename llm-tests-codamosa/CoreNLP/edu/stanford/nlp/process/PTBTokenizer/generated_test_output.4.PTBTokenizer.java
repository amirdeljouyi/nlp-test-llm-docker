import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String text = "Hello, world!";
    StringReader reader = new StringReader(text);
    PTBTokenizer<Word> tokenizer = PTBTokenizer.newPTBTokenizer(reader);
    List<String> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        Word word = tokenizer.next();
        tokens.add(word.word());
    } 
    assertEquals(3, tokens.size());
    assertEquals("Hello", tokens.get(0));
    assertEquals(",", tokens.get(1));
    assertEquals("world", tokens.get(2));
}

@Test
public void test2()
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
public void test3()
{
    TokenizerFactory<CoreLabel> factory = PTBTokenizer.coreLabelFactory();
    String sampleText = "Hello, world!";
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(new StringReader(sampleText));
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
    StringReader reader = new StringReader("Stanford NLP works.");
    Tokenizer<CoreLabel> tokenizer = factory.getTokenizer(reader);
    List<CoreLabel> tokens = tokenizer.tokenize();
    assertEquals(3, tokens.size());
    assertEquals("Stanford", tokens.get(0).word());
    assertEquals("NLP", tokens.get(1).word());
    assertEquals("works", tokens.get(2).word());
}

@Test
public void test5()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("Stanford NLP is awesome.");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = new ArrayList<Word>();
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    tokens.add(tokenizer.next());
    assertEquals(5, tokens.size());
    assertEquals("Stanford", tokens.get(0).word());
    assertEquals("NLP", tokens.get(1).word());
    assertEquals("is", tokens.get(2).word());
    assertEquals("awesome", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test6()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("Hello, world!");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = tokenizer.tokenize();
    assertEquals(4, tokens.size());
    assertEquals("Hello", tokens.get(0).word());
    assertEquals(",", tokens.get(1).word());
    assertEquals("world", tokens.get(2).word());
    assertEquals("!", tokens.get(3).word());
}

@Test
public void test7()
{
    TokenizerFactory<Word> factory = PTBTokenizer.factory();
    StringReader reader = new StringReader("Stanford NLP is awesome.");
    Tokenizer<Word> tokenizer = factory.getTokenizer(reader);
    List<Word> tokens = new ArrayList<>();
    while (tokenizer.hasNext()) {
        tokens.add(tokenizer.next());
    } 
    assertEquals(5, tokens.size());
    assertEquals("Stanford", tokens.get(0).word());
    assertEquals("NLP", tokens.get(1).word());
    assertEquals("is", tokens.get(2).word());
    assertEquals("awesome", tokens.get(3).word());
    assertEquals(".", tokens.get(4).word());
}

@Test
public void test8()
{
    String expected = "NEWLINE";
    String actual = PTBTokenizer.getNewlineToken();
    assertEquals(expected, actual);
}

@Test
public void test9()
{
    HasWord word1 = new Word("The");
    HasWord word2 = new Word("dog");
    HasWord word3 = new Word("``");
    HasWord word4 = new Word("barked");
    HasWord word5 = new Word("''");
    HasWord word6 = new Word(".");
    List<HasWord> ptbWords = Arrays.asList(word1, word2, word3, word4, word5, word6);
    String result = PTBTokenizer.labelList2Text(ptbWords);
    assertEquals("The dog \"barked\".", result);
}

@Test
public void test10()
{
    String ptbText = "The\\ cat\\ \'s\\ hat\\ was\\ on\\ the\\ mat\\ .";
    String expected = "The cat's hat was on the mat.";
    String actual = PTBTokenizer.ptb2Text(ptbText);
    assertEquals(expected, actual);
}

@Test
public void test11()
{
    String ptbEscaped = "He said , `` Hello ! ''";
    String expected = "He said, “Hello!”";
    String actual = PTBTokenizer.ptb2Text(ptbEscaped);
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
    String ptbInput = "Hello , world !";
    String expectedOutput = "Hello, world!";
    String actualOutput = PTBTokenizer.ptb2Text(ptbInput);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test14()
{
    PrintStream originalOut = System.out;
    PrintStream originalErr = System.err;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent, true, "UTF-8"));
    System.setErr(new PrintStream(errContent, true, "UTF-8"));
    String[] args = new String[]{ "-help" };
    PTBTokenizer.main(args);
    System.setOut(originalOut);
    System.setErr(originalErr);
    String stdOutText = outContent.toString(UTF_8.name());
    String stdErrText = errContent.toString(UTF_8.name());
    assert stdOutText.isEmpty();
    assert stdErrText.contains("Usage: java edu.stanford.nlp.process.PTBTokenizer [options]* filename*");
}

