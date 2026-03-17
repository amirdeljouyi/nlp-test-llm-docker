import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Pipe pipe = new SerialPipes(Arrays.asList(new CharSequence2TokenSequence("\\p{L}+"), new TokenSequenceLowercase(), new TokenSequence2FeatureVector()));
    InstanceList instances = new InstanceList(pipe);
    pipe.setTargetProcessing(true);
    pipe.setDataAlphabet(new LabelAlphabet());
    instances.addThruPipe(new Instance("apple orange banana", null, "doc1", null));
    instances.addThruPipe(new Instance("grape mango peach", null, "doc2", null));
    instances.addThruPipe(new Instance("car truck bike", null, "doc3", null));
    instances.addThruPipe(new Instance("airplane rocket satellite", null, "doc4", null));
    KMeans kMeans = new KMeans(pipe, 2);
    kMeans.setDistanceMetric(new DistanceMetric() {
        public double distance(SparseVector a, SparseVector b) {
            double sum = 0.0;
            for (int i = 0; i < a.numLocations(); i++) {
                int idx = a.indexAtLocation(i);
                double diff = a.valueAtLocation(i) - b.value(idx);
                sum += diff * diff;
            }
            return Math.sqrt(sum);
        }
    });
    kMeans.setNumClusters(2);
    kMeans.setEmptyAction(EMPTY_ERROR);
    Clustering clustering = kMeans.cluster(instances);
    Assert.assertNotNull(clustering);
    Assert.assertEquals(2, clustering.getNumClusters());
    int[] labels = clustering.getClusterLabels();
    Assert.assertEquals(4, labels.length);
    Assert.assertTrue((labels[0] == labels[1]) || (labels[2] == labels[3]));
    Assert.assertTrue((labels[0] != labels[2]) || (labels[1] != labels[3]));
}

@Test
public void test2()
{
    KMeans kMeans = new KMeans();
    Alphabet alphabet = new Alphabet();
    alphabet.lookupIndex("feature1", true);
    alphabet.lookupIndex("feature2", true);
    double[] values1 = new double[]{ 1.0, 0.0 };
    int[] indices1 = new int[]{ 0, 1 };
    double[] values2 = new double[]{ 0.5, 1.5 };
    int[] indices2 = new int[]{ 0, 1 };
    SparseVector vector1 = new SparseVector(indices1, values1, indices1.length, false);
    SparseVector vector2 = new SparseVector(indices2, values2, indices2.length, false);
    ArrayList<SparseVector> expectedMeans = new ArrayList<SparseVector>();
    expectedMeans.add(vector1);
    expectedMeans.add(vector2);
    try {
        Field field = KMeans.class.getDeclaredField("clusterMeans");
        field.setAccessible(true);
        field.set(kMeans, expectedMeans);
    } catch (Exception e) {
        fail("Failed to set clusterMeans field via reflection: " + e.getMessage());
    }
    ArrayList<SparseVector> actualMeans = kMeans.getClusterMeans();
    assertEquals(2, actualMeans.size());
    assertArrayEquals(vector1.getValues(), actualMeans.get(0).getValues(), 1.0E-5);
    assertArrayEquals(vector2.getValues(), actualMeans.get(1).getValues(), 1.0E-5);
}

