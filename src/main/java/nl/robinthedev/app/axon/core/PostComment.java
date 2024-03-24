package nl.robinthedev.app.axon.core;

import nl.robinthedev.app.api.messaging.command.UpdateComment;
import nl.robinthedev.app.api.messaging.event.CommentUpdated;
import nl.robinthedev.app.api.model.Comment;
import nl.robinthedev.app.api.model.CommentId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class PostComment {

  @EntityId
  private final CommentId commentId;

  private String text;

  public PostComment(Comment comment) {
    this.commentId = comment.publicId();
    this.text = comment.text();
  }

  @CommandHandler
  void handle(UpdateComment updateComment) {
    //check for profanity
    apply(new CommentUpdated(commentId, text, updateComment.newText()));
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
