package nl.robinthedev.app.axon.core;

import nl.robinthedev.app.api.messaging.command.CreatePost;
import nl.robinthedev.app.api.messaging.event.PostCreated;
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
  public void testPostCreationCommand() {
    fixture.when(new CreatePost(POST_ID, "a", "b"))
           .expectSuccessfulHandlerExecution()
           .expectEvents(new PostCreated(POST_ID, new Post("a", "b")));
  }
}