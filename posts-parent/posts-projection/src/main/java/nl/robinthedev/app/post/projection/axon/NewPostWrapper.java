package nl.robinthedev.app.post.projection.axon;

import java.util.UUID;
import nl.robinthedev.app.api.messaging.post.model.Post;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import nl.robinthedev.app.post.projection.core.model.NewPost;

record NewPostWrapper(PostId postId, Post post) implements NewPost {
  @Override
  public UUID id() {
    return postId.postId();
  }

  @Override
  public String title() {
    return post.title();
  }

  @Override
  public String text() {
    return post.content();
  }
}
