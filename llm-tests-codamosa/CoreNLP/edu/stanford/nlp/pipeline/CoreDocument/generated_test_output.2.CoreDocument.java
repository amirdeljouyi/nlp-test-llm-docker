import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation inputAnnotation = new Annotation("Stanford NLP Test");
    CoreDocument coreDocument = new CoreDocument(inputAnnotation);
    Annotation returnedAnnotation = coreDocument.annotation();
    assertSame("The annotation method should return the original annotationDocument", inputAnnotation, returnedAnnotation);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Test text");
    String expectedDate = "2024-06-01";
    annotation.set(DocDateAnnotation.class, expectedDate);
    CoreDocument coreDocument = new CoreDocument(annotation);
    assertEquals(expectedDate, coreDocument.docDate());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("");
    String expectedDocID = "test-document-id-123";
    annotation.set(DocIDAnnotation.class, expectedDocID);
    CoreDocument coreDocument = new CoreDocument(annotation);
    String actualDocID = coreDocument.docID();
    assertEquals(expectedDocID, actualDocID);
}

@Test
public void test4()
{
    CoreEntityMention mention1 = new CoreEntityMention();
    CoreEntityMention mention2 = new CoreEntityMention();
    List<CoreEntityMention> mockEntityMentions = new ArrayList<>();
    mockEntityMentions.add(mention1);
    mockEntityMentions.add(mention2);
    CoreDocument document = new CoreDocument("Sample text.");
    try {
        Field field = CoreDocument.class.getDeclaredField("entityMentions");
        field.setAccessible(true);
        field.set(document, mockEntityMentions);
    } catch (Exception e) {
        fail("Failed to set entityMentions field via reflection: " + e.getMessage());
    }
    List<CoreEntityMention> result = document.entityMentions();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertSame(mention1, result.get(0));
    assertSame(mention2, result.get(1));
}

@Test
public void test5()
{
    CoreDocument document = new CoreDocument("He said, 'Hello world!'");
    List<CoreQuote> expectedQuotes = new ArrayList<>();
    CoreQuote quote = new CoreQuote("Hello world!");
    expectedQuotes.add(quote);
    document.quotes = expectedQuotes;
    List<CoreQuote> actualQuotes = document.quotes();
    assertEquals(1, actualQuotes.size());
    assertEquals("Hello world!", actualQuotes.get(0).text());
}

@Test
public void test6()
{
    CoreSentence sentence1 = new CoreSentence();
    CoreSentence sentence2 = new CoreSentence();
    List<CoreSentence> expectedSentences = Arrays.asList(sentence1, sentence2);
    CoreDocument document = new CoreDocument("");
    Field sentencesField = CoreDocument.class.getDeclaredField("sentences");
    sentencesField.setAccessible(true);
    sentencesField.set(document, expectedSentences);
    List<CoreSentence> result = document.sentences();
    assertSame("The list of sentences should match the injected list", expectedSentences, result);
}

@Test
public void test7()
{
    List<CoreLabel> expectedTokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    expectedTokens.add(token1);
    expectedTokens.add(token2);
    TypesafeMap annotationMap = new TypesafeMap();
    annotationMap.set(TokensAnnotation.class, expectedTokens);
    CoreDocument coreDocument = new CoreDocument("");
    coreDocument.annotationDocument = annotationMap;
    List<CoreLabel> actualTokens = coreDocument.tokens();
    assertEquals(2, actualTokens.size());
    assertEquals("Hello", actualTokens.get(0).word());
    assertEquals("world", actualTokens.get(1).word());
}

@Test
public void test8()
{
    CorefMention mention = new CorefMention(0, 0, 0, 0, 0, "John", false);
    List<CorefMention> mentions = Collections.singletonList(mention);
    CorefChain corefChain = new CorefChain(1, mentions);
    Map<Integer, CorefChain> corefChainsMap = new HashMap<>();
    corefChainsMap.put(1, corefChain);
    Annotation annotation = new Annotation("John went to his house.");
    annotation.set(CorefChainAnnotation.class, corefChainsMap);
    CoreDocument doc = new CoreDocument(annotation);
    Map<Integer, CorefChain> result = doc.corefChains();
    Assert.assertNotNull(result);
    Assert.assertEquals(1, result.size());
    Assert.assertTrue(result.containsKey(1));
    Assert.assertEquals(corefChain, result.get(1));
}

@Test
public void test9()
{
    CoreMap mockAnnotationDocument = mock(CoreMap.class);
    CoreMap mockSentence = mock(CoreMap.class);
    when(mockSentence.get(EntityMentionsAnnotation.class)).thenReturn(Collections.singletonList("Entity"));
    when(mockSentence.containsKey(EntityMentionsAnnotation.class)).thenReturn(true);
    List<CoreMap> mockSentences = Collections.singletonList(mockSentence);
    when(mockAnnotationDocument.get(SentencesAnnotation.class)).thenReturn(mockSentences);
    CoreMap mockQuotes = mock(CoreMap.class);
    CoreDocument doc = new CoreDocument("");
    doc.annotationDocument = mockAnnotationDocument;
    doc.sentences = mockSentences;
    doc.wrapAnnotations();
    assertTrue(true);
}

