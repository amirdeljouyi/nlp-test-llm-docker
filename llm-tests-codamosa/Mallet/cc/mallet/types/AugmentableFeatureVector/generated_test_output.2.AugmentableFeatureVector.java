import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    AugmentableFeatureVector afv = new AugmentableFeatureVector(null);
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
    indicesField.setAccessible(true);
    valuesField.setAccessible(true);
    sizeField.setAccessible(true);
    maxSortedIndexField.setAccessible(true);
    int[] indices = new int[]{ 1, 1, 2 };
    double[] values = new double[]{ 0.5, 1.5, 2.0 };
    indicesField.set(afv, indices);
    valuesField.set(afv, values);
    sizeField.set(afv, 3);
    afv.removeDuplicates(0);
    int[] expectedIndices = new int[]{ 1, 2 };
    double[] expectedValues = new double[]{ 2.0, 2.0 };
    assertArrayEquals(expectedIndices, ((int[]) (indicesField.get(afv))));
    assertArrayEquals(expectedValues, ((double[]) (valuesField.get(afv))), 1.0E-4);
    assertEquals(2, sizeField.get(afv));
    assertEquals(1, maxSortedIndexField.get(afv));
}

@Test
public void test2()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA");
    int indexB = alphabet.lookupIndex("featureB");
    int[] indices = new int[]{ indexA, indexB };
    double[] values = new double[]{ 1.5, 2.5 };
    int length = indices.length;
    int size = 2;
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, indices, values, length, size, true, false, false);
    ConstantMatrix clonedMatrix = vector.cloneMatrix();
    assertNotNull(clonedMatrix);
    assertEquals(1, clonedMatrix.getNumRows());
    assertEquals(2, clonedMatrix.getNumColumns());
    assertEquals(1.5, clonedMatrix.value(0, 0), 1.0E-4);
    assertEquals(2.5, clonedMatrix.value(0, 1), 1.0E-4);
}

@Test
public void test3()
{
    Alphabet dictionary = new Alphabet();
    dictionary.lookupIndex("feature1");
    dictionary.lookupIndex("feature2");
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 2.0, 3.5 };
    int length = 2;
    AugmentableFeatureVector original = new AugmentableFeatureVector(dictionary, indices, values, length, length, false, false, false);
    ConstantMatrix cloned = original.cloneMatrixZeroed();
    assertTrue(cloned instanceof AugmentableFeatureVector);
    AugmentableFeatureVector clonedVector = ((AugmentableFeatureVector) (cloned));
    assertArrayEquals(indices, clonedVector.getIndices());
    assertEquals(2, clonedVector.getIndices().length);
    assertEquals(2, clonedVector.getValues().length);
    assertEquals(0.0, clonedVector.getValues()[0], 1.0E-4);
    assertEquals(0.0, clonedVector.getValues()[1], 1.0E-4);
}

@Test
public void test4()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("featureA", true);
    dict.lookupIndex("featureB", true);
    dict.lookupIndex("featureC", true);
    AugmentableFeatureVector afv = new AugmentableFeatureVector(dict, 3);
    afv.add("featureC", 1.0);
    afv.add("featureA", 2.0);
    afv.add("featureB", 3.0);
    FeatureVector fv = afv.toFeatureVector();
    assertNotNull(fv);
    assertEquals(3, fv.numLocations());
    assertEquals(dict.lookupIndex("featureA", false), fv.indexAtLocation(0));
    assertEquals(dict.lookupIndex("featureB", false), fv.indexAtLocation(1));
    assertEquals(dict.lookupIndex("featureC", false), fv.indexAtLocation(2));
    assertEquals(2.0, fv.valueAtLocation(0), 1.0E-5);
    assertEquals(3.0, fv.valueAtLocation(1), 1.0E-5);
    assertEquals(1.0, fv.valueAtLocation(2), 1.0E-5);
}

@Test
public void test5()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA", true);
    int indexB = alphabet.lookupIndex("featureB", true);
    AugmentableFeatureVector afv = new AugmentableFeatureVector(alphabet, false);
    afv.add(indexB, 3.0);
    afv.add(indexA, 1.5);
    SparseVector result = afv.toSparseVector();
    assertEquals(2, result.numLocations());
    assertEquals(indexA, result.indexAtLocation(0));
    assertEquals(1.5, result.valueAtLocation(0), 1.0E-4);
    assertEquals(indexB, result.indexAtLocation(1));
    assertEquals(3.0, result.valueAtLocation(1), 1.0E-4);
}

@Test
public void test6()
{
    Alphabets.Alphabet alphabet = new Alphabets.Alphabet();
    alphabet.lookupIndex("feature1");
    alphabet.lookupIndex("feature2");
    alphabet.lookupIndex("feature3");
    int[] indices = new int[]{ 0, 2 };
    double[] values = new double[]{ 1.5, -2.0 };
    boolean binary = false;
    AugmentableFeatureVector afv = new AugmentableFeatureVector();
    afv.alphabet = alphabet;
    afv.values = values;
    afv.indices = indices;
    afv.size = 2;
    afv.maxSortedIndex = 1;
    double[] denseValues = new double[]{ 2.0, 0.0, 1.0 };
    DenseVector denseVector = new DenseVector(denseValues);
    double result = afv.dotProduct(denseVector);
    assertEquals(1.0, result, 1.0E-5);
}

@Test
public void test7()
{
    double[] featureValues = new double[]{ 2.0, 3.0 };
    int[] featureIndices = new int[]{ 1, 3 };
    int vectorSize = 4;
    AugmentableFeatureVector afv = new AugmentableFeatureVector(null, featureIndices, featureValues, featureValues.length, true, vectorSize);
    double[] denseValues = new double[]{ 1.0, 2.0, 0.0, 4.0 };
    DenseVector denseVector = new DenseVector(denseValues);
    double expectedDotProduct = (2.0 * 2.0) + (3.0 * 4.0);
    double actualDotProduct = afv.dotProduct(denseVector);
    assertEquals(expectedDotProduct, actualDotProduct, 1.0E-10);
}

@Test
public void test8()
{
    DenseVector denseVector = new DenseVector(new double[]{ 1.0, 2.0, 3.0, 4.0, 5.0 });
    AugmentableFeatureVector afv = new AugmentableFeatureVector(null, null, null) {
        {
            this.size = 3;
            this.indices = new int[]{ 0, 2, 4 };
            this.values = new double[]{ 1.0, 0.5, 2.0 };
            this.maxSortedIndex = 2;
        }
    };
    double expected = 12.5;
    double result = afv.dotProduct(denseVector);
    assertEquals(expected, result, 1.0E-6);
}

@Test
public void test9()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, new int[]{ 0, 1, 2 }, new double[]{ 3.0, 4.0, 0.0 });
    double result = vector.twoNorm();
    assertEquals(5.0, result, 1.0E-5);
}

@Test
public void test10()
{
    Alphabet alphabet = new Alphabet();
    String feature = "feature1";
    int featureIndex = alphabet.lookupIndex(feature, true);
    double value = 1.0;
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, new String[]{ feature }, new double[]{ value });
    vector.indices = null;
    int result = vector.indexAtLocation(0);
    assertEquals(0, result);
}

@Test
public void test11()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, null, new double[]{ 1.0, 2.0 });
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, null);
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(vector, 2);
    int result = vector.numLocations();
    assertEquals(2, result);
}

@Test
public void test12()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    int[] indices1 = new int[]{ dict.lookupIndex("feature1") };
    double[] values1 = new double[]{ 2.0 };
    FeatureVector sourceFV = new FeatureVector(dict, indices1, values1);
    AugmentableFeatureVector targetAFV = new AugmentableFeatureVector(dict);
    assertEquals(-1, targetAFV.location(indices1[0]));
    targetAFV.add(sourceFV);
    int addedIndex = indices1[0];
    int loc = targetAFV.location(addedIndex);
    assertTrue(loc >= 0);
    assertEquals(2.0, targetAFV.valueAtLocation(loc), 1.0E-5);
}

@Test
public void test13()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA");
    int indexB = alphabet.lookupIndex("featureB");
    int[] sourceIndices = new int[]{ indexB };
    double[] sourceValues = new double[]{ 1.0 };
    FeatureVector sourceFV = new FeatureVector(alphabet, sourceIndices, sourceValues);
    AugmentableFeatureVector targetFV = new AugmentableFeatureVector(alphabet, new String[]{ "featureA" });
    targetFV.add(sourceFV);
    int[] indices = targetFV.getIndices();
    double[] values = targetFV.getValues();
    assertEquals(2, indices.length);
    boolean hasA = ((indices[0] == indexA) && (values[0] == 1.0)) || ((indices[1] == indexA) && (values[1] == 1.0));
    boolean hasB = ((indices[0] == indexB) && (values[0] == 1.0)) || ((indices[1] == indexB) && (values[1] == 1.0));
    assertTrue(hasA);
    assertTrue(hasB);
}

@Test
public void test14()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    dict.lookupIndex("feature3");
    AugmentableFeatureVector afv = new AugmentableFeatureVector(dict, new String[]{ "feature1" }, new double[]{ 1.0 });
    FeatureVector fv = new FeatureVector(dict, new String[]{ "feature2", "feature3" }, new double[]{ 2.0, 3.0 });
    afv.add(fv);
    int index1 = dict.lookupIndex("feature1");
    int index2 = dict.lookupIndex("feature2");
    int index3 = dict.lookupIndex("feature3");
    assertEquals(1.0, afv.value(index1), 1.0E-4);
    assertEquals(2.0, afv.value(index2), 1.0E-4);
    assertEquals(3.0, afv.value(index3), 1.0E-4);
}

@Test
public void test15()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    AugmentableFeatureVector afv = new AugmentableFeatureVector(dict, new String[]{ "feature1" });
    assertEquals(1, afv.numLocations());
    assertEquals("feature1", dict.lookupObject(afv.indexAtLocation(0)));
    FeatureVector fv2 = new FeatureVector(dict, new String[]{ "feature2" }, new double[]{ 1.0 });
    afv.add(fv2);
    assertEquals(2, afv.numLocations());
    boolean hasFeature1 = false;
    boolean hasFeature2 = false;
    int index1 = dict.lookupIndex("feature1");
    int index2 = dict.lookupIndex("feature2");
    hasFeature1 = afv.location(index1) != (-1);
    hasFeature2 = afv.location(index2) != (-1);
    assertTrue(hasFeature1);
    assertTrue(hasFeature2);
}

@Test
public void test16()
{
    Alphabet alphabet = new Alphabet();
    String feature1 = "feature_one";
    String feature2 = "feature_two";
    int index1 = alphabet.lookupIndex(feature1);
    int index2 = alphabet.lookupIndex(feature2);
    AugmentableFeatureVector targetVector = new AugmentableFeatureVector(alphabet);
    targetVector.add(index1, 1.0);
    int[] sourceIndices = new int[]{ index1, index2 };
    double[] sourceValues = new double[]{ 1.0, 2.0 };
    FeatureVector sourceVector = new FeatureVector(alphabet, sourceIndices, sourceValues);
    targetVector.add(sourceVector);
    assertEquals(1.0, targetVector.value(index1), 1.0E-4);
    assertEquals(2.0, targetVector.value(index2), 1.0E-4);
}

@Test
public void test17()
{
    AugmentableFeatureVector v1 = new AugmentableFeatureVector(null, new double[]{ 1.0, 2.0, 3.0 });
    AugmentableFeatureVector v2 = new AugmentableFeatureVector(null, new double[]{ 0.5, 1.0, 1.5 });
    v1.size = 3;
    v1.maxSortedIndex = 2;
    v2.size = 3;
    v2.maxSortedIndex = 2;
    v1.plusEquals(v2, 1.0);
    assertEquals(1.5, v1.values[0], 1.0E-5);
    assertEquals(3.0, v1.values[1], 1.0E-5);
    assertEquals(4.5, v1.values[2], 1.0E-5);
}

@Test
public void test18()
{
    AugmentableFeatureVector v1 = new AugmentableFeatureVector();
    v1.values = new double[]{ 1.0, 2.0, 3.0 };
    v1.indices = null;
    v1.size = 3;
    v1.maxSortedIndex = 2;
    AugmentableFeatureVector v2 = new AugmentableFeatureVector();
    v2.values = new double[]{ 0.5, 0.5, 0.5 };
    v2.indices = null;
    v2.size = 3;
    v2.maxSortedIndex = 2;
    v1.plusEquals(v2, 1.0);
    assertArrayEquals(new double[]{ 1.5, 2.5, 3.5 }, v1.values, 1.0E-5);
    assertEquals(3, v1.size);
}

@Test
public void test19()
{
    AugmentableFeatureVector featureVector = new AugmentableFeatureVector(null, new String[]{ "feature1", "feature2", "feature3" });
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    double[] initialValues = new double[]{ 1.0, 2.0, 3.0 };
    valuesField.set(featureVector, initialValues);
    featureVector.setAll(5.5);
    double[] expectedValues = new double[]{ 5.5, 5.5, 5.5 };
    double[] actualValues = ((double[]) (valuesField.get(featureVector)));
    assertArrayEquals(expectedValues, actualValues, 1.0E-5);
}

@Test
public void test20()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector();
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(vector, 3);
    Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
    maxSortedIndexField.setAccessible(true);
    maxSortedIndexField.setInt(vector, 2);
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, new double[]{ 0.1, 0.2, 0.3 });
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, null);
    vector.setValue(1, 9.99);
    double[] updatedValues = ((double[]) (valuesField.get(vector)));
    assertEquals(9.99, updatedValues[1], 1.0E-6);
    assertEquals(0.1, updatedValues[0], 1.0E-6);
    assertEquals(0.3, updatedValues[2], 1.0E-6);
}

@Test
public void test21()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1");
    alphabet.lookupIndex("feature2");
    alphabet.lookupIndex("feature3");
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, new String[]{ "feature1", "feature2", "feature3" }, new double[]{ 1.0, 2.0, 3.0 });
    vector.setValueAtLocation(1, 5.5);
    double[] valuesField = vector.getValues();
    assertEquals(5.5, valuesField[1], 1.0E-4);
    assertEquals(1.0, valuesField[0], 1.0E-4);
    assertEquals(3.0, valuesField[2], 1.0E-4);
}

