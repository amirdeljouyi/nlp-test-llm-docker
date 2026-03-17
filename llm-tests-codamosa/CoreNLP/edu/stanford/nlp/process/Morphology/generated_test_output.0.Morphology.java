import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    Object lexerProxy = Proxy.newProxyInstance(Morphology.class.getClassLoader(), new Class<?>[]{  }, new InvocationHandler() {
        private boolean called = false;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("next".equals(method.getName())) {
                if (!called) {
                    called = true;
                    return "running";
                }
                return null;
            }
            throw new UnsupportedOperationException("Unsupported method: " + method.getName());
        }
    });
    Morphology morphology = new Morphology();
    Field lexerField = Morphology.class.getDeclaredField("lexer");
    lexerField.setAccessible(true);
    lexerField.set(morphology, lexerProxy);
    Word result = morphology.next();
    assertNotNull(result);
    assertEquals("running", result.word());
}

@Test
public void test2()
{
    Morphology morphology = new Morphology();
    Word inputWord = new Word("running");
    Word stemmedWord = morphology.stem(inputWord);
    Assert.assertEquals("run", stemmedWord.word());
}

@Test
public void test3()
{
    String word = "Dogs";
    String tag = "NNS";
    boolean lowercase = true;
    Morpha lexer = new Morpha();
    lexer.setOption(1, lowercase);
    String wordtag = (word + "_") + tag;
    lexer.yyreset(new StringReader(wordtag));
    lexer.yybegin(scan);
    String lemma = lexer.next();
    lexer.next();
    assertEquals("dog", lemma);
}

@Test
public void test4()
{
    Morphology morphology = new Morphology();
    String word = "running";
    String tag = "VBG";
    String expectedLemma = "run";
    String actualLemma = morphology.lemmatize(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test5()
{
    Morphology morphology = new Morphology();
    String word = "dogs";
    String tag = "NNS";
    String expectedLemma = "dog";
    String actualLemma = morphology.lemmatize(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test6()
{
    Morphology morphology = new Morphology();
    Word input = new Word("dogs");
    Word result = morphology.stem(input);
    assertEquals("dog", result.word());
}

@Test
public void test7()
{
    WordTag input = new WordTag("played", "VBD");
    WordLemmaTag result = Morphology.lemmatizeStatic(input);
    assertEquals("played", result.word());
    assertEquals("play", result.lemma());
    assertEquals("VBD", result.tag());
}

@Test
public void test8()
{
    String word = "running";
    String tag = "VBG";
    WordTag result = Morphology.stemStatic(word, tag);
    assertEquals("run", result.word());
    assertEquals("VBG", result.tag());
}

@Test
public void test9()
{
    String word = "Running";
    String tag = "VBG";
    WordTag result = Morphology.stemStatic(word, tag);
    assertEquals("run", result.word());
    assertEquals("VBG", result.tag());
}

@Test
public void test10()
{
    String word = "dogs";
    String tag = "NNS";
    String expectedLemma = "dog";
    String actualLemma = Morphology.lemmaStatic(word, tag);
    assertEquals(expectedLemma, actualLemma);
}

@Test
public void test11()
{
    String word = "running";
    String tag = "VBG";
    String expected = "run";
    String actual = Morphology.lemmaStatic(word, tag);
    assertEquals(expected, actual);
}

@Test
public void test12()
{
    Morphology morphology = new Morphology();
    Word input = new Word("running");
    Word result = morphology.stem(input);
    assertEquals("run", result.word());
}

