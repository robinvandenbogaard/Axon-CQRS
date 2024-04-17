package nl.robinthedev.app.post.projection.core.model;

import java.util.UUID;

public interface UpdatedComment {
  UUID commentId();

  String newText();
}
