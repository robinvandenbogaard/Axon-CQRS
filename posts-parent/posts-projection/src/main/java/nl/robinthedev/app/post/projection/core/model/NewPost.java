package nl.robinthedev.app.post.projection.core.model;

import java.util.UUID;

public interface NewPost {
  UUID id();

  String title();

  String text();
}
