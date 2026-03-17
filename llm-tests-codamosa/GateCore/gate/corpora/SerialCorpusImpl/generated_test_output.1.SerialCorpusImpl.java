import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore mockDataStore = mock(DataStore.class);
    corpus.setDataStore(mockDataStore);
    corpus.docDataList = new ArrayList<>();
    corpus.documents = new ArrayList<>();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getDataStore()).thenReturn(mockDataStore);
    when(mockDoc.getName()).thenReturn("doc1");
    when(mockDoc.getLRPersistenceId()).thenReturn("persistId1");
    when(mockDoc.getClass()).thenReturn(Document.class);
    boolean result = corpus.add(mockDoc);
    assertTrue("Expected add() to return true for document from same datastore", result);
    assertEquals(1, corpus.docDataList.size());
    assertEquals(1, corpus.documents.size());
    assertEquals("doc1", corpus.docDataList.get(0).name);
}

@Test
public void test2()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore dataStore = mock(DataStore.class);
    corpus.setDataStore(dataStore);
    Document document = mock(Document.class);
    when(document.getDataStore()).thenReturn(dataStore);
    when(document.getName()).thenReturn("testDoc");
    when(document.getLRPersistenceId()).thenReturn("12345");
    when(document.getClass()).thenReturn(Document.class);
    corpus.docDataList = new ArrayList<DocumentData>();
    corpus.documents = new ArrayList<Document>();
    boolean result = corpus.add(document);
    assertTrue("Expected the add method to return true for a valid document from same datastore", result);
    assertEquals("Expected document list size to be 1", 1, corpus.documents.size());
    assertEquals("Expected docDataList size to be 1", 1, corpus.docDataList.size());
    assertSame("Expected added document to be the same as input", document, corpus.documents.get(0));
}

@Test
public void test3()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc1 = new Document() {
        @Override
        public String getName() {
            return "doc1";
        }
    };
    Document doc2 = new Document() {
        @Override
        public String getName() {
            return "doc2";
        }
    };
    SerialCorpusImpl testCorpus = new SerialCorpusImpl() {
        private int count = 0;

        @Override
        public boolean add(Document doc) {
            count++;
            return count != 2;
        }
    };
    Collection<Document> documents = new ArrayList<>();
    documents.add(doc1);
    documents.add(doc2);
    boolean result = testCorpus.addAll(documents);
    assertFalse("Expected addAll to return false when one add fails", result);
}

@Test
public void test4()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documentList = new ArrayList<>();
    Document existingDoc = mock(Document.class);
    when(existingDoc.getName()).thenReturn("doc1");
    when(existingDoc.getPersistentID()).thenReturn(123L);
    documentList.add(existingDoc);
    Field docDataField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataField.setAccessible(true);
    docDataField.set(corpus, documentList);
    Document queryDoc = mock(Document.class);
    when(queryDoc.getName()).thenReturn("doc1");
    when(queryDoc.getPersistentID()).thenReturn(123L);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(0).when(spyCorpus).findDocument(queryDoc);
    assertTrue(spyCorpus.contains(queryDoc));
}

@Test
public void test5()
{
    SerialCorpusImpl corpus1 = new SerialCorpusImpl();
    SerialCorpusImpl corpus2 = new SerialCorpusImpl();
    String persistentId = UUID.randomUUID().toString();
    corpus1.setLRPersistenceId(persistentId);
    corpus2.setLRPersistenceId(persistentId);
    String name = "testCorpus";
    corpus1.setName(name);
    corpus2.setName(name);
    DataStore dataStore = new MockDataStore();
    corpus1.setDataStore(dataStore);
    corpus2.setDataStore(dataStore);
    List<DocumentData> docList = new ArrayList<DocumentData>();
    DocumentData docData = new DocumentData();
    docList.add(docData);
    corpus1.docDataList = docList;
    corpus2.docDataList = new ArrayList<DocumentData>();
    corpus2.docDataList.add(docData);
    assertTrue(corpus1.equals(corpus2));
}

@Test
public void test6()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documentList = new ArrayList<Document>();
    documentList.add(new DocumentImpl());
    try {
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documentList);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to set up test due to reflection error: " + e.getMessage());
    }
    boolean result = corpus.isDocumentLoaded(0);
    assertTrue("Expected document to be loaded at index 0", result);
}

@Test
public void test7()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documents = new ArrayList<>();
    documents.add(new Document() {});
    corpus.setDocuments(documents);
    SerialCorpusImpl.DocData docData = corpus.new DocData();
    docData.setPersistentID(new PersistentCorpusID());
    List<SerialCorpusImpl.DocData> docDataList = new ArrayList<>();
    docDataList.add(docData);
    corpus.setDocDataList(docDataList);
    boolean result = corpus.isPersistentDocument(0);
    assertTrue(result);
}

@Test
public void test8()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = Mockito.mock(Document.class);
    Mockito.when(mockDoc.getName()).thenReturn("testDoc");
    List<Object> documents = corpus.getDocuments();
    List<DocumentData> docDataList = corpus.getDocDataList();
    documents.add(mockDoc);
    DocumentData docData = new DocumentData(new SimpleFeatureMapImpl());
    docData.setDocumentName("testDoc");
    docDataList.add(docData);
    SerialCorpusImpl spyCorpus = Mockito.spy(corpus);
    Mockito.doReturn("12345").when(spyCorpus).getDocumentPersistentID(0);
    Field debugField;
    try {
        debugField = SerialCorpusImpl.class.getDeclaredField("DEBUG");
        debugField.setAccessible(true);
        debugField.setBoolean(null, false);
    } catch (Exception e) {
        fail("Failed to disable DEBUG flag: " + e.getMessage());
    }
    boolean result = spyCorpus.remove(mockDoc);
    assertTrue(result);
    assertFalse(spyCorpus.getDocuments().contains(mockDoc));
    assertEquals(0, spyCorpus.getDocuments().size());
}

@Test
public void test9()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl("TestCorpus");
    Document doc1 = new DocumentImpl();
    Document doc2 = new DocumentImpl();
    corpus.add(doc1);
    corpus.add(doc2);
    Collection<Document> docsToRemove = Arrays.asList(doc1, doc2);
    boolean result = corpus.removeAll(docsToRemove);
    assertTrue("Expected all documents to be removed", result);
    assertFalse("Corpus should not contain doc1", corpus.contains(doc1));
    assertFalse("Corpus should not contain doc2", corpus.contains(doc2));
}

@Test
public void test10()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    Collection<String> sampleCollection = Arrays.asList("item1", "item2");
    serialCorpus.retainAll(sampleCollection);
}

@Test
public void test11()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDocument = Mockito.mock(Document.class);
    List<Document> documents = new ArrayList<>();
    documents.add(mockDocument);
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData mockDocData = Mockito.mock(DocumentData.class);
    docDataList.add(mockDocData);
    try {
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documents);
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docDataList);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    Document result = corpus.get(0);
    assertSame("Should return the cached document", mockDocument, result);
}

@Test
public void test12()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = mock(Document.class);
    List<Object> documents = new ArrayList<>();
    List<DocumentData> docDataList = new ArrayList<>();
    DocumentData docData = mock(DocumentData.class);
    when(docData.getDocumentName()).thenReturn("TestDocName");
    documents.add(doc);
    docDataList.add(docData);
    try {
        Field documentsField = SerialCorpusImpl.class.getDeclaredField("documents");
        documentsField.setAccessible(true);
        documentsField.set(corpus, documents);
        Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
        docDataListField.setAccessible(true);
        docDataListField.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Failed to set up test data: " + e.getMessage());
    }
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(0).when(spyCorpus).findDocument(doc);
    doReturn("persistent-id-123").when(spyCorpus).getDocumentPersistentID(0);
    doNothing().when(spyCorpus).documentRemoved("persistent-id-123");
    doNothing().when(spyCorpus).fireDocumentRemoved(any(CorpusEvent.class));
    boolean result = spyCorpus.remove(doc);
    assertTrue("Expected remove to return true for existing document", result);
    assertEquals("Expected document to be removed from documents list", 0, spyCorpus.documents.size());
    assertEquals("Expected document to be removed from docDataList list", 0, spyCorpus.docDataList.size());
}

@Test
public void test13()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document dummyDocument = new Document() {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public void setName(String name) {
        }

        @Override
        public String getSourceUrl() {
            return null;
        }

        @Override
        public void setSourceUrl(String sourceUrl) {
        }

        @Override
        public List getAnnotations() {
            return null;
        }

        @Override
        public Object getFeatures() {
            return null;
        }

        @Override
        public void cleanup() {
        }

        @Override
        public String toXml() {
            return null;
        }

        @Override
        public String toXml(Set set) {
            return null;
        }

        @Override
        public String toXml(Set set, boolean b) {
            return null;
        }

        @Override
        public String toXml(Set set, boolean b, boolean b1) {
            return null;
        }

        @Override
        public String toXml(Set set, boolean b, boolean b1, boolean b2) {
            return null;
        }

        @Override
        public String getMimeType() {
            return null;
        }
    };
    corpus.set(0, dummyDocument);
}

@Test
public void test14()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    Factory.DuplicationContext context = new Factory.DuplicationContext();
    serialCorpus.duplicate(context);
}

@Test
public void test15()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Resource returnedResource = corpus.init();
    assertSame("init() should return the same instance (this)", corpus, returnedResource);
}

@Test
public void test16()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    FeatureMap features = new SimpleFeatureMapImpl();
    IndexDefinition mockIndexDefinition = new IndexDefinition();
    features.put(CORPUS_INDEX_DEFINITION_FEATURE_KEY, mockIndexDefinition);
    corpus.setFeatures(features);
    IndexDefinition result = corpus.getIndexDefinition();
    assertSame(mockIndexDefinition, result);
}

@Test
public void test17()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    IndexManager expectedIndexManager = new DummyIndexManager();
    Field field = SerialCorpusImpl.class.getDeclaredField("indexManager");
    field.setAccessible(true);
    field.set(corpus, expectedIndexManager);
    IndexManager actualIndexManager = corpus.getIndexManager();
    assertSame("getIndexManager should return the indexManager field", expectedIndexManager, actualIndexManager);
}

@Test
public void test18()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    FeatureMap features = Factory.newFeatureMap();
    IndexStatistics expectedStatistics = mock(IndexStatistics.class);
    features.put(CORPUS_INDEX_STATISTICS_FEATURE_KEY, expectedStatistics);
    corpus.setFeatures(features);
    IndexStatistics result = corpus.getIndexStatistics();
    assertSame("Expected the same IndexStatistics object from features", expectedStatistics, result);
}

@Test
public void test19()
{
    Document mockDoc = mock(Document.class);
    when(mockDoc.getName()).thenReturn("testDoc");
    UUID persistentId = UUID.randomUUID();
    when(mockDoc.getLRPersistenceId()).thenReturn(persistentId);
    when(mockDoc.getClass()).thenReturn(((Class) (mock(Document.class).getClass())));
    DocumentData mockDocData = mock(DocumentData.class);
    when(mockDocData.getDocumentName()).thenReturn("testDoc");
    when(mockDocData.getPersistentID()).thenReturn(persistentId);
    when(mockDocData.getClassType()).thenReturn(mockDoc.getClass().getName());
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documentList = new ArrayList<>();
    documentList.add(null);
    corpus.documents = documentList;
    List<DocumentData> docDataList = new ArrayList<>();
    docDataList.add(mockDocData);
    corpus.docDataList = docDataList;
    int result = corpus.findDocument(mockDoc);
    assertEquals(0, result);
}

@Test
public void test20()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = new DocumentImpl();
    corpus.add(doc);
    int index = corpus.indexOf(doc);
    assertEquals(0, index);
}

@Test
public void test21()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.lastIndexOf("test");
}

@Test
public void test22()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDocument1 = new DocumentContentImpl("Dummy text 1");
    Document mockDocument2 = new DocumentContentImpl("Dummy text 2");
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        List<Document> docList = new ArrayList<Document>();
        docList.add(mockDocument1);
        docList.add(mockDocument2);
        field.set(corpus, docList);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    assertEquals(2, corpus.size());
}

@Test
public void test23()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<Document> documents = new ArrayList<>();
    List<DocumentData> docDataList = new ArrayList<>();
    corpus.setDocuments(documents);
    corpus.setDocDataList(docDataList);
    int outOfBoundsIndex = 0;
    Document result = corpus.get(outOfBoundsIndex);
    assertNull(result);
}

@Test
public void test24()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docList = new ArrayList<DocumentData>();
    DocumentData mockDocData = new DocumentData("mockDoc", null, null);
    mockDocData.setPersistentID("doc-123");
    docList.add(mockDocData);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docList);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Object result = corpus.getDocumentPersistentID(0);
    assertEquals("doc-123", result);
}

@Test
public void test25()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    Object result = serialCorpus.getTransientSource();
    assertNull("getTransientSource should return null", result);
}

@Test
public void test26()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document doc = new DocumentImpl();
    corpus.getDocuments().add(doc);
    SerialCorpusImpl.DocumentData data = corpus.new DocumentData();
    data.setDocument(doc);
    data.setDocumentName("SampleDoc");
    corpus.getDocDataList().add(data);
    corpus.getPersistentIDs().add("123");
    boolean result = corpus.remove(doc);
    assertTrue(result);
    assertFalse(corpus.getDocuments().contains(doc));
    assertEquals(0, corpus.getDocDataList().size());
}

@Test
public void test27()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.set(0, null);
}

@Test
public void test28()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.toArray();
}

@Test
public void test29()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.toArray();
}

@Test
public void test30()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> mockDocDataList = new ArrayList<>();
    DocumentData documentData = new DocumentData();
    documentData.setClassType("SampleClassType");
    mockDocDataList.add(documentData);
    Field docDataListField = SerialCorpusImpl.class.getDeclaredField("docDataList");
    docDataListField.setAccessible(true);
    docDataListField.set(corpus, mockDocDataList);
    String result = corpus.getDocumentClassType(5);
    assertNull(result);
}

@Test
public void test31()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<>();
    Document doc = new DocumentImpl();
    doc.setName("TestDoc");
    DocumentData docData = corpus.new DocumentData(doc);
    docDataList.add(docData);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    String name = corpus.getDocumentName(0);
    assertEquals("TestDoc", name);
}

@Test
public void test32()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    List<Object> docDataList = new ArrayList<>();
    docDataList.add("DocData1");
    docDataList.add("DocData2");
    List<Document> documents = new ArrayList<>();
    Document doc1 = new MockDocument("Document1");
    Document doc2 = new MockDocument("Document2");
    documents.add(doc1);
    documents.add(doc2);
    serialCorpus.docDataList = docDataList;
    serialCorpus.documents = documents;
    String expected = "document data [DocData1, DocData2] documents [Document1, Document2]";
    assertEquals(expected, serialCorpus.toString());
}

@Test
public void test33()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDocument = mock(Document.class);
    DocumentData docData = new DocumentData();
    List<DocumentData> docDataList = new ArrayList<>();
    docDataList.add(docData);
    corpus.docDataList = docDataList;
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(mockDocument).when(spyCorpus).get(0);
    Iterator<Document> iterator = spyCorpus.iterator();
    assertTrue(iterator.hasNext());
    Document returnedDoc = iterator.next();
    assertSame(mockDocument, returnedDoc);
}

@Test
public void test34()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<DocumentData>();
    DocumentData doc1 = new DocumentData();
    doc1.setClassType("gate.corpora.XmlDocumentImpl");
    DocumentData doc2 = new DocumentData();
    doc2.setClassType("gate.corpora.JsonDocumentImpl");
    docDataList.add(doc1);
    docDataList.add(doc2);
    corpus.docDataList = docDataList;
    List<String> result = corpus.getDocumentClassTypes();
    assertEquals(2, result.size());
    assertEquals("gate.corpora.XmlDocumentImpl", result.get(0));
    assertEquals("gate.corpora.JsonDocumentImpl", result.get(1));
}

@Test
public void test35()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData doc1 = new DocumentData();
    doc1.setDocumentName("Document1");
    DocumentData doc2 = new DocumentData();
    doc2.setDocumentName("Document2");
    List<Object> docDataList = new ArrayList<Object>();
    docDataList.add(doc1);
    docDataList.add(doc2);
    corpus.docDataList = docDataList;
    List<String> result = corpus.getDocumentNames();
    assertEquals(2, result.size());
    assertEquals("Document1", result.get(0));
    assertEquals("Document2", result.get(1));
}

@Test
public void test36()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List<DocumentData> docDataList = new ArrayList<DocumentData>();
    DocumentData doc1 = new DocumentData();
    doc1.setPersistentID("doc1-id");
    DocumentData doc2 = new DocumentData();
    doc2.setPersistentID("doc2-id");
    docDataList.add(doc1);
    docDataList.add(doc2);
    try {
        Field field = SerialCorpusImpl.class.getDeclaredField("docDataList");
        field.setAccessible(true);
        field.set(corpus, docDataList);
    } catch (Exception e) {
        fail("Failed to set docDataList via reflection: " + e.getMessage());
    }
    List<Object> result = corpus.getDocumentPersistentIDs();
    assertEquals(2, result.size());
    assertEquals("doc1-id", result.get(0));
    assertEquals("doc2-id", result.get(1));
}

@Test
public void test37()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    List result = corpus.subList(0, 1);
}

@Test
public void test38()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ListIterator<Document> iterator = corpus.listIterator();
}

@Test
public void test39()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    ListIterator<?> iterator = corpus.listIterator();
}

@Test
public void test40()
{
    File tempDir = new File(System.getProperty("java.io.tmpdir"), "corpusTestDir");
    tempDir.mkdir();
    File file = new File(tempDir, "doc1.xml");
    FileWriter writer = new FileWriter(file);
    writer.write("<xml>Document content</xml>");
    writer.close();
    URL directoryUrl = tempDir.toURI().toURL();
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setName("TestCorpus");
    corpus.populate(directoryUrl, null, "UTF-8", false);
    assertEquals(1, corpus.size());
    file.delete();
    tempDir.delete();
}

@Test
public void test41()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore commonDataStore = new DataStore() {};
    corpus.setDataStore(commonDataStore);
    Document mockDocument = new Document() {
        @Override
        public DataStore getDataStore() {
            return commonDataStore;
        }

        @Override
        public String getName() {
            return "TestDoc";
        }

        @Override
        public Object getLRPersistenceId() {
            return UUID.randomUUID().toString();
        }

        @Override
        public Class<?> getClass() {
            return this.getClass();
        }
    };
    boolean added = corpus.add(mockDocument);
    assertTrue(added);
}

@Test
public void test42()
{
    DataStore mockDatastore = mock(DataStore.class);
    CreoleEvent mockEvent = mock(CreoleEvent.class);
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    when(mockEvent.getDatastore()).thenReturn(mockDatastore);
    doReturn(mockDatastore).when(corpus).getDataStore();
    corpus.datastoreClosed(mockEvent);
    verify(mockDatastore).removeDatastoreListener(corpus);
    verifyStatic(Factory.class);
    Factory.deleteResource(corpus);
}

@Test
public void test43()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DataStore mockDataStore = mock(DataStore.class);
    corpus.setDataStore(mockDataStore);
    String docIdToDelete = UUID.randomUUID().toString();
    DocumentData mockDocData = mock(DocumentData.class);
    when(mockDocData.getPersistentID()).thenReturn(docIdToDelete);
    corpus.docDataList = new ArrayList<>();
    corpus.documents = new ArrayList<>();
    corpus.docDataList.add(mockDocData);
    corpus.documents.add(mock(Document.class));
    DatastoreEvent mockEvent = mock(DatastoreEvent.class);
    when(mockEvent.getSource()).thenReturn(mockDataStore);
    when(mockEvent.getResourceID()).thenReturn(docIdToDelete);
    when(mockEvent.getResource()).thenReturn(null);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doNothing().when(spyCorpus).documentRemoved(docIdToDelete);
    doNothing().when(mockDataStore).sync(spyCorpus);
    spyCorpus.resourceDeleted(mockEvent);
    assertEquals(0, spyCorpus.docDataList.size());
    assertEquals(0, spyCorpus.documents.size());
    verify(spyCorpus).documentRemoved(docIdToDelete);
    verify(mockDataStore).sync(spyCorpus);
}

@Test
public void test44()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = Mockito.mock(Document.class);
    Mockito.when(mockDoc.getDataStore()).thenReturn(null);
    Mockito.when(mockDoc.getName()).thenReturn("testDoc");
    corpus.setDocuments(new ArrayList<>());
    corpus.getDocuments().add(mockDoc);
    Mockito.when(mockDoc.getSourceUrl()).thenReturn(null);
    corpus.setDataStore(Mockito.mock(DataStore.class));
    Mockito.when(mockDoc.getDataStore()).thenReturn(Mockito.mock(DataStore.class));
    CreoleEvent mockEvent = Mockito.mock(CreoleEvent.class);
    Mockito.when(mockEvent.getResource()).thenReturn(mockDoc);
    corpus.resourceUnloaded(mockEvent);
    assertFalse("Transient document should be removed from corpus", corpus.getDocuments().contains(mockDoc));
}

@Test
public void test45()
{
    SerialCorpusImpl corpus = spy(new SerialCorpusImpl());
    UUID mockUUID = UUID.randomUUID();
    DatastoreEvent mockEvent = mock(DatastoreEvent.class);
    when(mockEvent.getResourceID()).thenReturn(mockUUID);
    doReturn(mockUUID).when(corpus).getLRPersistenceId();
    doNothing().when(corpus).thisResourceWritten();
    corpus.resourceWritten(mockEvent);
    verify(corpus, times(1)).thisResourceWritten();
}

@Test
public void test46()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    DocumentData docData = new DocumentData("doc1", null, null, null);
    corpus.docDataList = new ArrayList<>();
    corpus.docDataList.add(docData);
    Object expectedID = "persistent-123";
    corpus.setDocumentPersistentID(0, expectedID);
    assertEquals(expectedID, corpus.docDataList.get(0).getPersistentID());
}

@Test
public void test47()
{
    IndexManager mockIndexManager = mock(IndexManager.class);
    class StubIREngine implements IREngine {
        @Override
        public IndexManager getIndexmanager() {
            return mockIndexManager;
        }
    }
    String engineClassName = StubIREngine.class.getName();
    IndexDefinition mockDefinition = mock(IndexDefinition.class);
    when(mockDefinition.getIrEngineClassName()).thenReturn(engineClassName);
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    corpus.setIndexDefinition(mockDefinition);
    Map<String, Object> features = corpus.getFeatures();
    assertTrue(features.containsKey(CORPUS_INDEX_DEFINITION_FEATURE_KEY));
    assertEquals(mockDefinition, features.get(CORPUS_INDEX_DEFINITION_FEATURE_KEY));
    verify(mockIndexManager).setIndexDefinition(mockDefinition);
    verify(mockIndexManager).setCorpus(corpus);
    assertNotNull(corpus.addedDocs);
    assertTrue(corpus.addedDocs.isEmpty());
    assertNotNull(corpus.removedDocIDs);
    assertTrue(corpus.removedDocIDs.isEmpty());
    assertNotNull(corpus.changedDocs);
    assertTrue(corpus.changedDocs.isEmpty());
}

@Test
public void test48()
{
    SerialCorpusImpl serialCorpus = new SerialCorpusImpl();
    serialCorpus.setName("OriginalName");
    FeatureMap originalFeatures = Factory.newFeatureMap();
    originalFeatures.put("key", "value");
    serialCorpus.setFeatures(originalFeatures);
    Object nonCorpusSource = new Object();
    serialCorpus.setTransientSource(nonCorpusSource);
    assertEquals("OriginalName", serialCorpus.getName());
    assertEquals("value", serialCorpus.getFeatures().get("key"));
    assertNull(serialCorpus.getDocDataList());
    assertNull(serialCorpus.getDocuments());
}

@Test
public void test49()
{
    final Object[] capturedLrId = new Object[1];
    Document originalDoc = mock(Document.class);
    when(originalDoc.getLRPersistenceId()).thenReturn(null);
    Document adoptedDoc = mock(Document.class);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("persist-123");
    DataStore dataStoreMock = mock(DataStore.class);
    when(dataStoreMock.adopt(originalDoc)).thenReturn(adoptedDoc);
    SerialCorpusImpl corpus = new SerialCorpusImpl() {
        @Override
        protected boolean isDocumentLoaded(int index) {
            return true;
        }

        @Override
        protected boolean isPersistentDocument(int index) {
            return true;
        }

        @Override
        public DataStore getDataStore() {
            return dataStoreMock;
        }

        @Override
        protected void setDocumentPersistentID(int index, Object lrID) {
            capturedLrId[0] = lrID;
        }
    };
    List<Document> docs = new ArrayList<>();
    docs.add(originalDoc);
    corpus.documents = docs;
    corpus.unloadDocument(0, true);
    verify(dataStoreMock).adopt(originalDoc);
    verify(dataStoreMock).sync(adoptedDoc);
    assertEquals("persist-123", capturedLrId[0]);
    assertNull("Document should be removed from memory", corpus.documents.get(0));
}

@Test
public void test50()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null).thenReturn("doc-id");
    Document adoptedDoc = mock(Document.class);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("doc-id");
    DataStore mockDataStore = mock(DataStore.class);
    when(mockDataStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    List<Document> docList = new ArrayList<>();
    docList.add(mockDoc);
    corpus.documents = docList;
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(true).when(spyCorpus).isDocumentLoaded(0);
    doReturn(true).when(spyCorpus).isPersistentDocument(0);
    doReturn(mockDataStore).when(spyCorpus).getDataStore();
    doNothing().when(spyCorpus).setDocumentPersistentID(eq(0), eq("doc-id"));
    spyCorpus.unloadDocument(0, true);
    verify(mockDataStore).adopt(mockDoc);
    verify(mockDataStore).sync(adoptedDoc);
    verify(spyCorpus).setDocumentPersistentID(0, "doc-id");
    assertNull(spyCorpus.documents.get(0));
}

@Test
public void test51()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null, "persisted-id");
    Document adoptedDoc = mock(Document.class);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("persisted-id");
    DataStore mockDataStore = mock(DataStore.class);
    when(mockDataStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    doNothing().when(mockDataStore).sync(adoptedDoc);
    int index = 0;
    List<Document> docList = new ArrayList<>();
    docList.add(mockDoc);
    corpus.documents = docList;
    corpus.setDataStore(mockDataStore);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(true).when(spyCorpus).isDocumentLoaded(index);
    doReturn(true).when(spyCorpus).isPersistentDocument(index);
    doNothing().when(spyCorpus).setDocumentPersistentID(index, "persisted-id");
    spyCorpus.unloadDocument(index, true);
    assertNull("Document should be removed from memory", spyCorpus.documents.get(index));
    verify(mockDataStore).sync(adoptedDoc);
    verify(spyCorpus).setDocumentPersistentID(index, "persisted-id");
}

@Test
public void test52()
{
    SerialCorpusImpl corpus = new SerialCorpusImpl();
    Document mockDoc = mock(Document.class);
    when(mockDoc.getLRPersistenceId()).thenReturn(null);
    DataStore mockStore = mock(DataStore.class);
    Document adoptedDoc = mock(Document.class);
    when(mockStore.adopt(mockDoc)).thenReturn(adoptedDoc);
    when(adoptedDoc.getLRPersistenceId()).thenReturn("persist-id");
    List<Document> docList = new ArrayList<>();
    docList.add(mockDoc);
    corpus.setDocuments(docList);
    corpus.setDataStore(mockStore);
    SerialCorpusImpl spyCorpus = spy(corpus);
    doReturn(true).when(spyCorpus).isDocumentLoaded(0);
    doReturn(true).when(spyCorpus).isPersistentDocument(0);
    doNothing().when(spyCorpus).setDocumentPersistentID(0, "persist-id");
    spyCorpus.unloadDocument(0, true);
    assertNull("Document should be unloaded from memory", spyCorpus.getDocument(0));
    verify(mockStore).adopt(mockDoc);
    verify(mockStore).sync(adoptedDoc);
    verify(spyCorpus).setDocumentPersistentID(0, "persist-id");
}

