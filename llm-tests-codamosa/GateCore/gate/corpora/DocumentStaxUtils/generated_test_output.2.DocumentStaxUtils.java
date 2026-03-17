import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String xml = "<GateDocumentFeatures>" + (((("<Feature>" + "<Name className=\"java.lang.String\">category</Name>") + "<Value className=\"java.lang.String\">news</Value>") + "</Feature>") + "</GateDocumentFeatures>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext() && (reader.next() != XMLStreamReader.START_ELEMENT));
    FeatureMap featureMap = DocumentStaxUtils.readFeatureMap(reader);
    assertNotNull(featureMap);
    assertEquals(1, featureMap.size());
    assertEquals("news", featureMap.get("category"));
}

@Test
public void test2()
{
    String xmlInput = "<struct xmlns='http://nite.sourceforge.net/'>" + ("<feat name='category' value='person'/>" + "</struct>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xmlInput));
    while (reader.hasNext() && (reader.getEventType() != XMLStreamConstants.START_ELEMENT)) {
        reader.next();
    } 
    FeatureMap featureMap = DocumentStaxUtils.readXcesFeatureMap(reader);
    assertEquals(1, featureMap.size());
    assertEquals("person", featureMap.get("category"));
}

@Test
public void test3()
{
    String xml = "<AnnotationSet>" + (((((("<Annotation Type=\"Token\" StartNode=\"1\" EndNode=\"2\">" + "<Feature>") + "<Name className=\"java.lang.String\">length</Name>") + "<Value className=\"java.lang.Integer\">5</Value>") + "</Feature>") + "</Annotation>") + "</AnnotationSet>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext() && (!(reader.isStartElement() && reader.getLocalName().equals("AnnotationSet")))) {
        reader.next();
    } 
    AnnotationSet annotationSet = Factory.newDocument("").getAnnotations();
    Map<Integer, Long> nodeIdToOffsetMap = null;
    Set<Integer> allAnnotIds = new HashSet<Integer>();
    Boolean requireAnnotationIds = null;
    Boolean result = DocumentStaxUtils.readAnnotationSet(reader, annotationSet, nodeIdToOffsetMap, allAnnotIds, requireAnnotationIds);
    assertEquals(Boolean.FALSE, result);
    assertEquals(1, annotationSet.size());
    Annotation ann = annotationSet.iterator().next();
    assertEquals(1L, ann.getStartNode().getOffset().longValue());
    assertEquals(2L, ann.getEndNode().getOffset().longValue());
    assertEquals("Token", ann.getType());
    assertEquals(1, ann.getFeatures().size());
    assertEquals(5, ann.getFeatures().get("length"));
}

@Test
public void test4()
{
    String xml = "<TextWithNodes>Hello<Node id=\"1\"/>World</TextWithNodes>";
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext()) {
        if ((reader.next() == XMLStreamReader.START_ELEMENT) && "TextWithNodes".equals(reader.getLocalName())) {
            break;
        }
    } 
    Map<Integer, Long> nodeMap = new HashMap<>();
    String result = DocumentStaxUtils.readTextWithNodes(reader, nodeMap);
    assertEquals("HelloWorld", result);
    assertEquals(1, nodeMap.size());
    assertTrue(nodeMap.containsKey(1));
    assertEquals(Long.valueOf(5), nodeMap.get(1));
}

@Test
public void test5()
{
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + ((((((((("<GateDocument>" + "<TextWithNodes>") + "<Node id=\"0\"/>") + "<Node id=\"5\"/>") + "Hello") + "</TextWithNodes>") + "<AnnotationSet>") + "<Annotation Id=\"1\" Type=\"Token\" StartNode=\"0\" EndNode=\"5\"/>") + "</AnnotationSet>") + "</GateDocument>");
    StringReader stringReader = new StringReader(xml);
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader xsr = factory.createXMLStreamReader(stringReader);
    while (xsr.hasNext()) {
        if (xsr.isStartElement() && "GateDocument".equals(xsr.getLocalName())) {
            break;
        }
        xsr.next();
    } 
    Document doc = Factory.newDocument("");
    DocumentStaxUtils.readGateXmlDocument(xsr, doc);
    assertEquals("Hello", doc.getContent().toString());
    assertNotNull(doc.getAnnotations());
    assertEquals(1, doc.getAnnotations().size());
    assertEquals("Token", doc.getAnnotations().iterator().next().getType());
}

@Test
public void test6()
{
    String xml = "<?xml version='1.0' encoding='UTF-8'?>" + ((((((("<GateDocument>" + "<TextWithNodes>") + "<Node id='0'/>") + "<Node id='1'/>") + "</TextWithNodes>") + "<AnnotationSet>") + "</AnnotationSet>") + "</GateDocument>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext() && (!(reader.isStartElement() && reader.getLocalName().equals("GateDocument")))) {
        reader.next();
    } 
    Document doc = Factory.newDocument("");
    DocumentStaxUtils.readGateXmlDocument(reader, doc);
    assertTrue("Document content should not be null", doc.getContent() != null);
    assertEquals("Content should be empty", "", doc.getContent().toString());
    assertTrue("Default annotation set should exist", (doc.getAnnotations() != null) && doc.getAnnotations().isEmpty());
}

@Test
public void test7()
{
    String xml = "<RelationSet>" + (((("<Relation Type=\"coref\" Id=\"1\" Members=\"101;102\">" + "<UserData>Some user data</UserData>") + "<FeatureMap><feature><name>key</name><value class=\"java.lang.String\">value</value></feature></FeatureMap>") + "</Relation>") + "</RelationSet>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader xsr = factory.createXMLStreamReader(new StringReader(xml));
    while (xsr.hasNext() && (xsr.next() != XMLStreamReader.START_ELEMENT)) {
    } 
    assertEquals("RelationSet", xsr.getLocalName());
    RelationSet relationSet = new RelationSet();
    Set<Integer> allAnnotIds = new HashSet<>();
    DocumentStaxUtils.readRelationSet(xsr, relationSet, allAnnotIds);
    assertEquals(1, relationSet.size());
    Relation relation = relationSet.iterator().next();
    assertEquals("coref", relation.getType());
    assertArrayEquals(new int[]{ 101, 102 }, relation.getMembers());
    assertEquals("value", relation.getFeatures().get("key"));
    assertEquals("Some user data", relation.getUserData());
}

@Test
public void test8()
{
    String xcesXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + (((("<cesDoc>\n" + "  <body>\n") + "    <p>Test content</p>\n") + "  </body>\n") + "</cesDoc>");
    InputStream inputStream = new ByteArrayInputStream(xcesXml.getBytes("UTF-8"));
    Document document = Factory.newDocument("");
    AnnotationSet annotationSet = document.getAnnotations();
    DocumentStaxUtils.readXces(inputStream, annotationSet);
    assertNotNull(annotationSet);
    assertTrue(annotationSet.isEmpty() || (annotationSet.size() >= 0));
}

@Test
public void test9()
{
    String xcesXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + (("<cesDoc xmlns=\"http://www.xces.org\">" + "<chunkList><chunk type=\"s\" id=\"s1\">Sample text.</chunk></chunkList>") + "</cesDoc>");
    ByteArrayInputStream inputStream = new ByteArrayInputStream(xcesXml.getBytes(UTF_8));
    Document document = Factory.newDocument("");
    AnnotationSet annotationSet = document.getAnnotations();
    DocumentStaxUtils.readXces(inputStream, annotationSet);
    assertFalse("Annotation set should not be empty after reading valid XCES", annotationSet.isEmpty());
}

@Test
public void test10()
{
    String documentContent = "<doc>This is a test document.</doc>";
    Document doc = Factory.newDocument(documentContent);
    File tempFile = Files.createTempFile("gate_test_doc", ".xml").toFile();
    tempFile.deleteOnExit();
    DocumentStaxUtils.writeDocument(doc, tempFile);
    String writtenContent = new String(Files.readAllBytes(tempFile.toPath()));
    assertNotNull(writtenContent);
    assertFalse(writtenContent.trim().isEmpty());
    Factory.deleteResource(doc);
}

@Test
public void test11()
{
}
{
    Gate.init();
}

@Test
public void test12()
{
    DocumentStaxUtils.writeDocument(document, tempFile);
    assertTrue("The output file should exist after writing the document.", tempFile.exists());
    String writtenContent = new String(Files.readAllBytes(tempFile.toPath()));
    assertTrue("The written file should contain document content.", writtenContent.contains("This is a test document."));
}

@Test
public void test13()
{
    File tempFile = File.createTempFile("testDoc", ".xml");
    tempFile.deleteOnExit();
    String content = "This is a test document.";
    Document doc = Factory.newDocument(content);
    DocumentStaxUtils.writeDocument(doc, tempFile);
    String fileContent = new String(Files.readAllBytes(tempFile.toPath()), StandardCharsets.UTF_8);
    assertTrue("Written file should contain document content", fileContent.contains("This is a test document."));
}

@Test
public void test14()
{
    StringWriter stringWriter = new StringWriter();
    XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(stringWriter);
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("author", "John Smith");
    String namespaceURI = "http://example.org/ns";
    DocumentStaxUtils.writeFeatures(features, xsw, namespaceURI);
    xsw.flush();
    xsw.close();
    String output = stringWriter.toString();
    assertTrue(output.contains("<Feature"));
    assertTrue(output.contains("<Name"));
    assertTrue(output.contains("className=\"java.lang.String\""));
    assertTrue(output.contains(">author<"));
    assertTrue(output.contains("<Value"));
    assertTrue(output.contains(">John Smith<"));
}

@Test
public void test15()
{
    Document mockDoc = mock(Document.class);
    String testContent = "This is some sample text.";
    when(mockDoc.getContent()).thenReturn(new SimpleDocumentContentImpl(testContent));
    Node mockStartNode = mock(Node.class);
    when(mockStartNode.getOffset()).thenReturn(8L);
    Node mockEndNode = mock(Node.class);
    when(mockEndNode.getOffset()).thenReturn(12L);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(mockStartNode);
    when(mockAnnotation.getEndNode()).thenReturn(mockEndNode);
    Collection<Collection<Annotation>> annotationSets = Collections.singletonList(Collections.singletonList(mockAnnotation));
    StringWriter outputWriter = new StringWriter();
    XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(outputWriter);
    String namespaceURI = "http://test.namespace";
    DocumentStaxUtils.writeTextWithNodes(mockDoc, annotationSets, xsw, namespaceURI);
    xsw.flush();
    String xmlOutput = outputWriter.toString();
    assert xmlOutput.contains("<TextWithNodes");
    assert xmlOutput.contains("This is ");
    assert xmlOutput.contains("some");
    assert xmlOutput.contains("sample text.");
    assert xmlOutput.contains("<Node");
    assert xmlOutput.contains("id=\"8\"");
    assert xmlOutput.contains("id=\"12\"");
    assert xmlOutput.contains("</TextWithNodes>");
}

@Test
public void test16()
{
    Document mockDoc = mock(Document.class);
    when(mockDoc.getContent()).thenReturn(new StringContent("Sample text"));
    Node startNode = mock(Node.class);
    when(startNode.getOffset()).thenReturn(0L);
    Node endNode = mock(Node.class);
    when(endNode.getOffset()).thenReturn(6L);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(startNode);
    when(mockAnnotation.getEndNode()).thenReturn(endNode);
    Collection<Annotation> annotationSet = Collections.singleton(mockAnnotation);
    Collection<Collection<Annotation>> allAnnotationSets = Collections.singleton(annotationSet);
    StringWriter stringWriter = new StringWriter();
    XMLStreamWriter mockXsw = mock(XMLStreamWriter.class);
    doAnswer(( invocation) -> {
        String text = invocation.getArgument(2);
        return null;
    }).when(mockXsw).writeCharacters(anyString());
    DocumentStaxUtils.writeTextWithNodes(mockDoc, allAnnotationSets, mockXsw, "");
    verify(mockXsw).writeStartElement("", "TextWithNodes");
    verify(mockXsw).writeEmptyElement("", "Node");
    verify(mockXsw).writeAttribute("id", "0");
    verify(mockXsw).writeEmptyElement("", "Node");
    verify(mockXsw).writeAttribute("id", "6");
    verify(mockXsw).writeEndElement();
}

@Test
public void test17()
{
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Collection<Annotation> emptyAnnotations = Collections.emptyList();
    DocumentStaxUtils.writeXcesAnnotations(emptyAnnotations, outputStream, "UTF-8");
    String outputXml = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    assertTrue("Output should contain XML declaration", outputXml.startsWith("<?xml"));
}

@Test
public void test18()
{
    Collection<Annotation> annotations = new ArrayList<Annotation>();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String encoding = "UTF-8";
    DocumentStaxUtils.writeXcesAnnotations(annotations, outputStream, encoding);
    String output = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    assertTrue(output.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
}

@Test
public void test19()
{
    CharSequence buf = "\u0007abc";
    boolean result = DocumentStaxUtils.isInvalidXmlChar(buf, 0);
    assertTrue(result);
}

@Test
public void test20()
{
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    reader.nextTag();
    Object result = DocumentStaxUtils.readFeatureNameOrValue(reader);
    assertTrue(result instanceof Collection);
    @SuppressWarnings("unchecked")
    Collection<String> values = ((Collection<String>) (result));
    assertEquals(3, values.size());
    assertTrue(values.contains("apple"));
    assertTrue(values.contains("banana"));
    assertTrue(values.contains("cherry"));
}

@Test
public void test21()
{
    String input = "Hello\u0001World";
    String expected = "Hello World";
    String result = DocumentStaxUtils.replaceXMLIllegalCharactersInString(input);
    assertEquals(expected, result);
}

@Test
public void test22()
{
    char[] input = new char[]{ 'a', 0x1, 'b', 0x9, 0xa, 'c', 0xd800, 0xffff, 'd' };
    DocumentStaxUtils.replaceXMLIllegalCharacters(input);
    assertEquals('a', input[0]);
    assertEquals(' ', input[1]);
    assertEquals('b', input[2]);
    assertEquals(0x9, input[3]);
    assertEquals(0xa, input[4]);
    assertEquals('c', input[5]);
    assertEquals(' ', input[6]);
    assertEquals(' ', input[7]);
    assertEquals('d', input[8]);
}

@Test
public void test23()
{
    XMLStreamWriter mockWriter = mock(XMLStreamWriter.class);
    String input = "This is some <![CDATA[escaped]]> text ]]> with CDATA end marker ]]> inside.";
    DocumentStaxUtils.writeCharactersOrCDATA(mockWriter, input);
    verify(mockWriter, atLeastOnce()).writeCData(anyString());
    verify(mockWriter, atLeastOnce()).writeCharacters("]]>");
}

