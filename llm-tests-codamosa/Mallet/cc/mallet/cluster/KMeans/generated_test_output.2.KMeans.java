import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Noop pipe = new Noop();
    Alphabet dataAlphabet = new Alphabet();
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    double[] values1 = new double[]{ 1.0, 0.0 };
    double[] values2 = new double[]{ 0.0, 1.0 };
    int[] indices = new int[]{ 0, 1 };
    SparseVector vector1 = new SparseVector(indices, values1);
    SparseVector vector2 = new SparseVector(indices, values2);
    InstanceList instances = new InstanceList(pipe);
    instances.addThruPipe(new Instance(vector1, null, "inst1", null));
    instances.addThruPipe(new Instance(vector2, null, "inst2", null));
    KMeans kMeans = new KMeans(pipe, 2);
    kMeans.setMetric(new EuclideanDistance());
    Clustering result = kMeans.cluster(instances);
    assertNotNull(result);
    assertEquals(2, result.getNumClusters());
    assertEquals(2, result.getClusterLabels().length);
}

@Test
public void test2()
{
    KMeans kMeans = new KMeans();
    SparseVector vector1 = new SparseVector(new Alphabet(), new int[]{ 0 }, new double[]{ 1.0 });
    SparseVector vector2 = new SparseVector(new Alphabet(), new int[]{ 1 }, new double[]{ 2.0 });
    ArrayList<SparseVector> dummyMeans = new ArrayList<>(Arrays.asList(vector1, vector2));
    try {
        Field field = KMeans.class.getDeclaredField("clusterMeans");
        field.setAccessible(true);
        field.set(kMeans, dummyMeans);
    } catch (Exception e) {
        fail("Reflection failed to set clusterMeans: " + e.getMessage());
    }
    ArrayList<SparseVector> result = kMeans.getClusterMeans();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertSame(vector1, result.get(0));
    assertSame(vector2, result.get(1));
}


