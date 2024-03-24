package nl.robinthedev.app.api.model;

import java.util.UUID;

public record CommentId(UUID commentId) {
  public static CommentId fromString(String uuid) {
    return new CommentId(UUID.fromString(uuid));
  }
}
