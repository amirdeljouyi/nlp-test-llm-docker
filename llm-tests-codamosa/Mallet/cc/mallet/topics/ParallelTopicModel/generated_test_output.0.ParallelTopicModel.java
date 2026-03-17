import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    ParallelTopicModel model = new ParallelTopicModel(5);
    model.alpha = 0.1;
    model.alphaSum = 0.5;
    model.beta = 0.01;
    model.numTopics = 5;
    model.tokensPerTopic = new int[]{ 100, 80, 90, 110, 95 };
    model.typeTopicCounts = new int[][]{ new int[]{ 1, 2, 3 }, new int[]{ 4, 5 }, new int[]{  }, new int[]{ 6 }, new int[]{ 7, 8, 9, 10 } };
    MarginalProbEstimator estimator = model.getProbEstimator();
    assertNotNull(estimator);
    assertTrue(estimator instanceof MarginalProbEstimator);
}

