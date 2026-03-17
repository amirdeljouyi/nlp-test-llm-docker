import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation expectedAnnotation = new Annotation("Sample text");
    CoreDocument coreDocument = new CoreDocument(expectedAnnotation);
    Annotation actualAnnotation = coreDocument.annotation();
    assertSame("The annotation() method should return the same Annotation instance provided to CoreDocument", expectedAnnotation, actualAnnotation);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Sample text for testing.");
    String expectedDocID = "doc-12345";
    annotation.set(DocIDAnnotation.class, expectedDocID);
    CoreDocument coreDocument = new CoreDocument(annotation);
    String actualDocID = coreDocument.docID();
    assertEquals(expectedDocID, actualDocID);
}

@Test
public void test3()
{
    CoreEntityMention entity1 = new CoreEntityMention();
    CoreEntityMention entity2 = new CoreEntityMention();
    CoreDocument document = new CoreDocument("Test document");
    Field entityMentionsField = CoreDocument.class.getDeclaredField("entityMentions");
    entityMentionsField.setAccessible(true);
    List<CoreEntityMention> expectedMentions = Arrays.asList(entity1, entity2);
    entityMentionsField.set(document, expectedMentions);
    List<CoreEntityMention> actualMentions = document.entityMentions();
    assertNotNull(actualMentions);
    assertEquals(2, actualMentions.size());
    assertSame(entity1, actualMentions.get(0));
    assertSame(entity2, actualMentions.get(1));
}

@Test
public void test4()
{
    CoreQuote quote1 = new CoreQuote();
    CoreQuote quote2 = new CoreQuote();
    CoreDocument document = new CoreDocument("Test text.");
    List<CoreQuote> predefinedQuotes = Arrays.asList(quote1, quote2);
    try {
        Field quotesField = CoreDocument.class.getDeclaredField("quotes");
        quotesField.setAccessible(true);
        quotesField.set(document, predefinedQuotes);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set quotes field via reflection: " + e.getMessage());
    }
    List<CoreQuote> resultQuotes = document.quotes();
    assertEquals(2, resultQuotes.size());
    assertSame(quote1, resultQuotes.get(0));
    assertSame(quote2, resultQuotes.get(1));
}

@Test
public void test5()
{
    CoreSentence sentence1 = new CoreSentence();
    CoreSentence sentence2 = new CoreSentence();
    List<CoreSentence> expectedSentences = Arrays.asList(sentence1, sentence2);
    CoreDocument document = new CoreDocument("This is a test.");
    document.sentences = expectedSentences;
    List<CoreSentence> actualSentences = document.sentences();
    assertNotNull(actualSentences);
    assertEquals(2, actualSentences.size());
    assertSame(sentence1, actualSentences.get(0));
    assertSame(sentence2, actualSentences.get(1));
}

@Test
public void test6()
{
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("World");
    List<CoreLabel> mockTokenList = Arrays.asList(token1, token2);
    Annotation mockAnnotationDoc = mock(Annotation.class);
    when(mockAnnotationDoc.get(TokensAnnotation.class)).thenReturn(mockTokenList);
    CoreDocument coreDocument = new CoreDocument(mockAnnotationDoc);
    List<CoreLabel> resultTokens = coreDocument.tokens();
    assertNotNull(resultTokens);
    assertEquals(2, resultTokens.size());
    assertEquals("Hello", resultTokens.get(0).word());
    assertEquals("World", resultTokens.get(1).word());
}

@Test
public void test7()
{
    CoreMap annotationDocument = new ArrayCoreMap();
    Map<Integer, CorefChain> corefChainMap = new HashMap<>();
    CorefChain.CorefMention mention = new CorefChain.CorefMention(0, 0, 0, 0, "John", false);
    CorefChain chain = new CorefChain(0, Collections.singletonList(mention));
    corefChainMap.put(1, chain);
    annotationDocument.set(CorefChainAnnotation.class, corefChainMap);
    CoreDocument coreDocument = new CoreDocument("John went to the store.");
    coreDocument.annotationDocument = annotationDocument;
    Map<Integer, CorefChain> result = coreDocument.corefChains();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.containsKey(1));
    assertEquals(chain, result.get(1));
}

@Test
public void test8()
{
    CoreMap mockSentence = mock(CoreMap.class);
    when(mockSentence.get(TokensAnnotation.class)).thenReturn(Collections.emptyList());
    when(mockSentence.get(SentencesAnnotation.class)).thenReturn(null);
    when(mockSentence.get(CharacterOffsetBeginAnnotation.class)).thenReturn(0);
    when(mockSentence.get(CharacterOffsetEndAnnotation.class)).thenReturn(1);
    when(mockSentence.get(TextAnnotation.class)).thenReturn("Test sentence");
    when(mockSentence.get(TokenBeginAnnotation.class)).thenReturn(0);
    when(mockSentence.get(TokenEndAnnotation.class)).thenReturn(1);
    List<EntityMention> entityMentionsList = Arrays.asList(new EntityMention());
    when(mockSentence.get(EntityMentionsAnnotation.class)).thenReturn(entityMentionsList);
    List<CoreMap> sentenceCoreMaps = Collections.singletonList(mockSentence);
    Annotation annotation = new Annotation("This is a test.");
    annotation.set(SentencesAnnotation.class, sentenceCoreMaps);
    List<CoreMap> mockQuotes = Collections.singletonList(mock(CoreMap.class));
    mockStatic(QuoteAnnotator.class);
    when(QuoteAnnotator.gatherQuotes(annotation)).thenReturn(mockQuotes);
    CoreDocument doc = new CoreDocument(annotation);
    doc.wrapAnnotations();
    assertNotNull(doc.sentences());
    assertEquals(1, doc.sentences().size());
    assertNotNull(doc.sentences().get(0).entityMentions());
    assertEquals(entityMentionsList, doc.sentences().get(0).entityMentions());
    assertNotNull(doc.entityMentions());
    assertEquals(entityMentionsList, doc.entityMentions());
    assertNotNull(doc.quotes());
    assertEquals(mockQuotes, doc.quotes());
}

