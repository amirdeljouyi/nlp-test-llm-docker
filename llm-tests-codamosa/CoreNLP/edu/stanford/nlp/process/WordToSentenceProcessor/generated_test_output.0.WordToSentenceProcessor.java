import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordToSentenceProcessor<HasWord> processor = new WordToSentenceProcessor<>();
    List<HasWord> wordList = Arrays.asList(new Word("Hello"), new Word(","), new Word("world"), new Word("!"));
    Document<String, String, HasWord> inputDoc = new Document<>("doc1", "meta1", wordList);
    Document<String, String, List<HasWord>> resultDoc = processor.processDocument(inputDoc);
    assertEquals("doc1", resultDoc.documentID());
    assertEquals("meta1", resultDoc.metaData());
    assertEquals(1, resultDoc.size());
    List<HasWord> sentence = resultDoc.get(0);
    assertEquals(4, sentence.size());
    assertEquals("Hello", sentence.get(0).word());
    assertEquals(",", sentence.get(1).word());
    assertEquals("world", sentence.get(2).word());
    assertEquals("!", sentence.get(3).word());
}

@Test
public void test2()
{
    WordToSentenceProcessor<HasWord> processor = new WordToSentenceProcessor<>();
    List<HasWord> input = new ArrayList<>();
    input.add(new SimpleHasWord("Hello"));
    input.add(new SimpleHasWord("."));
    input.add(new SimpleHasWord("How"));
    input.add(new SimpleHasWord("are"));
    input.add(new SimpleHasWord("you"));
    input.add(new SimpleHasWord("?"));
    List<List<HasWord>> result = processor.process(input);
    assertEquals(2, result.size());
    assertEquals(2, result.get(0).size());
    assertEquals("Hello", result.get(0).get(0).word());
    assertEquals(".", result.get(0).get(1).word());
    assertEquals(4, result.get(1).size());
    assertEquals("How", result.get(1).get(0).word());
    assertEquals("are", result.get(1).get(1).word());
    assertEquals("you", result.get(1).get(2).word());
    assertEquals("?", result.get(1).get(3).word());
}

@Test
public void test3()
{
    WordToSentenceProcessor.NewlineIsSentenceBreak result = WordToSentenceProcessor.stringToNewlineIsSentenceBreak("always");
    assertEquals(ALWAYS, result);
}

