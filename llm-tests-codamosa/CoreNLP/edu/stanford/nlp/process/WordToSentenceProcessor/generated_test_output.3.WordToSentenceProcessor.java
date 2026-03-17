import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    CoreLabel token3 = new CoreLabel();
    token3.setWord(".");
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3);
    Document<String, String, CoreLabel> inputDoc = new Document<>(tokens);
    WordToSentenceProcessor<CoreLabel> processor = new WordToSentenceProcessor<>();
    Document<String, String, List<CoreLabel>> outputDoc = processor.processDocument(inputDoc);
    assertEquals(1, outputDoc.size());
    List<CoreLabel> sentence = outputDoc.get(0);
    assertEquals(3, sentence.size());
    assertEquals("Hello", sentence.get(0).word());
    assertEquals("world", sentence.get(1).word());
    assertEquals(".", sentence.get(2).word());
}

@Test
public void test2()
{
    WordToSentenceProcessor<Word> processor = new WordToSentenceProcessor<>();
    List<Word> input = new ArrayList<>();
    input.add(new Word("This"));
    input.add(new Word("is"));
    input.add(new Word("a"));
    input.add(new Word("sentence"));
    input.add(new Word("."));
    input.add(new Word("Another"));
    input.add(new Word("one"));
    input.add(new Word("."));
    List<List<Word>> result = processor.process(input);
    assertEquals(2, result.size());
    assertEquals(5, result.get(0).size());
    assertEquals("This", result.get(0).get(0).word());
    assertEquals(".", result.get(0).get(4).word());
    assertEquals(3, result.get(1).size());
    assertEquals("Another", result.get(1).get(0).word());
    assertEquals(".", result.get(1).get(2).word());
}

@Test
public void test3()
{
    WordToSentenceProcessor.NewlineIsSentenceBreak result = WordToSentenceProcessor.stringToNewlineIsSentenceBreak("always");
    assertEquals(ALWAYS, result);
}


