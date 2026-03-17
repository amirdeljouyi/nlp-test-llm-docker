import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    DocumentImpl document = new DocumentImpl();
    URL testUrl = new URL("http://example.com/doc");
    document.sourceUrl = testUrl;
    document.sourceUrlStartOffset = 10L;
    document.sourceUrlEndOffset = 20L;
    String expected = "http://example.com/doc1020";
    String actual = document.getOrderingString();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    DocumentImpl document = new DocumentImpl();
    Long start = 10L;
    Long end = 20L;
    boolean result = document.isValidOffsetRange(start, end);
    assertTrue("Expected valid range when start=10 and end=20", result);
}

@Test
public void test3()
{
    DocumentImpl document = new DocumentImpl();
    final boolean[] eventFired = new boolean[]{ false };
    document.addDocumentListener(new DocumentListener() {
        @Override
        public void annotationSetAdded(DocumentEvent e) {
            if ((e.getType() == DocumentEvent.ANNOTATION_SET_ADDED) && "".equals(e.getAnnotationSetName())) {
                eventFired[0] = true;
            }
        }

        @Override
        public void annotationSetRemoved(DocumentEvent e) {
        }

        @Override
        public void contentModified(DocumentEvent e) {
        }

        @Override
        public void documentReset(DocumentEvent e) {
        }

        @Override
        public void nameChanged(DocumentEvent e) {
        }

        @Override
        public void featuresChanged(DocumentEvent e) {
        }

        @Override
        public void annotationSetRenamed(DocumentEvent e) {
        }
    });
    AnnotationSet annotations = document.getAnnotations();
    assertNotNull("Default annotation set should not be null", annotations);
    assertTrue("Default annotation set should be instance of AnnotationSetImpl", annotations instanceof AnnotationSetImpl);
    assertTrue("Event for annotation set added should be fired", eventFired[0]);
}

@Test
public void test4()
{
    DocumentImpl document = new DocumentImpl();
    DocumentListener mockListener = mock(DocumentListener.class);
    document.addDocumentListener(mockListener);
    AnnotationSet annotations = document.getAnnotations();
    assertNotNull("Annotation set should not be null", annotations);
    assertEquals("Annotation set name should be empty string", "", annotations.getName());
    AnnotationSet annotationsSecondCall = document.getAnnotations();
    assertSame("Subsequent calls should return the same annotation set", annotations, annotationsSecondCall);
    verify(mockListener, times(1)).annotationSetAdded(any(DocumentEvent.class));
}

@Test
public void test5()
{
    DocumentImpl document = new DocumentImpl();
    DocumentContent mockContent = new DocumentContent() {
        @Override
        public String toString() {
            return "Sample content";
        }
    };
    Field contentField = DocumentImpl.class.getDeclaredField("content");
    contentField.setAccessible(true);
    contentField.set(document, mockContent);
    DocumentContent result = document.getContent();
    assertNotNull(result);
    assertEquals("Sample content", result.toString());
}

@Test
public void test6()
{
    DocumentImpl document = new DocumentImpl();
    FeatureMap returnedFeatures = document.getFeatures();
    assertNotNull("Features map should not be null", returnedFeatures);
    assertTrue("Returned FeatureMap should be instance of SimpleFeatureMapImpl", returnedFeatures instanceof SimpleFeatureMapImpl);
    assertEquals("New FeatureMap should be empty", 0, returnedFeatures.size());
}

@Test
public void test7()
{
    DocumentImpl document = new DocumentImpl();
    String testContent = "Sample text content for testing.";
    Field stringContentField = DocumentImpl.class.getDeclaredField("stringContent");
    stringContentField.setAccessible(true);
    stringContentField.set(document, testContent);
    Resource result = document.init();
    assertNotNull("Returned resource should not be null", result);
    assertTrue("Result should be instance of DocumentImpl", result instanceof DocumentImpl);
    assertNotNull("Document content should not be null", document.getContent());
    assertEquals("Content should match input string", testContent, document.getContent().toString());
    FeatureMap features = document.getFeatures();
    assertTrue("Features should contain 'gate.SourceURL' key", features.containsKey("gate.SourceURL"));
    assertEquals("Feature 'gate.SourceURL' should be set correctly", "created from String", features.get("gate.SourceURL"));
}

@Test
public void test8()
{
    DocumentImpl doc1 = new DocumentImpl();
    DocumentImpl doc2 = new DocumentImpl();
    Field urlField = DocumentImpl.class.getDeclaredField("sourceUrl");
    urlField.setAccessible(true);
    urlField.set(doc1, new URL("http://example.com/a"));
    urlField.set(doc2, new URL("http://example.com/b"));
    Field offsetField = DocumentImpl.class.getDeclaredField("sourceUrlStartOffset");
    offsetField.setAccessible(true);
    offsetField.set(doc1, null);
    offsetField.set(doc2, null);
    int result = doc1.compareTo(doc2);
    assertTrue("Expected doc1 to be less than doc2", result < 0);
}

@Test
public void test9()
{
    DocumentImpl document = new DocumentImpl();
    Field field = null;
    try {
        field = DocumentImpl.class.getDeclaredField("collectRepositioningInfo");
        field.setAccessible(true);
        field.set(document, Boolean.TRUE);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Boolean result = document.getCollectRepositioningInfo();
    assertTrue(result);
}

@Test
public void test10()
{
    DocumentImpl document = new DocumentImpl();
    Field field;
    try {
        field = DocumentImpl.class.getDeclaredField("preserveOriginalContent");
        field.setAccessible(true);
        field.set(document, Boolean.TRUE);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Reflection failed to set private field: " + e.getMessage());
        return;
    }
    Boolean result = document.getPreserveOriginalContent();
    assertTrue("Expected preserveOriginalContent to be TRUE", result);
}

@Test
public void test11()
{
    DocumentImpl doc = new DocumentImpl();
    Integer firstId = doc.getNextAnnotationId();
    Integer secondId = doc.getNextAnnotationId();
    Assert.assertNotNull("First ID should not be null", firstId);
    Assert.assertNotNull("Second ID should not be null", secondId);
    Assert.assertEquals("Second ID should be exactly one greater than first ID", ((Integer) (firstId + 1)), secondId);
}

@Test
public void test12()
{
    DocumentImpl document = new DocumentImpl();
    Integer firstId = document.getNextNodeId();
    Integer secondId = document.getNextNodeId();
    assertEquals(Integer.valueOf(firstId + 1), secondId);
}

@Test
public void test13()
{
    DocumentImpl document = new DocumentImpl();
    Integer expectedId = 42;
    try {
        Field field = DocumentImpl.class.getDeclaredField("nextAnnotationId");
        field.setAccessible(true);
        field.set(document, expectedId);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set nextAnnotationId via reflection: " + e.getMessage());
    }
    Integer actualId = document.peakAtNextAnnotationId();
    assertEquals("The method should return the current nextAnnotationId without modifying it.", expectedId, actualId);
}

@Test
public void test14()
{
    DocumentImpl document = new DocumentImpl();
    Long expectedOffset = 12345L;
    Field field = DocumentImpl.class.getDeclaredField("sourceUrlEndOffset");
    field.setAccessible(true);
    field.set(document, expectedOffset);
    Long actualOffset = document.getSourceUrlEndOffset();
    assertEquals(expectedOffset, actualOffset);
}

@Test
public void test15()
{
    DocumentImpl document = new DocumentImpl();
    Long expectedOffset = 12345L;
    try {
        Field field = DocumentImpl.class.getDeclaredField("sourceUrlStartOffset");
        field.setAccessible(true);
        field.set(document, expectedOffset);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set up test: " + e.getMessage());
    }
    Long actualOffset = document.getSourceUrlStartOffset();
    assertEquals(expectedOffset, actualOffset);
}

@Test
public void test16()
{
    DocumentImpl document = new DocumentImpl();
    try {
        Field startOffsetField = DocumentImpl.class.getDeclaredField("sourceUrlStartOffset");
        startOffsetField.setAccessible(true);
        startOffsetField.set(document, 100L);
        Field endOffsetField = DocumentImpl.class.getDeclaredField("sourceUrlEndOffset");
        endOffsetField.setAccessible(true);
        endOffsetField.set(document, 200L);
    } catch (Exception e) {
        fail("Failed to set private fields via reflection: " + e.getMessage());
    }
    Long[] result = document.getSourceUrlOffsets();
    assertNotNull("The returned array should not be null", result);
    assertEquals("Array length should be 2", 2, result.length);
    assertEquals("Start offset should be correct", Long.valueOf(100L), result[0]);
    assertEquals("End offset should be correct", Long.valueOf(200L), result[1]);
}

@Test
public void test17()
{
    DocumentImpl document = new DocumentImpl();
    Field encodingField = DocumentImpl.class.getDeclaredField("encoding");
    encodingField.setAccessible(true);
    encodingField.set(document, null);
    String expectedEncoding = Charset.forName(System.getProperty("file.encoding")).name();
    String actualEncoding = document.getEncoding();
    assertEquals(expectedEncoding, actualEncoding);
}

@Test
public void test18()
{
    DocumentImpl document = new DocumentImpl();
    Field mimeTypeField = DocumentImpl.class.getDeclaredField("mimeType");
    mimeTypeField.setAccessible(true);
    mimeTypeField.set(document, "text/xml");
    String result = document.getMimeType();
    assertEquals("text/xml", result);
}

@Test
public void test19()
{
    DocumentImpl document = new DocumentImpl();
    String expectedContent = "This is a test string content.";
    try {
        Field field = DocumentImpl.class.getDeclaredField("stringContent");
        field.setAccessible(true);
        field.set(document, expectedContent);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set stringContent via reflection: " + e.getMessage());
    }
    String actualContent = document.getStringContent();
    assertEquals("The returned stringContent should match the expected string.", expectedContent, actualContent);
}

@Test
public void test20()
{
    DocumentImpl document = new DocumentImpl();
    document.setContent(new DocumentContentImpl("Sample content"));
    AnnotationSet defaultAnnots = new AnnotationSetImpl(document);
    document.setAnnotations(defaultAnnots);
    document.setEncoding("UTF-8");
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("key", "value");
    document.setFeatures(features);
    document.setMarkupAware(Boolean.TRUE);
    Map<String, AnnotationSet> namedSets = new HashMap<String, AnnotationSet>();
    namedSets.put("Set1", new AnnotationSetImpl(document));
    document.setNamedAnnotationSets(namedSets);
    document.setNextAnnotationId(10);
    document.setNextNodeId(20);
    URL sourceUrl = new URL("http://example.com/source");
    document.setSourceUrl(sourceUrl);
    document.setSourceUrlStartOffset(5L);
    document.setSourceUrlEndOffset(50L);
    String result = document.toString();
    String nl = Strings.getNl();
    assertTrue(result.contains("DocumentImpl: " + nl));
    assertTrue(result.contains(("  content:" + document.getContent().toString()) + nl));
    assertTrue(result.contains(("  defaultAnnots:" + defaultAnnots.toString()) + nl));
    assertTrue(result.contains("  encoding:UTF-8" + nl));
    assertTrue(result.contains(("  features:" + features.toString()) + nl));
    assertTrue(result.contains("  markupAware:true" + nl));
    assertTrue(result.contains(("  namedAnnotSets:" + namedSets.toString()) + nl));
    assertTrue(result.contains("  nextAnnotationId:10" + nl));
    assertTrue(result.contains("  nextNodeId:20" + nl));
    assertTrue(result.contains(("  sourceUrl:" + sourceUrl.toString()) + nl));
    assertTrue(result.contains("  sourceUrlStartOffset:5" + nl));
    assertTrue(result.contains("  sourceUrlEndOffset:50" + nl));
}

@Test
public void test21()
{
    DocumentImpl document = new DocumentImpl();
    Set<Annotation> emptyAnnotationSet = new HashSet<>();
    String xmlOutput = document.toXml(emptyAnnotationSet);
    assertNotNull(xmlOutput);
    String expectedOutput = document.toXml(emptyAnnotationSet, true);
    assertEquals(expectedOutput, xmlOutput);
}

@Test
public void test22()
{
    DocumentImpl document = spy(new DocumentImpl());
    Set<Annotation> emptyAnnotationSet = Collections.emptySet();
    doReturn("<xml>test</xml>").when(document).toXml(emptyAnnotationSet, true);
    String result = document.toXml(emptyAnnotationSet);
    assertEquals("<xml>test</xml>", result);
    verify(document).toXml(emptyAnnotationSet, true);
}

@Test
public void test23()
{
    DocumentImpl document = new DocumentImpl();
    Set<Annotation> annotations = new HashSet<Annotation>();
    String result = document.toXml(annotations);
    assertNotNull("The returned XML should not be null", result);
}

@Test
public void test24()
{
    DocumentImpl document = new DocumentImpl();
    URL expectedUrl = new URL("http://example.com/document");
    Field sourceUrlField = DocumentImpl.class.getDeclaredField("sourceUrl");
    sourceUrlField.setAccessible(true);
    sourceUrlField.set(document, expectedUrl);
    URL actualUrl = document.getSourceUrl();
    assertEquals(expectedUrl, actualUrl);
}

@Test
public void test25()
{
    DocumentImpl document = new DocumentImpl();
    Map<String, AnnotationSet> result = document.getNamedAnnotationSets();
    assertNotNull("Returned map should not be null", result);
    assertTrue("Returned map should be empty", result.isEmpty());
}

@Test
public void test26()
{
    DocumentImpl document = new DocumentImpl();
    try {
        Field field = DocumentImpl.class.getDeclaredField("namedAnnotSets");
        field.setAccessible(true);
        HashMap<String, AnnotationSet> map = new HashMap<String, AnnotationSet>();
        map.put("Annotations1", null);
        map.put("Annotations2", null);
        field.set(document, map);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set up namedAnnotSets via reflection: " + e.getMessage());
    }
    Set<String> setNames = document.getAnnotationSetNames();
    assertEquals(2, setNames.size());
    assertTrue(setNames.contains("Annotations1"));
    assertTrue(setNames.contains("Annotations2"));
}

@Test
public void test27()
{
    DocumentImpl document = new DocumentImpl();
    document.defaultAnnots = mock(AnnotationSet.class);
    document.namedAnnotSets = new HashMap<>();
    document.namedAnnotSets.put("testSet", mock(AnnotationSet.class));
    document.lrPersistentId = "persist-id";
    CreoleRegister mockRegister = mock(CreoleRegister.class);
    Gate.init();
    Gate.setCreoleRegister(mockRegister);
    DataStore mockDataStore = mock(DataStore.class);
    DocumentImpl spyDocument = spy(document);
    doReturn(mockDataStore).when(spyDocument).getDataStore();
    spyDocument.cleanup();
    assertNull(spyDocument.defaultAnnots);
    assertTrue(spyDocument.namedAnnotSets.isEmpty());
    verify(mockRegister).removeCreoleListener(spyDocument);
    verify(mockDataStore).removeDatastoreListener(spyDocument);
}

@Test
public void test28()
{
    DocumentImpl document = spy(new DocumentImpl());
    DataStore mockDataStore = mock(DataStore.class);
    CreoleEvent mockEvent = mock(CreoleEvent.class);
    doReturn(mockDataStore).when(document).getDataStore();
    when(mockEvent.getDatastore()).thenReturn(mockDataStore);
    document.datastoreClosed(mockEvent);
    verifyStatic(Factory.class);
    Factory.deleteResource(document);
}

@Test
public void test29()
{
    DocumentImpl document = new DocumentImpl();
    DocumentContentImpl content = new DocumentContentImpl("This is a test document.");
    document.setDocumentContent(content);
    AnnotationSetImpl defaultAnnots = new AnnotationSetImpl(document, "Default", null);
    document.setDefaultAnnotationSet(defaultAnnots);
    AnnotationSetImpl namedSet1 = new AnnotationSetImpl(document, "Set1", null);
    AnnotationSetImpl namedSet2 = new AnnotationSetImpl(document, "Set2", null);
    Map<String, AnnotationSet> namedAnnotSets = new HashMap<>();
    namedAnnotSets.put("Set1", namedSet1);
    namedAnnotSets.put("Set2", namedSet2);
    document.setNamedAnnotationSets(namedAnnotSets);
    final boolean[] eventFired = new boolean[]{ false };
    document.addDocumentListener(new DocumentListener() {
        @Override
        public void contentEdited(DocumentEvent e) {
            eventFired[0] = true;
            assertEquals(CONTENT_EDITED, e.getType());
            assertEquals(Long.valueOf(5), e.getStartOffset());
            assertEquals(Long.valueOf(7), e.getEndOffset());
        }
    });
    DocumentContent replacement = new DocumentContentImpl("was");
    document.edit(5L, 7L, replacement);
    assertTrue("Expected contentEdited event to be fired", eventFired[0]);
}

@Test
public void test30()
{
    DataStore mockDataStore = mock(DataStore.class);
    Object mockResourceID = new Object();
    DocumentImpl document = spy(new DocumentImpl());
    doReturn(mockDataStore).when(document).getDataStore();
    doReturn(mockResourceID).when(document).getLRPersistenceId();
    DatastoreEvent mockEvent = mock(DatastoreEvent.class);
    when(mockEvent.getSource()).thenReturn(mockDataStore);
    when(mockEvent.getResourceID()).thenReturn(mockResourceID);
    document.resourceDeleted(mockEvent);
    verifyStatic(Factory.class);
    Factory.deleteResource(document);
}

