import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Alphabet dataAlphabet = new Alphabet();
    LabelAlphabet labelAlphabet = new LabelAlphabet();
    InstanceList instances = new InstanceList(new Noop(dataAlphabet, labelAlphabet));
    double[] vector1 = new double[]{ 1.0, 2.0 };
    double[] vector2 = new double[]{ 1.1, 2.1 };
    instances.addThruPipe(new Instance(new SparseVector(vector1), null, "inst1", null));
    instances.addThruPipe(new Instance(new SparseVector(vector2), null, "inst2", null));
    KMeans kMeans = new KMeans(new Noop(dataAlphabet, labelAlphabet), new Metric() {
        @Override
        public double distance(Object a, Object b) {
            SparseVector v1 = ((SparseVector) (a));
            SparseVector v2 = ((SparseVector) (b));
            double sum = 0.0;
            for (int i = 0; i < v1.numLocations(); i++) {
                double diff = v1.valueAtLocation(i) - v2.valueAtLocation(i);
                sum += diff * diff;
            }
            return Math.sqrt(sum);
        }
    });
    kMeans.setNumClusters(2);
    kMeans.setEmptyAction(EMPTY_ERROR);
    Clustering result = kMeans.cluster(instances);
    assertNotNull(result);
    assertEquals(2, result.getNumClusters());
    int[] labels = result.getClusterAssignments();
    assertEquals(2, labels.length);
    assertNotEquals(labels[0], labels[1]);
}

@Test
public void test2()
{
    KMeans kMeans = new KMeans();
    Alphabet alphabet = new Alphabet();
    SparseVector vector1 = new SparseVector(new int[]{ 0, 1 }, new double[]{ 1.0, 2.0 }, 2);
    SparseVector vector2 = new SparseVector(new int[]{ 1, 2 }, new double[]{ 3.0, 4.0 }, 2);
    ArrayList<SparseVector> expectedMeans = new ArrayList<>();
    expectedMeans.add(vector1);
    expectedMeans.add(vector2);
    try {
        Field field = KMeans.class.getDeclaredField("clusterMeans");
        field.setAccessible(true);
        field.set(kMeans, expectedMeans);
    } catch (Exception e) {
        fail("Failed to set clusterMeans via reflection: " + e.getMessage());
    }
    ArrayList<SparseVector> actualMeans = kMeans.getClusterMeans();
    assertEquals("Cluster means list size mismatch", expectedMeans.size(), actualMeans.size());
    assertSame("First cluster mean mismatch", expectedMeans.get(0), actualMeans.get(0));
    assertSame("Second cluster mean mismatch", expectedMeans.get(1), actualMeans.get(1));
}

