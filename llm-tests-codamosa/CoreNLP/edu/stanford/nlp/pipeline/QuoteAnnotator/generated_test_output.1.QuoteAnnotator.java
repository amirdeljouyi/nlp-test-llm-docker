import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    String inputText = "He said, \"This is a quote.\"";
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.getQuotes(inputText);
    List<Pair<Integer, Integer>> outerQuotes = result.first;
    List<Pair<Integer, Integer>> innerQuotes = result.second;
    assertNotNull("Outer quotes should not be null", outerQuotes);
    assertNotNull("Inner quotes should not be null", innerQuotes);
    assertEquals("There should be one outer quote", 1, outerQuotes.size());
    Pair<Integer, Integer> outerQuote = outerQuotes.get(0);
    assertEquals("Outer quote start index should be 10", Integer.valueOf(10), outerQuote.first);
    assertEquals("Outer quote end index should be 29", Integer.valueOf(29), outerQuote.second);
    assertTrue("There should be no inner quotes", innerQuotes.isEmpty());
}

@Test
public void test2()
{
    String text = "He said, \"This is a quote.\"";
    int offset = 0;
    String prevQuote = null;
    QuoteAnnotator annotator = new QuoteAnnotator();
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = annotator.recursiveQuotes(text, offset, prevQuote);
    Assert.assertEquals(1, result.first().size());
    Pair<Integer, Integer> quoteSpan = result.first().get(0);
    Assert.assertEquals(Integer.valueOf(9), quoteSpan.first());
    Assert.assertEquals(Integer.valueOf(28), quoteSpan.second());
    Assert.assertTrue(result.second().isEmpty());
}

@Test
public void test3()
{
    QuoteAnnotator annotator = new QuoteAnnotator();
    Field field = QuoteAnnotator.class.getDeclaredField("ATTRIBUTE_QUOTES");
    field.setAccessible(true);
    field.setBoolean(null, true);
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertTrue(result.contains(QuotationsAnnotation.class));
    assertTrue(result.contains(QuotationIndexAnnotation.class));
    assertTrue(result.contains(MentionAnnotation.class));
    assertTrue(result.contains(MentionBeginAnnotation.class));
    assertTrue(result.contains(MentionEndAnnotation.class));
    assertTrue(result.contains(MentionTypeAnnotation.class));
    assertTrue(result.contains(MentionSieveAnnotation.class));
    assertTrue(result.contains(SpeakerAnnotation.class));
    assertTrue(result.contains(SpeakerSieveAnnotation.class));
    assertTrue(result.contains(ParagraphIndexAnnotation.class));
    assertEquals(10, result.size());
}

@Test
public void test4()
{
    String surfaceForm = "This is a quoted sentence.";
    int begin = 10;
    int end = 36;
    int tokenOffset = 5;
    int sentenceBeginIndex = 2;
    int sentenceEndIndex = 3;
    String docID = "doc123";
    CoreLabel token1 = new CoreLabel();
    token1.set(IndexAnnotation.class, 1);
    token1.setWord("This");
    CoreLabel token2 = new CoreLabel();
    token2.set(IndexAnnotation.class, 2);
    token2.setWord("is");
    List<CoreLabel> quoteTokens = new ArrayList<>();
    quoteTokens.add(token1);
    quoteTokens.add(token2);
    Annotation quote = QuoteAnnotator.makeQuote(surfaceForm, begin, end, quoteTokens, tokenOffset, sentenceBeginIndex, sentenceEndIndex, docID);
    assertEquals(surfaceForm, quote.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(begin), quote.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(Integer.valueOf(end), quote.get(CharacterOffsetEndAnnotation.class));
    assertEquals(docID, quote.get(DocIDAnnotation.class));
    assertEquals(quoteTokens, quote.get(TokensAnnotation.class));
    assertEquals(Integer.valueOf(tokenOffset), quote.get(TokenBeginAnnotation.class));
    assertEquals(Integer.valueOf((tokenOffset + quoteTokens.size()) - 1), quote.get(TokenEndAnnotation.class));
    assertEquals(Integer.valueOf(sentenceBeginIndex), quote.get(SentenceBeginAnnotation.class));
    assertEquals(Integer.valueOf(sentenceEndIndex), quote.get(SentenceEndAnnotation.class));
}

@Test
public void test5()
{
    String input = "“Hello”, she said. ‘It’s working!’";
    String expected = "\"Hello\", she said. \'It\'s working!\'";
    String actual = QuoteAnnotator.replaceUnicode(input);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    String documentText = "<quote>He said,</quote> \"Hello world!\"";
    CoreLabel token1 = new CoreLabel();
    token1.set(OriginalTextAnnotation.class, "He");
    token1.set(CharacterOffsetBeginAnnotation.class, 15);
    token1.set(CharacterOffsetEndAnnotation.class, 17);
    CoreLabel token2 = new CoreLabel();
    token2.set(OriginalTextAnnotation.class, "said");
    token2.set(CharacterOffsetBeginAnnotation.class, 18);
    token2.set(CharacterOffsetEndAnnotation.class, 22);
    CoreLabel token3 = new CoreLabel();
    token3.set(OriginalTextAnnotation.class, ",");
    token3.set(CharacterOffsetBeginAnnotation.class, 22);
    token3.set(CharacterOffsetEndAnnotation.class, 23);
    CoreLabel token4 = new CoreLabel();
    token4.set(OriginalTextAnnotation.class, "\"");
    token4.set(CharacterOffsetBeginAnnotation.class, 31);
    token4.set(CharacterOffsetEndAnnotation.class, 32);
    CoreLabel token5 = new CoreLabel();
    token5.set(OriginalTextAnnotation.class, "Hello");
    token5.set(CharacterOffsetBeginAnnotation.class, 32);
    token5.set(CharacterOffsetEndAnnotation.class, 37);
    CoreLabel token6 = new CoreLabel();
    token6.set(OriginalTextAnnotation.class, "world");
    token6.set(CharacterOffsetBeginAnnotation.class, 38);
    token6.set(CharacterOffsetEndAnnotation.class, 43);
    CoreLabel token7 = new CoreLabel();
    token7.set(OriginalTextAnnotation.class, "!");
    token7.set(CharacterOffsetBeginAnnotation.class, 43);
    token7.set(CharacterOffsetEndAnnotation.class, 44);
    CoreLabel token8 = new CoreLabel();
    token8.set(OriginalTextAnnotation.class, "\"");
    token8.set(CharacterOffsetBeginAnnotation.class, 44);
    token8.set(CharacterOffsetEndAnnotation.class, 45);
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3, token4, token5, token6, token7, token8);
    Annotation annotation = new Annotation(documentText);
    annotation.set(TokensAnnotation.class, tokens);
    String result = QuoteAnnotator.xmlFreeText(documentText, annotation);
    StringBuilder expected = new StringBuilder();
    expected.append("               ");
    expected.append("He");
    expected.append(" ");
    expected.append("said");
    expected.append("");
    expected.append(",");
    expected.append("        ");
    expected.append("\"");
    expected.append("Hello");
    expected.append(" ");
    expected.append("world");
    expected.append("!");
    expected.append("\"");
    assertEquals(expected.toString(), result);
}

@Test
public void test7()
{
    CoreMap quote1 = mock(CoreMap.class);
    CoreMap quote2 = mock(CoreMap.class);
    when(quote1.get(CharacterOffsetBeginAnnotation.class)).thenReturn(10);
    when(quote2.get(CharacterOffsetBeginAnnotation.class)).thenReturn(20);
    Comparator<CoreMap> comparator = QuoteAnnotator.getQuoteComparator();
    int result = comparator.compare(quote1, quote2);
    assertTrue("quote1 should come before quote2", result < 0);
}

@Test
public void test8()
{
    CoreMap innerMost = new ArrayCoreMap();
    innerMost.set(BeginIndexAnnotation.class, 2);
    innerMost.set(QuotationsAnnotation.class, null);
    CoreMap middle = new ArrayCoreMap();
    middle.set(BeginIndexAnnotation.class, 1);
    List<CoreMap> middleEmbedded = Generics.newArrayList();
    middleEmbedded.add(innerMost);
    middle.set(QuotationsAnnotation.class, middleEmbedded);
    CoreMap outer = new ArrayCoreMap();
    outer.set(BeginIndexAnnotation.class, 0);
    List<CoreMap> outerEmbedded = Generics.newArrayList();
    outerEmbedded.add(middle);
    outer.set(QuotationsAnnotation.class, outerEmbedded);
    List<CoreMap> result = QuoteAnnotator.gatherQuotes(outer);
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).get(BeginIndexAnnotation.class).intValue());
    assertEquals(2, result.get(1).get(BeginIndexAnnotation.class).intValue());
}

@Test
public void test9()
{
    String text = "He said, \"This is a test.\"";
    int quoteStart = text.indexOf("\"");
    int quoteEnd = text.lastIndexOf("\"") + 1;
    List<Pair<Integer, Integer>> quotes = new ArrayList<>();
    quotes.add(new Pair<>(quoteStart, quoteEnd));
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("This");
    token1.setBeginPosition(text.indexOf("This"));
    token1.setEndPosition(text.indexOf("This") + 4);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("is");
    token2.setBeginPosition(text.indexOf("is"));
    token2.setEndPosition(text.indexOf("is") + 2);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.setWord("a");
    token3.setBeginPosition(text.indexOf("a"));
    token3.setEndPosition(text.indexOf("a") + 1);
    tokens.add(token3);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("test");
    token4.setBeginPosition(text.indexOf("test"));
    token4.setEndPosition(text.indexOf("test") + 4);
    tokens.add(token4);
    List<CoreMap> sentences = new ArrayList<>();
    ArrayCoreMap sentence = new ArrayCoreMap();
    sentence.set(CharacterOffsetBeginAnnotation.class, 0);
    sentence.set(CharacterOffsetEndAnnotation.class, text.length());
    sentence.set(SentenceIndexAnnotation.class, 0);
    sentences.add(sentence);
    List<CoreMap> result = QuoteAnnotator.getCoreMapQuotes(quotes, tokens, sentences, text, "testDocId", false);
    assertEquals(1, result.size());
    CoreMap quote = result.get(0);
    assertEquals(quoteStart, ((int) (quote.get(CharacterOffsetBeginAnnotation.class))));
    assertEquals(quoteEnd, ((int) (quote.get(CharacterOffsetEndAnnotation.class))));
    assertEquals("This is a test.", quote.get(TextAnnotation.class));
}

@Test
public void test10()
{
    Annotation annotation = new Annotation("\"Hello,\" he said.");
    annotation.set(TextAnnotation.class, "\"Hello,\" he said.");
    CoreLabel token1 = new CoreLabel();
    token1.setWord("\"");
    token1.setBeginPosition(0);
    token1.setEndPosition(1);
    CoreLabel token2 = new CoreLabel();
    token2.setWord("Hello");
    token2.setBeginPosition(1);
    token2.setEndPosition(6);
    CoreLabel token3 = new CoreLabel();
    token3.setWord(",");
    token3.setBeginPosition(6);
    token3.setEndPosition(7);
    CoreLabel token4 = new CoreLabel();
    token4.setWord("\"");
    token4.setBeginPosition(7);
    token4.setEndPosition(8);
    CoreLabel token5 = new CoreLabel();
    token5.setWord("he");
    token5.setBeginPosition(9);
    token5.setEndPosition(11);
    CoreLabel token6 = new CoreLabel();
    token6.setWord("said");
    token6.setBeginPosition(12);
    token6.setEndPosition(16);
    CoreLabel token7 = new CoreLabel();
    token7.setWord(".");
    token7.setBeginPosition(16);
    token7.setEndPosition(17);
    List<CoreLabel> tokens = Arrays.asList(token1, token2, token3, token4, token5, token6, token7);
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap sentence = new Annotation("Dummy sentence");
    sentence.set(TokensAnnotation.class, tokens);
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    annotation.set(DocIDAnnotation.class, "doc-1");
    QuoteAnnotator annotator = new QuoteAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> quotes = annotation.get(QuotationsAnnotation.class);
    assertNotNull(quotes);
    assertFalse(quotes.isEmpty());
}

