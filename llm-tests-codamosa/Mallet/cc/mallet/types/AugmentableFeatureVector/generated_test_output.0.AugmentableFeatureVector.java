import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    AugmentableFeatureVector afv = new AugmentableFeatureVector();
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(afv, new int[]{ 1, 1, 2, 3, 3, 3, 4 });
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(afv, new double[]{ 0.5, 1.5, 2.0, 0.1, 0.2, 0.7, 3.0 });
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(afv, 7);
    Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
    maxSortedIndexField.setAccessible(true);
    maxSortedIndexField.setInt(afv, 6);
    afv.removeDuplicates(0);
    int[] expectedIndices = new int[]{ 1, 2, 3, 4 };
    double[] expectedValues = new double[]{ 2.0, 2.0, 1.0, 3.0 };
    int[] actualIndices = ((int[]) (indicesField.get(afv)));
    double[] actualValues = ((double[]) (valuesField.get(afv)));
    int actualSize = sizeField.getInt(afv);
    int actualMaxSortedIndex = maxSortedIndexField.getInt(afv);
    assertArrayEquals(expectedIndices, actualIndices);
    assertArrayEquals(expectedValues, actualValues, 1.0E-4);
    assertEquals(4, actualSize);
    assertEquals(3, actualMaxSortedIndex);
}

@Test
public void test2()
{
    Alphabet dictionary = new Alphabet();
    dictionary.lookupIndex("feature1");
    dictionary.lookupIndex("feature2");
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 1.0, 2.0 };
    AugmentableFeatureVector originalVector = new AugmentableFeatureVector(dictionary, indices, values, indices.length, 2, false, false, false);
    ConstantMatrix clonedMatrix = originalVector.cloneMatrix();
    assertTrue(clonedMatrix instanceof AugmentableFeatureVector);
    AugmentableFeatureVector clonedVector = ((AugmentableFeatureVector) (clonedMatrix));
    assertEquals(2, clonedVector.numLocations());
    assertEquals(originalVector.getAlphabet(), clonedVector.getAlphabet());
    assertEquals(originalVector.value(0), clonedVector.value(0), 1.0E-5);
    assertEquals(originalVector.value(1), clonedVector.value(1), 1.0E-5);
}

@Test
public void test3()
{
    Alphabets alphabets = new Alphabets();
    Alphabets.InstanceLexicon dictionary = alphabets.newInstanceLexicon();
    dictionary.lookupIndex("feature1", true);
    dictionary.lookupIndex("feature2", true);
    int[] indices = new int[]{ 0, 1 };
    double[] values = new double[]{ 1.5, 2.5 };
    int length = 2;
    AugmentableFeatureVector originalVector = new AugmentableFeatureVector(dictionary, indices, values, length, length, false, false, false);
    ConstantMatrix cloned = originalVector.cloneMatrixZeroed();
    assertTrue(cloned instanceof AugmentableFeatureVector);
    AugmentableFeatureVector clonedVector = ((AugmentableFeatureVector) (cloned));
    assertNotSame(originalVector, clonedVector);
    assertArrayEquals(new int[]{ 0, 1 }, clonedVector.getIndices());
    assertArrayEquals(new double[]{ 0.0, 0.0 }, clonedVector.getValues(), 1.0E-9);
    assertEquals(2, clonedVector.getValues().length);
}

@Test
public void test4()
{
    Alphabet alphabet = new Alphabet();
    int indexA = alphabet.lookupIndex("featureA");
    int indexB = alphabet.lookupIndex("featureB");
    int[] unsortedIndices = new int[]{ indexB, indexA };
    double[] values = new double[]{ 2.0, 1.0 };
    AugmentableFeatureVector afv = new AugmentableFeatureVector(alphabet, null, null, false, true);
    afv.indices = unsortedIndices;
    afv.values = values;
    afv.size = 2;
    afv.maxSortedIndex = 0;
    FeatureVector fv = afv.toFeatureVector();
    assertArrayEquals(new int[]{ Math.min(indexA, indexB), Math.max(indexA, indexB) }, fv.getIndices());
    assertEquals(2, fv.numLocations());
    assertEquals(1.0, fv.valueAtLocation(0), 1.0E-5);
    assertEquals(2.0, fv.valueAtLocation(1), 1.0E-5);
}

@Test
public void test5()
{
    Alphabet alphabet = new Alphabet();
    String feature1 = "word1";
    String feature2 = "word2";
    int featureIndex1 = alphabet.lookupIndex(feature1);
    int featureIndex2 = alphabet.lookupIndex(feature2);
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, new String[]{ feature1, feature2 }, new double[]{ 1.0, 2.0 });
    SparseVector sparse = vector.toSparseVector();
    assertEquals(2, sparse.numLocations());
    assertEquals(featureIndex1, sparse.indexAtLocation(0));
    assertEquals(1.0, sparse.valueAtLocation(0), 1.0E-5);
    assertEquals(featureIndex2, sparse.indexAtLocation(1));
    assertEquals(2.0, sparse.valueAtLocation(1), 1.0E-5);
}

@Test
public void test6()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, new int[]{ 0, 1, 2 }, new double[]{ 3.0, 4.0, 0.0 });
    try {
        Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.setInt(vector, 3);
        Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
        maxSortedIndexField.setAccessible(true);
        maxSortedIndexField.setInt(vector, 2);
        Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
        valuesField.setAccessible(true);
        valuesField.set(vector, new double[]{ 3.0, 4.0, 0.0 });
    } catch (Exception e) {
        fail("Reflection setup failed: " + e.getMessage());
    }
    double result = vector.twoNorm();
    assertEquals(5.0, result, 1.0E-9);
}

@Test
public void test7()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, null);
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.set(vector, 3);
    Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
    maxSortedIndexField.setAccessible(true);
    maxSortedIndexField.set(vector, 2);
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, new int[]{ 1, 3, 5 });
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, new double[]{ 0.5, 1.5, 2.5 });
    Field klassField = Class.forName("cc.mallet.types.AugmentableFeatureVector").getDeclaredField("indices");
    klassField.setAccessible(true);
    double result = vector.value(3);
    assertEquals(1.5, result, 1.0E-5);
}

@Test
public void test8()
{
    Alphabet alphabet = new Alphabet();
    int[] featureIndices = null;
    double[] values = new double[]{ 1.0, 2.0 };
    FeatureVector fv = new FeatureVector(alphabet, new String[]{ "a", "b" }, values);
    AugmentableFeatureVector afv = new AugmentableFeatureVector(alphabet);
    afv.add("a", 1.0);
    afv.add("b", 2.0);
    afv.indices = null;
    afv.size = 2;
    afv.maxSortedIndex = 1;
    int location = 1;
    int result = afv.indexAtLocation(location);
    assertEquals(location, result);
}

@Test
public void test9()
{
    AugmentableFeatureVector vector = new AugmentableFeatureVector(null, null, null, null);
    Field valuesField = AugmentableFeatureVector.class.getDeclaredField("values");
    valuesField.setAccessible(true);
    valuesField.set(vector, new double[]{ 1.0, 2.0, 3.0 });
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, null);
    int result = vector.singleSize();
    assertEquals(3, result);
}

@Test
public void test10()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("word1");
    dict.lookupIndex("word2");
    dict.lookupIndex("word3");
    int[] baseIndices = new int[]{ dict.lookupIndex("word1") };
    double[] baseValues = new double[]{ 1.0 };
    AugmentableFeatureVector baseVector = new AugmentableFeatureVector(dict, baseIndices, baseValues);
    int[] addIndices = new int[]{ dict.lookupIndex("word2"), dict.lookupIndex("word3") };
    double[] addValues = new double[]{ 1.0, 1.0 };
    FeatureVector fvToAdd = new FeatureVector(dict, addIndices, addValues);
    baseVector.add(fvToAdd);
    assertEquals(3, baseVector.numLocations());
    assertEquals(dict.lookupIndex("word1"), baseVector.indexAtLocation(0));
    assertEquals(dict.lookupIndex("word2"), baseVector.indexAtLocation(1));
    assertEquals(dict.lookupIndex("word3"), baseVector.indexAtLocation(2));
    assertEquals(1.0, baseVector.valueAtLocation(0), 1.0E-4);
    assertEquals(1.0, baseVector.valueAtLocation(1), 1.0E-4);
    assertEquals(1.0, baseVector.valueAtLocation(2), 1.0E-4);
}

@Test
public void test11()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    dict.lookupIndex("feature3");
    String[] initialFeatures = new String[]{ "feature1" };
    AugmentableFeatureVector target = new AugmentableFeatureVector(dict, initialFeatures, new double[]{ 1.0 });
    String[] addedFeatures = new String[]{ "feature2", "feature3" };
    double[] values = new double[]{ 1.0, 1.0 };
    FeatureVector source = new FeatureVector(dict, addedFeatures, values);
    target.add(source);
    int idx1 = dict.lookupIndex("feature1");
    int idx2 = dict.lookupIndex("feature2");
    int idx3 = dict.lookupIndex("feature3");
    assertEquals(3, target.numLocations());
    assertEquals(1.0, target.valueAtLocation(target.indexOf(idx1)), 1.0E-4);
    assertEquals(1.0, target.valueAtLocation(target.indexOf(idx2)), 1.0E-4);
    assertEquals(1.0, target.valueAtLocation(target.indexOf(idx3)), 1.0E-4);
}

@Test
public void test12()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    dict.lookupIndex("feature3");
    int[] addIndices = new int[]{ dict.lookupIndex("feature1"), dict.lookupIndex("feature2") };
    double[] addValues = new double[]{ 1.0, 2.0 };
    FeatureVector fvToAdd = new FeatureVector(dict, addIndices, addValues);
    int[] baseIndices = new int[]{ dict.lookupIndex("feature3") };
    double[] baseValues = new double[]{ 3.0 };
    AugmentableFeatureVector afv = new AugmentableFeatureVector(dict, baseIndices, baseValues);
    afv.add(fvToAdd);
    assertEquals(3, afv.numLocations());
    assertEquals(dict.lookupIndex("feature3"), afv.indexAtLocation(0));
    assertEquals(3.0, afv.valueAtLocation(0), 1.0E-5);
    assertEquals(dict.lookupIndex("feature1"), afv.indexAtLocation(1));
    assertEquals(1.0, afv.valueAtLocation(1), 1.0E-5);
    assertEquals(dict.lookupIndex("feature2"), afv.indexAtLocation(2));
    assertEquals(2.0, afv.valueAtLocation(2), 1.0E-5);
}

@Test
public void test13()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("a");
    dict.lookupIndex("b");
    dict.lookupIndex("c");
    dict.lookupIndex("d");
    int indexA = dict.lookupIndex("a");
    int indexB = dict.lookupIndex("b");
    int[] indices1 = new int[]{ indexA, indexB };
    double[] values1 = new double[]{ 1.0, 2.0 };
    AugmentableFeatureVector vector = new AugmentableFeatureVector(dict, indices1, values1);
    int indexC = dict.lookupIndex("c");
    int indexD = dict.lookupIndex("d");
    int[] indices2 = new int[]{ indexC, indexD };
    double[] values2 = new double[]{ 3.0, 4.0 };
    FeatureVector otherVector = new FeatureVector(dict, indices2, values2);
    vector.add(otherVector);
    assertEquals(4, vector.numLocations());
    assertEquals(indexA, vector.indexAtLocation(0));
    assertEquals(1.0, vector.valueAtLocation(0), 1.0E-4);
    assertEquals(indexB, vector.indexAtLocation(1));
    assertEquals(2.0, vector.valueAtLocation(1), 1.0E-4);
    assertEquals(indexC, vector.indexAtLocation(2));
    assertEquals(3.0, vector.valueAtLocation(2), 1.0E-4);
    assertEquals(indexD, vector.indexAtLocation(3));
    assertEquals(4.0, vector.valueAtLocation(3), 1.0E-4);
}

@Test
public void test14()
{
    Alphabet dict = new Alphabet();
    int appleIndex = dict.lookupIndex("apple");
    AugmentableFeatureVector baseVector = new AugmentableFeatureVector(dict);
    baseVector.add(appleIndex, 1.0);
    int bananaIndex = dict.lookupIndex("banana");
    int[] indices = new int[]{ bananaIndex };
    double[] values = new double[]{ 1.0 };
    FeatureVector otherVector = new FeatureVector(dict, indices, values);
    baseVector.add(otherVector);
    boolean hasApple = baseVector.location(appleIndex) != (-1);
    boolean hasBanana = baseVector.location(bananaIndex) != (-1);
    assertTrue("Base vector should contain 'apple'", hasApple);
    assertTrue("Base vector should contain 'banana' after add", hasBanana);
    assertEquals("Banana value should be 1.0", 1.0, baseVector.valueAtLocation(baseVector.location(bananaIndex)), 1.0E-5);
}

@Test
public void test15()
{
    AugmentableFeatureVector target = new AugmentableFeatureVector(null, null, false);
    target.size = 3;
    target.indices = new int[]{ 0, 2, 4 };
    target.values = new double[]{ 1.0, 2.0, 3.0 };
    target.maxSortedIndex = 2;
    AugmentableFeatureVector source = new AugmentableFeatureVector(null, null, false);
    source.size = 3;
    source.indices = new int[]{ 2, 4, 5 };
    source.values = new double[]{ 10.0, 20.0, 30.0 };
    source.maxSortedIndex = 2;
    double factor = 0.5;
    target.plusEquals(source, factor);
    assertEquals(1.0, target.values[0], 1.0E-4);
    assertEquals(2.0 + (10.0 * 0.5), target.values[1], 1.0E-4);
    assertEquals(3.0 + (20.0 * 0.5), target.values[2], 1.0E-4);
}

@Test
public void test16()
{
    AugmentableFeatureVector v1 = new AugmentableFeatureVector(null, new double[]{ 1.0, 2.0, 3.0 });
    AugmentableFeatureVector v2 = new AugmentableFeatureVector(null, new double[]{ 0.5, 1.5, 2.5 });
    v1.size = 3;
    v1.values = new double[]{ 1.0, 2.0, 3.0 };
    v1.indices = null;
    v1.maxSortedIndex = -1;
    v2.size = 3;
    v2.values = new double[]{ 0.5, 1.5, 2.5 };
    v2.indices = null;
    v2.maxSortedIndex = -1;
    v1.plusEquals(v2, 1.0);
    assertEquals(1.5, v1.values[0], 1.0E-4);
    assertEquals(3.5, v1.values[1], 1.0E-4);
    assertEquals(5.5, v1.values[2], 1.0E-4);
}

@Test
public void test17()
{
    Alphabet alphabet = new Alphabet();
    String feature1 = "feature1";
    String feature2 = "feature2";
    String feature3 = "feature3";
    int idx1 = alphabet.lookupIndex(feature1);
    int idx2 = alphabet.lookupIndex(feature2);
    int idx3 = alphabet.lookupIndex(feature3);
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, new String[]{ feature1, feature2, feature3 }, new double[]{ 1.5, 2.5, 3.5 });
    vector.setAll(7.0);
    double[] expected = new double[]{ 7.0, 7.0, 7.0 };
    double[] actual = vector.getValues();
    assertNotNull("Values array should not be null", actual);
    assertEquals("Values array should have length 3", 3, actual.length);
    assertEquals(7.0, actual[0], 1.0E-4);
    assertEquals(7.0, actual[1], 1.0E-4);
    assertEquals(7.0, actual[2], 1.0E-4);
}

@Test
public void test18()
{
    size = 3;
    values = new double[]{ 1.0, 2.0, 3.0 };
    indices = null;
}

@Test
public void test19()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1");
    alphabet.lookupIndex("feature2");
    double[] initialValues = new double[]{ 1.0, 2.0 };
    int[] initialIndices = new int[]{ 0, 1 };
    AugmentableFeatureVector featureVector = new AugmentableFeatureVector(alphabet, initialIndices.clone(), initialValues.clone());
    featureVector.setValueAtLocation(1, 5.5);
    assertEquals(1.0, featureVector.valueAtLocation(0), 1.0E-4);
    assertEquals(5.5, featureVector.valueAtLocation(1), 1.0E-4);
}

