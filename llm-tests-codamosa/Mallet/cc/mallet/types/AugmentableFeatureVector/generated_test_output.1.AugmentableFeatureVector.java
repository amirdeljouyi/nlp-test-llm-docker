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
    valuesField.set(afv, new double[]{ 0.5, 1.5, 2.0, 0.7, 1.1, 0.2, 4.0 });
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(afv, 7);
    Field maxSortedIndexField = AugmentableFeatureVector.class.getDeclaredField("maxSortedIndex");
    maxSortedIndexField.setAccessible(true);
    maxSortedIndexField.setInt(afv, 6);
    afv.removeDuplicates(0);
    assertEquals(4, getPrivateIntField(afv, "size"));
    assertArrayEquals(new int[]{ 1, 2, 3, 4 }, ((int[]) (getPrivateField(afv, "indices"))));
    assertArrayEquals(new double[]{ 2.0, 2.0, 2.0, 4.0 }, ((double[]) (getPrivateField(afv, "values"))), 1.0E-5);
    assertEquals(3, getPrivateIntField(afv, "maxSortedIndex"));
}

@Test
public void test2()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1", true);
    alphabet.lookupIndex("feature2", true);
    int[] indices = new int[]{ alphabet.lookupIndex("feature1"), alphabet.lookupIndex("feature2") };
    double[] values = new double[]{ 1.0, 2.0 };
    int length = indices.length;
    int size = 2;
    AugmentableFeatureVector originalVector = new AugmentableFeatureVector(alphabet, indices, values, length, size, true, false, false);
    ConstantMatrix clonedMatrix = originalVector.cloneMatrix();
    Assert.assertNotNull(clonedMatrix);
    Assert.assertEquals(1, clonedMatrix.getNumRows());
    Assert.assertEquals(2, clonedMatrix.getNumColumns());
    Assert.assertEquals(1.0, clonedMatrix.value(0, 0), 1.0E-5);
    Assert.assertEquals(2.0, clonedMatrix.value(0, 1), 1.0E-5);
}

@Test
public void test3()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1", true);
    dict.lookupIndex("feature2", true);
    dict.lookupIndex("feature3", true);
    int[] indices = new int[]{ 0, 2 };
    double[] values = new double[]{ 1.5, 3.0 };
    AugmentableFeatureVector vector = new AugmentableFeatureVector(dict, indices, values, 2, 2, false, false, false);
    ConstantMatrix cloned = vector.cloneMatrixZeroed();
    assertTrue(cloned instanceof AugmentableFeatureVector);
    AugmentableFeatureVector clonedVector = ((AugmentableFeatureVector) (cloned));
    assertEquals(2, clonedVector.numLocations());
    assertEquals(0.0, clonedVector.value(0), 1.0E-6);
    assertEquals(0.0, clonedVector.value(1), 1.0E-6);
    assertArrayEquals(indices, clonedVector.getIndices());
}

@Test
public void test4()
{
    Alphabet alphabet = new Alphabet();
    int featureIndex1 = alphabet.lookupIndex("featureA");
    int featureIndex2 = alphabet.lookupIndex("featureB");
    AugmentableFeatureVector afv = new AugmentableFeatureVector();
    afv.dictionary = alphabet;
    afv.indices = new int[]{ featureIndex2, featureIndex1 };
    afv.values = new double[]{ 2.0, 1.0 };
    afv.size = 2;
    afv.maxSortedIndex = 0;
    FeatureVector fv = afv.toFeatureVector();
    assertArrayEquals(new int[]{ featureIndex1, featureIndex2 }, fv.getIndices());
    assertArrayEquals(new double[]{ 1.0, 2.0 }, fv.getValues(), 1.0E-4);
    assertEquals(2, fv.numLocations());
}

@Test
public void test5()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1");
    alphabet.lookupIndex("feature2");
    alphabet.lookupIndex("feature3");
    AugmentableFeatureVector afv = new AugmentableFeatureVector(alphabet);
    afv.add("feature1", 1.0);
    afv.add("feature2", 2.0);
    afv.add("feature3", 3.0);
    SparseVector sparseVector = afv.toSparseVector();
    assertEquals(3, sparseVector.numLocations());
    assertEquals(1.0, sparseVector.value(0), 1.0E-4);
    assertEquals(2.0, sparseVector.value(1), 1.0E-4);
    assertEquals(3.0, sparseVector.value(2), 1.0E-4);
    assertTrue(sparseVector.isSorted());
}

@Test
public void test6()
{
    AugmentableFeatureVector afv = new AugmentableFeatureVector();
    afv.size = 2;
    afv.maxSortedIndex = 1;
    afv.indices = new int[]{ 2, 0 };
    afv.values = new double[]{ 1.5, -2.0 };
    DenseVector dv = new DenseVector(new double[]{ 3.0, 0.0, 4.0 });
    double result = afv.dotProduct(dv);
    assertEquals(0.0, result, 1.0E-6);
}

@Test
public void test7()
{
    Alphabet alphabet = new Alphabet();
    String feature1 = "feature1";
    String feature2 = "feature2";
    int index1 = alphabet.lookupIndex(feature1);
    int index2 = alphabet.lookupIndex(feature2);
    double[] values = new double[]{ 2.0, 3.0 };
    int[] indices = new int[]{ index1, index2 };
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, new int[0], new double[0], true);
    vector.size = 2;
    vector.values = values;
    vector.indices = indices;
    vector.maxSortedIndex = 1;
    DenseVector denseVector = new DenseVector(new double[]{ 1.0, 4.0 });
    double result = vector.dotProduct(denseVector);
    assertEquals(14.0, result, 1.0E-5);
}

@Test
public void test8()
{
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1");
    alphabet.lookupIndex("feature2");
    AugmentableFeatureVector vector = new AugmentableFeatureVector(alphabet, new String[]{ "feature1", "feature2" }, new double[]{ 3.0, 4.0 });
    double result = vector.twoNorm();
    assertEquals(5.0, result, 1.0E-4);
}

@Test
public void test9()
{
    FeatureAlphabet dict = new FeatureAlphabet();
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    String[] features = new String[]{ "feature1", "feature2", "feature3" };
    double[] values = new double[]{ 1.0, 2.0, 3.0 };
    AugmentableFeatureVector vector = new AugmentableFeatureVector(dict, features, values);
    try {
        Field indicesField = vector.getClass().getSuperclass().getDeclaredField("indices");
        indicesField.setAccessible(true);
        indicesField.set(vector, null);
    } catch (Exception e) {
        throw new RuntimeException("Failed to set indices to null", e);
    }
    int location = 1;
    int result = vector.indexAtLocation(location);
    assertEquals("Expected indexAtLocation to return location when indices is null", location, result);
}

@Test
public void test10()
{
    AugmentableFeatureVector vector = ((AugmentableFeatureVector) (Class.forName("cc.mallet.types.AugmentableFeatureVector").getDeclaredConstructor().newInstance()));
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, null);
    Field sizeField = AugmentableFeatureVector.class.getDeclaredField("size");
    sizeField.setAccessible(true);
    sizeField.setInt(vector, 5);
    int result = vector.numLocations();
    assertEquals(5, result);
}

@Test
public void test11()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    dict.lookupIndex("feature3");
    AugmentableFeatureVector target = new AugmentableFeatureVector(dict, new String[]{ "feature1" }, new double[]{ 2.0 });
    FeatureVector source = new FeatureVector(dict, new String[]{ "feature2", "feature3" }, new double[]{ 1.0, 3.5 });
    assertEquals(1, target.numLocations());
    assertEquals(dict.lookupIndex("feature1"), target.indexAtLocation(0));
    assertEquals(2.0, target.valueAtLocation(0), 1.0E-4);
    target.add(source);
    assertEquals(3, target.numLocations());
    int idx1 = target.location(dict.lookupIndex("feature1"));
    int idx2 = target.location(dict.lookupIndex("feature2"));
    int idx3 = target.location(dict.lookupIndex("feature3"));
    assertTrue(idx1 >= 0);
    assertTrue(idx2 >= 0);
    assertTrue(idx3 >= 0);
    assertEquals(2.0, target.valueAtLocation(idx1), 1.0E-4);
    assertEquals(1.0, target.valueAtLocation(idx2), 1.0E-4);
    assertEquals(3.5, target.valueAtLocation(idx3), 1.0E-4);
}

@Test
public void test12()
{
    Alphabet alphabet = new Alphabet();
    String[] features = new String[]{ "foo", "bar" };
    double[] values = new double[]{ 1.0, 2.0 };
    FeatureVector sourceVector = new FeatureVector(alphabet, features, values);
    AugmentableFeatureVector targetVector = new AugmentableFeatureVector(alphabet);
    targetVector.add(sourceVector);
    int indexFoo = alphabet.lookupIndex("foo");
    int indexBar = alphabet.lookupIndex("bar");
    int locFoo = targetVector.location(indexFoo);
    int locBar = targetVector.location(indexBar);
    assertTrue("Expected index for 'foo' to exist", locFoo >= 0);
    assertTrue("Expected index for 'bar' to exist", locBar >= 0);
    assertEquals(1.0, targetVector.valueAtLocation(locFoo), 1.0E-4);
    assertEquals(2.0, targetVector.valueAtLocation(locBar), 1.0E-4);
}

@Test
public void test13()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    dict.lookupIndex("feature3");
    int[] indicesBase = new int[]{ dict.lookupIndex("feature1") };
    double[] valuesBase = new double[]{ 1.0 };
    AugmentableFeatureVector baseVector = new AugmentableFeatureVector(dict, indicesBase, valuesBase);
    int[] indicesSource = new int[]{ dict.lookupIndex("feature2"), dict.lookupIndex("feature3") };
    double[] valuesSource = new double[]{ 1.0, 0.5 };
    FeatureVector sourceVector = new FeatureVector(dict, indicesSource, valuesSource);
    baseVector.add(sourceVector);
    assertEquals(3, baseVector.numLocations());
    assertTrue(baseVector.contains(dict.lookupIndex("feature1")));
    assertTrue(baseVector.contains(dict.lookupIndex("feature2")));
    assertTrue(baseVector.contains(dict.lookupIndex("feature3")));
    assertEquals(1.0, baseVector.value(dict.lookupIndex("feature1")), 1.0E-4);
    assertEquals(1.0, baseVector.value(dict.lookupIndex("feature2")), 1.0E-4);
    assertEquals(0.5, baseVector.value(dict.lookupIndex("feature3")), 1.0E-4);
}

@Test
public void test14()
{
    Alphabet dict = new Alphabet();
    dict.lookupIndex("feature1");
    dict.lookupIndex("feature2");
    dict.lookupIndex("feature3");
    int[] indices1 = new int[]{ dict.lookupIndex("feature1") };
    double[] values1 = new double[]{ 1.0 };
    AugmentableFeatureVector augVec = new AugmentableFeatureVector(dict, indices1, values1);
    int[] indices2 = new int[]{ dict.lookupIndex("feature1"), dict.lookupIndex("feature2") };
    double[] values2 = new double[]{ 1.0, 2.0 };
    FeatureVector fv = new FeatureVector(dict, indices2, values2);
    augVec.add(fv);
    assertEquals(2, augVec.numLocations());
    int indexFeature1 = dict.lookupIndex("feature1");
    int indexFeature2 = dict.lookupIndex("feature2");
    boolean foundFeature1 = false;
    boolean foundFeature2 = false;
    for (int i = 0; i < augVec.numLocations(); i++) {
        int idx = augVec.indexAtLocation(i);
        double val = augVec.valueAtLocation(i);
        if (idx == indexFeature1) {
            foundFeature1 = true;
            assertEquals(1.0, val, 1.0E-5);
        }
        if (idx == indexFeature2) {
            foundFeature2 = true;
            assertEquals(2.0, val, 1.0E-5);
        }
    }
    assertTrue(foundFeature1);
    assertTrue(foundFeature2);
}

@Test
public void test15()
{
    Alphabet dict = new Alphabet();
    String feature1 = "feature1";
    String feature2 = "feature2";
    int feature1Index = dict.lookupIndex(feature1);
    double[] values = new double[]{ 1.0 };
    int[] indices = new int[]{ feature1Index };
    FeatureVector sourceVector = new FeatureVector(dict, indices, values);
    int feature2Index = dict.lookupIndex(feature2);
    AugmentableFeatureVector targetVector = new AugmentableFeatureVector(dict, new String[]{ feature2 }, new double[]{ 2.0 });
    targetVector.add(sourceVector);
    assertEquals(2, targetVector.numLocations());
    int loc0Index = targetVector.indexAtLocation(0);
    int loc1Index = targetVector.indexAtLocation(1);
    double loc0Value = targetVector.valueAtLocation(0);
    double loc1Value = targetVector.valueAtLocation(1);
    boolean hasFeature1 = ((loc0Index == feature1Index) && (loc0Value == 1.0)) || ((loc1Index == feature1Index) && (loc1Value == 1.0));
    boolean hasFeature2 = ((loc0Index == feature2Index) && (loc0Value == 2.0)) || ((loc1Index == feature2Index) && (loc1Value == 2.0));
    assertTrue(hasFeature1 && hasFeature2);
}

@Test
public void test16()
{
    AugmentableFeatureVector target = new AugmentableFeatureVector(null, 5, true);
    target.values = new double[]{ 1.0, 2.0, 3.0, 0.0, 0.0 };
    target.indices = new int[]{ 0, 1, 2, 3, 4 };
    target.size = 5;
    target.maxSortedIndex = 4;
    AugmentableFeatureVector source = new AugmentableFeatureVector(null, 3, true);
    source.values = new double[]{ 10.0, 20.0, 30.0 };
    source.indices = new int[]{ 1, 2, 4 };
    source.size = 3;
    source.maxSortedIndex = 2;
    target.plusEquals(source, 0.5);
    assertEquals(1.0, target.values[0], 1.0E-4);
    assertEquals(2.0 + 10.0, target.values[1], 1.0E-4);
    assertEquals(3.0 + (20.0 * 0.5), target.values[2], 1.0E-4);
    assertEquals(0.0, target.values[3], 1.0E-4);
    assertEquals(0.0 + (30.0 * 0.5), target.values[4], 1.0E-4);
}

@Test
public void test17()
{
    AugmentableFeatureVector target = new AugmentableFeatureVector();
    target.values = new double[]{ 1.0, 2.0, 3.0 };
    target.indices = null;
    target.size = 3;
    target.maxSortedIndex = 2;
    AugmentableFeatureVector v = new AugmentableFeatureVector();
    v.values = new double[]{ 0.5, 1.5, -2.0 };
    v.indices = null;
    v.size = 3;
    v.maxSortedIndex = 2;
    target.plusEquals(v, 1.0);
    assertEquals(1.5, target.values[0], 1.0E-4);
    assertEquals(3.5, target.values[1], 1.0E-4);
    assertEquals(1.0, target.values[2], 1.0E-4);
}

@Test
public void test18()
{
    Alphabets.Alphabet dictionary = new Alphabets.Alphabet();
    int featureIndex1 = dictionary.lookupIndex("feature1");
    int featureIndex2 = dictionary.lookupIndex("feature2");
    int featureIndex3 = dictionary.lookupIndex("feature3");
    AugmentableFeatureVector afv = new AugmentableFeatureVector(dictionary, new int[]{ featureIndex1, featureIndex2, featureIndex3 }, new double[]{ 1.0, 2.0, 3.0 });
    double newValue = 7.5;
    afv.setAll(newValue);
    double[] valuesField;
    try {
        Field valuesFieldRef = AugmentableFeatureVector.class.getDeclaredField("values");
        valuesFieldRef.setAccessible(true);
        valuesField = ((double[]) (valuesFieldRef.get(afv)));
    } catch (Exception e) {
        throw new AssertionError("Failed to access or retrieve 'values' field via reflection.", e);
    }
    assertEquals(3, valuesField.length);
    assertEquals(newValue, valuesField[0], 1.0E-5);
    assertEquals(newValue, valuesField[1], 1.0E-5);
    assertEquals(newValue, valuesField[2], 1.0E-5);
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
    double[] values = new double[]{ 0.1, 0.2, 0.3 };
    valuesField.set(vector, values);
    Field indicesField = AugmentableFeatureVector.class.getDeclaredField("indices");
    indicesField.setAccessible(true);
    indicesField.set(vector, null);
    vector.setValue(1, 9.9);
    assertEquals(0.1, values[0], 1.0E-4);
    assertEquals(9.9, values[1], 1.0E-4);
    assertEquals(0.3, values[2], 1.0E-4);
}

@Test
public void test20()
{
    ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
    pipeList.add(new CharSequence2TokenSequence("\\S+"));
    pipeList.add(new TokenSequenceLowercase());
    pipeList.add(new TokenSequence2FeatureVector());
    SerialPipes pipes = new SerialPipes(pipeList);
    InstanceList instances = new InstanceList(pipes);
    instances.addThruPipe(new Instance("apple banana cherry", null, null, null));
    AugmentableFeatureVector vector = ((AugmentableFeatureVector) (instances.get(0).getData()));
    int location = 1;
    assertTrue("Precondition failed: vector size too small", location < vector.numLocations());
    double newValue = 5.5;
    vector.setValueAtLocation(location, newValue);
    assertEquals(newValue, vector.valueAtLocation(location), 1.0E-4);
}

