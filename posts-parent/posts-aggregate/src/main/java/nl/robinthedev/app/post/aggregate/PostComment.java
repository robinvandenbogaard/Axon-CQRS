package nl.robinthedev.app.post.aggregate;

import static nl.robinthedev.app.post.aggregate.ProfanityEvaluation.*;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import nl.robinthedev.app.api.messaging.post.command.UpdateComment;
import nl.robinthedev.app.api.messaging.post.event.CommentRejected;
import nl.robinthedev.app.api.messaging.post.event.CommentUpdated;
import nl.robinthedev.app.api.messaging.post.model.Comment;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

class PostComment {

  @EntityId private final CommentId commentId;

  private String text;

  public PostComment(Comment comment) {
    this.commentId = comment.publicId();
    this.text = comment.text();
  }

  @CommandHandler
  void handle(UpdateComment updateComment, ProfanityService profanityService) {
    var profanityEvaluation = profanityService.calculateScore(updateComment.newText());

    var event =
        switch (profanityEvaluation) {
          case Acceptable ignored -> new CommentUpdated(commentId, text, updateComment.newText());
          case FailureToEvaluate ignored -> new CommentUpdated(
              commentId, text, updateComment.newText());
          case Unacceptable ignored -> new CommentRejected(
              commentId, updateComment.newText(), "Bad language!");
        };

    apply(event);
  }

  @EventSourcingHandler
  void handle(CommentUpdated commentUpdated) {
    this.text = commentUpdated.newText();
  }

  public CommentId getCommentId() {
    return commentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PostComment that = (PostComment) o;

    if (!commentId.equals(that.commentId)) return false;
    return text.equals(that.text);
  }

  @Override
  public int hashCode() {
    int result = commentId.hashCode();
    result = 31 * result + text.hashCode();
    return result;
  }
}
