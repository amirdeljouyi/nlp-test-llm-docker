import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    identifier.negLabel = "O";
    identifier.defaultNegTag = "NEG";
    identifier.defaultPosTag = "POS";
    identifier.ignoreProvidedTag = true;
    String label = "providedTag:O";
    LabelTagType result = identifier.getTagType(label);
    assertEquals("providedTag:O", result.label);
    assertEquals("NEG", result.tag);
    assertEquals("O", result.type);
}

@Test
public void test2()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier("NP", "NN");
    String result = identifier.getDefaultPosTag();
    assertEquals("NN", result);
}

@Test
public void test3()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    Field negLabelField = LabeledChunkIdentifier.class.getDeclaredField("negLabel");
    negLabelField.setAccessible(true);
    String expectedValue = "NEGATIVE_LABEL";
    negLabelField.set(identifier, expectedValue);
    String actualValue = identifier.getNegLabel();
    assertEquals(expectedValue, actualValue);
}

@Test
public void test4()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "Barack");
    token1.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Obama");
    token2.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "visited");
    token3.set(NamedEntityTagAnnotation.class, "O");
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3);
    List<CoreMap> chunks = identifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, NamedEntityTagAnnotation.class);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    assertEquals("Barack Obama", chunk.get(TextAnnotation.class));
}

@Test
public void test5()
{
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "Barack");
    token1.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "Obama");
    token2.set(NamedEntityTagAnnotation.class, "PERSON");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "visited");
    token3.set(NamedEntityTagAnnotation.class, "O");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, NamedEntityTagAnnotation.class);
    assertNotNull(chunks);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    List<CoreLabel> chunkTokens = chunk.get(TokensAnnotation.class);
    assertEquals(2, chunkTokens.size());
    assertEquals("Barack", chunkTokens.get(0).get(TextAnnotation.class));
    assertEquals("Obama", chunkTokens.get(1).get(TextAnnotation.class));
}

@Test
public void test6()
{
    LabeledChunkIdentifier chunkIdentifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "New");
    token1.set(AnswerAnnotation.class, "B-LOC");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "York");
    token2.set(AnswerAnnotation.class, "I-LOC");
    CoreLabel token3 = new CoreLabel();
    token3.set(TextAnnotation.class, "is");
    token3.set(AnswerAnnotation.class, "O");
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    List<CoreMap> chunks = chunkIdentifier.getAnnotatedChunks(tokens, 0, TextAnnotation.class, AnswerAnnotation.class);
    assertNotNull(chunks);
    assertEquals(1, chunks.size());
    CoreMap chunk = chunks.get(0);
    assertEquals("New York", chunk.get(TextAnnotation.class));
}

@Test
public void test7()
{
    LabeledChunkIdentifier identifier = new LabeledChunkIdentifier();
    CoreLabel token1 = new CoreLabel();
    token1.set(TextAnnotation.class, "New");
    token1.set(NamedEntityTagAnnotation.class, "B-LOC");
    CoreLabel token2 = new CoreLabel();
    token2.set(TextAnnotation.class, "York");
    token2.set(NamedEntityTagAnnotation.class, "I-LOC");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    int offset = 0;
    Class textKey = TextAnnotation.class;
    Class labelKey = NamedEntityTagAnnotation.class;
    List<CoreMap> result = identifier.getAnnotatedChunks(tokens, offset, textKey, labelKey);
    assertEquals(1, result.size());
    CoreMap chunk = result.get(0);
    assertEquals("New York", chunk.get(TextAnnotation.class));
}

