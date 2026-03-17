import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SparseVector vector = ((SparseVector) (UnsafeAllocator.create(SparseVector.class)));
    Field indicesField = SparseVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, new int[]{ 3, 1, 2 });
    Field valuesField = SparseVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, new double[]{ 0.3, 0.1, 0.2 });
    Method sortIndicesMethod = SparseVector.class.getDeclaredMethod("sortIndices");
    sortIndicesMethod.setAccessible(true);
    sortIndicesMethod.invoke(vector);
    int[] expectedIndices = new int[]{ 1, 2, 3 };
    double[] expectedValues = new double[]{ 0.1, 0.2, 0.3 };
    int[] actualIndices = ((int[]) (indicesField.get(vector)));
    double[] actualValues = ((double[]) (valuesField.get(vector)));
    assertArrayEquals(expectedIndices, actualIndices);
    assertArrayEquals(expectedValues, actualValues, 1.0E-5);
}

@Test
public void test2()
{
    int[] indices = new int[]{ 1, 3, 5 };
    double[] values = new double[]{ 0.5, 1.5, -2.0 };
    SparseVector original = new SparseVector(indices, values, true, false, false);
    ConstantMatrix cloned = original.cloneMatrix();
    assertNotSame(original, cloned);
    assertTrue(cloned instanceof SparseVector);
    SparseVector clonedVector = ((SparseVector) (cloned));
    assertArrayEquals(indices, clonedVector.getIndices());
    assertArrayEquals(values, clonedVector.getValues(), 0.0);
}

@Test
public void test3()
{
    int[] indices = new int[]{ 1, 3, 5 };
    double[] values = new double[]{ 10.0, 20.0, 30.0 };
    boolean sorted = true;
    boolean cacheEnabled = false;
    boolean binary = false;
    SparseVector original = new SparseVector(indices, values, sorted, cacheEnabled, binary);
    SparseVector cloned = ((SparseVector) (original.cloneMatrixZeroed()));
    assertNotSame("The cloned object should be a different instance", original, cloned);
    assertArrayEquals("Indices should be the same", indices, cloned.getIndices());
    assertArrayEquals("Values should be zeroed", new double[]{ 0.0, 0.0, 0.0 }, cloned.getValues(), 1.0E-6);
}

@Test
public void test4()
{
    int[] indices1 = new int[]{ 0, 2 };
    double[] values1 = new double[]{ 1.0, 2.0 };
    SparseVector sv1 = new SparseVector(indices1, values1, true, true, false);
    int[] indices2 = new int[]{ 1, 2 };
    double[] values2 = new double[]{ 3.0, 4.0 };
    SparseVector sv2 = new SparseVector(indices2, values2, true, true, false);
    double scale = 2.0;
    SparseVector result = sv1.vectorAdd(sv2, scale);
    int[] expectedIndices = new int[]{ 0, 2, 1, 2 };
    double[] expectedValues = new double[]{ 1.0, 2.0, 6.0, 8.0 };
    assertEquals(4, result.numLocations());
    assertEquals(4, result.numNonZeroEntries());
    assertEquals(1.0, result.valueAtLocation(0), 1.0E-5);
    assertEquals(2.0, result.valueAtLocation(1), 1.0E-5);
    assertEquals(6.0, result.valueAtLocation(2), 1.0E-5);
    assertEquals(8.0, result.valueAtLocation(3), 1.0E-5);
    assertEquals(0, result.indexAtLocation(0));
    assertEquals(2, result.indexAtLocation(1));
    assertEquals(1, result.indexAtLocation(2));
    assertEquals(2, result.indexAtLocation(3));
}

@Test
public void test5()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ -1.5, 2.0, -3.5 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    double expected = (Math.abs(-1.5) + Math.abs(2.0)) + Math.abs(-3.5);
    double result = vector.absNorm();
    assertEquals(expected, result, 1.0E-10);
}

@Test
public void test6()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    double[] inputVector = new double[]{ 1.0, 0.0, 2.0, 0.0, 3.0 };
    SparseVector sparseVector = new SparseVector(indices, values, indices.length, true, false);
    double result = sparseVector.dotProduct(inputVector);
    double expected = ((1.0 * 1.0) + (2.0 * 2.0)) + (3.0 * 3.0);
    assertEquals(expected, result, 1.0E-5);
}

@Test
public void test7()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    double[] v = new double[]{ 10.0, 0.0, 20.0, 0.0, 30.0 };
    SparseVector vector = new SparseVector(indices, values, indices.length, true, false);
    double result = vector.dotProduct(v);
    double expected = ((1.0 * 10.0) + (2.0 * 20.0)) + (3.0 * 30.0);
    assertEquals(expected, result, 1.0E-10);
}

@Test
public void test8()
{
    int[] indices = new int[]{ 1, 3, 5 };
    double[] values = new double[]{ 2.0, 4.0, 6.0 };
    double[] inputVector = new double[]{ 0.0, 1.5, 0.0, 2.0, 0.0, 1.0 };
    SparseVector sparseVector = new SparseVector(indices, values, indices.length);
    double result = sparseVector.dotProduct(inputVector);
    assertEquals(17.0, result, 1.0E-4);
}

@Test
public void test9()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int length = 5;
    SparseVector vector = new SparseVector(indices, values, false, false, length);
    double[] v = new double[]{ 10.0, 0.0, 5.0, 0.0, 2.0 };
    double expected = 26.0;
    double result = vector.dotProduct(v);
    assertEquals(expected, result, 1.0E-6);
}

@Test
public void test10()
{
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ Double.POSITIVE_INFINITY, 0.0 };
    SparseVector sparseVector = new SparseVector(indices, values, false, false);
    double[] denseValues = new double[2];
    denseValues[0] = 0.0;
    denseValues[1] = Double.NEGATIVE_INFINITY;
    DenseVector denseVector = new DenseVector(denseValues);
    double result = sparseVector.extendedDotProduct(denseVector);
    assertEquals(0.0, result, 0.0);
    assertTrue(sparseVector.hasInfinite);
    assertTrue(denseVector.hasInfinite);
}

@Test
public void test11()
{
    int[] indices = new int[]{ 0, 1, 2 };
    double[] values = new double[]{ Double.NEGATIVE_INFINITY, 2.0, 0.0 };
    boolean sorted = true;
    SparseVector sparseVector = new SparseVector(indices, values, values.length, sorted);
    double[] denseValues = new double[]{ 0.0, 3.0, Double.POSITIVE_INFINITY };
    DenseVector denseVector = new DenseVector(denseValues);
    double result = sparseVector.extendedDotProduct(denseVector);
    assertEquals(6.0, result, 1.0E-5);
}

@Test
public void test12()
{
    Alphabet alphabet = new Alphabet();
    int[] indices = new int[]{ 2 };
    double[] values = new double[]{ 5.5 };
    boolean sorted = true;
    int length = 1;
    SparseVector vector = new SparseVector(alphabet, indices, values, sorted, length);
    double result = vector.singleValue(2);
    assertEquals(5.5, result, 1.0E-5);
}

@Test
public void test13()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 3.0, 4.0, 12.0 };
    SparseVector vector = new SparseVector(indices, values, false);
    double expected = Math.sqrt(((3.0 * 3.0) + (4.0 * 4.0)) + (12.0 * 12.0));
    double actual = vector.twoNorm();
    assertEquals(expected, actual, 1.0E-10);
}

@Test
public void test14()
{
    int[] testIndices = new int[]{ 2 };
    double[] values = new double[]{ 0.0, 1.5, 2.5 };
    int[] indices = new int[]{ 0, 1, 2 };
    SparseVector vector = new SparseVector(indices, values, values.length) {
        @Override
        protected int location(int index) {
            return index;
        }
    };
    double expected = 2.5;
    double actual = vector.value(testIndices);
    assertEquals(expected, actual, 1.0E-4);
}

@Test
public void test15()
{
    double[] values = new double[]{ 0.0, 1.5, 3.2 };
    int[] indices = new int[]{ 0, 2, 4 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    int testIndex = 2;
    int[] inputIndices = new int[]{ testIndex };
    double expectedValue = values[vector.location(testIndex)];
    double actualValue = vector.value(inputIndices);
    assertEquals(expectedValue, actualValue, 1.0E-5);
}

@Test
public void test16()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int size = 5;
    SparseVector vector = new SparseVector(indices, values, false, size);
    double[] arrayToCopy = new double[]{ 10.0, 0.0, 20.0, 0.0, 30.0 };
    vector.arrayCopyFrom(arrayToCopy);
    assertEquals(10.0, vector.value(0), 1.0E-4);
    assertEquals(0.0, vector.value(1), 1.0E-4);
    assertEquals(20.0, vector.value(2), 1.0E-4);
    assertEquals(0.0, vector.value(3), 1.0E-4);
    assertEquals(30.0, vector.value(4), 1.0E-4);
}

@Test
public void test17()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 3.0, 5.0 };
    SparseVector sparseVector = new SparseVector(indices, values, false, false);
    double[] destinationArray = new double[6];
    destinationArray[0] = -1.0;
    destinationArray[1] = -1.0;
    destinationArray[2] = -1.0;
    destinationArray[3] = -1.0;
    destinationArray[4] = -1.0;
    destinationArray[5] = -1.0;
    int nextLocation = sparseVector.arrayCopyInto(destinationArray, 2);
    assertEquals(5, nextLocation);
    assertEquals(-1.0, destinationArray[0], 1.0E-4);
    assertEquals(-1.0, destinationArray[1], 1.0E-4);
    assertEquals(1.0, destinationArray[2], 1.0E-4);
    assertEquals(3.0, destinationArray[3], 1.0E-4);
    assertEquals(5.0, destinationArray[4], 1.0E-4);
    assertEquals(-1.0, destinationArray[5], 1.0E-4);
}

@Test
public void test18()
{
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int[] indices = new int[]{ 2, 4, 7 };
    int[] sizes = new int[1];
    SparseVector vector = new SparseVector(indices, values, false);
    int result = vector.getDimensions(sizes);
    assertEquals(1, result);
    assertEquals(7, sizes[0]);
}

@Test
public void test19()
{
    SparseVector sparseVector = new SparseVector(null, null, true, false);
    int numDimensions = sparseVector.getNumDimensions();
    assertEquals(1, numDimensions);
}

@Test
public void test20()
{
    double[] values = new double[]{ 1.0, 0.0, 3.5 };
    int[] indices = new int[]{ 0, 2, 5 };
    SparseVector vector = new SparseVector(10, indices, values);
    int result = vector.numLocations();
    assertEquals(3, result);
}

@Test
public void test21()
{
    int[] input = new int[]{ 42 };
    SparseVector vector = new SparseVector(1);
    int result = vector.singleIndex(input);
    assertEquals(42, result);
}

@Test
public void test22()
{
    Alphabets.Alphabet alphabet = new Alphabets.Alphabet();
    alphabet.lookupIndex("feature1", true);
    alphabet.lookupIndex("feature2", true);
    int[] indices = new int[]{ alphabet.lookupIndex("feature1", false), alphabet.lookupIndex("feature2", false) };
    double[] values = new double[]{ 1.5, 2.5 };
    SparseVector vector = new SparseVector(alphabet, indices, values);
    String result = vector.toString();
    assertNotNull(result);
    assertTrue(result.contains("feature1"));
    assertTrue(result.contains("feature2"));
    assertTrue(result.contains("1.5"));
    assertTrue(result.contains("2.5"));
}

@Test
public void test23()
{
    Alphabet alphabet = new Alphabet();
    int featureIndex = alphabet.lookupIndex("feature1");
    int[] indices = new int[]{ featureIndex };
    double[] values = new double[]{ 2.5 };
    SparseVector vector = new SparseVector(alphabet, indices, values);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals(expected, actual);
}

@Test
public void test24()
{
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int[] indices = new int[]{ 0, 1, 2 };
    int length = 3;
    SparseVector vector = new SparseVector(indices, values, length, false, false);
    Method multiplyByTwo = SparseVectorTest.class.getDeclaredMethod("multiplyByTwo", Double.class);
    vector.map(multiplyByTwo);
    assertEquals(2.0, vector.value(0), 1.0E-4);
    assertEquals(4.0, vector.value(1), 1.0E-4);
    assertEquals(6.0, vector.value(2), 1.0E-4);
}

@Test
public void test25()
{
    int[] indices1 = new int[]{ 1, 3, 5 };
    double[] values1 = new double[]{ 2.0, 4.0, 6.0 };
    int size1 = 6;
    SparseVector sv1 = new SparseVector(indices1, values1, size1);
    int[] indices2 = new int[]{ 3, 4, 5 };
    double[] values2 = new double[]{ 10.0, 20.0, 30.0 };
    int size2 = 6;
    SparseVector sv2 = new SparseVector(indices2, values2, size2);
    sv1.timesEqualsSparseZero(sv2, 2.0);
    assertEquals(0.0, sv1.value(1), 1.0E-10);
    assertEquals(80.0, sv1.value(3), 1.0E-10);
    assertEquals(360.0, sv1.value(5), 1.0E-10);
    assertEquals(0.0, sv1.value(0), 1.0E-10);
    assertEquals(0.0, sv1.value(2), 1.0E-10);
    assertEquals(0.0, sv1.value(4), 1.0E-10);
}

