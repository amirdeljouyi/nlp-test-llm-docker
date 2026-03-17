import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Document mockDocument = mock(Document.class);
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(mockDocument, null);
    AnnotationSet emptySet = annotationSetImpl.emptyAS();
    assertNotNull("Resulting AnnotationSet should not be null", emptySet);
    assertTrue("Resulting AnnotationSet should be empty", emptySet.isEmpty());
    assertTrue("Resulting AnnotationSet should be an instance of ImmutableAnnotationSetImpl", emptySet instanceof ImmutableAnnotationSetImpl);
    Document resultDoc = emptySet.getDocument();
    assertSame("Documents should be the same", mockDocument, resultDoc);
}

@Test
public void test2()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Node startNode = mock(Node.class);
    Node endNode = mock(Node.class);
    when(startNode.getOffset()).thenReturn(10L);
    when(endNode.getOffset()).thenReturn(20L);
    when(startNode.getId()).thenReturn(1);
    Annotation annotation = mock(Annotation.class);
    when(annotation.getStartNode()).thenReturn(startNode);
    when(annotation.getEndNode()).thenReturn(endNode);
    annotationSet.nodesByOffset = new HashMap<>();
    annotationSet.nodesByOffset.put(10L, "startMarker");
    annotationSet.nodesByOffset.put(20L, "endMarker");
    Set<Annotation> emptySet = new HashSet<>();
    mockStatic(Utils.class);
    Mockito.when(Utils.getAnnotationsAtOffset(annotationSet, 10L)).thenReturn(emptySet);
    Mockito.when(Utils.getAnnotationsEndingAtOffset(annotationSet, 10L)).thenReturn(emptySet);
    Mockito.when(Utils.getAnnotationsAtOffset(annotationSet, 20L)).thenReturn(emptySet);
    Mockito.when(Utils.getAnnotationsEndingAtOffset(annotationSet, 20L)).thenReturn(emptySet);
    Annotation otherAnnotation = mock(Annotation.class);
    Collection<Annotation> startAnnotations = new ArrayList<>();
    startAnnotations.add(annotation);
    startAnnotations.add(otherAnnotation);
    annotationSet.annotsByStartNode = new HashMap<>();
    annotationSet.annotsByStartNode.put(1, startAnnotations);
    annotationSet.removeFromOffsetIndex(annotation);
    assertFalse(annotationSet.nodesByOffset.containsKey(10L));
    assertFalse(annotationSet.nodesByOffset.containsKey(20L));
    Object remaining = annotationSet.annotsByStartNode.get(1);
    assertTrue(remaining instanceof Annotation);
    assertSame(otherAnnotation, remaining);
}

@Test
public void test3()
{
    Document document = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(document);
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("gender", "male");
    features1.put("age", "30");
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("gender", "female");
    Annotation annotation1 = Factory.newAnnotation(1L, "Person", 0, 5, features1);
    Annotation annotation2 = Factory.newAnnotation(2L, "Person", 6, 10, features2);
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    Set<Object> featureNames = new HashSet<Object>();
    featureNames.add("gender");
    AnnotationSet result = annotationSet.get("Person", featureNames);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(annotation1));
    assertTrue(result.contains(annotation2));
}

@Test
public void test4()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc);
    FeatureMap features = Factory.newFeatureMap();
    features.put("firstName", "John");
    features.put("lastName", "Doe");
    long startOffset = 0L;
    long endOffset = 4L;
    annotationSet.add(startOffset, endOffset, "Person", features);
    Set<Object> requiredFeatureNames = new HashSet<>();
    requiredFeatureNames.add("firstName");
    requiredFeatureNames.add("lastName");
    AnnotationSet result = annotationSet.get("Person", requiredFeatureNames);
    assertNotNull(result);
    assertEquals(1, result.size());
    Annotation returnedAnnotation = result.iterator().next();
    assertEquals("Person", returnedAnnotation.getType());
    assertTrue(returnedAnnotation.getFeatures().keySet().containsAll(requiredFeatureNames));
}

@Test
public void test5()
{
    FeatureMap featureMap1 = new SimpleFeatureMapImpl();
    featureMap1.put("gender", "male");
    featureMap1.put("age", "30");
    FeatureMap featureMap2 = new SimpleFeatureMapImpl();
    featureMap2.put("gender", "female");
    Annotation annotation1 = new MockAnnotation(1L, "Person", 0, 5, featureMap1);
    Annotation annotation2 = new MockAnnotation(2L, "Person", 6, 10, featureMap2);
    Annotation annotation3 = new MockAnnotation(3L, "Location", 11, 15, new SimpleFeatureMapImpl());
    Document mockDoc = new MockDocument();
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc);
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    annotationSet.add(annotation3);
    Set<Object> requestedFeatures = new HashSet<Object>();
    requestedFeatures.add("gender");
    AnnotationSet result = annotationSet.get("Person", requestedFeatures);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(annotation1));
    assertTrue(result.contains(annotation2));
}

@Test
public void test6()
{
    Document mockDoc = mock(Document.class);
    Set<Object> featureNames = new HashSet<>();
    featureNames.add("key1");
    featureNames.add("key2");
    FeatureMap featureMap = mock(FeatureMap.class);
    Set<Object> featureKeys = new HashSet<>();
    featureKeys.add("key1");
    featureKeys.add("key2");
    featureKeys.add("extraKey");
    when(featureMap.keySet()).thenReturn(featureKeys);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getFeatures()).thenReturn(featureMap);
    String type = "Person";
    AnnotationSetImpl baseSet = new AnnotationSetImpl(mockDoc);
    baseSet.annotsByType = new HashMap<>();
    Set<Integer> annotsByIdKey = new HashSet<>();
    annotsByIdKey.add(1);
    baseSet.annotsById = new LinkedHashMap<>();
    baseSet.annotsById.put(1, mockAnnotation);
    AnnotationSetImpl typeSet = new AnnotationSetImpl(mockDoc);
    typeSet.annotsById = new LinkedHashMap<>();
    typeSet.annotsById.put(1, mockAnnotation);
    when(typeSet.iterator()).thenReturn(Arrays.asList(mockAnnotation).iterator());
    baseSet.annotsByType.put(type, typeSet);
    when(baseSet.get(type)).thenReturn(typeSet);
    AnnotationSet result = baseSet.get(type, featureNames);
    assertNotNull(result);
    assertTrue(result instanceof ImmutableAnnotationSetImpl);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test7()
{
    Document doc = Factory.newDocument("Sample text");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc);
    FeatureMap features = Factory.newFeatureMap();
    features.put("key1", "value1");
    Annotation annotation = annotationSet.add(0L, 6L, "Person", features);
    HashSet<Object> featureNames = new HashSet<Object>();
    featureNames.add("key1");
    AnnotationSet result = annotationSet.get("Person", featureNames);
    assertNotNull("Returned AnnotationSet should not be null", result);
    assertEquals("Returned AnnotationSet should contain one annotation", 1, result.size());
    assertTrue("Returned AnnotationSet should contain the original annotation", result.contains(annotation));
}

@Test
public void test8()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc);
    FeatureMap features1 = Factory.newFeatureMap();
    features1.put("category", "Person");
    Annotation annotation1 = annotationSet.add(0L, 4L, "NamedEntity", features1);
    FeatureMap features2 = Factory.newFeatureMap();
    features2.put("category", "Location");
    Annotation annotation2 = annotationSet.add(5L, 10L, "NamedEntity", features2);
    FeatureMap unrelatedFeatures = Factory.newFeatureMap();
    unrelatedFeatures.put("type", "Other");
    Annotation annotation3 = annotationSet.add(11L, 15L, "OtherEntity", unrelatedFeatures);
    HashSet<Object> featureNames = new HashSet<>();
    featureNames.add("category");
    AnnotationSet result = annotationSet.get("NamedEntity", featureNames);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(annotation1));
    assertTrue(result.contains(annotation2));
}

@Test
public void test9()
{
    Document doc = Factory.newDocument("");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc);
    Map<String, Object> features1 = new HashMap<>();
    features1.put("gender", "male");
    Annotation ann1 = Factory.newAnnotation(0L, 0L, "Person", features1);
    Map<String, Object> features2 = new HashMap<>();
    features2.put("gender", "female");
    features2.put("age", "30");
    Annotation ann2 = Factory.newAnnotation(1L, 1L, "Person", features2);
    Map<String, Object> features3 = new HashMap<>();
    features3.put("occupation", "engineer");
    Annotation ann3 = Factory.newAnnotation(2L, 2L, "Organization", features3);
    annotationSet.add(ann1);
    annotationSet.add(ann2);
    annotationSet.add(ann3);
    Set<String> featureNames = new HashSet<>();
    featureNames.add("gender");
    AnnotationSet result = annotationSet.get("Person", featureNames);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(ann1));
    assertTrue(result.contains(ann2));
    assertFalse(result.contains(ann3));
}

@Test
public void test10()
{
    Document doc = Factory.newDocument("Sample text");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc, "MyAS");
    FeatureMap features = Factory.newFeatureMap();
    features.put("category", "test");
    Annotation a1 = annotationSet.add(0L, 6L, "Token", features);
    Annotation a2 = annotationSet.add(7L, 11L, "Token", Factory.newFeatureMap());
    HashSet<Object> featureNames = new HashSet<Object>();
    featureNames.add("category");
    AnnotationSet result = annotationSet.get("Token", featureNames);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(a1));
    assertFalse(result.contains(a2));
}

@Test
public void test11()
{
    Document mockDocument = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDocument);
    Annotation mockAnnotation = mock(Annotation.class);
    FeatureMap featureMap = new SimpleFeatureMapImpl();
    featureMap.put("category", "noun");
    featureMap.put("confidence", "0.95");
    when(mockAnnotation.getFeatures()).thenReturn(featureMap);
    String type = "Token";
    Integer annotationId = 1;
    Map<Integer, Annotation> annotsById = new HashMap<>();
    annotsById.put(annotationId, mockAnnotation);
    Set<Annotation> tokenAnnotations = new HashSet<>();
    tokenAnnotations.add(mockAnnotation);
    Map<String, AnnotationSet> annotsByType = new HashMap<>();
    annotsByType.put(type, new DefaultAnnotationSetImpl(mockDocument, tokenAnnotations));
    try {
        Field idField = AnnotationSetImpl.class.getDeclaredField("annotsById");
        idField.setAccessible(true);
        idField.set(annotationSet, annotsById);
        Field typeField = AnnotationSetImpl.class.getDeclaredField("annotsByType");
        typeField.setAccessible(true);
        typeField.set(annotationSet, annotsByType);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    Set<Object> featureNames = new HashSet<>();
    featureNames.add("category");
    AnnotationSet result = annotationSet.get(type, featureNames);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getFeatures().keySet().containsAll(featureNames));
}

@Test
public void test12()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc);
    Annotation annotation1 = mock(Annotation.class);
    when(annotation1.getType()).thenReturn("Person");
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("gender", "male");
    features1.put("age", "30");
    when(annotation1.getFeatures()).thenReturn(features1);
    when(annotation1.getId()).thenReturn(1);
    Annotation annotation2 = mock(Annotation.class);
    when(annotation2.getType()).thenReturn("Person");
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("gender", "female");
    when(annotation2.getFeatures()).thenReturn(features2);
    when(annotation2.getId()).thenReturn(2);
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    Set<Object> featureNames = new HashSet<>();
    featureNames.add("gender");
    AnnotationSet result = annotationSet.get("Person", featureNames);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(annotation1));
    assertTrue(result.contains(annotation2));
}

@Test
public void test13()
{
    Document mockDoc = mock(Document.class);
    Node startNode1 = mock(Node.class);
    when(startNode1.getOffset()).thenReturn(10L);
    Node endNode1 = mock(Node.class);
    when(endNode1.getOffset()).thenReturn(20L);
    Node startNode2 = mock(Node.class);
    when(startNode2.getOffset()).thenReturn(15L);
    Node endNode2 = mock(Node.class);
    when(endNode2.getOffset()).thenReturn(25L);
    Annotation annot1 = mock(Annotation.class);
    when(annot1.getStartNode()).thenReturn(startNode1);
    when(annot1.getEndNode()).thenReturn(endNode1);
    Annotation annot2 = mock(Annotation.class);
    when(annot2.getStartNode()).thenReturn(startNode2);
    when(annot2.getEndNode()).thenReturn(endNode2);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc, null);
    Map<Long, Node> nodesByOffset = new TreeMap<>();
    nodesByOffset.put(10L, startNode1);
    nodesByOffset.put(15L, startNode2);
    annotationSet.nodesByOffset = nodesByOffset;
    Map<Integer, Collection<Annotation>> annotsByStartNode = new HashMap<>();
    annotsByStartNode.put(startNode1.getId(), Arrays.asList(annot1));
    annotsByStartNode.put(startNode2.getId(), Arrays.asList(annot2));
    annotationSet.annotsByStartNode = annotsByStartNode;
    when(startNode1.getId()).thenReturn(1);
    when(startNode2.getId()).thenReturn(2);
    Long startOffset = 10L;
    Long endOffset = 22L;
    ImmutableAnnotationSetImpl result = ((ImmutableAnnotationSetImpl) (annotationSet.getContained(startOffset, endOffset)));
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(annot1));
}

@Test
public void test14()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc, new HashSet<Annotation>());
    Node mockStartNode = mock(Node.class);
    Node mockEndNode = mock(Node.class);
    when(mockStartNode.getOffset()).thenReturn(5L);
    when(mockEndNode.getOffset()).thenReturn(15L);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(mockStartNode);
    when(mockAnnotation.getEndNode()).thenReturn(mockEndNode);
    when(mockAnnotation.getType()).thenReturn("Person");
    Set<Annotation> annotationSetContent = new HashSet<Annotation>();
    annotationSetContent.add(mockAnnotation);
    AnnotationSetImpl spySet = spy(new AnnotationSetImpl(mockDoc, annotationSetContent));
    doReturn(10L).when(spySet).getLongestAnnotationLength();
    doReturn(null).when(spySet).getAnnotsByStartNode(anyLong());
    Map<Long, Node> mockNodesByOffset = new TreeMap<Long, Node>();
    mockNodesByOffset.put(5L, mockStartNode);
    spySet.nodesByOffset = mockNodesByOffset;
    spySet.longestAnnot = 10L;
    spySet.annotsByStartNode = new HashMap<Long, Collection<Annotation>>();
    spySet.annotsByStartNode.put(mockStartNode.getId(), Collections.singletonList(mockAnnotation));
    when(mockStartNode.getId()).thenReturn(1L);
    when(mockEndNode.getOffset()).thenReturn(15L);
    doReturn(Collections.singletonList(mockAnnotation)).when(spySet).getAnnotsByStartNode(1L);
    AnnotationSet result = spySet.getCovering("Person", 5L, 10L);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test15()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(mockDoc);
    long testOffset = 100L;
    Node mockNode = mock(Node.class);
    when(mockNode.getId()).thenReturn(1L);
    HashMap<Long, Node> nodesByOffset = new HashMap<>();
    nodesByOffset.put(testOffset, mockNode);
    Field nodesByOffsetField = AnnotationSetImpl.class.getDeclaredField("nodesByOffset");
    nodesByOffsetField.setAccessible(true);
    nodesByOffsetField.set(annotationSetImpl, nodesByOffset);
    HashMap<Long, Set<Annotation>> annotsByStartNode = new HashMap<>();
    Annotation mockAnnotation = mock(Annotation.class);
    Set<Annotation> annotations = new HashSet<>();
    annotations.add(mockAnnotation);
    annotsByStartNode.put(1L, annotations);
    Field annotsByStartNodeField = AnnotationSetImpl.class.getDeclaredField("annotsByStartNode");
    annotsByStartNodeField.setAccessible(true);
    annotsByStartNodeField.set(annotationSetImpl, annotsByStartNode);
    AnnotationSet result = annotationSetImpl.getStartingAt(testOffset);
    assertNotNull(result);
    assertTrue(result instanceof ImmutableAnnotationSetImpl);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test16()
{
    Document mockDoc = mock(Document.class);
    Annotation mockAnnotation = mock(Annotation.class);
    Node mockStartNode = mock(Node.class);
    Node mockEndNode = mock(Node.class);
    Long startOffset = 100L;
    Long endOffset = 200L;
    when(mockAnnotation.getEndNode()).thenReturn(mockEndNode);
    when(mockEndNode.getOffset()).thenReturn(endOffset);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc);
    annotationSet.nodesByOffset = new HashMap<>();
    annotationSet.nodesByOffset.put(startOffset, mockStartNode);
    Map<Integer, Collection<Annotation>> mockAnnotsByStartNode = new HashMap<>();
    mockAnnotsByStartNode.put(mockStartNode.getId(), Arrays.asList(mockAnnotation));
    annotationSet.annotsByStartNode = mockAnnotsByStartNode;
    AnnotationSetImpl spySet = spy(annotationSet);
    doReturn(Arrays.asList(mockAnnotation)).when(spySet).getAnnotsByStartNode(mockStartNode.getId());
    ImmutableAnnotationSetImpl result = ((ImmutableAnnotationSetImpl) (spySet.getStrict(startOffset, endOffset)));
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test17()
{
    Document mockDocument = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, mockDocument, null, null);
    assertSame("getDocument should return the assigned Document", mockDocument, annotationSet.getDocument());
}

@Test
public void test18()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, null, null);
    long offset1 = 100L;
    long offset2 = 200L;
    Node node1 = new NodeImpl(null, offset1);
    Node node2 = new NodeImpl(null, offset2);
    try {
        Field nodesField = AnnotationSetImpl.class.getDeclaredField("nodesByOffset");
        nodesField.setAccessible(true);
        @SuppressWarnings("unchecked")
        OffsetOrderedList<Node> nodeList = new OffsetOrderedList();
        nodeList.add(node1);
        nodeList.add(node2);
        nodesField.set(annotationSet, nodeList);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Node result = annotationSet.nextNode(node1);
    assertEquals("The next node should have offset 200", offset2, result.getOffset().longValue());
}

@Test
public void test19()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, null, null);
    RelationSet firstCall = annotationSet.getRelations();
    assertNotNull("First call to getRelations should not return null", firstCall);
    RelationSet secondCall = annotationSet.getRelations();
    assertSame("getRelations should return the same instance on subsequent calls", firstCall, secondCall);
}

@Test
public void test20()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl("Default");
    Annotation annotation1 = new Annotation(1L, 0L, 5L, "Token", new SimpleFeatureMapImpl());
    Annotation annotation2 = new Annotation(2L, 6L, 10L, "Token", new SimpleFeatureMapImpl());
    HashMap<Integer, Annotation> annotsById = new HashMap<>();
    annotsById.put(annotation1.getId().intValue(), annotation1);
    annotsById.put(annotation2.getId().intValue(), annotation2);
    try {
        Field field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        field.set(annotationSet, annotsById);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    assertEquals(2, annotationSet.size());
}

@Test
public void test21()
{
    Document mockDoc = mock(Document.class);
    AnnotationFactory mockFactory = mock(AnnotationFactory.class);
    Integer expectedId = 42;
    when(mockDoc.getNextAnnotationId()).thenReturn(expectedId);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl("SetName", mockDoc);
    AnnotationSetImpl.annFactory = mockFactory;
    Node startNode = mock(Node.class);
    Node endNode = mock(Node.class);
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("key", "value");
    Integer actualId = annotationSet.add(startNode, endNode, "Token", features);
    verify(mockFactory).createAnnotationInSet(annotationSet, expectedId, startNode, endNode, "Token", features);
    assertEquals(expectedId, actualId);
}

@Test
public void test22()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl.AnnotationFactory mockFactory = mock(AnnotationFactory.class);
    when(mockDoc.getNextAnnotationId()).thenReturn(1001);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc, null);
    AnnotationSetImpl.annFactory = mockFactory;
    Node mockStart = mock(Node.class);
    Node mockEnd = mock(Node.class);
    FeatureMap features = Factory.newFeatureMap();
    String type = "Person";
    Integer returnedId = annotationSet.add(mockStart, mockEnd, type, features);
    assertEquals(Integer.valueOf(1001), returnedId);
    verify(mockFactory).createAnnotationInSet(annotationSet, 1001, mockStart, mockEnd, type, features);
}

@Test
public void test23()
{
    AnnotationSetImpl original = new AnnotationSetImpl(null, "testSet");
    AnnotationSetImpl cloned = ((AnnotationSetImpl) (original.clone()));
    assertNotSame("Cloned object should be a different instance", original, cloned);
    assertEquals("Cloned object should be equal in content", original.getName(), cloned.getName());
}

@Test
public void test24()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null);
    SimpleFeatureMapImpl features1 = new SimpleFeatureMapImpl();
    features1.put("type", "token");
    Annotation annotation1 = annotationSet.add(0L, 5L, "Token", features1);
    SimpleFeatureMapImpl features2 = new SimpleFeatureMapImpl();
    features2.put("type", "token");
    Annotation annotation2 = annotationSet.add(6L, 10L, "Token", features2);
    Iterator<Annotation> iterator = annotationSet.iterator();
    assertTrue(iterator.hasNext());
    Annotation first = iterator.next();
    assertNotNull(first);
    assertTrue(first.equals(annotation1) || first.equals(annotation2));
    assertTrue(iterator.hasNext());
    Annotation second = iterator.next();
    assertNotNull(second);
    assertTrue((second.equals(annotation1) || second.equals(annotation2)) && (!second.equals(first)));
    assertFalse(iterator.hasNext());
}

@Test
public void test25()
{
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl();
    Node node1 = mock(Node.class);
    Node node2 = mock(Node.class);
    when(node1.getId()).thenReturn(1L);
    when(node2.getId()).thenReturn(2L);
    Annotation annotationA = mock(Annotation.class);
    Annotation annotationB = mock(Annotation.class);
    Map<Long, Collection<Annotation>> mockAnnotsByStartNode = new HashMap<>();
    mockAnnotsByStartNode.put(1L, Collections.singletonList(annotationA));
    mockAnnotsByStartNode.put(2L, Collections.singletonList(annotationB));
    LinkedHashMap<Integer, Node> mockNodesByOffset = new LinkedHashMap<>();
    mockNodesByOffset.put(5, node1);
    mockNodesByOffset.put(10, node2);
    annotationSetImpl.nodesByOffset = mockNodesByOffset;
    annotationSetImpl.annotsByStartNode = mockAnnotsByStartNode;
    List<Annotation> orderedAnnotations = annotationSetImpl.inDocumentOrder();
    assertEquals(2, orderedAnnotations.size());
    assertSame(annotationA, orderedAnnotations.get(0));
    assertSame(annotationB, orderedAnnotations.get(1));
}

@Test
public void test26()
{
    Document dummyDoc = new DocumentImpl();
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(dummyDoc);
    AnnotationImpl annotation1 = new AnnotationImpl(0, 5, "Person", Factory.newFeatureMap());
    AnnotationImpl annotation2 = new AnnotationImpl(6, 10, "Location", Factory.newFeatureMap());
    AnnotationImpl annotation3 = new AnnotationImpl(11, 15, "Person", Factory.newFeatureMap());
    annotationSet.add(annotation1.getId(), annotation1.getStartNode(), annotation1.getEndNode(), annotation1.getType(), annotation1.getFeatures());
    annotationSet.add(annotation2.getId(), annotation2.getStartNode(), annotation2.getEndNode(), annotation2.getType(), annotation2.getFeatures());
    annotationSet.add(annotation3.getId(), annotation3.getStartNode(), annotation3.getEndNode(), annotation3.getType(), annotation3.getFeatures());
    Set<String> types = annotationSet.getAllTypes();
    assertEquals(2, types.size());
    assertTrue(types.contains("Person"));
    assertTrue(types.contains("Location"));
}

@Test
public void test27()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, "default");
    NodeImpl node1 = new NodeImpl(1L, 0L);
    NodeImpl node2 = new NodeImpl(2L, 5L);
    NodeImpl node3 = new NodeImpl(3L, 10L);
    annotationSet.getNodesByOffset().put(node1.getOffset(), node1);
    annotationSet.getNodesByOffset().put(node2.getOffset(), node2);
    annotationSet.getNodesByOffset().put(node3.getOffset(), node3);
    AnnotationImpl annotation1 = new AnnotationImpl(1, "Token", node1, node2, null);
    AnnotationImpl annotation2 = new AnnotationImpl(2, "Token", node2, node3, null);
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    System.setProperty(DOCEDIT_INSERT_PREPEND, "true");
    DocumentContent replacement = new SimpleDocumentContent("XYZ");
    annotationSet.edit(5L, 10L, replacement);
    assertEquals(Long.valueOf(0L), ((NodeImpl) (annotation1.getStartNode())).getOffset());
    assertEquals("After edit, annotation2's start should be firstNode", annotation1.getStartNode(), annotation2.getStartNode());
    assertEquals("After edit, annotation2's end should be firstNode", annotation2.getStartNode(), annotation2.getEndNode());
    assertFalse("Annotation2 should be removed due to zero-length", annotationSet.contains(annotation2));
}


