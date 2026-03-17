import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    String xml = "<GateDocumentFeatures>" + (((("<Feature>" + "<Name className=\"java.lang.String\">category</Name>") + "<Value className=\"java.lang.String\">news</Value>") + "</Feature>") + "</GateDocumentFeatures>");
    StringReader reader = new StringReader(xml);
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader xsr = factory.createXMLStreamReader(reader);
    while (xsr.hasNext() && (!(xsr.isStartElement() && "GateDocumentFeatures".equals(xsr.getLocalName())))) {
        xsr.next();
    } 
    FeatureMap featureMap = DocumentStaxUtils.readFeatureMap(xsr);
    assertNotNull(featureMap);
    assertEquals(1, featureMap.size());
    assertEquals("news", featureMap.get("category"));
}

@Test
public void test2()
{
    String xml = ((("<struct xmlns=\"" + DocumentStaxUtils.XCES_NAMESPACE) + "\">") + "<feat name=\"category\" value=\"noun\"/>") + "</struct>";
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext() && (!(reader.isStartElement() && "struct".equals(reader.getLocalName())))) {
        reader.next();
    } 
    FeatureMap featureMap = DocumentStaxUtils.readXcesFeatureMap(reader);
    assertEquals(1, featureMap.size());
    assertEquals("noun", featureMap.get("category"));
}

@Test
public void test3()
{
    String xml = "<AnnotationSet>" + ((("<Annotation Id=\"1\" Type=\"Person\" StartNode=\"10\" EndNode=\"20\">" + "<Feature><Name>gender</Name><Value>male</Value></Feature>") + "</Annotation>") + "</AnnotationSet>");
    XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));
    while (xsr.hasNext() && (xsr.next() != XMLStreamReader.START_ELEMENT));
    Map<Integer, Long> nodeIdToOffset = new HashMap<Integer, Long>();
    nodeIdToOffset.put(10, 100L);
    nodeIdToOffset.put(20, 200L);
    Set<Integer> allAnnotIds = new HashSet<Integer>();
    AnnotationSet annotationSet = new AnnotationSetImpl(((Document) (null)), "TempSet", null);
    Boolean requireIds = null;
    Boolean result = DocumentStaxUtils.readAnnotationSet(xsr, annotationSet, nodeIdToOffset, allAnnotIds, requireIds);
    assertEquals(Boolean.TRUE, result);
    assertEquals(1, annotationSet.size());
    assertTrue(allAnnotIds.contains(1));
    assertNotNull(annotationSet.get(1));
    assertEquals(100L, annotationSet.get(1).firstNode().getOffset().longValue());
    assertEquals(200L, annotationSet.get(1).lastNode().getOffset().longValue());
    assertEquals("Person", annotationSet.get(1).getType());
    assertEquals("male", annotationSet.get(1).getFeatures().get("gender"));
}

@Test
public void test4()
{
    String xml = "<TextWithNodes>Hello<Node id='1'/>World<Node id='2'/>!</TextWithNodes>";
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    while (reader.hasNext()) {
        if ((reader.next() == XMLStreamConstants.START_ELEMENT) && "TextWithNodes".equals(reader.getLocalName())) {
            break;
        }
    } 
    Map<Integer, Long> nodeIdToOffsetMap = new HashMap<>();
    String result = DocumentStaxUtils.readTextWithNodes(reader, nodeIdToOffsetMap);
    assertEquals("HelloWorld!", result);
    assertEquals(Long.valueOf(5), nodeIdToOffsetMap.get(1));
    assertEquals(Long.valueOf(10), nodeIdToOffsetMap.get(2));
    assertEquals(2, nodeIdToOffsetMap.size());
}

@Test
public void test5()
{
    String content = "This is a test document.";
    Document doc = Factory.newDocument(content);
    if (doc instanceof TextualDocument) {
        ((TextualDocument) (doc)).setEncoding("UTF-8");
    }
    String xmlOutput = DocumentStaxUtils.toXml(doc);
    assertNotNull(xmlOutput);
    assertTrue(xmlOutput.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>") || xmlOutput.contains("<?xml version=\"1.0\"?>"));
    assertTrue(xmlOutput.contains("This is a test document."));
    Factory.deleteResource(doc);
}

@Test
public void test6()
{
    String gateXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + (((((((((((((("<GateDocument>\n" + "  <TextWithNodes>\n") + "    <Node id=\"0\" />\n") + "    Sample content\n") + "    <Node id=\"17\" />\n") + "  </TextWithNodes>\n") + "  <AnnotationSet>\n") + "    <Annotation Id=\"1\" Type=\"Token\" StartNode=\"0\" EndNode=\"6\">\n") + "      <Feature>\n") + "        <Name className=\"java.lang.String\">kind</Name>\n") + "        <Value className=\"java.lang.String\">word</Value>\n") + "      </Feature>\n") + "    </Annotation>\n") + "  </AnnotationSet>\n") + "</GateDocument>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(gateXml));
    while (reader.hasNext()) {
        if ((reader.next() == XMLStreamReader.START_ELEMENT) && "GateDocument".equals(reader.getLocalName())) {
            break;
        }
    } 
    Document doc = new DocumentContentImpl("initial content");
    DocumentStaxUtils.readGateXmlDocument(reader, doc);
    assertEquals("Sample content", doc.getContent().toString().trim());
    assertNotNull(doc.getAnnotations());
    assertEquals(1, doc.getAnnotations().size());
    assertEquals("Token", doc.getAnnotations().iterator().next().getType());
}

@Test
public void test7()
{
    String gateXml = "<?xml version=\'1.0\' encoding=\'UTF-8\'?>\n" + ((((((("<GateDocument>\n" + "  <TextWithNodes>\n") + "    <Text><![CDATA[Hello World]]></Text>\n") + "  </TextWithNodes>\n") + "  <AnnotationSet>\n") + "    <Annotation Id=\"0\" Type=\"Greeting\" StartNode=\"0\" EndNode=\"5\"/>\n") + "  </AnnotationSet>\n") + "</GateDocument>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader xsr = factory.createXMLStreamReader(new StringReader(gateXml));
    while (xsr.hasNext() && (!(xsr.isStartElement() && "GateDocument".equals(xsr.getLocalName())))) {
        xsr.next();
    } 
    Document doc = Factory.newDocument("Dummy");
    DocumentStaxUtils.readGateXmlDocument(xsr, doc);
    assertEquals("Hello World", doc.getContent().toString());
    assertEquals(1, doc.getAnnotations().size());
    assertEquals("Greeting", doc.getAnnotations().get(0L).iterator().next().getType());
    Factory.deleteResource(doc);
}

@Test
public void test8()
{
    String xml = "<Relations>" + ((((((((("<Relation Type=\"co-reference\" Id=\"101\" Members=\"1;2\">" + "<UserData>Some user data</UserData>") + "<FeatureSet>") + "<Feature>") + "<Name>confidence</Name>") + "<Value className=\"java.lang.Double\">0.95</Value>") + "</Feature>") + "</FeatureSet>") + "</Relation>") + "</Relations>");
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader xsr = factory.createXMLStreamReader(new StringReader(xml));
    while (xsr.hasNext() && (xsr.next() != XMLStreamReader.START_ELEMENT)) {
    } 
    RelationSet relations = new RelationSet();
    Set<Integer> allAnnotIds = new HashSet<>();
    allAnnotIds.add(1);
    allAnnotIds.add(2);
    DocumentStaxUtils.readRelationSet(xsr, relations, allAnnotIds);
    assertEquals(1, relations.size());
    SimpleRelation relation = ((SimpleRelation) (relations.iterator().next()));
    assertEquals(101, relation.getId());
    assertEquals("co-reference", relation.getType());
    assertArrayEquals(new int[]{ 1, 2 }, relation.getMembers());
    assertEquals("Some user data", relation.getUserData());
    assertNotNull(relation.getFeatures());
    assertEquals(1, relation.getFeatures().size());
    assertEquals(0.95, ((Double) (relation.getFeatures().get("confidence"))), 0.001);
}

@Test
public void test9()
{
    String xcesXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + (((("<cesDoc xmlns=\"http://www.xces.org/\">\n" + "  <body>\n") + "    <seg id=\"s1\">This is a test sentence.</seg>\n") + "  </body>\n") + "</cesDoc>");
    InputStream inputStream = new ByteArrayInputStream(xcesXml.getBytes("UTF-8"));
    Document dummyDoc = Factory.newDocument("");
    AnnotationSet annotationSet = dummyDoc.getAnnotations();
    DocumentStaxUtils.readXces(inputStream, annotationSet);
    assertNotNull("AnnotationSet should not be null", annotationSet);
    assertTrue("AnnotationSet should contain annotations after reading XCES", annotationSet.size() > 0);
}

@Test
public void test10()
{
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + (((("<cesDoc>\n" + "  <body>\n") + "    <p>This is a test paragraph.</p>\n") + "  </body>\n") + "</cesDoc>");
    InputStream inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
    AnnotationSet annotationSet = mock(AnnotationSet.class);
    DocumentStaxUtils.readXces(inputStream, annotationSet);
}

@Test
public void test11()
{
    AnnotationSet mockAnnotationSet = mock(AnnotationSet.class);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotationSet.getName()).thenReturn("TestSet");
    when(mockAnnotationSet.iterator()).thenReturn(Arrays.asList(mockAnnotation).iterator());
    when(mockAnnotationSet.size()).thenReturn(1);
    when(mockAnnotationSet.toArray()).thenReturn(new Object[]{ mockAnnotation });
    @SuppressWarnings("unchecked")
    Collection<Annotation> castedAnnotations = ((Collection<Annotation>) ((Collection<?>) (Arrays.asList(mockAnnotation))));
    XMLStreamWriter mockXsw = mock(XMLStreamWriter.class);
    DocumentStaxUtils.writeAnnotationSet(mockAnnotationSet, mockXsw, "http://example.com/ns");
    verify(mockAnnotationSet, times(1)).getName();
}

@Test
public void test12()
{
    Annotation annotation = mock(Annotation.class);
    Collection<Annotation> annotationCollection = Arrays.asList(annotation);
    AnnotationSet annotationSet = mock(AnnotationSet.class);
    when(annotationSet.getName()).thenReturn("TestSet");
    when(((Collection<Annotation>) (annotationSet))).thenReturn(annotationCollection);
    StringWriter stringWriter = new StringWriter();
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter xmlStreamWriter = factory.createXMLStreamWriter(stringWriter);
    DocumentStaxUtils.writeAnnotationSet(annotationSet, xmlStreamWriter, "http://test.namespace");
    xmlStreamWriter.flush();
    xmlStreamWriter.close();
    String output = stringWriter.toString();
    assertNotNull(output);
}

@Test
public void test13()
{
    Document doc = Factory.newDocument("Sample test content");
    DocumentStaxUtils.writeDocument(doc, tempFile);
    assertTrue("Output file should exist after writing the document", tempFile.exists());
    String content = new String(Files.readAllBytes(tempFile.toPath()), "UTF-8");
    assertTrue("File should contain the original document content", content.contains("Sample test content"));
    Factory.deleteResource(doc);
}

@Test
public void test14()
{
    Gate.init();
    String content = "This is a test document.";
    Document doc = Factory.newDocument(content);
    File tempFile = File.createTempFile("gate_test_doc", ".xml");
    tempFile.deleteOnExit();
    DocumentStaxUtils.writeDocument(doc, tempFile);
    String fileContent = new String(Files.readAllBytes(tempFile.toPath()), StandardCharsets.UTF_8);
    assertTrue("File should contain the original document content", fileContent.contains("This is a test document."));
    Factory.deleteResource(doc);
}

@Test
public void test15()
{
    Document doc = Factory.newDocument("This is a test document.");
    DocumentStaxUtils.writeDocument(doc, tempFile);
    assertTrue("Output file should exist after writing document", tempFile.exists() && (tempFile.length() > 0));
    doc.cleanup();
}

@Test
public void test16()
{
    Document document = Factory.newDocument("This is a test document.");
    DocumentStaxUtils.writeDocument(document, tempFile);
    assertTrue("Output file should exist after writing the document", tempFile.exists() && (Files.size(tempFile.toPath()) > 0));
    Factory.deleteResource(document);
}

@Test
public void test17()
{
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("author", "Alice");
    StringWriter stringWriter = new StringWriter();
    XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(stringWriter);
    xsw.writeStartDocument();
    xsw.writeStartElement("http://example.com/ns", "GateDocumentFeatures");
    DocumentStaxUtils.writeFeatures(features, xsw, "http://example.com/ns");
    xsw.writeEndElement();
    xsw.writeEndDocument();
    xsw.flush();
    xsw.close();
    String xmlOutput = stringWriter.toString();
    assertTrue(xmlOutput.contains("<Feature"));
    assertTrue(xmlOutput.contains("<Name className=\"java.lang.String\">author</Name>"));
    assertTrue(xmlOutput.contains("<Value className=\"java.lang.String\">Alice</Value>"));
}

@Test
public void test18()
{
    String sampleText = "Hello World";
    Document mockDoc = mock(Document.class);
    when(mockDoc.getContent()).thenReturn(new DocumentContentImpl(sampleText));
    Node startNode = mock(Node.class);
    when(startNode.getOffset()).thenReturn(0L);
    Node endNode = mock(Node.class);
    when(endNode.getOffset()).thenReturn(5L);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(startNode);
    when(mockAnnotation.getEndNode()).thenReturn(endNode);
    Collection<Collection<Annotation>> annotationSets = Collections.singleton(Collections.singleton(mockAnnotation));
    StringWriter stringWriter = new StringWriter();
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter xsw = factory.createXMLStreamWriter(stringWriter);
    String namespaceURI = "http://testnamespace";
    DocumentStaxUtils.writeTextWithNodes(mockDoc, annotationSets, xsw, namespaceURI);
    xsw.flush();
    String outputXml = stringWriter.toString();
    assertTrue(outputXml.contains("TextWithNodes"));
    assertTrue(outputXml.contains("Node"));
    assertTrue(outputXml.contains("id=\"0\""));
    assertTrue(outputXml.contains("id=\"5\""));
    assertTrue(outputXml.contains("Hello"));
    assertTrue(outputXml.contains("World"));
}

@Test
public void test19()
{
    Document document = mock(Document.class);
    when(document.getContent()).thenReturn(new SimpleDocumentContent("Hello World"));
    Node startNode = mock(Node.class);
    Node endNode = mock(Node.class);
    when(startNode.getOffset()).thenReturn(0L);
    when(endNode.getOffset()).thenReturn(5L);
    Annotation annotation = mock(Annotation.class);
    when(annotation.getStartNode()).thenReturn(startNode);
    when(annotation.getEndNode()).thenReturn(endNode);
    Collection<Annotation> annotationSet = Collections.singletonList(annotation);
    Collection<Collection<Annotation>> allAnnotationSets = Collections.singletonList(annotationSet);
    XMLStreamWriter writer = mock(XMLStreamWriter.class);
    DocumentStaxUtils.writeTextWithNodes(document, allAnnotationSets, writer, "");
    verify(writer).writeStartElement("", "TextWithNodes");
    verify(writer).writeCharacters("Hello");
    verify(writer).writeEmptyElement("", "Node");
    verify(writer).writeAttribute("id", "0");
    verify(writer).writeCharacters(" ");
    verify(writer).writeEmptyElement("", "Node");
    verify(writer).writeAttribute("id", "5");
    verify(writer).writeCharacters("World");
    verify(writer).writeEndElement();
}

@Test
public void test20()
{
    Collection<Annotation> annotations = new ArrayList<Annotation>();
    OutputStream os = new ByteArrayOutputStream();
    String encoding = "UTF-8";
    DocumentStaxUtils.writeXcesAnnotations(annotations, os, encoding);
    String output = os.toString();
    assertTrue("Output should start with XML declaration", output.startsWith("<?xml"));
}

@Test
public void test21()
{
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentStaxUtils.writeXcesAnnotations(Collections.emptyList(), outputStream, "UTF-8");
    byte[] xmlBytes = outputStream.toByteArray();
    assertNotNull(xmlBytes);
    assertTrue(xmlBytes.length > 0);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlBytes);
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream, "UTF-8");
    boolean foundStartDocument = false;
    while (reader.hasNext()) {
        int event = reader.next();
        if (event == XMLStreamConstants.START_DOCUMENT) {
            foundStartDocument = true;
            break;
        }
    } 
    assertTrue("Expected START_DOCUMENT in XML output", foundStartDocument);
    reader.close();
}

@Test
public void test22()
{
    CharSequence input = "\u0007A";
    boolean result = DocumentStaxUtils.isInvalidXmlChar(input, 0);
    assertTrue("Character \\u0007 should be considered invalid in XML", result);
}

@Test
public void test23()
{
    String xml = "<feature className=\"java.lang.String\">Example Feature Value</feature>";
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
    reader.nextTag();
    Object result = DocumentStaxUtils.readFeatureNameOrValue(reader);
    assertTrue(result instanceof String);
    assertEquals("Example Feature Value", result);
}

@Test
public void test24()
{
    String input = "Valid\u0001Text\u000bWith\u0000Controls";
    String expected = "Valid Text With Controls";
    String result = DocumentStaxUtils.replaceXMLIllegalCharactersInString(input);
    assertEquals(expected, result);
}

@Test
public void test25()
{
    XMLStreamWriter mockWriter = mock(XMLStreamWriter.class);
    String input = "Text with CDATA end ]]> and more text";
    DocumentStaxUtils.CDATA_END_PATTERN = Pattern.compile("]]>");
    DocumentStaxUtils.writeCharactersOrCDATA(mockWriter, input);
    verify(mockWriter).writeCData("Text with CDATA end ");
    verify(mockWriter).writeCharacters("]]>");
    verify(mockWriter).writeCData(" and more text");
    verifyNoMoreInteractions(mockWriter);
}

