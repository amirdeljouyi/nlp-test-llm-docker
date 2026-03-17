import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    StanfordCoreNLP pipeline = new StanfordCoreNLP();
    AnnotatorImplementations result = pipeline.getAnnotatorImplementations();
    assertNotNull("Expected non-null AnnotatorImplementations instance", result);
}

@Test
public void test2()
{
    File tempFile = File.createTempFile("test", ".txt");
    tempFile.deleteOnExit();
    FileWriter writer = new FileWriter(tempFile);
    writer.write("Stanford CoreNLP is a natural language processing library.");
    writer.close();
    List<File> files = Collections.singletonList(tempFile);
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    props.setProperty("outputFormat", "text");
    props.setProperty("outputDirectory", tempFile.getParent());
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.processFiles(tempFile.getParent(), files, 1, false, Optional.of(new Timing()));
    File outputFile = new File(tempFile.getParent(), tempFile.getName() + ".out.txt");
    assertTrue("Expected output file to exist", outputFile.exists());
    outputFile.deleteOnExit();
}

@Test
public void test3()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String inputText = "Stanford NLP is awesome.";
    Annotation result = pipeline.process(inputText);
    assertNotNull(result);
    assertEquals(inputText, result.get(TextAnnotation.class));
}

@Test
public void test4()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String text = "Stanford CoreNLP is amazing.";
    CoreDocument coreDocument = pipeline.processToCoreDocument(text);
    assertNotNull(coreDocument);
    assertNotNull(coreDocument.annotation());
    assertEquals(text, coreDocument.text());
    assertFalse(coreDocument.tokens().isEmpty());
    assertEquals("Stanford", coreDocument.tokens().get(0).word());
}

@Test
public void test5()
{
    Properties props = new Properties();
    props.setProperty("encoding", "ISO-8859-1");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String actualEncoding = pipeline.getEncoding();
    assertEquals("ISO-8859-1", actualEncoding);
}

@Test
public void test6()
{
    Properties expectedProperties = new Properties();
    expectedProperties.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(expectedProperties);
    Properties actualProperties = pipeline.getProperties();
    assertSame(expectedProperties, actualProperties);
}

@Test
public void test7()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
    boolean result = StanfordCoreNLP.usesBinaryTrees(props);
    assertTrue(result);
}

@Test
public void test8()
{
    String[] inputAnnotators = new String[]{ "lemma" };
    Properties props = new Properties();
    String result = StanfordCoreNLP.ensurePrerequisiteAnnotators(inputAnnotators, props);
    assertEquals("tokenize,ssplit,pos,lemma", result);
}

@Test
public void test9()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    AnnotationOutputter.Options options = Options.valueOf("INLINE_XML");
    BiConsumer<Annotation, OutputStream> outputter = StanfordCoreNLP.createOutputter(props, options);
    Annotation annotation = new Annotation("Stanford CoreNLP is awesome.");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.annotate(annotation);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputter.accept(annotation, outputStream);
    String output = outputStream.toString();
    assertTrue("Output should contain expected annotation content", output.contains("Stanford"));
}

@Test
public void test10()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    AnnotatorImplementations implementations = new AnnotatorImplementations();
    AnnotatorPool pool1 = StanfordCoreNLP.getDefaultAnnotatorPool(props, implementations);
    AnnotatorPool pool2 = StanfordCoreNLP.getDefaultAnnotatorPool(props, implementations);
    assertNotNull(pool1);
    assertSame(pool1, pool2);
    Annotator tokenizer = pool1.get("tokenize");
    Annotator ssplit = pool1.get("ssplit");
    assertNotNull(tokenizer);
    assertNotNull(ssplit);
}

@Test
public void test11()
{
    String[] args = new String[]{ "-help", "pos" };
    PrintStream originalErr = System.err;
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));
    StanfordCoreNLP.main(args);
    System.setErr(originalErr);
    String output = errContent.toString();
    System.out.println("Help output: " + output);
}

@Test
public void test12()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    CoreDocument doc = new CoreDocument("Barack Obama was born in Hawaii.");
    pipeline.annotate(doc);
    assertNotNull("Sentences should not be null after annotation", doc.sentences());
    assertFalse("Sentences should not be empty after annotation", doc.sentences().isEmpty());
    assertNotNull("Entity mentions should not be null after annotation", doc.entityMentions());
    assertFalse("Entity mentions should not be empty after annotation", doc.entityMentions().isEmpty());
}

@Test
public void test13()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    CoreDocument document = new CoreDocument("Stanford CoreNLP is great.");
    pipeline.annotate(document);
    assertNotNull(document.sentences());
    assertEquals(1, document.sentences().size());
    assertEquals("Stanford CoreNLP is great.", document.sentences().get(0).text());
}

@Test
public void test14()
{
    StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators", "tokenize,ssplit,pos,lemma,ner"));
    CoreDocument document = new CoreDocument("Barack Obama was born in Hawaii.");
    pipeline.annotate(document);
    assertNotNull(document.sentences());
    assertEquals(1, document.sentences().size());
    assertEquals("Barack Obama was born in Hawaii.", document.text());
    assertFalse(document.sentences().get(0).tokens().isEmpty());
    assertFalse(document.sentences().get(0).entityMentions().isEmpty());
}

@Test
public void test15()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("Stanford CoreNLP is awesome.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.conllPrint(annotation, writer);
    String output = writer.toString();
    assertTrue("Output should contain sentence token", output.contains("Stanford"));
    assertTrue("Output should contain lemma", output.contains("Stanford"));
    assertTrue("Output should contain part-of-speech tag", output.matches("(?s).*\\bNNP\\b.*"));
}

@Test
public void test16()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("Stanford NLP is awesome.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.jsonPrint(annotation, writer);
    String jsonOutput = writer.toString();
    assertTrue(jsonOutput.contains("Stanford"));
    assertTrue(jsonOutput.contains("NLP"));
    assertTrue(jsonOutput.contains("awesome"));
}

@Test
public void test17()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String text = "Stanford NLP is great.";
    Annotation annotation = new Annotation(text);
    pipeline.annotate(annotation);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    pipeline.prettyPrint(annotation, outputStream);
    String output = outputStream.toString();
    assertTrue(output.contains("Stanford"));
    assertTrue(output.contains("great"));
    assertTrue(output.contains("Tokens"));
}

@Test
public void test18()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("The quick brown fox jumps over the lazy dog.");
    pipeline.annotate(annotation);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    pipeline.prettyPrint(annotation, out);
    String result = out.toString("UTF-8");
    assertTrue(result.contains("The quick brown fox jumps over the lazy dog"));
}

@Test
public void test19()
{
    Properties properties = new Properties();
    properties.setProperty("annotators", "tokenize,ssplit");
    properties.setProperty("outputFormat", "text");
    File tempFile = File.createTempFile("testDoc", ".txt");
    tempFile.deleteOnExit();
    FileWriter writer = new FileWriter(tempFile);
    writer.write("This is a test sentence.");
    writer.close();
    StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
    Collection<File> files = Arrays.asList(tempFile);
    pipeline.processFiles(tempFile.getParent(), files, 1, true, Optional.empty());
}

@Test
public void test20()
{
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "nlpTestDir");
    tempDir.mkdir();
    File inputFile = new File(tempDir, "test.txt");
    FileWriter writer = new FileWriter(inputFile);
    writer.write("Stanford CoreNLP is a great NLP toolkit.");
    writer.close();
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    props.setProperty("outputFormat", "text");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.processFiles(tempDir.getAbsolutePath(), Collections.singletonList(inputFile), 1, false, Optional.empty());
    inputFile.delete();
    tempDir.delete();
}

@Test
public void test21()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    props.setProperty("outputFormat", "text");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String baseDir = ".";
    File tempFile = File.createTempFile("testDoc", ".txt");
    tempFile.deleteOnExit();
    pipeline.processFiles(baseDir, Collections.singleton(tempFile), 1, true, Optional.empty());
}

@Test
public void test22()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.run();
}

@Test
public void test23()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.run();
}

@Test
public void test24()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("Hello world.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.xmlPrint(annotation, writer);
    String output = writer.toString();
    assertTrue("Expected output to contain at least one XML tag", output.contains("<") && output.contains(">"));
}

@Test
public void test25()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    props.setProperty("outputFormat", "xml");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("Hello world.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.xmlPrint(annotation, writer);
    String output = writer.toString();
    assertTrue(output.contains("Hello"));
    assertTrue(output.contains("world"));
    assertTrue(output.contains("xml"));
}

@Test
public void test26()
{
    Properties properties = new Properties();
    properties.setProperty("annotators", "tokenize,ssplit,pos");
    String oldAnnotator = "tokenize";
    String newAnnotator = "cdc_tokenize";
    StanfordCoreNLP.replaceAnnotator(properties, oldAnnotator, newAnnotator);
    String updatedAnnotators = properties.getProperty("annotators");
    assertEquals("cdc_tokenize,ssplit,pos", updatedAnnotators);
}

@Test
public void test27()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,cleanxml,ssplit");
    StanfordCoreNLP.unifyTokenizeProperty(props, "cleanxml", "tokenize.cleanxml");
    assertEquals("tokenize,ssplit", props.getProperty("annotators"));
    assertEquals("true", props.getProperty("tokenize.cleanxml"));
}

