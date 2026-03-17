import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String text = "Stanford NLP provides natural language processing tools.";
    Annotation expectedAnnotation = new Annotation(text);
    CoreDocument coreDocument = new CoreDocument(expectedAnnotation);
    Annotation actualAnnotation = coreDocument.annotation();
    Assert.assertSame("The annotation() method should return the underlying annotation instance", expectedAnnotation, actualAnnotation);
}

@Test
public void test1()
{
    Annotation expectedAnnotation = new Annotation("This is a test.");
    CoreDocument coreDocument = new CoreDocument(expectedAnnotation);
    Annotation actualAnnotation = coreDocument.annotation();
    assertSame("The annotation() method should return the original Annotation instance", expectedAnnotation, actualAnnotation);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Sample text");
    String expectedDate = "2024-05-01";
    annotation.set(DocDateAnnotation.class, expectedDate);
    CoreDocument coreDocument = new CoreDocument(annotation);
    assertEquals(expectedDate, coreDocument.docDate());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("");
    annotation.set(DocIDAnnotation.class, "doc123");
    CoreDocument coreDocument = new CoreDocument(annotation);
    assertEquals("doc123", coreDocument.docID());
}

@Test
public void test4()
{
    List<CoreEntityMention> mockMentions = new ArrayList<>();
    CoreLabel token = new CoreLabel();
    token.setWord("Stanford");
    CoreEntityMention mention = new CoreEntityMention(token, "ORGANIZATION");
    mockMentions.add(mention);
    CoreDocument document = new CoreDocument("");
    document.entityMentions = mockMentions;
    List<CoreEntityMention> result = document.entityMentions();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Stanford", result.get(0).text());
    assertEquals("ORGANIZATION", result.get(0).entityType());
}

@Test
public void test5()
{
    CoreDocument coreDocument = new CoreDocument("He said, \"Hello there.\"");
    List<CoreQuote> expectedQuotes = new ArrayList<>();
    CoreQuote quote = new CoreQuote();
    expectedQuotes.add(quote);
    coreDocument.quotes = expectedQuotes;
    List<CoreQuote> actualQuotes = coreDocument.quotes();
    assertSame("The quotes list returned should be the same as the one assigned", expectedQuotes, actualQuotes);
}

@Test
public void test6()
{
    CoreSentence sentence1 = new CoreSentence();
    CoreSentence sentence2 = new CoreSentence();
    List<CoreSentence> expectedSentences = Arrays.asList(sentence1, sentence2);
    CoreDocument document = new CoreDocument("");
    document.sentences = expectedSentences;
    List<CoreSentence> actualSentences = document.sentences();
    assertEquals(expectedSentences, actualSentences);
}

@Test
public void test7()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    List<CoreLabel> expectedTokens = new ArrayList<>();
    expectedTokens.add(token1);
    expectedTokens.add(token2);
    TypesafeMap annotationMap = mock(TypesafeMap.class);
    when(annotationMap.get(TokensAnnotation.class)).thenReturn(expectedTokens);
    CoreDocument doc = new CoreDocument("Dummy text") {
        {
            this.annotationDocument = annotationMap;
        }
    };
    List<CoreLabel> actualTokens = doc.tokens();
    assertNotNull(actualTokens);
    assertEquals(2, actualTokens.size());
    assertEquals("Hello", actualTokens.get(0).word());
    assertEquals("world", actualTokens.get(1).word());
}

@Test
public void test8()
{
    Annotation annotation = new Annotation("");
    Map<Integer, CorefChain> expectedCorefChains = new HashMap<>();
    Integer chainId = 1;
    CorefChain mockChain = new CorefChain(chainId, new HashMap<>());
    expectedCorefChains.put(chainId, mockChain);
    annotation.set(CorefChainAnnotation.class, expectedCorefChains);
    CoreDocument coreDocument = new CoreDocument(annotation);
    Map<Integer, CorefChain> actualCorefChains = coreDocument.corefChains();
    assertNotNull(actualCorefChains);
    assertEquals(1, actualCorefChains.size());
    assertTrue(actualCorefChains.containsKey(chainId));
    assertSame(mockChain, actualCorefChains.get(chainId));
}

@Test
public void test9()
{
    Annotation annotation = new Annotation("Some text.");
    List<CoreMap> sentenceAnnotations = new ArrayList<>();
    CoreMap sentence = new CoreMap() {};
    sentenceAnnotations.add(sentence);
    annotation.set(SentencesAnnotation.class, sentenceAnnotations);
    CoreDocument doc = new CoreDocument("Test document");
    doc.annotationDocument = annotation;
    CoreSentence coreSentence = new CoreSentence(sentence);
    coreSentence.entityMentions = Arrays.asList(new Object());
    doc.sentences = Arrays.asList(coreSentence);
    QuoteAnnotator qa = new QuoteAnnotator();
    annotation.set(QuotesAnnotation.class, Arrays.asList(new Object()));
    doc.wrapAnnotations();
    assertNotNull(doc.sentences);
}

