import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    AnnotationImpl thisAnnot = new AnnotationImpl();
    FeatureMap thisFeatures = mock(FeatureMap.class);
    thisAnnot.setFeatures(thisFeatures);
    Annotation otherAnnot = mock(Annotation.class);
    FeatureMap otherFeatures = mock(FeatureMap.class);
    AnnotationImpl spyThisAnnot = spy(thisAnnot);
    doReturn(true).when(spyThisAnnot).coextensive(otherAnnot);
    when(otherAnnot.getFeatures()).thenReturn(otherFeatures);
    when(otherFeatures.subsumes(thisFeatures)).thenReturn(true);
    boolean result = spyThisAnnot.isCompatible(otherAnnot);
    assertTrue(result);
}

@Test
public void test2()
{
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    AnnotationImpl thisAnnot = new AnnotationImpl();
    thisAnnot.setFeatures(thisFeatures);
    Annotation otherAnnot = mock(Annotation.class);
    FeatureMap otherFeatures = new SimpleFeatureMapImpl();
    otherFeatures.put("key1", "value1");
    otherFeatures.put("key2", "value2");
    when(otherAnnot.getFeatures()).thenReturn(otherFeatures);
    when(otherAnnot.getStartNode()).thenReturn(() -> 0L);
    when(otherAnnot.getEndNode()).thenReturn(() -> 10L);
    thisAnnot.setStartNode(() -> 5L);
    thisAnnot.setEndNode(() -> 15L);
    boolean result = thisAnnot.isPartiallyCompatible(otherAnnot);
    assertTrue(result);
}

@Test
public void test3()
{
    AnnotationImpl annotation = new AnnotationImpl();
    annotation.setId(123);
    annotation.setType("Person");
    FeatureMap features = new SimpleFeatureMapImpl();
    features.put("gender", "male");
    features.put("age", 30);
    annotation.setFeatures(features);
    annotation.setStartNode(new NodeImpl(0L));
    annotation.setEndNode(new NodeImpl(10L));
    String expected = "AnnotationImpl: id=123; type=Person; features={gender=male, age=30}; start=0; end=10" + System.getProperty("line.separator");
    assertEquals(expected, annotation.toString());
}

