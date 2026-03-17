import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Document mockDoc = mock(Document.class);
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(mockDoc, null);
    AnnotationSet emptySet = annotationSetImpl.emptyAS();
    assertTrue(emptySet instanceof ImmutableAnnotationSetImpl);
    assertEquals(mockDoc, emptySet.getDocument());
    assertTrue(emptySet.isEmpty());
    assertEquals(0, emptySet.size());
}

@Test
public void test2()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, "dummySetName");
    Integer annotationId = 42;
    String type = "Token";
    Long startOffset = 0L;
    Long endOffset = 5L;
    FeatureMap features = Factory.newFeatureMap();
    features.put("kind", "word");
    Annotation annotation = new AnnotationImpl(annotationId, startOffset, endOffset, type, features);
    Field field;
    try {
        field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        HashMap<Integer, Annotation> annotsById = ((HashMap<Integer, Annotation>) (field.get(annotationSet)));
        annotsById.put(annotationId, annotation);
    } catch (Exception e) {
        fail("Failed to setup internal annotation map: " + e.getMessage());
    }
    Annotation retrieved = annotationSet.get(annotationId);
    assertNotNull(retrieved);
    assertEquals(annotationId, retrieved.getId());
    assertEquals(startOffset, retrieved.getStartNode().getOffset());
    assertEquals(endOffset, retrieved.getEndNode().getOffset());
    assertEquals(type, retrieved.getType());
    assertEquals("word", retrieved.getFeatures().get("kind"));
}

@Test
public void test3()
{
    Map<Integer, Annotation> mockAnnotsById = new HashMap<>();
    Annotation expectedAnnotation = new AnnotationImpl(1L, "Token", 0L, 4L, null);
    mockAnnotsById.put(100, expectedAnnotation);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    try {
        Field field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        field.set(annotationSet, mockAnnotsById);
    } catch (Exception e) {
        fail("Failed to inject mock map into AnnotationSetImpl: " + e.getMessage());
    }
    Annotation result = annotationSet.get(100);
    assertSame("The returned annotation should match the one inserted in annotsById", expectedAnnotation, result);
}

@Test
public void test4()
{
    Annotation mockAnnotation = mock(Annotation.class);
    Integer annotationId = 100;
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null);
    annotationSet.annotsById = new HashMap<>();
    annotationSet.annotsById.put(annotationId, mockAnnotation);
    Annotation result = annotationSet.get(annotationId);
    assertSame("The returned annotation should match the expected annotation.", mockAnnotation, result);
}

@Test
public void test5()
{
    Annotation dummyAnnotation = new AnnotationImpl(1, "Token", 0L, 4L, new FeatureMapImpl());
    HashMap<Integer, Annotation> annotationMap = new HashMap<>();
    annotationMap.put(1, dummyAnnotation);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null);
    Field annotsByIdField;
    try {
        annotsByIdField = AnnotationSetImpl.class.getDeclaredField("annotsById");
        annotsByIdField.setAccessible(true);
        annotsByIdField.set(annotationSet, annotationMap);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException("Failed to set up test", e);
    }
    Annotation result = annotationSet.get(1);
    assertEquals(dummyAnnotation, result);
}

@Test
public void test6()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Annotation mockAnnotation = new Annotation() {
        @Override
        public Integer getId() {
            return 42;
        }

        @Override
        public String getType() {
            return "MockType";
        }

        @Override
        public Long getStartNode() {
            return 0L;
        }

        @Override
        public Long getEndNode() {
            return 1L;
        }

        @Override
        public Object getFeatures() {
            return null;
        }
    };
    annotationSet.annotsById = new HashMap<>();
    annotationSet.annotsById.put(42, mockAnnotation);
    Annotation result = annotationSet.get(42);
    assertNotNull(result);
    assertEquals(Integer.valueOf(42), result.getId());
}

@Test
public void test7()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Annotation mockAnnotation = mock(Annotation.class);
    Integer id = 42;
    try {
        Field field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<Integer, Annotation> map = ((Map<Integer, Annotation>) (field.get(annotationSet)));
        if (map == null) {
            map = new HashMap<>();
            field.set(annotationSet, map);
        }
        map.put(id, mockAnnotation);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Annotation result = annotationSet.get(id);
    assertSame("Expected the annotation retrieved to match the inserted one", mockAnnotation, result);
}

@Test
public void test8()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Annotation expectedAnnotation = new Annotation() {
        @Override
        public Integer getId() {
            return 42;
        }

        @Override
        public Long getStartNode() {
            return null;
        }

        @Override
        public Long getEndNode() {
            return null;
        }

        @Override
        public String getType() {
            return null;
        }

        @Override
        public Object getFeatures() {
            return null;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    };
    try {
        Field field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<Integer, Annotation> annotsById = ((Map<Integer, Annotation>) (field.get(annotationSet)));
        annotsById.put(42, expectedAnnotation);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    Annotation actualAnnotation = annotationSet.get(42);
    assertSame("The returned Annotation should match the inserted one.", expectedAnnotation, actualAnnotation);
}

@Test
public void test9()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Annotation mockAnnotation = mock(Annotation.class);
    Integer annotationId = 42;
    try {
        Field field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        HashMap<Integer, Annotation> map = ((HashMap<Integer, Annotation>) (field.get(annotationSet)));
        map.put(annotationId, mockAnnotation);
    } catch (NoSuchFieldException | IllegalAccessException e) {
        fail("Failed to inject mock annotation into AnnotationSetImpl: " + e.getMessage());
    }
    Annotation result = annotationSet.get(annotationId);
    assertSame("Returned annotation should be the one inserted", mockAnnotation, result);
}

@Test
public void test10()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Annotation mockAnnotation = mock(Annotation.class);
    Integer annotationId = 42;
    try {
        Field field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        HashMap<Integer, Annotation> map = new HashMap<>();
        map.put(annotationId, mockAnnotation);
        field.set(annotationSet, map);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Annotation result = annotationSet.get(annotationId);
    assertSame("Expected the same annotation object for the given ID", mockAnnotation, result);
}

@Test
public void test11()
{
    Annotation annotationMock = mock(Annotation.class);
    HashMap<Integer, Annotation> annotsMap = new HashMap<>();
    annotsMap.put(1, annotationMock);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Field field;
    try {
        field = AnnotationSetImpl.class.getDeclaredField("annotsById");
        field.setAccessible(true);
        field.set(annotationSet, annotsMap);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Annotation result = annotationSet.get(1);
    assertSame("Should return the annotation associated with id 1", annotationMock, result);
}

@Test
public void test12()
{
    Node startNode = mock(Node.class);
    Node endNode = mock(Node.class);
    when(endNode.getOffset()).thenReturn(15L);
    Annotation annotation = mock(Annotation.class);
    when(annotation.getStartNode()).thenReturn(startNode);
    when(annotation.getEndNode()).thenReturn(endNode);
    AnnotationSetImpl annotationSetImpl = new AnnotationSetImpl(mock(Document.class), null);
    Map<Long, Node> nodesByOffsetMap = new TreeMap<>();
    Node nodeAt10 = mock(Node.class);
    when(nodeAt10.getId()).thenReturn(10L);
    nodesByOffsetMap.put(10L, nodeAt10);
    Map<Long, Node> spyNodesByOffset = spy(new TreeMap<>(nodesByOffsetMap));
    annotationSetImpl.nodesByOffset = spyNodesByOffset;
    doReturn(Arrays.asList(annotation)).when(annotationSetImpl).getAnnotsByStartNode(10L);
    annotationSetImpl.annotsByStartNode = new HashMap<>();
    ImmutableAnnotationSetImpl resultSet = ((ImmutableAnnotationSetImpl) (annotationSetImpl.getContained(10L, 20L)));
    assertNotNull(resultSet);
    assertEquals(1, resultSet.size());
    assertTrue(resultSet.contains(annotation));
}

@Test
public void test13()
{
    Document mockDocument = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDocument, new HashSet<>());
    Node startNode = mock(Node.class);
    Node endNode = mock(Node.class);
    when(startNode.getOffset()).thenReturn(5L);
    when(endNode.getOffset()).thenReturn(20L);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(startNode);
    when(mockAnnotation.getEndNode()).thenReturn(endNode);
    when(mockAnnotation.getType()).thenReturn("Person");
    annotationSet.add(mockAnnotation);
    annotationSet.indexByStartOffset();
    annotationSet.longestAnnot = 20;
    AnnotationSet result = annotationSet.getCovering("Person", 6L, 18L);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test14()
{
    Document dummyDoc = null;
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(dummyDoc, null);
    long testOffset = 100L;
    Node nodeAtOffset = new Node(testOffset) {
        @Override
        public Long getOffset() {
            return testOffset;
        }

        @Override
        public Integer getId() {
            return 1;
        }
    };
    Map<Long, Node> nodesByOffset = new HashMap<>();
    nodesByOffset.put(testOffset, nodeAtOffset);
    annotationSet.nodesByOffset = nodesByOffset;
    Annotation annotation1 = new AnnotationImpl(1, 100L, 200L, "Token", null);
    Set<Annotation> annotsSet = new HashSet<>();
    annotsSet.add(annotation1);
    Map<Integer, Set<Annotation>> annotsByStartNode = new HashMap<>();
    annotsByStartNode.put(1, annotsSet);
    annotationSet.annotsByStartNode = annotsByStartNode;
    AnnotationSet result = annotationSet.getStartingAt(testOffset);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(annotation1));
}

@Test
public void test15()
{
    Document mockDoc = mock(Document.class);
    Long startOffset = 100L;
    Long endOffset = 200L;
    Node startNode = mock(Node.class);
    when(startNode.getOffset()).thenReturn(startOffset);
    when(startNode.getId()).thenReturn(1L);
    Node endNode = mock(Node.class);
    when(endNode.getOffset()).thenReturn(endOffset);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getStartNode()).thenReturn(startNode);
    when(mockAnnotation.getEndNode()).thenReturn(endNode);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(mockDoc, null);
    annotationSet.nodesByOffset = new HashMap<>();
    annotationSet.nodesByOffset.put(startOffset, startNode);
    Map<Long, Collection<Annotation>> annotsByStartNodeMap = new HashMap<>();
    Collection<Annotation> annots = new ArrayList<>();
    annots.add(mockAnnotation);
    annotsByStartNodeMap.put(1L, annots);
    annotationSet.annotsByStartNode = annotsByStartNodeMap;
    AnnotationSet result = annotationSet.getStrict(startOffset, endOffset);
    assertEquals(1, result.size());
    assertTrue(result.contains(mockAnnotation));
}

@Test
public void test16()
{
    Document mockDocument = mock(Document.class);
    AnnotationSetImpl annotationSet = new AnnotationSetImpl("default", mockDocument);
    Document returnedDocument = annotationSet.getDocument();
    assertSame("The returned document should be the same as the one assigned", mockDocument, returnedDocument);
}

@Test
public void test17()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, null, null, null);
    RelationSet firstCall = annotationSet.getRelations();
    assertNotNull(firstCall);
    RelationSet secondCall = annotationSet.getRelations();
    assertSame("getRelations should return the same RelationSet instance on subsequent calls", firstCall, secondCall);
}

@Test
public void test18()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null);
    AnnotationImpl annotation1 = new AnnotationImpl(0L, 10L, "Token", new HashMap<String, Object>(), 1L);
    AnnotationImpl annotation2 = new AnnotationImpl(11L, 20L, "Token", new HashMap<String, Object>(), 2L);
    AnnotationImpl annotation3 = new AnnotationImpl(21L, 30L, "Token", new HashMap<String, Object>(), 3L);
    annotationSet.add(annotation1);
    annotationSet.add(annotation2);
    annotationSet.add(annotation3);
    int size = annotationSet.size();
    assertEquals(3, size);
}

@Test
public void test19()
{
    Document mockDoc = mock(Document.class);
    when(mockDoc.getNextAnnotationId()).thenReturn(42);
    Node startNode = mock(Node.class);
    Node endNode = mock(Node.class);
    FeatureMap mockFeatures = Factory.newFeatureMap();
    mockFeatures.put("key", "value");
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, "DummySet", mockDoc);
    AnnotationFactory mockAnnFactory = mock(AnnotationFactory.class);
    AnnotationSetImpl.annFactory = mockAnnFactory;
    doNothing().when(mockAnnFactory).createAnnotationInSet(eq(annotationSet), eq(42), eq(startNode), eq(endNode), eq("Person"), eq(mockFeatures));
    Integer returnedId = annotationSet.add(startNode, endNode, "Person", mockFeatures);
    assertEquals(Integer.valueOf(42), returnedId);
    verify(mockAnnFactory).createAnnotationInSet(eq(annotationSet), eq(42), eq(startNode), eq(endNode), eq("Person"), eq(mockFeatures));
}

@Test
public void test20()
{
    Document doc = new DocumentImpl();
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(doc, "MyAnnotSet");
    Node startNode = doc.getDefaultAnnotationSet().getStartNode();
    Node endNode = doc.getDefaultAnnotationSet().getEndNode();
    FeatureMap features = Factory.newFeatureMap();
    features.put("key1", "value1");
    features.put("key2", 42);
    Integer id = annotationSet.add(startNode, endNode, "TestType", features);
    Annotation addedAnnotation = annotationSet.get(id);
    assertNotNull(addedAnnotation);
    assertEquals(startNode, addedAnnotation.getStartNode());
    assertEquals(endNode, addedAnnotation.getEndNode());
    assertEquals("TestType", addedAnnotation.getType());
    assertEquals(features, addedAnnotation.getFeatures());
}

@Test
public void test21()
{
    AnnotationSetImpl original = new AnnotationSetImpl(null, null, null);
    AnnotationSetImpl cloned = ((AnnotationSetImpl) (original.clone()));
    assertNotNull("Cloned object should not be null", cloned);
    assertNotSame("Cloned object should be a different instance", original, cloned);
    assertEquals("Cloned object should be equal to the original", original, cloned);
}

@Test
public void test22()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null, "testDocument");
    FeatureMap features1 = Factory.newFeatureMap();
    features1.put("type", "Person");
    Annotation annotation1 = annotationSet.add(((long) (0)), ((long) (5)), "Person", features1);
    FeatureMap features2 = Factory.newFeatureMap();
    features2.put("type", "Location");
    Annotation annotation2 = annotationSet.add(((long) (6)), ((long) (10)), "Location", features2);
    Iterator<Annotation> iterator = annotationSet.iterator();
    assertTrue(iterator.hasNext());
    Annotation first = iterator.next();
    assertNotNull(first);
    assertTrue(first.equals(annotation1) || first.equals(annotation2));
    assertTrue(iterator.hasNext());
    Annotation second = iterator.next();
    assertNotNull(second);
    assertTrue((!second.equals(first)) && (second.equals(annotation1) || second.equals(annotation2)));
    assertFalse(iterator.hasNext());
}

@Test
public void test23()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Node node1 = mock(Node.class);
    when(node1.getId()).thenReturn(1L);
    Node node2 = mock(Node.class);
    when(node2.getId()).thenReturn(2L);
    annotationSet.nodesByOffset = new TreeMap<>();
    annotationSet.nodesByOffset.put(5L, node1);
    annotationSet.nodesByOffset.put(10L, node2);
    Annotation annotation1 = mock(Annotation.class);
    Annotation annotation2 = mock(Annotation.class);
    Map<Long, Collection<Annotation>> annotsMap = new HashMap<>();
    annotsMap.put(1L, Arrays.asList(annotation1));
    annotsMap.put(2L, Arrays.asList(annotation2));
    annotationSet.annotsByStartNode = annotsMap;
    List<Annotation> result = annotationSet.inDocumentOrder();
    assertEquals(2, result.size());
    assertTrue(result.contains(annotation1));
    assertTrue(result.contains(annotation2));
    assertSame(annotation1, result.get(0));
    assertSame(annotation2, result.get(1));
}

@Test
public void test24()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(((Document) (null)), null);
    try {
        AnnotationImpl annotation1 = new AnnotationImpl(1L, 0L, 5L, "Token", new HashMap<>());
        AnnotationImpl annotation2 = new AnnotationImpl(2L, 5L, 10L, "Sentence", new HashMap<>());
        AnnotationImpl annotation3 = new AnnotationImpl(3L, 10L, 15L, "Token", new HashMap<>());
        Field annotsField = AnnotationSetImpl.class.getDeclaredField("annots");
        annotsField.setAccessible(true);
        Map<Long, AnnotationImpl> annots = ((Map<Long, AnnotationImpl>) (annotsField.get(annotationSet)));
        annots.put(annotation1.getId(), annotation1);
        annots.put(annotation2.getId(), annotation2);
        annots.put(annotation3.getId(), annotation3);
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    Set<String> types = annotationSet.getAllTypes();
    Set<String> expectedTypes = new HashSet<>();
    expectedTypes.add("Token");
    expectedTypes.add("Sentence");
    assertEquals(expectedTypes, types);
}

@Test
public void test25()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl(null);
    NodeImpl nodeStart = new NodeImpl(1L, 5L);
    NodeImpl nodeMiddle = new NodeImpl(2L, 7L);
    NodeImpl nodeEnd = new NodeImpl(3L, 10L);
    NodeImpl nodeAfter = new NodeImpl(4L, 12L);
    annotationSet.getNodesByOffset().put(nodeStart.getOffset(), nodeStart);
    annotationSet.getNodesByOffset().put(nodeMiddle.getOffset(), nodeMiddle);
    annotationSet.getNodesByOffset().put(nodeEnd.getOffset(), nodeEnd);
    annotationSet.getNodesByOffset().put(nodeAfter.getOffset(), nodeAfter);
    AnnotationImpl annotationToMerge = new AnnotationImpl(1, "Token", nodeMiddle, nodeEnd);
    AnnotationImpl annotationEndingInRange = new AnnotationImpl(2, "Word", nodeStart, nodeMiddle);
    AnnotationImpl annotationAfter = new AnnotationImpl(3, "After", nodeAfter, nodeAfter);
    annotationSet.add(annotationToMerge);
    annotationSet.add(annotationEndingInRange);
    annotationSet.add(annotationAfter);
    Map<Long, Set<Annotation>> annotsByStartNode = annotationSet.getAnnotsByStartNodeMap();
    annotsByStartNode.put(nodeMiddle.getId(), new HashSet<>(Collections.singletonList(annotationToMerge)));
    annotsByStartNode.put(nodeAfter.getId(), new HashSet<>(Collections.singletonList(annotationAfter)));
    annotsByStartNode.put(nodeStart.getId(), new HashSet<>(Collections.singletonList(annotationEndingInRange)));
    Gate.getUserConfig().put(DOCEDIT_INSERT_PREPEND, true);
    DocumentContent replacement = new DocumentContentImpl("abc");
    annotationSet.edit(6L, 10L, replacement);
    assertEquals(Long.valueOf(6L), nodeStart.getOffset());
    assertNull(annotationSet.get(1));
    assertNull(annotationSet.get(2));
    assertEquals(Long.valueOf(14L), nodeAfter.getOffset());
}

@Test
public void test26()
{
    AnnotationSetImpl annotationSet = new AnnotationSetImpl();
    Map<Long, Node> nodesByOffset = new HashMap<>();
    Map<Integer, Object> annotsByStartNode = new HashMap<>();
    annotationSet.nodesByOffset = nodesByOffset;
    annotationSet.annotsByStartNode = annotsByStartNode;
    annotationSet.longestAnnot = 5L;
    Node mockStartNode = new Node() {
        @Override
        public Long getOffset() {
            return 10L;
        }

        @Override
        public Integer getId() {
            return 1;
        }
    };
    Node mockEndNode = new Node() {
        @Override
        public Long getOffset() {
            return 20L;
        }

        @Override
        public Integer getId() {
            return 2;
        }
    };
    Annotation mockAnnotation = new Annotation() {
        @Override
        public Node getStartNode() {
            return mockStartNode;
        }

        @Override
        public Node getEndNode() {
            return mockEndNode;
        }
    };
    annotationSet.addToStartOffsetIndex(mockAnnotation);
    assertEquals(mockStartNode, annotationSet.nodesByOffset.get(10L));
    assertEquals(mockEndNode, annotationSet.nodesByOffset.get(20L));
    assertEquals(Long.valueOf(10L), Long.valueOf(annotationSet.longestAnnot));
    assertTrue(annotationSet.annotsByStartNode.containsKey(1));
    assertEquals(mockAnnotation, annotationSet.annotsByStartNode.get(1));
}

