import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Annotation expectedAnnotation = new Annotation("Stanford CoreNLP test");
    CoreDocument coreDocument = new CoreDocument(expectedAnnotation);
    Annotation actualAnnotation = coreDocument.annotation();
    assertSame("The annotation() method should return the same Annotation instance passed to the CoreDocument constructor.", expectedAnnotation, actualAnnotation);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("Sample text");
    annotation.set(DocDateAnnotation.class, "2024-05-01");
    CoreDocument coreDocument = new CoreDocument(annotation);
    assertEquals("2024-05-01", coreDocument.docDate());
}

@Test
public void test3()
{
    Annotation annotation = new Annotation("Sample text");
    annotation.set(DocIDAnnotation.class, "doc-123");
    CoreDocument coreDocument = new CoreDocument(annotation);
    assertEquals("doc-123", coreDocument.docID());
}

@Test
public void test4()
{
    CoreEntityMention mention1 = new CoreEntityMention();
    CoreEntityMention mention2 = new CoreEntityMention();
    List<CoreEntityMention> expectedMentions = Arrays.asList(mention1, mention2);
    CoreDocument document = new CoreDocument("Sample text");
    document.entityMentions = expectedMentions;
    List<CoreEntityMention> actualMentions = document.entityMentions();
    assertEquals(expectedMentions, actualMentions);
}

@Test
public void test5()
{
    CoreDocument document = new CoreDocument("Sample text.");
    List<CoreQuote> expectedQuotes = new ArrayList<>();
    CoreQuote quote1 = new CoreQuote();
    CoreQuote quote2 = new CoreQuote();
    expectedQuotes.add(quote1);
    expectedQuotes.add(quote2);
    try {
        Field quotesField = CoreDocument.class.getDeclaredField("quotes");
        quotesField.setAccessible(true);
        quotesField.set(document, expectedQuotes);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Reflection failed: " + e.getMessage());
    }
    List<CoreQuote> result = document.quotes();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(quote1, result.get(0));
    assertEquals(quote2, result.get(1));
}

@Test
public void test6()
{
    CoreSentence sentence1 = new CoreSentence(null, null, null, null, null, null);
    CoreSentence sentence2 = new CoreSentence(null, null, null, null, null, null);
    List<CoreSentence> expectedSentences = Arrays.asList(sentence1, sentence2);
    CoreDocument document = new CoreDocument("dummy text");
    try {
        Field field = CoreDocument.class.getDeclaredField("sentences");
        field.setAccessible(true);
        field.set(document, expectedSentences);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set up test due to reflection error: " + e.getMessage());
    }
    List<CoreSentence> actualSentences = document.sentences();
    assertEquals(expectedSentences, actualSentences);
}

@Test
public void test7()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    CoreDocument document = new CoreDocument("Stanford NLP rocks.");
    pipeline.annotate(document);
    List<CoreLabel> tokens = document.tokens();
    assertEquals(4, tokens.size());
    assertEquals("Stanford", tokens.get(0).word());
    assertEquals("NLP", tokens.get(1).word());
    assertEquals("rocks", tokens.get(2).word());
    assertEquals(".", tokens.get(3).word());
}

@Test
public void test8()
{
    CoreDocument coreDocument = new CoreDocument("Alice went to the park. She saw a dog.");
    Map<Integer, CorefChain> expectedMap = new HashMap<>();
    @SuppressWarnings("unchecked")
    TypesafeMap.AnnotationMap map = new TypesafeMap.AnnotationMap();
    map.set(CorefChainAnnotation.class, expectedMap);
    try {
        Field annotationField = CoreDocument.class.getDeclaredField("annotationDocument");
        annotationField.setAccessible(true);
        annotationField.set(coreDocument, map);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set private annotationDocument field: " + e.getMessage());
    }
    Map<Integer, CorefChain> actualMap = coreDocument.corefChains();
    assertSame(expectedMap, actualMap);
}

@Test
public void test9()
{
    Annotation annotation = new Annotation("This is a test.");
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(TokensAnnotation.class, Collections.emptyList());
    sentence.set(MentionsAnnotation.class, null);
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    CoreDocument doc = new CoreDocument(annotation);
    doc.sentences().add(new CoreSentence(sentence));
    doc.wrapAnnotations();
    assertNotNull(doc.sentences());
    assertTrue(doc.sentences().size() == 1);
}

