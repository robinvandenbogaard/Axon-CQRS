package nl.robinthedev.app.post.projection.axon;

import nl.robinthedev.app.api.messaging.post.event.CommentAdded;
import nl.robinthedev.app.api.messaging.post.event.CommentUpdated;
import nl.robinthedev.app.api.messaging.post.event.PostCreated;
import nl.robinthedev.app.post.projection.core.PostService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class PostEventHandler {

  private final PostService postService;

  public PostEventHandler(PostService postService) {
    this.postService = postService;
  }

  @EventHandler
  public void handle(PostCreated postCreated) {
    postService.createPost(new NewPostWrapper(postCreated.postId(), postCreated.post()));
  }

  @EventHandler
  public void handle(CommentAdded commentAdded) {
    postService.addCommentToPost(
        new CommentWrapper(
            commentAdded.postId().postId(),
            commentAdded.comment().publicId().commentId(),
            commentAdded.comment().text()));
  }

  @EventHandler
  public void handle(CommentUpdated commentUpdated) {
    postService.updateCommentText(
        new UpdatedCommentWrapper(commentUpdated.commentId(), commentUpdated.newText()));
  }
}
