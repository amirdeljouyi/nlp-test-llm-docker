import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    AnnotationImpl thisAnnot = new AnnotationImpl();
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    thisAnnot.setFeatures(thisFeatures);
    Annotation otherAnnot = mock(Annotation.class);
    FeatureMap otherFeatures = new SimpleFeatureMapImpl();
    otherFeatures.put("key1", "value1");
    otherFeatures.put("key2", "value2");
    when(otherAnnot.getFeatures()).thenReturn(otherFeatures);
    AnnotationImpl spyThisAnnot = spy(thisAnnot);
    doReturn(true).when(spyThisAnnot).coextensive(otherAnnot);
    boolean result = spyThisAnnot.isCompatible(otherAnnot);
    assertTrue(result);
}

@Test
public void test2()
{
    AnnotationImpl annotationUnderTest = new AnnotationImpl();
    FeatureMap thisFeatureMap = new SimpleFeatureMapImpl();
    thisFeatureMap.put("key1", "value1");
    annotationUnderTest.setFeatures(thisFeatureMap);
    Annotation mockAnnotation = mock(Annotation.class);
    FeatureMap mockFeatureMap = mock(FeatureMap.class);
    when(mockAnnotation.getFeatures()).thenReturn(mockFeatureMap);
    when(mockFeatureMap.subsumes(thisFeatureMap)).thenReturn(true);
    when(mockAnnotation.getStartNode()).thenReturn(() -> 0L);
    when(mockAnnotation.getEndNode()).thenReturn(() -> 10L);
    annotationUnderTest.setStartNode(() -> 5L);
    annotationUnderTest.setEndNode(() -> 15L);
    assertTrue(annotationUnderTest.isPartiallyCompatible(mockAnnotation));
}

@Test
public void test3()
{
    Long start = 0L;
    Long end = 10L;
    FeatureMap featuresThis = Factory.newFeatureMap();
    featuresThis.put("key1", "value1");
    FeatureMap featuresOther = Factory.newFeatureMap();
    featuresOther.put("key1", "value1");
    featuresOther.put("key2", "value2");
    AnnotationImpl thisAnnot = new AnnotationImpl(1L, start, end, "Person", featuresThis);
    AnnotationImpl otherAnnot = new AnnotationImpl(2L, 5L, 15L, "Person", featuresOther);
    assertTrue(thisAnnot.isPartiallyCompatible(otherAnnot));
}

@Test
public void test4()
{
    AnnotationImpl annotation = new AnnotationImpl();
    try {
        Field idField = AnnotationImpl.class.getDeclaredField("id");
        Field typeField = AnnotationImpl.class.getDeclaredField("type");
        Field featuresField = AnnotationImpl.class.getDeclaredField("features");
        Field startField = AnnotationImpl.class.getDeclaredField("start");
        Field endField = AnnotationImpl.class.getDeclaredField("end");
        idField.setAccessible(true);
        typeField.setAccessible(true);
        featuresField.setAccessible(true);
        startField.setAccessible(true);
        endField.setAccessible(true);
        idField.set(annotation, Long.valueOf(123));
        typeField.set(annotation, "Person");
        FeatureMap featureMap = new SimpleFeatureMapImpl();
        featureMap.put("gender", "female");
        featuresField.set(annotation, featureMap);
        startField.set(annotation, 10L);
        endField.set(annotation, 25L);
    } catch (Exception e) {
        throw new RuntimeException("Failed to set fields via reflection", e);
    }
    String expected = "AnnotationImpl: id=123; type=Person; features={gender=female}; start=10; end=25" + System.getProperty("line.separator");
    assertEquals(expected, annotation.toString());
}

