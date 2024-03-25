package nl.robinthedev.app.profanity;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import nl.robinthedev.app.api.messaging.query.CalculateProfanityScoreQuery;
import nl.robinthedev.app.api.messaging.query.ProfanityScore;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class CalculateProfanityScoreQueryHandler {

  @QueryHandler
  public CompletableFuture<ProfanityScore> handle(CalculateProfanityScoreQuery query) {
    int score = rateComment();
    ProfanityScore profanityScore = new ProfanityScore(score);
    return CompletableFuture.completedFuture(profanityScore);
  }

  private int rateComment() {
    var randomValue = new Random().nextInt(100) + 1;

    // Determine if the random value meets the condition for being allowed
    int score = randomValue;
    if (randomValue >= 80 && new Random().nextInt(100) < 5) { // 5% chance
      score = new Random().nextInt(80) + 1; // generate a random score less than 80
    }
    return score;
  }
}
