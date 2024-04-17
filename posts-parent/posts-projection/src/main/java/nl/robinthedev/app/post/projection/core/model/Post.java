package nl.robinthedev.app.post.projection.core.model;

import java.util.List;
import java.util.UUID;

public interface Post {
  UUID id();

  String title();

  String text();

  List<? extends Comment> comments();
}
