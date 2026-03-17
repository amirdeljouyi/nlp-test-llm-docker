import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    String inputText = "He said, \"This is a quote.\"";
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.getQuotes(inputText);
    assertNotNull(result);
    assertNotNull(result.first());
    assertEquals(1, result.first().size());
    Pair<Integer, Integer> quoteSpan = result.first().get(0);
    assertNotNull(quoteSpan);
    assertEquals(((Integer) (9)), quoteSpan.first());
    assertEquals(((Integer) (29)), quoteSpan.second());
    assertNotNull(result.second());
    assertTrue(result.second().isEmpty());
}

@Test
public void test2()
{
    String text = "He said, \"She said, \'Hello.\'\"";
    int offset = 0;
    String prevQuote = null;
    QuoteAnnotator annotator = new QuoteAnnotator();
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.recursiveQuotes(text, offset, prevQuote);
    List<Pair<Integer, Integer>> quotes = result.first();
    List<Pair<Integer, Integer>> unclosedQuotes = result.second();
    assertEquals(2, quotes.size());
    assertTrue(quotes.contains(new Pair<>(9, 29)));
    assertTrue(quotes.contains(new Pair<>(20, 28)));
    assertTrue(unclosedQuotes.isEmpty());
}

@Test
public void test3()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    Field field = QuoteAnnotator.class.getDeclaredField("ATTRIBUTE_QUOTES");
    field.setAccessible(true);
    field.set(null, false);
    Set<Class<? extends CoreAnnotation>> expected = Collections.singleton(QuotationsAnnotation.class);
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test4()
{
    String surfaceForm = "This is a quote.";
    int begin = 10;
    int end = 28;
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("quote.");
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    int tokenOffset = 5;
    int sentenceBeginIndex = 1;
    int sentenceEndIndex = 2;
    String docID = "doc-123";
    Annotation quote = QuoteAnnotator.makeQuote(surfaceForm, begin, end, tokens, tokenOffset, sentenceBeginIndex, sentenceEndIndex, docID);
    Assert.assertEquals(surfaceForm, quote.get(TextAnnotation.class));
    Assert.assertEquals(Integer.valueOf(begin), quote.get(CharacterOffsetBeginAnnotation.class));
    Assert.assertEquals(Integer.valueOf(end), quote.get(CharacterOffsetEndAnnotation.class));
    Assert.assertEquals(docID, quote.get(DocIDAnnotation.class));
    Assert.assertEquals(tokens, quote.get(TokensAnnotation.class));
    Assert.assertEquals(Integer.valueOf(tokenOffset), quote.get(TokenBeginAnnotation.class));
    Assert.assertEquals(Integer.valueOf((tokenOffset + tokens.size()) - 1), quote.get(TokenEndAnnotation.class));
    Assert.assertEquals(Integer.valueOf(sentenceBeginIndex), quote.get(SentenceBeginAnnotation.class));
    Assert.assertEquals(Integer.valueOf(sentenceEndIndex), quote.get(SentenceEndAnnotation.class));
}

@Test
public void test5()
{
    String input = "“This is a ‘test’ of unicode quotes”";
    String expected = "\"This is a \'test\' of unicode quotes\"";
    String actual = QuoteAnnotator.replaceUnicode(input);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    String documentText = "<xml><tag>Hello</tag>, <b>world</b>!</xml>";
    CoreLabel token1 = new CoreLabel();
    token1.set(OriginalTextAnnotation.class, "Hello");
    token1.set(CharacterOffsetBeginAnnotation.class, 11);
    token1.set(CharacterOffsetEndAnnotation.class, 16);
    CoreLabel token2 = new CoreLabel();
    token2.set(OriginalTextAnnotation.class, ",");
    token2.set(CharacterOffsetBeginAnnotation.class, 16);
    token2.set(CharacterOffsetEndAnnotation.class, 17);
    CoreLabel token3 = new CoreLabel();
    token3.set(OriginalTextAnnotation.class, "world");
    token3.set(CharacterOffsetBeginAnnotation.class, 22);
    token3.set(CharacterOffsetEndAnnotation.class, 27);
    CoreLabel token4 = new CoreLabel();
    token4.set(OriginalTextAnnotation.class, "!");
    token4.set(CharacterOffsetBeginAnnotation.class, 32);
    token4.set(CharacterOffsetEndAnnotation.class, 33);
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3, token4);
    Annotation annotation = new Annotation(documentText);
    annotation.set(TokensAnnotation.class, tokens);
    String result = QuoteAnnotator.xmlFreeText(documentText, annotation);
    String expected = "          Hello world ";
    expected = ((((((documentText.substring(0, 11).replaceAll("\\S", " ") + "Hello") + ",".replaceAll("\\S", " ")) + documentText.substring(17, 22).replaceAll("\\S", " ")) + "world") + documentText.substring(27, 32).replaceAll("\\S", " ")) + "!") + documentText.substring(33).replaceAll("\\S", " ");
    assertEquals(expected, result);
}

@Test
public void test7()
{
    CoreMap quote1 = mock(CoreMap.class);
    CoreMap quote2 = mock(CoreMap.class);
    when(quote1.get(CharacterOffsetBeginAnnotation.class)).thenReturn(5);
    when(quote2.get(CharacterOffsetBeginAnnotation.class)).thenReturn(10);
    Comparator<CoreMap> comparator = QuoteAnnotator.getQuoteComparator();
    int result = comparator.compare(quote1, quote2);
    assertTrue("Expected quote1 to come before quote2", result < 0);
}

@Test
public void test8()
{
    CoreMap innerMostQuote = new ArrayCoreMap();
    innerMostQuote.set(QuotationsAnnotation.class, Collections.emptyList());
    innerMostQuote.set(CharacterOffsetBeginAnnotation.class, 30);
    CoreMap nestedQuote = new ArrayCoreMap();
    nestedQuote.set(QuotationsAnnotation.class, Collections.singletonList(innerMostQuote));
    nestedQuote.set(CharacterOffsetBeginAnnotation.class, 20);
    CoreMap topQuote = new ArrayCoreMap();
    topQuote.set(QuotationsAnnotation.class, Collections.singletonList(nestedQuote));
    topQuote.set(CharacterOffsetBeginAnnotation.class, 10);
    List<CoreMap> result = QuoteAnnotator.gatherQuotes(topQuote);
    assertEquals(3, result.size());
    assertSame(innerMostQuote, result.get(0));
    assertSame(nestedQuote, result.get(1));
    assertSame(topQuote.get(QuotationsAnnotation.class).get(0), result.get(2));
}

@Test
public void test9()
{
    String text = "He said, \"Hello world.\" Then he left.";
    String docID = "doc1";
    boolean unclosed = false;
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token0 = new CoreLabel();
    token0.setWord("He");
    token0.setBeginPosition(0);
    token0.setEndPosition(2);
    tokens.add(token0);
    CoreLabel token1 = new CoreLabel();
    token1.setWord("said");
    token1.setBeginPosition(3);
    token1.setEndPosition(7);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord(",");
    token2.setBeginPosition(7);
    token2.setEndPosition(8);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("\"");
    token3.setBeginPosition(9);
    token3.setEndPosition(10);
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("Hello");
    token4.setBeginPosition(10);
    token4.setEndPosition(15);
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("world");
    token5.setBeginPosition(16);
    token5.setEndPosition(21);
    tokens.add(token5);
    CoreLabel token6 = new CoreLabel();
    token6.setWord(".");
    token6.setBeginPosition(21);
    token6.setEndPosition(22);
    tokens.add(token6);
    CoreLabel token7 = new CoreLabel();
    token7.setWord("\"");
    token7.setBeginPosition(22);
    token7.setEndPosition(23);
    tokens.add(token7);
    CoreMap sentence = new ArrayCoreMap();
    sentence.set(CharacterOffsetBeginAnnotation.class, 0);
    sentence.set(CharacterOffsetEndAnnotation.class, 23);
    sentence.set(SentenceIndexAnnotation.class, 0);
    List<CoreMap> sentences = Arrays.asList(sentence);
    List<Pair<Integer, Integer>> quotes = Arrays.asList(new Pair<>(10, 22));
    List<CoreMap> result = QuoteAnnotator.getCoreMapQuotes(quotes, tokens, sentences, text, docID, unclosed);
    assertEquals(1, result.size());
    CoreMap quote = result.get(0);
    assertEquals("Hello world.", quote.get(TextAnnotation.class));
    assertEquals(((Integer) (10)), quote.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(((Integer) (22)), quote.get(CharacterOffsetEndAnnotation.class));
    assertEquals("doc1", quote.get(DocIDAnnotation.class));
    @SuppressWarnings("unchecked")
    List<CoreLabel> quoteTokens = quote.get(TokensAnnotation.class);
    assertEquals(3, quoteTokens.size());
    assertEquals("Hello", quoteTokens.get(0).word());
    assertEquals("world", quoteTokens.get(1).word());
    assertEquals(".", quoteTokens.get(2).word());
    List<CoreMap> embedded = quote.get(QuotationsAnnotation.class);
    assertNotNull(embedded);
    assertTrue(embedded.isEmpty());
}

@Test
public void test10()
{
    Annotation annotation = new Annotation("She said, “Hello there.”");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("She");
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("said");
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(",");
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("“");
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("Hello");
    tokens.add(token5);
    CoreLabel token6 = new CoreLabel();
    token6.setWord("there");
    tokens.add(token6);
    CoreLabel token7 = new CoreLabel();
    token7.setWord(".");
    tokens.add(token7);
    CoreLabel token8 = new CoreLabel();
    token8.setWord("”");
    tokens.add(token8);
    annotation.set(TextAnnotation.class, "She said, “Hello there.”");
    annotation.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = new ArrayList<>();
    CoreMap sentence = new Annotation("She said, “Hello there.”");
    sentence.set(TokensAnnotation.class, tokens);
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    annotation.set(DocIDAnnotation.class, "doc1");
    QuoteAnnotator annotator = new QuoteAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> quotes = annotation.get(QuotationsAnnotation.class);
    assertNotNull("Quotes should not be null", quotes);
    assertFalse("There should be at least one quote", quotes.isEmpty());
}

