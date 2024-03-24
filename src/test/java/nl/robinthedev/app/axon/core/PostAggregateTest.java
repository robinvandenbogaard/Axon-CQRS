package nl.robinthedev.app.axon.core;

import nl.robinthedev.app.api.messaging.command.AddComment;
import nl.robinthedev.app.api.messaging.command.CreatePost;
import nl.robinthedev.app.api.messaging.command.UpdateComment;
import nl.robinthedev.app.api.messaging.event.CommentAdded;
import nl.robinthedev.app.api.messaging.event.CommentUpdated;
import nl.robinthedev.app.api.messaging.event.PostCreated;
import nl.robinthedev.app.api.model.Comment;
import nl.robinthedev.app.api.model.CommentId;
import nl.robinthedev.app.api.model.Post;
import nl.robinthedev.app.api.model.PostId;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PostAggregateTest {

  private static final PostId POST_ID = new PostId(UUID.randomUUID());

  AggregateTestFixture<PostAggregate> fixture;

  @BeforeEach
  public void setUp() {
    fixture = new AggregateTestFixture<>(PostAggregate.class);
  }

  @Test
  public void testCreatePostCommand() {
    fixture.when(new CreatePost(POST_ID, "a", "b"))
           .expectSuccessfulHandlerExecution()
           .expectEvents(new PostCreated(POST_ID, new Post("a", "b")));
  }

  @Test
  public void testAddCommentCommand() {
    CommentId commentId = new CommentId(UUID.randomUUID());
    fixture.given(new PostCreated(POST_ID, new Post("a", "b")))
           .when(new AddComment(POST_ID, commentId, "c"))
           .expectSuccessfulHandlerExecution()
           .expectEvents(new CommentAdded(POST_ID, new Comment(commentId, "c")));
  }

  @Test
  public void testUpdateCommentCommand() {
    CommentId commentId = new CommentId(UUID.randomUUID());
    fixture.given(new PostCreated(POST_ID, new Post("a", "b")))
           .andGiven(new CommentAdded(POST_ID, new Comment(commentId, "c")))
           .when(new UpdateComment(POST_ID, commentId, "d"))
           .expectSuccessfulHandlerExecution()
           .expectEvents(new CommentUpdated(commentId, "c", "d"));
  }
}