import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation expectedAnnotation = new Annotation("Sample text for testing");
    CoreDocument coreDocument = new CoreDocument(expectedAnnotation);
    Annotation actualAnnotation = coreDocument.annotation();
    assertSame("The annotation method should return the same Annotation instance used in construction", expectedAnnotation, actualAnnotation);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Sample text");
    annotation.set(DocDateAnnotation.class, "2023-12-25");
    CoreDocument document = new CoreDocument(annotation);
    assertEquals("2023-12-25", document.docDate());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("This is a test document.");
    annotation.set(DocIDAnnotation.class, "DOC123");
    CoreDocument coreDocument = new CoreDocument(annotation);
    String docId = coreDocument.docID();
    assertEquals("DOC123", docId);
}

@Test
public void test4()
{
    CoreEntityMention mention1 = new CoreEntityMention("Barack Obama", "PERSON");
    CoreEntityMention mention2 = new CoreEntityMention("United States", "LOCATION");
    List<CoreEntityMention> expectedMentions = Arrays.asList(mention1, mention2);
    CoreDocument document = new CoreDocument("Barack Obama was born in the United States.");
    document.entityMentions = expectedMentions;
    List<CoreEntityMention> actualMentions = document.entityMentions();
    assertEquals(expectedMentions, actualMentions);
}

@Test
public void test5()
{
    CoreDocument coreDocument = new CoreDocument("Example text with a quote.");
    List<CoreQuote> expectedQuotes = new ArrayList<>();
    CoreQuote quote1 = new CoreQuote();
    expectedQuotes.add(quote1);
    try {
        Field quotesField = CoreDocument.class.getDeclaredField("quotes");
        quotesField.setAccessible(true);
        quotesField.set(coreDocument, expectedQuotes);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set quotes field via reflection: " + e.getMessage());
    }
    List<CoreQuote> actualQuotes = coreDocument.quotes();
    assertEquals("Returned quote list should match the expected list", expectedQuotes, actualQuotes);
}

@Test
public void test6()
{
    CoreSentence sentence1 = mock(CoreSentence.class);
    CoreSentence sentence2 = mock(CoreSentence.class);
    List<CoreSentence> sentenceList = Arrays.asList(sentence1, sentence2);
    CoreDocument document = new CoreDocument("Dummy text");
    document.sentences = sentenceList;
    List<CoreSentence> result = document.sentences();
    assertEquals(2, result.size());
    assertSame(sentence1, result.get(0));
    assertSame(sentence2, result.get(1));
}

@Test
public void test7()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String text = "Hello world.";
    Annotation document = new Annotation(text);
    pipeline.annotate(document);
    CoreDocument coreDocument = new CoreDocument(document);
    List<CoreLabel> tokens = coreDocument.tokens();
    assertNotNull(tokens);
    assertEquals(3, tokens.size());
    assertEquals("Hello", tokens.get(0).originalText());
    assertEquals("world", tokens.get(1).originalText());
    assertEquals(".", tokens.get(2).originalText());
}

@Test
public void test8()
{
    CorefChain mockChain = mock(CorefChain.class);
    Map<Integer, CorefChain> expectedMap = new HashMap<>();
    expectedMap.put(1, mockChain);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.get(CorefChainAnnotation.class)).thenReturn(expectedMap);
    CoreDocument coreDoc = new CoreDocument(mockAnnotation);
    Map<Integer, CorefChain> actualMap = coreDoc.corefChains();
    assertNotNull(actualMap);
    assertEquals(1, actualMap.size());
    assertSame(mockChain, actualMap.get(1));
}

@Test
public void test9()
{
    CoreDocument document = new CoreDocument("");
    Annotation annotation = new Annotation("");
    List<CoreMap> sentenceList = new ArrayList<>();
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(EntityMentionsAnnotation.class, new ArrayList<>());
    sentenceList.add(sentence);
    annotation.set(SentencesAnnotation.class, sentenceList);
    document.setAnnotation(annotation);
    document.wrapAnnotations();
    assertNotNull(document.entityMentions());
}

