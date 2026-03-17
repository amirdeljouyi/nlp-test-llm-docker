import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    GenderAnnotator annotator = new GenderAnnotator();
    Set<Class<? extends CoreAnnotation>> result = annotator.requirementsSatisfied();
    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.contains(GenderAnnotation.class));
}

@Test
public void test2()
{
    GenderAnnotator annotator = new GenderAnnotator();
    annotator.maleNames.add("john");
    CoreLabel token = new CoreLabel();
    token.setWord("John");
    CoreMap mention = new Annotation("John Doe");
    mention.set(EntityTypeAnnotation.class, "PERSON");
    mention.set(TokensAnnotation.class, Collections.singletonList(token));
    CoreMap sentence = new Annotation("John Doe");
    sentence.set(MentionsAnnotation.class, Collections.singletonList(mention));
    Annotation annotation = new Annotation("John Doe");
    annotation.set(SentencesAnnotation.class, Collections.singletonList(sentence));
    annotator.annotate(annotation);
    assertEquals("MALE", mention.get(GenderAnnotation.class));
}

@Test
public void test3()
{
    CoreLabel token1 = new CoreLabel();
    CoreLabel token2 = new CoreLabel();
    List<CoreLabel> tokens = Arrays.asList(token1, token2);
    CoreMap entityMention = new ArrayCoreMap();
    entityMention.set(TokensAnnotation.class, tokens);
    GenderAnnotator annotator = new GenderAnnotator();
    annotator.annotateEntityMention(entityMention, "FEMALE");
    assertEquals("FEMALE", entityMention.get(GenderAnnotation.class));
    assertEquals("FEMALE", token1.get(GenderAnnotation.class));
    assertEquals("FEMALE", token2.get(GenderAnnotation.class));
}

@Test
public void test4()
{
    File tempFile = File.createTempFile("test_gender_names", ".txt");
    tempFile.deleteOnExit();
    FileWriter writer = new FileWriter(tempFile);
    writer.write("Alice,Bob\n");
    writer.write("Charlie,Diana\n");
    writer.close();
    HashSet<String> genderSet = new HashSet<>();
    GenderAnnotator annotator = new GenderAnnotator();
    annotator.loadGenderNames(genderSet, tempFile.getAbsolutePath());
    assertTrue(genderSet.contains("alice"));
    assertTrue(genderSet.contains("bob"));
    assertTrue(genderSet.contains("charlie"));
    assertTrue(genderSet.contains("diana"));
}

