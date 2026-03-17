import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SparseVector vector = new SparseVector();
    Field indicesField = SparseVector.class.getDeclaredField("indices");
    Field valuesField = SparseVector.class.getDeclaredField("values");
    indicesField.setAccessible(true);
    valuesField.setAccessible(true);
    int[] testIndices = new int[]{ 5, 2, 3 };
    double[] testValues = new double[]{ 50.0, 20.0, 30.0 };
    indicesField.set(vector, testIndices);
    valuesField.set(vector, testValues);
    Method method = SparseVector.class.getDeclaredMethod("sortIndices");
    method.setAccessible(true);
    method.invoke(vector);
    int[] expectedIndices = new int[]{ 2, 3, 5 };
    double[] expectedValues = new double[]{ 20.0, 30.0, 50.0 };
    assertArrayEquals(expectedIndices, ((int[]) (indicesField.get(vector))));
    assertArrayEquals(expectedValues, ((double[]) (valuesField.get(vector))), 1.0E-5);
}

@Test
public void test2()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector original = new SparseVector(indices, values);
    SparseVector cloned = ((SparseVector) (original.cloneMatrix()));
    assertNotSame("Cloned object should not be the same instance", original, cloned);
    assertArrayEquals("Indices should be equal", indices, cloned.getIndices());
    assertArrayEquals("Values should be equal", values, cloned.getValues(), 1.0E-6);
    assertEquals("Length should match", original.getNumLocations(), cloned.getNumLocations());
}

@Test
public void test3()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector original = new SparseVector(indices, values, true, false, false);
    SparseVector cloned = ((SparseVector) (original.cloneMatrixZeroed()));
    assertArrayEquals(indices, cloned.getIndices());
    assertNotSame(indices, cloned.getIndices());
    assertArrayEquals(new double[]{ 0.0, 0.0, 0.0 }, cloned.getValues(), 0.0);
    assertEquals(original.numLocations(), cloned.numLocations());
    assertEquals(original.length(), cloned.length());
}

@Test
public void test4()
{
    int[] indices1 = new int[]{ 0, 2 };
    double[] values1 = new double[]{ 1.0, 3.0 };
    SparseVector sv1 = new SparseVector(indices1, values1, true, true, false);
    int[] indices2 = new int[]{ 1, 2 };
    double[] values2 = new double[]{ 2.0, 4.0 };
    SparseVector sv2 = new SparseVector(indices2, values2, true, true, false);
    double scale = 0.5;
    SparseVector result = sv1.vectorAdd(sv2, scale);
    int[] expectedIndices = new int[]{ 0, 2, 1, 2 };
    double[] expectedValues = new double[]{ 1.0, 3.0, 1.0, 2.0 };
    assertEquals(4, result.numLocations());
    assertEquals(1.0, result.valueAtLocation(0), 1.0E-10);
    assertEquals(3.0, result.valueAtLocation(1), 1.0E-10);
    assertEquals(1.0, result.valueAtLocation(2), 1.0E-10);
    assertEquals(2.0, result.valueAtLocation(3), 1.0E-10);
    assertEquals(0, result.indexAtLocation(0));
    assertEquals(2, result.indexAtLocation(1));
    assertEquals(1, result.indexAtLocation(2));
    assertEquals(2, result.indexAtLocation(3));
}

@Test
public void test5()
{
    int[] indices = new int[]{ 0, 1, 2 };
    double[] values = new double[]{ -1.0, 2.0, -3.0 };
    SparseVector vector = new SparseVector(indices, values, false, false);
    double result = vector.absNorm();
    assertEquals(6.0, result, 1.0E-5);
}

@Test
public void test6()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 3.0, 4.0, 0.0 };
    SparseVector vector = new SparseVector(indices, values, indices.length, true, false);
    double result = vector.twoNorm();
    assertEquals(5.0, result, 1.0E-5);
}

@Test
public void test7()
{
    int[] indicesArray = new int[]{ 3 };
    double[] valuesArray = new double[]{ 5.0 };
    SparseVector sparseVector = new SparseVector(indicesArray, valuesArray, true, false);
    int[] queryIndex = new int[]{ 3 };
    double result = sparseVector.value(queryIndex);
    assertEquals(5.0, result, 1.0E-4);
}

@Test
public void test8()
{
    int[] indices = new int[]{ 2 };
    double[] values = new double[]{ 0.0, 0.0, 5.5 };
    int[] sortedIndices = new int[]{ 2 };
    int size = 3;
    SparseVector vector = new SparseVector(sortedIndices, values, size, false) {
        @Override
        protected int location(int index) {
            return 0;
        }
    };
    double result = vector.value(new int[]{ 2 });
    assertEquals(5.5, result, 1.0E-4);
}

@Test
public void test9()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int length = 5;
    SparseVector vector = new SparseVector(indices, values, indices.length, false, length);
    double[] sourceArray = new double[]{ 10.0, 0.0, 20.0, 0.0, 30.0 };
    vector.arrayCopyFrom(sourceArray);
    assertEquals(10.0, vector.value(0), 1.0E-5);
    assertEquals(20.0, vector.value(2), 1.0E-5);
    assertEquals(30.0, vector.value(4), 1.0E-5);
    assertEquals(0.0, vector.value(1), 1.0E-5);
    assertEquals(0.0, vector.value(3), 1.0E-5);
}

@Test
public void test10()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.5, 2.5, 3.5 };
    boolean sorted = true;
    boolean compacted = true;
    SparseVector vector = new SparseVector(indices, values, sorted, compacted);
    double[] targetArray = new double[6];
    targetArray[0] = -1.0;
    targetArray[1] = -1.0;
    targetArray[2] = -1.0;
    targetArray[3] = -1.0;
    targetArray[4] = -1.0;
    targetArray[5] = -1.0;
    int resultIndex = vector.arrayCopyInto(targetArray, 2);
    assertEquals(5, resultIndex);
    assertEquals(-1.0, targetArray[0], 0.0);
    assertEquals(-1.0, targetArray[1], 0.0);
    assertEquals(1.5, targetArray[2], 0.0);
    assertEquals(2.5, targetArray[3], 0.0);
    assertEquals(3.5, targetArray[4], 0.0);
    assertEquals(-1.0, targetArray[5], 0.0);
}

@Test
public void test11()
{
    SparseVector vector = new SparseVector(new int[]{ 2, 4, 6 }, new double[]{ 1.0, 2.0, 3.0 });
    int[] sizes = new int[1];
    int result = vector.getDimensions(sizes);
    assertEquals(1, result);
    assertEquals(6, sizes[0]);
}

@Test
public void test12()
{
    SparseVector sparseVector = new SparseVector(new int[]{ 0 }, new double[]{ 1.0 }, true, false);
    int result = sparseVector.getNumDimensions();
    Assert.assertEquals(1, result);
}

@Test
public void test13()
{
    SparseVector vector = new SparseVector();
    Field valuesField = SparseVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, null);
    Field indicesField = SparseVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, new int[]{ 0, 2, 5 });
    int result = vector.numLocations();
    assertEquals(3, result);
}

@Test
public void test14()
{
    SparseVector vector = new SparseVector(10);
    int[] input = new int[]{ 7 };
    int result = vector.singleIndex(input);
    assertEquals(7, result);
}

@Test
public void test15()
{
    Alphabet alphabet = new Alphabet();
    int[] indices = new int[]{ 1, 3 };
    double[] values = new double[]{ 1.5, -2.0 };
    SparseVector vector = new SparseVector(alphabet, indices, values);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals("Expected toString() to return result of toString(false)", expected, actual);
}

@Test
public void test16()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("token1");
    alphabet.lookupIndex("token2");
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 1.5, 2.5 };
    boolean sorted = true;
    boolean binary = false;
    SparseVector vector = new SparseVector(alphabet, indices, values, sorted, binary);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals(expected, actual);
}

@Test
public void test17()
{
    double[] data = new double[]{ 1.0, 2.0, 3.0 };
    int[] indices = new int[]{ 0, 1, 2 };
    int size = 3;
    SparseVector vector = new SparseVector(indices, data, size);
    Method squareMethod = getClass().getDeclaredMethod("square", Double.class);
    vector.map(squareMethod);
    double[] expected = new double[]{ 1.0, 4.0, 9.0 };
    assertEquals(3, vector.numLocations());
    assertEquals(1.0, vector.valueAtLocation(0), 0.0);
    assertEquals(4.0, vector.valueAtLocation(1), 0.0);
    assertEquals(9.0, vector.valueAtLocation(2), 0.0);
}

@Test
public void test18()
{
    int[] indicesThis = new int[]{ 1, 3, 5 };
    double[] valuesThis = new double[]{ 2.0, 4.0, 6.0 };
    SparseVector vecThis = new SparseVector(indicesThis, valuesThis, false, false);
    int[] indicesV = new int[]{ 3, 5, 7 };
    double[] valuesV = new double[]{ 10.0, 0.5, 8.0 };
    SparseVector vecV = new SparseVector(indicesV, valuesV, false, false);
    vecThis.timesEqualsSparseZero(vecV, 2.0);
    assertEquals(0.0, vecThis.valueAtLocation(0), 1.0E-5);
    assertEquals(80.0, vecThis.valueAtLocation(1), 1.0E-5);
    assertEquals(6.0, vecThis.valueAtLocation(2), 1.0E-5);
    assertEquals(1, vecThis.indexAtLocation(0));
    assertEquals(3, vecThis.indexAtLocation(1));
    assertEquals(5, vecThis.indexAtLocation(2));
}

