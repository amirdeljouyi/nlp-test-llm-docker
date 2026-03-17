import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    String inputText = "He said, \"I heard her say, \'I\'ll be back soon.\'\"";
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.getQuotes(inputText);
    List<Pair<Integer, Integer>> outerQuotes = result.first;
    List<Pair<Integer, Integer>> innerQuotes = result.second;
    assertEquals(1, outerQuotes.size());
    assertEquals(Integer.valueOf(9), outerQuotes.get(0).first);
    assertEquals(Integer.valueOf(48), outerQuotes.get(0).second);
    assertEquals(1, innerQuotes.size());
    assertEquals(Integer.valueOf(26), innerQuotes.get(0).first);
    assertEquals(Integer.valueOf(45), innerQuotes.get(0).second);
}

@Test
public void test2()
{
    RedwoodConfiguration.empty().capture(System.err).apply();
    String text = "He said, \"Hello world.\"";
    int offset = 0;
    String prevQuote = null;
    QuoteAnnotator annotator = new QuoteAnnotator();
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.recursiveQuotes(text, offset, prevQuote);
    List<Pair<Integer, Integer>> quotes = result.first();
    List<Pair<Integer, Integer>> unclosedQuotes = result.second();
    assertEquals(1, quotes.size());
    assertEquals(Integer.valueOf(9), quotes.get(0).first());
    assertEquals(Integer.valueOf(23), quotes.get(0).second());
    assertTrue(unclosedQuotes.isEmpty());
}

@Test
public void test3()
{
    Field field = QuoteAnnotator.class.getDeclaredField("ATTRIBUTE_QUOTES");
    field.setAccessible(true);
    field.setBoolean(null, false);
    QuoteAnnotator quoteAnnotator = new QuoteAnnotator();
    Set<Class<? extends CoreAnnotation>> result = quoteAnnotator.requirementsSatisfied();
    Set<Class<? extends CoreAnnotation>> expected = Collections.singleton(QuotationsAnnotation.class);
    assertEquals(expected, result);
}

@Test
public void test4()
{
    String input = "'";
    boolean result = QuoteAnnotator.isSingleQuote(input);
    assertTrue(result);
}

@Test
public void test5()
{
    String surfaceForm = "\"Hello world\"";
    int begin = 5;
    int end = 18;
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.set(CharacterOffsetBeginAnnotation.class, 6);
    token1.set(CharacterOffsetEndAnnotation.class, 11);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    token2.set(CharacterOffsetBeginAnnotation.class, 12);
    token2.set(CharacterOffsetEndAnnotation.class, 17);
    List<CoreLabel> quoteTokens = Arrays.asList(token1, token2);
    int tokenOffset = 3;
    int sentenceBeginIndex = 0;
    int sentenceEndIndex = 1;
    String docID = "doc123";
    Annotation result = QuoteAnnotator.makeQuote(surfaceForm, begin, end, quoteTokens, tokenOffset, sentenceBeginIndex, sentenceEndIndex, docID);
    assertEquals(surfaceForm, result.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(begin), result.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(Integer.valueOf(end), result.get(CharacterOffsetEndAnnotation.class));
    assertEquals(docID, result.get(DocIDAnnotation.class));
    assertEquals(quoteTokens, result.get(TokensAnnotation.class));
    assertEquals(Integer.valueOf(tokenOffset), result.get(TokenBeginAnnotation.class));
    assertEquals(Integer.valueOf((tokenOffset + quoteTokens.size()) - 1), result.get(TokenEndAnnotation.class));
    assertEquals(Integer.valueOf(sentenceBeginIndex), result.get(SentenceBeginAnnotation.class));
    assertEquals(Integer.valueOf(sentenceEndIndex), result.get(SentenceEndAnnotation.class));
}

@Test
public void test6()
{
    String input = "“Hello”, she said. ‘Yes,’ he replied.";
    String expected = "\"Hello\", she said. \'Yes,\' he replied.";
    String actual = QuoteAnnotator.replaceUnicode(input);
    assertEquals(expected, actual);
}

@Test
public void test7()
{
    String documentText = "<quote>Stanford NLP</quote> is great.";
    CoreLabel token1 = new CoreLabel();
    token1.set(OriginalTextAnnotation.class, "Stanford");
    token1.set(CharacterOffsetBeginAnnotation.class, 8);
    token1.set(CharacterOffsetEndAnnotation.class, 16);
    CoreLabel token2 = new CoreLabel();
    token2.set(OriginalTextAnnotation.class, "NLP");
    token2.set(CharacterOffsetBeginAnnotation.class, 17);
    token2.set(CharacterOffsetEndAnnotation.class, 20);
    CoreLabel token3 = new CoreLabel();
    token3.set(OriginalTextAnnotation.class, "is");
    token3.set(CharacterOffsetBeginAnnotation.class, 22);
    token3.set(CharacterOffsetEndAnnotation.class, 24);
    CoreLabel token4 = new CoreLabel();
    token4.set(OriginalTextAnnotation.class, "great");
    token4.set(CharacterOffsetBeginAnnotation.class, 25);
    token4.set(CharacterOffsetEndAnnotation.class, 30);
    CoreLabel token5 = new CoreLabel();
    token5.set(OriginalTextAnnotation.class, ".");
    token5.set(CharacterOffsetBeginAnnotation.class, 30);
    token5.set(CharacterOffsetEndAnnotation.class, 31);
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    tokens.add(token5);
    Annotation annotation = new Annotation(documentText);
    annotation.set(TokensAnnotation.class, tokens);
    String result = QuoteAnnotator.xmlFreeText(documentText, annotation);
    assertEquals("        Stanford NLP is great.", result);
}

@Test
public void test8()
{
    CoreMap quote1 = mock(CoreMap.class);
    CoreMap quote2 = mock(CoreMap.class);
    when(quote1.get(CharacterOffsetBeginAnnotation.class)).thenReturn(10);
    when(quote2.get(CharacterOffsetBeginAnnotation.class)).thenReturn(20);
    Comparator<CoreMap> comparator = QuoteAnnotator.getQuoteComparator();
    int result = comparator.compare(quote1, quote2);
    assertTrue("Expected quote1 to come before quote2", result < 0);
    verify(quote1).get(CharacterOffsetBeginAnnotation.class);
    verify(quote2).get(CharacterOffsetBeginAnnotation.class);
}

@Test
public void test9()
{
    CoreMap nestedQuote1 = mock(CoreMap.class);
    CoreMap nestedQuote2 = mock(CoreMap.class);
    CoreMap topQuote = mock(CoreMap.class);
    List<CoreMap> nestedLevel1 = Arrays.asList(nestedQuote1, nestedQuote2);
    when(nestedQuote1.get(QuotationsAnnotation.class)).thenReturn(null);
    when(nestedQuote2.get(QuotationsAnnotation.class)).thenReturn(null);
    when(nestedQuote1.get(any())).thenReturn(5);
    when(nestedQuote2.get(any())).thenReturn(10);
    when(topQuote.get(QuotationsAnnotation.class)).thenReturn(nestedLevel1);
    when(topQuote.get(any())).thenReturn(1);
    List<CoreMap> result = QuoteAnnotator.gatherQuotes(topQuote);
    assertEquals(2, result.size());
    assertTrue(result.contains(nestedQuote1));
    assertTrue(result.contains(nestedQuote2));
}

@Test
public void test10()
{
    String text = "She said, \"Hello world.\" Then she left.";
    Pair<Integer, Integer> quoteSpan = new Pair<>(10, 24);
    List<Pair<Integer, Integer>> quotes = Arrays.asList(quoteSpan);
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    token1.setBeginPosition(11);
    token1.setEndPosition(16);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("world");
    token2.setBeginPosition(17);
    token2.setEndPosition(22);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(".");
    token3.setBeginPosition(22);
    token3.setEndPosition(23);
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3);
    CoreMap sentence1 = new ArrayCoreMap();
    sentence1.set(CharacterOffsetBeginAnnotation.class, 0);
    sentence1.set(CharacterOffsetEndAnnotation.class, 25);
    sentence1.set(SentenceIndexAnnotation.class, 0);
    CoreMap sentence2 = new ArrayCoreMap();
    sentence2.set(CharacterOffsetBeginAnnotation.class, 26);
    sentence2.set(CharacterOffsetEndAnnotation.class, 42);
    sentence2.set(SentenceIndexAnnotation.class, 1);
    List<CoreMap> sentences = Arrays.asList(sentence1, sentence2);
    String docID = "doc1";
    boolean unclosed = false;
    List<CoreMap> result = QuoteAnnotator.getCoreMapQuotes(quotes, tokens, sentences, text, docID, unclosed);
    assertEquals(1, result.size());
    CoreMap quote = result.get(0);
    assertEquals("Hello world.", quote.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(10), quote.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(Integer.valueOf(24), quote.get(CharacterOffsetEndAnnotation.class));
    assertEquals(tokens, quote.get(TokensAnnotation.class));
    assertEquals(0, ((int) (quote.get(SentenceBeginAnnotation.class))));
    assertEquals(0, ((int) (quote.get(SentenceEndAnnotation.class))));
    assertTrue((quote.get(QuotationsAnnotation.class) == null) || ((List<?>) (quote.get(QuotationsAnnotation.class))).isEmpty());
}

@Test
public void test11()
{
    Annotation annotation = new Annotation("\"This is a quote.\"");
    annotation.set(TextAnnotation.class, "\"This is a quote.\"");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("\"");
    token1.setIndex(1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("This");
    token2.setIndex(2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("is");
    token3.setIndex(3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("a");
    token4.setIndex(4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("quote");
    token5.setIndex(5);
    CoreLabel token6 = new CoreLabel();
    token6.setWord(".");
    token6.setIndex(6);
    CoreLabel token7 = new CoreLabel();
    token7.setWord("\"");
    token7.setIndex(7);
    List<CoreLabel> tokens = new ArrayList<>();
    Collections.addAll(tokens, token1, token2, token3, token4, token5, token6, token7);
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap sentence = new Annotation("This is a quote.");
    sentence.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    annotation.set(DocIDAnnotation.class, "doc1");
    QuoteAnnotator annotator = new QuoteAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> quotes = annotation.get(QuotationsAnnotation.class);
    assertNotNull("QuotationsAnnotation should not be null", quotes);
    assertFalse("There should be at least one quote detected", quotes.isEmpty());
}

