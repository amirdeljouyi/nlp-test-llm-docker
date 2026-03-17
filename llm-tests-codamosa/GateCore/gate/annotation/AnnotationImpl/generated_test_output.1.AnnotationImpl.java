import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    AnnotationImpl annotation = new AnnotationImpl();
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    thisFeatures.put("key2", "value2");
    annotation.setFeatures(thisFeatures);
    Annotation mockAnnotation = mock(Annotation.class);
    when(mockAnnotation.getFeatures()).thenReturn(new SimpleFeatureMapImpl() {
        {
            put("key1", "value1");
            put("key2", "value2");
            put("key3", "value3");
        }
    });
    when(mockAnnotation.getStartNode()).thenReturn(null);
    when(mockAnnotation.getEndNode()).thenReturn(null);
    when(mockAnnotation.getType()).thenReturn(annotation.getType());
    AnnotationImpl spy = spy(annotation);
    doReturn(true).when(spy).coextensive(mockAnnotation);
    boolean result = spy.isCompatible(mockAnnotation);
    assertTrue(result);
}

@Test
public void test2()
{
    AnnotationImpl annotation = new AnnotationImpl();
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    annotation.setFeatures(thisFeatures);
    Annotation otherAnnotation = mock(Annotation.class);
    when(otherAnnotation.getFeatures()).thenReturn(new SimpleFeatureMapImpl());
    otherAnnotation.getFeatures().put("key1", "value1");
    otherAnnotation.getFeatures().put("key2", "value2");
    AnnotationImpl spyAnnotation = spy(annotation);
    doReturn(true).when(spyAnnotation).coextensive(otherAnnotation);
    assertTrue(spyAnnotation.isCompatible(otherAnnotation));
}

@Test
public void test3()
{
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    FeatureMap otherFeatures = new SimpleFeatureMapImpl();
    otherFeatures.put("key1", "value1");
    otherFeatures.put("key2", "value2");
    Annotation otherAnnot = mock(Annotation.class);
    when(otherAnnot.getFeatures()).thenReturn(otherFeatures);
    AnnotationImpl thisAnnot = new AnnotationImpl();
    thisAnnot.setFeatures(thisFeatures);
    AnnotationImpl spyAnnot = spy(thisAnnot);
    doReturn(true).when(spyAnnot).overlaps(otherAnnot);
    boolean result = spyAnnot.isPartiallyCompatible(otherAnnot);
    assertTrue(result);
}

@Test
public void test4()
{
    AnnotationImpl annotation = new AnnotationImpl();
    try {
        Field idField = AnnotationImpl.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(annotation, Integer.valueOf(101));
        Field typeField = AnnotationImpl.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(annotation, "Token");
        FeatureMap features = new SimpleFeatureMapImpl();
        features.put("pos", "NN");
        features.put("lemma", "run");
        Field featuresField = AnnotationImpl.class.getDeclaredField("features");
        featuresField.setAccessible(true);
        featuresField.set(annotation, features);
        Field startField = AnnotationImpl.class.getDeclaredField("start");
        startField.setAccessible(true);
        startField.set(annotation, Long.valueOf(5L));
        Field endField = AnnotationImpl.class.getDeclaredField("end");
        endField.setAccessible(true);
        endField.set(annotation, Long.valueOf(8L));
    } catch (Exception e) {
        fail("Reflection error: " + e.getMessage());
    }
    String expected = "AnnotationImpl: id=101; type=Token; features={pos=NN, lemma=run}; start=5; end=8" + System.getProperty("line.separator");
    assertEquals(expected, annotation.toString());
}


