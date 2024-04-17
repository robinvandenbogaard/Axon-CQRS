package nl.robinthedev.app.post.projection.rest;

import java.util.UUID;
import nl.robinthedev.app.post.projection.core.model.Comment;

record RComment(UUID id, String text) {
  public static RComment from(Comment comment) {
    return new RComment(comment.id(), comment.text());
  }
}
