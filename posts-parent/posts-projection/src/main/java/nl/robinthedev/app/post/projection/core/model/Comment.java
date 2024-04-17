package nl.robinthedev.app.post.projection.core.model;

import java.util.UUID;

public interface Comment {
  UUID id();

  String text();
}
