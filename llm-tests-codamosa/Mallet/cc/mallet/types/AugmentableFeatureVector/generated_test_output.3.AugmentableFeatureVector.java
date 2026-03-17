import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    AugmentableFeatureVector afv = new AugmentableFeatureVector();
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(afv, new int[]{ 1, 2, 2, 3, 4, 4 });
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(afv, new double[]{ 1.0, 2.0, 2.5, 3.0, 4.0, 1.0 });
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(afv, 6);
    afv.removeDuplicates(0);
    int[] expectedIndices = new int[]{ 1, 2, 3, 4 };
    double[] expectedValues = new double[]{ 1.0, 4.5, 3.0, 5.0 };
    int[] actualIndices = ((int[]) (indicesField.get(afv)));
    double[] actualValues = ((double[]) (valuesField.get(afv)));
    int actualSize = sizeField.getInt(afv);
    assertArrayEquals(expectedIndices, actualIndices);
    assertArrayEquals(expectedValues, actualValues, 1.0E-4);
    assertEquals(4, actualSize);
}

@Test
public void test2()
{
    Alphabet alphabet = new Alphabet();
    int index0 = alphabet.lookupIndex("feature1");
    int index1 = alphabet.lookupIndex("feature2");
    int[] indices = new int[]{ index0, index1 };
    double[] values = new double[]{ 1.0, 2.0 };
    int capacity = 2;
    int size = 2;
    AugmentableFeatureVector original = new AugmentableFeatureVector(alphabet, indices, values, capacity, size, true, false, false);
    ConstantMatrix clonedMatrix = original.cloneMatrix();
    assertNotNull(clonedMatrix);
    assertTrue(clonedMatrix instanceof ConstantMatrix);
    assertEquals(original.numLocations(), clonedMatrix.numLocations());
    assertEquals(original.value(0), clonedMatrix.value(0), 1.0E-4);
    assertEquals(original.value(1), clonedMatrix.value(1), 1.0E-4);
}

@Test
public void test3()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1", true);
    dict.lookupIndex("feature2", true);
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 1.5, 2.5 };
    int used = 2;
    boolean grows = false;
    boolean binary = false;
    boolean unmodifiable = false;
    AugmentableFeatureVector original = new AugmentableFeatureVector(dict, indices, values, used, values.length, grows, binary, unmodifiable);
    ConstantMatrix cloned = original.cloneMatrixZeroed();
    assertTrue(cloned instanceof AugmentableFeatureVector);
    AugmentableFeatureVector clonedVector = ((AugmentableFeatureVector) (cloned));
    assertNotSame(original, clonedVector);
    assertEquals(original.getAlphabet(), clonedVector.getAlphabet());
    assertArrayEquals(indices, clonedVector.getIndices());
    assertArrayEquals(new double[]{ 0.0, 0.0 }, clonedVector.getValues(), 1.0E-5);
    assertEquals(used, clonedVector.numLocations());
}

@Test
public void test4()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA");
    int indexB = alphabet.lookupIndex("featureB");
    AugmentableFeatureVector augFV = new AugmentableFeatureVector(alphabet);
    augFV.add("featureB", 2.0);
    augFV.add("featureA", 1.0);
    FeatureVector fv = augFV.toFeatureVector();
    Assert.assertEquals(2, fv.numLocations());
    Assert.assertEquals("featureA", fv.getObjectAlphabet().lookupObject(fv.indexAtLocation(0)));
    Assert.assertEquals("featureB", fv.getObjectAlphabet().lookupObject(fv.indexAtLocation(1)));
    Assert.assertEquals(1.0, fv.valueAtLocation(0), 1.0E-4);
    Assert.assertEquals(2.0, fv.valueAtLocation(1), 1.0E-4);
}

@Test
public void test5()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("featureA", true);
    dict.lookupIndex("featureB", true);
    dict.lookupIndex("featureC", true);
    AugmentableFeatureVector afv = new AugmentableFeatureVector(dict, null, 3);
    afv.add("featureC", 3.0);
    afv.add("featureA", 1.0);
    afv.add("featureB", 2.0);
    SparseVector sv = afv.toSparseVector();
    assertEquals(3, sv.numLocations());
    assertEquals(dict.lookupIndex("featureA"), sv.indexAtLocation(0));
    assertEquals(dict.lookupIndex("featureB"), sv.indexAtLocation(1));
    assertEquals(dict.lookupIndex("featureC"), sv.indexAtLocation(2));
    assertEquals(1.0, sv.valueAtLocation(0), 1.0E-4);
    assertEquals(2.0, sv.valueAtLocation(1), 1.0E-4);
    assertEquals(3.0, sv.valueAtLocation(2), 1.0E-4);
}

@Test
public void test6()
{
    double[] denseValues = new double[]{ 1.0, 2.0, 3.0, 4.0 };
    DenseVector denseVector = new DenseVector(denseValues);
    AugmentableFeatureVector featureVector = new AugmentableFeatureVector(null) {
        {
            this.size = 3;
            this.indices = new int[]{ 2, 0, 3 };
            this.values = new double[]{ 1.5, 2.0, -1.0 };
            this.maxSortedIndex = 2;
        }
    };
    double result = featureVector.dotProduct(denseVector);
    assertEquals(2.5, result, 1.0E-10);
}

@Test
public void test7()
{
    double[] denseValues = new double[]{ 0.5, 1.0, -2.0, 3.0 };
    DenseVector denseVector = new DenseVector(denseValues);
    AugmentableFeatureVector featureVector = new AugmentableFeatureVector(null, null, false) {
        {
            this.size = 3;
            this.values = new double[]{ 2.0, -1.0, 4.0 };
            this.indices = new int[]{ 0, 2, 3 };
            this.maxSortedIndex = 2;
        }
    };
    double expected = 15.0;
    double actual = featureVector.dotProduct(denseVector);
    assertEquals(expected, actual, 1.0E-10);
}

@Test
public void test8()
{
    AugmentableFeatureVector afv = new AugmentableFeatureVector(null, null);
    try {
        Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
        Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
        Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
        Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
        valuesField.setAccessible(true);
        indicesField.setAccessible(true);
        sizeField.setAccessible(true);
        maxSortedIndexField.setAccessible(true);
        valuesField.set(afv, new double[]{ 1.0, 2.0, 3.0 });
        indicesField.set(afv, new int[]{ 0, 2, 4 });
        sizeField.set(afv, 3);
        maxSortedIndexField.set(afv, 2);
    } catch (Exception e) {
        fail("Failed to set private fields: " + e.getMessage());
    }
    double[] denseValues = new double[]{ 10.0, 0.0, 20.0, 0.0, 30.0 };
    DenseVector denseVector = new DenseVector(denseValues);
    double result = afv.dotProduct(denseVector);
    assertEquals(140.0, result, 1.0E-6);
}

@Test
public void test9()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, new int[]{ 0, 1, 2 }, new double[]{ 3.0, 4.0, 0.0 });
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(vector, 3);
    Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
    maxSortedIndexField.setAccessible(true);
    maxSortedIndexField.setInt(vector, 2);
    double result = vector.twoNorm();
    assertEquals(5.0, result, 1.0E-5);
}

@Test
public void test10()
{
    AugmentableFeatureVector afv = new AugmentableFeatureVector(null, null, null, null);
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(afv, 3);
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(afv, null);
    int result = afv.numLocations();
    assertEquals(3, result);
}

@Test
public void test11()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1", true);
    alphabet.lookupIndex("feature2", true);
    alphabet.lookupIndex("feature3", true);
    AugmentableFeatureVector afv = new AugmentableFeatureVector(alphabet);
    afv.add(0, 1.0);
    int[] indices = new int[]{ 1, 2 };
    double[] values = new double[]{ 2.0, 3.0 };
    FeatureVector fv = new FeatureVector(alphabet, indices, values);
    afv.add(fv);
    assertEquals(3, afv.numLocations());
    assertEquals(1.0, afv.valueAtLocation(afv.location(0)), 1.0E-4);
    assertEquals(2.0, afv.valueAtLocation(afv.location(1)), 1.0E-4);
    assertEquals(3.0, afv.valueAtLocation(afv.location(2)), 1.0E-4);
}

@Test
public void test12()
{
    Alphabet dict = new Alphabet();
    String feature1 = "wordA";
    String feature2 = "wordB";
    String feature3 = "wordC";
    AugmentableFeatureVector vector1 = new AugmentableFeatureVector(dict, new String[]{ feature1 }, new double[]{ 1.0 });
    FeatureVector vector2 = new FeatureVector(dict, new String[]{ feature2, feature3 }, new double[]{ 2.0, 3.0 });
    vector1.add(vector2);
    int index1 = dict.lookupIndex(feature1);
    int index2 = dict.lookupIndex(feature2);
    int index3 = dict.lookupIndex(feature3);
    assertEquals(3, vector1.numLocations());
    assertEquals(index1, vector1.indexAtLocation(0));
    assertEquals(1.0, vector1.valueAtLocation(0), 1.0E-4);
    assertEquals(index2, vector1.indexAtLocation(1));
    assertEquals(2.0, vector1.valueAtLocation(1), 1.0E-4);
    assertEquals(index3, vector1.indexAtLocation(2));
    assertEquals(3.0, vector1.valueAtLocation(2), 1.0E-4);
}

@Test
public void test13()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA");
    AugmentableFeatureVector baseVector = new AugmentableFeatureVector(alphabet, new int[]{ indexA }, new double[]{ 1.0 });
    int indexB = alphabet.lookupIndex("featureB");
    FeatureVector otherVector = new FeatureVector(alphabet, new int[]{ indexB }, new double[]{ 2.0 });
    baseVector.add(otherVector);
    int newNumLocations = baseVector.numLocations();
    assertEquals(2, newNumLocations);
    int idx0 = baseVector.indexAtLocation(0);
    int idx1 = baseVector.indexAtLocation(1);
    double val0 = baseVector.valueAtLocation(0);
    double val1 = baseVector.valueAtLocation(1);
    boolean hasFeatureA = ((idx0 == indexA) && (val0 == 1.0)) || ((idx1 == indexA) && (val1 == 1.0));
    boolean hasFeatureB = ((idx0 == indexB) && (val0 == 2.0)) || ((idx1 == indexB) && (val1 == 2.0));
    assertTrue(hasFeatureA);
    assertTrue(hasFeatureB);
}

@Test
public void test14()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA");
    int indexB = alphabet.lookupIndex("featureB");
    int indexC = alphabet.lookupIndex("featureC");
    int[] fvIndices = new int[]{ indexA, indexB };
    double[] fvValues = new double[]{ 1.0, 2.0 };
    FeatureVector fv = new FeatureVector(alphabet, fvIndices, fvValues);
    AugmentableFeatureVector afv = new AugmentableFeatureVector(alphabet);
    afv.add(indexA, 1.0);
    afv.add(fv);
    assertEquals(2, afv.numLocations());
    int locA = afv.location(indexA);
    int locB = afv.location(indexB);
    assertTrue(locA != (-1));
    assertTrue(locB != (-1));
    assertEquals(1.0, afv.valueAtLocation(locA), 0.001);
    assertEquals(2.0, afv.valueAtLocation(locB), 0.001);
}

@Test
public void test15()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("word1", true);
    dict.lookupIndex("word2", true);
    dict.lookupIndex("word3", true);
    String[] sourceFeatures = new String[]{ "word1", "word2" };
    double[] sourceValues = new double[]{ 1.0, 2.0 };
    FeatureVector sourceVector = new FeatureVector(dict, sourceFeatures, sourceValues);
    AugmentableFeatureVector targetVector = new AugmentableFeatureVector(dict, new String[0], new double[0]);
    targetVector.add(sourceVector);
    int index1 = dict.lookupIndex("word1");
    int index2 = dict.lookupIndex("word2");
    assertEquals(1.0, targetVector.value(index1), 1.0E-4);
    assertEquals(2.0, targetVector.value(index2), 1.0E-4);
    assertEquals(2, targetVector.numLocations());
}

@Test
public void test16()
{
    AugmentableFeatureVector afv1 = new AugmentableFeatureVector(null, 3);
    afv1.values = new double[]{ 1.0, 2.0, 3.0 };
    afv1.indices = null;
    afv1.size = 3;
    afv1.maxSortedIndex = 2;
    AugmentableFeatureVector afv2 = new AugmentableFeatureVector(null, 3);
    afv2.values = new double[]{ 0.5, 1.5, -0.5 };
    afv2.indices = null;
    afv2.size = 3;
    afv2.maxSortedIndex = 2;
    afv1.plusEquals(afv2, 1.0);
    assertEquals(1.5, afv1.values[0], 1.0E-4);
    assertEquals(3.5, afv1.values[1], 1.0E-4);
    assertEquals(2.5, afv1.values[2], 1.0E-4);
}

@Test
public void test17()
{
    AugmentableFeatureVector base = new AugmentableFeatureVector(null);
    base.indices = new int[]{ 1, 3 };
    base.values = new double[]{ 1.0, 2.0 };
    base.size = 2;
    base.maxSortedIndex = 1;
    AugmentableFeatureVector v = new AugmentableFeatureVector(null);
    v.indices = new int[]{ 1, 2, 3 };
    v.values = new double[]{ 0.5, 4.0, 1.5 };
    v.size = 3;
    v.maxSortedIndex = 2;
    base.plusEquals(v, 2.0);
    assertEquals(2.0, base.values[0], 1.0E-4);
    assertEquals(5.0, base.values[1], 1.0E-4);
}

@Test
public void test18()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, new int[]{ 0, 1, 2 }, new double[]{ 1.0, 2.0, 3.0 });
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    double[] initialValues = ((double[]) (valuesField.get(vector)));
    assertArrayEquals(new double[]{ 1.0, 2.0, 3.0 }, initialValues, 1.0E-6);
    vector.setAll(5.5);
    double[] updatedValues = ((double[]) (valuesField.get(vector)));
    assertArrayEquals(new double[]{ 5.5, 5.5, 5.5 }, updatedValues, 1.0E-6);
}

@Test
public void test19()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector();
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(vector, 3);
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    double[] values = new double[]{ 0.0, 0.0, 0.0 };
    valuesField.set(vector, values);
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, null);
    vector.setValue(1, 5.5);
    assertEquals(5.5, values[1], 1.0E-5);
}

@Test
public void test20()
{
    Alphabet alphabet = new Alphabet();
    String[] features = new String[]{ "featureA" };
    double[] initialValues = new double[]{ 1.0 };
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, features, initialValues);
    boolean assertionThrown = false;
    try {
        vector.setValueAtLocation(1, 2.5);
    } catch (AssertionError e) {
        assertionThrown = true;
    }
    assertTrue("Expected AssertionError for invalid index", assertionThrown);
}

