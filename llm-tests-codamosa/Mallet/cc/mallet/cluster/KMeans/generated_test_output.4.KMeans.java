import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Noop pipe = new Noop();
    Alphabet dataAlphabet = new Alphabet();
    InstanceList instances = new InstanceList(pipe);
    double[] features1 = new double[]{ 1.0, 0.0 };
    double[] features2 = new double[]{ 0.0, 1.0 };
    FeatureVector fv1 = new FeatureVector(dataAlphabet, new int[]{ dataAlphabet.lookupIndex("f1"), dataAlphabet.lookupIndex("f2") }, features1);
    FeatureVector fv2 = new FeatureVector(dataAlphabet, new int[]{ dataAlphabet.lookupIndex("f1"), dataAlphabet.lookupIndex("f2") }, features2);
    Instance inst1 = new Instance(fv1, null, "inst1", null);
    Instance inst2 = new Instance(fv2, null, "inst2", null);
    instances.add(inst1);
    instances.add(inst2);
    KMeans kmeans = new KMeans(pipe, 2, new EuclideanDistance());
    kmeans.setRandomSeed(42);
    Clustering result = kmeans.cluster(instances);
    assertNotNull(result);
    assertEquals(2, result.getNumClusters());
    int[] clusters = result.getClusterAssignments();
    assertEquals(2, clusters.length);
    assertTrue(clusters[0] != clusters[1]);
}

@Test
public void test2()
{
    KMeans kMeans = new KMeans();
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1");
    double[] values = new double[]{ 1.5 };
    int[] indices = new int[]{ 0 };
    SparseVector vector = new SparseVector(alphabet, indices, values);
    ArrayList<SparseVector> clusterList = new ArrayList<>();
    clusterList.add(vector);
    try {
        Field field = KMeans.class.getDeclaredField("clusterMeans");
        field.setAccessible(true);
        field.set(kMeans, clusterList);
    } catch (Exception e) {
        fail("Failed to set clusterMeans via reflection: " + e.getMessage());
    }
    ArrayList<SparseVector> result = kMeans.getClusterMeans();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(clusterList.get(0), result.get(0));
}

