import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SparseVector vector = new SparseVector(new int[]{ 3, 1, 2, 2 }, new double[]{ 0.3, 0.1, 0.2, 0.4 }, false, false);
    Method m = SparseVector.class.getDeclaredMethod("sortIndices");
    m.setAccessible(true);
    m.invoke(vector);
    Field indicesField = SparseVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    int[] indices = ((int[]) (indicesField.get(vector)));
    Field valuesField = SparseVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    double[] values = ((double[]) (valuesField.get(vector)));
    assertEquals(3, indices.length);
    assertArrayEquals(new int[]{ 1, 2, 3 }, indices);
    assertArrayEquals(new double[]{ 0.1, 0.6, 0.3 }, values, 1.0E-6);
}

@Test
public void test2()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 3.0, 5.0 };
    SparseVector original = new SparseVector(indices, values, true, false, false);
    SparseVector cloned = ((SparseVector) (original.cloneMatrix()));
    assertNotSame(original, cloned);
    assertArrayEquals(indices, cloned.getIndices());
    assertArrayEquals(values, cloned.getValues(), 1.0E-6);
    assertTrue(cloned.isSorted());
    assertFalse(cloned.isBinary());
    assertFalse(cloned.isZero());
}

@Test
public void test3()
{
    int[] indices = new int[]{ 1, 3, 5 };
    double[] values = new double[]{ 2.5, -1.0, 4.0 };
    boolean isSorted = true;
    boolean isLengthFixed = false;
    boolean isImmutable = false;
    SparseVector original = new SparseVector(indices, values, isSorted, isLengthFixed, isImmutable);
    SparseVector cloned = ((SparseVector) (original.cloneMatrixZeroed()));
    assertNotSame("Cloned object should not be the same as original", original, cloned);
    assertArrayEquals("Indices should be equal after cloning", indices, cloned.getIndices());
    assertArrayEquals("Values array should be zeroed", new double[]{ 0.0, 0.0, 0.0 }, cloned.getValues(), 0.0);
    assertEquals("isSorted flag should be preserved", isSorted, cloned.isSorted());
    assertEquals("isLengthFixed flag should be preserved", isLengthFixed, cloned.isLengthFixed());
    assertEquals("isImmutable flag should be preserved", isImmutable, cloned.isImmutable());
}

@Test
public void test4()
{
    int[] denseIndices = null;
    double[] denseValues = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector denseVector1 = new SparseVector(denseIndices, denseValues, false);
    double[] denseValues2 = new double[]{ 0.5, -2.0, 1.5 };
    SparseVector denseVector2 = new SparseVector(null, denseValues2, false);
    SparseVector result = denseVector1.vectorAdd(denseVector2, 2.0);
    assertEquals(3, result.numLocations());
    assertEquals(0, result.indexAtLocation(0));
    assertEquals(2.0, result.valueAtLocation(0), 1.0E-10);
    assertEquals(1, result.indexAtLocation(1));
    assertEquals(-2.0, result.valueAtLocation(1), 1.0E-10);
    assertEquals(2, result.indexAtLocation(2));
    assertEquals(6.0, result.valueAtLocation(2), 1.0E-10);
}

@Test
public void test5()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ -1.0, 2.5, -3.5 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    double expected = (Math.abs(-1.0) + Math.abs(2.5)) + Math.abs(-3.5);
    assertEquals(expected, vector.absNorm(), 1.0E-5);
}

@Test
public void test6()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 3.0, 4.0, 0.0 };
    SparseVector vector = new SparseVector(indices, values, indices.length, false);
    double result = vector.twoNorm();
    assertEquals(5.0, result, 1.0E-10);
}

@Test
public void test7()
{
    double[] values = new double[]{ 42.0 };
    int[] indices = new int[]{ 3 };
    SparseVector vector = new SparseVector(new int[]{ 3 }, new double[]{ 42.0 }, false, false);
    double result = vector.value(new int[]{ 3 });
    assertEquals(42.0, result, 1.0E-5);
}

@Test
public void test8()
{
    double[] testValues = new double[]{ 0.0, 2.5, 4.0 };
    int[] testIndices = new int[]{ 0, 1, 2 };
    SparseVector vector = new SparseVector(testIndices, testValues, true, false);
    int[] inputIndices = new int[]{ 1 };
    double result = vector.value(inputIndices);
    assertEquals(2.5, result, 1.0E-6);
}

@Test
public void test9()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] initialValues = new double[]{ 0.0, 0.0, 0.0 };
    SparseVector vector = new SparseVector(indices, initialValues, 5);
    double[] newValues = new double[]{ 1.0, 2.0, 3.0 };
    vector.arrayCopyFrom(newValues);
    assertEquals(1.0, vector.value(0), 1.0E-5);
    assertEquals(0.0, vector.value(1), 1.0E-5);
    assertEquals(2.0, vector.value(2), 1.0E-5);
    assertEquals(0.0, vector.value(3), 1.0E-5);
    assertEquals(3.0, vector.value(4), 1.0E-5);
}

@Test
public void test10()
{
    int[] indices = new int[]{ 1, 3, 7 };
    double[] values = new double[]{ 2.5, 4.0, -3.3 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    double[] targetArray = new double[10];
    for (int i = 0; i < targetArray.length; i++) {
        targetArray[i] = 0.0;
    }
    int resultIndex = vector.arrayCopyInto(targetArray, 2);
    assertEquals(5, resultIndex);
    assertEquals(0.0, targetArray[0], 1.0E-4);
    assertEquals(0.0, targetArray[1], 1.0E-4);
    assertEquals(2.5, targetArray[2], 1.0E-4);
    assertEquals(4.0, targetArray[3], 1.0E-4);
    assertEquals(-3.3, targetArray[4], 1.0E-4);
    assertEquals(0.0, targetArray[5], 1.0E-4);
}

@Test
public void test11()
{
    int[] indices = new int[]{ 2, 4, 7 };
    double[] values = new double[]{ 1.0, 3.5, 2.2 };
    boolean[] binary = new boolean[]{ false, false, false };
    int capacity = 3;
    SparseVector vector = new SparseVector(indices, values, binary, capacity);
    int[] sizes = new int[1];
    int result = vector.getDimensions(sizes);
    assertEquals(1, result);
    assertEquals(7, sizes[0]);
}

@Test
public void test12()
{
    SparseVector sparseVector = new SparseVector(new int[]{ 0 }, new double[]{ 1.0 }, false, false);
    int result = sparseVector.getNumDimensions();
    assertEquals(1, result);
}

@Test
public void test13()
{
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int[] indices = new int[]{ 0, 2, 5 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    assertEquals(3, vector.numLocations());
}

@Test
public void test14()
{
    SparseVector vector = new SparseVector(1);
    int[] indices = new int[]{ 42 };
    int result = vector.singleIndex(indices);
    assertEquals(42, result);
}

@Test
public void test15()
{
    Alphabet alphabet = new Alphabet();
    int indexOne = alphabet.lookupIndex("feature1", true);
    int indexTwo = alphabet.lookupIndex("feature2", true);
    int[] indices = new int[]{ indexOne, indexTwo };
    double[] values = new double[]{ 1.0, 2.0 };
    SparseVector vector = new SparseVector(alphabet, indices, values);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals(expected, actual);
}

@Test
public void test16()
{
    Alphabet alphabet = new Alphabet();
    int idx1 = alphabet.lookupIndex("feature1");
    int idx2 = alphabet.lookupIndex("feature2");
    int[] indices = new int[]{ idx1, idx2 };
    double[] values = new double[]{ 1.5, 2.5 };
    boolean sorted = true;
    int capacity = 2;
    SparseVector vector = new SparseVector(alphabet, indices, values, sorted, capacity);
    String result = vector.toString();
    assertEquals(vector.toString(false), result);
}

@Test
public void test17()
{
    int[] indices = new int[]{ 0, 2 };
    double[] values = new double[]{ 2.0, 3.0 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    class FunctionHolder {
        public static Double square(Double x) {
            return x * x;
        }
    }
    Method squareMethod = FunctionHolder.class.getMethod("square", Double.class);
    vector.map(squareMethod);
    assertEquals(4.0, vector.value(0), 1.0E-5);
    assertEquals(9.0, vector.value(2), 1.0E-5);
}

@Test
public void test18()
{
    int[] indices1 = new int[]{ 0, 2, 4 };
    double[] values1 = new double[]{ 1.0, 2.0, 3.0 };
    int length = 5;
    SparseVector v1 = new SparseVector(indices1, values1, length);
    int[] indices2 = new int[]{ 2, 4 };
    double[] values2 = new double[]{ 10.0, 5.0 };
    SparseVector v2 = new SparseVector(indices2, values2, length);
    v1.timesEqualsSparseZero(v2, 2.0);
    double[] expected = new double[]{ 0.0, 40.0, 30.0 };
    int[] expectedIndices = new int[]{ 0, 2, 4 };
    assertEquals(3, v1.numLocations());
    assertEquals(expectedIndices[0], v1.indexAtLocation(0));
    assertEquals(expected[0], v1.valueAtLocation(0), 1.0E-5);
    assertEquals(expectedIndices[1], v1.indexAtLocation(1));
    assertEquals(expected[1], v1.valueAtLocation(1), 1.0E-5);
    assertEquals(expectedIndices[2], v1.indexAtLocation(2));
    assertEquals(expected[2], v1.valueAtLocation(2), 1.0E-5);
}

