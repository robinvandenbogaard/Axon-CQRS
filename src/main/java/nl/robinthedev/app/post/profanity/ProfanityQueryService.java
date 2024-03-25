package nl.robinthedev.app.post.profanity;

import static nl.robinthedev.app.post.axon.ProfanityEvaluation.*;

import nl.robinthedev.app.post.axon.ProfanityEvaluation;
import nl.robinthedev.app.post.axon.ProfanityService;
import org.springframework.stereotype.Service;

@Service
public class ProfanityQueryService implements ProfanityService {

  @Override
  public ProfanityEvaluation calculateScore(String comment) {
    //    try {
    //      var scoreRequest = profanityService.calculateScore(updateComment.newText()).get();
    //      if (scoreRequest.score() <80) {
    //        apply(new CommentUpdated(commentId, text, updateComment.newText()));
    //      } else {
    //        apply(new CommentRejected(commentId, updateComment.newText(), "Bad language!"));
    //      }
    //    } catch (InterruptedException | ExecutionException e) {
    //      //Can't check profanity, in good faith we accept.
    //      apply(new CommentUpdated(commentId, text, updateComment.newText()));
    //    }
    return new Acceptable();
  }
}
