import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Alphabet dict = new Alphabet();
    Noop pipe = new Noop();
    double[] values1 = new double[]{ 1.0, 0.0 };
    int[] indices1 = new int[]{ dict.lookupIndex("feature1"), dict.lookupIndex("feature2") };
    FeatureVector fv1 = new FeatureVector(dict, indices1, values1);
    Instance inst1 = new Instance(fv1, null, "inst1", null);
    double[] values2 = new double[]{ 0.0, 1.0 };
    int[] indices2 = new int[]{ dict.lookupIndex("feature1"), dict.lookupIndex("feature2") };
    FeatureVector fv2 = new FeatureVector(dict, indices2, values2);
    Instance inst2 = new Instance(fv2, null, "inst2", null);
    InstanceList data = new InstanceList(pipe);
    data.add(inst1);
    data.add(inst2);
    KMeans kMeans = new KMeans(pipe, 2, new MaxCosineDistance());
    kMeans.setRandomSeed(42);
    Clustering result = kMeans.cluster(data);
    assertNotNull(result);
    assertEquals(2, result.getNumClusters());
    assertEquals(2, result.getClusterLabels().length);
    assertNotEquals(result.getClusterLabels()[0], result.getClusterLabels()[1]);
}

@Test
public void test2()
{
    KMeans kMeans = new KMeans();
    ArrayList<SparseVector> expectedMeans = new ArrayList<>();
    expectedMeans.add(new FeatureVector(new int[]{ 0, 1 }, new double[]{ 1.0, 2.0 }));
    expectedMeans.add(new FeatureVector(new int[]{ 2, 3 }, new double[]{ 3.0, 4.0 }));
    try {
        Field field = KMeans.class.getDeclaredField("clusterMeans");
        field.setAccessible(true);
        field.set(kMeans, expectedMeans);
    } catch (Exception e) {
        fail("Reflection failed: " + e.getMessage());
    }
    ArrayList<SparseVector> actualMeans = kMeans.getClusterMeans();
    assertEquals("Cluster mean count mismatch", expectedMeans.size(), actualMeans.size());
    assertEquals("First cluster mean mismatch", expectedMeans.get(0), actualMeans.get(0));
    assertEquals("Second cluster mean mismatch", expectedMeans.get(1), actualMeans.get(1));
}

