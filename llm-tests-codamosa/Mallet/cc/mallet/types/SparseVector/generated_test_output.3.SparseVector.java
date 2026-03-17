import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SparseVector vector = ((SparseVector) (Constructor.class.getDeclaredConstructor(new Class[0]).newInstance()));
    Field indicesField = SparseVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, new int[]{ 3, 1, 2 });
    Field valuesField = SparseVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, new double[]{ 30.0, 10.0, 20.0 });
    Method sortIndicesMethod = SparseVector.class.getDeclaredMethod("sortIndices");
    sortIndicesMethod.setAccessible(true);
    sortIndicesMethod.invoke(vector);
    int[] expectedIndices = new int[]{ 1, 2, 3 };
    double[] expectedValues = new double[]{ 10.0, 20.0, 30.0 };
    int[] sortedIndices = ((int[]) (indicesField.get(vector)));
    double[] sortedValues = ((double[]) (valuesField.get(vector)));
    assertArrayEquals(expectedIndices, sortedIndices);
    assertArrayEquals(expectedValues, sortedValues, 1.0E-5);
}

@Test
public void test2()
{
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 1.0, Double.NaN };
    boolean lengthOK = true;
    SparseVector vector = new SparseVector(indices, values, values.length, lengthOK);
    assertTrue(vector.isNaN());
}

@Test
public void test3()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector original = new SparseVector(indices, values);
    ConstantMatrix cloned = original.cloneMatrix();
    assertNotNull(cloned);
    assertTrue(cloned instanceof SparseVector);
    SparseVector clonedVector = ((SparseVector) (cloned));
    assertArrayEquals(indices, clonedVector.getIndices());
    assertArrayEquals(values, clonedVector.getValues(), 1.0E-5);
    assertNotSame(original, cloned);
}

@Test
public void test4()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    SparseVector original = new SparseVector(indices, values, true, false, false);
    SparseVector cloned = ((SparseVector) (original.cloneMatrixZeroed()));
    assertNotSame("Cloned instance should be a new object", original, cloned);
    assertArrayEquals("Indices should be copied correctly", indices, cloned.getIndices());
    assertArrayEquals("Values should be zeroed", new double[]{ 0.0, 0.0, 0.0 }, cloned.getValues(), 1.0E-5);
    assertEquals("Cloned vector should have same length as original", original.size(), cloned.size());
}

@Test
public void test5()
{
    int[] indicesA = new int[]{ 0, 2 };
    double[] valuesA = new double[]{ 1.0, 3.0 };
    SparseVector sparseA = new SparseVector(indicesA, valuesA, true, true, false);
    int[] indicesB = new int[]{ 1, 2 };
    double[] valuesB = new double[]{ 4.0, -1.0 };
    SparseVector sparseB = new SparseVector(indicesB, valuesB, true, true, false);
    SparseVector result = sparseA.vectorAdd(sparseB, 0.5);
    assertEquals(3, result.numLocations());
    assertEquals(1.0, result.valueAtLocation(0), 1.0E-10);
    assertEquals(2.0, result.valueAtLocation(1), 1.0E-10);
    assertEquals(2.5, result.valueAtLocation(2), 1.0E-10);
    assertEquals(0, result.indexAtLocation(0));
    assertEquals(1, result.indexAtLocation(1));
    assertEquals(2, result.indexAtLocation(2));
}

@Test
public void test6()
{
    int[] indices = new int[]{ 0, 2, 4 };
    double[] values = new double[]{ -1.5, 2.0, -3.5 };
    SparseVector vector = new SparseVector(indices, values, false);
    double result = vector.absNorm();
    assertEquals(7.0, result, 1.0E-9);
}

@Test
public void test7()
{
    int[] indices = new int[]{ 0, 2, 5 };
    double[] values = null;
    SparseVector vector = new SparseVector(indices, values, false, false);
    double expected = Math.sqrt(indices.length);
    double actual = vector.twoNorm();
    assertEquals(expected, actual, 1.0E-10);
}

@Test
public void test8()
{
    int[] mockIndices = new int[]{ 2 };
    double[] mockValues = new double[]{ 0.0, 1.0, 2.5, 3.0 };
    SparseVector vector = new SparseVector(mockIndices, mockValues) {
        @Override
        protected int location(int index) {
            return index;
        }
    };
    int[] input = new int[]{ 2 };
    double expected = 2.5;
    double actual = vector.value(input);
    assertEquals(expected, actual, 1.0E-5);
}

@Test
public void test9()
{
    int[] sparseIndices = new int[]{ 2 };
    double[] sparseValues = new double[]{ 0.0, 1.5, 3.7 };
    int[] denseIndices = new int[]{ 0, 1, 2 };
    SparseVector vector = new SparseVector(denseIndices, sparseValues, false, false, false);
    double expected = 3.7;
    double actual = vector.value(sparseIndices);
    assertEquals(expected, actual, 1.0E-6);
}

@Test
public void test10()
{
    int[] indices = new int[]{ 0, 1, 2 };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int length = 3;
    SparseVector vector = new SparseVector(indices, values, length, false);
    double[] newValues = new double[]{ 4.0, 5.0, 6.0 };
    vector.arrayCopyFrom(newValues);
    assertEquals(4.0, vector.value(0), 1.0E-5);
    assertEquals(5.0, vector.value(1), 1.0E-5);
    assertEquals(6.0, vector.value(2), 1.0E-5);
}

@Test
public void test11()
{
    SparseVector sparseVector = new SparseVector(new int[]{ 0, 2 }, new double[]{ 1.5, -2.0 }, false, false);
    double[] targetArray = new double[5];
    targetArray[0] = 0.0;
    targetArray[1] = 0.0;
    targetArray[2] = 0.0;
    targetArray[3] = 0.0;
    targetArray[4] = 0.0;
    int startingIndex = 2;
    int nextPosition = sparseVector.arrayCopyInto(targetArray, startingIndex);
    assertEquals(4, nextPosition);
    assertEquals(0.0, targetArray[0], 0.0);
    assertEquals(0.0, targetArray[1], 0.0);
    assertEquals(1.5, targetArray[2], 0.0);
    assertEquals(-2.0, targetArray[3], 0.0);
    assertEquals(0.0, targetArray[4], 0.0);
}

@Test
public void test12()
{
    SparseVector vector = new SparseVector(new int[]{ 0, 2, 5 }, new double[]{ 1.0, 2.0, 3.0 });
    int[] sizes = new int[1];
    int result = vector.getDimensions(sizes);
    assertEquals(1, result);
    assertEquals(5, sizes[0]);
}

@Test
public void test13()
{
    SparseVector sparseVector = new SparseVector(new int[]{ 0 }, new double[]{ 1.0 });
    int numDimensions = sparseVector.getNumDimensions();
    assertEquals(1, numDimensions);
}

@Test
public void test14()
{
    int[] testIndices = new int[]{ 0, 2, 4 };
    double[] testValues = new double[]{ 1.0, 0.5, -3.2 };
    SparseVector vector = new SparseVector(testIndices, testValues, false, false);
    assertEquals("numLocations should return length of values when values is not null", 3, vector.numLocations());
}

@Test
public void test15()
{
    SparseVector vector = new SparseVector(new int[]{ 0 }, new double[]{ 1.0 }, false, false);
    int[] input = new int[]{ 42 };
    int result = vector.singleIndex(input);
    assertEquals(42, result);
}

@Test
public void test16()
{
    Alphabets.Alphabet alphabet = new Alphabets.Alphabet();
    alphabet.lookupIndex("feature1");
    alphabet.lookupIndex("feature2");
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 1.0, 2.0 };
    boolean sorted = true;
    SparseVector vector = new SparseVector(alphabet, indices, values, sorted);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals(expected, actual);
}

@Test
public void test17()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("test");
    int[] indices = new int[]{ 0 };
    double[] values = new double[]{ 1.0 };
    boolean sorted = true;
    SparseVector vector = new SparseVector(alphabet, indices, values, sorted);
    String expected = vector.toString(false);
    String actual = vector.toString();
    assertEquals(expected, actual);
}

@Test
public void test18()
{
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    int[] indices = new int[]{ 0, 1, 2 };
    SparseVector vector = new SparseVector(indices, values, values.length, false, false);
    class DoubleFunction {
        public static Double square(Double x) {
            return x * x;
        }
    }
    Method method = DoubleFunction.class.getMethod("square", Double.class);
    vector.map(method);
    assertEquals(1.0, vector.value(0), 1.0E-4);
    assertEquals(4.0, vector.value(1), 1.0E-4);
    assertEquals(9.0, vector.value(2), 1.0E-4);
}

