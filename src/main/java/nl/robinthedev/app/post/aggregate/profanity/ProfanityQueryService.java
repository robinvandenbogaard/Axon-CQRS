package nl.robinthedev.app.post.aggregate.profanity;

import static nl.robinthedev.app.post.aggregate.ProfanityEvaluation.*;

import java.util.concurrent.ExecutionException;
import nl.robinthedev.app.api.messaging.profanity.query.CalculateProfanityScoreQuery;
import nl.robinthedev.app.api.messaging.profanity.query.ProfanityScore;
import nl.robinthedev.app.post.aggregate.ProfanityEvaluation;
import nl.robinthedev.app.post.aggregate.ProfanityService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
class ProfanityQueryService implements ProfanityService {

  private final QueryGateway queryGateway;

  public ProfanityQueryService(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  @Override
  public ProfanityEvaluation calculateScore(String comment) {
    try {
      var profanityScore =
          queryGateway
              .query(
                  new CalculateProfanityScoreQuery(comment),
                  ResponseTypes.instanceOf(ProfanityScore.class))
              .get();
      if (profanityScore.score() < 80) {
        return new Acceptable();
      } else {
        return new Unacceptable();
      }
    } catch (InterruptedException | ExecutionException e) {
      return new FailureToEvaluate();
    }
  }
}
