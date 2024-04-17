package nl.robinthedev.app.post.projection.rest;

import java.util.List;
import java.util.UUID;
import nl.robinthedev.app.post.projection.core.model.Post;

record RPost(UUID id, String title, String text, List<RComment> comments) {
  public static RPost from(Post post) {
    return new RPost(
        post.id(),
        post.title(),
        post.text(),
        post.comments().stream().map(RComment::from).toList());
  }
}
