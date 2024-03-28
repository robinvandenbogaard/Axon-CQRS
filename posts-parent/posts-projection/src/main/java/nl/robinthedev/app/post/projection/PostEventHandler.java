package nl.robinthedev.app.post.projection;

import nl.robinthedev.app.api.messaging.post.event.CommentAdded;
import nl.robinthedev.app.api.messaging.post.event.CommentUpdated;
import nl.robinthedev.app.api.messaging.post.event.PostCreated;
import nl.robinthedev.app.api.messaging.post.model.Post;
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
    JpaPost entity = new JpaPost(postCreated.postId().postId(), post.title(), post.content());
    postService.createPost(entity);
  }

  @EventHandler
  void handle(CommentAdded commentAdded) {
    JpaComment entity = new JpaComment(commentAdded.comment());
    postService.addCommentToPost(commentAdded.postId(), entity);
  }

  @EventHandler
  public void handle(CommentUpdated commentUpdated) {
    postService.updateCommentText(commentUpdated.commentId(), commentUpdated.newText());
  }
}