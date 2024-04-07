package nl.robinthedev.app.profanity;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import nl.robinthedev.app.api.messaging.profanity.query.CalculateProfanityScoreQuery;
import nl.robinthedev.app.api.messaging.profanity.query.ProfanityScore;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CalculateProfanityScoreQueryHandler {

  private static final Logger log =
      LoggerFactory.getLogger(CalculateProfanityScoreQueryHandler.class);
  private static final Random RANDOM = new Random();

  @QueryHandler
  public CompletableFuture<ProfanityScore> handle(CalculateProfanityScoreQuery query) {
    int score = rateComment();
    log.info("Evaluated score for comment at {}.", score);
    ProfanityScore profanityScore = new ProfanityScore(score);
    return CompletableFuture.completedFuture(profanityScore);
  }

  private int rateComment() {
    var randomValue = RANDOM.nextInt(100) + 1;

    // Determine if the random value meets the condition for being allowed
    int score = randomValue;
    if (randomValue >= 80 && RANDOM.nextInt(100) < 5) { // 5% chance
      score = RANDOM.nextInt(80) + 1; // generate a random score less than 80
    }
    return score;
  }
}
