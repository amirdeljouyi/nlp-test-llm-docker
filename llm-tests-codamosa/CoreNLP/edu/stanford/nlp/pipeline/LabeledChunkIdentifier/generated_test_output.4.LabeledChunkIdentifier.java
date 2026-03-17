import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String negLabel = "O";
    String defaultNegTag = "NEG";
    String defaultPosTag = "POS";
    boolean ignoreProvidedTag = true;
    String inputLabel = "NP-PERSON";
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier(defaultPosTag, defaultNegTag, negLabel, ignoreProvidedTag);
    LabelTagType result = identifier.getTagType(inputLabel);
    assertEquals("NP-PERSON", result.label);
    assertEquals("POS", result.tag);
    assertEquals("PERSON", result.type);
}

@Test
public void test2()
{
    LabeledChunkIdentifier labeledChunkIdentifier = new LabeledChunkIdentifier();
    Field negLabelField = LabeledChunkIdentifier.class.getDeclaredField("negLabel");
    negLabelField.setAccessible(true);
    negLabelField.set(labeledChunkIdentifier, "NEG_LABEL_TEST");
    String result = labeledChunkIdentifier.getNegLabel();
    assertEquals("NEG_LABEL_TEST", result);
}

@Test
public void test3()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "Barack");
    token1.set(AnswerAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Obama");
    token2.set(AnswerAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "visited");
    token3.set(AnswerAnnotation.class, "O");
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3);
    List<CoreMap> chunks = identifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, AnswerAnnotation.class);
    assertNotNull(chunks);
    assertEquals(1, chunks.size());
    String chunkText = chunks.get(0).get(TextAnnotation.class);
    assertEquals("Barack Obama", chunkText);
}

@Test
public void test4()
{
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "New");
    token1.set(AnswerAnnotation.class, "B-LOC");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "York");
    token2.set(AnswerAnnotation.class, "I-LOC");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "City");
    token3.set(AnswerAnnotation.class, "I-LOC");
    CoreLabel token4 = new CoreLabel();
    token4.set(TextAnnotation.class, ".");
    token4.set(AnswerAnnotation.class, "O");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, AnswerAnnotation.class);
    assertNotNull(chunks);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    assertEquals("New York City", chunk.get(TextAnnotation.class));
}

@Test
public void test5()
{
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "Barack");
    token1.set(AnswerAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Obama");
    token2.set(AnswerAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "was");
    token3.set(AnswerAnnotation.class, "O");
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3);
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, AnswerAnnotation.class);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    String chunkText = chunk.get(TextAnnotation.class);
    assertEquals("Barack Obama", chunkText);
    String chunkLabel = chunk.get(AnswerAnnotation.class);
    assertEquals("PERSON", chunkLabel);
}

@Test
public void test6()
{
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "The");
    token1.set(AnswerAnnotation.class, "B-NP");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "cat");
    token2.set(AnswerAnnotation.class, "I-NP");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "sat");
    token3.set(AnswerAnnotation.class, "O");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, AnswerAnnotation.class);
    assertNotNull("Chunks list should not be null", chunks);
    assertEquals("Should identify one chunk", 1, chunks.size());
    CoreMap chunk = chunks.get(0);
    String chunkText = chunk.get(TextAnnotation.class);
    assertEquals("Chunk text should be 'The cat'", "The cat", chunkText);
}

