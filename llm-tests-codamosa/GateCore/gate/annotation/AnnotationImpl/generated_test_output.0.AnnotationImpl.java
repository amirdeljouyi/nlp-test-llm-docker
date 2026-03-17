import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    AnnotationImpl thisAnnot = new AnnotationImpl();
    thisAnnot.setStartNode(new SimpleNode(0));
    thisAnnot.setEndNode(new SimpleNode(10));
    thisAnnot.setFeatures(thisFeatures);
    Annotation otherAnnot = mock(Annotation.class);
    when(otherAnnot.getStartNode()).thenReturn(new SimpleNode(0));
    when(otherAnnot.getEndNode()).thenReturn(new SimpleNode(10));
    FeatureMap otherFeatures = new SimpleFeatureMapImpl();
    otherFeatures.put("key1", "value1");
    otherFeatures.put("key2", "value2");
    when(otherAnnot.getFeatures()).thenReturn(otherFeatures);
    AnnotationImpl spyThisAnnot = spy(thisAnnot);
    doReturn(true).when(spyThisAnnot).coextensive(otherAnnot);
    doReturn(thisFeatures).when(spyThisAnnot).getFeatures();
    boolean result = spyThisAnnot.isCompatible(otherAnnot);
    assertTrue(result);
}

@Test
public void test2()
{
    AnnotationImpl thisAnnotation = new AnnotationImpl();
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    thisAnnotation.setFeatures(thisFeatures);
    Annotation otherAnnotation = mock(Annotation.class);
    FeatureMap otherFeatures = mock(FeatureMap.class);
    when(otherAnnotation.getFeatures()).thenReturn(otherFeatures);
    when(otherFeatures.subsumes(thisFeatures)).thenReturn(true);
    when(thisAnnotation.coextensive(otherAnnotation)).thenReturn(true);
    boolean result = thisAnnotation.isCompatible(otherAnnotation);
    assertTrue(result);
}

@Test
public void test3()
{
    FeatureMap thisFeatures = new SimpleFeatureMapImpl();
    thisFeatures.put("key1", "value1");
    FeatureMap otherFeatures = new SimpleFeatureMapImpl();
    otherFeatures.put("key1", "value1");
    otherFeatures.put("key2", "value2");
    AnnotationImpl thisAnnot = new AnnotationImpl(1, 5, "Token", thisFeatures);
    AnnotationImpl otherAnnot = new AnnotationImpl(3, 7, "Token", otherFeatures);
    assertTrue(thisAnnot.isPartiallyCompatible(otherAnnot));
}

