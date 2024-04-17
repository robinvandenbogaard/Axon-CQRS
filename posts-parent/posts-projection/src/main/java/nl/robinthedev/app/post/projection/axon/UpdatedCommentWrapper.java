package nl.robinthedev.app.post.projection.axon;

import java.util.UUID;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.post.projection.core.model.UpdatedComment;

record UpdatedCommentWrapper(CommentId id, String text) implements UpdatedComment {
  @Override
  public UUID commentId() {
    return id.commentId();
  }

  @Override
  public String newText() {
    return text;
  }
}
