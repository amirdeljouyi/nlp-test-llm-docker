import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(mockDoc, null);
    AnnotationSet result = annotationSetImpl.emptyAS();
    assertNotNull("Resulting AnnotationSet should not be null", result);
    assertTrue("Resulting AnnotationSet should be instance of ImmutableAnnotationSetImpl", result instanceof ImmutableAnnotationSetImpl);
    assertEquals("Document in returned AnnotationSet should match original", mockDoc, result.getDocument());
    assertTrue("Returned AnnotationSet should be empty", result.isEmpty());
}

@Test
public void test2()
{
    Node startNode = Factory.newNode(100L);
    Node endNode = Factory.newNode(200L);
    Annotation annotation = Factory.newAnnotation(1L, startNode, endNode, "Token", new FeatureMapImpl());
    Map<Long, Object> nodesByOffset = new HashMap<>();
    Set<Annotation> startOffsetSet = new HashSet<>();
    Set<Annotation> endOffsetSet = new HashSet<>();
    startOffsetSet.add(annotation);
    endOffsetSet.add(annotation);
    nodesByOffset.put(100L, startOffsetSet);
    nodesByOffset.put(200L, endOffsetSet);
    annotationSet.nodesByOffset = nodesByOffset;
    Map<Integer, Object> annotsByStartNode = new HashMap<>();
    annotsByStartNode.put(startNode.getId(), annotation);
    annotationSet.annotsByStartNode = annotsByStartNode;
    annotationSet.removeFromOffsetIndex(annotation);
    assertFalse(annotationSet.nodesByOffset.containsKey(100L));
    assertFalse(annotationSet.nodesByOffset.containsKey(200L));
    assertFalse(annotationSet.annotsByStartNode.containsKey(startNode.getId()));
}

@Test
public void test3()
{
    Document document = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(document);
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("category", "person");
    annotationSet.add(0, 4, "Token", features);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("category", "person");
    AnnotationSet result = annotationSet.get("Token", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    Annotation annotation = result.iterator().next();
    assertEquals("Token", annotation.getType());
    assertEquals("person", annotation.getFeatures().get("category"));
}

@Test
public void test4()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc);
    FeatureMap features1 = Factory.newFeatureMap();
    features1.put("category", "person");
    features1.put("gender", "male");
    annotationSet.add(0L, 4L, "Entity", features1);
    FeatureMap features2 = Factory.newFeatureMap();
    features2.put("category", "organization");
    annotationSet.add(5L, 10L, "Entity", features2);
    FeatureMap features3 = Factory.newFeatureMap();
    features3.put("category", "person");
    annotationSet.add(11L, 15L, "OtherType", features3);
    FeatureMap constraints = Factory.newFeatureMap();
    constraints.put("category", "person");
    constraints.put("gender", "male");
    AnnotationSet resultSet = annotationSet.get("Entity", constraints);
    assertNotNull(resultSet);
    assertEquals(1, resultSet.size());
    Annotation resultAnnotation = resultSet.iterator().next();
    assertEquals("Entity", resultAnnotation.getType());
    assertEquals("person", resultAnnotation.getFeatures().get("category"));
    assertEquals("male", resultAnnotation.getFeatures().get("gender"));
    Factory.deleteResource(doc);
}

@Test
public void test5()
{
    Document mockDoc = null;
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc);
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("key1", "value1");
    features1.put("key2", "value2");
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("key1", "value1");
    annotationSet.add(0L, 5L, "Token", features1);
    annotationSet.add(6L, 10L, "Token", features2);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("key2", "value2");
    AnnotationSet result = annotationSet.get("Token", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    Annotation annotation = result.iterator().next();
    assertEquals("Token", annotation.getType());
    assertTrue(annotation.getFeatures().get("key2").equals("value2"));
}

@Test
public void test6()
{
    Document doc = Factory.newDocument("Sample text");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc);
    FeatureMap features = Factory.newFeatureMap();
    features.put("category", "person");
    features.put("length", 6);
    annotationSet.add(0L, 6L, "Token", features);
    FeatureMap constraints = Factory.newFeatureMap();
    constraints.put("category", "person");
    constraints.put("length", 6);
    AnnotationSet result = annotationSet.get("Token", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    Annotation annotation = result.iterator().next();
    assertEquals("Token", annotation.getType());
    assertEquals("person", annotation.getFeatures().get("category"));
    assertEquals(6, annotation.getFeatures().get("length"));
}

@Test
public void test7()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc, new HashSet<Annotation>());
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("category", "Person");
    features1.put("confidence", 0.9);
    Annotation ann1 = Factory.newAnnotation(doc, 0, 4, "Entity", features1);
    annotationSet.add(ann1);
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("category", "Location");
    Annotation ann2 = Factory.newAnnotation(doc, 5, 10, "Entity", features2);
    annotationSet.add(ann2);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("category", "Person");
    AnnotationSet result = annotationSet.get("Entity", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(ann1));
    assertFalse(result.contains(ann2));
}

@Test
public void test8()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc, "Default");
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("category", "Person");
    Annotation annotation1 = annotationSet.add(0L, 4L, "Entity", features1);
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("category", "Location");
    Annotation annotation2 = annotationSet.add(5L, 10L, "Entity", features2);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("category", "Person");
    AnnotationSet result = annotationSet.get("Entity", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(annotation1));
    assertFalse(result.contains(annotation2));
}

@Test
public void test9()
{
    Document document = Factory.newDocument("Sample text.");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(document, "TestAS");
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("category", "name");
    features1.put("length", 4);
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("category", "location");
    features2.put("length", 6);
    annotationSet.add(0L, 4L, "MyType", features1);
    annotationSet.add(5L, 11L, "MyType", features2);
    annotationSet.add(12L, 20L, "OtherType", features1);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("category", "name");
    AnnotationSet result = annotationSet.get("MyType", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    Annotation annotation = result.iterator().next();
    assertEquals("MyType", annotation.getType());
    assertEquals("name", annotation.getFeatures().get("category"));
}

@Test
public void test10()
{
    Document doc = Factory.newDocument("");
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("category", "Person");
    Annotation annotation = new AnnotationImpl(0L, 0, 4, "Token", features);
    HashSet<Annotation> annotationSet = new HashSet<Annotation>();
    annotationSet.add(annotation);
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(doc);
    annotationSetImpl.add(annotation);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("category", "Person");
    AnnotationSet result = annotationSetImpl.get("Token", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getFeatures().get("category").equals("Person"));
}

@Test
public void test11()
{
    Document document = Factory.newDocument("");
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(document);
    FeatureMap annotationFeatures = new SimpleFeatureMapImpl();
    annotationFeatures.put("category", "person");
    annotationFeatures.put("confidence", 0.95);
    Annotation annotation = Factory.createAnnotation(0L, 5L, "Entity", annotationFeatures);
    annotationSetImpl.add(annotation);
    FeatureMap constraints = new SimpleFeatureMapImpl();
    constraints.put("category", "person");
    AnnotationSet resultSet = annotationSetImpl.get("Entity", constraints);
    assertNotNull(resultSet);
    assertEquals(1, resultSet.size());
    Annotation resultAnnotation = resultSet.iterator().next();
    assertEquals("Entity", resultAnnotation.getType());
    assertTrue(resultAnnotation.getFeatures().subsumes(constraints));
}

@Test
public void test12()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(doc);
    FeatureMap features1 = Factory.newFeatureMap();
    features1.put("type", "Person");
    features1.put("gender", "male");
    annotationSetImpl.add(0L, 4L, "person", features1);
    FeatureMap features2 = Factory.newFeatureMap();
    features2.put("type", "Person");
    features2.put("gender", "female");
    annotationSetImpl.add(5L, 10L, "person", features2);
    FeatureMap constraints = Factory.newFeatureMap();
    constraints.put("gender", "female");
    AnnotationSet result = annotationSetImpl.get("person", constraints);
    assertNotNull(result);
    assertEquals(1, result.size());
    Annotation annotation = result.iterator().next();
    assertEquals("female", annotation.getFeatures().get("gender"));
}

@Test
public void test13()
{
    DocumentImpl dummyDoc = new DocumentImpl();
    dummyDoc.setName("DummyDoc");
    Node startNode1 = new LongNode(5L);
    Node endNode1 = new LongNode(10L);
    Node startNode2 = new LongNode(12L);
    Node endNode2 = new LongNode(18L);
    Node startNode3 = new LongNode(20L);
    Node endNode3 = new LongNode(25L);
    AnnotationImpl annot1 = new AnnotationImpl(1, startNode1, endNode1, "Token", Factory.newFeatureMap());
    AnnotationImpl annot2 = new AnnotationImpl(2, startNode2, endNode2, "Entity", Factory.newFeatureMap());
    AnnotationImpl annot3 = new AnnotationImpl(3, startNode3, endNode3, "Entity", Factory.newFeatureMap());
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(dummyDoc);
    annotationSet.add(annot1);
    annotationSet.add(annot2);
    annotationSet.add(annot3);
    annotationSet.indexByStartOffset();
    AnnotationSet contained = annotationSet.getContained(5L, 20L);
    assertEquals(2, contained.size());
    assertTrue(contained.contains(annot1));
    assertTrue(contained.contains(annot2));
    assertFalse(contained.contains(annot3));
}

@Test
public void test14()
{
    Document mockDoc = Mockito.mock(Document.class);
    Node startNode = Mockito.mock(Node.class);
    Mockito.when(startNode.getOffset()).thenReturn(5L);
    Mockito.when(startNode.getId()).thenReturn(1);
    Node endNode = Mockito.mock(Node.class);
    Mockito.when(endNode.getOffset()).thenReturn(15L);
    Annotation annotation = Mockito.mock(Annotation.class);
    Mockito.when(annotation.getStartNode()).thenReturn(startNode);
    Mockito.when(annotation.getEndNode()).thenReturn(endNode);
    Mockito.when(annotation.getType()).thenReturn("Token");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc);
    annotationSet.longestAnnot = 20L;
    Map<Integer, Collection<Annotation>> startNodeMap = new HashMap<>();
    startNodeMap.put(1, Collections.singletonList(annotation));
    annotationSet.annotsByStartNode = startNodeMap;
    TreeMap<Long, Node> nodesByOffset = new TreeMap<>();
    nodesByOffset.put(5L, startNode);
    annotationSet.nodesByOffset = nodesByOffset;
    AnnotationSet result = annotationSet.getCovering("Token", 6L, 10L);
    assertEquals(1, result.size());
    assertTrue(result.contains(annotation));
}

@Test
public void test15()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Document mockDoc = Factory.newDocument("");
    annotationSet.setDocument(mockDoc);
    Node nodeAtOffset = Factory.newNode(5L);
    Map<Long, Node> nodesByOffset = new HashMap<>();
    nodesByOffset.put(5L, nodeAtOffset);
    annotationSet.nodesByOffset = nodesByOffset;
    Annotation annot = Factory.newAnnotation(1L, nodeAtOffset, Factory.newNode(10L), "Token", Factory.newFeatureMap());
    Map<Integer, Set<Annotation>> annotsByStartNode = new HashMap<>();
    Set<Annotation> annotsAtNode = new HashSet<>();
    annotsAtNode.add(annot);
    annotsByStartNode.put(nodeAtOffset.getId(), annotsAtNode);
    annotationSet.annotsByStartNode = annotsByStartNode;
    ImmutableAnnotationSetImpl result = ((ImmutableAnnotationSetImpl) (annotationSet.getStartingAt(5L)));
    assertEquals(1, result.size());
    assertTrue(result.contains(annot));
}

@Test
public void test16()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc, "TestSet");
    Long startOffset = 100L;
    Long endOffset = 200L;
    Node mockStartNode = mock(Node.class);
    when(mockStartNode.getOffset()).thenReturn(startOffset);
    when(mockStartNode.getId()).thenReturn(1);
    Node mockEndNode = mock(Node.class);
    when(mockEndNode.getOffset()).thenReturn(endOffset);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getEndNode()).thenReturn(mockEndNode);
    Map<Long, Node> nodesByOffset = new HashMap<>();
    nodesByOffset.put(startOffset, mockStartNode);
    Field nodesByOffsetField = AnnotationSetImpl.class.getDeclaredField("nodesByOffset");
    nodesByOffsetField.setAccessible(true);
    nodesByOffsetField.set(annotationSet, nodesByOffset);
    Map<Integer, Collection<Annotation>> annotsByStartNode = new HashMap<>();
    Collection<Annotation> annotations = new ArrayList<>();
    annotations.add(mockAnnotation);
    annotsByStartNode.put(1, annotations);
    Field annotsByStartNodeField = AnnotationSetImpl.class.getDeclaredField("annotsByStartNode");
    annotsByStartNodeField.setAccessible(true);
    annotsByStartNodeField.set(annotationSet, annotsByStartNode);
    Field docField = AnnotationSetImpl.class.getDeclaredField("doc");
    docField.setAccessible(true);
    docField.set(annotationSet, mockDoc);
    AnnotationSet result = annotationSet.getStrict(startOffset, endOffset);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test17()
{
    Document mockDocument = new SimpleDocument();
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDocument, "TestSet");
    Document returnedDocument = annotationSet.getDocument();
    assertSame("The getDocument() method should return the document passed in the constructor.", mockDocument, returnedDocument);
}

@Test
public void test18()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, null, null);
    RelationSet firstCall = annotationSet.getRelations();
    assertNotNull("First call to getRelations() should not return null", firstCall);
    RelationSet secondCall = annotationSet.getRelations();
    assertSame("Second call to getRelations() should return the same instance", firstCall, secondCall);
}

@Test
public void test19()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, "TestSet", null);
    AnnotationImpl annotation1 = new AnnotationImpl(1L, "Person", 0L, 4L, new HashMap<String, Object>(), annotationSet);
    AnnotationImpl annotation2 = new AnnotationImpl(2L, "Location", 5L, 10L, new HashMap<String, Object>(), annotationSet);
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    int expectedSize = 2;
    int actualSize = annotationSet.size();
    assertEquals(expectedSize, actualSize);
}

@Test
public void test20()
{
    Document mockDocument = mock(Document.class);
    when(mockDocument.getNextAnnotationId()).thenReturn(42);
    Node mockStartNode = mock(Node.class);
    Node mockEndNode = mock(Node.class);
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("key", "value");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDocument, null, "testSet");
    AnnotationSetImpl.annFactory = mock(AnnotationFactory.class);
    doAnswer(( invocation) -> {
        Object[] args = invocation.getArguments();
        AnnotationSetImpl set = ((AnnotationSetImpl) (args[0]));
        Integer id = ((Integer) (args[1]));
        Node start = ((Node) (args[2]));
        Node end = ((Node) (args[3]));
        String type = ((String) (args[4]));
        FeatureMap fm = ((FeatureMap) (args[5]));
        Annotation annotation = mock(.class);
        when(annotation.getId()).thenReturn(id);
        set.put(id, annotation);
        return null;
    }).when(annFactory).createAnnotationInSet(any(), any(), any(), any(), any(), any());
    Integer returnedId = annotationSet.add(mockStartNode, mockEndNode, "Person", features);
    assertEquals(Integer.valueOf(42), returnedId);
    assertTrue(annotationSet.containsKey(42));
    assertEquals(42, annotationSet.get(42).getId().intValue());
}

@Test
public void test21()
{
    AnnotationSetImpl original = new AnnotationSetImpl(null, null);
    AnnotationSetImpl cloned = ((AnnotationSetImpl) (original.clone()));
    assertNotSame("Cloned object should not be the same reference as original", original, cloned);
    assertEquals("Cloned object should be equal to original", original, cloned);
}

@Test
public void test22()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl("TestDocument");
    FeatureMap features1 = Factory.newFeatureMap();
    features1.put("type", "Person");
    Annotation annotation1 = annotationSet.add(0L, 5L, "Person", features1);
    FeatureMap features2 = Factory.newFeatureMap();
    features2.put("type", "Location");
    Annotation annotation2 = annotationSet.add(6L, 10L, "Location", features2);
    Iterator<Annotation> iterator = annotationSet.iterator();
    assertNotNull("Iterator should not be null", iterator);
    assertTrue("Iterator should have first annotation", iterator.hasNext());
    Annotation result1 = iterator.next();
    assertTrue("First annotation should be one of the added annotations", result1.equals(annotation1) || result1.equals(annotation2));
    assertTrue("Iterator should have second annotation", iterator.hasNext());
    Annotation result2 = iterator.next();
    assertTrue("Second annotation should be one of the added annotations", (result2.equals(annotation1) || result2.equals(annotation2)) && (!result2.equals(result1)));
    assertFalse("Iterator should have no more annotations", iterator.hasNext());
}

@Test
public void test23()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Node node1 = new Node(5L) {
        @Override
        public Long getId() {
            return 5L;
        }
    };
    Node node2 = new Node(10L) {
        @Override
        public Long getId() {
            return 10L;
        }
    };
    Node node3 = new Node(15L) {
        @Override
        public Long getId() {
            return 15L;
        }
    };
    Annotation ann1 = new AnnotationImpl(1, 5L, 8L, "Token", null);
    Annotation ann2 = new AnnotationImpl(2, 10L, 12L, "Token", null);
    Annotation ann3 = new AnnotationImpl(3, 15L, 18L, "Token", null);
    Map<Long, Collection<Annotation>> annotsByStartNode = new HashMap<>();
    annotsByStartNode.put(5L, Collections.singleton(ann1));
    annotsByStartNode.put(10L, Collections.singleton(ann2));
    annotsByStartNode.put(15L, Collections.singleton(ann3));
    Map<Long, Node> nodesByOffset = new TreeMap<>();
    nodesByOffset.put(5L, node1);
    nodesByOffset.put(10L, node2);
    nodesByOffset.put(15L, node3);
    try {
        Field field1 = AnnotationSetImpl.class.getDeclaredField("annotsByStartNode");
        field1.setAccessible(true);
        field1.set(annotationSet, annotsByStartNode);
        Field field2 = AnnotationSetImpl.class.getDeclaredField("nodesByOffset");
        field2.setAccessible(true);
        field2.set(annotationSet, nodesByOffset);
    } catch (Exception e) {
        fail("Reflection failed to set internal state: " + e.getMessage());
    }
    List<Annotation> result = annotationSet.inDocumentOrder();
    assertEquals(3, result.size());
    assertEquals(Long.valueOf(5L), result.get(0).getStartNode().getOffset());
    assertEquals(Long.valueOf(10L), result.get(1).getStartNode().getOffset());
    assertEquals(Long.valueOf(15L), result.get(2).getStartNode().getOffset());
}

@Test
public void test24()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, "TestSet");
    AnnotationImpl annotation1 = new AnnotationImpl(0, 5, "Person", new HashMap<>());
    AnnotationImpl annotation2 = new AnnotationImpl(6, 10, "Location", new HashMap<>());
    AnnotationImpl annotation3 = new AnnotationImpl(11, 15, "Person", new HashMap<>());
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    annotationSet.add(annotation3);
    Set<String> types = annotationSet.getAllTypes();
    Set<String> expectedTypes = new HashSet<>();
    expectedTypes.add("Person");
    expectedTypes.add("Location");
    assertEquals(expectedTypes, types);
}

@Test
public void test25()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    NodeImpl node1 = new NodeImpl(1L, 5L);
    NodeImpl node2 = new NodeImpl(2L, 10L);
    AnnotationImpl annotation = new AnnotationImpl(100L, "test", node1, node2);
    annotationSet.getNodesByOffset().put(5L, node1);
    annotationSet.getNodesByOffset().put(10L, node2);
    annotationSet.getAnnotsByStartNode().put(1L, new ArrayList<Annotation>(Arrays.asList(annotation)));
    annotationSet.getAnnotsByEndNode().put(2L, new ArrayList<Annotation>(Arrays.asList(annotation)));
    DocumentContent replacement = new DocumentContent() {
        @Override
        public char charAt(int index) {
            return 'x';
        }

        @Override
        public String getContent(int start, int end) {
            return "x";
        }

        @Override
        public String toString() {
            return "x";
        }

        @Override
        public Long size() {
            return 1L;
        }
    };
    Gate.setGateHome(new File("."));
    Gate.init();
    Gate.getUserConfig().put(DOCEDIT_INSERT_PREPEND, Boolean.TRUE);
    annotationSet.edit(5L, 10L, replacement);
    assertFalse(annotationSet.getAnnotsByStartNode().containsKey(1L));
    assertFalse(annotationSet.getAnnotsByEndNode().containsKey(2L));
    assertFalse(annotationSet.getNodesByOffset().containsKey(10L));
    assertEquals(Long.valueOf(5L), node1.getOffset());
}

