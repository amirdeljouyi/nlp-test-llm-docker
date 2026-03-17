import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest {

@Test
public void test1()
{
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator();
    Set<Class<? extends CoreAnnotation>> expected = new HashSet<>(Arrays.asList(MentionAnnotation.class, MentionBeginAnnotation.class, MentionEndAnnotation.class, CanonicalMentionAnnotation.class, CanonicalMentionBeginAnnotation.class, CanonicalMentionEndAnnotation.class, MentionTypeAnnotation.class, MentionSieveAnnotation.class, SpeakerAnnotation.class, SpeakerSieveAnnotation.class, ParagraphIndexAnnotation.class));
    Set<Class<? extends CoreAnnotation>> actual = annotator.requirementsSatisfied();
    assertEquals(expected, actual);
}

@Test
public void test2()
{
    Annotation annotation = new Annotation("\"I will help you,\" said John.");
    CoreLabel speakerToken = new CoreLabel();
    speakerToken.setIndex(0);
    speakerToken.set(EntityMentionIndexAnnotation.class, 0);
    List<CoreLabel> tokens = new ArrayList<>();
    tokens.add(speakerToken);
    annotation.set(TokensAnnotation.class, tokens);
    CoreMap mention = new Annotation("John");
    mention.set(CanonicalEntityMentionIndexAnnotation.class, 1);
    CoreMap canonicalMention = new Annotation("John");
    canonicalMention.set(TextAnnotation.class, "John");
    CoreLabel firstToken = new CoreLabel();
    firstToken.set(TokenBeginAnnotation.class, 0);
    CoreLabel lastToken = new CoreLabel();
    lastToken.set(TokenBeginAnnotation.class, 0);
    List<CoreLabel> canonicalTokens = new ArrayList<>();
    canonicalTokens.add(firstToken);
    canonicalTokens.add(lastToken);
    canonicalMention.set(TokensAnnotation.class, canonicalTokens);
    List<CoreMap> mentions = new ArrayList<>();
    mentions.add(mention);
    mentions.add(canonicalMention);
    annotation.set(MentionsAnnotation.class, mentions);
    CoreMap quote = new Annotation("\"I will help you,\" said John.");
    quote.set(MentionBeginAnnotation.class, 0);
    List<CoreMap> quotes = new ArrayList<>();
    quotes.add(quote);
    annotation.set(QuotationsAnnotation.class, quotes);
    Properties props = new Properties();
    props.setProperty("quote.attribution.qm.sieves", "");
    props.setProperty("quote.attribution.ms.sieves", "");
    QuoteAttributionAnnotator annotator = new QuoteAttributionAnnotator(props);
    annotator.annotate(annotation);
    assertEquals("John", quote.get(CanonicalMentionAnnotation.class));
    assertEquals(Integer.valueOf(0), quote.get(CanonicalMentionBeginAnnotation.class));
    assertEquals(Integer.valueOf(0), quote.get(CanonicalMentionEndAnnotation.class));
}

