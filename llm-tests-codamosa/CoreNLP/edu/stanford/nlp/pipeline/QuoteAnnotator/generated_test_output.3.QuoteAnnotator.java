import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    String inputText = "She said, \"Hello world!\" and walked away.";
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.getQuotes(inputText);
    List<Pair<Integer, Integer>> openQuotes = result.first;
    List<Pair<Integer, Integer>> closeQuotes = result.second;
    assertNotNull(openQuotes);
    assertNotNull(closeQuotes);
    assertEquals(1, openQuotes.size());
    assertEquals(1, closeQuotes.size());
    Pair<Integer, Integer> expectedOpen = new Pair<>(10, 11);
    Pair<Integer, Integer> expectedClose = new Pair<>(23, 24);
    assertEquals(expectedOpen, openQuotes.get(0));
    assertEquals(expectedClose, closeQuotes.get(0));
}

@Test
public void test2()
{
    String text = "He said, \"She replied, \'I agree.\'\"";
    QuoteAnnotator annotator = new QuoteAnnotator();
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.recursiveQuotes(text, 0, null);
    List<Pair<Integer, Integer>> quotes = result.first();
    List<Pair<Integer, Integer>> unclosedQuotes = result.second();
    assertEquals(2, quotes.size());
    Pair<Integer, Integer> outerQuote = quotes.get(0);
    Pair<Integer, Integer> innerQuote = quotes.get(1);
    assertEquals(Integer.valueOf(9), outerQuote.first());
    assertEquals(Integer.valueOf(33), outerQuote.second());
    assertEquals(Integer.valueOf(23), innerQuote.first());
    assertEquals(Integer.valueOf(32), innerQuote.second());
    assertTrue(unclosedQuotes.isEmpty());
}

@Test
public void test3()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    Field attributeQuotesField = QuoteAnnotator.class.getDeclaredField("ATTRIBUTE_QUOTES");
    attributeQuotesField.setAccessible(true);
    attributeQuotesField.set(null, false);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertEquals(1, result.size());
    assertTrue(result.contains(QuotationsAnnotation.class));
    assertFalse(result.contains(QuotationIndexAnnotation.class));
    assertFalse(result.contains(SpeakerAnnotation.class));
}

@Test
public void test4()
{
    String surfaceForm = "He said, \"Hello there.\"";
    int begin = 10;
    int end = 26;
    CoreLabel token1 = new CoreLabel();
    token1.setWord("Hello");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("there");
    CoreLabel token3 = new CoreLabel();
    token3.setWord(".");
    List<CoreLabel> quoteTokens = Arrays.asList(token1, token2, token3);
    int tokenOffset = 4;
    int sentenceBeginIndex = 1;
    int sentenceEndIndex = 1;
    String docID = "doc123";
    Annotation result = QuoteAnnotator.makeQuote(surfaceForm, begin, end, quoteTokens, tokenOffset, sentenceBeginIndex, sentenceEndIndex, docID);
    assertEquals(surfaceForm, result.get(TextAnnotation.class));
    assertEquals(((Integer) (begin)), result.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(((Integer) (end)), result.get(CharacterOffsetEndAnnotation.class));
    assertEquals(docID, result.get(DocIDAnnotation.class));
    assertEquals(quoteTokens, result.get(TokensAnnotation.class));
    assertEquals(((Integer) (tokenOffset)), result.get(TokenBeginAnnotation.class));
    assertEquals(((Integer) ((tokenOffset + quoteTokens.size()) - 1)), result.get(TokenEndAnnotation.class));
    assertEquals(((Integer) (sentenceBeginIndex)), result.get(SentenceBeginAnnotation.class));
    assertEquals(((Integer) (sentenceEndIndex)), result.get(SentenceEndAnnotation.class));
}

@Test
public void test5()
{
    String input = "“Hello”, he said, ‘How are you?’";
    String expectedOutput = "\"Hello\", he said, \'How are you?\'";
    String actualOutput = QuoteAnnotator.replaceUnicode(input);
    assertEquals(expectedOutput, actualOutput);
}

@Test
public void test6()
{
    String documentText = "<quote>He said, \"Hello World\"</quote> after the meeting.";
    Annotation annotation = new Annotation(documentText);
    List<CoreLabel> tokens = new ArrayList<CoreLabel>();
    CoreLabel token1 = new CoreLabel();
    token1.set(OriginalTextAnnotation.class, "He");
    token1.set(CharacterOffsetBeginAnnotation.class, 7);
    token1.set(CharacterOffsetEndAnnotation.class, 9);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.set(OriginalTextAnnotation.class, "said");
    token2.set(CharacterOffsetBeginAnnotation.class, 10);
    token2.set(CharacterOffsetEndAnnotation.class, 14);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.set(OriginalTextAnnotation.class, ",");
    token3.set(CharacterOffsetBeginAnnotation.class, 14);
    token3.set(CharacterOffsetEndAnnotation.class, 15);
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.set(OriginalTextAnnotation.class, "\"");
    token4.set(CharacterOffsetBeginAnnotation.class, 16);
    token4.set(CharacterOffsetEndAnnotation.class, 17);
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.set(OriginalTextAnnotation.class, "Hello");
    token5.set(CharacterOffsetBeginAnnotation.class, 17);
    token5.set(CharacterOffsetEndAnnotation.class, 22);
    tokens.add(token5);
    CoreLabel token6 = new CoreLabel();
    token6.set(OriginalTextAnnotation.class, "World");
    token6.set(CharacterOffsetBeginAnnotation.class, 23);
    token6.set(CharacterOffsetEndAnnotation.class, 28);
    tokens.add(token6);
    CoreLabel token7 = new CoreLabel();
    token7.set(OriginalTextAnnotation.class, "\"");
    token7.set(CharacterOffsetBeginAnnotation.class, 28);
    token7.set(CharacterOffsetEndAnnotation.class, 29);
    tokens.add(token7);
    CoreLabel token8 = new CoreLabel();
    token8.set(OriginalTextAnnotation.class, "after");
    token8.set(CharacterOffsetBeginAnnotation.class, 37);
    token8.set(CharacterOffsetEndAnnotation.class, 42);
    tokens.add(token8);
    CoreLabel token9 = new CoreLabel();
    token9.set(OriginalTextAnnotation.class, "the");
    token9.set(CharacterOffsetBeginAnnotation.class, 43);
    token9.set(CharacterOffsetEndAnnotation.class, 46);
    tokens.add(token9);
    CoreLabel token10 = new CoreLabel();
    token10.set(OriginalTextAnnotation.class, "meeting");
    token10.set(CharacterOffsetBeginAnnotation.class, 47);
    token10.set(CharacterOffsetEndAnnotation.class, 54);
    tokens.add(token10);
    CoreLabel token11 = new CoreLabel();
    token11.set(OriginalTextAnnotation.class, ".");
    token11.set(CharacterOffsetBeginAnnotation.class, 54);
    token11.set(CharacterOffsetEndAnnotation.class, 55);
    tokens.add(token11);
    annotation.set(TokensAnnotation.class, tokens);
    String expected = "       He said, \"Hello World\"         after the meeting.";
    String actual = QuoteAnnotator.xmlFreeText(documentText, annotation);
    assertEquals(expected, actual);
}

@Test
public void test7()
{
    CoreMap quote1 = new TypesafeMap.CoreMap();
    CoreMap quote2 = new TypesafeMap.CoreMap();
    quote1.set(CharacterOffsetBeginAnnotation.class, 5);
    quote2.set(CharacterOffsetBeginAnnotation.class, 10);
    Comparator<CoreMap> comparator = QuoteAnnotator.getQuoteComparator();
    assertTrue("Quote with smaller offset should come first", comparator.compare(quote1, quote2) < 0);
    assertEquals("Quotes with same offset should be equal", 0, comparator.compare(quote1, quote1));
    assertTrue("Quote with larger offset should come after", comparator.compare(quote2, quote1) > 0);
}

@Test
public void test8()
{
    CoreMap innerQuote = mock(CoreMap.class);
    CoreMap outerQuote = mock(CoreMap.class);
    CoreMap rootQuote = mock(CoreMap.class);
    when(innerQuote.get(QuotationsAnnotation.class)).thenReturn(null);
    when(outerQuote.get(QuotationsAnnotation.class)).thenReturn(Arrays.asList(innerQuote));
    when(rootQuote.get(QuotationsAnnotation.class)).thenReturn(Arrays.asList(outerQuote));
    when(innerQuote.get(Mockito.any())).thenReturn(3);
    when(outerQuote.get(Mockito.any())).thenReturn(2);
    when(rootQuote.get(Mockito.any())).thenReturn(1);
    List<CoreMap> result = QuoteAnnotator.gatherQuotes(rootQuote);
    assertEquals(2, result.size());
    assertTrue(result.contains(outerQuote));
    assertTrue(result.contains(innerQuote));
    assertEquals(innerQuote, result.get(1));
    assertEquals(outerQuote, result.get(0));
}

@Test
public void test9()
{
    String text = "He said, \"I am leaving now.\" Then he left.";
    String docID = "doc1";
    boolean unclosed = false;
    int quoteBegin = text.indexOf("\"I am leaving now.\"");
    int quoteEnd = quoteBegin + "\"I am leaving now.\"".length();
    Pair<Integer, Integer> quoteSpan = new Pair<>(quoteBegin, quoteEnd);
    List<Pair<Integer, Integer>> quotes = new ArrayList<>();
    quotes.add(quoteSpan);
    CoreLabel token1 = new CoreLabel();
    token1.setWord("He");
    token1.setBeginPosition(0);
    token1.setEndPosition(2);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("said");
    token2.setBeginPosition(3);
    token2.setEndPosition(7);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(",");
    token3.setBeginPosition(7);
    token3.setEndPosition(8);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("\"");
    token4.setBeginPosition(9);
    token4.setEndPosition(10);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("I");
    token5.setBeginPosition(10);
    token5.setEndPosition(11);
    CoreLabel token6 = new CoreLabel();
    token6.setWord("am");
    token6.setBeginPosition(12);
    token6.setEndPosition(14);
    CoreLabel token7 = new CoreLabel();
    token7.setWord("leaving");
    token7.setBeginPosition(15);
    token7.setEndPosition(22);
    CoreLabel token8 = new CoreLabel();
    token8.setWord("now");
    token8.setBeginPosition(23);
    token8.setEndPosition(26);
    CoreLabel token9 = new CoreLabel();
    token9.setWord(".");
    token9.setBeginPosition(26);
    token9.setEndPosition(27);
    CoreLabel token10 = new CoreLabel();
    token10.setWord("\"");
    token10.setBeginPosition(27);
    token10.setEndPosition(28);
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(token1);
    tokens.add(token2);
    tokens.add(token3);
    tokens.add(token4);
    tokens.add(token5);
    tokens.add(token6);
    tokens.add(token7);
    tokens.add(token8);
    tokens.add(token9);
    tokens.add(token10);
    CoreMap sentence = new Annotation(text);
    sentence.set(CharacterOffsetBeginAnnotation.class, 0);
    sentence.set(CharacterOffsetEndAnnotation.class, text.length());
    sentence.set(SentenceIndexAnnotation.class, 0);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    List<CoreMap> result = QuoteAnnotator.getCoreMapQuotes(quotes, tokens, sentences, text, docID, unclosed);
    assertEquals(1, result.size());
    CoreMap quote = result.get(0);
    assertEquals(quoteBegin, ((int) (quote.get(CharacterOffsetBeginAnnotation.class))));
    assertEquals(quoteEnd, ((int) (quote.get(CharacterOffsetEndAnnotation.class))));
    assertEquals(text.substring(quoteBegin, quoteEnd), quote.get(TextAnnotation.class));
}

@Test
public void test10()
{
    Annotation annotation = new Annotation("\"Hello world,\" she said.");
    annotation.set(TextAnnotation.class, "\"Hello world,\" she said.");
    annotation.set(DocIDAnnotation.class, "doc123");
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("\"");
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Hello");
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("world");
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord(",");
    tokens.add(token4);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("\"");
    tokens.add(token5);
    CoreLabel token6 = new CoreLabel();
    token6.setWord("she");
    tokens.add(token6);
    CoreLabel token7 = new CoreLabel();
    token7.setWord("said");
    tokens.add(token7);
    CoreLabel token8 = new CoreLabel();
    token8.setWord(".");
    tokens.add(token8);
    annotation.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = new ArrayList<>();
    CoreMap sentence = new Annotation("\"Hello world,\" she said.");
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    QuoteAnnotator quoteAnnotator = new QuoteAnnotator();
    quoteAnnotator.annotate(annotation);
    List<CoreMap> quoteSpans = annotation.get(QuotationsAnnotation.class);
    assertNotNull("Quotation annotations should not be null", quoteSpans);
    assertFalse("There should be at least one quote detected", quoteSpans.isEmpty());
}

