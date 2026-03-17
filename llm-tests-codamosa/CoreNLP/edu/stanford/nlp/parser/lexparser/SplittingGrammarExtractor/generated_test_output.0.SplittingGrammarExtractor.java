import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    SplittingGrammarExtractor extractor = new SplittingGrammarExtractor();
    double[] result = extractor.neginfDoubles(3);
    assertNotNull(result);
    assertEquals(3, result.length);
    assertEquals(Double.NEGATIVE_INFINITY, result[0], 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, result[1], 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, result[2], 0.0);
}

@Test
public void test2()
{
    SplittingGrammarExtractor extractor = new SplittingGrammarExtractor();
    TwoDimensionalMap<String, String, double[][]> tempUnaryBetas = TwoDimensionalMap.treeMap();
    double[][] unaryArray = new double[][]{ new double[]{ 0.5, 0.3 }, new double[]{ 0.7, 0.2 } };
    tempUnaryBetas.put("A", "B", unaryArray);
    ThreeDimensionalMap<String, String, String, double[][][]> tempBinaryBetas = new ThreeDimensionalMap<>();
    double[][][] binaryArray = new double[][][]{ new double[][]{ new double[]{ 0.1, 0.2 }, new double[]{ 0.3, 0.4 } } };
    tempBinaryBetas.put("X", "Y", "Z", binaryArray);
    boolean testConverged = false;
    boolean result = extractor.useNewBetas(testConverged, tempUnaryBetas, tempBinaryBetas);
    assertFalse(result);
}

@Test
public void test1()
{
    SplittingGrammarExtractor extractor = new SplittingGrammarExtractor();
    extractor.iteration = SplittingGrammarExtractor.MIN_DEBUG_ITERATION;
    boolean result = extractor.DEBUG();
    assertTrue(result);
}

@Test
public void test2()
{
    SplittingGrammarExtractor extractor = new SplittingGrammarExtractor();
    double[] result = extractor.neginfDoubles(3);
    assertNotNull(result);
    assertEquals(3, result.length);
    assertEquals(Double.NEGATIVE_INFINITY, result[0], 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, result[1], 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, result[2], 0.0);
}

@Test
public void test3()
{
    SplittingGrammarExtractor extractor = new SplittingGrammarExtractor();
    Set<String> originalStates = new HashSet<>();
    originalStates.add("NP");
    try {
        Field field = SplittingGrammarExtractor.class.getDeclaredField("originalStates");
        field.setAccessible(true);
        field.set(extractor, originalStates);
    } catch (Exception e) {
        fail("Failed to set originalStates via reflection: " + e.getMessage());
    }
    SplittingGrammarExtractor testExtractor = new SplittingGrammarExtractor() {
        @Override
        protected int getStateSplitCount(String state) {
            if ("NP".equals(state)) {
                return 3;
            }
            return 0;
        }
    };
    try {
        Field field = SplittingGrammarExtractor.class.getDeclaredField("originalStates");
        field.setAccessible(true);
        field.set(testExtractor, originalStates);
    } catch (Exception e) {
        fail("Failed to set originalStates via reflection: " + e.getMessage());
    }
    List<Triple<String, Integer, Double>> deltas = new ArrayList<>();
    deltas.add(new Triple<>("NP", 0, 0.0));
    Map<String, int[]> result = testExtractor.buildMergeCorrespondence(deltas);
    assertTrue(result.containsKey("NP"));
    int[] correspondence = result.get("NP");
    assertArrayEquals(new int[]{ 0, 0, 1 }, correspondence);
}

@Test
public void test4()
{
    SplittingGrammarExtractor extractor = new SplittingGrammarExtractor();
    double epsilon = SplittingGrammarExtractor.EPSILON;
    TwoDimensionalMap<String, String, double[][]> unaryBetas = TwoDimensionalMap.treeMap();
    double[][] oldUnaryMatrix = new double[][]{ new double[]{ 1.0, 2.0 }, new double[]{ 3.0, 4.0 } };
    unaryBetas.put("NP", "DT", oldUnaryMatrix);
    extractor.unaryBetas = unaryBetas;
    TwoDimensionalMap<String, String, double[][]> tempUnaryBetas = TwoDimensionalMap.treeMap();
    double[][] newUnaryMatrix = new double[][]{ new double[]{ 1.0 + (epsilon / 2), 2.0 - (epsilon / 3) }, new double[]{ 3.0 + (epsilon / 4), 4.0 - (epsilon / 10) } };
    tempUnaryBetas.put("NP", "DT", newUnaryMatrix);
    ThreeDimensionalMap<String, String, String, double[][][]> binaryBetas = new ThreeDimensionalMap<>();
    double[][][] oldBinaryMatrix = new double[][][]{ new double[][]{ new double[]{ 5.0, 6.0 }, new double[]{ 7.0, 8.0 } }, new double[][]{ new double[]{ 9.0, 10.0 }, new double[]{ 11.0, 12.0 } } };
    binaryBetas.put("S", "NP", "VP", oldBinaryMatrix);
    extractor.binaryBetas = binaryBetas;
    ThreeDimensionalMap<String, String, String, double[][][]> tempBinaryBetas = new ThreeDimensionalMap<>();
    double[][][] newBinaryMatrix = new double[][][]{ new double[][]{ new double[]{ 5.0 + (epsilon / 2), 6.0 - (epsilon / 4) }, new double[]{ 7.0 + (epsilon / 3), 8.0 - (epsilon / 5) } }, new double[][]{ new double[]{ 9.0 + (epsilon / 6), 10.0 - (epsilon / 7) }, new double[]{ 11.0 + (epsilon / 8), 12.0 - (epsilon / 9) } } };
    tempBinaryBetas.put("S", "NP", "VP", newBinaryMatrix);
    boolean result = extractor.testConvergence(tempUnaryBetas, tempBinaryBetas);
    assertTrue(result);
}


