import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String xml = "<GateDocumentFeatures>" + (((("<Feature>" + "<Name className=\"java.lang.String\">feature1</Name>") + "<Value className=\"java.lang.String\">value1</Value>") + "</Feature>") + "</GateDocumentFeatures>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext() && (reader.next() != XMLStreamReader.START_ELEMENT));
    FeatureMap featureMap = DocumentStaxUtils.readFeatureMap(reader);
    assertEquals(1, featureMap.size());
    assertEquals("value1", featureMap.get("feature1"));
}

@Test
public void test2()
{
    String xml = "<struct xmlns='http://www.xces.org/2003/05'>" + ("<feat name='category' value='noun'/>" + "</struct>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader xsr = factory.createXMLStreamReader(new StringReader(xml));
    while (xsr.hasNext() && (!(xsr.isStartElement() && xsr.getLocalName().equals("struct")))) {
        xsr.next();
    } 
    FeatureMap result = DocumentStaxUtils.readXcesFeatureMap(xsr);
    assertEquals(1, result.size());
    assertEquals("noun", result.get("category"));
}

@Test
public void test3()
{
    String xml = "<AnnotationSet>" + ((("<Annotation Type=\"Person\" StartNode=\"1\" EndNode=\"2\" Id=\"100\">" + "<Feature><Name>gender</Name><Value>male</Value></Feature>") + "</Annotation>") + "</AnnotationSet>");
    XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));
    while (xsr.hasNext() && (!(xsr.isStartElement() && xsr.getLocalName().equals("AnnotationSet")))) {
        xsr.next();
    } 
    AnnotationSet mockSet = mock(AnnotationSet.class);
    Map<Integer, Long> nodeIdToOffsetMap = new HashMap<Integer, Long>();
    nodeIdToOffsetMap.put(1, 10L);
    nodeIdToOffsetMap.put(2, 20L);
    Set<Integer> allAnnotIds = new HashSet<Integer>();
    Boolean result = DocumentStaxUtils.readAnnotationSet(xsr, mockSet, nodeIdToOffsetMap, allAnnotIds, null);
    assertTrue(result);
    assertTrue(allAnnotIds.contains(100));
    verify(mockSet).add(eq(100), eq(10L), eq(20L), eq("Person"), any());
}

@Test
public void test4()
{
    String xml = "<TextWithNodes>" + ((("Hello " + "<Node id=\"1\"/>") + "world") + "</TextWithNodes>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext() && (reader.next() != XMLStreamReader.START_ELEMENT)) {
    } 
    Map<Integer, Long> nodeMap = new HashMap<>();
    String result = DocumentStaxUtils.readTextWithNodes(reader, nodeMap);
    assertEquals("Hello world", result);
    assertEquals(1, nodeMap.size());
    assertTrue(nodeMap.containsKey(1));
    assertEquals(Long.valueOf(6), nodeMap.get(1));
}

@Test
public void test5()
{
    String xml = "<GateDocument>" + ((((((((((("<TextWithNodes>" + "<Node id=\"0\"/>Hello<Node id=\"1\"/>") + "</TextWithNodes>") + "<AnnotationSet>") + "<Annotation Id=\"1\" Type=\"Token\" StartNode=\"0\" EndNode=\"1\">") + "<Feature>") + "<Name className=\"java.lang.String\">kind</Name>") + "<Value className=\"java.lang.String\">word</Value>") + "</Feature>") + "</Annotation>") + "</AnnotationSet>") + "</GateDocument>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext()) {
        if ((reader.next() == XMLStreamReader.START_ELEMENT) && "GateDocument".equals(reader.getLocalName())) {
            break;
        }
    } 
    Document doc = Factory.newDocument("");
    DocumentStaxUtils.readGateXmlDocument(reader, doc);
    Assert.assertEquals("Hello", doc.getContent().toString());
    Assert.assertEquals(1, doc.getAnnotations().size());
    Assert.assertEquals("word", doc.getAnnotations().iterator().next().getFeatures().get("kind"));
}

@Test
public void test6()
{
    String gateXml = "" + ((((((((((((("<GateDocument>" + "<TextWithNodes>") + "  <Node id='0'/>") + "  <Node id='5'/>") + "</TextWithNodes>") + "<AnnotationSet>") + "  <Annotation Id='1' Type='Entity' StartNode='0' EndNode='5'>") + "    <Feature>") + "      <Name className='java.lang.String'>kind</Name>") + "      <Value className='java.lang.String'>Person</Value>") + "    </Feature>") + "  </Annotation>") + "</AnnotationSet>") + "</GateDocument>");
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(gateXml));
    while (reader.hasNext()) {
        if (reader.isStartElement() && reader.getLocalName().equals("GateDocument")) {
            break;
        }
        reader.next();
    } 
    Document doc = new DocumentImpl();
    DocumentStaxUtils.readGateXmlDocument(reader, doc);
    assertNotNull(doc);
    assertEquals("Entity", doc.getAnnotations().get(0L).iterator().next().getType());
    assertEquals("Person", doc.getAnnotations().get(0L).iterator().next().getFeatures().get("kind"));
}

@Test
public void test7()
{
    String xml = "<Relations>" + (((("<Relation Type=\"typeX\" Id=\"7\" Members=\"11;22\">" + "<UserData></UserData>") + "<FeatureMap/>") + "</Relation>") + "</Relations>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    if ((reader.nextTag() != XMLStreamConstants.START_ELEMENT) || (!"Relations".equals(reader.getLocalName()))) {
        throw new IllegalStateException("Expected <Relations> element");
    }
    RelationSet relationSet = new RelationSetImpl();
    Set<Integer> allAnnotIds = new HashSet<>();
    DocumentStaxUtils.readRelationSet(reader, relationSet, allAnnotIds);
    assertEquals(1, relationSet.size());
    Relation rel = relationSet.iterator().next();
    assertEquals(7, rel.getId());
    assertEquals("typeX", rel.getType());
    assertArrayEquals(new int[]{ 11, 22 }, rel.getMembers());
    assertNull(rel.getUserData());
    assertTrue(rel.getFeatures().isEmpty());
}

@Test
public void test8()
{
    String xcesXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + (("<cesDoc>\n" + "  <text>Sample text</text>\n") + "</cesDoc>");
    ByteArrayInputStream inputStream = new ByteArrayInputStream(xcesXml.getBytes(UTF_8));
    AnnotationSet mockAnnotationSet = mock(AnnotationSet.class);
    DocumentStaxUtils.readXces(inputStream, mockAnnotationSet);
    assertNotNull(mockAnnotationSet);
}

@Test
public void test9()
{
    String xcesXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + ((("<cesDoc xmlns=\"http://www.xces.org/schema/2003\">" + "<cesHeader><fileDesc><titleStmt><title>Test</title></titleStmt></fileDesc></cesHeader>") + "<text><body><p>This is a test.</p></body></text>") + "</cesDoc>");
    ByteArrayInputStream inputStream = new ByteArrayInputStream(xcesXml.getBytes(UTF_8));
    AnnotationSet annotationSet = new AnnotationSetImpl(null, null);
    DocumentStaxUtils.readXces(inputStream, annotationSet);
    assertNotNull(annotationSet);
    assertFalse(annotationSet.isEmpty());
}

@Test
public void test10()
{
    Annotation mockAnnotation = mock(Annotation.class);
    AnnotationSet mockAnnotationSet = mock(AnnotationSet.class);
    when(mockAnnotationSet.getName()).thenReturn("OriginalMarkups");
    when(((Collection<Annotation>) (mockAnnotationSet))).thenReturn(Arrays.asList(mockAnnotation));
    StringWriter outputWriter = new StringWriter();
    XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(outputWriter);
    DocumentStaxUtils.writeAnnotationSet(mockAnnotationSet, xmlStreamWriter, "http://example.org/ns");
    xmlStreamWriter.flush();
    String resultXml = outputWriter.toString();
    assertTrue(resultXml.contains("OriginalMarkups"));
}

@Test
public void test11()
{
    File tempFile = File.createTempFile("gateDocTest", ".xml");
    tempFile.deleteOnExit();
    String documentContent = "This is a sample GATE document.";
    Document doc = Factory.newDocument(documentContent);
    DocumentStaxUtils.writeDocument(doc, tempFile);
    String writtenContent = new String(Files.readAllBytes(tempFile.toPath()), "UTF-8");
    assertTrue("Written file should contain the original document content or XML structure.", writtenContent.contains("This is a sample GATE document"));
    doc.cleanup();
}

@Test
public void test12()
{
    DocumentStaxUtils.writeDocument(testDocument, outputFile);
    assertTrue("Output file should exist after writing", outputFile.exists());
    assertTrue("Output file should not be empty", outputFile.length() > 0);
}

@Test
public void test13()
{
    Gate.init();
    String sampleText = "This is a test GATE document.";
    Document document = Factory.newDocument(sampleText);
    File tempFile = File.createTempFile("gatetest", ".xml");
    tempFile.deleteOnExit();
    DocumentStaxUtils.writeDocument(document, tempFile);
    BufferedReader reader = new BufferedReader(new FileReader(tempFile));
    StringBuilder fileContent = new StringBuilder();
    String line = reader.readLine();
    if (line != null) {
        fileContent.append(line);
    }
    reader.close();
    assertTrue("File should contain GATE document content", fileContent.toString().contains("This is a test GATE document."));
    Factory.deleteResource(document);
}

@Test
public void test14()
{
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("language", "English");
    StringWriter stringWriter = new StringWriter();
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    XMLStreamWriter xsw = outputFactory.createXMLStreamWriter(stringWriter);
    String namespaceURI = "http://example.com/ns";
    DocumentStaxUtils.writeFeatures(features, xsw, namespaceURI);
    xsw.flush();
    String resultXml = stringWriter.toString();
    assertTrue(resultXml.contains("<Feature"));
    assertTrue(resultXml.contains("<Name"));
    assertTrue(resultXml.contains("className=\"java.lang.String\""));
    assertTrue(resultXml.contains(">language<"));
    assertTrue(resultXml.contains("<Value"));
    assertTrue(resultXml.contains(">English<"));
}

@Test
public void test15()
{
    Document mockDoc = mock(Document.class);
    when(mockDoc.getContent()).thenReturn(new DocumentContentImpl("Hello world"));
    Annotation mockAnnotation = mock(Annotation.class);
    Node mockStartNode = mock(Node.class);
    when(mockStartNode.getOffset()).thenReturn(0L);
    Node mockEndNode = mock(Node.class);
    when(mockEndNode.getOffset()).thenReturn(5L);
    when(mockAnnotation.getStartNode()).thenReturn(mockStartNode);
    when(mockAnnotation.getEndNode()).thenReturn(mockEndNode);
    Collection<Collection<Annotation>> annotationSets = Collections.singletonList(Collections.singletonList(mockAnnotation));
    XMLStreamWriter mockWriter = mock(XMLStreamWriter.class);
    DocumentStaxUtils.writeTextWithNodes(mockDoc, annotationSets, mockWriter, "http://example.com");
    verify(mockWriter).writeStartElement("http://example.com", "TextWithNodes");
    verify(mockWriter).writeEmptyElement("http://example.com", "Node");
    verify(mockWriter).writeAttribute("id", "0");
    verify(mockWriter).writeEmptyElement("http://example.com", "Node");
    verify(mockWriter).writeAttribute("id", "5");
    verify(mockWriter).writeEndElement();
}

@Test
public void test16()
{
    Document mockDocument = mock(Document.class);
    when(mockDocument.getContent()).thenReturn(new StringBuilder("Hello world"));
    Node mockStartNode = mock(Node.class);
    Node mockEndNode = mock(Node.class);
    when(mockStartNode.getOffset()).thenReturn(0L);
    when(mockEndNode.getOffset()).thenReturn(5L);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(mockStartNode);
    when(mockAnnotation.getEndNode()).thenReturn(mockEndNode);
    Collection<Annotation> annotationSet = Collections.singleton(mockAnnotation);
    Collection<Collection<Annotation>> annotationSets = Collections.singleton(annotationSet);
    XMLStreamWriter mockWriter = mock(XMLStreamWriter.class);
    DocumentStaxUtils.writeTextWithNodes(mockDocument, annotationSets, mockWriter, "http://test.namespace");
    verify(mockWriter).writeStartElement("http://test.namespace", "TextWithNodes");
    verify(mockWriter).writeEmptyElement("http://test.namespace", "Node");
    verify(mockWriter).writeAttribute("id", "0");
    verify(mockWriter).writeEmptyElement("http://test.namespace", "Node");
    verify(mockWriter).writeAttribute("id", "5");
    verify(mockWriter).writeEndElement();
}

@Test
public void test17()
{
    Collection<Annotation> annotations = new ArrayList<>();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String encoding = "UTF-8";
    DocumentStaxUtils.writeXcesAnnotations(annotations, outputStream, encoding);
    String output = outputStream.toString(encoding);
    assertTrue("Output should contain XML declaration", output.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
}

@Test
public void test18()
{
    Collection<Annotation> annotations = Collections.emptyList();
    OutputStream os = new ByteArrayOutputStream();
    String encoding = "UTF-8";
    DocumentStaxUtils.writeXcesAnnotations(annotations, os, encoding);
    String xmlOutput = os.toString();
    assertTrue("Output should start with XML declaration", xmlOutput.startsWith("<?xml"));
}

@Test
public void test19()
{
    CharSequence input = "\u0007";
    boolean result = DocumentStaxUtils.isInvalidXmlChar(input, 0);
    assertTrue("Character 0x07 should be invalid in XML", result);
}

@Test
public void test20()
{
    String input = "Valid\u0001Text\u0003With\tIllegal\u000bChars";
    String expected = "Valid Text With\tIllegal Chars";
    String actual = DocumentStaxUtils.replaceXMLIllegalCharactersInString(input);
    assertEquals(expected, actual);
}

@Test
public void test21()
{
    XMLStreamWriter mockWriter = mock(XMLStreamWriter.class);
    String input = "Some text with CDATA end marker ]]> in the middle and another ]]> at end]]>";
    String xmlLikeInput = ("<a><b>" + input) + "</b></a>";
    DocumentStaxUtils.writeCharactersOrCDATA(mockWriter, xmlLikeInput);
    verify(mockWriter, atLeastOnce()).writeCData(anyString());
    verify(mockWriter, atLeastOnce()).writeCharacters("]]>");
}


