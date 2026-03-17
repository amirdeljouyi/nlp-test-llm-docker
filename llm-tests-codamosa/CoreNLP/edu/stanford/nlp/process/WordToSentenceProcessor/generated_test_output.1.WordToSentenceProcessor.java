import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("!");
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    Document<String, String, CoreLabel> inputDoc = new Document<>(tokens);
    WordToSentenceProcessor<CoreLabel> processor = new WordToSentenceProcessor<>();
    Document<String, String, List<CoreLabel>> resultDoc = processor.processDocument(inputDoc);
    assertEquals(1, resultDoc.size());
    List<CoreLabel> sentence = resultDoc.get(0);
    assertEquals(3, sentence.size());
    assertEquals("Hello", sentence.get(0).word());
    assertEquals("world", sentence.get(1).word());
    assertEquals("!", sentence.get(2).word());
}

@Test
public void test2()
{
    List<HasWord> tokens = new ArrayList<>();
    tokens.add(() -> "Hello");
    tokens.add(() -> ",");
    tokens.add(() -> "world");
    tokens.add(() -> "!");
    tokens.add(() -> "This");
    tokens.add(() -> "is");
    tokens.add(() -> "a");
    tokens.add(() -> "test");
    tokens.add(() -> ".");
    WordToSentenceProcessor<HasWord> processor = new WordToSentenceProcessor<>();
    List<List<HasWord>> sentences = processor.process(tokens);
    assertEquals(2, sentences.size());
    assertEquals(4, sentences.get(0).size());
    assertEquals("Hello", sentences.get(0).get(0).word());
    assertEquals(",", sentences.get(0).get(1).word());
    assertEquals("world", sentences.get(0).get(2).word());
    assertEquals("!", sentences.get(0).get(3).word());
    assertEquals(5, sentences.get(1).size());
    assertEquals("This", sentences.get(1).get(0).word());
    assertEquals("is", sentences.get(1).get(1).word());
    assertEquals("a", sentences.get(1).get(2).word());
    assertEquals("test", sentences.get(1).get(3).word());
    assertEquals(".", sentences.get(1).get(4).word());
}

@Test
public void test3()
{
    WordToSentenceProcessor.NewlineIsSentenceBreak result = WordToSentenceProcessor.stringToNewlineIsSentenceBreak("always");
    assertEquals(ALWAYS, result);
}

