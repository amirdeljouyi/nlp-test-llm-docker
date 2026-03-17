import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    LabelTagType result = identifier.getTagType(null);
    assertNotNull(result);
    assertEquals(identifier.negLabel, result.label);
    assertEquals(identifier.negLabel, result.type);
    assertEquals(identifier.defaultNegTag, result.tag);
}

@Test
public void test2()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    String expectedNegLabel = "NEG";
    identifier.negLabel = expectedNegLabel;
    String actualNegLabel = identifier.getNegLabel();
    assertEquals(expectedNegLabel, actualNegLabel);
}

@Test
public void test3()
{
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "New");
    token1.set(NamedEntityTagAnnotation.class, "B-LOC");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "York");
    token2.set(NamedEntityTagAnnotation.class, "I-LOC");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "City");
    token3.set(NamedEntityTagAnnotation.class, "I-LOC");
    CoreLabel token4 = new CoreLabel();
    token4.set(TextAnnotation.class, ".");
    token4.set(NamedEntityTagAnnotation.class, "O");
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, NamedEntityTagAnnotation.class);
    Assert.assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    String chunkText = chunk.get(TextAnnotation.class);
    Assert.assertEquals("New York City", chunkText);
}

@Test
public void test4()
{
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "San");
    token1.set(NamedEntityTagAnnotation.class, "LOCATION");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Francisco");
    token2.set(NamedEntityTagAnnotation.class, "LOCATION");
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    tokens.add(token1);
    tokens.add(token2);
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, NamedEntityTagAnnotation.class);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    assertEquals("San Francisco", chunk.get(TextAnnotation.class));
}

@Test
public void test5()
{
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "New");
    token1.set(AnswerAnnotation.class, "B-LOC");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "York");
    token2.set(AnswerAnnotation.class, "I-LOC");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, AnswerAnnotation.class);
    assertNotNull(chunks);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    assertEquals("New York", chunk.get(TextAnnotation.class));
    assertEquals("LOC", chunk.get(AnswerAnnotation.class));
}

@Test
public void test6()
{
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "John");
    token1.set(NamedEntityTagAnnotation.class, "B-PER");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Smith");
    token2.set(NamedEntityTagAnnotation.class, "I-PER");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "went");
    token3.set(NamedEntityTagAnnotation.class, "O");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    LabeledChunkIdentifier chunker = new LabeledChunkIdentifier();
    List<CoreMap> chunks = chunker.getAnnotatedChunks(tokens, 0, TextAnnotation.class, NamedEntityTagAnnotation.class);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    assertEquals("John Smith", chunk.get(TextAnnotation.class));
}

