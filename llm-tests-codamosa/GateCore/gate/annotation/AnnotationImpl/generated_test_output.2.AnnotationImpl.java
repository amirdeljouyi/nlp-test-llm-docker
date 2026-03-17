import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    FeatureMap otherFeatures = new SimpleFeatureMapImpl();
    otherFeatures.put("key1", "value1");
    otherFeatures.put("key2", "value2");
    AnnotationImpl thisAnnot = new AnnotationImpl();
    thisAnnot.setFeatures(thisFeatures);
    Annotation otherAnnot = mock(Annotation.class);
    when(otherAnnot.getFeatures()).thenReturn(otherFeatures);
    when(otherAnnot.getStartNode()).thenReturn(null);
    when(otherAnnot.getEndNode()).thenReturn(null);
    AnnotationImpl spyAnnot = spy(thisAnnot);
    doReturn(true).when(spyAnnot).coextensive(otherAnnot);
    assertTrue(spyAnnot.isCompatible(otherAnnot));
}

@Test
public void test2()
{
    AnnotationImpl annotation1 = new AnnotationImpl(1L, 0L, 5L, "Person", new SimpleFeatureMapImpl());
    FeatureMap features2 = new SimpleFeatureMapImpl();
    features2.put("gender", "male");
    features2.put("name", "John");
    FeatureMap features1 = new SimpleFeatureMapImpl();
    features1.put("name", "John");
    AnnotationImpl annotation2 = new AnnotationImpl(2L, 3L, 10L, "Person", features2);
    annotation1.setFeatures(features1);
    Annotation annotationSpy = new AnnotationImpl(annotation2.getId(), annotation2.getStartNode().getOffset(), annotation2.getEndNode().getOffset(), annotation2.getType(), annotation2.getFeatures()) {
        @Override
        public boolean overlaps(Annotation other) {
            return true;
        }

        @Override
        public FeatureMap getFeatures() {
            return features2;
        }
    };
    AnnotationImpl annotationUnderTest = new AnnotationImpl(annotation1.getId(), annotation1.getStartNode().getOffset(), annotation1.getEndNode().getOffset(), annotation1.getType(), annotation1.getFeatures()) {
        @Override
        public boolean overlaps(Annotation other) {
            return true;
        }

        @Override
        public FeatureMap getFeatures() {
            return features1;
        }
    };
    boolean result = annotationUnderTest.isPartiallyCompatible(annotationSpy);
    assertTrue(result);
}

@Test
public void test3()
{
    AnnotationImpl annotation = new AnnotationImpl();
    annotation.setId(42L);
    annotation.setType("Person");
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("gender", "female");
    features.put("age", 30);
    annotation.setFeatures(features);
    annotation.setStartNode(new NodeImpl(5L));
    annotation.setEndNode(new NodeImpl(10L));
    String expected = (("AnnotationImpl: id=42; type=Person; features=" + features.toString()) + "; start=5; end=10") + System.getProperty("line.separator");
    String actual = annotation.toString();
    assertEquals(expected, actual);
}

