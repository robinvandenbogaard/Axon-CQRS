package nl.robinthedev.app.post.query;

import nl.robinthedev.app.api.messaging.event.PostCreated;
import nl.robinthedev.app.api.model.Post;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
class PostEventHandler {

  private final PostService postService;

  public PostEventHandler(PostService postService) {
    this.postService = postService;
  }

  @EventHandler
  void handle(PostCreated postCreated) {
    Post post = postCreated.post();
    JpaPost entity = new JpaPost(post.title(), post.content());
    postService.createPost(entity);
  }
}
