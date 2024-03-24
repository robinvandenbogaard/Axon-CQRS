package nl.robinthedev.app.post.query;

import nl.robinthedev.app.api.messaging.event.CommentAdded;
import nl.robinthedev.app.api.messaging.event.PostCreated;
import nl.robinthedev.app.api.model.Comment;
import nl.robinthedev.app.api.model.CommentId;
import nl.robinthedev.app.api.model.Post;
import nl.robinthedev.app.api.model.PostId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostEventHandlerIT {

  public static final UUID POST_UUID = UUID.randomUUID();
  private static final PostId POST_ID = new PostId(POST_UUID);
  public static final UUID COMMENT_UUID = UUID.randomUUID();
  private static final CommentId COMMENT_ID = new CommentId(COMMENT_UUID);

  @Autowired
  private PostEventHandler handler;

  @Autowired
  private PostRepository postRepository;

  @Test
  void createdPost() {
    handler.handle(new PostCreated(POST_ID, new Post("x", "y")));
    assertThat(postRepository.findByPublicId(POST_UUID)).isPresent();
  }

  @Test
  void addedComment() {
    handler.handle(new PostCreated(POST_ID, new Post("x", "y")));
    handler.handle(new CommentAdded(POST_ID, new Comment(COMMENT_ID, "my comment")));
    assertThat(postRepository.findByPublicId(POST_UUID)).hasValueSatisfying(jpaPost -> assertThat(jpaPost.getComments()
                                                                                                         .getFirst()
                                                                                                         .getText()).isEqualTo("my comment"));
  }
}