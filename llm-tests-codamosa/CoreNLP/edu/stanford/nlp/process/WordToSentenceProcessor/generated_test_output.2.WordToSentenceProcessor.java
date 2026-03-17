import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    WordToSentenceProcessor<CoreLabel> processor = new WordToSentenceProcessor<>();
    List<CoreLabel> tokens = Arrays.asList(new CoreLabel() {
        {
            setWord("Hello");
        }
    }, new CoreLabel() {
        {
            setWord(",");
        }
    }, new CoreLabel() {
        {
            setWord("world");
        }
    }, new CoreLabel() {
        {
            setWord("!");
        }
    });
    Document<String, String, CoreLabel> inputDoc = new Document<>(tokens);
    Document<String, String, List<CoreLabel>> resultDoc = processor.processDocument(inputDoc);
    assertEquals(1, resultDoc.size());
    List<CoreLabel> sentence = resultDoc.get(0);
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
    List<HasWord> input = Arrays.asList(new Word("This"), new Word("is"), new Word("a"), new Word("test"), new Word("."), new Word("Another"), new Word("sentence"), new Word("."));
    List<List<HasWord>> result = processor.process(input);
    assertEquals(2, result.size());
    assertEquals(Arrays.asList(new Word("This"), new Word("is"), new Word("a"), new Word("test"), new Word(".")), result.get(0));
    assertEquals(Arrays.asList(new Word("Another"), new Word("sentence"), new Word(".")), result.get(1));
}

@Test
public void test3()
{
    WordToSentenceProcessor.NewlineIsSentenceBreak result = WordToSentenceProcessor.stringToNewlineIsSentenceBreak("always");
    assertEquals(ALWAYS, result);
}

