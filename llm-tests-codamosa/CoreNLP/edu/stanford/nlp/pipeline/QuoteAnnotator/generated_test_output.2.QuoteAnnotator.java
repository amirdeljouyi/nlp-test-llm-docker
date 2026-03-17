import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAnnotator quoteAnnotator = new QuoteAnnotator();
    String text = "He said, \"This is a quote.\" Then she replied, \"Indeed it is.\"";
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = quoteAnnotator.getQuotes(text);
    List<Pair<Integer, Integer>> quoteSpans = result.first();
    List<Pair<Integer, Integer>> quoteContents = result.second();
    assertEquals(2, quoteSpans.size());
    assertEquals(Integer.valueOf(9), quoteSpans.get(0).first);
    assertEquals(Integer.valueOf(28), quoteSpans.get(0).second);
    assertEquals(Integer.valueOf(48), quoteSpans.get(1).first);
    assertEquals(Integer.valueOf(64), quoteSpans.get(1).second);
    assertEquals(2, quoteContents.size());
    assertEquals(Integer.valueOf(10), quoteContents.get(0).first);
    assertEquals(Integer.valueOf(27), quoteContents.get(0).second);
    assertEquals(Integer.valueOf(49), quoteContents.get(1).first);
    assertEquals(Integer.valueOf(63), quoteContents.get(1).second);
}

@Test
public void test2()
{
    QuoteAnnotator quoteAnnotator = new QuoteAnnotator();
    String inputText = "He said, \"She replied, \'Indeed.\' and left.\"";
    int offset = 0;
    String prevQuote = null;
    Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> result = quoteAnnotator.recursiveQuotes(inputText, offset, prevQuote);
    List<Pair<Integer, Integer>> quotes = result.first();
    List<Pair<Integer, Integer>> unclosedQuotes = result.second();
    assertEquals(2, quotes.size());
    Pair<Integer, Integer> outerQuote = quotes.get(0);
    assertEquals(Integer.valueOf(9), outerQuote.first());
    assertEquals(Integer.valueOf(43), outerQuote.second());
    assertEquals("\"She replied, \'Indeed.\' and left.\"", inputText.substring(outerQuote.first(), outerQuote.second()));
    Pair<Integer, Integer> innerQuote = quotes.get(1);
    assertEquals(Integer.valueOf(23), innerQuote.first());
    assertEquals(Integer.valueOf(31), innerQuote.second());
    assertEquals("'Indeed.'", inputText.substring(innerQuote.first(), innerQuote.second()));
    assertTrue(unclosedQuotes.isEmpty());
}

@Test
public void test3()
{
    Field field = QuoteAnnotator.class.getDeclaredField("ATTRIBUTE_QUOTES");
    field.setAccessible(true);
    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(field, field.getModifiers() & (~Modifier.FINAL));
    field.set(null, true);
    QuoteAnnotator annotator = new QuoteAnnotator();
    Set<Class<?>> expected = new HashSet<>(Arrays.asList(QuotationsAnnotation.class, QuotationIndexAnnotation.class, MentionAnnotation.class, MentionBeginAnnotation.class, MentionEndAnnotation.class, MentionTypeAnnotation.class, MentionSieveAnnotation.class, SpeakerAnnotation.class, SpeakerSieveAnnotation.class, ParagraphIndexAnnotation.class));
    Set<Class<? extends CoreAnnotations>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test4()
{
    String surfaceForm = "She said hello.";
    int begin = 5;
    int end = 20;
    CoreLabel token1 = new CoreLabel();
    token1.setWord("She");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("said");
    CoreLabel token3 = new CoreLabel();
    token3.setWord("hello");
    CoreLabel token4 = new CoreLabel();
    token4.setWord(".");
    List<CoreLabel> quoteTokens = new ArrayList<CoreLabel>();
    quoteTokens.add(token1);
    quoteTokens.add(token2);
    quoteTokens.add(token3);
    quoteTokens.add(token4);
    int tokenOffset = 2;
    int sentenceBeginIndex = 0;
    int sentenceEndIndex = 1;
    String docID = "doc-123";
    Annotation result = QuoteAnnotator.makeQuote(surfaceForm, begin, end, quoteTokens, tokenOffset, sentenceBeginIndex, sentenceEndIndex, docID);
    assertEquals("She said hello.", result.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(5), result.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(Integer.valueOf(20), result.get(CharacterOffsetEndAnnotation.class));
    assertEquals("doc-123", result.get(DocIDAnnotation.class));
    assertEquals(quoteTokens, result.get(TokensAnnotation.class));
    assertEquals(Integer.valueOf(2), result.get(TokenBeginAnnotation.class));
    assertEquals(Integer.valueOf(5), result.get(TokenEndAnnotation.class));
    assertEquals(Integer.valueOf(0), result.get(SentenceBeginAnnotation.class));
    assertEquals(Integer.valueOf(1), result.get(SentenceEndAnnotation.class));
}

@Test
public void test5()
{
    String input = "“Hello”, she said. ‘It’s a lovely day.’";
    String expected = "\"Hello\", she said. \'It\'s a lovely day.\'";
    String actual = QuoteAnnotator.replaceUnicode(input);
    assertEquals(expected, actual);
}

@Test
public void test6()
{
    String inputText = "<xml><tag>Hello</tag> world!</xml>";
    Annotation annotation = new Annotation(inputText);
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.set(OriginalTextAnnotation.class, "Hello");
    token1.set(CharacterOffsetBeginAnnotation.class, 11);
    token1.set(CharacterOffsetEndAnnotation.class, 16);
    tokens.add(token1);
    CoreLabel token2 = new CoreLabel();
    token2.set(OriginalTextAnnotation.class, "world");
    token2.set(CharacterOffsetBeginAnnotation.class, 17);
    token2.set(CharacterOffsetEndAnnotation.class, 22);
    tokens.add(token2);
    CoreLabel token3 = new CoreLabel();
    token3.set(OriginalTextAnnotation.class, "!");
    token3.set(CharacterOffsetBeginAnnotation.class, 22);
    token3.set(CharacterOffsetEndAnnotation.class, 23);
    tokens.add(token3);
    annotation.set(TokensAnnotation.class, tokens);
    String result = QuoteAnnotator.xmlFreeText(inputText, annotation);
    String expected = "          Hello world!           ";
    assertEquals(expected, result);
}

@Test
public void test7()
{
    CoreMap quote1 = new ArrayCoreMap();
    quote1.set(CharacterOffsetBeginAnnotation.class, 10);
    CoreMap quote2 = new ArrayCoreMap();
    quote2.set(CharacterOffsetBeginAnnotation.class, 20);
    Comparator<CoreMap> comparator = QuoteAnnotator.getQuoteComparator();
    int result = comparator.compare(quote1, quote2);
    assertTrue("Expected quote1 to come before quote2", result < 0);
}

@Test
public void test8()
{
    ArrayCoreMap innermostQuote = new ArrayCoreMap();
    innermostQuote.set(QuotationsAnnotation.class, null);
    innermostQuote.set(CharacterOffsetBeginAnnotation.class, 10);
    ArrayCoreMap innerQuote = new ArrayCoreMap();
    innerQuote.set(QuotationsAnnotation.class, Generics.newArrayList(innermostQuote));
    innerQuote.set(CharacterOffsetBeginAnnotation.class, 5);
    ArrayCoreMap outerQuote = new ArrayCoreMap();
    outerQuote.set(QuotationsAnnotation.class, Generics.newArrayList(innerQuote));
    outerQuote.set(CharacterOffsetBeginAnnotation.class, 0);
    List<CoreMap> result = QuoteAnnotator.gatherQuotes(outerQuote);
    assertEquals(2, result.size());
    assertSame(innermostQuote, result.get(0));
    assertSame(innerQuote, result.get(1));
}

@Test
public void test9()
{
    String text = "He said, \"Hello world!\" and left.";
    String docID = "doc1";
    boolean unclosed = false;
    List<Pair<Integer, Integer>> quotes = new ArrayList<>();
    quotes.add(new Pair<>(10, 24));
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel tok1 = new CoreLabel();
    tok1.setWord("He");
    tok1.setBeginPosition(0);
    tok1.setEndPosition(2);
    tokens.add(tok1);
    CoreLabel tok2 = new CoreLabel();
    tok2.setWord("said");
    tok2.setBeginPosition(3);
    tok2.setEndPosition(7);
    tokens.add(tok2);
    CoreLabel tok3 = new CoreLabel();
    tok3.setWord(",");
    tok3.setBeginPosition(7);
    tok3.setEndPosition(8);
    tokens.add(tok3);
    CoreLabel tok4 = new CoreLabel();
    tok4.setWord("\"");
    tok4.setBeginPosition(9);
    tok4.setEndPosition(10);
    tokens.add(tok4);
    CoreLabel tok5 = new CoreLabel();
    tok5.setWord("Hello");
    tok5.setBeginPosition(10);
    tok5.setEndPosition(15);
    tokens.add(tok5);
    CoreLabel tok6 = new CoreLabel();
    tok6.setWord("world");
    tok6.setBeginPosition(16);
    tok6.setEndPosition(21);
    tokens.add(tok6);
    CoreLabel tok7 = new CoreLabel();
    tok7.setWord("!");
    tok7.setBeginPosition(21);
    tok7.setEndPosition(22);
    tokens.add(tok7);
    CoreLabel tok8 = new CoreLabel();
    tok8.setWord("\"");
    tok8.setBeginPosition(23);
    tok8.setEndPosition(24);
    tokens.add(tok8);
    CoreLabel tok9 = new CoreLabel();
    tok9.setWord("and");
    tok9.setBeginPosition(25);
    tok9.setEndPosition(28);
    tokens.add(tok9);
    CoreLabel tok10 = new CoreLabel();
    tok10.setWord("left");
    tok10.setBeginPosition(29);
    tok10.setEndPosition(33);
    tokens.add(tok10);
    CoreLabel tok11 = new CoreLabel();
    tok11.setWord(".");
    tok11.setBeginPosition(33);
    tok11.setEndPosition(34);
    tokens.add(tok11);
    CoreMap sentence = new TypesafeMap<>();
    sentence.set(CharacterOffsetBeginAnnotation.class, 0);
    sentence.set(CharacterOffsetEndAnnotation.class, 34);
    sentence.set(SentenceIndexAnnotation.class, 0);
    List<CoreMap> sentences = Collections.singletonList(sentence);
    List<CoreMap> result = QuoteAnnotator.getCoreMapQuotes(quotes, tokens, sentences, text, docID, unclosed);
    assertEquals(1, result.size());
    CoreMap quoteAnnotation = result.get(0);
    assertEquals("Hello world!", quoteAnnotation.get(TextAnnotation.class));
    assertEquals(Integer.valueOf(10), quoteAnnotation.get(CharacterOffsetBeginAnnotation.class));
    assertEquals(Integer.valueOf(24), quoteAnnotation.get(CharacterOffsetEndAnnotation.class));
    assertEquals(Integer.valueOf(0), quoteAnnotation.get(SentenceBeginAnnotation.class));
    assertEquals(Integer.valueOf(0), quoteAnnotation.get(SentenceEndAnnotation.class));
    List<CoreLabel> quoteTokens = quoteAnnotation.get(TokensAnnotation.class);
    assertNotNull(quoteTokens);
    assertEquals(4, quoteTokens.size());
    assertEquals("Hello", quoteTokens.get(0).word());
}

@Test
public void test10()
{
    String text = "She said, “Hello, world!” and smiled.";
    Annotation annotation = new Annotation(text);
    annotation.set(TextAnnotation.class, text);
    List<CoreLabel> tokens = new ArrayList<>();
    CoreLabel token1 = new CoreLabel();
    token1.setWord("She");
    CoreLabel token2 = new CoreLabel();
    token2.setWord("said");
    CoreLabel token3 = new CoreLabel();
    token3.setWord(",");
    CoreLabel token4 = new CoreLabel();
    token4.setWord("“");
    CoreLabel token5 = new CoreLabel();
    token5.setWord("Hello");
    CoreLabel token6 = new CoreLabel();
    token6.setWord(",");
    CoreLabel token7 = new CoreLabel();
    token7.setWord("world");
    CoreLabel token8 = new CoreLabel();
    token8.setWord("!”");
    CoreLabel token9 = new CoreLabel();
    token9.setWord("and");
    CoreLabel token10 = new CoreLabel();
    token10.setWord("smiled");
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
    annotation.set(TokensAnnotation.class, tokens);
    List<CoreMap> sentences = new ArrayList<>();
    CoreMap sentence = new Annotation("She said, “Hello, world!” and smiled.");
    sentence.set(TokensAnnotation.class, tokens);
    sentences.add(sentence);
    annotation.set(SentencesAnnotation.class, sentences);
    annotation.set(DocIDAnnotation.class, "testDoc");
    QuoteAnnotator annotator = new QuoteAnnotator();
    annotator.annotate(annotation);
    List<CoreMap> quotes = annotation.get(QuotationsAnnotation.class);
    assertNotNull("Quotes should not be null after annotation", quotes);
    assertFalse("There should be at least one quote identified", quotes.isEmpty());
}

