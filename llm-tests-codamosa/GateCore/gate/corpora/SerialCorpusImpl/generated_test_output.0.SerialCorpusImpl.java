import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    CorpusListener mockListener = mock(CorpusListener.class);
    Vector<CorpusListener> listenersVector = new Vector<CorpusListener>();
    listenersVector.add(mockListener);
    Field listenersField = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
    listenersField.setAccessible(true);
    listenersField.set(corpus, listenersVector);
    CorpusEvent mockEvent = mock(CorpusEvent.class);
    Method method = SerialCorpusImpl.class.getDeclaredMethod("fireDocumentAdded", CorpusEvent.class);
    method.setAccessible(true);
    method.invoke(corpus, mockEvent);
    verify(mockListener, times(1)).documentAdded(mockEvent);
}

@Test
public void test2()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    CorpusListener mockListener = mock(CorpusListener.class);
    CorpusEvent mockEvent = mock(CorpusEvent.class);
    Vector<CorpusListener> listeners = new Vector<CorpusListener>();
    listeners.add(mockListener);
    Field field = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
    field.setAccessible(true);
    field.set(corpus, listeners);
    corpus.fireDocumentRemoved(mockEvent);
    verify(mockListener, times(1)).documentRemoved(mockEvent);
}

@Test
public void test3()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore sharedDataStore = new DataStore() {};
    corpus.setDataStore(sharedDataStore);
    Document mockDocument = new Document() {
        @Override
        public DataStore getDataStore() {
            return sharedDataStore;
        }

        @Override
        public String getName() {
            return "TestDoc";
        }

        @Override
        public Object getLRPersistenceId() {
            return UUID.randomUUID();
        }

        @Override
        public Class<?> getClass() {
            return Document.class;
        }
    };
    boolean result = corpus.add(mockDocument);
    assertTrue(result);
}

@Test
public void test4()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore dataStore = mock(DataStore.class);
    corpus.setDataStore(dataStore);
    Document doc = mock(Document.class);
    when(doc.getDataStore()).thenReturn(dataStore);
    when(doc.getName()).thenReturn("TestDoc");
    when(doc.getLRPersistenceId()).thenReturn(UUID.randomUUID().toString());
    when(doc.getClass()).thenReturn(((Class) (Document.class)));
    boolean result = corpus.add(doc);
    assertTrue("Document from same datastore should be added successfully", result);
}

@Test
public void test5()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    Document doc1 = mock(Document.class);
    Document doc2 = mock(Document.class);
    Document doc3 = mock(Document.class);
    Collection<Document> documents = new ArrayList<>();
    documents.add(doc1);
    documents.add(doc2);
    documents.add(doc3);
    doReturn(true).when(corpus).add(doc1);
    doReturn(false).when(corpus).add(doc2);
    doReturn(true).when(corpus).add(doc3);
    boolean result = corpus.addAll(documents);
    assertFalse("Expected addAll to return false when one document fails to add", result);
    verify(corpus).add(doc1);
    verify(corpus).add(doc2);
    verify(corpus).add(doc3);
}

@Test
public void test6()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = new DocumentImpl();
    Document doc2 = new DocumentImpl();
    Collection<Document> documents = new ArrayList<Document>();
    documents.add(doc1);
    documents.add(doc2);
    boolean result = corpus.addAll(documents);
    assertTrue("All documents should be added successfully", result);
    assertTrue("Corpus should contain doc1", corpus.contains(doc1));
    assertTrue("Corpus should contain doc2", corpus.contains(doc2));
}

@Test
public void test7()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = new DocumentImpl();
    String docName = "testDoc";
    String persistentId = UUID.randomUUID().toString();
    doc.setName(docName);
    doc.setFeatures(new FeatureMapImpl());
    doc.getFeatures().put("gate.persistence.PersistentID", persistentId);
    corpus.add(doc);
    Document queryDoc = new DocumentImpl();
    queryDoc.setName(docName);
    queryDoc.setFeatures(new FeatureMapImpl());
    queryDoc.getFeatures().put("gate.persistence.PersistentID", persistentId);
    boolean result = corpus.contains(queryDoc);
    assertTrue("Corpus should contain a document with matching name and persistent ID", result);
}

@Test
public void test8()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = mock(Document.class);
    Document doc2 = mock(Document.class);
    corpus.add(doc1);
    corpus.add(doc2);
    Collection<Document> testCollection = new ArrayList<>();
    testCollection.add(doc1);
    testCollection.add(doc2);
    boolean result = corpus.containsAll(testCollection);
    assertTrue(result);
}

@Test
public void test9()
{
    SerialCorpusImpl corpus1 = new SerialCorpusImpl();
    SerialCorpusImpl corpus2 = new SerialCorpusImpl();
    corpus1.setLRPersistenceId("1234");
    corpus2.setLRPersistenceId("1234");
    corpus1.setName("TestCorpus");
    corpus2.setName("TestCorpus");
    DataStore mockDataStore = new DataStore() {
        @Override
        public String getStorageDir() {
            return null;
        }

        @Override
        public void sync(Object o) {
        }

        @Override
        public void close() {
        }

        @Override
        public void delete() {
        }

        @Override
        public String getName() {
            return "mock";
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    };
    corpus1.setDataStore(mockDataStore);
    corpus2.setDataStore(mockDataStore);
    List<Document> documents = new ArrayList<>();
    documents.add(new DocumentImpl("Test Document"));
    corpus1.setDocDataList(documents);
    corpus2.setDocDataList(documents);
    assertTrue(corpus1.equals(corpus2));
}

@Test
public void test10()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> docList = new ArrayList<Document>();
    Document mockDocument = Mockito.mock(Document.class);
    docList.add(mockDocument);
    corpus.setDocuments(docList);
    boolean result = corpus.isDocumentLoaded(0);
    assertTrue("Expected document to be loaded at index 0", result);
}

@Test
public void test11()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> emptyList = new ArrayList<Document>();
    Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
    field.setAccessible(true);
    field.set(corpus, emptyList);
    boolean result = corpus.isEmpty();
    assertTrue(result);
}

@Test
public void test12()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documents = new ArrayList<>();
    documents.add(null);
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData docData = new DocumentData();
    docData.setPersistentID("non-null-id");
    docDataList.add(docData);
    corpus.documents = documents;
    corpus.docDataList = docDataList;
    boolean result = corpus.isPersistentDocument(0);
    assertTrue(result);
}

@Test
public void test13()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    List<Object> docDataList = new ArrayList<>();
    List<Document> documents = new ArrayList<>();
    Object docData = mock(Object.class);
    when(((DocumentData) (docData)).getDocumentName()).thenReturn("TestDoc");
    docDataList.add(docData);
    documents.add(mockDoc);
    try {
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docDataList);
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documents);
    } catch (Exception e) {
        fail("Exception during reflection setup: " + e.getMessage());
    }
    try {
        Method findDocumentMethod = SerialCorpusImpl.class.getDeclaredMethod("findDocument", Document.class);
        findDocumentMethod.setAccessible(true);
        findDocumentMethod.invoke(corpus, mockDoc);
    } catch (Exception ignored) {
    }
    boolean result = corpus.remove(mockDoc);
    assertTrue(result);
    assertEquals(0, docDataList.size());
    assertEquals(0, documents.size());
}

@Test
public void test14()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = Factory.newDocument("Document 1");
    Document doc2 = Factory.newDocument("Document 2");
    corpus.add(doc1);
    corpus.add(doc2);
    Collection<Document> toRemove = new ArrayList<>();
    toRemove.add(doc1);
    toRemove.add(doc2);
    boolean result = corpus.removeAll(toRemove);
    assertTrue("Expected all documents to be removed", result);
    assertFalse("Corpus should not contain doc1 after removal", corpus.contains(doc1));
    assertFalse("Corpus should not contain doc2 after removal", corpus.contains(doc2));
}

@Test
public void test15()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Collection<String> collection = Arrays.asList("doc1", "doc2");
    corpus.retainAll(collection);
}

@Test
public void test16()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    List<Document> documents = new ArrayList<>();
    documents.add(mockDoc);
    List<DocumentData> docDataList = new ArrayList<>();
    docDataList.add(mock(DocumentData.class));
    try {
        Field docsField = SerialCorpusImpl.class.getDeclaredField("documents");
        docsField.setAccessible(true);
        docsField.set(corpus, documents);
        Field dataField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        dataField.setAccessible(true);
        dataField.set(corpus, docDataList);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Reflection failed: " + e.getMessage());
    }
    Document result = corpus.get(0);
    assertNotNull(result);
    assertEquals(mockDoc, result);
}

@Test
public void test17()
{
    Document mockDocument = mock(Document.class);
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData mockData = mock(DocumentData.class);
    when(mockData.getDocumentName()).thenReturn("testDoc");
    corpus.documents = new ArrayList<>();
    corpus.docDataList = new ArrayList<>();
    corpus.documents.add(mockDocument);
    corpus.docDataList.add(mockData);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(0).when(spyCorpus).findDocument(mockDocument);
    doReturn("docID123").when(spyCorpus).getDocumentPersistentID(0);
    doNothing().when(spyCorpus).fireDocumentRemoved(any(CorpusEvent.class));
    boolean result = spyCorpus.remove(mockDocument);
    assertTrue(result);
    assertEquals(0, spyCorpus.documents.size());
    assertEquals(0, spyCorpus.docDataList.size());
}

@Test
public void test18()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document dummyDocument = null;
    corpus.set(0, dummyDocument);
}

@Test
public void test19()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Factory.DuplicationContext context = new Factory.DuplicationContext();
    corpus.duplicate(context);
}

@Test
public void test20()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Resource result = corpus.init();
    assertSame("init() should return the same instance (this)", corpus, result);
}

@Test
public void test21()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    FeatureMap features = new SimpleFeatureMapImpl();
    IndexDefinition expectedDefinition = new IndexDefinition();
    features.put(CORPUS_INDEX_DEFINITION_FEATURE_KEY, expectedDefinition);
    corpus.setFeatures(features);
    IndexDefinition actualDefinition = corpus.getIndexDefinition();
    assertSame("The returned IndexDefinition should be the same instance put in features.", expectedDefinition, actualDefinition);
}

@Test
public void test22()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    IndexManager mockIndexManager = mock(IndexManager.class);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("indexManager");
        field.setAccessible(true);
        field.set(corpus, mockIndexManager);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    IndexManager result = corpus.getIndexManager();
    assertSame("The returned IndexManager should be the one assigned", mockIndexManager, result);
}

@Test
public void test23()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    IndexStatistics expectedStats = new IndexStatistics() {};
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put(CORPUS_INDEX_STATISTICS_FEATURE_KEY, expectedStats);
    corpus.setFeatures(features);
    IndexStatistics actualStats = corpus.getIndexStatistics();
    assertSame("Returned IndexStatistics should be the same as inserted", expectedStats, actualStats);
}

@Test
public void test24()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = new Document() {
        @Override
        public String getName() {
            return "TestDoc";
        }

        @Override
        public Object getLRPersistenceId() {
            return UUID.randomUUID().toString();
        }

        @Override
        public String getSourceUrl() {
            return null;
        }

        @Override
        public URL getSourceUrlObject() {
            return null;
        }

        @Override
        public void setSourceUrl(URL sourceUrl) {
        }

        @Override
        public void setSourceUrl(String sourceUrl) {
        }

        @Override
        public void setName(String name) {
        }

        @Override
        public void cleanup() {
        }

        @Override
        public void release() {
        }

        @Override
        public void setLRPersistenceId(Object id) {
        }

        @Override
        public String getEncoding() {
            return null;
        }

        @Override
        public void setEncoding(String encoding) {
        }

        @Override
        public String getMimeType() {
            return null;
        }

        @Override
        public void setMimeType(String mimeType) {
        }

        @Override
        public String getDocumentContent() {
            return null;
        }

        @Override
        public void setDocumentContent(String content) {
        }

        @Override
        public Object getContent() {
            return null;
        }

        @Override
        public void setContent(Object content) {
        }

        @Override
        public String toXml() {
            return null;
        }

        @Override
        public String toXml(AnnotationSet annotations) {
            return null;
        }

        @Override
        public String toXml(AnnotationSet annotations, boolean includeFeatures) {
            return null;
        }

        @Override
        public String toXml(AnnotationSet annotations, boolean includeFeatures, boolean includeContent) {
            return null;
        }

        @Override
        public AnnotationSet getAnnotations() {
            return null;
        }

        @Override
        public AnnotationSet getAnnotations(String name) {
            return null;
        }

        @Override
        public Set<String> getAnnotationSetNames() {
            return null;
        }

        @Override
        public void removeAnnotationSet(String name) {
        }

        @Override
        public Long getLastModified() {
            return null;
        }

        @Override
        public void setLastModified(Long time) {
        }
    };
    String name = doc.getName();
    String persistentId = doc.getLRPersistenceId().toString();
    String classType = doc.getClass().getName();
    DocumentData docData = new DocumentData(name, persistentId, classType);
    corpus.getDocDataList().add(docData);
    int index = corpus.findDocument(doc);
    assertEquals(0, index);
}

@Test
public void test25()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Object nonDocumentObject = new Object();
    int index = corpus.indexOf(nonDocumentObject);
    assertEquals(-1, index);
}

@Test
public void test26()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.lastIndexOf("test");
}

@Test
public void test27()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> mockDocuments = new ArrayList<>();
    List<Object> mockDocDataList = new ArrayList<>();
    Document mockDoc = mock(Document.class);
    mockDocuments.add(mockDoc);
    mockDocDataList.add(new Object());
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, mockDocuments);
    Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataListField.setAccessible(true);
    docDataListField.set(corpus, mockDocDataList);
    Document result = corpus.get(0);
    assertSame(mockDoc, result);
}

@Test
public void test28()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData docData = new DocumentData();
    Object expectedID = "doc123";
    docData.setPersistentID(expectedID);
    corpus.docDataList = new ArrayList<DocumentData>();
    corpus.docDataList.add(docData);
    Object result = corpus.getDocumentPersistentID(0);
    assertEquals(expectedID, result);
}

@Test
public void test29()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Object result = corpus.getTransientSource();
    assertNull("getTransientSource() should return null", result);
}

@Test
public void test30()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    DocumentData mockDocData = mock(DocumentData.class);
    when(mockDocData.getDocumentName()).thenReturn("testDocName");
    List<Document> documentsList = new ArrayList<>();
    documentsList.add(mockDoc);
    List<DocumentData> docDataList = new ArrayList<>();
    docDataList.add(mockDocData);
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, documentsList);
    Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataListField.setAccessible(true);
    docDataListField.set(corpus, docDataList);
    Method findDocumentMethod = SerialCorpusImpl.class.getDeclaredMethod("findDocument", Document.class);
    findDocumentMethod.setAccessible(true);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(0).when(spyCorpus).findDocument(mockDoc);
    doReturn("persistentID").when(spyCorpus).getDocumentPersistentID(0);
    doNothing().when(spyCorpus).fireDocumentRemoved(any(CorpusEvent.class));
    boolean result = spyCorpus.remove(mockDoc);
    assertTrue(result);
    assertEquals(0, ((List) (documentsField.get(spyCorpus))).size());
    assertEquals(0, ((List) (docDataListField.get(spyCorpus))).size());
}

@Test
public void test31()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document dummyDocument = new Document() {
        @Override
        public String toString() {
            return "DummyDocument";
        }
    };
    corpus.set(0, dummyDocument);
}

@Test
public void test32()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.toArray();
}

@Test
public void test33()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.toArray();
}

@Test
public void test34()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docList = new ArrayList<DocumentData>();
    DocumentData doc1 = new DocumentData();
    doc1.setClassType("TestClassType");
    docList.add(doc1);
    corpus.docDataList = docList;
}

@Test
public void test35()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData docData = new DocumentData();
    docData.setDocumentName("TestDocument");
    docDataList.add(docData);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Failed to set docDataList field: " + e.getMessage());
    }
    String result = corpus.getDocumentName(0);
    assertEquals("TestDocument", result);
}

@Test
public void test36()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<String> docDataList = new ArrayList<>();
    docDataList.add("doc1");
    docDataList.add("doc2");
    List<Document> documents = new ArrayList<>();
    try {
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docDataList);
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documents);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    String expected = "document data [doc1, doc2] documents []";
    assertEquals(expected, corpus.toString());
}

@Test
public void test37()
{
    Document doc1 = mock(Document.class);
    Document doc2 = mock(Document.class);
    DocumentData docData1 = mock(DocumentData.class);
    DocumentData docData2 = mock(DocumentData.class);
    SerialCorpusImpl corpus = new SerialCorpusImpl() {
        List<DocumentData> docDataList = new ArrayList<DocumentData>() {
            {
                add(docData1);
                add(docData2);
            }
        };

        List<Document> docs = new ArrayList<Document>() {
            {
                add(doc1);
                add(doc2);
            }
        };

        @Override
        public Document get(int index) {
            return docs.get(index);
        }

        @Override
        public Iterator<Document> iterator() {
            return new Iterator<Document>() {
                Iterator<DocumentData> docDataIter = docDataList.iterator();

                @Override
                public boolean hasNext() {
                    return docDataIter.hasNext();
                }

                @Override
                public Document next() {
                    DocumentData data = docDataIter.next();
                    int idx = docDataList.indexOf(data);
                    return get(idx);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException("SerialCorpusImpl does not support remove in the iterators");
                }
            };
        }
    };
    Iterator<Document> iterator = corpus.iterator();
    assertTrue(iterator.hasNext());
    assertSame(doc1, iterator.next());
    assertTrue(iterator.hasNext());
    assertSame(doc2, iterator.next());
    assertFalse(iterator.hasNext());
}

@Test
public void test38()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<DocumentData>();
    DocumentData doc1 = new DocumentData();
    doc1.setClassType("gate.corpora.DocumentImpl");
    DocumentData doc2 = new DocumentData();
    doc2.setClassType("gate.corpora.XmlDocumentImpl");
    docDataList.add(doc1);
    docDataList.add(doc2);
    corpus.docDataList = docDataList;
    List<String> expected = new ArrayList<String>();
    expected.add("gate.corpora.DocumentImpl");
    expected.add("gate.corpora.XmlDocumentImpl");
    List<String> actual = corpus.getDocumentClassTypes();
    assertEquals(expected, actual);
}

@Test
public void test39()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Object> docDataList = new ArrayList<Object>();
    DocumentData doc1 = new DocumentData("DocumentOne", null, null);
    DocumentData doc2 = new DocumentData("DocumentTwo", null, null);
    DocumentData doc3 = new DocumentData("DocumentThree", null, null);
    docDataList.add(doc1);
    docDataList.add(doc2);
    docDataList.add(doc3);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Reflection error: " + e.getMessage());
    }
    List<String> result = corpus.getDocumentNames();
    assertEquals(3, result.size());
    assertEquals("DocumentOne", result.get(0));
    assertEquals("DocumentTwo", result.get(1));
    assertEquals("DocumentThree", result.get(2));
}

@Test
public void test40()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData doc1 = new DocumentData();
    Object id1 = UUID.randomUUID();
    doc1.setPersistentID(id1);
    DocumentData doc2 = new DocumentData();
    Object id2 = UUID.randomUUID();
    doc2.setPersistentID(id2);
    List<DocumentData> docDataList = new ArrayList<DocumentData>();
    docDataList.add(doc1);
    docDataList.add(doc2);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Failed to set docDataList via reflection: " + e.getMessage());
    }
    List<Object> persistentIDs = corpus.getDocumentPersistentIDs();
    assertEquals(2, persistentIDs.size());
    assertEquals(id1, persistentIDs.get(0));
    assertEquals(id2, persistentIDs.get(1));
}

@Test
public void test41()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List result = corpus.subList(0, 1);
}

@Test
public void test42()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ListIterator<Document> iterator = corpus.listIterator();
}

@Test
public void test43()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ListIterator<Document> iterator = corpus.listIterator();
}

@Test
public void test44()
{
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "corpusTestDir");
    tempDir.mkdir();
    File testFile = new File(tempDir, "example.txt");
    FileWriter writer = new FileWriter(testFile);
    writer.write("This is a test document.");
    writer.close();
    URL dirUrl = tempDir.toURI().toURL();
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.populate(dirUrl, null, "UTF-8", false);
    assertEquals("Corpus should contain one document", 1, corpus.size());
    testFile.delete();
    tempDir.delete();
}

@Test
public void test45()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    CorpusListener listener = new CorpusListener() {};
    corpus.addCorpusListener(listener);
    Field field = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
    field.setAccessible(true);
    @SuppressWarnings("unchecked")
    Vector<CorpusListener> listeners = ((Vector<CorpusListener>) (field.get(corpus)));
    assertNotNull("The internal corpusListeners should not be null", listeners);
    assertEquals("There should be exactly one listener added", 1, listeners.size());
    assertTrue("The added listener should be present in the corpusListeners", listeners.contains(listener));
}

@Test
public void test46()
{
    DataStore mockDataStore = mock(DataStore.class);
    Document mockDocument = mock(Document.class);
    when(mockDocument.getDataStore()).thenReturn(mockDataStore);
    when(mockDocument.getName()).thenReturn("TestDoc");
    when(mockDocument.getLRPersistenceId()).thenReturn("12345");
    when(mockDocument.getClass()).thenAnswer(( invocation) -> .class);
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setDataStore(mockDataStore);
    boolean result = corpus.add(mockDocument);
    assertTrue(result);
}

@Test
public void test47()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    boolean result = corpus.add(null);
    assertFalse(result);
}

@Test
public void test48()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    CreoleRegister mockRegister = mock(CreoleRegister.class);
    Gate.setCreoleRegister(mockRegister);
    try {
        Field debugField = SerialCorpusImpl.class.getDeclaredField("DEBUG");
        debugField.setAccessible(true);
        debugField.set(null, true);
    } catch (Exception e) {
        fail("Failed to set DEBUG value via reflection");
    }
    List<CorpusListener> listeners = new ArrayList<>();
    listeners.add(mock(CorpusListener.class));
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
        field.setAccessible(true);
        field.set(corpus, listeners);
    } catch (Exception e) {
        fail("Failed to set corpusListeners via reflection");
    }
    List<Document> docs = new ArrayList<>();
    docs.add(mock(Document.class));
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("documents");
        field.setAccessible(true);
        field.set(corpus, docs);
    } catch (Exception e) {
        fail("Failed to set documents via reflection");
    }
    List<Object> docData = new ArrayList<>();
    docData.add(new Object());
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docData);
    } catch (Exception e) {
        fail("Failed to set docDataList via reflection");
    }
    DataStore mockStore = mock(DataStore.class);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("dataStore");
        field.setAccessible(true);
        field.set(corpus, mockStore);
    } catch (Exception e) {
        fail("Failed to set dataStore via reflection");
    }
    corpus.cleanup();
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
        field.setAccessible(true);
        assertNull(field.get(corpus));
    } catch (Exception e) {
        fail("Failed to access corpusListeners after cleanup");
    }
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("documents");
        field.setAccessible(true);
        List<?> clearedDocs = ((List<?>) (field.get(corpus)));
        assertNotNull(clearedDocs);
        assertTrue(clearedDocs.isEmpty());
    } catch (Exception e) {
        fail("Failed to access documents after cleanup");
    }
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        List<?> clearedData = ((List<?>) (field.get(corpus)));
        assertNotNull(clearedData);
        assertTrue(clearedData.isEmpty());
    } catch (Exception e) {
        fail("Failed to access docDataList after cleanup");
    }
    verify(mockRegister).removeCreoleListener(corpus);
    verify(mockStore).removeDatastoreListener(corpus);
}

@Test
public void test49()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = Factory.newDocument("This is document 1.");
    Document doc2 = Factory.newDocument("This is document 2.");
    corpus.add(doc1);
    corpus.add(doc2);
    assertEquals(2, corpus.size());
    corpus.clear();
    assertEquals(0, corpus.size());
    assertTrue(corpus.getDataList().isEmpty());
}

@Test
public void test50()
{
    DataStore mockDataStore = mock(DataStore.class);
    CreoleEvent mockEvent = mock(CreoleEvent.class);
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    doReturn(mockDataStore).when(corpus).getDataStore();
    when(mockEvent.getDatastore()).thenReturn(mockDataStore);
    doNothing().when(mockDataStore).removeDatastoreListener(corpus);
    mockStatic(Factory.class);
    Factory.deleteResource(corpus);
    corpus.datastoreClosed(mockEvent);
    verify(mockDataStore).removeDatastoreListener(corpus);
    verifyStatic(Factory.class);
    Factory.deleteResource(corpus);
}

@Test
public void test51()
{
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "testCorpusDir");
    tempDir.mkdir();
    tempDir.deleteOnExit();
    File tempFile = new File(tempDir, "testDoc.xml");
    FileWriter writer = new FileWriter(tempFile);
    writer.write("<doc>Sample</doc>");
    writer.close();
    tempFile.deleteOnExit();
    URL directoryUrl = tempDir.toURI().toURL();
    FileFilter xmlFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".xml");
        }
    };
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setName("testCorpus");
    corpus.populate(directoryUrl, xmlFilter, "UTF-8", false);
    assertEquals(1, corpus.size());
    assertNotNull(corpus.get(0));
}

@Test
public void test52()
{
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "testCorpusDir");
    tempDir.mkdir();
    tempDir.deleteOnExit();
    File testFile = new File(tempDir, "testDoc.txt");
    FileWriter writer = new FileWriter(testFile);
    writer.write("This is a test document.");
    writer.close();
    testFile.deleteOnExit();
    FileFilter txtFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.isFile() && pathname.getName().endsWith(".txt");
        }
    };
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setName("TestCorpus");
    URL dirUrl = tempDir.toURI().toURL();
    corpus.populate(dirUrl, txtFilter, "UTF-8", false);
    assertEquals(1, corpus.size());
    assertEquals("testDoc.txt", corpus.get(0).getName());
}

@Test
public void test53()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore mockDataStore = mock(DataStore.class);
    corpus.setDataStore(mockDataStore);
    DocumentData docData1 = mock(DocumentData.class);
    DocumentData docData2 = mock(DocumentData.class);
    when(docData1.getPersistentID()).thenReturn("doc1ID");
    when(docData2.getPersistentID()).thenReturn("doc2ID");
    corpus.docDataList = new ArrayList<>();
    corpus.docDataList.add(docData1);
    corpus.docDataList.add(docData2);
    corpus.documents = new ArrayList<>();
    corpus.documents.add(mock(Document.class));
    corpus.documents.add(mock(Document.class));
    Object deletedId = "doc1ID";
    DatastoreEvent evt = mock(DatastoreEvent.class);
    when(evt.getSource()).thenReturn(mockDataStore);
    when(evt.getResourceID()).thenReturn(deletedId);
    when(evt.getResource()).thenReturn(null);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doNothing().when(spyCorpus).documentRemoved("doc1ID");
    doNothing().when(mockDataStore).sync(spyCorpus);
    spyCorpus.resourceDeleted(evt);
    assertEquals(1, spyCorpus.docDataList.size());
    assertEquals("doc2ID", spyCorpus.docDataList.get(0).getPersistentID());
    assertEquals(1, spyCorpus.documents.size());
    verify(spyCorpus).documentRemoved("doc1ID");
    verify(mockDataStore).sync(spyCorpus);
}

@Test
public void test54()
{
    Document mockDocument = mock(Document.class);
    DataStore mockDataStore1 = mock(DataStore.class);
    DataStore mockDataStore2 = mock(DataStore.class);
    when(mockDocument.getDataStore()).thenReturn(mockDataStore1);
    SerialCorpusImpl corpus = new SerialCorpusImpl() {
        @Override
        public DataStore getDataStore() {
            return mockDataStore2;
        }
    };
    corpus.add(mockDocument);
    assertTrue("Document should be added", corpus.contains(mockDocument));
    CreoleEvent mockEvent = mock(CreoleEvent.class);
    when(mockEvent.getResource()).thenReturn(mockDocument);
    corpus.resourceUnloaded(mockEvent);
    assertFalse("Document should be removed from corpus", corpus.contains(mockDocument));
}

@Test
public void test55()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    UUID matchingId = UUID.randomUUID();
    doReturn(matchingId).when(corpus).getLRPersistenceId();
    DatastoreEvent mockEvent = mock(DatastoreEvent.class);
    when(mockEvent.getResourceID()).thenReturn(matchingId);
    doNothing().when(corpus).thisResourceWritten();
    corpus.resourceWritten(mockEvent);
    verify(corpus, times(1)).thisResourceWritten();
}

@Test
public void test56()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData docData = new DocumentData();
    ArrayList<DocumentData> docList = new ArrayList<DocumentData>();
    docList.add(docData);
    try {
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docList);
    } catch (Exception e) {
        fail("Failed to set up docDataList via reflection: " + e.getMessage());
    }
    Object expectedID = "persistent-id-123";
    corpus.setDocumentPersistentID(0, expectedID);
    assertEquals("persistent-id-123", docData.getPersistentID());
}

@Test
public void test57()
{
    Gate.init();
    Gate.setClassLoader(this.getClass().getClassLoader());
    IndexDefinition indexDefinition = new IndexDefinition() {
        @Override
        public String getIrEngineClassName() {
            return DummyEngine.class.getName();
        }
    };
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setIndexDefinition(indexDefinition);
    assertEquals(indexDefinition, corpus.getFeatures().get(CORPUS_INDEX_DEFINITION_FEATURE_KEY));
    assertNotNull(corpus.getAddedDocuments());
    assertTrue(corpus.getAddedDocuments() instanceof Vector);
    assertNotNull(corpus.getRemovedDocumentIDs());
    assertTrue(corpus.getRemovedDocumentIDs() instanceof Vector);
    assertNotNull(corpus.getChangedDocuments());
    assertTrue(corpus.getChangedDocuments() instanceof Vector);
    assertNotNull(corpus.getIndexManager());
}

@Test
public void test58()
{
    Corpus sourceCorpus = Factory.newCorpus("TransientCorpus");
    Document doc = Factory.newDocument("Sample content.");
    doc.setName("Doc1");
    sourceCorpus.add(doc);
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    serialCorpus.setDataStore(null);
    serialCorpus.setLRPersistenceId(null);
    serialCorpus.setTransientSource(sourceCorpus);
    assertEquals("TransientCorpus", serialCorpus.getName());
    FeatureMap srcFeatures = sourceCorpus.getFeatures();
    FeatureMap destFeatures = serialCorpus.getFeatures();
    assertEquals(srcFeatures, destFeatures);
    assertEquals(1, serialCorpus.size());
    assertEquals(doc.getContent().toString(), serialCorpus.get(0).getContent().toString());
    assertNotNull(serialCorpus.getDocumentDataList());
    assertEquals(1, serialCorpus.getDocumentDataList().size());
    assertEquals("Doc1", serialCorpus.getDocumentDataList().get(0).getName());
    assertTrue(serialCorpus.getAddedDocs() instanceof Vector);
    assertTrue(serialCorpus.getRemovedDocIDs() instanceof Vector);
    assertTrue(serialCorpus.getChangedDocs() instanceof Vector);
}

@Test
public void test59()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null).thenReturn("persistentId");
    DataStore mockDataStore = mock(DataStore.class);
    Document adoptedDoc = mock(Document.class);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("persistentId");
    when(mockDataStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    doNothing().when(mockDataStore).sync(adoptedDoc);
    ArrayList<Document> documentList = new ArrayList<>();
    documentList.add(mockDoc);
    setPrivateField(corpus, "documents", documentList);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(true).when(spyCorpus).isDocumentLoaded(0);
    doReturn(true).when(spyCorpus).isPersistentDocument(0);
    doReturn(mockDataStore).when(spyCorpus).getDataStore();
    doNothing().when(spyCorpus).setDocumentPersistentID(eq(0), eq("persistentId"));
    spyCorpus.unloadDocument(0, true);
    assertNull(documentList.get(0));
    verify(mockDataStore).adopt(mockDoc);
    verify(mockDataStore).sync(adoptedDoc);
    verify(spyCorpus).setDocumentPersistentID(0, "persistentId");
}

@Test
public void test60()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null);
    Document adoptedDoc = mock(Document.class);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("mockId");
    ArrayList<Document> docs = new ArrayList<>();
    docs.add(mockDoc);
    Field docsField = SerialCorpusImpl.class.getDeclaredField("documents");
    docsField.setAccessible(true);
    docsField.set(corpus, docs);
    DataStore mockDataStore = mock(DataStore.class);
    when(mockDataStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(mockDataStore).when(spyCorpus).getDataStore();
    doReturn(true).when(spyCorpus).isDocumentLoaded(0);
    doReturn(true).when(spyCorpus).isPersistentDocument(0);
    spyCorpus.unloadDocument(0, true);
    verify(mockDataStore).adopt(mockDoc);
    verify(mockDataStore).sync(adoptedDoc);
    verify(spyCorpus).setDocumentPersistentID(0, "mockId");
    assert spyCorpus.documents.get(0) == null;
}

@Test
public void test61()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn("doc123");
    List<Document> docs = new ArrayList<>();
    docs.add(mockDoc);
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, docs);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(true).when(spyCorpus).isDocumentLoaded(0);
    doReturn(true).when(spyCorpus).isPersistentDocument(0);
    DataStore mockStore = mock(DataStore.class);
    doNothing().when(mockStore).sync(mockDoc);
    doReturn(mockStore).when(spyCorpus).getDataStore();
    spyCorpus.unloadDocument(0, true);
    @SuppressWarnings("unchecked")
    List<Document> updatedDocs = ((List<Document>) (documentsField.get(spyCorpus)));
    assertNull(updatedDocs.get(0));
    verify(mockStore).sync(mockDoc);
}

@Test
public void test62()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    Document mockDoc = mock(Document.class);
    DataStore mockDataStore = mock(DataStore.class);
    String mockLRID = "mockLRID";
    when(mockDoc.getLRPersistenceId()).thenReturn(mockLRID);
    List<Document> mockDocList = new ArrayList<>();
    mockDocList.add(mockDoc);
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, mockDocList);
    doReturn(true).when(corpus).isDocumentLoaded(0);
    doReturn(true).when(corpus).isPersistentDocument(0);
    doReturn(mockDataStore).when(corpus).getDataStore();
    corpus.unloadDocument(0, true);
    verify(mockDataStore).sync(mockDoc);
    assertNull(mockDocList.get(0));
}

@Test
public void test1()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    CorpusListener listenerMock = mock(CorpusListener.class);
    CorpusEvent eventMock = mock(CorpusEvent.class);
    Vector<CorpusListener> listeners = new Vector<CorpusListener>();
    listeners.add(listenerMock);
    Field field = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
    field.setAccessible(true);
    field.set(corpus, listeners);
    Method method = SerialCorpusImpl.class.getDeclaredMethod("fireDocumentAdded", CorpusEvent.class);
    method.setAccessible(true);
    method.invoke(corpus, eventMock);
    verify(listenerMock, times(1)).documentAdded(eventMock);
}

@Test
public void test2()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore mockDataStore = mock(DataStore.class);
    corpus.setDataStore(mockDataStore);
    Document mockDocument = mock(Document.class);
    when(mockDocument.getDataStore()).thenReturn(mockDataStore);
    when(mockDocument.getName()).thenReturn("documentName");
    when(mockDocument.getLRPersistenceId()).thenReturn("123");
    when(mockDocument.getClass()).thenReturn(((Class) (Document.class)));
    boolean result = corpus.add(mockDocument);
    assertTrue(result);
}

@Test
public void test3()
{
    Document mockDocument = mock(Document.class);
    DataStore mockDataStore = mock(DataStore.class);
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore corpusDataStore = mockDataStore;
    corpus.setDataStore(corpusDataStore);
    when(mockDocument.getDataStore()).thenReturn(corpusDataStore);
    when(mockDocument.getName()).thenReturn("doc1");
    when(mockDocument.getLRPersistenceId()).thenReturn(UUID.randomUUID().toString());
    when(mockDocument.getClass()).thenReturn(((Class) (Document.class)));
    try {
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, new ArrayList<>());
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, new ArrayList<>());
    } catch (Exception e) {
        fail("Failed to access or set internal fields: " + e.getMessage());
    }
    boolean result = corpus.add(mockDocument);
    assertTrue("Expected add to return true for valid document from same datastore", result);
}

@Test
public void test4()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    String docName = "testDoc";
    String persistentId = "12345";
    when(mockDoc.getName()).thenReturn(docName);
    when(mockDoc.getFeatures()).thenReturn(new FeatureMapImpl());
    mockDoc.getFeatures().put("gate.persistentId", persistentId);
    corpus.add(mockDoc);
    boolean result = corpus.contains(mockDoc);
    assertTrue(result);
}

@Test
public void test5()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = Factory.newDocument("Document 1");
    Document doc2 = Factory.newDocument("Document 2");
    corpus.add(doc1);
    corpus.add(doc2);
    Collection<Document> inputCollection = new ArrayList<>();
    inputCollection.add(doc1);
    inputCollection.add(doc2);
    boolean result = corpus.containsAll(inputCollection);
    assertTrue(result);
}

@Test
public void test6()
{
    SerialCorpusImpl corpus1 = new SerialCorpusImpl();
    corpus1.lrPersistentId = "id123";
    corpus1.name = "TestCorpus";
    corpus1.dataStore = new DummyDataStore("datastore1");
    corpus1.docDataList = new ArrayList<>();
    corpus1.docDataList.add("doc1");
    corpus1.docDataList.add("doc2");
    SerialCorpusImpl corpus2 = new SerialCorpusImpl();
    corpus2.lrPersistentId = "id123";
    corpus2.name = "TestCorpus";
    corpus2.dataStore = new DummyDataStore("datastore1");
    corpus2.docDataList = new ArrayList<>();
    corpus2.docDataList.add("doc1");
    corpus2.docDataList.add("doc2");
    assertTrue(corpus1.equals(corpus2));
}

@Test
public void test7()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> mockDocuments = new ArrayList<Document>();
    mockDocuments.add(null);
    Document mockDoc = new Document() {
        @Override
        public String getName() {
            return "MockDoc";
        }

        @Override
        public void setName(String name) {
        }

        @Override
        public String toString() {
            return "MockDoc";
        }
    };
    mockDocuments.add(mockDoc);
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, mockDocuments);
    boolean result = corpus.isDocumentLoaded(1);
    assertTrue(result);
}

@Test
public void test8()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataListField.setAccessible(true);
    docDataListField.set(corpus, new ArrayList<Document>());
    boolean result = corpus.isEmpty();
    assertTrue(result);
}

@Test
public void test9()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documents = new ArrayList<>();
    Document mockDoc = new DocumentImpl();
    documents.add(mockDoc);
    corpus.setDocuments(documents);
    DocData docData = new DocData();
    docData.setPersistentID("doc-persistent-id");
    List<DocData> docDataList = new ArrayList<>();
    docDataList.add(docData);
    corpus.setDocDataList(docDataList);
    boolean result = corpus.isPersistentDocument(0);
    assertTrue(result);
}

@Test
public void test10()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = new SimpleDocumentImpl();
    corpus.getDocumentList().add(doc);
    corpus.getDocDataList().add(new DocumentData(doc.getName(), doc));
    assertEquals(1, corpus.getDocuments().size());
    assertEquals(1, corpus.getDocDataList().size());
    boolean result = corpus.remove(doc);
    assertTrue(result);
    assertEquals(0, corpus.getDocuments().size());
    assertEquals(0, corpus.getDocDataList().size());
}

@Test
public void test11()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl(null, "TestCorpus"));
    Document doc1 = mock(Document.class);
    Document doc2 = mock(Document.class);
    Document doc3 = mock(Document.class);
    doReturn(true).when(corpus).remove(doc1);
    doReturn(false).when(corpus).remove(doc2);
    doReturn(true).when(corpus).remove(doc3);
    Collection<Document> documents = Arrays.asList(doc1, doc2, doc3);
    boolean result = corpus.removeAll(documents);
    assertFalse(result);
    verify(corpus).remove(doc1);
    verify(corpus).remove(doc2);
    verify(corpus).remove(doc3);
}

@Test
public void test12()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<String> sampleCollection = Arrays.asList("token1", "token2");
    corpus.retainAll(sampleCollection);
}

@Test
public void test13()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    SerialDataStore mockDataStore = mock(SerialDataStore.class);
    corpus.setDataStore(mockDataStore);
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData mockDocData = mock(DocumentData.class);
    when(mockDocData.getPersistentID()).thenReturn("dummyID");
    when(mockDocData.getClassType()).thenReturn("gate.corpora.DocumentImpl");
    docDataList.add(mockDocData);
    List<Document> documents = new ArrayList<>();
    documents.add(null);
    Field docDataField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataField.setAccessible(true);
    docDataField.set(corpus, docDataList);
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, documents);
    Document mockDocument = mock(Document.class);
    when(mockDocument.getName()).thenReturn("TestDoc");
    Factory.deleteResource(mockDocument);
    Document createdDoc = ((Document) (Factory.createResource("gate.corpora.DocumentImpl", Factory.newFeatureMap())));
    Factory.deleteResource(createdDoc);
    Factory.createResource = (String className,FeatureMap parameters) -> mockDocument;
    Document result = corpus.get(0);
    assertNotNull("Document should not be null", result);
    assertEquals("Loaded document should be the mock instance", mockDocument, result);
}

@Test
public void test14()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = new Document() {
        @Override
        public String getName() {
            return "doc1";
        }

        @Override
        public Object getLRPersistenceId() {
            return "123";
        }
    };
    corpus.documents = new ArrayList<>();
    corpus.documents.add(mockDoc);
    corpus.docDataList = new ArrayList<>();
    DocumentData docData = new DocumentData("doc1", "file://doc1", null);
    corpus.docDataList.add(docData);
    SerialCorpusImpl.DEBUG = false;
    SerialCorpusImpl corpusSpy = new SerialCorpusImpl() {
        @Override
        protected int findDocument(Document doc) {
            return 0;
        }

        @Override
        protected Object getDocumentPersistentID(int index) {
            return "123";
        }

        @Override
        protected void documentRemoved(String id) {
        }

        @Override
        protected void fireDocumentRemoved(CorpusEvent e) {
        }
    };
    corpusSpy.documents = corpus.documents;
    corpusSpy.docDataList = corpus.docDataList;
    boolean result = corpusSpy.remove(mockDoc);
    assertTrue(result);
    assertEquals(0, corpusSpy.documents.size());
    assertEquals(0, corpusSpy.docDataList.size());
}

@Test
public void test15()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document dummyDocument = null;
    corpus.set(0, dummyDocument);
}

@Test
public void test16()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Resource returnedResource = corpus.init();
    assertSame("init() should return the same instance", corpus, returnedResource);
}

@Test
public void test17()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    IndexDefinition expectedIndexDefinition = new IndexDefinition();
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put(CORPUS_INDEX_DEFINITION_FEATURE_KEY, expectedIndexDefinition);
    corpus.setFeatures(features);
    IndexDefinition actualIndexDefinition = corpus.getIndexDefinition();
    assertSame("The returned IndexDefinition should be the one set in the features map", expectedIndexDefinition, actualIndexDefinition);
}

@Test
public void test18()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    IndexManager expectedIndexManager = new IndexManager() {};
    serialCorpus.indexManager = expectedIndexManager;
    IndexManager actualIndexManager = serialCorpus.getIndexManager();
    assertSame("getIndexManager should return the assigned IndexManager instance", expectedIndexManager, actualIndexManager);
}

@Test
public void test19()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    FeatureMap features = new SimpleFeatureMapImpl();
    IndexStatistics expectedStats = new IndexStatistics();
    features.put(CORPUS_INDEX_STATISTICS_FEATURE_KEY, expectedStats);
    corpus.setFeatures(features);
    IndexStatistics actualStats = corpus.getIndexStatistics();
    assertSame("The returned IndexStatistics should match the one set in the features", expectedStats, actualStats);
}

@Test
public void test20()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = new Document() {
        @Override
        public String getName() {
            return "testDoc";
        }

        @Override
        public String toString() {
            return "testDoc";
        }

        @Override
        public String getSourceUrl() {
            return null;
        }

        @Override
        public void cleanup() {
        }

        @Override
        public URL getSourceUrlOrFile() {
            return null;
        }

        @Override
        public String getEncoding() {
            return null;
        }

        @Override
        public Collection getAnnotations() {
            return null;
        }

        @Override
        public Collection getNamedAnnotationSets() {
            return null;
        }

        @Override
        public void setName(String name) {
        }

        @Override
        public void setSourceUrl(URL sourceUrl) {
        }

        @Override
        public void setSourceUrlOrFile(URL sourceUrlOrFile) {
        }

        @Override
        public void setEncoding(String encoding) {
        }

        @Override
        public void setContent(DocumentContent content) {
        }

        @Override
        public DocumentContent getContent() {
            return null;
        }

        @Override
        public void setDocumentFeatures(FeatureMap features) {
        }

        @Override
        public FeatureMap getFeatures() {
            return null;
        }

        @Override
        public void setAnnotations(AnnotationSet annotations) {
        }

        @Override
        public AnnotationSet getAnnotations(String name) {
            return null;
        }

        @Override
        public void setNamedAnnotationSets(NamedAnnotationSets sets) {
        }

        @Override
        public String getMimeType() {
            return null;
        }

        @Override
        public Object getLRPersistenceId() {
            return "persist123";
        }
    };
    corpus.getDocuments().add(doc);
    DocumentData docData = new DocumentData("otherDoc", "id456", "gate.Document", null);
    corpus.getDocumentDataList().add(docData);
    int result = corpus.findDocument(doc);
    assertEquals(0, result);
}

@Test
public void test21()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    String nonDocumentObject = "NotADocument";
    int result = corpus.indexOf(nonDocumentObject);
    assertEquals(-1, result);
}

@Test
public void test22()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Object dummyObject = new Object();
    corpus.lastIndexOf(dummyObject);
}

@Test
public void test23()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = new DummyDocument("Document 1");
    Document doc2 = new DummyDocument("Document 2");
    Document doc3 = new DummyDocument("Document 3");
    corpus.getDocumentList().add(doc1);
    corpus.getDocumentList().add(doc2);
    corpus.getDocumentList().add(doc3);
    int result = corpus.size();
    assertEquals(3, result);
}

@Test
public void test24()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Object> docDataList = new ArrayList<>();
    List<Document> documents = new ArrayList<>();
    try {
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docDataList);
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documents);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Document result = corpus.get(0);
    assertNull("Expected null when index is out of bounds", result);
}

@Test
public void test25()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData mockDocData = mock(DocumentData.class);
    Object expectedPersistentID = "doc-123";
    when(mockDocData.getPersistentID()).thenReturn(expectedPersistentID);
    docDataList.add(mockDocData);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set up test: " + e.getMessage());
    }
    Object actualPersistentID = corpus.getDocumentPersistentID(0);
    assertEquals(expectedPersistentID, actualPersistentID);
}

@Test
public void test26()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Object result = corpus.getTransientSource();
    assertNull("Expected getTransientSource() to return null", result);
}

@Test
public void test27()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    List<SerialCorpusImpl.DocumentData> docDataList = new ArrayList<>();
    List<Document> documents = new ArrayList<>();
    SerialCorpusImpl.DocumentData docData = mock(DocumentData.class);
    when(docData.getDocumentName()).thenReturn("mockDocName");
    docDataList.add(docData);
    documents.add(mockDoc);
    corpus.setDocDataList(docDataList);
    corpus.setDocuments(documents);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(0).when(spyCorpus).findDocument(mockDoc);
    doReturn("persistent-id").when(spyCorpus).getDocumentPersistentID(0);
    doNothing().when(spyCorpus).fireDocumentRemoved(any(CorpusEvent.class));
    boolean result = spyCorpus.remove(mockDoc);
    assertTrue(result);
    assertEquals(0, spyCorpus.getDocuments().size());
    assertEquals(0, spyCorpus.getDocDataList().size());
}

@Test
public void test28()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document dummyDocument = null;
    corpus.set(0, dummyDocument);
}

@Test
public void test29()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    serialCorpus.toArray();
}

@Test
public void test30()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.toArray();
}

@Test
public void test31()
{
}
{
    docDataList = new ArrayList<>();
    docDataList.add(new DocumentData() {
        @Override
        public String getClassType() {
            return "my.document.TypeA";
        }
    });
}

@Test
public void test32()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData mockDocData = new DocumentData();
    mockDocData.setDocumentName("TestDoc");
    docDataList.add(mockDocData);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Failed to set up test: " + e.getMessage());
    }
    String result = corpus.getDocumentName(0);
    assertEquals("TestDoc", result);
}

@Test
public void test33()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    try {
        List<String> docDataList = new ArrayList<>();
        docDataList.add("docMeta1");
        docDataList.add("docMeta2");
        List<Document> documents = new ArrayList<>();
        Document mockDoc1 = Mockito.mock(Document.class);
        Document mockDoc2 = Mockito.mock(Document.class);
        documents.add(mockDoc1);
        documents.add(mockDoc2);
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docDataList);
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documents);
    } catch (Exception e) {
        throw new RuntimeException("Failed to set up test context via reflection", e);
    }
    String expected = ((("document data [docMeta1, docMeta2] documents [" + corpus.get(0)) + ", ") + corpus.get(1)) + "]";
    String actual = corpus.toString();
    assertEquals(expected, actual);
}

@Test
public void test34()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    DocumentData mockDocData = mock(DocumentData.class);
    Document mockDocument = mock(Document.class);
    List<DocumentData> mockDocDataList = new ArrayList<>();
    mockDocDataList.add(mockDocData);
    Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataListField.setAccessible(true);
    docDataListField.set(corpus, mockDocDataList);
    doReturn(mockDocument).when(corpus).get(0);
    Iterator<Document> iterator = corpus.iterator();
    assertTrue(iterator.hasNext());
    Document result = iterator.next();
    assertSame(mockDocument, result);
    assertFalse(iterator.hasNext());
}

@Test
public void test35()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData doc1 = new DocumentData();
    DocumentData doc2 = new DocumentData();
    doc1.setClassType("TypeA");
    doc2.setClassType("TypeB");
    List<DocumentData> docDataList = new ArrayList<DocumentData>();
    docDataList.add(doc1);
    docDataList.add(doc2);
    corpus.docDataList = docDataList;
    List<String> result = corpus.getDocumentClassTypes();
    assertEquals(2, result.size());
    assertEquals("TypeA", result.get(0));
    assertEquals("TypeB", result.get(1));
}

@Test
public void test36()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Object> mockDocDataList = new ArrayList<Object>();
    DocumentData doc1 = new DocumentData();
    doc1.setDocumentName("Doc1");
    DocumentData doc2 = new DocumentData();
    doc2.setDocumentName("Doc2");
    mockDocDataList.add(doc1);
    mockDocDataList.add(doc2);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, mockDocDataList);
    } catch (Exception e) {
        fail("Failed to set docDataList field via reflection: " + e.getMessage());
    }
    List<String> result = corpus.getDocumentNames();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("Doc1", result.get(0));
    assertEquals("Doc2", result.get(1));
}

@Test
public void test37()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData doc1 = mock(DocumentData.class);
    DocumentData doc2 = mock(DocumentData.class);
    Object persistentId1 = "doc-001";
    Object persistentId2 = "doc-002";
    when(doc1.getPersistentID()).thenReturn(persistentId1);
    when(doc2.getPersistentID()).thenReturn(persistentId2);
    List<DocumentData> docDataList = new ArrayList<DocumentData>();
    docDataList.add(doc1);
    docDataList.add(doc2);
    corpus.docDataList = docDataList;
    List<Object> result = corpus.getDocumentPersistentIDs();
    assertEquals(2, result.size());
    assertEquals(persistentId1, result.get(0));
    assertEquals(persistentId2, result.get(1));
}

@Test
public void test38()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.subList(0, 1);
}

@Test
public void test39()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ListIterator<Document> iterator = corpus.listIterator();
}

@Test
public void test40()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ListIterator<Document> iterator = corpus.listIterator();
}

@Test
public void test41()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "testCorpusDir");
    tempDir.mkdir();
    File sampleFile = new File(tempDir, "doc.txt");
    sampleFile.createNewFile();
    Files.write(sampleFile.toPath(), "Sample content".getBytes());
    URL directoryUrl = tempDir.toURI().toURL();
    FileFilter filter = null;
    String encoding = "UTF-8";
    boolean recurse = false;
    corpus.populate(directoryUrl, filter, encoding, recurse);
    assertEquals(1, corpus.size());
    sampleFile.delete();
    tempDir.delete();
}

@Test
public void test42()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    CorpusListener mockListener = new CorpusListener() {};
    Field field = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
    field.setAccessible(true);
    field.set(corpus, null);
    corpus.addCorpusListener(mockListener);
    @SuppressWarnings("unchecked")
    Vector<CorpusListener> listeners = ((Vector<CorpusListener>) (field.get(corpus)));
    assertNotNull(listeners);
    assertEquals(1, listeners.size());
    assertTrue(listeners.contains(mockListener));
}

@Test
public void test43()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    CorpusListener listener = new CorpusListener() {};
    Vector<CorpusListener> initialListeners = new Vector<>();
    initialListeners.add(listener);
    corpus.corpusListeners = initialListeners;
    corpus.removeCorpusListener(listener);
    assertNotSame("CorpusListeners vector should be a clone", initialListeners, corpus.corpusListeners);
    assertFalse("Listener should be removed from corpusListeners", corpus.corpusListeners.contains(listener));
}

@Test
public void test44()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    boolean result = corpus.add(null);
    assertFalse(result);
}

@Test
public void test45()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setDataStore(dataStore);
    Document doc = new Document() {
        @Override
        public DataStore getDataStore() {
            return dataStore;
        }

        @Override
        public Object getLRPersistenceId() {
            return UUID.randomUUID();
        }

        @Override
        public String getName() {
            return "TestDoc";
        }

        @Override
        public void setName(String name) {
        }

        @Override
        public void cleanup() {
        }

        @Override
        public Object getContent() {
            return null;
        }

        @Override
        public void setContent(Object content) {
        }

        @Override
        public String getEncoding() {
            return null;
        }

        @Override
        public void setEncoding(String encoding) {
        }

        @Override
        public AnnotationSet getAnnotations() {
            return null;
        }

        @Override
        public AnnotationSet getAnnotations(String name) {
            return null;
        }

        @Override
        public Long getCreationTime() {
            return null;
        }

        @Override
        public boolean isAnnotationSetPersistent() {
            return false;
        }

        @Override
        public void setAnnotationSetPersistent(boolean persistent) {
        }

        @Override
        public Class<?> getClass() {
            return Document.class;
        }
    };
    boolean result = corpus.add(doc);
    assertTrue("Document from same datastore should be added successfully", result);
}

@Test
public void test46()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Field corpusListenersField = SerialCorpusImpl.class.getDeclaredField("corpusListeners");
    corpusListenersField.setAccessible(true);
    corpusListenersField.set(corpus, new ArrayList<>());
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    List<Document> documents = new ArrayList<>();
    documents.add(mock(Document.class));
    documentsField.set(corpus, documents);
    Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataListField.setAccessible(true);
    List<Object> docDataList = new ArrayList<>();
    docDataList.add(new Object());
    docDataListField.set(corpus, docDataList);
    DataStore mockDataStore = mock(DataStore.class);
    Field dataStoreField = SerialCorpusImpl.class.getDeclaredField("dataStore");
    dataStoreField.setAccessible(true);
    dataStoreField.set(corpus, mockDataStore);
    Gate.getCreoleRegister().addCreoleListener(corpus);
    corpus.cleanup();
    assertNull("corpusListeners should be null after cleanup", corpusListenersField.get(corpus));
    @SuppressWarnings("unchecked")
    List<Document> clearedDocs = ((List<Document>) (documentsField.get(corpus)));
    assertNotNull("documents list itself should not be null", clearedDocs);
    assertTrue("documents list should be empty", clearedDocs.isEmpty());
    @SuppressWarnings("unchecked")
    List<Object> clearedDocDataList = ((List<Object>) (docDataListField.get(corpus)));
    assertNotNull("docDataList should not be null", clearedDocDataList);
    assertTrue("docDataList should be empty", clearedDocDataList.isEmpty());
    assertFalse("corpus should be removed as CreoleListener", Gate.getCreoleRegister().getCreoleListeners().contains(corpus));
    verify(mockDataStore).removeDatastoreListener(corpus);
}

@Test
public void test47()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = Factory.newDocument("Test document 1");
    Document doc2 = Factory.newDocument("Test document 2");
    corpus.add(doc1);
    corpus.add(doc2);
    assertEquals(2, corpus.size());
    corpus.clear();
    assertEquals(0, corpus.size());
    List<?> docDataListField = ((List<?>) (SerialCorpusImpl.class.getDeclaredField("docDataList").get(corpus)));
    assertTrue(docDataListField.isEmpty());
}

@Test
public void test48()
{
    DataStore mockDatastore = mock(DataStore.class);
    CreoleEvent mockEvent = mock(CreoleEvent.class);
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    doReturn(mockDatastore).when(corpus).getDataStore();
    when(mockEvent.getDatastore()).thenReturn(mockDatastore);
    corpus.datastoreClosed(mockEvent);
    verify(mockDatastore).removeDatastoreListener(corpus);
    verifyStatic(Factory.class);
    Factory.deleteResource(corpus);
}

@Test
public void test49()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore mockDataStore = mock(DataStore.class);
    when(mockDataStore.equals(any())).thenAnswer(( invocation) -> invocation.getArgument(0) == mockDataStore);
    corpus.setDataStore(mockDataStore);
    DocumentData mockDocData = mock(DocumentData.class);
    Object docID = "doc123";
    when(mockDocData.getPersistentID()).thenReturn(docID);
    List<DocumentData> docDataList = new ArrayList<>();
    docDataList.add(mockDocData);
    corpus.setDocDataList(docDataList);
    List<Object> documents = new ArrayList<>();
    documents.add(new Object());
    corpus.setDocuments(documents);
    DatastoreEvent evt = mock(DatastoreEvent.class);
    when(evt.getSource()).thenReturn(mockDataStore);
    when(evt.getResourceID()).thenReturn(docID);
    when(evt.getResource()).thenReturn(null);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doNothing().when(spyCorpus).documentRemoved(docID.toString());
    doNothing().when(mockDataStore).sync(spyCorpus);
    spyCorpus.resourceDeleted(evt);
    assertEquals(0, spyCorpus.getDocDataList().size());
    assertEquals(0, spyCorpus.getDocuments().size());
}

@Test
public void test50()
{
    Document mockDoc = mock(Document.class);
    Object mockDataStore1 = new Object();
    Object mockDataStore2 = new Object();
    when(mockDoc.getDataStore()).thenReturn(mockDataStore2);
    CreoleEvent mockEvent = mock(CreoleEvent.class);
    when(mockEvent.getResource()).thenReturn(mockDoc);
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setDataStore(mockDataStore1);
    corpus.add(mockDoc);
    assertEquals(1, corpus.size());
    assertEquals(mockDoc, corpus.get(0));
    corpus.resourceUnloaded(mockEvent);
    assertEquals(0, corpus.size());
}

@Test
public void test51()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    UUID mockId = UUID.randomUUID();
    DatastoreEvent mockEvent = mock(DatastoreEvent.class);
    when(mockEvent.getResourceID()).thenReturn(mockId);
    doReturn(mockId).when(corpus).getLRPersistenceId();
    corpus.resourceWritten(mockEvent);
    verify(corpus).thisResourceWritten();
}

@Test
public void test52()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ArrayList<DocumentData> docDataList = new ArrayList<DocumentData>();
    DocumentData docData = new DocumentData();
    docDataList.add(docData);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    Object expectedPersistentID = "doc-123";
    corpus.setDocumentPersistentID(0, expectedPersistentID);
    assertEquals(expectedPersistentID, docDataList.get(0).getPersistentID());
}

@Test
public void test53()
{
    IndexDefinition mockDefinition = mock(IndexDefinition.class);
    String engineClassName = "test.MockIREngine";
    when(mockDefinition.getIrEngineClassName()).thenReturn(engineClassName);
    IndexManager mockIndexManager = mock(IndexManager.class);
    class MockIREngine implements IREngine {
        public MockIREngine() {
        }

        public IndexManager getIndexmanager() {
            return mockIndexManager;
        }
    }
    ClassLoader mockLoader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (name.equals(engineClassName)) {
                return MockIREngine.class;
            }
            return getClass().getClassLoader().loadClass(name);
        }
    };
    Gate.init();
    Field classLoaderField = Gate.class.getDeclaredField("classLoader");
    classLoaderField.setAccessible(true);
    classLoaderField.set(null, mockLoader);
    StringWriter errorCapture = new StringWriter();
    PrintWriter mockWriter = new PrintWriter(errorCapture);
    Field errWriterField = Err.class.getDeclaredField("err");
    errWriterField.setAccessible(true);
    errWriterField.set(null, mockWriter);
    SerialCorpusImpl corpus = new SerialCorpusImpl() {
        private Map<Object, Object> features = new SimpleFeatureMapImpl();

        @Override
        public Map<Object, Object> getFeatures() {
            return features;
        }
    };
    corpus.setIndexDefinition(mockDefinition);
    assertSame(mockDefinition, corpus.getFeatures().get(CORPUS_INDEX_DEFINITION_FEATURE_KEY));
    assertNotNull(corpus.addedDocs);
    assertTrue(corpus.addedDocs instanceof Vector);
    assertNotNull(corpus.removedDocIDs);
    assertTrue(corpus.removedDocIDs instanceof Vector);
    assertNotNull(corpus.changedDocs);
    assertTrue(corpus.changedDocs instanceof Vector);
    verify(mockIndexManager).setIndexDefinition(mockDefinition);
    verify(mockIndexManager).setCorpus(corpus);
}

@Test
public void test54()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    Corpus mockCorpus = mock(Corpus.class);
    when(mockCorpus.getName()).thenReturn("TestCorpus");
    FeatureMap mockFeatures = mock(FeatureMap.class);
    when(mockCorpus.getFeatures()).thenReturn(mockFeatures);
    List<String> docNames = new ArrayList<>();
    docNames.add("Doc1");
    when(mockCorpus.getDocumentNames()).thenReturn(docNames);
    Document mockDoc = mock(Document.class);
    when(mockDoc.getClass()).thenReturn(((Class) (Document.class)));
    when(mockCorpus.get(0)).thenReturn(mockDoc);
    List<Document> docs = new ArrayList<>();
    docs.add(mockDoc);
    when(mockCorpus.iterator()).thenReturn(docs.iterator());
    when(mockCorpus.size()).thenReturn(1);
    when(mockCorpus.get(0)).thenReturn(mockDoc);
    serialCorpus.setTransientSource(mockCorpus);
    assertEquals("TestCorpus", serialCorpus.getName());
    assertSame(mockFeatures, serialCorpus.getFeatures());
    assertNotNull(serialCorpus.getDocuments());
    assertEquals(1, serialCorpus.getDocuments().size());
    assertSame(mockDoc, serialCorpus.getDocuments().get(0));
    assertNotNull(serialCorpus.getDocDataList());
    assertEquals(1, serialCorpus.getDocDataList().size());
    DocumentData docData = serialCorpus.getDocDataList().get(0);
    assertEquals("Doc1", docData.getName());
    assertEquals(Document.class.getName(), docData.getClassName());
    assertNotNull(serialCorpus.getAddedDocs());
    assertTrue(serialCorpus.getAddedDocs().isEmpty());
    assertNotNull(serialCorpus.getRemovedDocIDs());
    assertTrue(serialCorpus.getRemovedDocIDs().isEmpty());
    assertNotNull(serialCorpus.getChangedDocs());
    assertTrue(serialCorpus.getChangedDocs().isEmpty());
}

@Test
public void test55()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null);
    Document adoptedDoc = mock(Document.class);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("docId123");
    DataStore mockStore = mock(DataStore.class);
    when(mockStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    doReturn(true).when(corpus).isDocumentLoaded(0);
    doReturn(true).when(corpus).isPersistentDocument(0);
    doReturn(mockStore).when(corpus).getDataStore();
    List<Document> docs = new ArrayList<>();
    docs.add(mockDoc);
    corpus.documents = docs;
    corpus.unloadDocument(0, true);
    verify(mockStore).sync(adoptedDoc);
    verify(corpus).setDocumentPersistentID(0, "docId123");
    assertNull(corpus.documents.get(0));
}

@Test
public void test56()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDocument = mock(Document.class);
    when(mockDocument.getLRPersistenceId()).thenReturn("persisted-id");
    List<Document> mockDocumentList = new ArrayList<>();
    mockDocumentList.add(mockDocument);
    Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
    documentsField.setAccessible(true);
    documentsField.set(corpus, mockDocumentList);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(true).when(spyCorpus).isDocumentLoaded(0);
    doReturn(true).when(spyCorpus).isPersistentDocument(0);
    spyCorpus.unloadDocument(0, false);
    List<Document> updatedDocs = ((List<Document>) (documentsField.get(spyCorpus)));
    assertNull("Document should be set to null after unload", updatedDocs.get(0));
}

@Test
public void test57()
{
    int index = 0;
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null).thenReturn("persistent-id");
    List<Document> docs = new ArrayList<>();
    docs.add(mockDoc);
    corpus.documents = docs;
    DataStore mockDataStore = mock(DataStore.class);
    Document adoptedDoc = mock(Document.class);
    when(mockDataStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("persistent-id");
    doNothing().when(mockDataStore).sync(adoptedDoc);
    doReturn(mockDataStore).when(corpus).getDataStore();
    doReturn(true).when(corpus).isDocumentLoaded(index);
    doReturn(true).when(corpus).isPersistentDocument(index);
    doNothing().when(corpus).setDocumentPersistentID(index, "persistent-id");
    corpus.unloadDocument(index, true);
    assertNull("Document should be removed from memory (set to null)", corpus.documents.get(index));
    verify(mockDataStore).adopt(mockDoc);
    verify(mockDataStore).sync(adoptedDoc);
    verify(corpus).setDocumentPersistentID(index, "persistent-id");
}

@Test
public void test58()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null, "generated-persistence-id");
    DataStore mockDataStore = mock(DataStore.class);
    Document adoptedDoc = mock(Document.class);
    when(mockDataStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    doNothing().when(mockDataStore).sync(adoptedDoc);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("generated-persistence-id");
    int index = 0;
    List<Document> docList = new ArrayList<>();
    docList.add(mockDoc);
    corpus.setDocuments(docList);
    corpus.setDocumentLoaded(index, true);
    corpus.setPersistentDocument(index, false);
    corpus.setDataStore(mockDataStore);
    corpus.unloadDocument(index, true);
    assertNull("Document should be removed from memory (set to null)", corpus.getDocuments().get(index));
    verify(mockDataStore).adopt(mockDoc);
    verify(mockDataStore).sync(adoptedDoc);
}

