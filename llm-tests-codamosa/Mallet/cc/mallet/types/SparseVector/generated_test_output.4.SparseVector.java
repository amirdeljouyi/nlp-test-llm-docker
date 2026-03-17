import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    int[] unsortedIndices = new int[]{ 5, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector vector = new SparseVector();
    Field indicesField = SparseVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, unsortedIndices);
    Field valuesField = SparseVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, values);
    Method sortIndicesMethod = SparseVector.class.getDeclaredMethod("sortIndices");
    sortIndicesMethod.setAccessible(true);
    sortIndicesMethod.invoke(vector);
    int[] sortedIndices = ((int[]) (indicesField.get(vector)));
    double[] sortedValues = ((double[]) (valuesField.get(vector)));
    assertArrayEquals(new int[]{ 2, 4, 5 }, sortedIndices);
    assertArrayEquals(new double[]{ 2.0, 3.0, 1.0 }, sortedValues, 1.0E-4);
}

@Test
public void test2()
{
    int[] indices = new int[]{ 0 };
    double[] values = new double[]{ Double.NaN };
    boolean lengthFixed = false;
    boolean sorted = true;
    SparseVector vector = new SparseVector(indices, values, values.length, lengthFixed, sorted);
    assertTrue(vector.isNaN());
}

@Test
public void test3()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector original = new SparseVector(indices, values, true, false, false);
    ConstantMatrix cloned = original.cloneMatrix();
    assertTrue(cloned instanceof SparseVector);
    SparseVector clonedVector = ((SparseVector) (cloned));
    assertArrayEquals(indices, clonedVector.getIndices());
    assertArrayEquals(values, clonedVector.getValues(), 1.0E-6);
    assertFalse(clonedVector.isLengthImplicit());
    assertFalse(clonedVector.isGrowable());
    assertTrue(clonedVector.isDirty());
}

@Test
public void test4()
{
    int[] indices = new int[]{ 1, 3, 5 };
    double[] values = new double[]{ 2.0, 4.0, 6.0 };
    boolean sort = true;
    boolean binary = false;
    boolean immutable = false;
    SparseVector original = new SparseVector(indices, values, sort, binary, immutable);
    SparseVector cloned = ((SparseVector) (original.cloneMatrixZeroed()));
    assertNotNull(cloned);
    assertEquals(indices.length, cloned.numLocations());
    for (int i = 0; i < indices.length; i++) {
        assertEquals(0.0, cloned.valueAtLocation(i), 1.0E-5);
        assertEquals(indices[i], cloned.indexAtLocation(i));
    }
    assertFalse(cloned.isImmutable());
    assertFalse(cloned.isBinary());
    assertTrue(cloned.isSorted());
}

@Test
public void test5()
{
    int[] indices1 = new int[]{ 0, 2 };
    double[] values1 = new double[]{ 1.0, 3.0 };
    SparseVector sv1 = new SparseVector(indices1, values1, true, true, false);
    int[] indices2 = new int[]{ 1, 2 };
    double[] values2 = new double[]{ 2.0, 4.0 };
    SparseVector sv2 = new SparseVector(indices2, values2, true, true, false);
    SparseVector result = sv1.vectorAdd(sv2, 0.5);
    int[] expectedIndices = new int[]{ 0, 2, 1, 2 };
    double[] expectedValues = new double[]{ 1.0, 3.0, 1.0, 2.0 };
    assertEquals(expectedIndices.length, result.numLocations());
    assertEquals(expectedValues.length, result.numLocations());
    assertEquals(0, result.indexAtLocation(0));
    assertEquals(2, result.indexAtLocation(1));
    assertEquals(1, result.indexAtLocation(2));
    assertEquals(2, result.indexAtLocation(3));
    assertEquals(1.0, result.valueAtLocation(0), 1.0E-9);
    assertEquals(3.0, result.valueAtLocation(1), 1.0E-9);
    assertEquals(1.0, result.valueAtLocation(2), 1.0E-9);
    assertEquals(2.0, result.valueAtLocation(3), 1.0E-9);
}

@Test
public void test6()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ -1.5, 2.0, -3.5 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    double result = vector.absNorm();
    assertEquals(7.0, result, 1.0E-4);
}

@Test
public void test7()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 3.0, 4.0, 12.0 };
    SparseVector vector = new SparseVector(indices, values, indices.length, false);
    double norm = vector.twoNorm();
    assertEquals(13.0, norm, 1.0E-10);
}

@Test
public void test8()
{
    int[] testIndices = new int[]{ 2 };
    double[] valuesArray = new double[]{ 0.1, 0.5, 1.2 };
    int[] indicesArray = new int[]{ 0, 1, 2 };
    SparseVector sparseVector = new SparseVector(indicesArray, valuesArray, false, false);
    double expectedValue = valuesArray[2];
    double actualValue = sparseVector.value(testIndices);
    assertEquals(expectedValue, actualValue, 1.0E-4);
}

@Test
public void test9()
{
    int[] indices = new int[]{ 2 };
    double[] values = new double[]{ 5.0 };
    int[] sortedIndices = new int[]{ 2 };
    boolean sorted = true;
    SparseVector vector = new SparseVector(indices, values, sorted);
    int[] queryIndex = new int[]{ 2 };
    double result = vector.value(queryIndex);
    assertEquals(5.0, result, 1.0E-6);
}

@Test
public void test10()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    double[] inputArray = new double[]{ 10.0, 20.0, 30.0 };
    vector.arrayCopyFrom(inputArray);
    assertEquals(10.0, vector.value(0), 1.0E-5);
    assertEquals(20.0, vector.value(2), 1.0E-5);
    assertEquals(30.0, vector.value(4), 1.0E-5);
}

@Test
public void test11()
{
    double[] vals = new double[]{ 1.2, 3.4, 5.6 };
    int[] indices = new int[]{ 0, 1, 2 };
    SparseVector vector = new SparseVector(indices, vals, vals.length);
    double[] targetArray = new double[6];
    targetArray[0] = 9.9;
    targetArray[1] = 8.8;
    int startingIndex = 2;
    int nextIndex = vector.arrayCopyInto(targetArray, startingIndex);
    assertEquals(5, nextIndex);
    assertEquals(9.9, targetArray[0], 0.001);
    assertEquals(8.8, targetArray[1], 0.001);
    assertEquals(1.2, targetArray[2], 0.001);
    assertEquals(3.4, targetArray[3], 0.001);
    assertEquals(5.6, targetArray[4], 0.001);
    assertEquals(0.0, targetArray[5], 0.001);
}

@Test
public void test12()
{
    int[] indices = new int[]{ 0, 3, 5 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector vector = new SparseVector(indices, values);
    int[] sizes = new int[1];
    int dimensionsReturned = vector.getDimensions(sizes);
    assertEquals(1, dimensionsReturned);
    assertEquals(5, sizes[0]);
}

@Test
public void test13()
{
    SparseVector sparseVector = new SparseVector(new int[]{ 0 }, new double[]{ 1.0 });
    int numDimensions = sparseVector.getNumDimensions();
    Assert.assertEquals(1, numDimensions);
}

@Test
public void test14()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector vector = new SparseVector(indices, values, true, false);
    int result = vector.numLocations();
    assertEquals(3, result);
}

@Test
public void test15()
{
    int[] input = new int[]{ 42 };
    SparseVector vector = new SparseVector(10);
    int result = vector.singleIndex(input);
    assertEquals(42, result);
}

@Test
public void test16()
{
    Alphabet alphabet = new Alphabet();
    int featureIndex1 = alphabet.lookupIndex("featureA", true);
    int featureIndex2 = alphabet.lookupIndex("featureB", true);
    int[] indices = new int[]{ featureIndex1, featureIndex2 };
    double[] values = new double[]{ 1.5, 2.5 };
    boolean isSorted = true;
    SparseVector vector = new SparseVector(alphabet, indices, values, isSorted, values.length);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals(expected, actual);
}

@Test
public void test17()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1", true);
    alphabet.lookupIndex("feature2", true);
    int[] indices = new int[]{ alphabet.lookupIndex("feature1"), alphabet.lookupIndex("feature2") };
    double[] values = new double[]{ 1.5, 2.5 };
    SparseVector sparseVector = new SparseVector(alphabet, indices, values);
    String expected = sparseVector.toString(false);
    String actual = sparseVector.toString();
    assertEquals(expected, actual);
}

@Test
public void test18()
{
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int[] indices = new int[]{ 0, 1, 2 };
    SparseVector vector = new SparseVector(indices, values, values.length, false, false);
    class Transformer {
        public static Double square(Double x) {
            return x * x;
        }
    }
    Method squareMethod = Transformer.class.getMethod("square", Double.class);
    vector.map(squareMethod);
    assertEquals(1.0, vector.value(0), 1.0E-5);
    assertEquals(4.0, vector.value(1), 1.0E-5);
    assertEquals(9.0, vector.value(2), 1.0E-5);
}

