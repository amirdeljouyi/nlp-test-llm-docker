import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    StanfordCoreNLP pipeline = new StanfordCoreNLP(new Properties());
    AnnotatorImplementations implementations = pipeline.getAnnotatorImplementations();
    assertNotNull("getAnnotatorImplementations should not return null", implementations);
}

@Test
public void test2()
{
    File tempFileList = File.createTempFile("fileList", ".txt");
    tempFileList.deleteOnExit();
    File referencedFile = File.createTempFile("referenced", ".txt");
    referencedFile.deleteOnExit();
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileList));
    writer.write(referencedFile.getAbsolutePath());
    writer.newLine();
    writer.close();
    Collection<File> result = StanfordCoreNLP.readFileList(tempFileList.getAbsolutePath());
    assertNotNull(result);
    assertEquals(1, result.size());
    File fileFromResult = result.iterator().next();
    assertEquals(referencedFile.getAbsolutePath(), fileFromResult.getAbsolutePath());
}

@Test
public void test3()
{
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    StanfordCoreNLP.printHelp(printStream, "parser");
    String output = outputStream.toString();
    assertTrue(output.contains("StanfordCoreNLP currently supports the following parsers:"));
    assertTrue(output.contains("stanford - Stanford lexicalized parser (default)"));
    assertTrue(output.contains("Charniak and Johnson parser-specific options:"));
    assertTrue(output.contains("parse.executable - path to the parseIt binary or parse.sh script"));
}

@Test
public void test4()
{
    File tempFile = File.createTempFile("testFile", ".txt");
    tempFile.deleteOnExit();
    FileWriter writer = new FileWriter(tempFile);
    writer.write("This is a test file for StanfordCoreNLP.");
    writer.close();
    String baseDir = tempFile.getParent();
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    props.setProperty("outputFormat", "text");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.processFiles(baseDir, Arrays.asList(tempFile), 1, true, Optional.empty());
}

@Test
public void test5()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String inputText = "Stanford NLP is great.";
    Annotation result = pipeline.process(inputText);
    assertNotNull(result);
    assertEquals(inputText, result.get(TextAnnotation.class));
}

@Test
public void test6()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String inputText = "Stanford CoreNLP is an NLP toolkit.";
    CoreDocument document = pipeline.processToCoreDocument(inputText);
    assertNotNull(document);
    assertNotNull(document.annotation());
    assertEquals(inputText, document.text());
    assertFalse(document.tokens().isEmpty());
    assertEquals("Stanford", document.tokens().get(0).word());
}

@Test
public void test7()
{
    Properties props = new Properties();
    props.setProperty("encoding", "ISO-8859-1");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String encoding = pipeline.getEncoding();
    assertEquals("ISO-8859-1", encoding);
}

@Test
public void test8()
{
    Properties expectedProperties = new Properties();
    expectedProperties.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(expectedProperties);
    Properties actualProperties = pipeline.getProperties();
    assertEquals("Returned properties should match the ones provided during construction.", expectedProperties, actualProperties);
}

@Test
public void test9()
{
    String[] inputAnnotators = new String[]{ "lemma" };
    Properties props = new Properties();
    String result = StanfordCoreNLP.ensurePrerequisiteAnnotators(inputAnnotators, props);
    assertEquals("tokenize,ssplit,pos,lemma", result);
}

@Test
public void test10()
{
    Properties properties = PropertiesUtils.asProperties("annotators", "tokenize,ssplit,pos");
    AnnotationOutputter.Options options = new AnnotationOutputter.Options();
    BiConsumer<Annotation, OutputStream> outputter = StanfordCoreNLP.createOutputter(properties, options);
    Annotation annotation = new Annotation("This is a test.");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
    pipeline.annotate(annotation);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputter.accept(annotation, outputStream);
    String output = outputStream.toString();
    assertTrue(output.contains("This"));
}

@Test
public void test11()
{
    Properties props = new Properties();
    AnnotatorImplementations implementations = new AnnotatorImplementations();
    AnnotatorPool pool = StanfordCoreNLP.getDefaultAnnotatorPool(props, implementations);
    assertNotNull("Returned annotator pool should not be null", pool);
    assertSame("Returned pool should be the singleton instance", SINGLETON, pool);
}

@Test
public void test12()
{
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(errContent));
    String[] args = new String[]{ "-help", "annotators" };
    StanfordCoreNLP.main(args);
    System.setErr(originalErr);
    String output = errContent.toString();
    Assert.assertTrue("Help output should mention 'annotators'", output.contains("annotators"));
}

@Test
public void test13()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String text = "Barack Obama was born in Hawaii.";
    CoreDocument document = new CoreDocument(text);
    pipeline.annotate(document);
    assertNotNull("Sentences should not be null", document.sentences());
    assertFalse("There should be at least one sentence", document.sentences().isEmpty());
    assertNotNull("Entity mentions should not be null", document.entityMentions());
    assertFalse("There should be at least one entity mention", document.entityMentions().isEmpty());
    boolean foundPerson = false;
    if (!document.entityMentions().isEmpty()) {
        for (CoreEntityMention em : document.entityMentions()) {
            if ("Barack Obama".equals(em.text()) && "PERSON".equals(em.entityType())) {
                foundPerson = true;
                break;
            }
        }
    }
    assertTrue("Entity mention 'Barack Obama' of type 'PERSON' should be found", foundPerson);
}

@Test
public void test14()
{
    StanfordCoreNLP pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators", "tokenize,ssplit,pos,lemma,depparse"));
    Annotation annotation = new Annotation("Hello world.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.conllPrint(annotation, writer);
    String output = writer.toString();
    assertTrue("Output should contain token information", output.contains("Hello"));
    assertTrue("Output should contain POS tags or similar info", output.matches("(?s).*\\b1\\b\\s+Hello.*"));
}

@Test
public void test15()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("Hello world.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.jsonPrint(annotation, writer);
    String jsonOutput = writer.toString();
    assertTrue(jsonOutput.contains("Hello"));
    assertTrue(jsonOutput.contains("world"));
    assertTrue(jsonOutput.contains("tokens"));
    assertTrue(jsonOutput.contains("sentences"));
}

@Test
public void test16()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String text = "The quick brown fox jumps over the lazy dog.";
    Annotation annotation = new Annotation(text);
    pipeline.annotate(annotation);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    pipeline.prettyPrint(annotation, outputStream);
    String output = outputStream.toString();
    assertNotNull("Pretty print output should not be null", output);
    assertTrue("Output should contain sentence text", output.contains("The quick brown fox"));
    assertTrue("Output should contain part-of-speech tag", output.matches("(?s).*\\bPOS=.*"));
}

@Test
public void test17()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    String text = "Stanford NLP is awesome.";
    Annotation annotation = new Annotation(text);
    pipeline.annotate(annotation);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    pipeline.prettyPrint(annotation, outputStream);
    String output = outputStream.toString();
    assertTrue(output.contains("Stanford"));
    assertTrue(output.contains("NLP"));
    assertTrue(output.contains("awesome"));
    assertTrue(output.contains("TextAnnotation"));
}

@Test
public void test18()
{
    File tempDir = new File("tempInputDir");
    tempDir.mkdir();
    File tempFile = new File(tempDir, "test.txt");
    FileWriter writer = new FileWriter(tempFile);
    writer.write("This is a test.");
    writer.close();
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos");
    props.setProperty("outputFormat", "text");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.processFiles(tempDir.getAbsolutePath(), Collections.singletonList(tempFile), 1, true, Optional.empty());
    tempFile.delete();
    tempDir.delete();
}

@Test
public void test19()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    props.setProperty("outputFormat", "text");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    File tempFile = File.createTempFile("sample", ".txt");
    tempFile.deleteOnExit();
    pipeline.processFiles(tempFile.getParent(), Collections.singleton(tempFile), 1, false, Optional.empty());
}

@Test
public void test20()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    props.setProperty("outputFormat", "text");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "testProcessFilesDir");
    tempDir.mkdir();
    tempDir.deleteOnExit();
    File tempFile = new File(tempDir, "test.txt");
    FileWriter writer = new FileWriter(tempFile);
    writer.write("Stanford CoreNLP testing.");
    writer.close();
    tempFile.deleteOnExit();
    Collection<File> files = Arrays.asList(tempFile);
    pipeline.processFiles(tempDir.getAbsolutePath(), files, 1, true, Optional.empty());
}

@Test
public void test21()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.run();
}

@Test
public void test22()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("This is a test.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.xmlPrint(annotation, writer);
    String output = writer.toString();
    assertTrue("Output should contain XML content", output.contains("<?xml"));
}

@Test
public void test23()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation = new Annotation("Stanford NLP is great.");
    pipeline.annotate(annotation);
    StringWriter writer = new StringWriter();
    pipeline.xmlPrint(annotation, writer);
    String xmlOutput = writer.toString();
    assertTrue(xmlOutput.contains("Stanford"));
    assertTrue(xmlOutput.contains("token"));
    assertTrue(xmlOutput.startsWith("<?xml"));
}

@Test
public void test24()
{
    Properties properties = new Properties();
    properties.setProperty("annotators", "tokenize,ssplit,pos,cdc_tokenize,lemma");
    String oldAnnotator = "cdc_tokenize";
    String newAnnotator = "tokenize";
    StanfordCoreNLP.replaceAnnotator(properties, oldAnnotator, newAnnotator);
    String updatedAnnotators = properties.getProperty("annotators");
    assertEquals("tokenize,ssplit,pos,tokenize,lemma", updatedAnnotators);
}

@Test
public void test25()
{
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,cleanxml,sentiment");
    StanfordCoreNLP.unifyTokenizeProperty(props, "cleanxml", "tokenize.cleanxml");
    assertEquals("tokenize,sentiment", props.getProperty("annotators"));
    assertEquals("true", props.getProperty("tokenize.cleanxml"));
}


