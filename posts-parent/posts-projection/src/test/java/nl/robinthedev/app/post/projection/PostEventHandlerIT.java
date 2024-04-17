package nl.robinthedev.app.post.projection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import nl.robinthedev.app.api.messaging.post.event.CommentAdded;
import nl.robinthedev.app.api.messaging.post.event.CommentUpdated;
import nl.robinthedev.app.api.messaging.post.event.PostCreated;
import nl.robinthedev.app.api.messaging.post.model.Comment;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.api.messaging.post.model.Post;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import nl.robinthedev.app.post.projection.axon.PostEventHandler;
import nl.robinthedev.app.post.projection.database.JpaComment;
import nl.robinthedev.app.post.projection.database.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostEventHandlerIT {

  public static final UUID POST_UUID = UUID.randomUUID();
  private static final PostId POST_ID = new PostId(POST_UUID);
  public static final UUID COMMENT_UUID = UUID.randomUUID();
  private static final CommentId COMMENT_ID = new CommentId(COMMENT_UUID);

  @Autowired private PostEventHandler handler;

  @Autowired private PostRepository postRepository;

  @Test
  void createdPost() {
    handler.handle(new PostCreated(POST_ID, new Post("x", "y")));
    assertThat(postRepository.findByPublicId(POST_UUID)).isPresent();
  }

  @Test
  void addedComment() {
    handler.handle(new PostCreated(POST_ID, new Post("x", "y")));
    handler.handle(new CommentAdded(POST_ID, new Comment(COMMENT_ID, "my comment")));
    assertThat(postRepository.findByPublicId(POST_UUID))
        .hasValueSatisfying(
            jpaPost -> {
              JpaComment comment = jpaPost.getComments().getFirst();
              assertThat(comment.getText()).isEqualTo("my comment");
              assertThat(comment.getPublicId()).isEqualTo(COMMENT_ID.commentId());
            });
  }

  @Test
  void updatedComment() {
    handler.handle(new PostCreated(POST_ID, new Post("x", "y")));
    handler.handle(new CommentAdded(POST_ID, new Comment(COMMENT_ID, "my comment")));
    handler.handle(new CommentUpdated(COMMENT_ID, "my comment", "my new comment"));
    assertThat(postRepository.findByPublicId(POST_UUID))
        .hasValueSatisfying(
            jpaPost ->
                assertThat(jpaPost.getComments().getFirst().getText()).isEqualTo("my new comment"));
  }
}
